package com.amazon.stepdefinitions;

import com.amazon.library.ExtentReporter;
import com.amazon.library.PropertyFileReader;
import com.amazon.library.ThreadLocalDriver;
import com.aventstack.extentreports.Status;
import cucumber.api.PickleStepTestStep;
import cucumber.api.Result;
import cucumber.api.Scenario;
import cucumber.api.TestCase;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Properties;

public class Hooks {

    private final String appName = "Amazon";
    public String featureScenario = "";
    AppiumDriver driver = ThreadLocalDriver.getTLDriver();
    PropertyFileReader propertyFileReader;
    XmlTest readXMLparams = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
    String tags = readXMLparams.getParameter("tags");
    private String DeviceType = readXMLparams.getParameter("DeviceType");


    private static void logError(Scenario scenario) {
        Field field = FieldUtils.getField((scenario).getClass(), "stepResults", true);
        field.setAccessible(true);
        try {
            ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
            for (Result result : results) {
                if (result.getError() != null)
                    ExtentReporter.getTest().error(result.getError());
            }
        } catch (Exception e) {
            ExtentReporter.getTest().error(e);
        }
    }

    @Before
    public void startScenario(Scenario scenario) throws Exception {
        ExtentReporter.getReporter();
//        PropertyFileReader propertyFileReader = new PropertyFileReader();
        String featureScenario = scenario.getName();
        ExtentReporter.flush();
        ExtentReporter.createTest("Scenario : " + featureScenario);
        XmlTest readXMLparams = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest(); // Read the testNG.xml
//        Properties appProperties = PropertyFileReader.loadAppProperties();

    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {

    }

    @AfterStep
    public void afterStep(Scenario scenario) throws IOException {

        String stepName = null;
        try {

            Field stepResultsField = FieldUtils.getField((scenario).getClass(), "stepResults", true);
            ArrayList<Result> stepresults = (ArrayList<Result>) stepResultsField.get(scenario);
            int sizeofresults = stepresults.size();
            if (sizeofresults >= 3) {
                Field testCaseField = FieldUtils.getField((scenario).getClass(), "testCase", true);
                TestCase testCase = (TestCase) testCaseField.get(scenario);
                Field testSteps = FieldUtils.getField((testCase).getClass(), "testSteps", true);
                ArrayList<PickleStepTestStep> pickleStepTestSteps = (ArrayList<PickleStepTestStep>) testSteps.get(testCase);
                int valuetouse = (sizeofresults / 3) - 1;
                stepName = pickleStepTestSteps.get(valuetouse).getPickleStep().getText();
            }
        } catch (Exception e) {
            // ExtentReporter.logStep(Status.FAIL, stepName);
        }
        // ExtentReporter.logStep(Status.PASS, stepName);

        if (scenario.getStatus().toString().equalsIgnoreCase("Failed")) {
            ExtentReporter.logStep(Status.FAIL, stepName);
        } else if (scenario.getStatus().toString().equalsIgnoreCase("Passed")) {
            ExtentReporter.logStep(Status.PASS, stepName);
        } else {
            ExtentReporter.logStep(Status.SKIP, stepName);
        }
    }

    @AfterTest
    private void endTest() {
    }

    @After
    public void endScenario(Scenario scenario) throws IOException {
        ExtentReporter.flush();
    }
}
