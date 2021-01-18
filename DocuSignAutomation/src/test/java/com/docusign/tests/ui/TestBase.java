package com.docusign.tests.ui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	protected WebDriver driver;
	protected static WebDriverWait wait;

	public static final String USERNAME = "nikhilakanigiri1";
	public static final String AUTOMATE_KEY = "ZdBxsYa7Aye8Dy5EJBy7";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public void start() {
		runLocal();
		wait = new WebDriverWait(driver, 30);
		driver.get("https://admin-qa2.shmarinas.com");
		driver.manage().window().maximize();

	}

	public void runRemote() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("os_version", "10");
		caps.setCapability("resolution", "1920x1080");
		caps.setCapability("browser", "Chrome");
		caps.setCapability("browser_version", "latest");
		caps.setCapability("os", "Windows");
		caps.setCapability("name", "BStack-[Java] Sample Test"); // test name
		caps.setCapability("build", "BStack Build Number 1");
		try {
			driver = new RemoteWebDriver(new java.net.URL(URL), caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void runLocal() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

}
