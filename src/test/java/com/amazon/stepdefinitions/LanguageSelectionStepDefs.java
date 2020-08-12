package com.amazon.stepdefinitions;

import com.amazon.library.BaseTest;
import com.amazon.library.ThreadLocalDriver;
import com.amazon.pages.LanguageSelectionScreen;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.cucumber.datatable.DataTable;
import org.testng.Reporter;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.Map;


/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class LanguageSelectionStepDefs extends BaseTest {

    private AppiumDriver driver = ThreadLocalDriver.getTLDriver();
    private LanguageSelectionScreen languageSelectionScreen;
    private XmlTest readXMLparams = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();

    public LanguageSelectionStepDefs() {
        languageSelectionScreen = new LanguageSelectionScreen(driver);
    }

    //To verify if user is landed on HomeScreen
    @Given("^I launch the app and verify I am on Initial screen$")
    public void verifyHomeScreen() throws Throwable {
        languageSelectionScreen.verifyAmazonLogo();
    }

    @When("I selected language as \"([^\"]*)\"")
    public void selectLanguage(String language) throws Exception {
        if (language.equalsIgnoreCase("english")) {
            languageSelectionScreen.selectLanguageEnglish();
        } else {
            languageSelectionScreen.selectLanguageHindi();
        }
    }
}