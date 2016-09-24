package org.rong.spider.scheduler.db;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Select;

public interface JobDao {
	@Select("select * from job order by update_time desc")
	public ArrayList<Job> getAllJobs();
}
