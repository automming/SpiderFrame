package com.spiderframe.common.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	Properties properties;

	public PropertyReader(String filePath) {
		File file = new File(filePath);
		try {
			properties = new Properties();
			properties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}

}
