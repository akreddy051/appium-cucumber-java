package org.example.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.example.utils.ConfigReader;
import org.example.utils.Device;
import org.example.utils.DeviceManager;

import java.net.URL;

public class DriverFactory {

    public static AppiumDriver createDriver() {

        try {
            Device device = DeviceManager.getDevice();
            String platform = device.getPlatformName();

            if (platform.equalsIgnoreCase("Android")) {
                return createAndroidDriver(device);
            }
            else if (platform.equalsIgnoreCase("iOS")) {
                return createIOSDriver(device);
            }
            else {
                throw new RuntimeException("Unsupported platform: " + platform);
            }

        } catch (Exception e) {
            throw new RuntimeException("Driver creation failed", e);
        }
    }

    private static AppiumDriver createAndroidDriver(Device device) throws Exception {
        System.out.println(
                "Thread: " + Thread.currentThread().getId() +
                        " | Device: " + device.getDeviceName() +
                        " | SystemPort: " + device.getSystemPort()
        );

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(device.getPlatformName());
        options.setDeviceName(device.getDeviceName());
        options.setAutomationName(device.getAutomationName());
        options.setSystemPort(device.getSystemPort());
        options.setAppPackage("com.androidsample.generalstore");
        options.setAppActivity("com.androidsample.generalstore.MainActivity");
        options.setUdid(device.getUdid());

        String appPath = System.getProperty("user.dir") + "/" +
                ConfigReader.getProperty("appPath");

        options.setApp(appPath);

        URL appiumServer = new URL(
                ConfigReader.getProperty("appiumServer")
        );

        return new AndroidDriver(appiumServer, options);
    }

    private static AppiumDriver createIOSDriver(Device device) throws Exception {

        XCUITestOptions options = new XCUITestOptions();

        options.setPlatformName(device.getPlatformName());
        options.setDeviceName(device.getDeviceName());
        options.setAutomationName(device.getAutomationName());

        String appPath = System.getProperty("user.dir") + "/" +
                ConfigReader.getProperty("appPath");

        options.setApp(appPath);

        URL appiumServer = new URL(
                ConfigReader.getProperty("appiumServer")
        );

        return new IOSDriver(appiumServer, options);
    }
}