package Testcases;

	import java.io.FileInputStream;
	import java.util.List;
	import java.util.concurrent.TimeUnit;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.PageLoadStrategy;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

	public class UpdateSignedContractCheck {

		WebDriver d;
		
		@BeforeTest
		 public void setUp() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nikhila.Anand\\Downloads\\chromedriver_win32\\chromedriver.exe"); //Need to set chromedriver path here
	      //d = new ChromeDriver();  
			ChromeOptions ch=new ChromeOptions();
	  	    ch.setPageLoadStrategy(PageLoadStrategy.NONE); 
	        d = new ChromeDriver(ch);
	      d.manage().window().maximize();
	      d.manage().timeouts().implicitlyWait(7000,TimeUnit.SECONDS);        
		}
	@Test
		public void test() throws Exception {
			// TODO Auto-generated method stub
		
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
			WebElement marina_selector = d.findElement(By.id("marinaSelector"));
			marina_selector.click();
			Thread.sleep(1000);
			List<WebElement> marina_selector_options = marina_selector.findElements(By.cssSelector("ul > li"));
			for (WebElement li : marina_selector_options) {
				WebElement span = li.findElement(By.tagName("span"));
				if (span.getText().trim().equals(s.getRow(i).getCell(5).getStringCellValue())) {
					li.click();
					
					break;
				}
			}
	       Thread.sleep(5000); 
	       
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
	     
	     WebElement ctrt_sign_upload_file = d.findElement(By.xpath("//input[@type='file']"));
	     ctrt_sign_upload_file.sendKeys(s.getRow(i).getCell(2).getStringCellValue()); // Provide Upload file location here
	     
	     Thread.sleep(4000);
	     d.findElement(By.xpath("//span[contains(.,'Confirm')]")).click(); 
	     Thread.sleep(5000);
	        
	     /* Check the contract status is in Active and it is Signed */     
	     /*String ctrt_sign_status = d.findElement(By.cssSelector("contract > form > div.layout-content-header > div:nth-child(3) > div:nth-child(1)")).getText();
	     String ctrt_sign_statuses[] = ctrt_sign_status.split(": ");
	     String ctrt_stat_curr=ctrt_sign_statuses[1];
	     Assert.assertEquals(s.getRow(i).getCell(3).getStringCellValue(), ctrt_stat_curr);*/
	     
	     String ctrt_sign_paper = d.findElement(By.cssSelector("contract > form > div.layout-content-header > div:nth-child(3) > div:nth-child(2)")).getText();
	     String ctrt_sign[] = ctrt_sign_paper.split(": ");
	     String ctrt_sign_curr=ctrt_sign[1];
	     Assert.assertTrue(ctrt_sign_curr.contains(s.getRow(i).getCell(5).getStringCellValue()), "Data doesn't match" );
	     
	     /*WebElement ctrt_sign_calendar = d.findElement(By.cssSelector("contract > form > div.layout-content-header > div:nth-child(2) > div:nth-child(1)"));
	     System.out.println("Contract Duration : "+ctrt_sign_calendar.getText());
	     
	     WebElement ctrt_sign_duration = d.findElement(By.cssSelector("contract > form > div.layout-content-header > div:nth-child(2) > div:nth-child(2)"));
	     System.out.println("Contract Duration(in Months) : "+ctrt_sign_duration.getText());*/
	     
	    /*Navigate back to Contracts Page*/     
	     d.findElement(By.linkText("Contracts")).click();
	     Thread.sleep(2000);
	 
		}
		   
		/* Logging Out*/     
		d.findElement(By.cssSelector(".topbar-item-name")).click();
		d.findElement(By.xpath("//span[contains(.,'Logout')]")).click();
	     
	}
		/*@AfterTest*/
			
			public void tearDown()
			{
			 d.quit();
			 d.close();

		    }

	}

