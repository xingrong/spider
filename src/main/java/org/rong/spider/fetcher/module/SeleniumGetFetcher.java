package org.rong.spider.fetcher.module;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fetcher module using Selenium and Get method
 * 
 * @author Rong
 * 
 */
public class SeleniumGetFetcher extends FetcherBase {
	final static Logger logger = LoggerFactory
			.getLogger(SeleniumGetFetcher.class);

	public boolean beforeFetch(JSONObject jobObject) {
		return true;
	}

	public boolean fetch(JSONObject jobObject) {
		ChromeDriver driver = null;
		try {
			String url = jobObject.getString("url");

			System.setProperty("webdriver.chrome.driver",
					SpiderConstants.BIN_DIR + "chromedriver.exe");

			// disable image
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.managed_default_content_settings.images", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
			chromeCaps.setCapability(ChromeOptions.CAPABILITY, options);

			driver = new ChromeDriver(chromeCaps);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			Cookie testCookie = new Cookie("CUSTOMER_SEGMENT_IDS",
					"30%2C32%2C34%2C41");
			driver.get(url);
			driver.manage().addCookie(testCookie);
			driver.get(url);
			jobObject.put("src_content", driver.getPageSource());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (driver != null) {
				driver.close();
			}
		}
		return true;
	}

	public void afterFetch(JSONObject jobObject) {
	}

	public void close(JSONObject jobObject) {
	}
}
