package org.rong.spider.scheduler;

import java.util.LinkedList;

import org.json.JSONObject;
import org.rong.spider.db.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * scheduler main entry
 * 
 * @author Rong
 * 
 */
public class SchedulerMain {
	final static Logger logger = LoggerFactory.getLogger(SchedulerMain.class);
	private static SchedulerMain schedulerMain = null;
	private static LinkedList<JSONObject> schedulerJobQueue = new LinkedList<JSONObject>();

	/**
	 * singleton schema
	 * 
	 * @return
	 */
	public synchronized static SchedulerMain getInstance() {
		if (null == schedulerMain) {
			schedulerMain = new SchedulerMain();
		}
		return schedulerMain;
	}

	private SchedulerMain() {
	}
	
	public synchronized static JSONObject getSchedulerJob() {
		return schedulerJobQueue.poll();
	}

	public synchronized static void addSchedulerJob(JSONObject jobObject) {
		schedulerJobQueue.add(jobObject);
	}
}
