package org.rong.spider.main;

import org.rong.spider.fetcher.main.FetcherMain;
import org.rong.spider.parser.main.ParserMain;
import org.rong.spider.scheduler.db.Job;
import org.rong.spider.scheduler.main.SchedulerMain;
import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * main entrance
 * 
 * use -c [conf_dir] -d [data_dir] to run main, or you will use default conf_dir
 * and data_dir
 * 
 * @author Rong
 * 
 */
public class Main {
	final static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {

		if (args.length == 4) {
			SpiderConstants.CONF_DIR = args[1];
			SpiderConstants.DATA_DIR = args[3];
			logger.info("Use customized conf_dir : " + SpiderConstants.CONF_DIR
					+ " and data_dir : " + SpiderConstants.DATA_DIR);
		} else {
			logger.info("Use default conf_dir : " + SpiderConstants.CONF_DIR
					+ " and data_dir : " + SpiderConstants.DATA_DIR);
			logger.info("If you wanna customize conf_dir, please use -c [conf_dir] -d [data_dir]");
		}

		SchedulerMain.getInstance();
		FetcherMain.getInstance();
		ParserMain.getInstance();
		
		// demo for item page crawling
		// read job description from database in production environment
		Job itemJob = new Job();
		itemJob.setId(0);
		itemJob.setName("sli_demo_item");
		itemJob.setUrls_file(SpiderConstants.DATA_DIR + "/urls/sli_demo.item");
		itemJob.setFetcher_module("SeleniumGetFetcher");
		itemJob.setParser_module("SLIDemoItemParser");
		SchedulerMain.addSchedulerJob(itemJob);

		// demo for hub page crawling
		Job hubJob = new Job();
		hubJob.setId(1);
		hubJob.setName("sli_demo_hub");
		hubJob.setUrls_file(SpiderConstants.DATA_DIR + "/urls/sli_demo.hub");
		hubJob.setFetcher_module("HttpGetFetcher");
		hubJob.setParser_module("SLIDemoHubParser");
		SchedulerMain.addSchedulerJob(hubJob);
	}
}
