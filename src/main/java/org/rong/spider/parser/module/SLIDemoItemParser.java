package org.rong.spider.parser.module;

import java.util.Set;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.rong.spider.parser.main.ParserMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * parser module for sli-demo item page
 * 
 * @author Rong
 * 
 */
public class SLIDemoItemParser extends ParserBase {
	public static final Logger logger = LoggerFactory
			.getLogger(SLIDemoItemParser.class);

	public boolean parse(JSONObject jobObject) {
		Document doc;
		JSONObject itemObject = new JSONObject();
		try {
			doc = Jsoup.parse(jobObject.getString("src_content"));

			// Title
			String title = doc.select(
					"div.product-shop div.product-name span.h1").text();
			itemObject.put("Title", title);

			// Price
			Elements priceElements = doc.select(
					"div.product-shop div.price-info div.price-box");
			itemObject.put("Price", priceElements.last().select("span.price").text());

			// Brand
			String brand = title.split(" ")[0];
			itemObject.put("Brand", brand);

			// Short Description
			String shortDescription = doc.select(
					"div.product-shop div.short-description").text();
			itemObject.put("Short Description", shortDescription);

			// Additional Information
			String additionalInformation = doc.select("div.tab-content table")
					.text();
			itemObject.put("Additional Information", additionalInformation);

			// Category
			String category = doc.select("div.breadcrumbs").text();
			itemObject.put("Category", category);

			// storage
			Set<String> keySet = itemObject.keySet();
			String storageContent = "";
			for (String key : keySet) {
				storageContent += key + ": " + itemObject.getString(key) + "\r";
			}
			logger.info(storageContent);
			jobObject.put("storageContent", storageContent);
			ParserMain.addStorageObject(jobObject);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}
}
