import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class Execution {

	public static void main(String[] args) throws InterruptedException, IOException, JSONException {
		// TODO Auto-generated method stub
		try {
			
		
		WebDriver driver = GetDriver();
		String AuthToken;
		AuthToken = myCommon.GetAuthenticationToken(((JavascriptExecutor) driver));

		 if(args[0].contains("InventoryTests"))
		 { 
			 InventoryTests tcInventoryTests;
		 	  tcInventoryTests= new InventoryTests(driver,AuthToken);
		 }
		 if(args[0].contains("CalenderTests"))
		 { 
		 	CalenderTests tcCalenderTests;
		 	tcCalenderTests= new CalenderTests(driver,AuthToken);
	 
		 }
		 if(args[0].contains("ClientTests"))
		 { 
			 ClientTests tcClientTests;
			 tcClientTests= new ClientTests(driver,AuthToken);
	 
		 }
		 if(args[0].contains("SaloonTests"))
		 { 
			 SaloonTests tcSaloonTests;
			 tcSaloonTests= new SaloonTests(driver,AuthToken);
	 
		 }
		 if(args[0].contains("NotificationTests"))
		 { 
			 NotificationTests tcNotificationTests;
			 tcNotificationTests= new NotificationTests(driver,AuthToken);
	 
		 }
		 if(args[0].contains("ReportTests"))
		 { 
			 ReportTests tcReportTests;
			 tcReportTests= new ReportTests(driver,AuthToken);
	 
		 }
		 else
		 {
			 NotificationTests tcNotificationTests;
			 tcNotificationTests= new NotificationTests(driver,AuthToken);
		 }
	 	
		} catch (Exception e) {
			 
			System.out.println("The program threw an Exception in the Main Method due to: "+e.getMessage());
		}

	}

	public static WebDriver GetDriver() throws InterruptedException {
	
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");	
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

		
		WebDriver driver;

		driver = new ChromeDriver(caps);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

		
		if(myCommon.TestingEnvironment.contains("staging"))
		{
			driver.get("https://rocket:rock4me@my-"+myCommon.TestingEnvironment+".vaniday.com.au/");
			driver.get("https://my-"+myCommon.TestingEnvironment+".vaniday.com.au/");
		}
		else {
			driver.get("http://rocket:rock4me@my-"+myCommon.TestingEnvironment+".vaniday.com.au/");
			driver.get("http://my-"+myCommon.TestingEnvironment+".vaniday.com.au/");
	
		}
		

		
		Thread.sleep(5000);
		

		driver.findElement(By.name("email")).sendKeys("my@vaniday.com");
		driver.findElement(By.name("password")).sendKeys("vaniday");

		// driver.findElement(By.name("login")).click();

		driver.findElement(By.className("btn-sm")).click();

		Thread.sleep(8000);
		
		return driver;
	
	}

}
