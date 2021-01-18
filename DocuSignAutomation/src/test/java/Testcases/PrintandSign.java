package Testcases;

	import java.io.File;
	import java.io.FileInputStream;
	import java.util.List;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.PageLoadStrategy;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;


	public class PrintandSign {

		WebDriver d;
		
		@BeforeTest
		 public void setUp() {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Nikhila.Anand\\Downloads\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions ch=new ChromeOptions();
	  	    ch.setPageLoadStrategy(PageLoadStrategy.NONE); 
	        d = new ChromeDriver(ch);
	        d.manage().window().maximize();
	        d.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);        
		}
		
	@Test
		public void test() throws Exception {
			// TODO Auto-generated method stub
			

		/*Login to SHM Site*/
	    d.get("https://admin-qa2.shmarinas.com");
	    d.findElement(By.name("email")).sendKeys("admin@shm.com");
	    d.findElement(By.name("password")).sendKeys("3ngIq&22M(NX*y3");
	    d.findElement(By.name("submit")).click();
	    //d.get("https://az-app-t-next.azurewebsites.net/");
	    //d.findElement(By.name("UserName")).sendKeys("P.dantuluri");
	    //d.findElement(By.name("Password")).sendKeys("Tekz0626!!");
	    //d.findElement(By.id("submitButton")).click();
	    Thread.sleep(5000);
	    /*FileInputStream fs = new FileInputStream("D:\\SHM\\Level2scripts\\UploadCommercialContractsTestData.xlsx"); // Need to add your test data sheet location
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet s=wb.getSheetAt(0);
		
		for( int i=1; i<s.getLastRowNum()+1; i++)
		{
	       
	        /*Navigating to Safe Harbor Pier 121*       
	        WebElement marina_selector = d.findElement(By.id("marinaSelector"));
	        marina_selector.click();
	        List<WebElement> marina_selector_options = marina_selector.findElements(By.cssSelector("ul > li"));
	        for (WebElement li : marina_selector_options) 
	        {
	     	 WebElement span = li.findElement(By.tagName("span"));
	     	 if (span.getText().trim().equals(s.getRow(i).getCell(11).getStringCellValue())) 
	     	 {
	     	   li.click();
	     	   break;
	     	 }
	     	  }
	      
	        /*Navigating to Customers Page*
	        d.findElement(By.linkText("Customers")).click();
	        Thread.sleep(2000);
	        
			
			WebElement custfilterName=d.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(2) > input"));
			custfilterName.clear();
	        custfilterName.sendKeys(s.getRow(i).getCell(0).getStringCellValue());
	        Thread.sleep(2000);
	        d.findElement(By.xpath("(//tbody//tr[@class='ng-star-inserted'])[1]")).click();
	        Thread.sleep(3000);
	        
	        /*Navigating to Contracts Tab*
	        d.findElement(By.xpath("//a//span[text()='Contracts']")).click();       
	        d.findElement(By.xpath("//button//span[text()='Add New Commercial Contract']")).click();
	        System.out.println("Add New Commercial Contract displayed and clicked");        
	        Thread.sleep(2000);
	        
	        /*Buttons & Fields Verification in Add New Commercial Contract Page*
	        boolean cancelBtn=d.findElement(By.xpath("//span[contains(.,'Cancel')]")).isEnabled(); 
	        boolean saveBtn=d.findElement(By.xpath("//span[contains(.,'Save')]")).isEnabled();
	        if(cancelBtn==true && saveBtn==false)
	        {
	        	System.out.println("Navigated to Add New Commercial Contract page");
	        } 
	        else 
	        {
	        	System.out.println("User is in profile tab Customers page");
	        }
	        
	        boolean memNamefield=d.findElement(By.xpath("//tek-search[@displayname='Member Name']")).isDisplayed();
	        boolean incepDate=d.findElement(By.cssSelector("tek-datepicker[controlname='inception']")).isDisplayed();
	        boolean ctrtYrs=d.findElement(By.xpath("(//input[@type='number'])[1]")).isDisplayed();
	        boolean ctrtMonths=d.findElement(By.xpath("(//input[@type='number'])[2]")).isDisplayed();
	        boolean spaceselect=d.findElement(By.cssSelector("tek-dropdown[controlname='spaceId']")).isDisplayed();
	                
	        if(memNamefield==true && incepDate==true && ctrtYrs==true && ctrtMonths==true && spaceselect==true)
	        {
	        	System.out.println("Column Names displayed in Add New Commercial Contracts page");
	        } 
	        else 
	        {
	        	System.out.println("Column Names not displayed in Add New Commercial Contracts page");
	        }
	        
	       boolean reqdFields1=d.findElement(By.xpath("//tek-datepicker//span[contains(@class,'required ng-star-inserted')]")).isDisplayed();
	       boolean reqdFields2=d.findElement(By.xpath("//tek-dropdown//span[contains(@class,'required ng-star-inserted')]")).isDisplayed();
	        
	        if((reqdFields1==true) && (reqdFields2==true))
	        {
	        	System.out.println("Mandatory fields displayed in Add New Commercial Contracts page");
	        } 
	        else 
	        {
	        	System.out.println("Mandatory fields not displayed in Add New Contract page" +reqdFields1+reqdFields2);
	        }
			  
			    /* Creation of New Commercial Contract*
			       DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
			       WebElement ctrt_incep_date = d.findElement(By.cssSelector("tek-datepicker[controlname='inception']"));
			       ctrt_incep_date.findElement(By.tagName("button")).click();
			       Select ctrt_incep_date_month = new Select(ctrt_incep_date.findElement(By.className("ui-datepicker-month")));
			       ctrt_incep_date_month.selectByVisibleText(s.getRow(i).getCell(1).getStringCellValue());       
			       Select ctrt_incep_date_year = new Select(ctrt_incep_date.findElement(By.className("ui-datepicker-year")));
			       Cell cella = s.getRow(i).getCell(2);
			       String incep_year = formatter.formatCellValue(cella);
			       ctrt_incep_date_year.selectByValue(incep_year); 
			       Cell cellb = s.getRow(i).getCell(3);
			       String incep_date = formatter.formatCellValue(cellb);
			       ctrt_incep_date.findElement(By.linkText(incep_date)).click();
			       Thread.sleep(3000);
			       
			       WebElement con_dur_yr = d.findElement(By.xpath("(//input[@type='number'])[1]"));   
			       con_dur_yr.clear();	   
			       Cell cellc = s.getRow(i).getCell(4);
				   String ctrt_yr = formatter.formatCellValue(cellc);
				   con_dur_yr.sendKeys(ctrt_yr);
			            
				   WebElement con_dur_mnth = d.findElement(By.xpath("(//input[@type='number'])[2]"));   
				   con_dur_mnth.clear();	   
			       Cell celld = s.getRow(i).getCell(5);
				   String ctrt_mnth = formatter.formatCellValue(celld);
				   con_dur_mnth.sendKeys(ctrt_mnth);
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
			           
			      /*Saving the Information*
			      d.findElement(By.xpath("//span[contains(.,'Save')]")).click(); 
			      Thread.sleep(3000); 
			      
			      /*Navigate to Contract View to add items and activate the Contract*
			      WebElement ctrt_items_type = d.findElement(By.cssSelector("p-autocomplete[formcontrolname='item']"));
			      ctrt_items_type.findElement(By.tagName("button")).click();
			      List<WebElement> ctrt_items_type_options = ctrt_items_type.findElements(By.cssSelector("ul > li"));
			      for (WebElement li : ctrt_items_type_options) 
			      {
			    	if (li.getText().trim().equals(s.getRow(i).getCell(7).getStringCellValue())) 
			    	{
			    	  li.click();
			    	  break;
			    	}
			      }
			      Thread.sleep(4000); 
			      
			      /* An item entry is added in a table *
			      WebElement table = d.findElement(By.className("ui-table-tbody"));
			      List<WebElement> allrows = table.findElements(By.tagName("tr"));
			      System.out.println("Number of rows in the table "+allrows.size());
			      
			      List<WebElement> allcols = table.findElements(By.tagName("td"));       
			      System.out.println("Number of columns in the table "+allcols.size());
			      
			      WebElement ctrt_items_billing = table.findElement(By.cssSelector("tek-enum[controlname='billingFrequency']"));
			      ctrt_items_billing.click();
			      Thread.sleep(1000);
			      List<WebElement> ctrt_items_billing_options = ctrt_items_billing.findElements(By.cssSelector("ul > li"));
			      for (WebElement li : ctrt_items_billing_options) 
			      {
			    	WebElement span = li.findElement(By.tagName("span"));
			    	if (span.getText().trim().contains(s.getRow(i).getCell(8).getStringCellValue())) 
			     	{
			     	  li.click();
			     	  break;
			     	}
			      }
			      Thread.sleep(2000);
			         
			     /* Save the item entry *
				  d.findElement(By.xpath("//span[@class='ui-button-icon-left ui-clickable fa fa-save']")).click();
				  Thread.sleep(4000);
				     
				   /*Getting Contract ID information*
				   WebElement ctrt_info_id = d.findElement(By.cssSelector("contract > form > div.layout-content-header > div:nth-child(1) > h1"));
				   String ctrt_info = ctrt_info_id.getText();
				   String ctrt_inform[] = ctrt_info.split("#");
				   String ctrt_inform_id=ctrt_inform[1];
				   System.out.println("Contract ID: "+ctrt_inform_id);
				
				   /*Check Activate & Delete buttons exists after saving the entry*
				     if(d.findElement(By.xpath("//span[contains(.,'Activate')]")).isDisplayed())
				     { System.out.println("Activate Button exists");
				     }
				     else
				     { System.out.println("Activate Button does not exists");
				     }
				     Thread.sleep(1000); 
				     if(d.findElement(By.xpath("//span[contains(.,'Delete')]")).isDisplayed())
				     { System.out.println("Delete Button exists");
				     }
				     else
				     { System.out.println("Delete Button does not exists");
				     }
				     Thread.sleep(2000);
				     
			/* Activate the Contract *
				     d.findElement(By.xpath("//span[contains(.,'Activate')]")).click();         
				     WebElement ctrt_act_temp = d.findElement(By.cssSelector("tek-dropdown[controlname='contractTemplateId']"));
				     ctrt_act_temp.click();
				     List<WebElement> ctrt_act_temp_options = ctrt_act_temp.findElements(By.cssSelector("ul > li"));
				     for (WebElement li : ctrt_act_temp_options) 
				     {
				   	 WebElement span = li.findElement(By.tagName("span"));
				   	 if (span.getText().trim().equals(s.getRow(i).getCell(9).getStringCellValue())) 
				    	{
				    	  li.click();
				    	  break;
				    	}
				     }
				     
				    Thread.sleep(2000);
				     d.findElement(By.xpath("//span[contains(.,'Confirm')]")).click(); 
				     Thread.sleep(2000);
				     		     
				     /*Contract Status changed from 'Draft' to 'Active' *
				     String statusctrt=d.findElement(By.xpath("//div//i[@class='fa fa-file-signature']")).getText();
				     if(statusctrt.contains("Active"))
				     {System.out.println("Status of Contract changed from Draft to Active");
				     }else
				     {System.out.println("Status ofcontract displayed"+statusctrt);
				     }
				     Thread.sleep(1000);
			
		    /*Upload Contract & Terminate Buttons and Non-Renewal checkbox fields verification*
		     if(d.findElement(By.xpath("//label[contains(.,'Upload Contract')]")).isDisplayed())
		      {
			    System.out.println("Upload Contract Button Displays");
			  }
			  else
			  {
				System.out.println("Upload Contract Button do not displays");
			  } 
			 if(d.findElement(By.xpath("//button[@label='Terminate']")).isDisplayed())
			 {
	     	  System.out.println("Terminate Button Displays");
			 }
			 else
		     {
				 System.out.println("Terminate Button do not displays");
		     } 
					 
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
		     
		     /*Perform Upload Contract*
		     //d.findElement(By.xpath("//label[contains(.,'Upload Contract')]")).click();
		     WebElement ctrt_sign_upload_file = d.findElement(By.xpath("//input[@type='file']"));
		     ctrt_sign_upload_file.sendKeys(s.getRow(i).getCell(10).getStringCellValue()); // Provide the upload file location
		     Thread.sleep(2000);
		     
		     /*Take Screenshot to check for the toaster message either success or failure*
		      File src =((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		      //FileUtils.copyFile(src, new File("E:\\SHM\\SHM Scripts\\UploadContract.png")); 
		      
		      /*Check for the success toast message*
		      String toast_title = d.findElement(By.className("ui-toast-summary")).getText();
		        System.out.println(toast_title);
		        Assert.assertTrue(toast_title.equals("Contract successfully uploaded"), "Data Mismatch");

		        /*Contract Document link verification*
			     if(d.findElement(By.linkText("Contract Document")).isDisplayed())
			      {
				    System.out.println("Contract Document link Displays");
				  }
				  else
				  {
					System.out.println("Contract Document link do not displays");
				  } 
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
			     
			     /*Now navigate to Contracts tab and verify the same contract exists with Contract Document link *
			     d.findElement(By.linkText("Contracts")).click();
			     
			     /*Click on Filters link *
			       d.findElement(By.xpath("//span[contains(.,'Filters...')]")).click();		       
			       List<WebElement> ctrt_filters = d.findElements(By.cssSelector("p-checkbox > label"));		       
			       for(int k=0;k<ctrt_filters.size();k++){
			       if (ctrt_filters.get(k).getText().trim().equals("Id")) 
			   	   {
			    	   ctrt_filters.get(k).click();
			   	   break;
			   	   }
			       }
			       d.findElement(By.cssSelector("span.ui-button-icon-left.ui-clickable.fa.fa-times-circle")).click();
			       WebElement contract_table = d.findElement(By.className("ui-table-thead"));       
			       contract_table.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(8) > input")).clear();  
			       contract_table.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(8) > input")).sendKeys(ctrt_inform_id);
			       Thread.sleep(3000);		       
			       WebElement contract_tab = d.findElement(By.className("ui-table-tbody"));       
			       contract_tab.findElement(By.tagName("td")).click();
			       Thread.sleep(2000);
			       
			       /*Contract Document link verification*
				     if(d.findElement(By.linkText("Contract Document")).isDisplayed())
				      {
					    System.out.println("Contract Document link Displays");
					  }
					  else
					  {
						System.out.println("Contract Document link do not displays");
					  } 
				     
				     /*Navigate to Customer page*
				     d.findElement(By.linkText("Customers")).click();*/
	}  
		      /* Logging Out *
		      d.findElement(By.xpath("//span[contains(@class,'topbar-item-name profile-name')]")).click();  
		      d.findElement(By.xpath("//span[contains(.,'Logout')]")).click();*/
		     
		

	@AfterTest

	public void tearDown()
	{
	 d.quit();

	}

	}

		



}
