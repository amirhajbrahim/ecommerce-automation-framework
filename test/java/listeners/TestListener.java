package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getInstance() instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) result.getInstance();
            baseTest.takeScreenshot();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
    }
}