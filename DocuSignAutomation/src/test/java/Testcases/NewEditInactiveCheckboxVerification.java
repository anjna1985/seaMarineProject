package Testcases;

	import java.io.FileInputStream;
	import java.util.List;
	import java.util.concurrent.TimeUnit;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

	public class NewEditInactiveCheckboxVerification {

		WebDriver d;
		public static String downloadPath = "C:\\Users\\Nikhila.Anand\\Downloads";  //Need to set our downloads path here 
		
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

			FileInputStream fs = new FileInputStream("D:\\SHM\\Template\\NewEditInactiveCheckboxVerification.xlsx"); // Need to add your test data sheet location
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
					if (span.getText().trim().equals(s.getRow(i).getCell(0).getStringCellValue())) 
					{
						li.click();
						break;
					}
				}

				d.findElement(By.linkText("System")).click();
				Thread.sleep(2000);
				d.findElement(By.linkText("Contract Templates")).click();
				Thread.sleep(2000);
				
				Cell cell = s.getRow(i).getCell(2);
			    String temp_name = formatter.formatCellValue(cell);		
				WebElement template_table = d.findElement(By.className("ui-table-thead"));
				template_table.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(1) > input")).clear();
				template_table.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(1) > input")).sendKeys(temp_name);
				Thread.sleep(2000);		 
				WebElement template_table_list = d.findElement(By.className("ui-table-tbody"));       
				template_table_list.findElement(By.tagName("td")).click();
			    Thread.sleep(3000);
				
			    /*Edit the Contract Template
				d.findElement(By.xpath("//span[contains(.,'Edit')]")).click(); 
				Thread.sleep(3000);
				d.findElement(By.xpath("//label[contains(.,'Upload')]")).click(); 
				Thread.sleep(3000);
				WebElement ctrt_upload_file = d.findElement(By.xpath("//input[@type='file']"));
			    ctrt_upload_file.sendKeys(s.getRow(i).getCell(1).getStringCellValue()); // Provide the upload file location
			    Thread.sleep(2000);
			    d.findElement(By.xpath("//span[contains(.,'Save')]")).click(); 
				Thread.sleep(2000); */
			    d.findElement(By.xpath("//span[contains(.,'Edit')]")).click(); 
				Thread.sleep(3000);
				/*boolean inactivechkbox = d.findElement(By.xpath("//p-checkbox//div//span[@class='ui-chkbox-icon ui-clickable']")).isSelected();
				Thread.sleep(2000);*/
				d.findElement(By.xpath("//span[@class='ui-chkbox-icon ui-clickable']")).click();
				Thread.sleep(1000);
				/*if (inactivechkbox == true) {
				    	 System.out.println("Inactive checkbox is enabled"+inactivechkbox);
				     }
				     else
				     {
				    	 System.out.println("Inactive checkbox is disabled"+inactivechkbox);
				     }
				/*WebElement inActiveChk =  d.findElement(By.xpath("//span[@class='ui-chkbox-icon ui-clickable']"));
				d.findElement(By.xpath("//span[@class='ui-chkbox-icon ui-clickable']")).click();
			     if(inActiveChk.isSelected())
			     { 
			    	 System.out.println("Inactive checkbox is enabled");
			     }
			     else
			     {
			    	 System.out.println("Inactive checkbox is disabled");
			     }*/
			     Thread.sleep(2000);
			     d.findElement(By.xpath("//span[contains(.,'Save')]")).click(); 
				 Thread.sleep(2000);
				 
			 /*Navigating to Customers Page*/
	        d.findElement(By.linkText("Customers")).click();
	        Thread.sleep(2000);
			WebElement custfilterName=d.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(2) > input"));
			custfilterName.clear();
	        custfilterName.sendKeys(s.getRow(i).getCell(10).getStringCellValue());
	        Thread.sleep(2000);
	        d.findElement(By.xpath("(//tbody//tr[@class='ng-star-inserted'])[1]")).click();
	        Thread.sleep(3000);
	        
	        /*Navigating to Contracts Tab*/
	        d.findElement(By.xpath("//a//span[text()='Contracts']")).click();       
	        d.findElement(By.xpath("//button//span[text()='Add New Contract']")).click();
	        Thread.sleep(2000);
			WebElement ctrt_incep_date = d.findElement(By.cssSelector("tek-datepicker[controlname='inception']"));
			ctrt_incep_date.findElement(By.tagName("button")).click();
			Select ctrt_incep_date_month = new Select(ctrt_incep_date.findElement(By.className("ui-datepicker-month")));
			ctrt_incep_date_month.selectByVisibleText(s.getRow(i).getCell(3).getStringCellValue());       
			Select ctrt_incep_date_year = new Select(ctrt_incep_date.findElement(By.className("ui-datepicker-year")));
			Cell cella = s.getRow(i).getCell(4);
			String incep_year = formatter.formatCellValue(cella);
			ctrt_incep_date_year.selectByValue(incep_year); 
			Cell cellb = s.getRow(i).getCell(5);
			String incep_date = formatter.formatCellValue(cellb);
			ctrt_incep_date.findElement(By.linkText(incep_date)).click();
			Thread.sleep(1000);

			WebElement ctrt_space_type = d.findElement(By.cssSelector("tek-dropdown[controlname='spaceId']"));
			ctrt_space_type.click();     
			Thread.sleep(5000);
			List<WebElement> ctrt_space_type_options = ctrt_space_type.findElements(By.cssSelector("ul > li"));
			for (WebElement li : ctrt_space_type_options) 
			{
				WebElement span = li.findElement(By.tagName("span"));
				if (span.getText().trim().equals(s.getRow(i).getCell(6).getStringCellValue())) 
				{
					li.click();
					break;
				}
			}

			WebElement ctrt_boats_type = d.findElement(By.cssSelector("tek-dropdown[controlname='boatId']"));
			ctrt_boats_type.click();
			List<WebElement> ctrt_boats_type_options = ctrt_boats_type.findElements(By.cssSelector("ul > li"));
			for (WebElement li : ctrt_boats_type_options) 
			{
				WebElement span = li.findElement(By.tagName("span"));
				if (span.getText().trim().contains(s.getRow(i).getCell(7).getStringCellValue())) 
				{
					li.click();
					break;
				}
			}

			/*Saving the Information*/
			d.findElement(By.xpath("//span[contains(.,'Save')]")).click(); 

			      /*Navigate to Contract View to add items and activate the Contract*/
			      WebElement ctrt_items_type = d.findElement(By.cssSelector("p-autocomplete[formcontrolname='item']"));
			      ctrt_items_type.findElement(By.tagName("button")).click();
			      List<WebElement> ctrt_items_type_options = ctrt_items_type.findElements(By.cssSelector("ul > li"));
			      for (WebElement li : ctrt_items_type_options) 
			      {
			    	//if (li.getText().trim().equals(s.getRow(i).getCell(8).getStringCellValue())) 
			    	{
			    	  li.click();
			    	  break;
			    	}
			      }
			      Thread.sleep(4000); 
			      
			      /* An item entry is added in a table */
			      WebElement table = d.findElement(By.className("ui-table-tbody"));
			      WebElement ctrt_items_billing = table.findElement(By.cssSelector("tek-enum[controlname='billingFrequency']"));
			      ctrt_items_billing.click();
			      Thread.sleep(1000);
			      List<WebElement> ctrt_items_billing_options = ctrt_items_billing.findElements(By.cssSelector("ul > li"));
			      for (WebElement li : ctrt_items_billing_options) 
			      {
			    	WebElement span = li.findElement(By.tagName("span"));
			    	if (span.getText().trim().contains(s.getRow(i).getCell(9).getStringCellValue())) 
			     	{
			     	  li.click();
			     	  break;
			     	}
			      }
			      Thread.sleep(2000);
			         
			     /* Save the item entry */
				  d.findElement(By.xpath("//span[@class='ui-button-icon-left ui-clickable fa fa-save']")).click();
				  Thread.sleep(4000);
				     
				  				
				   				     
			/* Activate the Contract */
				     d.findElement(By.xpath("//span[contains(.,'Activate')]")).click();         
				     WebElement ctrt_act_temp = d.findElement(By.cssSelector("tek-dropdown[controlname='contractTemplateId']"));
				     ctrt_act_temp.click();
				     List<WebElement> ctrt_act_temp_options = ctrt_act_temp.findElements(By.cssSelector("ul > li"));
				     for (WebElement li : ctrt_act_temp_options) 
				     {
				   	 WebElement span = li.findElement(By.tagName("span"));
				   	 if (span.getText().trim().equals(s.getRow(i).getCell(1).getStringCellValue())) 
				    	{
				    	  System.out.println("Inactivate Contract Template not found");
				    	} else
				    	{
				    		System.out.println("Inactivate Contract Template found");
				    	}
				     }
				     
				    Thread.sleep(2000);
				     d.findElement(By.xpath("//span[@class='pi pi-times']")).click(); 
				     Thread.sleep(2000);
				     d.findElement(By.linkText("System")).click();
						Thread.sleep(2000);
						d.findElement(By.linkText("Contract Templates")).click();
						Thread.sleep(2000);
						
						Cell cellc = s.getRow(i).getCell(1);
					    String temp_name1 = formatter.formatCellValue(cellc);		
						WebElement temp_table = d.findElement(By.className("ui-table-thead"));
						temp_table.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(1) > input")).clear();
						temp_table.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(1) > input")).sendKeys(temp_name1);
						Thread.sleep(2000);		 
						WebElement template_tablelist = d.findElement(By.className("ui-table-tbody"));       
						template_tablelist.findElement(By.tagName("td")).click();
					    Thread.sleep(3000);
						
					    /*Edit the Contract Template*/
						
						d.findElement(By.xpath("//span[contains(.,'Edit')]")).click(); 
						Thread.sleep(3000);     		     
						WebElement inActiveChkbox =  d.findElement(By.xpath("//span[@class='ui-chkbox-icon ui-clickable']"));
						inActiveChkbox.click();
					     
					     Thread.sleep(2000);
					     d.findElement(By.xpath("//span[contains(.,'Save')]")).click(); 
						 Thread.sleep(2000); 
						 /*Navigating to Contracts Page*/
					       d.findElement(By.linkText("Contracts")).click();
					       /*Clicking on the filtered contract based on provided "Id" */
					       WebElement contract_table = d.findElement(By.className("ui-table-thead"));       
					       WebElement contract_tab = d.findElement(By.className("ui-table-tbody"));       
					       contract_tab.findElement(By.tagName("td")).click();
					       Thread.sleep(2000);
			       
			       /* Activate the Contract */
			     d.findElement(By.xpath("//span[contains(.,'Activate')]")).click();    
			     
			     
			     WebElement ctrt_act_temp_active = d.findElement(By.cssSelector("tek-dropdown[controlname='contractTemplateId']"));
			     ctrt_act_temp_active.click();
			     Thread.sleep(3000);
			     List<WebElement> ctrt_act_temp_options_active = ctrt_act_temp_active.findElements(By.cssSelector("ul > li"));
			     for (WebElement li : ctrt_act_temp_options) 
			     {
			   	 WebElement span = li.findElement(By.tagName("span"));
			   	//System.out.println("List : "+span.getText());
			   	if (span.getText().trim().equals(s.getRow(i).getCell(1).getStringCellValue())) 
			    	{
			    	  li.click();
			    	  break;
			    	}
			     }
			     
			     Thread.sleep(1000);
		    
			     d.findElement(By.xpath("//span[contains(.,'Confirm')]")).click(); 
			     Thread.sleep(2000);
			     /*Check for the success message*/
			      String toast_message = d.findElement(By.className("ui-toast-summary")).getText();
			        System.out.println(toast_message);
			        Assert.assertTrue(toast_message.equals("Contract successfully uploaded"), "Data Mismatch");
			        WebElement nonrenewalChk =  d.findElement(By.xpath("//p-checkbox[@label='Non-Renewal']"));
				     if(nonrenewalChk.isSelected())
				     { 
				    	 System.out.println("Non Renewal checkbox is enabled");
				     }
				     else
				     {
				    	   System.out.println("Non Renewal checkbox is disabled");
				     }
				     Thread.sleep(2000);  
			}
			     
			    /* Logging Out */
				d.findElement(By.cssSelector(".topbar-item-name")).click();  
				d.findElement(By.xpath("//span[contains(.,'Logout')]")).click();
			}
		
			@AfterTest
			public void tearDown()
			{
				d.close();
				d.quit();

			}
		  
	}