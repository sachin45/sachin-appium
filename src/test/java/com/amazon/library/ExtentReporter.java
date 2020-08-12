package com.amazon.library;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.Reporter;
import org.testng.xml.XmlTest;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporter extends BaseTest {

    static String reporterfileName = "Amazon Extent Report_" + Utility.getDateWithFormat(new Date(), "ddMMMyyyy") + ".html";
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> featureTestThreadLocal = new InheritableThreadLocal();
    private static ThreadLocal<ExtentTest> scenarioThreadLocal = new InheritableThreadLocal();
    private static ThreadLocal<Boolean> isHookThreadLocal = new InheritableThreadLocal();
    private static ThreadLocal<ExtentTest> stepTestThreadLocal = new InheritableThreadLocal();
    private static XmlTest readXMLparams = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
    private static String DeviceType = readXMLparams.getParameter("DeviceType");


    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            String workspaceloc = System.getProperty("user.dir");

            Date date1 = new Date();
            DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
            String folder = dateFormat.format(date1);

            File theDir = new File(System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReport" + File.separator + folder);
            if (!theDir.exists()) {
                theDir.mkdir();
                Log.info("Info : [EW] Screenshots - Folder created");
            }
            System.setProperty("ExtentReportDailyFolder", theDir.getAbsolutePath());
            if (System.getProperty("RunCounter") == null) {
                System.setProperty("RunCounter", getReporterRunName());
                System.setProperty("ExtentReportFolder", System.getProperty("ExtentReportDailyFolder") + File.separator + System.getProperty("RunCounter") + File.separator);
            }

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("ExtentReportFolder") + reporterfileName);

            htmlReporter.config().enableTimeline(true);
            htmlReporter.config().setAutoCreateRelativePathMedia(true);
            htmlReporter.config().setCSS("css-string");
            htmlReporter.config().setDocumentTitle("Automation Test Execution Results");
            htmlReporter.config().setEncoding("utf-8");
            htmlReporter.config().setJS("js-string");
            htmlReporter.config().setProtocol(Protocol.HTTPS);
            htmlReporter.config().setReportName(reporterfileName);
            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    public synchronized static void flush() {
        extent.flush();
    }

    public synchronized static ExtentTest getTest() {
        return extentTest.get();
    }

    public synchronized static ExtentTest createTest(String name, String description, String category) {
        ExtentTest test = extent.createTest(name, description);
        if (category != null && !category.isEmpty()) test.assignCategory(category);
        extentTest.set(test);
        return getTest();
    }

    public synchronized static ExtentTest createTest(String name, String description) {
        return createTest(name, description, null);
    }

    public synchronized static ExtentTest createTest(String name) {
        return createTest(name, " ");
    }

    public synchronized static void logScenario(String scenarioname) {
        if (scenarioThreadLocal.get() == null) {
            ExtentTest scenario = extent.createTest(scenarioname, "");
            scenarioThreadLocal.set(scenario);
        } else {
            scenarioThreadLocal.get();
        }
    }

    public synchronized static void logStep(Status status, String message) {
        try {
            String screenshot = Utility.screenshot(ThreadLocalDriver.getTLDriver(), message);
            ExtentTest step = getTest().log(status, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        } catch (IOException e) {

        }
    }

    public synchronized static void log(Status status, String message) {
        getTest().log(status, message);
    }

   /* public synchronized static void log(String message) {
        try {
            if (getBuffer("takeScreenEvidence").equalsIgnoreCase("true")) {
                String screenshot = UobUtils.screenshot(ThreadLocalDriver.getTLDriver(), getPageName());
                getTest().log(getTest().getStatus(), message, MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
            } else {
                getTest().log(getTest().getStatus(), message);
            }

        } catch (IOException e) {

        }
    }

    public synchronized static void log(Status status, String message) {
        try {
            if (getBuffer("takeScreenEvidence").equalsIgnoreCase("true")) {
                String screenshot = UobUtils.screenshot(ThreadLocalDriver.getTLDriver(), getPageName());
                if(status==Status.SKIP){
                    getTest().log(status, message);
                }
                else{
                    getTest().log(status, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
                }
            } else {
                getTest().log(status, message);
            }
        } catch (IOException e) {

        }
    }
*/

    private synchronized static String getReporterRunName() {
        String filepath = System.getProperty("ExtentReportDailyFolder");
        String filename = "runcounter.txt";
        int newrun = 1;
        File runCounterFile = new File(filepath + File.separator + filename);
        if (!runCounterFile.exists()) {
            BufferedWriter bw = null;
            FileWriter fw = null;
            try {

                fw = new FileWriter(runCounterFile.getAbsolutePath(), false);
                bw = new BufferedWriter(fw);
                bw.write("Run:1");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null)
                        bw.close();
                    if (fw != null)
                        fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            BufferedWriter bw = null;
            FileWriter fw = null;
            try {

                BufferedReader br = new BufferedReader(new FileReader(runCounterFile.getAbsolutePath()));
                String st;
                st = br.readLine();

                br.close();
                String[] runns = st.split(":");
                newrun = Integer.parseInt(runns[1]) + 1;

                fw = new FileWriter(runCounterFile.getAbsolutePath(), false);
                bw = new BufferedWriter(fw);
                bw.write("Run:" + newrun);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null)
                        bw.close();
                    if (fw != null)
                        fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return "Run-" + newrun;
    }


    public synchronized static void info(String message) {
        getTest().info(message);
    }

    public synchronized static void addScreenshot(String filepath, String title) throws IOException {
        getTest().addScreenCaptureFromPath(filepath, title);
    }


}
