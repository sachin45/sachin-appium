package com.amazon.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */
public class LanguageSelectionScreenPageObjects {

    //amazonLogo
    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/splash_logo")
    public MobileElement amazonlogoimg;

    //English Language button
    @AndroidFindBy(xpath = "(//*[@class ='android.widget.ImageView'])[2]")
    public MobileElement englishbtn;

    //Hindi Language button
    @AndroidFindBy(xpath = "(//*[@class ='android.widget.ImageView'])[3]")
    public MobileElement hindibtn;
}
