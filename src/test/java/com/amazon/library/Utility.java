package com.amazon.library;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Utility {
    private static final int BUFFER_SIZE = 4096;


    public static String screenshot(AppiumDriver driver, String screenshotName) throws IOException {
        Date date1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String folder = dateFormat.format(date1);
        File theDir = null;
        String dest = null;
        try {


            theDir = new File(System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "screenshots" + File.separator + folder);
            if (!theDir.exists()) {
                theDir.mkdir();
                Log.info("Info : [EW] Screenshots - Folder created");
            }
            dateFormat = new SimpleDateFormat("HH_mm_ss");
            String fileName = dateFormat.format(date1) + "_" + screenshotName;
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            dest = theDir.toPath() + File.separator + fileName + ".png";
            //FileUtils.moveFile(srcFile, new File(dest));
            FileUtils.copyFile(srcFile, new File(dest));
            FileUtils.forceDelete(srcFile);
        } catch (Exception e) {
            theDir = new File(System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "screenshots" + File.separator + folder);
            if (!theDir.exists()) {
                theDir.mkdir();
                Log.info("Info : [EW] Screenshots - Folder created");
            }
            dateFormat = new SimpleDateFormat("HH_mm_ss");
            String fileName = dateFormat.format(date1) + "_" + screenshotName;
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            dest = theDir.toPath() + File.separator + fileName + ".png";
            //FileUtils.moveFile(srcFile, new File(dest));
            FileUtils.copyFile(srcFile, new File(dest));
            FileUtils.forceDelete(srcFile);
        }
        return dest;
    }

    public static String getDateWithFormat(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static boolean scrollDownTillElementFound(AppiumDriver driver, WebElement ele) {

        boolean isElementFound = false;
        int maxScroll = Integer.parseInt("5");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        while (!isElementFound && maxScroll >= 0) {
            try {
                Dimension size = driver.manage().window().getSize();
                int starty = (int) (size.height * 0.80);
                int endy = (int) (size.height * 0.20);
                int startx = size.width / 2;
                doSwipeOnScreen(driver, "up", 800);
                TouchAction longtap = new TouchAction(driver);
                longtap.press(PointOption.point(startx, starty)).moveTo(PointOption.point(startx, endy)).release().perform();
                isElementFound = ele.isDisplayed();
                if (isElementFound) {
                    String coordinates = ele.getAttribute("bounds");
                    String[] coords = coordinates.split("]");
                    String[] startcoords = coords[0].split(",");
                    int ycoord = Integer.parseInt(startcoords[1]);
                    int ylowerlimit = (int) (ycoord - ycoord * 0.15);
                    int yupperlimit = (int) (ycoord + ycoord * 0.15);
                    if (ylowerlimit < ycoord && ycoord > yupperlimit) {
                    } else if (ylowerlimit < ycoord) {
                        doSwipeOnScreen(driver, "up", 3000);
                    } else if (ycoord > yupperlimit) {
                        doSwipeOnScreen(driver, "down", 3000);
                    }
                } else {
                    maxScroll--;
                }
                maxScroll--;
            } catch (Exception e) {
                System.out.println("Exception caught");
                maxScroll--;
            }
        }
        return isElementFound;
    }

    public static boolean doSwipeOnScreen(AppiumDriver driver, String swipeDirection, int duration) {
        final long startTime = Log.startTime();
        boolean isTrue = false;
        try {
            Dimension size = driver.manage().window().getSize();
            int startY = (int) (size.height * 0.6);
            int endY = (int) (size.height * 0.75);
            int startX = (int) (size.width * 0.75);
            int endX = (int) (size.width * 0.125);

            switch (swipeDirection.toLowerCase()) {
                case "left": {
                    new TouchAction(driver).press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                case "right": {
                    new TouchAction(driver).press(PointOption.point(endX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(startX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                case "up": {
                    new TouchAction(driver).press(PointOption.point(startX, endY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endX, endY / 2))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                case "down": {
                    new TouchAction(driver).press(PointOption.point(endX, endY / 2))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
                default: {
                    new TouchAction(driver).press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000))).moveTo(PointOption.point(endX, endY))
                            .release().perform();
                    Log.info("Pass : [EW] Do swipe on screen towards direction \t:" + swipeDirection);
                    break;
                }
            }
            isTrue = true;
        }//End try

        catch (Exception e) {
            //throw new Exception("Exception at ElementWrapper.doSwipeOnScreen \t: " + e.getMessage(), e);
        }//End catch

        finally {
            Log.info("ElementWrapper.doSwipeOnScreen\t" + Log.elapsedTime(startTime));
        }//End finally

        return isTrue;
    }

}

