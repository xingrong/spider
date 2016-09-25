package com.rong.spider.parser.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaobaoItemParser {
	private FirefoxDriver driver;
	private JavascriptExecutor js;

	private final static Logger logger = LoggerFactory
			.getLogger(TaobaoItemParser.class);

	public TaobaoItemParser() {
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
	}

	public void parse(String url) {
		driver.get(url);
		String html = driver.getPageSource();
		Pattern pattern = Pattern
				.compile("<span class=\"tm-price\">\\d+.\\d+</span>");
		Matcher matcher = pattern.matcher(html);
		if (matcher.find()) {
			String str = matcher.group();
			pattern = Pattern.compile("\\d+.\\d+");
			matcher = pattern.matcher(str);
			if (matcher.find()) {
				logger.info(matcher.group());
			}
		}
		pattern = Pattern.compile("<p class=\"tm-count\">\\d+</p>");
		matcher = pattern.matcher(html);
		if (matcher.find()) {
			String str = matcher.group();
			pattern = Pattern.compile("\\d+");
			matcher = pattern.matcher(str);
			if (matcher.find()) {
				logger.info(matcher.group());
			}
		}
	}

	public static void main(String[] args) {
		TaobaoItemParser parser = new TaobaoItemParser();
		parser.parse("http://detail.tmall.com/item.htm?spm=a2106.m896.1000384.8.8JYnF1&id=38453676599&source=dou&scm=1029.newlist-0.bts2.50016864&ppath=&sku=&ug=");
	}

}
