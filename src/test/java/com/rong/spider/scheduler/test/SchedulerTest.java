package com.rong.spider.scheduler.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.rong.spider.scheduler.main.SchedulerMain;
import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class SchedulerTest extends TestCase {
	private final static Logger logger = LoggerFactory
			.getLogger(SchedulerTest.class);

	public void testScheduler() throws Exception {
		// SchedulerMain schedulerMain = SchedulerMain.getInstance();
		// Runtime rt = Runtime.getRuntime();
		// Process p =
		// rt.exec("C:/phantomjs-2.1.1-windows/bin/phantomjs.exe C:/Users/aaa/Work/Java_Work/test.js http://www.baidu.com");//这里我的codes.js是保存在c盘下面的phantomjs目录
		// InputStream is = p.getInputStream();
		// BufferedReader br = new BufferedReader(new InputStreamReader(is));
		// StringBuffer sbf = new StringBuffer();
		// String tmp = "";
		// while((tmp = br.readLine())!=null){
		// sbf.append(tmp);
		// }
		// System.out.println(sbf.toString());

		System.setProperty("webdriver.chrome.driver", SpiderConstants.DATA_DIR
				+ "chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		// FirefoxDriver driver = new FirefoxDriver();
		//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
//		Cookie baiduUID = new Cookie(
//				"cookie",
//				"CACHED_FRONT_FORM_KEY; SLIBeacon_494863495; SLI1_494863495; SLI2_494863495; LAST_CATEGORY; CATEGORY_INFO; frontend; CUSTOMER_SEGMENT_IDS; VIEWED_PRODUCT_IDS");
		//driver.manage().addCookie(baiduUID);
//		CACHED_FRONT_FORM_KEY=PAF5tGEFkSUr3KSL; VIEWED_PRODUCT_IDS=392%2C446; LAST_CATEGORY=24; CATEGORY_INFO=%5B%5D
//		CACHED_FRONT_FORM_KEY; SLIBeacon_494863495; SLI1_494863495; SLI2_494863495; LAST_CATEGORY; CATEGORY_INFO; frontend; CUSTOMER_SEGMENT_IDS; VIEWED_PRODUCT_IDS
		
		Cookie testCookie = new Cookie("CUSTOMER_SEGMENT_IDS", "30%2C32%2C34%2C41");
		
		String url = "http://www.sli-demo.com/home-decor/electronics/madison-lx2200.html";
		
		//driver.get("http://www.sli-demo.com/home-decor/electronics.html");
		driver.get(url);
		Set<Cookie> cookieSet = driver.manage().getCookies();
		for (Cookie c : cookieSet) {
			logger.info(c.getName()+" => " + c.getValue());
		}
		driver.manage().addCookie(testCookie);
		//driver.manage().addCookie(new Cookie("CACHED_FRONT_FORM_KEY",""));
		driver.get(url);
		Thread.sleep(1000);
		driver.get("http://www.sli-demo.com/home-decor/electronics/madison-rx3400.html");
		Thread.sleep(1000);
		driver.get("http://www.sli-demo.com/home-decor/electronics/mp3-player-with-audio.html");
		
		String pageSource = driver.getPageSource();
		logger.info("#################################################"
				+ pageSource + "##############################################");
		// driver.close();
	}
}
