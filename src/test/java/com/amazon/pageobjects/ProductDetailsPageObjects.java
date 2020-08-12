package com.amazon.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class ProductDetailsPageObjects {

    //product name
    @AndroidFindBy(xpath = "//*[@resource-id='title_feature_div']/android.view.View")
    public MobileElement productName;

    //product price
    @AndroidFindBy(xpath = "//*[@class='android.widget.EditText']")
    public MobileElement productPrice;

    //Buy now button
    @AndroidFindBy(xpath = "//*[@resource-id='a-autoid-8']")
    public MobileElement buynow;

    //Add to cart button
    @AndroidFindBy(id = "add-to-cart-button")
    public MobileElement addtocart;

}
