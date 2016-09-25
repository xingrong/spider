package com.rong.spider.parser.test;

import org.json.JSONObject;
import org.rong.spider.parser.module.SLIDemoItemParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class ElectronicsItemParserTest extends TestCase {
	private final static Logger logger = LoggerFactory
			.getLogger(ElectronicsItemParserTest.class);

	public void testParser() {
		SLIDemoItemParser parser = new SLIDemoItemParser();
		JSONObject jobObject = new JSONObject();
		parser.parse(jobObject);
	}
}
