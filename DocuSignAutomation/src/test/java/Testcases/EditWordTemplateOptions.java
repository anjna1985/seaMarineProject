package Testcases;

	import java.io.File;
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
    import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;


	public class EditWordTemplateOptions {

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

				/*WebElement scrollPanelElement = d.findElement(By.xpath("//p-scrollpanel//div[contains(@class,'bar-y')]"));
				WebDriverWait wait = new WebDriverWait(d, 120);
				wait.until(ExpectedConditions.visibilityOf(scrollPanelElement));
				boolean Scroll_Bar=scrollPanelElement.isEnabled();*/

				d.findElement(By.linkText("System")).click();
				Thread.sleep(2000);
				d.findElement(By.linkText("Contract Templates")).click();
				Thread.sleep(2000);
				
				Cell cell = s.getRow(i).getCell(0);
			    String temp_name = formatter.formatCellValue(cell);		
				WebElement template_table = d.findElement(By.className("ui-table-thead"));
				template_table.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(1) > input")).clear();
				template_table.findElement(By.cssSelector("tr:nth-child(2) > th:nth-child(1) > input")).sendKeys(temp_name);
				Thread.sleep(2000);		 
				WebElement template_table_list = d.findElement(By.className("ui-table-tbody"));       
				template_table_list.findElement(By.tagName("td")).click();
			    Thread.sleep(3000);
				
			    /*Edit the Contract Template*/
				d.findElement(By.xpath("//span[contains(.,'Edit')]")).click(); 
				Thread.sleep(2000);
				d.findElement(By.xpath("//span[contains(.,'Cancel')]")).click(); 
				Thread.sleep(2000);
				boolean editBtn=d.findElement(By.xpath("//span[contains(.,'Edit')]")).isDisplayed();
				if(editBtn==true)
				{ System.out.println("Cancel not allowing to Edit the Contract Template");
				} else
				{ System.out.println("Cancel allowing to edit the Contract template");
				}
				Thread.sleep(3000);
				d.findElement(By.xpath("//span[contains(.,'Edit')]")).click(); 
				Thread.sleep(3000);
				d.findElement(By.cssSelector("tek-input[controlname='name'] input")).clear();
				Thread.sleep(2000);
				WebElement template_name =  d.findElement(By.cssSelector("tek-input[controlname='name']"));
				template_name.findElement(By.tagName("input")).sendKeys(s.getRow(i).getCell(1).getStringCellValue());
				Thread.sleep(2000);
				/*WebElement marina_multi = d.findElement(By.xpath("//div[@class='multi-select-contract-template']//span"));
				marina_multi.click();*/
				WebElement marina_multi = d.findElement(By.cssSelector("p-multiselect[formcontrolname='selectedMarinas']"));
				marina_multi.click();
				Thread.sleep(2000);
				List<WebElement> marina_multi_options = marina_multi.findElements(By.cssSelector("ul > li"));
				System.out.println(marina_multi_options.size());
				/*for (WebElement li : marina_multi_options) {
					WebElement span = li.findElement(By.tagName("span"));
					if (span.getText().trim().equals(s.getRow(i).getCell(3).getStringCellValue())) {
						li.click();
						break;
					}
				}*/
				Thread.sleep(3000);
				/*WebElement chkboxMulti=d.findElement(By.xpath("//input[@role='textbox']"));
				chkboxMulti.sendKeys(s.getRow(i).getCell(3).getStringCellValue());
				Thread.sleep(3000);*/
				d.findElement(By.xpath("(//div//span[@class='ui-chkbox-icon ui-clickable'])[3]")).click();
				Thread.sleep(2000);
				d.findElement(By.xpath("//span[contains(.,'Save')]")).click(); 
				Thread.sleep(2000);
				String toast_title = d.findElement(By.className("ui-toast-summary")).getText();
		        System.out.println(toast_title);
		        Assert.assertTrue(toast_title.equals("Successfully saved"), "Data Mismatch");

				/*Verify the Edited field values*/
				String updated_temp_name=d.findElement(By.cssSelector("tek-input[controlname='name'] input")).getText();
				System.out.println("Temp"+template_name);
				System.out.println("temp"+updated_temp_name);
				if(template_name.equals(updated_temp_name))
				{ System.out.println("Contract Template name updated");
				}else
				{
					System.out.println("Contract Template name not updated");
				}
				Thread.sleep(2000);
				d.findElement(By.xpath("//a[@class='dowload-link']")).click();
				Thread.sleep(2000);
				File getLatestFile = getLatestFilefromDir(downloadPath);
			    String fileName = getLatestFile.getName();
			    System.out.println("Filename: "+fileName);
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
		  
			    public boolean isFileDownloaded(String downloadPath, String fileName) {
			  		 boolean flag = false;
			  		    File dir = new File(downloadPath);
			  		    File[] dir_contents = dir.listFiles();

			  		    for (int i = 0; i < dir_contents.length; i++) {
			  		        if (dir_contents[i].getName().equals(fileName))
			  		            return flag=true;
			  		            }

			  		    return flag;
			  		}

			  		private boolean isFileDownloaded_Ext(String dirPath, String ext){
			  		 boolean flag=false;
			  		    File dir = new File(dirPath);
			  		    File[] files = dir.listFiles();
			  		    if (files == null || files.length == 0) {
			  		        flag = false;
			  		    }

			  		    for (int i = 1; i < files.length; i++) {
			  		     if(files[i].getName().contains(ext)) {
			  		      flag=true;
			  		     }
			  		    }
			  		    return flag;
			  		}

			  		private File getLatestFilefromDir(String dirPath){
			  		    File dir = new File(dirPath);
			  		    File[] files = dir.listFiles();
			  		    if (files == null || files.length == 0) {
			  		        return null;
			  		    }

			  		    File lastModifiedFile = files[0];
			  		    for (int i = 1; i < files.length; i++) {
			  		       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
			  		           lastModifiedFile = files[i];
			  		       }
			  		    }
			  		    return lastModifiedFile;
			  		}
			  }
