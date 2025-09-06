package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Base;
import utils.ScreenshotUtil;

import java.io.File;
import java.io.IOException;

import static utils.Base.driver;

public class Reporting implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(Reporting.class);

    @Override
    public void onStart(ITestContext context) {
        // Create reports directory
        new File("reports/screenshots").mkdirs();

        String reportPath = "reports/ExtentReport.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Stock Info Test Suite");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        logger.info("===== Test Suite Started =====");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        logger.info("===== Test Suite Finished =====");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
        logger.info("Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            String screenshotPath = ScreenshotUtil.capture(driver,
                    "pass_" + result.getMethod().getMethodName());

            testThread.get().pass("Test Passed")
                    .addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            testThread.get().pass("Test Passed (screenshot unavailable)");
        }
        logger.info("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().fail(result.getThrowable());

        try {
            String screenshotPath = ScreenshotUtil.capture(driver,
                    "fail_" + result.getMethod().getMethodName());

            // Embed screenshot in report
            testThread.get().fail("Screenshot on Failure")
                    .addScreenCaptureFromPath(screenshotPath);

            logger.error("Test Failed: " + result.getMethod().getMethodName()
                    + " | Screenshot: " + screenshotPath);
        } catch (IOException e) {
            testThread.get().fail("Failed to attach screenshot");
            logger.error("Screenshot capture failed", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip("Test Skipped");
        logger.warn("Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }
}