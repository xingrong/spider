package org.rong.spider.parser;

import java.util.LinkedList;
import org.rong.spider.db.model.Job;
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
	private static LinkedList<Job> parserJobQueue = new LinkedList<Job>();

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
	}

	public synchronized static Job getParserJob() {
		return parserJobQueue.poll();
	}

	public synchronized static void addParserJob(Job job) {
		parserJobQueue.add(job);
	}
}
