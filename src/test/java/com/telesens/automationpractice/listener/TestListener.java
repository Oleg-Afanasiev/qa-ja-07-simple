package com.telesens.automationpractice.listener;

import com.telesens.framework.test.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest test = (BaseTest) result.getInstance();
        test.saveScreenshotPNG();
    }
}
