package com.rong.spider.fetcher.test;

import java.util.Set;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumFetcherWithClickTest extends TestCase {
	private final static Logger logger = LoggerFactory
			.getLogger(SeleniumFetcherWithClickTest.class);

	public void testDriver() {
		FirefoxDriver driver = new FirefoxDriver();
		String url = "http://data.weibo.com/index";
		driver.get(url);
		// driver.navigate().
		// driver.setJavascriptEnabled(true);
		// driver.executeScript("window.scrollBy(0,document.body.scrollHeight)",
		// "");

		// Cookie baiduUID = new Cookie("BAIDUID",
		// "3763D40DDE48C57C085A8541F90F148C:FG=1");
		// Cookie bauSS = new Cookie(
		// "BDUSS",
		// "BHZUNCWDl6SFdsLXZ3eXlxaW9rZH5TWHJ3VjJiTDRTbVJYRjFQTVRTRjlqbzFUQVFBQUFBJCQAAAAAAAAAAAEAAADQTNsAeGluZ3JvbmcwODA0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH0BZlN9AWZTen");
		// driver.manage().deleteAllCookies();
		// driver.manage().addCookie(bauSS);
		// driver.manage().addCookie(baiduUID);

		// Cookie uc1 = new Cookie("uc1", "cookie14=UoLVbEWto3kJZA%3D%3D");
		// Cookie v = new Cookie("v", "0");
		// Cookie cookie2 = new Cookie("cookie2",
		// "3f8a2399fcb0b10a5c8f07cb9a0d57e1");
		// Cookie t = new Cookie("t", "dfd95552588bdec17e8b5e3d4433ae02");
		// driver.manage().deleteAllCookies();
		// driver.manage().addCookie(uc1);
		// driver.manage().addCookie(v);
		// driver.manage().addCookie(cookie2);
		// driver.manage().addCookie(t);
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// if (!driver.getCurrentUrl().startsWith(url)) {
		// try {
		// driver.findElement(By.id("TPL_username_1")).clear();
		// driver.findElement(By.id("TPL_username_1")).sendKeys("xingrong0804");
		//
		// driver.findElement(By.id("TPL_password_1")).clear();
		// driver.findElement(By.id("TPL_password_1")).sendKeys("franksnow0804");
		// driver.findElement(By.id("J_SubmitStatic")).click();
		// } catch (Exception e) {
		// logger.error("login error, error: " + e.getMessage());
		// }
		// driver.switchTo().defaultContent();
		// }
		try {
			driver.findElement(By.id("TPL_username_1")).clear();
			driver.findElement(By.id("TPL_username_1"))
					.sendKeys("xingrong");

			driver.findElement(By.id("TPL_password_1")).clear();
			driver.findElement(By.id("TPL_password_1")).sendKeys(
					"test");
			driver.findElement(By.id("J_SubmitStatic")).click();
		} catch (Exception e) {
			logger.error("login error, error: " + e.getMessage());
		}
		driver.switchTo().defaultContent();
		// Set<Cookie> cookies = driver.manage().getCookies();
		// String cookieStr = "";
		// for (Cookie cookie : cookies) {
		// cookieStr += cookie.getName() + "=" + cookie.getValue() + "; ";
		// }
		// driver.navigate().refresh();
		// String title = driver.getTitle();
		String pageSource = driver.getPageSource();
		logger.info("#################################################"
				+ pageSource + "##############################################");
		driver.close();
	}

}
