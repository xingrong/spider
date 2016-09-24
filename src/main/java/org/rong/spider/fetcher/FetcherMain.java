package org.rong.spider.fetcher;

import java.util.LinkedList;

import org.json.JSONObject;
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
	}
	
	public synchronized static JSONObject getFetcherJob() {
		return fetcherJobQueue.poll();
	}

	public synchronized static void addFetcherJob(JSONObject jobObject) {
		fetcherJobQueue.add(jobObject);
	}
}
