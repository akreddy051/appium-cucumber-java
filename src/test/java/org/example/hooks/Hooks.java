package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.base.BaseTest;
import org.example.driver.DriverManager;
import org.example.utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class Hooks {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @Before
    public void before(Scenario scenario) throws MalformedURLException {
        BaseTest.initializeAppiumDriver();
        scenario.log("Executing on device: " + DriverManager.getDriver().getCapabilities().getCapability("deviceName"));
        log.info("Test Started");
    }

    @After
    public void after(Scenario scenario){
        if (scenario.isFailed()) {

            byte[] screenshot = ScreenshotUtils.takeScreenshot(DriverManager.getDriver());

            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        log.info("Test Ended");
        BaseTest.quitDriver();
        log.info("Driver quit successfully");
    }
}
