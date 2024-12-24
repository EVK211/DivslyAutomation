package com.divsly.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties properties;

	String path = "Configuration/config.properties";

	// constructor
	public ReadConfig() {
		try {
			properties = new Properties();

			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getBaseUrl() {
		String value = properties.getProperty("baseUrl");

		if (value != null)
			return value;
		else
			throw new RuntimeException("url not specified in config file.");

	}

	public String getBrowser() {
		String value = properties.getProperty("browser");

		if (value != null)
			return value;
		else
			throw new RuntimeException("url not specified in config file.");

	}

	public boolean getHeadless() {
		String headless = properties.getProperty("headless");
		if (headless.equalsIgnoreCase("true"))
			return true;
		else
			return false;

	}

	public long getImplicitWaitTime() {
		String waitTimeinSeconds = properties.getProperty("waitTimeinSeconds");
		if (waitTimeinSeconds != null)
			return Integer.parseInt(waitTimeinSeconds) * 1000;
		else
			return 10000;

	}

	public long getShortWait() {
		String shortWaitinSeconds = properties.getProperty("shortWait");
		if (shortWaitinSeconds != null && shortWaitinSeconds.chars().allMatch(Character::isDigit))
			return Integer.parseInt(shortWaitinSeconds) * 1000;
		else
			return 2000;

	}

	public long getMedWait() {
		String medWaitinSeconds = properties.getProperty("medWait");
		if (medWaitinSeconds != null && medWaitinSeconds.chars().allMatch(Character::isDigit))
			return Integer.parseInt(medWaitinSeconds) * 1000;
		else
			return 5000;

	}

	public long getLongWait() {
		String longWaitinSeconds = properties.getProperty("longWait");
		if (longWaitinSeconds != null && longWaitinSeconds.chars().allMatch(Character::isDigit))
			return Integer.parseInt(longWaitinSeconds) * 1000;
		else
			return 10000;

	}

}
