package com.amazon.pages;

import com.amazon.constants.ExcelParameters;
import com.amazon.library.BaseTest;
import com.amazon.library.ExcelReader;
import com.amazon.pageobjects.HomeScreenPageObjects;
import com.amazon.pageobjects.StartScreenPageObjects;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Map;

import static org.testng.Assert.assertTrue;

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class HomeScreen extends BaseTest {

    HomeScreenPageObjects homeScreenPageObjects = new HomeScreenPageObjects();
    private AppiumDriver driver;

    public HomeScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), homeScreenPageObjects);
    }

    // Verify home page is displayed
    public void verifyAmazonLogo() throws InterruptedException {
        Thread.sleep(6000);
        assertTrue(homeScreenPageObjects.amazonlogoimg.isDisplayed());
    }

    //Click search bar
    public void clickSearchBar() throws Exception {
        homeScreenPageObjects.srctxt.click();
    }

    //Enter text on search bar
    public void sendTxt(String User) throws Exception {
        ExcelReader excel = new ExcelReader();
        Map<ExcelParameters.shoppingDetails, String> amznshoppin = null;
        int counter = 0;
        int iterator = excel.getShoppinDetails().size();
        while (iterator != 0) {
            amznshoppin = excel.getShoppinDetails().get(counter);
            iterator--;
            if (amznshoppin.get(ExcelParameters.shoppingDetails.username).equalsIgnoreCase(User)) {
                break;
            } else {
                counter++;
                continue;
            }
        }
        homeScreenPageObjects.srctxt.sendKeys(amznshoppin.get(ExcelParameters.shoppingDetails.product));

    }

}
