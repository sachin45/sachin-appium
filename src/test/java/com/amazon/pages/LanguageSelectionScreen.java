package com.amazon.pages;

import com.amazon.library.BaseTest;
import com.amazon.pageobjects.LanguageSelectionScreenPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class LanguageSelectionScreen extends BaseTest {

    LanguageSelectionScreenPageObjects languageselectionpageobj = new LanguageSelectionScreenPageObjects();
    private AppiumDriver driver;

    public LanguageSelectionScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), languageselectionpageobj);
    }

    // Verify amazon app is launched
    public void verifyAmazonLogo() throws InterruptedException {
        Thread.sleep(7000);
        assertTrue(languageselectionpageobj.amazonlogoimg.isDisplayed());
    }

    // Select language as English
    public void selectLanguageEnglish() throws Exception {
        languageselectionpageobj.englishbtn.click();
    }

    //Select Language as Hindi
    public void selectLanguageHindi() throws Exception {
        languageselectionpageobj.hindibtn.click();
    }

}
