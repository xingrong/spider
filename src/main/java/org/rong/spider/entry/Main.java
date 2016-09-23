package org.rong.spider.entry;

import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * main entry
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

		long mainStartTime = System.currentTimeMillis();

		// main entry

		long mainEndTime = System.currentTimeMillis();
		logger.info("TaskMain done : " + (mainEndTime - mainStartTime) / 1000
				+ " seconds");
		System.exit(0);
	}
}
