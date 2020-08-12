package com.amazon.library;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class provides logging capability.
 * Logging is used to store exceptions, information, and warnings as messages that occur during the execution of a program.
 */

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */


public class Log {

    static Logger Log = Logger.getLogger(Log.class);
    static WebDriver driver = BaseTest.driver;
    private static AtomicInteger screenShotIndex = new AtomicInteger(0);

/*	static Logger logger = Logger.getLogger(Log.getName());
	Log= Logger.getLogger(Log.getClass().getName());*/

    public Log() {
        Log = Logger.getLogger(Log.getClass().getName());
    }

    public static void startTestCase(String testCaseName) {
        Log.info("Info : -------Scenario Execution Started---------");

    }

    public static void stopTestCase(String testCaseName) {
        Log.info("Info : -------Scenario Execution End---------" + testCaseName + "-------");
    }


    public static void info(String message) {
        Log.info(message);
    }

    public static void takesScreenshot(WebDriver driver, String filepath) {
        File screenshot = null;

        if (driver instanceof TakesScreenshot) {
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        } else {
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        }
        try {
            File destFile = new File(filepath);
            destFile.getParentFile().mkdirs();
            FileUtils.copyFile(screenshot, destFile);
            screenshot.delete(); // it will delete the previous screenshots
            Log.debug("screenshot taken and stored at " + destFile.getAbsolutePath());
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public static void pass(String message) {
        info("Pass : " + message);
    }

    public static void fail(String message) {
        info("Fail : " + message);
    }


    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) throws Exception {
        Log.fatal(Log);
        throw new Exception(message);
    }

    public static void debug(String message) {
        Log.debug(Log);
    }

    /**
     * startTime : This method is to returns start time in long
     *
     * @return Start time
     */
    public static long startTime() {
        long x = System.currentTimeMillis();
        return (x);
    }

    /**
     * elapsedTime : This method is to returns time difference
     *
     * @return Time difference
     */
    public static long elapsedTime(long startTime) {

        return ((System.currentTimeMillis() - startTime) / 1000);

    }


}