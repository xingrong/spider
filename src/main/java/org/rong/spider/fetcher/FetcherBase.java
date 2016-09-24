package org.rong.spider.fetcher;

import org.json.JSONObject;

public class FetcherBase {
	public boolean NeedFetch(JSONObject jobObject) {
		return true;
	}

	public boolean PreFetch(JSONObject jobObject) {
		return true;
	}

	public boolean Fetch(JSONObject jobObject) {
		return true;
	}

	public void AfterFetch(JSONObject jobObject) {
	}

	public void close(JSONObject jobObject) {
	}
}
