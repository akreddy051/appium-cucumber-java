package org.example.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.example.utils.ConfigReader;

import java.net.URL;

public class DriverFactory {

    public static AppiumDriver createDriver() {
        String executionType = ConfigReader.getProperty("executionType");
        String platform = ConfigReader.getProperty("platformName");
        try {
            if (executionType.equalsIgnoreCase("local")) {
                if (platform.equalsIgnoreCase("Android")) {
                    return createLocalAndroidDriver();
                }
                else if (platform.equalsIgnoreCase("iOS")) {
                    return createLocalIOSDriver();
                }
            }
            else if (executionType.equalsIgnoreCase("cloud")) {
                if (platform.equalsIgnoreCase("Android")) {
                    return createCloudAndroidDriver();
                }
                else if (platform.equalsIgnoreCase("iOS")) {
                    return createCloudIOSDriver();
                }
            }
            throw new RuntimeException(
                    "Unsupported combination: " + executionType + " + " + platform
            );
        } catch (Exception e) {
            throw new RuntimeException("Driver creation failed", e);
        }
    }

    /* ---------------- LOCAL DRIVERS ---------------- */
    private static AppiumDriver createLocalAndroidDriver() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ConfigReader.getProperty("platformName"));
        options.setDeviceName(ConfigReader.getProperty("deviceName"));
        options.setAutomationName(ConfigReader.getProperty("automationName"));
        String appPath = System.getProperty("user.dir") + "/" +
                ConfigReader.getProperty("appPath");
        options.setApp(appPath);
        URL appiumServer = new URL(ConfigReader.getProperty("appiumServer"));
        return new AndroidDriver(appiumServer, options);
    }

    private static AppiumDriver createLocalIOSDriver() throws Exception {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName(ConfigReader.getProperty("platformName"));
        options.setDeviceName(ConfigReader.getProperty("deviceName"));
        options.setAutomationName(ConfigReader.getProperty("automationName"));
        String appPath = System.getProperty("user.dir") + "/" +
                ConfigReader.getProperty("appPath");
        options.setApp(appPath);
        URL appiumServer = new URL(ConfigReader.getProperty("appiumServer"));
        return new IOSDriver(appiumServer, options);
    }

    /* ---------------- CLOUD DRIVERS ---------------- */
    private static AppiumDriver createCloudAndroidDriver() throws Exception {
        String userName = System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY");
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setDeviceName(ConfigReader.getProperty("cloud.deviceName"));
        options.setPlatformVersion(ConfigReader.getProperty("cloud.platformVersion"));
        options.setCapability("build", "Native App-6 automate Demo");
        options.setCapability("isRealMobile", true);
        options.setCapability("app",ConfigReader.getProperty("cloud.appId"));     //Enter the app url here
        options.setCapability("network", false);
        options.setCapability("video", true);
        options.setCapability("console", true);
        options.setCapability("visual", true);
        options.setCapability("privateCloud",true);
        String gridURL = "https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub";
        System.out.println("gridURL: "+gridURL);
        URL cloudUrl = new URL(gridURL);
        return new AndroidDriver(cloudUrl, options);
    }

    private static AppiumDriver createCloudIOSDriver() throws Exception {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setDeviceName(ConfigReader.getProperty("cloud.deviceName"));
        options.setPlatformVersion(ConfigReader.getProperty("cloud.platformVersion"));
        options.setCapability("appium:app", ConfigReader.getProperty("cloud.appId"));
        URL cloudUrl = new URL(ConfigReader.getProperty("cloud.url"));
        return new IOSDriver(cloudUrl, options);
    }
}