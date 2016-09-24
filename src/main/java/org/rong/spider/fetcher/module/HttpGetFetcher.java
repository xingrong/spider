package org.rong.spider.fetcher.module;

import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONObject;
import org.mozilla.intl.chardet.nsDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpGetFetcher extends FetcherBase {
	final static Logger logger = LoggerFactory.getLogger(HttpGetFetcher.class);

	public boolean beforeFetch(JSONObject jobObject) {
		return true;
	}

	public boolean fetch(JSONObject jobObject) {
		HttpClient httpClient = new HttpClient();
		String url = jobObject.getString("url");
		logger.info("fetching url: " + url);
		GetMethod get = new GetMethod(url);

		get.setRequestHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.setRequestHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
		get.setRequestHeader("Connection", "keep-alive");
		get.getParams().setContentCharset("UTF-8");
		get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		try {
			int status = httpClient.executeMethod(get);
			if (status != HttpStatus.SC_OK) {
				logger.error("Crawl failed, error: " + get.getStatusLine()
						+ " url: " + url);
				return false;
			}

			byte[] dataResponseBody = get.getResponseBody();
			String result = get.getResponseBodyAsString();

			String rType = getCharSet(dataResponseBody);

			if (!rType.equals("UTF-8")) {
				result = new String(dataResponseBody, Charset.forName("GBK"));
			}

			logger.info("doc url= " + url + ", charset = " + rType
					+ ", download file size= " + result.length());
			jobObject.put("src_content", result);
		} catch (HttpException e) {
			logger.error("HtmlFetcher Crawl, Fatal error: " + e.getMessage()
					+ " url: " + url);
			return false;
		} catch (IOException e) {
			logger.equals("HtmlFetcher Crawl, Net error: " + e.getMessage()
					+ " url: " + url);
			return false;
		} finally {
			get.releaseConnection();
		}
		return true;
	}

	public void afterFetch(JSONObject jobObject) {
	}

	public void close(JSONObject jobObject) {
	}

	private static String getCharSet(byte[] buf) {
		nsDetector det = new nsDetector(3);
		det.DoIt(buf, buf.length, false);
		det.DataEnd();
		String prob[] = det.getProbableCharsets();

		for (int i = 0; i < prob.length; i++) {
			if ("(BIG5)(GB18030)|(gb2312)|(gbk)|(GB2312)|(GBK)".indexOf(prob[i]
					.toUpperCase()) > 0)
				return "gb2312";
			else
				return prob[i];
		}
		return "UTF-8";

	}
}
