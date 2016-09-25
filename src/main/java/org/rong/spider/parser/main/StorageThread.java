package org.rong.spider.parser.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.json.JSONObject;
import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Background thread approach to storage
 * 
 * @author Rong
 * 
 */
public class StorageThread implements Runnable {
	final static Logger logger = LoggerFactory.getLogger(StorageThread.class);

	public void run() {
		while (true) {
			try {
				JSONObject jobObject = ParserMain.getStorageObject();
				if (jobObject != null) {
					String jobName = jobObject.getString("name");
					String fileName = SpiderConstants.PARSER_STORAGE_PATH
							+ jobName + ".txt";
					String content = jobObject.getString("storageContent");
					writeToDisk(fileName, content);
				}
				Thread.sleep(SpiderConstants.STORAGE_THREAD_INTERVAL);
			} catch (Exception e) {
				logger.error(e.getMessage());
				continue;
			}
		}
	}

	private void writeToDisk(String fileName, String content) {
		if (content == null || content.trim().equals("")) {
			return;
		}

		File outputFile = new File(fileName);
		if (!outputFile.getParentFile().exists()) {
			outputFile.getParentFile().mkdirs();
		}
		if (outputFile == null || !outputFile.exists()) {
			try {
				outputFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileOutputStream out = null;
		OutputStreamWriter writer = null;
		BufferedWriter bw = null;

		try {
			out = new FileOutputStream(outputFile, true);
			writer = new OutputStreamWriter(out);
			bw = new BufferedWriter(writer);

			bw.write(content);
			bw.newLine();
			bw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				writer.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
