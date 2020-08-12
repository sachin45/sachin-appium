package com.amazon.library;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class loads the properties from property files(such as applitools.properties/perfecto_details.properties)
 * which will be used during execution
 */


public class PropertyFileReader {
    private static Properties properties = null;
    private static FileInputStream appInputStream;

    static {
        properties = new Properties();
        loadAppProperties();
        loadTestExecutionProperties();
        setSystemProperties();
    }

    public static Properties loadAppProperties() {
        File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                "java" + File.separator + "com" + File.separator + "amazon" + File.separator + "config" + File.separator + "App.properties");

        try {
            appInputStream = new FileInputStream(file);
            properties.load(appInputStream);
            appInputStream.close();
            Log.info("Info : Loading config properties");
        } catch (FileNotFoundException e) {
            Log.info("Error : config properties file missing or corrupted - " + e);
            throw new RuntimeException("Error : config properties file missing or corrupted - " + e);
        } catch (IOException e) {
            Log.info("Error : Loading config properties - " + e);
            throw new RuntimeException("Error : Loading config properties - " + e);
        }
        return properties;
    }

    public static void loadTestExecutionProperties() {
        File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                "java" + File.separator + "com" + File.separator + "amazon" + File.separator + "config" + File.separator + "testExecution.properties");

        try {
            appInputStream = new FileInputStream(file);
            properties.load(appInputStream);
            appInputStream.close();
            Log.info("Info : Loading config properties");
        } catch (FileNotFoundException e) {
            Log.info("Error : config properties file missing or corrupted - " + e);
            throw new RuntimeException("Error : config properties file missing or corrupted - " + e);
        } catch (IOException e) {
            Log.info("Error : Loading config properties - " + e);
            throw new RuntimeException("Error : Loading config properties - " + e);
        }
    }

    public static void setSystemProperties() {
        System.setProperty("maxElementWait", properties.getProperty("maxElementWait"));
        System.setProperty("maxAssertionWait", properties.getProperty("maxAssertionWait"));
        System.setProperty("maxPageProcessingWait", properties.getProperty("maxPageProcessingWait"));
    }
}
