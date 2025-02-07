package com.qa.genericscripts;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners {
	public class MyTestListener implements ITestListener {
	    @Override
	    public void onTestStart(ITestResult result) {
	        System.out.println("Test Started: " + result.getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        System.out.println("Test Passed: " + result.getName());
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        System.out.println("Test Failed: " + result.getName());
	    }
	}

}
