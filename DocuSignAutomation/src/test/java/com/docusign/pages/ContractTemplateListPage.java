package com.docusign.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContractTemplateListPage extends Base {

	private static final By moreOptionLocator = By.xpath("//span[text()=\"New\"]/../../button[2]");

	public ContractTemplateListPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	public void clickNewDocument(String documentType) {
		documentType = documentType.toUpperCase();
		clickOn(moreOptionLocator);
		By documentLocator = By.xpath("//span[text()='" + documentType + "']");
		clickOn(documentLocator);

	}

}
