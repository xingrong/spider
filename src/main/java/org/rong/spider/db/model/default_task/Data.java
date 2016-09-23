package org.rong.spider.db.model.default_task;

import java.util.Calendar;
import java.util.Date;

public class Data{
	private int id;
	private Date update_time;
	private Date create_time;
	private String name;
	private int is_deleted;

	public Data() {
		Date date = Calendar.getInstance().getTime();
		this.setCreate_time(date);
		this.setUpdate_time(date);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}

}
