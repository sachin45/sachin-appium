package com.amazon.library;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the main class where the driver initialisation and quiting of driver is defined, before the start and after the end of test execution respectively.
 * It uses the device capabilities set in TestNG.xml file and launches app in the respective device
 */

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 * Description: Added capabilities to run multiple testcases/scenarios without terminating appium session.
 */

public class BaseTest {

    public static RemoteWebDriver driver = null;
    public static HashMap<String, String> TestData = new HashMap<String, String>();
    public AppiumDriverLocalService service;
    public AppiumServiceBuilder builder;
    public WebDriverWait wait;
    PropertyFileReader propertyFileReader;
    DesiredCapabilities caps = new DesiredCapabilities();

    @BeforeSuite
    public void startSuite() throws IOException {

        ExtentReporter.getReporter();
        try {
            service = service.buildDefaultService();
            //[TODO]: Comment below line if you want to debug or create test script
            service.clearOutPutStreams();
            service.start();
            System.out.println("Appium Server has started!!");
        } catch (Exception e) {
            System.out.println("Appium Server could not be started!!");
        }
        //[TODO]: Comment below lines if you want to debug or create test script
        //System.setOut(new java.io.PrintStream(new java.io.OutputStream(){public void write(int b) {}}));

    }

    @BeforeTest
    @Parameters({"automationName", "deviceName", "platformName", "platformVersion"})
    public synchronized void setupTest(String automationName, String deviceName, String platformName, @Optional("OptionalTag") String platformVersion) throws IOException {
        //    propertyFileReader = new PropertyFileReader();
        XmlTest readXMLparams = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
        int port = PortFinder.findFreePort();
//        String bundleidString = null;
//        String appPackageString = null;
//        String appString = null;

        caps.setCapability("deviceName", deviceName);
        caps.setCapability("automationName", automationName);
        caps.setCapability("platformVersion", platformVersion);
        caps.setCapability("platformName", platformName);
        caps.setCapability("automationName", automationName);
        caps.setCapability("noReset", true);
        caps.setCapability("trackScrollEvents", "true");
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability("appPackage", "in.amazon.mShop.android.shopping");
        caps.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
        caps.setCapability("systemPort", port);
        ThreadLocalDriver.setTLDriver(new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps));
        wait = new WebDriverWait(ThreadLocalDriver.getTLDriver(), 10);

    }

    @BeforeMethod
    @Parameters({"platformName"})
    public synchronized void setupScenario(String platformName) {
    }

    @AfterMethod
    @Parameters({"platformName"})
    public synchronized void teardownScenario(String platformName) {
    }

    @AfterTest
    public synchronized void teardowntest() {
        ThreadLocalDriver.getTLDriver().quit();
    }

    @AfterSuite
    public void endSuite() {
        try {
            service.stop();
            System.out.println("Appium Server is stopped!!");
        } catch (Exception e) {
            System.out.println("Appium Server is not stopped!!");
        }
    }

}