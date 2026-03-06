package org.example.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.example.utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class DriverFactory {
    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

    public static AppiumDriver createDriver() {

        try {
            UiAutomator2Options options = new UiAutomator2Options();

            options.setPlatformName(ConfigReader.getProperty("platformName"));
            options.setDeviceName(ConfigReader.getProperty("deviceName"));
            options.setAutomationName(ConfigReader.getProperty("automationName"));

            String appPath = System.getProperty("user.dir") + "/" +
                    ConfigReader.getProperty("appPath");

            options.setApp(appPath);

            URL appiumServer = new URL(
                    ConfigReader.getProperty("appiumServer")
            );
            return new AndroidDriver(
                    appiumServer,
                    options
            );
        } catch (Exception e) {
            log.error("Driver initialization failed", e);
            throw new RuntimeException(e);
        }
    }
}
