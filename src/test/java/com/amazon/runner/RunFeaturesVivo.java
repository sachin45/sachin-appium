package com.amazon.runner;

import com.amazon.library.BaseTest;
import com.amazon.library.PropertyFileReader;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created By: Sachin Saxena
 * Version: 1.0
 */

@CucumberOptions(
        monochrome = true,
        glue = "com.amazon.stepdefinitions",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class RunFeaturesVivo extends BaseTest {

    PropertyFileReader propertyFileReader;
    private TestNGCucumberRunner testNGCucumberRunner;

    private static void changeCucumberAnnotation(Class<?> clazz, String key, Object newValue) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Annotation options = clazz.getAnnotation(CucumberOptions.class);
        InvocationHandler proxyHandler = Proxy.getInvocationHandler(options);
        Field f = proxyHandler.getClass().getDeclaredField("memberValues");
        f.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) f.get(proxyHandler);
        memberValues.remove(key);
        memberValues.put(key, newValue);
    }

    @Parameters({"featurePath", "tags"})
    @BeforeClass(alwaysRun = true)
    public void setUpClass(String featurePath, String tags) throws Exception {
        Class<?> testClass = this.getClass();
        changeCucumberAnnotation(testClass, "features", new String[]{featurePath});
        changeCucumberAnnotation(testClass, "tags", new String[]{tags});
        testNGCucumberRunner = new TestNGCucumberRunner(testClass);
    }

    @Test(groups = "cucumber", description = "Amazon Test Automation", dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEventWrapper, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEventWrapper.getPickleEvent());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        propertyFileReader = new PropertyFileReader();
        testNGCucumberRunner.finish();
    }
}
