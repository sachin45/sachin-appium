package com.amazon.library;

import io.appium.java_client.AppiumDriver;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

/**
 * This class is used to set threads for Appium driver which will help to perform parallel execution
 */

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class ThreadLocalDriver {

    private static ThreadLocal<AppiumDriver> tlDriver = new ThreadLocal<>();

    public synchronized static AppiumDriver getTLDriver() {
        return tlDriver.get();
    }

    public synchronized static void setTLDriver(AppiumDriver driver) {
        tlDriver.set(driver);
    }


}
