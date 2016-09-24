package org.rong.spider.scheduler;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.rong.spider.db.dao.JobDao;
import org.rong.spider.db.model.Job;
import org.rong.spider.fetcher.FetcherMain;
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
public class DBJobDispatcher implements Runnable {
	final static Logger logger = LoggerFactory.getLogger(DBJobDispatcher.class);

	public void run() {
		while (true) {
			SqlSession session = MybatisSessionFactory.getSqlSessionFactory(
					"default").openSession();
			JobDao jobDao = session.getMapper(JobDao.class);
			ArrayList<Job> jobList = jobDao.getAllJobs();
			for (Job job : jobList) {
				// TODO add new job strategy
				if (true) {
					FetcherMain.addFetcherJob(generateJobObject(job));
				}
			}
			session.commit();
			session.close();
			sleep(SpiderConstants.DB_DISPATCH_INTERVAL);
		}
	}

	private JSONObject generateJobObject(Job job) {
		JSONObject jobObject = new JSONObject();
		return jobObject;
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
