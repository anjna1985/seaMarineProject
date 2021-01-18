package Testcases;

import java.io.FileInputStream;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PrintandSignStoredContractsCheck {
	
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
		d.get("https://admin-qa2.shmarinas.com");
	    d.findElement(By.name("loginfmt")).sendKeys("nikhila.anand@tekzenit.com");
	    d.findElement(By.id("idSIButton9")).click();
	    Thread.sleep(2000);
	    d.findElement(By.name("passwd")).sendKeys("T3kP@ss67%*90");
	    d.findElement(By.id("idSIButton9")).click();
		Thread.sleep(5000);
		
		FileInputStream fs = new FileInputStream("D:\\SHM\\Template\\EditWordTemplateOptionsTestData.xlsx"); // Need to add your test data sheet location
		XSSFWorkbook wb = new XSSFWorkbook(fs);		
		XSSFSheet s=wb.getSheetAt(0);	
		DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale 

		for( int i=1; i<s.getLastRowNum()+1; i++)			
		{ 
			/*Navigating to selected marina*/       
			WebElement marina_selector = d.findElement(By.id("marinaSelector"));
			marina_selector.click();

			List<WebElement> marina_selector_options = marina_selector.findElements(By.cssSelector("ul > li"));

			for (WebElement li : marina_selector_options) {
				WebElement span = li.findElement(By.tagName("span"));
				if (span.getText().trim().equals(s.getRow(i).getCell(2).getStringCellValue())) 
				{
					li.click();
					break;
				}
			}
			
			      
		       /*Navigating to Contracts Page*/
		       d.findElement(By.linkText("Contracts")).click();       
		     
		       /*Click on Filters link and check the "Id" checkbox*/
		       d.findElement(By.xpath("//span[contains(.,'Filters...')]")).click();       
		       List<WebElement> ctrt_filters = d.findElements(By.cssSelector("p-checkbox > label"));              
		       for(int k=0;k<ctrt_filters.size();k++){
		       if (ctrt_filters.get(k).getText().trim().equals("Id")) 
		   	   {
		    	   ctrt_filters.get(k).click();
		   	   break;
		   	   }
		       }
		       Thread.sleep(4000);
		       d.findElement(By.cssSelector("span.ui-button-icon-left.ui-clickable.fa.fa-times-circle")).click();
		      
		       /*Clicking on the filtered contract based on provided "Id" */
		      
		       WebElement contract_table = d.findElement(By.className("ui-table-thead"));       
		       Cell cell = s.getRow(i).getCell(0);
		       String ctrt_id = formatter.formatCellValue(cell);
		       contract_table.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(8) > input")).clear();  
		       contract_table.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(8) > input")).sendKeys(ctrt_id);
		       Thread.sleep(1000);
		       
		       WebElement contract_tab = d.findElement(By.className("ui-table-tbody"));  
		       Thread.sleep(4000);
		       contract_tab.findElement(By.tagName("td")).click();
		       Thread.sleep(2000);
		       
		       
		     /* Sign the Contract and Upload File for Paper Sign*/     
		     d.findElement(By.xpath("//span[contains(.,'Sign Contract')]")).click(); 
		    
		     WebElement ctrt_sign_temp = d.findElement(By.name("contractSignOptionsDD"));
		     ctrt_sign_temp.click();
		     List<WebElement> ctrt_sign_temp_options = ctrt_sign_temp.findElements(By.cssSelector("ul > li"));
		     for (WebElement li : ctrt_sign_temp_options) 
		     {
		   	 WebElement span = li.findElement(By.tagName("span"));
		   	   	if (span.getText().trim().equals(s.getRow(i).getCell(1).getStringCellValue())) 
		    	{
		    	  li.click();
		    	  break;
		    	}
		     }
		     Thread.sleep(4000);
		     d.findElement(By.xpath("//span[contains(.,'Confirm')]")).click(); 
		     Thread.sleep(5000);
		     /*Check for the success toast message*/
		      String toast_title = d.findElement(By.className("ui-toast-summary")).getText();
		        System.out.println(toast_title);
		        Assert.assertTrue(toast_title.equals("Contract successfully uploaded"), "Data Mismatch");

		         String parent = d.getWindowHandle();
			     System.out.println("Parent Window ID : "+parent);
			     d.findElement(By.linkText("Contract Document")).click(); // TO view/download Uploaded file
			     
			     Set<String> allWindows = d.getWindowHandles();
			     int count = allWindows.size();
			     System.out.println("Tab Count : "+count);
			     
			     for(String child:allWindows)
			     {
			    	if(!parent.equalsIgnoreCase(child)) 
			    	{
			    		d.switchTo().window(child);
			    		Thread.sleep(2000);
			    		String contract_title = d.getTitle();
			    		Assert.assertTrue(contract_title.contains(ctrt_inform_id), "Uploaded Contract is not matched");
			    		d.close();
			    	}
			     }
			     d.switchTo().window(parent);
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     WebElement ctrt_sign_upload_file = d.findElement(By.xpath("//input[@type='file']"));
		     ctrt_sign_upload_file.sendKeys(s.getRow(i).getCell(2).getStringCellValue()); // Provide Upload file location here
		     
		     
		        
			/*Launch Memeber Portal*/
		      d.get("https://members-qa2.shmarinas.com/login");
		      Thread.sleep(5000);
		      d.findElement(By.name("email")).sendKeys(s.getRow(i).getCell(0).getStringCellValue());
		      d.findElement(By.name("password")).sendKeys(s.getRow(i).getCell(1).getStringCellValue());
		      d.findElement(By.id("login-button")).click();
		      Thread.sleep(10000);
		      /*Click on Account tab and Read & Sign button*/
		      d.findElement(By.cssSelector("app-header > div > div > div:nth-child(3) > div")).click();
		      Thread.sleep(3000);
		      	 //String parent = d.getWindowHandle();
			     //System.out.println("Parent Window ID : "+parent);
			     d.findElement(By.xpath("//span[contains(.,'Read And Sign')]")).click(); 	
			     Thread.sleep(3000);
			     
		}
	}
}
