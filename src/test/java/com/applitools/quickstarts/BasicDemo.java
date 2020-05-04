package com.applitools.quickstarts;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BasicDemo {

	public static WebDriver driver = null;
	public static EyesRunner runner = null;
	public static Eyes eyes = null;
	public static Configuration config = null;
	
	public static void main(String[] args) {
		
		setUp();
		
		TestDemoApp();
		
		driver.quit();

		// Wait and collect all test results
		TestResultsSummary allTestResults = runner.getAllTestResults();

		// Print results
		System.out.println(allTestResults);
		
	}
	
	public static void setUp() {
		// Use Chrome browser
		driver = new ChromeDriver();
	
		// Initialize the Runner for your test.
		runner = new ClassicRunner();
		
		//Initialize eyes configuration.
		config  = new Configuration();
		
		// set Api Key from environment variables.
		config.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
		
		// set new batch 
		config.setBatch(new BatchInfo("Demo batch"));
		
		// Initialize the eyes SDK
		eyes = new Eyes(runner);
		
		//set the configuration to eyes
		eyes.setConfiguration(config);
	}


	public static void TestDemoApp() {
		try {
		// Set AUT's name, test name and viewport size (width X height)
		// We have set it to 800 x 600 to accommodate various screens. Feel free to
		// change it.
		eyes.open(driver, "Demo App", "Smoke Test", new RectangleSize(800, 600));

		// Navigate the browser to the "ACME" demo app.
		driver.get("https://demo.applitools.com");

		// To see visual bugs after the first run, use the commented line below instead.
		//driver.get("https://demo.applitools.com/index_v2.html");
				
		// Visual checkpoint #1 - Check the login page. using the fluent API 
		//https://applitools.com/docs/topics/sdk/the-eyes-sdk-check-fluent-api.html
		eyes.check("Login Window", Target.window().fully());

		// This will create a test with two test steps.
		driver.findElement(By.id("log-in")).click();

		// Visual checkpoint #2 - Check the app page.
		eyes.check("App Window", Target.window().fully());

		// End the test.
		eyes.closeAsync();
		}
		catch(Exception e) {
			driver.quit();
		}
		finally {
			
			// If the test was aborted before eyes.close was called, ends the test as
			// aborted.
			eyes.abortAsync();
			
		}
	}

}
