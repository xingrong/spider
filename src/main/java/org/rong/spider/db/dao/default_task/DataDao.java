package org.rong.spider.db.dao.default_task;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.rong.spider.db.model.default_task.Data;

public interface DataDao {
	@Update("update data set name=#{name} where id=#{id}")
	public int updateNameById(@Param("id") int id, @Param("name") String name);

	@Select("select name from data order by update_time desc")
	public ArrayList<Data> getAllName();
}
