package org.rong.spider.db.model;

import java.util.Calendar;
import java.util.Date;

public class Job {
	private int id;
	private Date update_time;
	private Date create_time;
	private String job_name;
	private short need_repeat;
	private int repeat_interval;
	private Date start_time;
	private int need_proxy;
	private String urls_file_path;
	private int fetcher_id;
	private int handler_id;
	private int status;

	public Job() {
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

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public short getNeed_repeat() {
		return need_repeat;
	}

	public void setNeed_repeat(short need_repeat) {
		this.need_repeat = need_repeat;
	}

	public int getRepeat_interval() {
		return repeat_interval;
	}

	public void setRepeat_interval(int repeat_interval) {
		this.repeat_interval = repeat_interval;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public int getNeed_proxy() {
		return need_proxy;
	}

	public void setNeed_proxy(int need_proxy) {
		this.need_proxy = need_proxy;
	}

	public String getUrls_file_path() {
		return urls_file_path;
	}

	public void setUrls_file_path(String urls_file_path) {
		this.urls_file_path = urls_file_path;
	}

	public int getFetcher_id() {
		return fetcher_id;
	}

	public void setFetcher_id(int fetcher_id) {
		this.fetcher_id = fetcher_id;
	}

	public int getHandler_id() {
		return handler_id;
	}

	public void setHandler_id(int handler_id) {
		this.handler_id = handler_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", update_time=" + update_time
				+ ", create_time=" + create_time + ", job_name=" + job_name
				+ ", need_repeat=" + need_repeat + ", repeat_interval="
				+ repeat_interval + ", start_time=" + start_time
				+ ", need_proxy=" + need_proxy + ", urls_file_path="
				+ urls_file_path + ", fetcher_id=" + fetcher_id
				+ ", handler_id=" + handler_id + ", status=" + status + "]";
	}

}
