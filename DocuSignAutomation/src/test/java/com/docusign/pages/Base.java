package com.docusign.pages;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Base {
	protected  WebDriver driver;// null
	protected static WebDriverWait wait;
	
	public Base(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		}


	protected String getLinkRef(By searcharticlelocator2) {
		// TODO Auto-generated method stub
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searcharticlelocator2));
		String text = element.getAttribute("href");
		return text;
	}

	public void enterText(String textToEnter, By Locator) {
		WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
		inputElement.sendKeys(textToEnter);

	}

	public void clickOn(By Locator)  {

		WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(Locator));
		buttonElement.click();
//		Thread.sleep(5000);
	}

	public String getUserName(By Locator) {
		WebElement userNameSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
		String userName = userNameSpan.getAttribute("data-fullname");
		return userName;
	}

	protected boolean isElementPresent(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		if (element == null) {
			return false;
		} else {
			return true;// element present
		}

	}

	protected int countOfElements(By locator, By loaderLocator) {
		WebDriverWait waitNew = new WebDriverWait(driver, 30);
		waitNew.until(ExpectedConditions.visibilityOfElementLocated(loaderLocator));

		waitNew.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
		List<WebElement> elements = waitNew.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return elements.size();
	}
	
	public  void closeBrowser() {
		driver.quit();
	}
}
