package org.example.driver;

import io.appium.java_client.AppiumDriver;
import lombok.*;

public class DriverManager {
    @Getter
    @Setter
    private static AppiumDriver driver;

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
