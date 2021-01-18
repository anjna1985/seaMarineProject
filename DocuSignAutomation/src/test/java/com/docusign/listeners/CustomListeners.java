package com.docusign.listeners;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.docusign.tests.ui.TestBase;
import com.docusign.utility.TestUtility;

public class CustomListeners implements ITestListener {
	ExtentHtmlReporter extentHTML;//
	ExtentReports extent;//
	ExtentTest test;

	public static final Logger LOG = LoggerFactory.getLogger(CustomListeners.class);

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("******************** Test Started*************" + result.getMethod().getMethodName());
		LOG.info("******************** Test Started*************");
		LOG.info(result.getMethod().getMethodName());

		test = extent.createTest("TC " + result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("******************** Test Passed*************" + result.getMethod().getMethodName());
		test.pass("Status PASS " + result.getMethod().getMethodName());
		LOG.info("******************** Test Passed*************");
		LOG.info(result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("******************** Test Failed*************" + result.getMethod().getMethodName());
		test.fail("Status FAILED " + result.getMethod().getMethodName());
		LOG.error("******************** Test Passed*************");
		LOG.error(result.getMethod().getMethodName());

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("********************  Test Skipped*************" + result.getMethod().getMethodName());
		test.skip("Status SKIPPED " + result.getMethod().getMethodName());
		LOG.error("******************** skip*************");
		LOG.error(result.getMethod().getMethodName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("******************** Test Suite*************" + context.getClass().getSimpleName());

		java.io.File reportDirectory = new java.io.File(System.getProperty("user.dir") + "/report");
		java.io.File screenshotDirectory = new java.io.File(System.getProperty("user.dir") + "/screenshot");

		try {
			if (reportDirectory.exists() && screenshotDirectory.exists()) {
				System.out.print("Trying to delete the report folder......");
				FileUtils.deleteDirectory(reportDirectory);
				FileUtils.deleteDirectory(screenshotDirectory);
			}
			FileUtils.forceMkdir(reportDirectory);
			FileUtils.forceMkdir(screenshotDirectory);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("******************** Test Suite*************");
		String pattern = "dd-MM-yyyy-HH-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date d = new Date();
		System.out.println(d.toString());
		String date = simpleDateFormat.format(d);
		LOG.info("******************** Extent HTML ref created *************");

		extentHTML = new ExtentHtmlReporter("report/report-" + date + ".html");
		extent = new ExtentReports();
		extent.attachReporter(extentHTML);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out
				.println("******************** Test Suite Completed*************" + context.getClass().getSimpleName());
		extent.flush();// mandatory
		LOG.info("******************** Extent HTML report created *************");

	}

}
