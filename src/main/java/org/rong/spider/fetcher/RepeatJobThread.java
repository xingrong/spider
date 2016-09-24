package org.rong.spider.fetcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Background thread approach to repeat jobs
 * 
 * @author Rong
 * 
 */
public class RepeatJobThread implements Runnable {
	final static Logger logger = LoggerFactory
			.getLogger(RepeatJobThread.class);

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
