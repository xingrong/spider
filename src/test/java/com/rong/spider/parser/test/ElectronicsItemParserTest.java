package com.rong.spider.parser.test;

import org.json.JSONObject;
import org.rong.spider.parser.module.ElectronicsItemParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class ElectronicsItemParserTest extends TestCase {
	private final static Logger logger = LoggerFactory
			.getLogger(ElectronicsItemParserTest.class);

	public void testParser() {
		ElectronicsItemParser parser = new ElectronicsItemParser();
		JSONObject jobObject = new JSONObject();
		parser.parse(jobObject);
	}
}
