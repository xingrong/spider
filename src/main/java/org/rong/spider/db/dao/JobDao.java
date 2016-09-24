package org.rong.spider.db.dao;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Select;
import org.rong.spider.db.model.Job;

public interface JobDao {
	@Select("select * from job order by update_time desc")
	public ArrayList<Job> getAllJobs();
}
