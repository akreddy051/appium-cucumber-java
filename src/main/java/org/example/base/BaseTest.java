package org.example.base;

import org.example.driver.DriverFactory;
import org.example.driver.DriverManager;
import org.example.utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    public static void initializeAppiumDriver() {
        DriverManager.setDriver(DriverFactory.createDriver());
        log.info("Driver initialized successfully");
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));
    }

    public static void quitDriver() {
        DriverManager.quitDriver();
    }
}

