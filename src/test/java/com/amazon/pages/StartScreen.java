package com.amazon.pages;

import com.amazon.library.BaseTest;
import com.amazon.pageobjects.StartScreenPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class StartScreen extends BaseTest {

    StartScreenPageObjects startscreenpageobj = new StartScreenPageObjects();
    private AppiumDriver driver;

    public StartScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), startscreenpageobj);
    }

    // Verify amazon app is launched
    public void verifyAmazonLogo() throws InterruptedException {
        Thread.sleep(3000);
        assertTrue(startscreenpageobj.amazonlogoimg.isDisplayed());
    }

    //Select sign-in button
    public void selectSigninBtn() throws Exception {
        startscreenpageobj.signinbtn.click();
    }

    //Select sign-up button
    public void selectSignupBtn() throws Exception {
        startscreenpageobj.signupbtn.click();
    }

    //Select skip sign-in button
    public void selectSkipSigninBtn() throws Exception {
        startscreenpageobj.skipsigninbtn.click();
    }

}
