package com.amazon.stepdefinitions;

import com.amazon.library.BaseTest;
import com.amazon.library.ThreadLocalDriver;
import com.amazon.pages.HomeScreen;
import com.amazon.pages.StartScreen;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.appium.java_client.AppiumDriver;
import org.testng.Reporter;
import org.testng.xml.XmlTest;


/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class HomeScreenStepDefs extends BaseTest {

    private AppiumDriver driver = ThreadLocalDriver.getTLDriver();
    private HomeScreen homeScreen;
    private XmlTest readXMLparams = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();

    public HomeScreenStepDefs() {
        homeScreen = new HomeScreen(driver);
    }

    //To verify if user is landed on HomeScreen
    @And("^I verify I am on home screen$")
    public void verifyHomeScreen() throws Throwable {
        homeScreen.verifyAmazonLogo();
    }

    @And("I tap on search text bar")
    public void tapSearchBar() throws Exception {
        homeScreen.clickSearchBar();
        Thread.sleep(3000);
        homeScreen.clickSearchBar();
    }

    @And("I search product for \"([^\"]*)\"")
    public void enterInSearchBar(String user) throws Exception {
        homeScreen.sendTxt(user);

    }
}