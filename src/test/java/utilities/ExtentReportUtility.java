package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.BaseClass;

public class ExtentReportUtility implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext testContext) {
        // Setting up the timestamp and report name
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        
        // Initializing ExtentSparkReporter
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // Specify location of the report
        
        sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Title of the report
        sparkReporter.config().setReportName("Opencart Functional Testing"); // Name of the report
        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
        
        // Initializing ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        
        // Get OS and Browser info from testng.xml
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("Browser");
        extent.setSystemInfo("Browser", browser);

        // Get included groups if any
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // To display groups in the report
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.skip(result.getName() + " got skipped");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
        try {
            //Desktop.getDesktop().browse(new File(".\\reports\\" + repName).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
