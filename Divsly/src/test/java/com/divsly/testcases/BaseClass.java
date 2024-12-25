package com.divsly.testcases;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.divsly.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();
	String url = readConfig.getBaseUrl();
	String browser = readConfig.getBrowser();
	boolean headless = readConfig.getHeadless();
	long implicitWaitTime = readConfig.getImplicitWaitTime();
	long shortWaitime = readConfig.getShortWait();
	long medWaitime = readConfig.getMedWait();
	long longWaitime = readConfig.getLongWait();

	public static WebDriver driver;
	public static Logger logger;

	public static void loggingMethod(String loggerType, String message) {
		switch (loggerType) {
		case "debug": {
			logger.debug(message);
			break;
		}
		case "error": {
			logger.error(message);
			break;
		}
		case "info": {
			logger.info(message);
			break;
		}
		case "warn": {
			logger.warn(message);
			break;
		}
		case "trace": {
			logger.trace(message);
			break;
		}
		case "fatal": {
			logger.fatal(message);
			break;
		}
		case "message": {
			logger.debug(message);
			break;
		}
		}
	}

	@BeforeClass
	public void setup() {
		logger = LogManager.getLogger("Divsly");
		switch (browser.toLowerCase()) {
		case "chrome": {
			WebDriverManager.chromedriver().setup();
			if (headless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				driver = new ChromeDriver(options);
				loggingMethod("info", "Initialised chrome browser in headless mode");
				break;
			} else {
				driver = new ChromeDriver();
				loggingMethod("info", "Initialised chrome browser");
			}
			break;
		}
		case "edge": {
			WebDriverManager.edgedriver().setup();
			if (headless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=new");
				driver = new EdgeDriver(options);
				loggingMethod("info", "Initialised edge browser in headless mode");
				break;
			} else
				driver = new EdgeDriver();
			loggingMethod("info", "Initialised edge browser");
			break;

		}
		case "firefox": {
			WebDriverManager.firefoxdriver().setup();
			if (headless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=new");
				driver = new FirefoxDriver(options);
				loggingMethod("info", "Initialised firefox browser in headless mode");
				break;
			} else
				driver = new FirefoxDriver();
			loggingMethod("info", "Initialised firefox browser");
			break;
		}

		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			loggingMethod("info", "Initialised chrome browser using default config");
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(implicitWaitTime));

	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
