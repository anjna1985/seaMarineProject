package com.docusign.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class CustomRetryLogic implements IRetryAnalyzer {

	static int count = 1;

	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub TRUE ----Retry TestMethod
		// FALSE------ Don't retry the test method and will mark it as a failure
		if (count < 3) {
			count++;
			return true; //Retrying the failed Tests
		} else {
			return false; //Marking the test as failed
		}

	}

}
