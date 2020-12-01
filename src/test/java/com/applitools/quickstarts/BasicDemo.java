package com.applitools.quickstarts;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BasicDemo {
	final static Boolean gridExecution = Boolean.TRUE;
	static EyesRunner runner;
	static Eyes eyes;
	Configuration config = new Configuration();
	final static String appUrl = "https://demo.applitools.com/tlcHackathonMasterV2.html";

	@BeforeAll
	public static void setUp() {

		// Initialize the eyes configuration.
		Configuration config = new Configuration();
		// You can get your api key from the Applitools dashboard
		config.setApiKey("l3nWKvGTKnGIe8QRkLztKCZ7evf6DIMXdsrMcL70ybg110");
		if (gridExecution) {
			config.setBatch(new BatchInfo("Cross_platform_AppliFashion"));
			config.addBrowser(1200, 800, BrowserType.CHROME);
			config.addBrowser(1200, 800, BrowserType.FIREFOX);
			config.addBrowser(1200, 800, BrowserType.EDGE_CHROMIUM);
			config.addDeviceEmulation(DeviceName.iPhone_X);
			config.setMatchLevel(MatchLevel.LAYOUT);
			runner = new VisualGridRunner(new RunnerOptions().testConcurrency(10));

		} else {

			// set new batch
			config.setBatch(new BatchInfo("AppliFashion"));
			runner = new ClassicRunner();

		}

		eyes = new Eyes(runner);

		// set the configuration to eyes
		eyes.setConfiguration(config);
	}

	@Test
	public void test1() {

		// Use Chrome browser
		System.setProperty("webdriver.chrome.driver", "C:/vasvin/techie/sele2/chrome/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		try {

			TestDemoApp(driver, eyes);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Test
	public void test2() throws Exception {

		// Use Chrome browser
		System.setProperty("webdriver.chrome.driver", "C:/vasvin/techie/sele2/chrome/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		/*
		 * // Initialize the Runner for your test. EyesRunner runner = new
		 * ClassicRunner();
		 * 
		 * // Initialize the eyes SDK Eyes eyes = new Eyes(runner); setUp(eyes);
		 */

		try {

			TestDemoApp2(driver, eyes);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	@Test
	public void test3() throws Exception {

		// Use Chrome browser
		System.setProperty("webdriver.chrome.driver", "C:/vasvin/techie/sele2/chrome/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Initialize the Runner for your test.
//	EyesRunner runner = new ClassicRunner();

		// Initialize the eyes SDK
		// Eyes eyes = new Eyes(runner);

		try {

			TestDemoApp3(driver, eyes);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private static void TestDemoApp(WebDriver driver, Eyes eyes) {
		// Set AUT's name, test name and viewport size (width X height)
		// We have set it to 800 x 600 to accommodate various screens. Feel free to
		// change it.
		System.setProperty("webdriver.chrome.driver", "C:/vasvin/techie/sele2/chrome/chromedriver.exe");
		driver.get(appUrl);

		eyes.open(driver, "Test 1", "main page");

		// Navigate the browser to the "ACME" demo app.

		// To see visual bugs after the first run, use the commented line below instead.
		// driver.get("https://demo.applitools.com/index_v2.html");

		// Visual checkpoint #1 - Check the login page. using the fluent API
		// https://applitools.com/docs/topics/sdk/the-eyes-sdk-check-fluent-api.html?Highlight=fluent%20api
		eyes.check(Target.window().fully().withName("V1: Applitools End-2-End Hackathon Demo App"));

		// This will create a test with two test steps.
		// driver.findElement(By.id("log-in")).click();

		// Visual checkpoint #2 - Check the app page.

		// End the test.
		eyes.closeAsync();
		driver.close();

	}

	private static void TestDemoApp2(WebDriver driver, Eyes eyes) {
		// Set AUT's name, test name and viewport size (width X height)
		// We have set it to 800 x 600 to accommodate various screens. Feel free to
		// change it.
		System.setProperty("webdriver.chrome.driver", "C:/vasvin/techie/sele2/chrome/chromedriver.exe");
		driver.get(appUrl);

		try {
			eyes.open(driver, "Test 2", "filter by color");
			System.out.println("Starting test 2");
			// Navigate the browser to the "ACME" demo app.

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");

			driver.findElement(By.id("SPAN__checkmark__107")).click();
			Thread.sleep(100);

			driver.findElement(By.id("filterBtn")).click();
			Thread.sleep(100);
			// To see visual bugs after the first run, use the commented line below instead.
			// driver.get("https://demo.applitools.com/index_v2.html");

			// Visual checkpoint #1 - Check the login page. using the fluent API
			// https://applitools.com/docs/topics/sdk/the-eyes-sdk-check-fluent-api.html?Highlight=fluent%20api
			eyes.checkRegion(By.id("product_grid"));

			// This will create a test with two test steps.
			// driver.findElement(By.id("log-in")).click();

			// Visual checkpoint #2 - Check the app page.

			// End the test.
			eyes.closeAsync();
			driver.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void TestDemoApp3(WebDriver driver, Eyes eyes) throws Exception {
		// Set AUT's name, test name and viewport size (width X height)
		// We have set it to 800 x 600 to accommodate various screens. Feel free to
		// change it.
		System.setProperty("webdriver.chrome.driver", "C:/vasvin/techie/sele2/chrome/chromedriver.exe");
		driver.get(appUrl);

		eyes.open(driver, "Test 3", "product details");
		System.out.println("Starting test 3");
		// Navigate the browser to the "ACME" demo app.

		Thread.sleep(100);

		driver.findElement(By.id("H3____218")).click();
		Thread.sleep(100);

		// To see visual bugs after the first run, use the commented line below instead.
		// driver.get("https://demo.applitools.com/index_v2.html");

		// Visual checkpoint #1 - Check the login page. using the fluent API
		// https://applitools.com/docs/topics/sdk/the-eyes-sdk-check-fluent-api.html?Highlight=fluent%20api
		eyes.check(Target.window().fully());

		// This will create a test with two test steps.
		// driver.findElement(By.id("log-in")).click();

		// Visual checkpoint #2 - Check the app page.

		// End the test.
		eyes.closeAsync();
		driver.close();
	}

	@AfterAll
	public static void tearDown() {
		/*
		 * driver.quit(); eyes.closeAsync();
		 */
		// Wait and collect all test results
		// we pass false to this method to suppress the exception that is thrown if we
		// find visual differences
		TestResultsSummary allTestResults = runner.getAllTestResults(false);

		// Print results
		System.out.println(allTestResults);
	}

}
