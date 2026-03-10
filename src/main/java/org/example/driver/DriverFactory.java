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
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class DriverFactory {

    // Base systemPort for UiAutomator2
    private static final int BASE_SYSTEM_PORT = ThreadLocalRandom.current().nextInt(8200, 9000);

    // Atomic counter to assign ports to threads safely
    private static final AtomicInteger portCounter = new AtomicInteger(0);

    public static AppiumDriver createDriver() {

        try {
            Device device = DeviceManager.getDevice();
            String platform = device.getPlatformName();

            switch (ConfigReader.getProperty("platform.name")) {
                case "android" :
                    return createAndroidDriver(device);
                case "ios":
                    return createIOSDriver(device);
                default:
                    throw new RuntimeException("Unsupported platform: " + platform);
            }

        } catch (Exception e) {
            throw new RuntimeException("Driver creation failed", e);
        }
    }

    private static AppiumDriver createAndroidDriver(Device device) throws Exception {
        System.out.println(
                "Thread: " + Thread.currentThread().getId() +
                        " | Device: " + device.getDeviceName()
        );

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(device.getPlatformName());
        options.setDeviceName(device.getDeviceName());
        options.setAutomationName(device.getAutomationName());
        // Assign a unique systemPort for this thread
        int systemPort = BASE_SYSTEM_PORT + portCounter.getAndIncrement();
        options.setSystemPort(systemPort);
//        options.setAppPackage(ConfigReader.getProperty("app.package"));
//        options.setAppActivity(ConfigReader.getProperty("app.activity"));
        options.setUdid(device.getUdid());

        String appPath = System.getProperty("user.dir") + "/" +
                ConfigReader.getAppPath();

        options.setApp(appPath);

        URL appiumServer = new URL(
                ConfigReader.getProperty("appium.server.url")
        );

        return new AndroidDriver(appiumServer, options);
    }

    private static AppiumDriver createIOSDriver(Device device) throws Exception {

        XCUITestOptions options = new XCUITestOptions();

        options.setPlatformName(device.getPlatformName());
        options.setDeviceName(device.getDeviceName());
        options.setAutomationName(device.getAutomationName());

        String appPath = System.getProperty("user.dir") + "/" +
                ConfigReader.getAppPath();

        options.setApp(appPath);

        URL appiumServer = new URL(
                ConfigReader.getProperty("appiumServer")
        );

        return new IOSDriver(appiumServer, options);
    }
}