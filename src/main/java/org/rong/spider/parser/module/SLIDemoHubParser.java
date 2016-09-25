package org.rong.spider.parser.module;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.rong.spider.fetcher.main.FetcherMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * parser module for sli-demo hub page
 * 
 * @author Rong
 * 
 */
public class SLIDemoHubParser extends ParserBase {
	public static final Logger logger = LoggerFactory
			.getLogger(SLIDemoHubParser.class);

	public boolean parse(JSONObject jobObject) {
		Document doc;
		try {
			doc = Jsoup.parse(jobObject.getString("src_content"));
			for (Element element : doc.select("div.category-products ul.products-grid li.item")) {
				String url = element.select("div.product-info h2.product-name a").attr("href");
				JSONObject itemJobObject = new JSONObject();
				itemJobObject.put("id", jobObject.getInt("id"));
				itemJobObject.put("name", "sli_demo_item");
				itemJobObject.put("fetcher_module", "SeleniumGetFetcher");
				itemJobObject.put("parser_module", "SLIDemoItemParser");
				itemJobObject.put("url", url);
				FetcherMain.addFetcherJob(itemJobObject);
				//logger.info("add fetcher job => " + itemJobObject.toString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}
}
