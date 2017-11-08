import java.security.SecureRandom;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import jdk.jfr.Timespan;

public class ClientTests extends myCommon{
	
	public String FilePath;
	
	ClientTests(WebDriver mydriver,String myAuthToken)  {
		
		 driver=mydriver;
		 AuthToken=myAuthToken;
		 FilePath="/Users/arsaljalib/eclipse-workspace/MyVanidayTestApplication/Files";
		 

		 ResetClients(AuthToken);
			driver.manage().window().maximize();

	 // ExecuteAllTests(driver, ClientTests.class);

		 //ExecuteSpecificTest("Clients_TestCase_7",ClientTests.class);
		 ExecuteSimilarTests("Clients_TestCase_7",ClientTests.class,driver);
	  PrintTestResults();
	}
	
	public void Clients_TestCase_1() //Create a new client without specifying it's last name
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(15);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
	 
			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();

			if(FindClient(ClientName)!=null)
				throw new Exception("The Client was created where it should not have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Clients_TestCase_2() //Create a new client without specifying it's first name
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("lastName")).sendKeys(ClientName);
	 
			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();

			if(FindClient(ClientName)!=null)
				throw new Exception("The Client was created where it should not have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Clients_TestCase_3() //Create a new Client with just the required fields
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First client");

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			Sleep(4);
			if(FindClient(ClientName)==null)
				throw new Exception("The Client was not created where it should have");
			if(FindClient(ClientName).getString("lastName").contains("First client")!=true)
				throw new Exception("The Client last name was not set");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Clients_TestCase_4() //Create a new Client with all possible options
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First client");
			driver.findElement(By.name("email")).sendKeys("my@vaniday.com");
			driver.findElement(By.name("phone")).sendKeys("333845742");
			driver.findElement(By.name("day")).sendKeys("28");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(4) > div:nth-child(3) > div > div > a")).click();
			driver.findElement(By.name("year")).sendKeys("1993");
			driver.findElement(By.id("note")).sendKeys("Create a new Client with all possible options");
			new Select(driver.findElement(By.name("month"))).selectByVisibleText("September");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > div > div:nth-child(1) > label")).click();
			
			Sleep(3);

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			
			Sleep(4);
			
			if(FindClient(ClientName)==null)
				throw new Exception("The Client was not created where it should have");
			if(FindClient(ClientName).getString("lastName").contains("First client")!=true)
				throw new Exception("The Client last name was not set");
			if(FindClient(ClientName).getString("email").contains("my@vaniday.com")!=true)
				throw new Exception("The Client email was not set");
			if(FindClient(ClientName).getString("phoneNumber").contains("333845742")!=true)
				throw new Exception("The Client phone was not set");
			if(FindClient(ClientName).getString("birthday").contains("1993-09-28")!=true)
				throw new Exception("The Client birthday was not set");
			if(FindClient(ClientName).getString("note").contains("Create a new Client with all possible options")!=true)
				throw new Exception("The Client note was not set");
			if(FindClient(ClientName).getString("gender").contains("male")!=true)
				throw new Exception("The Client year was not set");
			 
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Clients_TestCase_5() //Create a new Client where a client with the same name already exists
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First client");
			driver.findElement(By.name("email")).sendKeys("my@vaniday.com");
			driver.findElement(By.name("phone")).sendKeys("333845742");
			driver.findElement(By.name("day")).sendKeys("28");
			Sleep(3);
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(4) > div:nth-child(3) > div > div > a")).click();
			Sleep(3);
			driver.findElement(By.name("year")).sendKeys("1993");
			driver.findElement(By.id("note")).sendKeys("Create a new Client with all possible options");
			new Select(driver.findElement(By.name("month"))).selectByVisibleText("September");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > div > div:nth-child(1) > label")).click();
			
			Sleep(3);

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			Sleep(3);

			//CREATING THE SECOND CLIENT HERE
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName+"Second");
			driver.findElement(By.name("lastName")).sendKeys("Second client");
			 
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > div > div:nth-child(1) > label")).click();
			
			Sleep(3);

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();

			
			if(FindClient(ClientName+"Second")==null)
				throw new Exception("The Client was not created where it should have");
			 
			 
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Clients_TestCase_7() //Create a new Client via Customer interface (By making an appointment) where the Client with the same Email does not exist
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://rocket:rock4me@www-staging.vaniday.com.au/");
			Sleep(5);
			driver.get("https://www-staging.vaniday.com.au/signup");
			
			Sleep(5 );
			
			String Rand =RandomStringUtils.randomAlphanumeric(4).toUpperCase();
			driver.findElement(By.name("firstName")).sendKeys(ClientName+Rand);
			driver.findElement(By.name("lastName")).sendKeys("CreatedViaMarketPlace");
			driver.findElement(By.name("email")).sendKeys(Rand+"my@vaniday.com");
			driver.findElement(By.name("password")).sendKeys(ClientName+Rand);
			 


			driver.findElement(By.cssSelector("#app > div.ng-scope.main > section > div > signup-form > form > fieldset > div > div.input-group.submit-signup > button")).click();
			
			Sleep(5);
			driver.get("http://www-staging.vaniday.com.au/salons/vanidateau");
			Sleep(10);
			
			//CREATING AND APPOINTMENT VIA MARKETPLACE USING THE NEWLY CREATED CLIENT
			
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__content.is-first.is-desktop.hide-on-med-and-down")).click();
			
			Sleep(3);
			driver.findElement(By.cssSelector("#section-salon > div.vaniday-sections-salon__main-info-wrapper > div.vaniday-sections-salon__main-info-wrapper__right-side > div.vaniday-sections-salon__book-now-box > vaniday-salon-block-book-now > div > a > span.cart-text")).click();

			Sleep(5);
			WebElement Date = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__monthly-carousel > div.vaniday-timepicker__monthly-carousel__carousel.ng-scope > ul > li:nth-child(1) > div:nth-child(4) > div.day.ng-binding"));
			Date.click();
			String DateValue = Date.getText();
			
			String Month = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[0].toString();
			String Year = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[1].toString().replace(",", "");

			Date FinalDate = new SimpleDateFormat("MMMMMMMMMM").parse(Month);
			FinalDate.setDate(Integer.parseInt(DateValue));
			FinalDate.setYear(Integer.parseInt(Year)-1900);
			//Calendar cal = Calendar.getInstance();
			//cal.setTime(date);
			//println(cal.get(Calendar.MONTH));
			
			

			Sleep(10);
			
			List<WebElement> TimeItems = driver.findElements(By.className("time"));
						
			for(WebElement Time: TimeItems)
			{
				if(Time.getText().contains("11:30 AM"))
					Time.click();
			}
			Sleep(10);
			
			try{
				driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > div > div:nth-child(1) > vaniday-payment-tabs > ul > li:nth-child(2) > a > span > strong")).click();

			}
			catch(org.openqa.selenium.StaleElementReferenceException ex)
			{
				driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > div > div:nth-child(1) > vaniday-payment-tabs > ul > li:nth-child(2) > a > span > strong")).click();

			  
			}
			
			//driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > div > div:nth-child(1) > vaniday-payment-tabs > ul > li:nth-child(2) > a > span > strong")).click();
			Sleep(3);

			driver.findElement(By.name("message")).sendKeys(ClientName);
			Sleep(3);
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-recap-booking-bar.ng-scope > vaniday-booking-recap-sidebar > aside > div:nth-child(4) > div > a")).click(); 
			
			Sleep(3);
			
			SecureRandom random = new SecureRandom();
			int num = random.nextInt(10000000);
			driver.findElement(By.cssSelector("#app > div.modal.fade.ng-isolate-scope.in > div > div > div > div > vani-phone-check > vani-phone-check-save > div > div.phone-check__content > form > div.input-group.phone.required.ng-scope > ng-transclude > div > bc-phone-number > section > input")).sendKeys("39"+num);
			Sleep(3);
			
			driver.findElement(By.cssSelector("#app > div.modal.fade.ng-isolate-scope.in > div > div > div > div > vani-phone-check > vani-phone-check-save > div > div.phone-check__content > form > div.btn-size-lg > button")).click();
			
			Sleep(5);
			// Verifying the appointment here.
			
			JSONArray Docs = SearchEventByNote(ClientName,new SimpleDateFormat("yyyy-MM-dd").format(FinalDate).toString() );
			
			if(Docs.length()==0)
				throw new Exception("The booking was not created");
			
			// OPENING THE APPOINTMENT HERE
			driver.get("http://my-staging.vaniday.com.au/#/vanidateau/calendar?view=day&date="+new SimpleDateFormat("yyyy-MM-dd").format(FinalDate).toString() );
			Sleep(10);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(4) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.duration-45.booking__automatic--pending > div.fc-content > div.duration > b")).click();
			Sleep(5);
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-automatic-booking-modal.ng-scope.in > div > div > div.modal-header > div > div.status-combo.is-pending > a > span.status-combo__active-status__action-change-status.ng-scope > span")).click();
			Sleep(2);
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-automatic-booking-modal.ng-scope.in > div > div > div.modal-header > div > div.status-combo.is-pending > ul > li.status-combo__list__item.ng-scope.is-approved > a")).click();

			Sleep(5);
			
			if(FindClient(ClientName+Rand)==null)
				throw new Exception("The Client was not created where it should have");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Clients_TestCase_9() //Create a new Client via Customer interface (By making an appointment) where the Client with the same Email exists BY SIGNING UP
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("http://rocket:rock4me@www-staging.vaniday.com.au/");
			Sleep(5);
			driver.get("http://www-staging.vaniday.com.au/signup");
			
			Sleep(5 );
			
			String Rand =RandomStringUtils.randomAlphanumeric(4).toUpperCase();
			driver.findElement(By.name("firstName")).sendKeys(ClientName+Rand);
			driver.findElement(By.name("lastName")).sendKeys("CreatedViaMarketPlace");
			driver.findElement(By.name("email")).sendKeys(Rand+"my@vaniday.com");
			driver.findElement(By.name("password")).sendKeys(ClientName+Rand);
			 


			driver.findElement(By.cssSelector("#app > div.ng-scope.main > section > div > signup-form > form > fieldset > div > div.input-group.submit-signup > button")).click();
			
			Sleep(5);
			//Creating the same client with same email ID again
			
driver.get("http://www-staging.vaniday.com.au/signup");
			
			Sleep(5 );
			
		 
			driver.findElement(By.name("firstName")).sendKeys(ClientName+Rand);
			driver.findElement(By.name("lastName")).sendKeys("CreatedViaMarketPlace");
			driver.findElement(By.name("email")).sendKeys(Rand+"my@vaniday.com");
			driver.findElement(By.name("password")).sendKeys(ClientName+Rand);
			 


			driver.findElement(By.cssSelector("#app > div.ng-scope.main > section > div > signup-form > form > fieldset > div > div.input-group.submit-signup > button")).click();
			
			
			 //
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	
	public void Clients_TestCase_10() //Create a new Client by providing invalid value in Phone
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First client");
			driver.findElement(By.name("email")).sendKeys("my@vaniday.com");
			driver.findElement(By.name("phone")).sendKeys("InvalidString");
			driver.findElement(By.name("day")).sendKeys("28");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(4) > div:nth-child(3) > div > div > a")).click();
			driver.findElement(By.name("year")).sendKeys("1993");
			driver.findElement(By.id("note")).sendKeys("Create a new Client with all possible options");
			new Select(driver.findElement(By.name("month"))).selectByVisibleText("September");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > div > div:nth-child(1) > label")).click();
			
			Sleep(3);

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();

			
			if(FindClient(ClientName)!=null)
				throw new Exception("The Client was created where it should not have");
			 
			 
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Clients_TestCase_11() //Create a new Client by providing invalid value in Day and YEar
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First client");
			driver.findElement(By.name("email")).sendKeys("my@vaniday.com");
			driver.findElement(By.name("phone")).sendKeys("InvalidString");
			driver.findElement(By.name("day")).sendKeys("Thirty");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(4) > div:nth-child(3) > div > div > a")).click();
			driver.findElement(By.name("year")).sendKeys("TwoThousand");
			driver.findElement(By.id("note")).sendKeys("Create a new Client with all possible options");
			new Select(driver.findElement(By.name("month"))).selectByVisibleText("September");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > div > div:nth-child(1) > label")).click();
			
			Sleep(3);

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();

			
			if(FindClient(ClientName)!=null)
				throw new Exception("The Client was  created where it should not have");
			 
			 
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Clients_TestCase_12() //Create a new Client by providing invalid value in Day and YEar
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First client");
			driver.findElement(By.name("email")).sendKeys("my@vaniday.com");
			driver.findElement(By.name("phone")).sendKeys("InvalidString");
			driver.findElement(By.name("day")).sendKeys("999");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(4) > div:nth-child(3) > div > div > a")).click();
			driver.findElement(By.name("year")).sendKeys("99999");
			driver.findElement(By.id("note")).sendKeys("Create a new Client with all possible options");
			new Select(driver.findElement(By.name("month"))).selectByVisibleText("September");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > div > div:nth-child(1) > label")).click();
			
			Sleep(3);

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();

			
			if(FindClient(ClientName)!=null)
				throw new Exception("The Client was  created where it should not have");
			 
			 
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Clients_TestCase_13() //Edit a client and change all of it's attributes
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First client");
			driver.findElement(By.name("email")).sendKeys("my@vaniday.com");
			driver.findElement(By.name("phone")).sendKeys("333845742");
			driver.findElement(By.name("day")).sendKeys("28");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(4) > div:nth-child(3) > div > div > a")).click();
			driver.findElement(By.name("year")).sendKeys("1993");
			driver.findElement(By.id("note")).sendKeys("Create a new Client with all possible options");
			new Select(driver.findElement(By.name("month"))).selectByVisibleText("September");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > div > div:nth-child(1) > label")).click();
			
			Sleep(3);

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			Sleep(3);
			driver.get("http://my-staging.vaniday.com.au/#/vanidateau/clients/"+FindClient(ClientName).getString("id"));
			Sleep(3);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.customer-container > div.customer-info > div.client-details.ng-scope > section > div.client-info-actions > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).clear();

			driver.findElement(By.name("firstName")).sendKeys(ClientName+"2");
			driver.findElement(By.name("lastName")).clear();
			driver.findElement(By.name("lastName")).sendKeys("First client2");
			driver.findElement(By.name("email")).clear();
			driver.findElement(By.name("email")).sendKeys("my@vaniday.com2");
			driver.findElement(By.name("phone")).clear();
			driver.findElement(By.name("phone")).sendKeys("333845744");
			driver.findElement(By.name("day")).clear();
			driver.findElement(By.name("day")).sendKeys("25");
			driver.findElement(By.name("year")).clear();
			driver.findElement(By.name("year")).sendKeys("1983");
			driver.findElement(By.id("note")).clear();

			driver.findElement(By.id("note")).sendKeys("New");
			new Select(driver.findElement(By.name("month"))).selectByVisibleText("October");
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > div > div:nth-child(1) > label")).click();
			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();

			Sleep(3);
			
			if(FindClient(ClientName+"2")==null)
				throw new Exception("The Client was not created where it should have");
			if(FindClient(ClientName+"2").getString("lastName").contains("First client2")!=true)
				throw new Exception("The Client last name was not set");
			if(FindClient(ClientName+"2").getString("email").contains("my@vaniday.com2")!=true)
				throw new Exception("The Client email was not set");
			if(FindClient(ClientName+"2").getString("phoneNumber").contains("333845744")!=true)
				throw new Exception("The Client phone was not set");
			if(FindClient(ClientName+"2").getString("birthday").contains("1983-10-25")!=true)
				throw new Exception("The Client birthday was not set");
			if(FindClient(ClientName+"2").getString("note").contains("New")!=true)
				throw new Exception("The Client note was not set");
			if(FindClient(ClientName+"2").getString("gender").contains("male")!=true)
				throw new Exception("The Client year was not set");
			 
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Clients_TestCase_14() //Delete a newly created client
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First time");

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			Sleep(3);

			if(FindClient(ClientName)==null)
				throw new Exception("The Client was not created where it should have");
			
			driver.get("http://my-staging.vaniday.com.au/#/vanidateau/clients/"+FindClient(ClientName).getString("id"));
			Sleep(3);
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.customer-container > div.customer-info > div.client-details.ng-scope > section > div.client-info-actions > a")).click();
			Sleep(3);
			
			driver.findElement(By.cssSelector("body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary")).click();

			Sleep(3);
			if(FindClient(ClientName)!=null)
				throw new Exception("The Client was cnot deleted where it should have");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Clients_TestCase_15_1() //Search a client by a search string NAME
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First time");
			


			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			Sleep(3);

			if(FindClient(ClientName)==null)
				throw new Exception("The Client was not created where it should have");
			 
			
			driver.findElement(By.name("customerSearch")).sendKeys(ClientName);
			
			List<WebElement> Contacts = driver.findElements(By.className("list-group-item"));
			
			for(WebElement element : Contacts)
			{
				 

				if(element.getText().contains(ClientName)!=true)
					throw new Exception("Search did not return proper results");
			}
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Clients_TestCase_15_2() //Search a client by a search string Email
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First time");
			driver.findElement(By.name("email")).sendKeys(ClientName+"@vaniday.com");

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			Sleep(3);

			if(FindClient(ClientName)==null)
				throw new Exception("The Client was not created where it should have");
			 
			
			driver.findElement(By.name("customerSearch")).sendKeys(ClientName+"@vaniday.com");
			
			List<WebElement> Contacts = driver.findElements(By.className("list-group-item"));
			
			for(WebElement element : Contacts)
			{
				 

				if(element.getText().contains(ClientName)!=true)
					throw new Exception("Search did not return proper results");
			}
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Clients_TestCase_15_3() //Search a client by a search string Phone number
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First time");
			driver.findElement(By.name("phone")).sendKeys("222845234");


			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			Sleep(3);

			if(FindClient(ClientName)==null)
				throw new Exception("The Client was not created where it should have");
			 
			
			driver.findElement(By.name("customerSearch")).sendKeys("222845234");
			
			List<WebElement> Contacts = driver.findElements(By.className("list-group-item"));
			
			for(WebElement element : Contacts)
			{
				 

				if(element.getText().contains(ClientName)!=true)
					throw new Exception("Search did not return proper results");
			}
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Clients_TestCase_16() //Make a new appointment though a client profile
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(4) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First time");

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			Sleep(3);

			if(FindClient(ClientName)==null)
				throw new Exception("The Client was not created where it should have");
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients/"+FindClient(ClientName).getString("id"));
			Sleep(3);
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.customer-container > div.customer-info > div.client-details.ng-scope > section > div.client-info-actions > vm-add-new-appointment-button > a")).click();
			Sleep(3);
			
			//CREATING THE APPOINTMENT HERE
			Sleep(1);
			 
			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(5);
			List<WebElement>  listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/form/ng-form/vm-booking-item/div/div/div[1]/div/div[1]/div/div/ul/li/a"));
			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("body > div.modal.manage-appointment-modal.fade.ng-scope.in > div > div > div.modal-footer > a")).click();
			driver.findElement(By.id("note")).sendKeys(ClientName);
			
			String CurrentDate= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			driver.findElement(By.cssSelector(
					"body > div.modal.manage-appointment-modal.fade.ng-scope.in > div > div > div.modal-footer > button > span"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(ClientName, CurrentDate);
			if(Docs.length()==0)
				throw new Exception("Appointment was not created");
			Sleep(3);
			if(FindClient(ClientName)==null)
				throw new Exception("The Client was  not created where it should have");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Clients_TestCase_17() //Make a new appointment though a client profile and then change the client during creation of the appointment
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First time");

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			Sleep(3);

			if(FindClient(ClientName)==null)
				throw new Exception("The Client was not created where it should have");
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients/"+FindClient(ClientName).getString("id"));
			Sleep(3);
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.customer-container > div.customer-info > div.client-details.ng-scope > section > div.client-info-actions > vm-add-new-appointment-button > a")).click();
			Sleep(3);
			
			//CREATING THE APPOINTMENT HERE
			Sleep(1);
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.customer-container > div.customer-info > div.client-details.ng-scope > section > div.client-info-actions > vm-add-new-appointment-button > a")).click();
			Sleep(3);
			driver.findElement(By.xpath("//*[@id=\"customerSearch\"]")).sendKeys("Julia Roberts");

			Sleep(3);
			List<WebElement> listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/form/vm-appointment-context/div/div[2]/vm-client-autocomplete/ng-form/div[2]/ul/li/a"));
			listItems.get(0).click();
			
			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			  listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/form/ng-form/vm-booking-item/div/div/div[1]/div/div[1]/div/div/ul/li/a"));
			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("body > div.modal.manage-appointment-modal.fade.ng-scope.in > div > div > div.modal-footer > a")).click();
			driver.findElement(By.id("note")).sendKeys(ClientName);
			
			String CurrentDate= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			driver.findElement(By.cssSelector(
					"body > div.modal.manage-appointment-modal.fade.ng-scope.in > div > div > div.modal-footer > button > span"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(ClientName, CurrentDate);
			if(Docs.getJSONObject(0).getJSONObject("appointment").getString("customerEmail").contains("julia.roberts@bollywood.com")!=true)
				throw new Exception("Customer was not changed");
			Sleep(3);
			if(FindClient(ClientName)==null)
				throw new Exception("The Client was  not created where it should have");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Clients_TestCase_18() //Create a new client while making a new appointment
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > vm-manage-client-button > a")).click();
			Sleep(3);
			
			driver.findElement(By.name("firstName")).sendKeys(ClientName);
			driver.findElement(By.name("lastName")).sendKeys("First time");

			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			Sleep(3);

			if(FindClient(ClientName)==null)
				throw new Exception("The Client was not created where it should have");
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients/"+FindClient(ClientName).getString("id"));
			Sleep(3);
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.customer-container > div.customer-info > div.client-details.ng-scope > section > div.client-info-actions > vm-add-new-appointment-button > a")).click();
			Sleep(3);
			
			//CREATING THE APPOINTMENT HERE
			Sleep(1);
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.customer-container > div.customer-info > div.client-details.ng-scope > section > div.client-info-actions > vm-add-new-appointment-button > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector("body > div.modal.manage-appointment-modal.fade.ng-scope.in > div > div > div.modal-body > form > vm-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > div > vm-manage-client-button > a")).click();
			Sleep(2);
			driver.findElement(By.name("firstName")).sendKeys("New AppointmentTestCase");
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();

			Sleep(3);
			 
			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(4);
			List<WebElement>  listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/form/ng-form/vm-booking-item/div/div/div[1]/div/div[1]/div/div/ul/li/a"));
			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("body > div.modal.manage-appointment-modal.fade.ng-scope.in > div > div > div.modal-footer > a")).click();
			driver.findElement(By.id("note")).sendKeys(ClientName);
			
			String CurrentDate= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			driver.findElement(By.cssSelector(
					"body > div.modal.manage-appointment-modal.fade.ng-scope.in > div > div > div.modal-footer > button > span"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(ClientName, CurrentDate);
			if(Docs.length()==0)
				throw new Exception("Appointment was not created");
			Sleep(3);
			if(FindClient("New AppointmentTestCase")==null)
				throw new Exception("The Client was  not created where it should have");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Import_Client_TestCase_1() // Import a file with valid text format and file format
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(8);
			
	 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > a > i")).click();
			 
			 Sleep(5);
			 
			 driver.findElement(By.name("file")).sendKeys(FilePath+"/CorrectDataCorrectFormat.csv");

			 Sleep(5);
			 if(VerifyClients("TestCase").size()!=10)
				 throw new Exception("The clients were not added");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Import_Client_TestCase_2() // Import a file with valid text format but invalid file format
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(8);
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > a > i")).click();
			 
			 Sleep(5);
			 
			 driver.findElement(By.name("file")).sendKeys(FilePath+"/CorrectDataInvalidFormat.jpg");
			 Sleep(5);

			 if(VerifyClients("TestCase").size()!=0)
				 throw new Exception("The clients were added");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Import_Client_TestCase_3() // Import a file with invalid text format but  valid file format
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(8);
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > a > i")).click();
			 
			 Sleep(5);
			 
			 driver.findElement(By.name("file")).sendKeys(FilePath+"/InvalidDataValidFormat.jpg");
			 Sleep(5);

			 if(VerifyClients("TestCase").size()!=0)
				 throw new Exception("The clients were not added");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Import_Client_TestCase_4() // Import a file with invalid text format invalid file format
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(8);
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > a > i")).click();
			 
			 Sleep(5);
			 
			 driver.findElement(By.name("file")).sendKeys(FilePath+"/InvalidDataInvalidFormat.jpg");
			 Sleep(5);

			 if(VerifyClients("TestCase").size()!=0)
				 throw new Exception("The clients were not added");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
//	public void Import_Client_TestCase_5() // Import a file with records greater than the limit allowed(Add around 500 clients)
//	{
//		try {
//			String ClientName = new Object() {
//			}.getClass().getEnclosingMethod().getName();
//			
//			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
//			Sleep(8);
//			
//			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > a > i")).click();
//			 
//			 Sleep(5);
//			 
//			 driver.findElement(By.name("file")).sendKeys(FilePath+"/LargeNumberClients.csv");
//			 Sleep(5);
//
//			 if(VerifyClients("TestCase").size()!=500)
//				 throw new Exception("The clients were not added");
//			
//			TestPassed();
//
//		} catch (Exception e) {
//			TestFailed(e);
//		}
//	}
	
	public void Import_Client_TestCase_6() // Import a file that contains headers as well
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(8);
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > a > i")).click();
			 
			 Sleep(5);
			 
			 driver.findElement(By.name("file")).sendKeys(FilePath+"/ClientsIncludingHeaders.csv");
			 Sleep(5);

			 if(VerifyClients("TestCase").size()!=10)
				 throw new Exception("The clients were not added");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Import_Client_TestCase_7() // import data with YYYY-MM-DD format
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(8);
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > a > i")).click();
			 
			 Sleep(5);
			 
			 driver.findElement(By.name("file")).sendKeys(FilePath+"/YYYY-MM-DDFormat.csv");
			 Sleep(5);

			 if(VerifyClients("TestCase").size()!=10)
				 throw new Exception("The clients were not added");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Import_Client_TestCase_8() // Import data with DD.MM.YYYY
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(8);
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > a > i")).click();
			 
			 Sleep(5);
			 
			 driver.findElement(By.name("file")).sendKeys(FilePath+"/DDdotMMdotYYYY.csv");
			 Sleep(5);

			 if(VerifyClients("TestCase").size()!=10)
				 throw new Exception("The clients were not added");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Import_Client_TestCase_9() // Import data with Gender as f/m
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(8);
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > a > i")).click();
			 
			 Sleep(5);
			 
			 driver.findElement(By.name("file")).sendKeys(FilePath+"/FMFormat.csv");
			 Sleep(5);

			 if(VerifyClients("TestCase").size()!=10)
				 throw new Exception("The clients were not added");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Import_Client_TestCase_10() //Import Data with Gender as female/male
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(8);
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > a > i")).click();
			 
			 Sleep(5);
			 
			 driver.findElement(By.name("file")).sendKeys(FilePath+"/female-maleFormat.csv");
			 Sleep(5);

			 if(VerifyClients("TestCase").size()!=10)
				 throw new Exception("The clients were not added");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	public void Import_Client_TestCase_11() //Import Data with mixed formats of Gender and DOBs
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/clients");
			Sleep(8);
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.section-customer-info > div.section-heading > div > a > i")).click();
			 
			 Sleep(5);
			 
			 driver.findElement(By.name("file")).sendKeys(FilePath+"/MixedFormat.csv");
			 Sleep(5);

			 if(VerifyClients("TestCase").size()!=10)
				 throw new Exception("The clients were not added");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	 
}