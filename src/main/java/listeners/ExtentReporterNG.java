package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReporterNG implements ITestListener {
    ExtentReports extent;
    ExtentTest test;

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    // Method to set Page (call from your base class, e.g., BMSBase.setup())
    public static void setPage(Page webPage) {
        page.set(webPage);
    }

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentManager.createInstance("ExtentReport.html");
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed");
        extentTest.get().fail(result.getThrowable());
        captureScreenshot(result.getMethod().getMethodName());  // Capture and attach
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Other methods can be implemented as needed
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // Not implemented
    }

    private void captureScreenshot(String testName) {
        Page webPage = page.get();
        if (webPage == null) {
            extentTest.get().fail("Screenshot not captured: Page instance is null");
            return;  // Skip if no page
        }

        try {
            // Create screenshots directory if it doesn't exist
            String screenshotDir = "test-output/screenshots/";
            Files.createDirectories(Paths.get(screenshotDir));

            // Generate unique filename
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = screenshotDir + fileName;

            // Take screenshot using Playwright
            byte[] screenshotBytes = webPage.screenshot();

            // Save screenshot to file
            Files.write(Paths.get(filePath), screenshotBytes);

            // Attach to ExtentReports
            extentTest.get().fail("Screenshot attached", MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());

        } catch (Exception e) {
            extentTest.get().fail("Failed to capture screenshot: " + e.getMessage());
        }
    }
}