package org.rong.spider.fetcher.module;

import org.json.JSONObject;

public class FetcherBase {

	public boolean beforeFetch(JSONObject jobObject) {
		return true;
	}

	public boolean fetch(JSONObject jobObject) {
		return true;
	}

	public void afterFetch(JSONObject jobObject) {
	}

	public void close(JSONObject jobObject) {
	}
}
