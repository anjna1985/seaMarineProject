package com.docusign.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class LoginPage extends Base {
	private static final By userNameLocator = By.name("loginfmt");
	private static final By passwordLocator = By.name("passwd");
	private static final By nextButtonLocator = By.id("idSIButton9");

	public LoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	@Step("Login into the application with userName:{0} and password {1}")
	public DashboardPage login(String userName, String password) throws InterruptedException {
		enterText(userName, userNameLocator);
		clickOn(nextButtonLocator);
		enterText(password, passwordLocator);
		clickOn(nextButtonLocator);
		return new DashboardPage(driver, wait); // Page Methods should always return page objects
	}

}
