package org.example.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    public static AndroidDriver driver;
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    public static void initializeDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("MyPixel9");
        options.setPlatformName("Android");
        options.setApp("/Users/asingadiwar/Documents/MyCode/Java/Appium-Java-Cucumber/src/test/resources/apps/General-Store.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
        log.info("Driver initialized successfully");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static AndroidDriver getDriver(){
        return driver;
    }

}
