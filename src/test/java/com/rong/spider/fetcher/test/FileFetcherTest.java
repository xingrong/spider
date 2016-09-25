package com.rong.spider.fetcher.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileFetcherTest extends TestCase {
	private final static Logger logger = LoggerFactory
			.getLogger(FileFetcherTest.class);

	public void testFetcher() {
		String REMOTE_FILE_URL = "http://xinpi.cs.com.cn/file/bulletin/2014/12/1200492384.PDF";
		int BUFFER = 1024;

		logger.info("tewwww");
		HttpClient client = new HttpClient();
		GetMethod httpGet = new GetMethod(REMOTE_FILE_URL);
		try {
			client.executeMethod(httpGet);
			InputStream in = httpGet.getResponseBodyAsStream();
			String filename=REMOTE_FILE_URL.substring(REMOTE_FILE_URL.lastIndexOf("/") + 1);
			FileOutputStream out = new FileOutputStream(new File(
					"C:\\" + filename));
			byte[] b = new byte[BUFFER];
			int len = 0;
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			in.close();
			out.close();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpGet.releaseConnection();
		}
		logger.info("download, success!!");
	}
}
