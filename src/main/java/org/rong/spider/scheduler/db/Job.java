package org.rong.spider.scheduler.db;

import java.util.Calendar;
import java.util.Date;

public class Job {
	private Date update_time;
	private Date create_time;
	private int id;
	private String name;
	private String urls_file;
	private String fetcher_module;
	private String parser_module;

	public Job() {
		Date date = Calendar.getInstance().getTime();
		this.setCreate_time(date);
		this.setUpdate_time(date);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrls_file() {
		return urls_file;
	}

	public void setUrls_file(String urls_file) {
		this.urls_file = urls_file;
	}

	public String getFetcher_module() {
		return fetcher_module;
	}

	public void setFetcher_module(String fetcher_module) {
		this.fetcher_module = fetcher_module;
	}

	public String getParser_module() {
		return parser_module;
	}

	public void setParser_module(String parser_module) {
		this.parser_module = parser_module;
	}

	@Override
	public String toString() {
		return "Job [update_time=" + update_time + ", create_time="
				+ create_time + ", id=" + id + ", name=" + name
				+ ", urls_file=" + urls_file + ", fetcher_module="
				+ fetcher_module + ", parser_module=" + parser_module + "]";
	}
	
}
