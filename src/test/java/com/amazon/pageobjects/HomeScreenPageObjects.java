package com.amazon.pageobjects;

import com.amazon.library.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class HomeScreenPageObjects {

    //amazonLogo
    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/action_bar_home_logo")
    public MobileElement amazonlogoimg;

    //Search text
    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/rs_search_src_text")
    public MobileElement srctxt;


}
