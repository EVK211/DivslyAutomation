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

	@BeforeClass
	public void setup() {

		switch (browser.toLowerCase()) {
		case "chrome": {
			WebDriverManager.chromedriver().setup();
			if (headless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				driver = new ChromeDriver(options);
				break;
			} else {
				driver = new ChromeDriver();
			}
			break;
		}
		case "edge": {
			WebDriverManager.edgedriver().setup();
			if (headless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=new");
				driver = new EdgeDriver(options);
				break;
			} else
				driver = new EdgeDriver();
			break;

		}
		case "firefox": {
			WebDriverManager.firefoxdriver().setup();
			if (headless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=new");
				driver = new FirefoxDriver(options);
				break;
			} else
				driver = new FirefoxDriver();
			break;
		}

		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
		logger = LogManager.getLogger("Divsly");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(implicitWaitTime));

	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
