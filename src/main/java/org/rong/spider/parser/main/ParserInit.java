package org.rong.spider.parser.main;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.rong.spider.util.MybatisSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * init for parser
 * 
 * @author Rong
 * 
 */
public class ParserInit {
	final static Logger logger = LoggerFactory.getLogger(ParserInit.class);
	
	public static int threadCount = 2;

	public ParserInit(String confFile) {
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
				ParserInit.threadCount = Integer.parseInt(props.getProperty(property));
				logger.info("parser thread count : " + ParserInit.threadCount);
			} else {
				logger.error("missing thread count config");
				System.exit(-1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
