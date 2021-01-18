package Testcases;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OutlookEmailVerification {
	
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
		 String parent = d.getWindowHandle();
		FileInputStream fs = new FileInputStream("D:\\SHM\\Template\\ContractCreationEmailVerification.xlsx"); // Need to add your test data sheet location
	 	XSSFWorkbook wb = new XSSFWorkbook(fs);		
	 	XSSFSheet s=wb.getSheetAt(0);	
	 		
	 	for( int i=1; i<s.getLastRowNum()+1; i++)			
	 	{  
		d.get("http://email.tekzenit.com");
		//d.get("https://login.live.com/");
		Thread.sleep(4000);
		/*d.findElement(By.xpath("//a[@data-task='signin'][1]")).click();
		Thread.sleep(2000);*/
	    d.findElement(By.name("loginfmt")).sendKeys("nikhila.anand@tekzenit.com");
	    d.findElement(By.id("idSIButton9")).click();
	    Thread.sleep(2000);
	    d.findElement(By.name("passwd")).sendKeys("T3kP@ss67%*90");
	    d.findElement(By.id("idSIButton9")).click();
	    Thread.sleep(2000);
	    if(d.findElement(By.xpath("//div[@class='row text-title']")).isDisplayed())
	    	{ d.findElement(By.xpath("//input[@value='Yes']")).click();
	    		} else
	    		{ System.out.println("Logged into Outlook");
	    				}
		Thread.sleep(10000);
		
		/*d.findElement(By.xpath("//div//input[@aria-label=\"Search\"]")).sendKeys("noreply@membermail.shmarinas.com");*/
		d.findElement(By.xpath("//span[contains(.,'Inbox')]")).click();
		//d.findElement(By.xpath("//div//input[@aria-label=\"Search\"]")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		//d.findElement(By.xpath("//div[@title='Safe Harbor Marinas: You Have Documents Waiting to be Signed']")).click();
		Thread.sleep(6000);
		d.findElement(By.xpath("(//a[@target='_blank'])[2]")).click();
		Thread.sleep(4000);
		d.switchTo().window(parent);
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
	      //d.findElement(By.xpath("//span[contains(.,'Read And Sign')]")).click();
	      //Thread.sleep(3000);
	      
	      	 //String parent = d.getWindowHandle();
		     System.out.println("Parent Window ID : "+parent);
		     d.findElement(By.xpath("//span[contains(.,'Read And Sign')]")).click(); 
		     /*
		     Set<String> allWindows = d.getWindowHandles();
		     int count = allWindows.size();
		     System.out.println("Tab Count : "+count);
		     for(String child:allWindows)
		     {
		    	if(!parent.equalsIgnoreCase(child)) 
		    	{
		    		d.switchTo().window(child);
		    		Thread.sleep(2000);
		    		String MemberSign_page = d.getTitle();
		    		System.out.println("Member ID Signature Page navigated to: "+MemberSign_page);
		    				//d.close();
		    	}
		     }*/
		     String MemberSign_page = d.getTitle();
	    	 System.out.println("Member ID Signature Page navigated to: "+MemberSign_page);
		     Thread.sleep(5000);
		     //d.findElement(By.xpath("//input[@type='checkbox']")).click();
		     //Thread.sleep(2000);
		     d.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
		     Thread.sleep(2000);
		     d.findElement(By.xpath("//button//span[contains(.,'Start')]")).click();
		     Thread.sleep(2000);
		     d.findElement(By.xpath("//button//span[contains(.,'Sign Here')]")).click();
		     Thread.sleep(2000);
		     d.findElement(By.xpath("//button[contains(.,'Adopt and Sign')]")).click();
		     Thread.sleep(2000);
		     
		     boolean btnView=d.findElement(By.xpath("//span[contains(.,'View Agreement')]")).isDisplayed();
		     if(btnView==true)
		     { System.out.println("DocuSign template signed by Member");
		         } else
		     { System.out.println("DocuSign Template not signed");
		         }
		     Thread.sleep(2000);
		     /*Logout from Member Portal*/
		     d.findElement(By.cssSelector("app-header > div > div > div:nth-child(7) > div")).click();
		     Thread.sleep(3000);
		     
		     /*d.get("http://email.tekzenit.com");
				//d.get("https://login.live.com/");
				Thread.sleep(4000);
				/*d.findElement(By.xpath("//a[@data-task='signin'][1]")).click();
				Thread.sleep(2000);
			    d.findElement(By.name("loginfmt")).sendKeys("nikhila.anand@tekzenit.com");
			    d.findElement(By.id("idSIButton9")).click();
			    Thread.sleep(2000);
			    d.findElement(By.name("passwd")).sendKeys("T3kP@ss67%*90");
			    d.findElement(By.id("idSIButton9")).click();
			    Thread.sleep(2000);
			    if(d.findElement(By.xpath("//div[@class='row text-title']")).isDisplayed())
			    	{ d.findElement(By.xpath("//input[@value='Yes']")).click();
			    		} else
			    		{ System.out.println("Logged into Outlook");
			    				}
				//Thread.sleep(10000);
				
				/*d.findElement(By.xpath("//div//input[@aria-label=\"Search\"]")).sendKeys("noreply@membermail.shmarinas.com");
				d.findElement(By.xpath("//span[contains(.,'Inbox')]")).click();
				//d.findElement(By.xpath("//div//input[@aria-label=\"Search\"]")).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				d.findElement(By.xpath("//div//span[contains(.,'Vijay Tummala')]")).click();
				Thread.sleep(6000);
				d.findElement(By.xpath("(//a[@target='_blank'])[2]")).click();
				Thread.sleep(4000);
	      */
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
