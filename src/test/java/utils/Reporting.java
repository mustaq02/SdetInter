package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reporting {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static final Logger logger = LogManager.getLogger(Reporting.class);

    public static ExtentReports getExtent() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("Stock Info Test Suite");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static void startTest(String testName) {
        test = getExtent().createTest(testName);
        logger.info("Started test: " + testName);
    }

    public static void logInfo(String message) {
        if (test != null) {
            test.info(message);   // Log in report
        }
        logger.info(message);     // Log in log4j
    }

    public static void logFail(String message) {
        if (test != null) {
            test.fail(message);
        }
        logger.error(message);
    }

    public static void logPass(String message) {
        if (test != null) {
            test.pass(message);
        }
        logger.info(message);
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static ExtentTest getTest() {
        return test;
    }
}