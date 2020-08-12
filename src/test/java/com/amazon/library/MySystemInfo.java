package com.amazon.library;

import com.vimalselvam.testng.SystemInfo;
import org.testng.collections.Maps;

import java.util.Map;

/**
 * This is a small utility class to prepare the system information
 */

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

public class MySystemInfo implements SystemInfo {
    @Override
    public Map<String, String> getSystemInfo() {
        Map<String, String> systemInfo = Maps.newHashMap();
        systemInfo.put("Author", "Sachin Saxena");
        systemInfo.put("TestEnv", "QA");
        systemInfo.put("AppName", "Amazon Application");
        return systemInfo;
    }
}
