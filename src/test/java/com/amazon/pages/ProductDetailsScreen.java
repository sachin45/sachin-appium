package com.amazon.pages;

import com.amazon.library.BaseTest;
import com.amazon.library.Utility;
import com.amazon.pageobjects.ProductDetailsPageObjects;
import com.amazon.pageobjects.ProductListPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class ProductDetailsScreen extends BaseTest {

    ProductDetailsPageObjects productDetailsPageObjects = new ProductDetailsPageObjects();
    private AppiumDriver driver;

    public ProductDetailsScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), productDetailsPageObjects);
    }

    // Store product name
    public void storeProductName() throws InterruptedException {
        Thread.sleep(3000);
        TestData.put("productdetailsname", productDetailsPageObjects.productName.getText());
        System.out.println(TestData.get("productdetailsname"));
        Assert.assertTrue(TestData.get("productdetailsname").contains(TestData.get("productname").replace("...", "")));
    }


    // Store product price
    public void storeProductPrice() throws InterruptedException {
        Utility.scrollDownTillElementFound(driver, productDetailsPageObjects.productPrice);
        TestData.put("productdetailprice", productDetailsPageObjects.productPrice.getText());
        System.out.println(TestData.get("productdetailprice"));
        Assert.assertTrue(TestData.get("productdetailprice").contains(TestData.get("productprice").replace("₹", "")));


    }

    // Click on Buy now
    public void clickBuynow() {
        Utility.scrollDownTillElementFound(driver,productDetailsPageObjects.buynow);
        productDetailsPageObjects.buynow.click();
    }

}
