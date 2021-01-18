package com.docusign.tests.ui;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.docusign.pages.ContractTemplateListPage;
import com.docusign.pages.DashboardPage;
import com.docusign.pages.LoginPage;
import com.docusign.pages.SystemPage;

import io.qameta.allure.Description;

public class DashboardPageTest extends TestBase {
	LoginPage loginPage;
	DashboardPage dashboardPage;
	SystemPage systemPage;
	ContractTemplateListPage contractTemplateListPage;

	@Description("Calling Setup Method to initiate the Browser")
	@BeforeClass
	public void setup() throws InterruptedException {
		start();
		loginPage = new LoginPage(driver, wait);
		dashboardPage = loginPage.login("nikhila.anand@tekzenit.com", "T3kP@ss67%*90");
	}

	@Description("Verifies the login operation for Univadis")
	@Test(testName = "loginTest", description = "verifies login")
	public void goToMarinaSystemPage() throws InterruptedException {
		dashboardPage.selectMarina("Safe Harbor Pier 121");
		systemPage = dashboardPage.goToSystemPage();
		contractTemplateListPage= systemPage.goToContractTemplateListPage();
		contractTemplateListPage.clickNewDocument("Word");
	
	}

//	@Description("Calling TearDown() to Quit the Browser")
//	@AfterMethod()
//	public void tearDown() {
//		loginPage.closeBrowser();
//	}
}
