package com.rong.spider.scheduler.test;

import org.rong.spider.scheduler.main.SchedulerMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class SchedulerTest extends TestCase {
	private final static Logger logger = LoggerFactory
			.getLogger(SchedulerTest.class);

	public void testScheduler() {
		SchedulerMain schedulerMain = SchedulerMain.getInstance();
	}
}
