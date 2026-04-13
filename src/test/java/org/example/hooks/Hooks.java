package org.example.hooks;

import io.cucumber.java.*;
import org.example.base.BaseTest;
import org.example.driver.DriverManager;
import org.example.utils.ScreenshotUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

//    @BeforeAll
//    public static void beforeAll(){
//        BaseTest.initializeAppiumDriver();
//    }

    @Before
    public void before(Scenario scenario) {
        BaseTest.initializeAppiumDriver();
        scenario.log("Executing on device: " + DriverManager.getDriver().getCapabilities().getCapability("deviceName"));
        log.info("Test Started");
    }

    @After
    public void after(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenshot = ScreenshotUtils.takeScreenshot(DriverManager.getDriver());
            scenario.attach(screenshot, "image/png", scenario.getName());
            DriverManager.getDriver().executeScript("lambda-hook: {\"action\": \"setTestStatus\",\"arguments\": {\"status\":\"failed\"}} ");
        }else{
            DriverManager.getDriver().executeScript("lambda-hook: {\"action\": \"setTestStatus\",\"arguments\": {\"status\":\"passed\"}} ");
        }
        log.info("Test Ended");
        BaseTest.quitDriver();
        log.info("Driver quit successfully");
    }

//    @AfterAll
//    public static void afterAll(){
//        BaseTest.quitDriver();
//        log.info("Driver quit successfully");
//    }
}
