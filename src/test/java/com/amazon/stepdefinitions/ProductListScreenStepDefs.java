package com.amazon.stepdefinitions;

import com.amazon.library.BaseTest;
import com.amazon.library.ThreadLocalDriver;
import com.amazon.pages.HomeScreen;
import com.amazon.pages.ProductListScreen;
import cucumber.api.java.en.And;
import io.appium.java_client.AppiumDriver;
import org.testng.Reporter;
import org.testng.xml.XmlTest;


/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class ProductListScreenStepDefs extends BaseTest {

    private AppiumDriver driver = ThreadLocalDriver.getTLDriver();
    private ProductListScreen productListScreen;
    private XmlTest readXMLparams = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();

    public ProductListScreenStepDefs() {
        productListScreen = new ProductListScreen(driver);
    }

    //Swipe screen till product found
    @And("^I swipe screen till product is found$")
    public void scrollTillProductFound() throws Throwable {
        productListScreen.scrollTillProduct();
        productListScreen.storeProductName();
        productListScreen.storeProductPrice();
    }

    @And("I tap on product")
    public void tapProduct() throws Exception {
        productListScreen.clickProduct();
    }
}