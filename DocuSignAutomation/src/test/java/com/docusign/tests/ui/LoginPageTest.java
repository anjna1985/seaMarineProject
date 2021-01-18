package com.docusign.tests.ui;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.docusign.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Listeners(com.docusign.listeners.CustomListeners.class)
@Epic("Regression Test")
@Feature("Login Page Test")
public class LoginPageTest extends TestBase {
	LoginPage loginPage;

	@Description("Calling Setup Method to initiate the Browser")
	@BeforeClass
	public void setup() {
		start();
	}

	@Description("Verifies the login operation for Univadis")
	@Test(testName = "loginTest", description = "verifies login")
	public void loginTest() throws InterruptedException {
		loginPage = new LoginPage(driver, wait);
		loginPage.login("nikhila.anand@tekzenit.com", "T3kP@ss67%*90");
	}

	@Description("Calling TearDown() to Quit the Browser")
	@AfterMethod()
	public void tearDown() {
		loginPage.closeBrowser();
	}
}
