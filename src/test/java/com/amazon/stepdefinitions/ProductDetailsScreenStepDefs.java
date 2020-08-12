package com.amazon.stepdefinitions;

import com.amazon.library.BaseTest;
import com.amazon.library.ThreadLocalDriver;
import com.amazon.pages.ProductDetailsScreen;
import com.amazon.pages.ProductListScreen;
import cucumber.api.java.en.And;
import io.appium.java_client.AppiumDriver;
import org.testng.Reporter;
import org.testng.xml.XmlTest;


/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class ProductDetailsScreenStepDefs extends BaseTest {

    private AppiumDriver driver = ThreadLocalDriver.getTLDriver();
    private ProductDetailsScreen productDetailsScreen;
    private XmlTest readXMLparams = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();

    public ProductDetailsScreenStepDefs() {
        productDetailsScreen = new ProductDetailsScreen(driver);
    }

    //Swipe screen till product found
    @And("^I verify product name and product price on product details page$")
    public void verifyProductDetails() throws Throwable {
        productDetailsScreen.storeProductName();
        productDetailsScreen.storeProductPrice();
    }

    @And("I click on Buy now")
    public void tapProduct() throws Exception {
        productDetailsScreen.clickBuynow();
    }
}