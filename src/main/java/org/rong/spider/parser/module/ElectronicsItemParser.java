package org.rong.spider.parser.module;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElectronicsItemParser extends ParserBase {
	public static final Logger logger = LoggerFactory
			.getLogger(ElectronicsItemParser.class);

	public boolean parse(JSONObject jobObject) {
		Document doc;
		try {
			doc = Jsoup.parse(jobObject.getString("src_content"));
			logger.info(doc.select(
					"div.product-shop div.price-info div.price-box span.price")
					.html());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}
}
