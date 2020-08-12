package com.amazon.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class ProductListPageObjects {

    //random product
    @AndroidFindBy(xpath = "(//android.view.View[contains(@content-desc,\"METZ 101 cm (40 inches)\")]/android.view.View[1])[2]")
    public MobileElement product;

    //product price
    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"METZ 101 cm (40 inches)\")]/android.widget.TextView[1]")
    public MobileElement productprice;

}
