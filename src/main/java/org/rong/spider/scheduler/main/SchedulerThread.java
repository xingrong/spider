package org.rong.spider.scheduler.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.rong.spider.fetcher.main.FetcherMain;
import org.rong.spider.scheduler.db.Job;
import org.rong.spider.scheduler.db.JobDao;
import org.rong.spider.util.MybatisSessionFactory;
import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Background thread approach to repeat jobs
 * 
 * @author Rong
 * 
 */
public class SchedulerThread implements Runnable {
	final static Logger logger = LoggerFactory.getLogger(SchedulerThread.class);

	public void run() {
		while (true) {
			try {
				Job job = SchedulerMain.getSchedulerJob();
				if (job != null) {
					String fileName = job.getUrls_file();
					ArrayList<String> urlList = readFile(fileName);
					for (String url : urlList) {
						JSONObject jobObject = new JSONObject(job);
						jobObject.put("url", url);
						FetcherMain.addFetcherJob(jobObject);
						logger.info("add fetcher job => " + url);
					}
				}
				Thread.sleep(SpiderConstants.SCHEDULER_THREAD_INTERVAL);
			} catch (Exception e) {
				logger.error(e.getMessage());
				continue;
			}

		}
	}

	private ArrayList<String> readFile(String fileName) {
		ArrayList<String> lineList = new ArrayList<String>();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				lineList.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return lineList;
	}
}
