import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class ReportTests extends myCommon{
	
	public String FilePath;
	public String CurrentDate;
	
	ReportTests(WebDriver mydriver,String myAuthToken)  {
		
		 driver=mydriver;
		 AuthToken=myAuthToken;
		 FilePath="/Users/arsaljalib/eclipse-workspace/MyVanidayTestApplication/Files";
		
		 CurrentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		 ResetClients(AuthToken);
			driver.manage().window().maximize();

	 // ExecuteAllTests(driver, ReportTests.class);

		 //ExecuteSpecificTest("Clients_TestCase_7",ReportTests.class);
		  ExecuteSimilarTests("Reports_CustomerRetention_TestCase_5",ReportTests.class,driver);
	  PrintTestResults();
	}
	public void Reports_AppointmentsSummary_TestCase_1() // ALL STAFF, Today
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Today");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

		 
			
			
			VerifyAppointmentSummary(listItems,"",CurrentDate,CurrentDate);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_2() // ALL STAFF, Yesterday
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Yesterday");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -1);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

			
			VerifyAppointmentSummary(listItems,"",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	
	public void Reports_AppointmentsSummary_TestCase_3() // ALL STAFF, Last 7 Days
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Last 7 days");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -7);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.add(Calendar.DATE, -1);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_4() // ALL STAFF, Last 30 Days
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Last 30 days");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -30);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.add(Calendar.DATE, -1);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_5() // ALL STAFF, This month
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("This month");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH,1);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.set(Calendar.DAY_OF_MONTH, 30);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_6() // ALL STAFF, Tomorrow
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Tomorrow");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 1);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.add(Calendar.DATE, 1);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_7() // ALL STAFF, Next 30 days
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Next 30 days");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
		 
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.add(Calendar.DATE, 30);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_8() // ALL STAFF, Next 7 days
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Next 7 days");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			 
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.add(Calendar.DATE, 7);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_9() // ALL STAFF, Next month
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Next month");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH,1);
			cal.add(Calendar.MONTH,1);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.set(Calendar.DAY_OF_MONTH, 30);
			cal2.add(Calendar.MONTH,1);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	
	public void Reports_AppointmentsSummary_TestCase_10() // Particular Staff, Today
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			new Select(driver.findElement(By.id("reportStaffFilter"))).selectByVisibleText("Camila");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Today");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

		 
			
			
			VerifyAppointmentSummary(listItems,"Camila",CurrentDate,CurrentDate);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_11() // Particular Staff, Yesterday
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			new Select(driver.findElement(By.id("reportStaffFilter"))).selectByVisibleText("Camila");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Yesterday");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -1);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

			
			VerifyAppointmentSummary(listItems,"Camila",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	
	public void Reports_AppointmentsSummary_TestCase_12() // Particular Staff, Last 7 Days
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			new Select(driver.findElement(By.id("reportStaffFilter"))).selectByVisibleText("Camila");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Last 7 Days");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -7);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.add(Calendar.DATE, -1);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"Camila",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_13() // Particular Staff, Last 30 Days
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			new Select(driver.findElement(By.id("reportStaffFilter"))).selectByVisibleText("Camila");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Last 30 Days");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -30);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.add(Calendar.DATE, -1);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"Camila",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_14() // Particular Staff, This month
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			new Select(driver.findElement(By.id("reportStaffFilter"))).selectByVisibleText("Camila");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("This month");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH,1);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.set(Calendar.DAY_OF_MONTH, 30);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"Camila",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_15() // Particular Staff, Tomorrow
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			new Select(driver.findElement(By.id("reportStaffFilter"))).selectByVisibleText("Camila");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Tomorrow");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 1);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.add(Calendar.DATE, 1);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"Camila",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_16() // Particular Staff, Next 30 days
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			new Select(driver.findElement(By.id("reportStaffFilter"))).selectByVisibleText("Camila");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Next 30 days");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
		 
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.add(Calendar.DATE, 30);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"Camila",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_17() // Particular Staff, Next 7 days
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			new Select(driver.findElement(By.id("reportStaffFilter"))).selectByVisibleText("Camila");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Next 7 days");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			 
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.add(Calendar.DATE, 7);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"Camila",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_18() // Particular Staff, Next month
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			new Select(driver.findElement(By.id("reportStaffFilter"))).selectByVisibleText("Camila");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Next month");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH,1);
			cal.add(Calendar.MONTH,1);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.set(Calendar.DAY_OF_MONTH, 30);
			cal2.add(Calendar.MONTH,1);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"Camila",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_19() // ALL STAFF, Custom Range
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH,1);
			cal.add(Calendar.MONTH,1);
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.set(Calendar.DAY_OF_MONTH, 30);
			cal2.add(Calendar.MONTH,1);
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			Sleep(5);

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Custom range");
			driver.findElement(By.id("startPeriod")).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
			driver.findElement(By.id("endPeriod")).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

		 
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
		 
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_20() // Particular staff, Custom Range
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH,1);
			cal.add(Calendar.MONTH,1);
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.set(Calendar.DAY_OF_MONTH, 30);
			cal2.add(Calendar.MONTH,1);
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			new Select(driver.findElement(By.id("reportStaffFilter"))).selectByVisibleText("Camila");
			Sleep(5);
			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Custom range");

			driver.findElement(By.id("startPeriod")).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
			driver.findElement(By.id("endPeriod")).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));

			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

			
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyAppointmentSummary(listItems,"Camila",Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_21() // ALL STAFF, All Dates
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Today");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

		 
			
			
			VerifyAppointmentSummary(listItems,"","2000-01-11",CurrentDate);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_AppointmentsSummary_TestCase_22() // Particular Staff, All Dates
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Appointments Summary");
			new Select(driver.findElement(By.id("reportStaffFilter"))).selectByVisibleText("Camila");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Today");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(4) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr"));

		 
			
			
			VerifyAppointmentSummary(listItems,"Camila","2000-01-11",CurrentDate);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_CustomerRetention_TestCase_1() //   Today
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Customer Retention");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Today");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(3) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope > div > div"));

		 
			
			
			VerifyRetentionSummary(listItems,CurrentDate,CurrentDate);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_CustomerRetention_TestCase_2() //   Yesterday
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Customer Retention");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Yesterday");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(3) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope > div > div"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -1);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			
			VerifyRetentionSummary(listItems,Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	
	public void Reports_CustomerRetention_TestCase_3() //   Last 7 days
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Customer Retention");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Last 7 days");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(3) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope > div > div"));

		 
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -7);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
 			
			VerifyRetentionSummary(listItems,Starting,CurrentDate);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_CustomerRetention_TestCase_4() //   This month
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Customer Retention");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("This month");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(3) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope > div > div"));

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH,1);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.set(Calendar.DAY_OF_MONTH, 30);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());
			
			
			VerifyRetentionSummary(listItems,Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_CustomerRetention_TestCase_5() //   Last 30 days
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Customer Retention");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("Last 30 days");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(3) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope > div > div"));

		 
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -30);
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.add(Calendar.DATE, -1);
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());
			
			VerifyRetentionSummary(listItems,Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_CustomerRetention_TestCase_6() //   Custom range
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH,1);
			cal.add(Calendar.MONTH,1);
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.set(Calendar.DAY_OF_MONTH, 30);
			cal2.add(Calendar.MONTH,1);
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Customer Retention");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText(" Custom range");
			driver.findElement(By.id("startPeriod")).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
			driver.findElement(By.id("endPeriod")).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(3) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope > div > div"));

		 
			String Starting = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			
			String Ending = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());

			
			VerifyRetentionSummary(listItems,Starting,Ending);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Reports_CustomerRetention_TestCase_7() //   All dates
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(6) > a > i")).click();
			
			Sleep(5);
			
			new Select(driver.findElement(By.id("reportTypeFilter"))).selectByVisibleText("Customer Retention");

			new Select(driver.findElement(By.id("reportPeriodFilter"))).selectByVisibleText("All dates");

			driver.findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-filters > form > div > div > div > div:nth-child(3) > vm-button-loading > button > span")).click();
			Sleep(5);
			
			
			List<WebElement> listItems = driver.findElements(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope > div > div"));

		 
			
			
			VerifyRetentionSummary(listItems,"2000-10-10",CurrentDate);
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	// Common methods for this test
	public void VerifyAppointmentSummary(List<WebElement> listItems,String Staff,String Start,String End)
	{
		try {
 
		for(int i=0; i<listItems.size();i++)
		{
			String StaffName= listItems.get(i).findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(1)")).getText();
			String TotalAppointments= listItems.get(i).findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(2)")).getText();
			String TotalSales= listItems.get(i).findElement(By.cssSelector("body > ui-view > ui-view > div > div > div > div > div > div > div.tab-content > div.reports-result-section.ng-scope > vm-appointments-summary-report > div:nth-child(1) > table > tbody > tr:nth-child("+(i+1)+") > td:nth-child(3)")).getText().replace(".","").replace("$","").replace(",","");

			String Query ="https://my-api-staging.vaniday.com/calendar/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/?from="+Start +"&to="+End;
			 
				 
				String result = ExecuteQuery(Query);

				// System.out.println(result);
				JSONObject jobj = new JSONObject(result);
				JSONArray JData;
				JData = jobj.getJSONArray("events");
				
				JSONArray Orders = new JSONArray();
				
				int sales=0;
				int numOfApps=0;
				
				List<String> apps = new ArrayList<String>();
				List<String> books = new ArrayList<String>();
				
				for(int j=0; j< JData.length();j++)
				{
					if(JData.getJSONObject(j).getString("type").contains("appointment"))
					{
						System.out.println(JData.getJSONObject(j));
						if(StaffName.contains(FindProfessionalByID(JData.getJSONObject(j).getString("professionalId")).getString("firstName"))==true)
							{
								
								apps.add(JData.getJSONObject(j).getJSONObject("appointment").getString("myOrderId"));
								numOfApps++;
								
							}
					//	sales= sales// +  //FindSalesByAppointment();
					}
					if(JData.getJSONObject(j).getString("type").contains("booking") || JData.getJSONObject(j).getString("type").contains("widget"))
					{						
						if( JData.getJSONObject(j).getJSONObject("booking").getString("professionalName").contains(StaffName)==true)
							 {
								if(JData.getJSONObject(j).getJSONObject("booking").getString("status").contains("done_by_professional"))
								{
									books.add(JData.getJSONObject(j).getJSONObject("booking").getString("orderId"));
									numOfApps++;
								}
								if(JData.getJSONObject(j).getJSONObject("booking").getString("status").contains("no_show_by_professional") && JData.getJSONObject(j).getJSONObject("booking").getString("paymentType").contains("credit-card"))
								{
									books.add(JData.getJSONObject(j).getJSONObject("booking").getString("orderId"));
									numOfApps++;
								}
							 }

					}
					 
				}
				
				Set<String> hs = new HashSet<>();
				hs.addAll(apps);
				apps.clear();
				apps.addAll(hs);
				
				Set<String> hs2 = new HashSet<>();
				hs2.addAll(books);
				books.clear();
				books.addAll(hs2);
				
				for(String appointmentID : apps)
				{
					sales= sales +FindSalesByAppointment(appointmentID,"my-orders",StaffName);
				}
				for(String bookingID : books)
				{
					sales= sales +FindSalesByAppointment(bookingID,"orders",StaffName);
				}
				

				if(sales!=Integer.parseInt( TotalSales))
					throw new Exception("The Sales numbers do not match");
					
					
				System.out.println(sales);	 
  
			
		}
		}
		catch(Exception e)
		{
			System.out.println("The program threw an Exception in Verify Appointment Summary due to: "+e.getMessage());
		}
	}
	public int FindSalesByAppointment(String ID, String type, String StaffName)
	{
		try {
			
			int returnVal=0;
		String Query ="https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/"+type+"/"+ID;
		 
		 
		String result = ExecuteQuery(Query);
		
		JSONObject jobj = new JSONObject(result);
		JSONArray JData;
		JData = jobj.getJSONArray("items");
		for(int j=0; j< JData.length();j++)
		{
			if(JData.getJSONObject(j).getString("professionalName").contains(StaffName)==true)
				{
					returnVal = returnVal + JData.getJSONObject(j).getInt("price") ;
				}
		}

		// System.out.println(result);
		return returnVal;
		}
		catch(Exception e)
		{
			System.out.println("The program threw an Exception in Find Sales By Appointment due to: "+e.getMessage());
		}
		return 0;
	}
	public void VerifyRetentionSummary(List<WebElement> listItems,String Start,String End) throws Exception
	{
		try {
 
		for(int i=0; i<listItems.size();i++)
		{
			String CustomerName= listItems.get(i).findElement(By.className("ui-grid-coluiGrid-0005")).getText();
			String PhoneNumber= listItems.get(i).findElement(By.className("ui-grid-coluiGrid-0006")).getText();
			String LastAppointment= listItems.get(i).findElement(By.className("ui-grid-coluiGrid-0007")).getText();
			String TotalSales= listItems.get(i).findElement(By.className("ui-grid-coluiGrid-000B")).getText().replace(".","").replace("$","").replace(",","");

			String Query ="https://my-api-staging.vaniday.com/calendar/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/?from="+Start +"&to="+End;
			 
				 
				String result = ExecuteQuery(Query);

				// System.out.println(result);
				JSONObject jobj = new JSONObject(result);
				JSONArray JData;
				JData = jobj.getJSONArray("events");
				
				JSONArray Orders = new JSONArray();
				
				int sales=0;
				int numOfApps=0;
				
				List<String> apps = new ArrayList<String>();
				List<String> books = new ArrayList<String>();
				
				for(int j=0; j< JData.length();j++)
				{
					if(JData.getJSONObject(j).getString("type").contains("appointment"))
					{
						
						if(JData.getJSONObject(j).getJSONObject("appointment").getString("customerName").contains(CustomerName)==true && JData.getJSONObject(j).getJSONObject("appointment").getString("customerPhoneNumber").contains(PhoneNumber)==true)
							{
								System.out.println(JData.getJSONObject(j));
								apps.add(JData.getJSONObject(j).getJSONObject("appointment").getString("myOrderId"));
								numOfApps++;
								
							}
					//	sales= sales// +  //FindSalesByAppointment();
					}
					if(JData.getJSONObject(j).getString("type").contains("booking") || JData.getJSONObject(j).getString("type").contains("widget"))
					{			
						

						if( JData.getJSONObject(j).getJSONObject("booking").getString("customerName").contains(CustomerName)==true && JData.getJSONObject(j).getJSONObject("booking").getString("customerPhoneNumber").contains(PhoneNumber)==true)
							 {System.out.println(JData.getJSONObject(j));
								if(JData.getJSONObject(j).getJSONObject("booking").getString("status").contains("done_by_professional"))
								{
									books.add(JData.getJSONObject(j).getJSONObject("booking").getString("orderId"));
									numOfApps++;
								}
								if(JData.getJSONObject(j).getJSONObject("booking").getString("status").contains("confirmed_by_professional") && JData.getJSONObject(j).getJSONObject("booking").getString("paymentType").contains("credit-card"))
								{
									books.add(JData.getJSONObject(j).getJSONObject("booking").getString("orderId"));
									numOfApps++;
								}
								if(JData.getJSONObject(j).getJSONObject("booking").getString("status").contains("no_show_by_professional") && JData.getJSONObject(j).getJSONObject("booking").getString("paymentType").contains("credit-card"))
								{
									books.add(JData.getJSONObject(j).getJSONObject("booking").getString("orderId"));
									numOfApps++;
								}
							 }

					}
					 
				}
				
				Set<String> hs = new HashSet<>();
				hs.addAll(apps);
				apps.clear();
				apps.addAll(hs);
				
				Set<String> hs2 = new HashSet<>();
				hs2.addAll(books);
				books.clear();
				books.addAll(hs2);
				
				for(String appointmentID : apps)
				{
					sales= sales +FindSalesByAppointment(appointmentID,"my-orders","");
				}
				for(String bookingID : books)
				{
					sales= sales +FindSalesByAppointment(bookingID,"orders","");
				}
				
				if(sales!=Integer.parseInt( TotalSales))
					throw new Exception("The Sales numbers do not match");
					
					
				System.out.println(sales);	 
  
			
		}
		}
		catch(Exception e)
		{
			System.out.println("The test threw an exception in Verify Retention Summary due to "+e.getMessage());
		 
			throw e;
			
		}
	}
	public void VerifySalesSummary()
	{
		
	}
}