package org.rong.spider.util;

/**
 * Project Constants
 */
public class SpiderConstants {
	public static String BIN_DIR = "bin/";
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
	public static final int STORAGE_THREAD_INTERVAL = 100;
	public static final String PARSER_MODULE_CLASSPATH = "org.rong.spider.parser.module.";
	public static final String PARSER_STORAGE_PATH = DATA_DIR + "parser/";
	
	/**
	 * storage configure
	 */
	public static final int MAX_QUEUE_SIZE = 100;
	public static final int OUTPUT_INTERNAL = 1000;
}
