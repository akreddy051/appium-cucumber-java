package org.example.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.example.utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class DriverFactory {

    public static AppiumDriver createDriver() {

        String platform = ConfigReader.getProperty("platformName");

        try {

            if (platform.equalsIgnoreCase("Android")) {
                return createAndroidDriver();
            } else if (platform.equalsIgnoreCase("iOS")) {
                return createIOSDriver();
            } else {
                throw new RuntimeException("Unsupported platform: " + platform);
            }

        } catch (Exception e) {
            throw new RuntimeException("Driver creation failed", e);
        }
    }

    private static AppiumDriver createAndroidDriver() throws Exception {

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
    }

    private static AppiumDriver createIOSDriver() throws Exception {

        XCUITestOptions options = new XCUITestOptions();

        options.setPlatformName(ConfigReader.getProperty("platformName"));
        options.setDeviceName(ConfigReader.getProperty("deviceName"));
        options.setAutomationName(ConfigReader.getProperty("automationName"));

        String appPath = System.getProperty("user.dir") + "/" +
                ConfigReader.getProperty("appPath");

        options.setApp(appPath);

        URL appiumServer = new URL(
                ConfigReader.getProperty("appiumServer")
        );

        return new IOSDriver(
                appiumServer,
                options
        );
    }
}
