package com.amazon.pages;

import com.amazon.constants.ExcelParameters;
import com.amazon.library.BaseTest;
import com.amazon.library.ExcelReader;
import com.amazon.library.Utility;
import com.amazon.pageobjects.HomeScreenPageObjects;
import com.amazon.pageobjects.ProductListPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.bouncycastle.util.test.TestRandomData;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Map;

import static org.testng.Assert.assertTrue;

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class ProductListScreen extends BaseTest {

    ProductListPageObjects productListPageObjects = new ProductListPageObjects();
    private AppiumDriver driver;

    public ProductListScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), productListPageObjects);
    }

    // Scroll till product found
    public void scrollTillProduct() throws InterruptedException {
        Thread.sleep(3000);
        Utility.scrollDownTillElementFound(driver, productListPageObjects.product);
    }

    // Store product name
    public void storeProductName() {
        TestData.put("productname", productListPageObjects.product.getText());
        System.out.println(TestData.get("productname"));
    }

    // Store product price
    public void storeProductPrice() throws InterruptedException {
        TestData.put("productprice", productListPageObjects.productprice.getText());
        System.out.println(TestData.get("productprice"));
    }

    // Click on product
    public void clickProduct() throws InterruptedException {
        productListPageObjects.product.click();
    }

}
