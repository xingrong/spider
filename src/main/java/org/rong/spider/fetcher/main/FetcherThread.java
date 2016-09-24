package org.rong.spider.fetcher.main;

import org.json.JSONObject;
import org.rong.spider.fetcher.module.FetcherBase;
import org.rong.spider.parser.main.ParserMain;
import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Background thread approach to crawl
 * 
 * @author Rong
 * 
 */
public class FetcherThread implements Runnable {
	final static Logger logger = LoggerFactory.getLogger(FetcherThread.class);

	public void run() {
		while (true) {
			try {
				JSONObject jobObject = FetcherMain.getFetcherJob();
				if (jobObject != null) {
					FetcherBase fetcher = (FetcherBase) (Class
							.forName(SpiderConstants.FETCHER_MODULE_CLASSPATH
									+ jobObject.getString("fetcher_module"))
							.newInstance());
					if (fetcher.fetch(jobObject)) {
						ParserMain.addParserJob(jobObject);
						logger.info("add parser job => " + jobObject.getString("parser_module"));
					} else {
						logger.error("fetcher crawl error : "
								+ jobObject.toString());
					}
				}
				Thread.sleep(SpiderConstants.FETCHER_THREAD_INTERVAL);
			} catch (Exception e) {
				logger.error(e.getMessage());
				continue;
			}
		}
	}
}
