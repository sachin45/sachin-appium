package com.amazon.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class StartScreenPageObjects {

    //amazonLogo
    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/sso_splash_logo")
    public MobileElement amazonlogoimg;

    //Sign in button
    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/sign_in_button")
    public MobileElement signinbtn;

    //new user sign-up button
    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/new_user")
    public MobileElement signupbtn;

    //skip sign-in button
    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/skip_sign_in_button")
    public MobileElement skipsigninbtn;


}
