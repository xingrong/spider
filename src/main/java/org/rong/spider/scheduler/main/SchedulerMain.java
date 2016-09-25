package org.rong.spider.scheduler.main;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.rong.spider.scheduler.db.Job;
import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * scheduler main
 * 
 * @author Rong
 * 
 */
public class SchedulerMain {
	final static Logger logger = LoggerFactory.getLogger(SchedulerMain.class);
	private static SchedulerMain schedulerMain = null;
	private static LinkedList<Job> schedulerJobQueue = new LinkedList<Job>();

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
		logger.info("#### init scheduler ####");
		initScheduler();

		logger.info("#### start scheduler thread ####");
		startQueueJobDispatcher();
	}

	private void initScheduler() {
		new SchedulerInit(SpiderConstants.CONF_DIR + "/scheduler.properties");
	}

	private void startQueueJobDispatcher() {
		ExecutorService exec = Executors
				.newFixedThreadPool(SchedulerInit.threadCount);
		SchedulerThread[] threadList = new SchedulerThread[SchedulerInit.threadCount];
		for (int i = 0; i < SchedulerInit.threadCount; ++i) {
			threadList[i] = new SchedulerThread();
		}
		for (int i = 0; i < SchedulerInit.threadCount; ++i) {
			exec.execute(threadList[i]);
		}
		exec.shutdown();
	}

	public synchronized static Job getSchedulerJob() {
		return schedulerJobQueue.poll();
	}

	public synchronized static void addSchedulerJob(Job job) {
		schedulerJobQueue.add(job);
	}
}
