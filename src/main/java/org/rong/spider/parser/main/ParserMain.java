package org.rong.spider.parser.main;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;
import org.rong.spider.parser.storage.OutputTask;
import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * parser main entry
 * 
 * @author Rong
 * 
 */
public class ParserMain {
	final static Logger logger = LoggerFactory.getLogger(ParserMain.class);
	private static ParserMain parserMain = null;
	private static LinkedList<JSONObject> parserJobQueue = new LinkedList<JSONObject>();

	/**
	 * singleton schema
	 * 
	 * @return
	 */
	public synchronized static ParserMain getInstance() {
		if (null == parserMain) {
			parserMain = new ParserMain();
		}
		return parserMain;
	}

	private ParserMain() {
		logger.info("init parser");
		initParser();

		logger.info("start parser thread");
		startParserThread();
		
		logger.info("start storage thread");
		startOutputTask();
	}

	private void initParser() {
		new ParserInit(SpiderConstants.CONF_DIR + "/parser.properties");
	}

	private void startParserThread() {
		ExecutorService exec = Executors
				.newFixedThreadPool(ParserInit.threadCount);
		ParserThread[] threadList = new ParserThread[ParserInit.threadCount];
		for (int i = 0; i < ParserInit.threadCount; ++i) {
			threadList[i] = new ParserThread();
		}
		for (int i = 0; i < ParserInit.threadCount; ++i) {
			exec.execute(threadList[i]);
		}
		exec.shutdown();
	}
	
	private void startOutputTask() {
		OutputTask outputTask = new OutputTask(SpiderConstants.DATA_DIR + "/"
				+ "test.txt");
		new Thread(outputTask).start();
	}

	public synchronized static JSONObject getParserJob() {
		return parserJobQueue.poll();
	}

	public synchronized static void addParserJob(JSONObject jobObject) {
		parserJobQueue.add(jobObject);
	}
}
