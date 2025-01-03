package com.divsly.testcases;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
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
	public static ExtentTest test;
	public static ExtentReports extent;

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

	@BeforeSuite
	public void setup() {
		logger = LogManager.getLogger("Divsly");
		loggingMethod("info", "Initialising the execution");
		configureReport();
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));

	}

	public static String AddScreenshot() throws Exception {
		return "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

	}

	public void waitforPageToLoad() {
		new WebDriverWait(driver, Duration.ofSeconds(longWaitime, 10)).until(
				t -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

	}

	@AfterSuite
	public void tearDown() {
		// driver.close();
		driver.quit();
		reports.flush();
		loggingMethod("info", "End of execution");
		loggingMethod("info",
				"****************************************************************************************");
	}

	// Method for adding logs passed from test cases
	public void reportLog(String message) {
		test.log(Status.INFO, message);// For extentTest HTML report
		logger.info("Message: " + message);
		Reporter.log(message);

	}

	/// Adding reporting code to add the reports as per user defnition
	ExtentSparkReporter htmlReporter;
	public static ExtentReports reports;
	boolean attachScreenShotforAll = new ReadConfig().getAttachScreenShotForPass();

	public void configureReport() {
		ReadConfig readConfig = new ReadConfig();
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		String reportName = "DivslyTestReport-" + timestamp + ".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//Reports//" + reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		// add system information/environment info to reports
		reports.setSystemInfo("browser:", readConfig.getBrowser());
		reports.setSystemInfo("user name:", "Automation User");

		// configuration to change look and feel of report
		htmlReporter.config().setDocumentTitle("Divsly Automation Report");
		htmlReporter.config().setReportName("Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);

	}

	@BeforeClass
	public void createParentTree() {
		String classNameOnly = this.getClass().getSimpleName();
		test = reports.createTest(classNameOnly);
	}

	@AfterMethod
	public void closeChildNode(ITestResult result) {
		if (result.isSuccess()) {
			extentIteration.log(Status.PASS, "Test case passed without any failure");
		}
		else if (!result.isSuccess()) {
			extentIteration.log(Status.FAIL, "failed during" + result.getTestContext());
		}

	}

	static ExtentTest extentIteration;

	public static ExtentTest createTestClassDetails(String name) {
		return extentIteration = test.createNode(name);
	}

	public static void addDetailsinReport(ExtentTest nodeName, String type, String DatatoShow) {
		if (type.equalsIgnoreCase("INFO"))
			nodeName.info(DatatoShow);
		else if (type.equalsIgnoreCase("PASS"))
			nodeName.pass(DatatoShow);
		else if (type.equalsIgnoreCase("FAIL"))
			nodeName.fail(DatatoShow);
		else if (type.equalsIgnoreCase("WARNING"))
			nodeName.warning(DatatoShow);

	}

}
