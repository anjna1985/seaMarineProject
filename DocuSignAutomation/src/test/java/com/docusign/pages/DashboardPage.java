package com.docusign.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends Base {

	private static final By marinaLocator = By.id("marinaSelector");
	private static final By systemLinkLocator = By.linkText("System");

	public DashboardPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	public void selectMarina(String marinaName) {
		clickOn(marinaLocator);
		By marninaNameLocator = By.xpath("//span[text()='" + marinaName + "']");
		clickOn(marninaNameLocator);

	}

	public SystemPage goToSystemPage() {
		clickOn(systemLinkLocator);
		return new SystemPage(driver, wait);

	}
}
