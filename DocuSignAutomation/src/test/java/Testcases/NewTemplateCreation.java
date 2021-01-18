package Testcases;

import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class NewTemplateCreation {

	WebDriver d;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Nikhila.Anand\\Downloads\\chromedriver_win32\\chromedriver.exe");
		d = new ChromeDriver();  
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(7000,TimeUnit.SECONDS);        
	}

	@Test
	public void test() throws Exception {
		/*Login to SHM Site*/
		d.get("https://az-app-t-next.azurewebsites.net/");
		Thread.sleep(3000);
		d.findElement(By.name("UserName")).sendKeys("p.dantuluri");
		d.findElement(By.name("Password")).sendKeys("Tekz0626!!");
		d.findElement(By.id("submitButton")).click();
		Thread.sleep(5000);

		FileInputStream fs = new FileInputStream("D:\\SHM\\Template\\NewTemplateCreationTestData.xlsx"); // Need to add your test data sheet location
		XSSFWorkbook wb = new XSSFWorkbook(fs);		
		XSSFSheet s=wb.getSheetAt(0);	

		for( int i=1; i<s.getLastRowNum()+1; i++)			
		{ 
			/*Navigating to selected marina*/       
			WebElement marina_selector = d.findElement(By.id("marinaSelector"));
			marina_selector.click();

			List<WebElement> marina_selector_options = marina_selector.findElements(By.cssSelector("ul > li"));

			for (WebElement li : marina_selector_options) {
				WebElement span = li.findElement(By.tagName("span"));
				if (span.getText().trim().equals(s.getRow(i).getCell(3).getStringCellValue())) 
				{
					li.click();
					break;
				}
			}
			Thread.sleep(2000);
			WebElement scrollPanelElement = d.findElement(By.xpath("//p-scrollpanel//div[contains(@class,'bar-y')]"));
			WebDriverWait wait = new WebDriverWait(d, 150);
			wait.until(ExpectedConditions.visibilityOf(scrollPanelElement));
			//boolean Scroll_Bar=scrollPanelElement.isEnabled();

			/*Navigating to Systems Menu*/
			d.findElement(By.linkText("System")).click();
			Thread.sleep(2000);

			d.findElement(By.linkText("Contract Templates")).click();
			Thread.sleep(2000);

			WebElement template_new = d.findElement(By.cssSelector("p-splitbutton > div"));
			template_new.findElement(By.cssSelector(".ui-splitbutton-menubutton > span")).click();
			List<WebElement> template_new_options = template_new.findElements(By.cssSelector("ul > li"));
			for (WebElement li : template_new_options) {
				WebElement span = li.findElement(By.tagName("span"));
				if (span.getText().trim().contains(s.getRow(i).getCell(1).getStringCellValue())) {
					li.click();
					Thread.sleep(1000);
					break;
				}
			}

			/*Navigate back to Customers List Page*/
			boolean btnctrtviewlink=d.findElement(By.xpath("//span[@class='ui-menuitem-text'][contains(.,'Contract Template View')]")).isDisplayed();
			boolean btncancel=d.findElement(By.xpath("//span[contains(.,'Cancel')]")).isEnabled();
			boolean btnsave=d.findElement(By.xpath("//span[contains(.,'Save')]")).isDisplayed();
			if(btnctrtviewlink==true && btncancel==true && btnsave==true)
			{
				System.out.println("Navigated to Contract Template View page");
			}
			else
			{
				System.out.println("Contract Template View page not navigated");
			}
			Thread.sleep(2000);

			WebElement template_name =  d.findElement(By.cssSelector("tek-input[controlname='name']"));
			template_name.findElement(By.tagName("input")).sendKeys(s.getRow(i).getCell(0).getStringCellValue());
			Thread.sleep(2000);

			/*
			WebElement ctrt_temp_type = d.findElement(By.cssSelector("tek-enum[controlname='contractTemplateTypeId']"));
			ctrt_temp_type.click();
			Thread.sleep(2000);
			List<WebElement> ctrt_temp_type_options = ctrt_temp_type.findElements(By.cssSelector("ul > li"));
			for (WebElement li : ctrt_temp_type_options) 
			{
				WebElement divtext = li.findElement(By.tagName("span"));
				if (divtext.getText().trim().equals(s.getRow(i).getCell(2).getStringCellValue())) 
				{
					li.click();
					break;
				}
			}
			
			Thread.sleep(2000);
			boolean btnsavechk=d.findElement(By.xpath("//span[contains(.,'Save')]")).isEnabled();
			if(btnsavechk==false)
			{
				System.out.println("Atleast one marina to be selected");
			}
			else
			{
				System.out.println("Save enabled without selecting marina"+btnsavechk);
			}
			Thread.sleep(2000);
			WebElement marina_multi = d.findElement(By.xpath("//div[@class='multi-select-contract-template']//span"));
			marina_multi.click();
			Thread.sleep(2000);
			d.findElement(By.xpath("(//span[@class='ui-chkbox-icon ui-clickable'])[3]")).click();
			Thread.sleep(2000);
			d.findElement(By.xpath("(//span[@class='ui-chkbox-icon ui-clickable'])[3]")).click();
			Thread.sleep(2000);
			d.findElement(By.xpath("(//span[@class='ui-chkbox-icon ui-clickable'])[3]")).click();
			d.findElement(By.xpath("(//span[@class='ui-chkbox-icon ui-clickable'])[4]")).click();
			Thread.sleep(2000);

			template_name.clear();
			d.findElement(By.xpath("//span[contains(.,'Save')]")).click();
			template_name.findElement(By.tagName("div")).getAttribute("value");
			System.out.println("Template Required field message: "+template_name.getText());
			Thread.sleep(2000);
			*/
			d.findElement(By.xpath("//span[contains(.,'Cancel')]")).click();
			Thread.sleep(2000);

		}
		/* Logging Out */
		d.findElement(By.cssSelector(".topbar-item-name")).click();  
		d.findElement(By.xpath("//span[contains(.,'Logout')]")).click();


	}
	@AfterTest

	public void tearDown()
	{
		d.quit();

	}

}

