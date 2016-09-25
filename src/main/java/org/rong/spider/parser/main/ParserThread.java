package org.rong.spider.parser.main;

import org.json.JSONObject;
import org.rong.spider.parser.module.ParserBase;
import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Background thread approach to parse
 * 
 * @author Rong
 * 
 */
public class ParserThread implements Runnable {
	final static Logger logger = LoggerFactory.getLogger(ParserThread.class);

	public void run() {
		while (true) {
			try {
				JSONObject jobObject = ParserMain.getParserJob();
				if (jobObject != null) {
					ParserBase parser = (ParserBase) (Class
							.forName(SpiderConstants.PARSER_MODULE_CLASSPATH
									+ jobObject.getString("parser_module"))
							.newInstance());
					if (!parser.parse(jobObject)) {
						logger.error("parser parse error : "
								+ jobObject.toString());
					}
				}
				Thread.sleep(SpiderConstants.PARSER_THREAD_INTERVAL);
			} catch (Exception e) {
				logger.error(e.getMessage());
				continue;
			}
		}
	}
}
