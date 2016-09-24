package org.rong.spider.fetcher.main;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;
import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * fetcher main entry
 * 
 * @author Rong
 * 
 */
public class FetcherMain {
	final static Logger logger = LoggerFactory.getLogger(FetcherMain.class);
	private static FetcherMain FetcherMain = null;
	private static LinkedList<JSONObject> fetcherJobQueue = new LinkedList<JSONObject>();

	/**
	 * singleton schema
	 * 
	 * @return
	 */
	public synchronized static FetcherMain getInstance() {
		if (null == FetcherMain) {
			FetcherMain = new FetcherMain();
		}
		return FetcherMain;
	}

	private FetcherMain() {
		logger.info("init fetcher");
		initFetcher();
		
		logger.info("start fetcher thread");
		startFetcherThread();
	}
	
	private void initFetcher() {
		new FetcherInit(SpiderConstants.CONF_DIR + "/fetcher.properties");
	}

	private void startFetcherThread() {
		ExecutorService exec = Executors
				.newFixedThreadPool(FetcherInit.threadCount);
		FetcherThread[] threadList = new FetcherThread[FetcherInit.threadCount];
		for (int i = 0; i < FetcherInit.threadCount; ++i) {
			threadList[i] = new FetcherThread();
		}
		for (int i = 0; i < FetcherInit.threadCount; ++i) {
			exec.execute(threadList[i]);
		}
		exec.shutdown();
	}
	
	public synchronized static JSONObject getFetcherJob() {
		return fetcherJobQueue.poll();
	}

	public synchronized static void addFetcherJob(JSONObject jobObject) {
		fetcherJobQueue.add(jobObject);
	}
}
