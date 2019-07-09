package com.applitools.quickstarts;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Unit test for simple App.
 */
@RunWith(JUnit4.class)
public class BasicDemo
{

    private EyesRunner runner;
    private Eyes eyes;

    private WebDriver driver;

    @Before
    public void beforeEach() {
        //Initialize the Runner for your test.
        runner = new ClassicRunner();

        // Initialize the eyes SDK (IMPORTANT: make sure your API key is set in the APPLITOOLS_API_KEY env variable).
        eyes = new Eyes(runner);

        // Use Chrome browser
        driver = new ChromeDriver();
    }


    @Test
    public void basicTest() {
        // Start the test by setting AUT's name, test name and viewport size (width X height)
        eyes.open(driver, "Demo App", "Smoke Test", new RectangleSize(600, 800));

        // Navigate the browser to the "ACME" demo app. To see visual bugs after the first run, use the commented line below instead.
        driver.get("https://demo.applitools.com");
//        driver.get("https://demo.applitools.com/index_v2.html");

        // Visual checkpoint #1 - Check the login page.
        eyes.checkWindow("Login Window");

        // This will create a test with two test steps.
        driver.findElement(By.id("log-in")).click();

        // Visual checkpoint #2 - Check the app page.
        eyes.checkWindow("App Window");

        // End the test.
        eyes.closeAsync();
    }

    @After
    public void afterEach() {
        // Close the browser.
        driver.quit();

        // If the test was aborted before eyes.close was called, ends the test as
        // aborted.
        eyes.abortIfNotClosed();

        //Wait and collect all test results
        TestResultsSummary allTestResults = runner.getAllTestResults();
    }
}
