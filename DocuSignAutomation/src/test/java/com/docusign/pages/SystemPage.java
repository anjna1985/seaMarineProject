package com.docusign.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SystemPage extends Base {

	private static final By contractTemplateLocator = By.linkText("Contract Templates");
	public SystemPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}
	
	public ContractTemplateListPage goToContractTemplateListPage() {
		clickOn(contractTemplateLocator);
		return new ContractTemplateListPage(driver, wait);

	}

}
