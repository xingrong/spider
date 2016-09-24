package org.rong.spider.scheduler.main;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.rong.spider.util.MybatisSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * init for scheduler
 * 
 * @author Rong
 * 
 */
public class SchedulerInit {
	final static Logger logger = LoggerFactory.getLogger(SchedulerInit.class);
	
	public static int threadCount = 2;

	public SchedulerInit(String confFile) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					confFile));
			props.load(in);
		} catch (IOException e) {
			logger.error("can't load config file: " + confFile);
		}
		try {
			String property = "";
			MybatisSessionFactory.init(props);
			
			property = "thread.count";
			if (props.getProperty(property) != null
					&& !props.getProperty(property).trim().equals("")) {
				SchedulerInit.threadCount = Integer.parseInt(props.getProperty(property));
				logger.info("scheduler thread count : " + SchedulerInit.threadCount);
			} else {
				logger.error("missing thread count config");
				System.exit(-1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
