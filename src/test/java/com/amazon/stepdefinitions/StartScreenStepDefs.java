package com.amazon.stepdefinitions;

import com.amazon.library.BaseTest;
import com.amazon.library.ThreadLocalDriver;
import com.amazon.pages.LanguageSelectionScreen;
import com.amazon.pages.StartScreen;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.testng.Reporter;
import org.testng.xml.XmlTest;


/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class StartScreenStepDefs extends BaseTest {

    private AppiumDriver driver = ThreadLocalDriver.getTLDriver();
    private StartScreen startScreen;
    private XmlTest readXMLparams = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();

    public StartScreenStepDefs() {
        startScreen = new StartScreen(driver);
    }

    //To verify if user is landed on HomeScreen
    @Given("^I verify that I am on sign-up page$")
    public void verifyHomeScreen() throws Throwable {
        startScreen.verifyAmazonLogo();
    }

    @And("I click on skip sign-in button")
    public void skipsignin() throws Exception {
        startScreen.selectSkipSigninBtn();
    }
}