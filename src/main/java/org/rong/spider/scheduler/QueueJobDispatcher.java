package org.rong.spider.scheduler;

import org.json.JSONObject;
import org.rong.spider.db.model.Job;
import org.rong.spider.fetcher.FetcherMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Background thread approach to dispatch jobs
 * 
 * @author Rong
 * 
 */
public class QueueJobDispatcher implements Runnable {
	final static Logger logger = LoggerFactory
			.getLogger(QueueJobDispatcher.class);

	public void run() {
		while (true) {
			JSONObject jobObject = SchedulerMain.getSchedulerJob();
			FetcherMain.addFetcherJob(jobObject);
			sleep(100);
		}
	}
	
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
