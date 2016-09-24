package org.rong.spider.util;

/**
 * Constants
 */
public class SpiderConstants {
	public static String CONF_DIR = "conf/";
	public static String DATA_DIR = "data/";
	
	/**
	 * scheduler configure
	 */
	public static final int SCHEDULER_THREAD_INTERVAL = 100;
	
	/**
	 * fetcher configure
	 */
	public static final int FETCHER_THREAD_INTERVAL = 100;
	public static final String FETCHER_MODULE_CLASSPATH = "org.rong.spider.fetcher.module.";
	
	/**
	 * parser configure
	 */
	public static final int PARSER_THREAD_INTERVAL = 100;
	public static final String PARSER_MODULE_CLASSPATH = "org.rong.spider.parser.module.";
	
	/**
	 * storage configure
	 */
	public static final int MAX_QUEUE_SIZE = 100;
	public static final int OUTPUT_INTERNAL = 1000;
}
