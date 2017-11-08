import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NotificationTests extends myCommon{
	
	public String FilePath;
	public String CurrentDate;
	
	NotificationTests(WebDriver mydriver,String myAuthToken)  {
		
		 driver=mydriver;
		 AuthToken=myAuthToken;
		 FilePath="/Users/arsaljalib/eclipse-workspace/MyVanidayTestApplication/Files";
		 
		 Calendar cal = Calendar.getInstance();  
		    cal.setTime(new Date());  
		    cal.add(Calendar.DATE, 10); 
		    
			CurrentDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());

		 	ResetAppointments(new SimpleDateFormat("yyyy-MM-dd").format( cal.getTime()));
		 ResetClients(AuthToken);
			driver.manage().window().maximize();

	 // ExecuteAllTests(driver, ClientTests.class);

		 //ExecuteSpecificTest("Clients_TestCase_7",ClientTests.class);
		 ExecuteSimilarTests("Notifications_TestCase_9",NotificationTests.class,driver);
	  PrintTestResults();
	}
	
	public void Notifications_TestCase_1() // Enable confirmation email and verify that the email is received (Manual appointment)
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
 			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(9) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > span > ul > li:nth-child(3) > a > span > i")).click();
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			
			WebElement myButton =driver.findElement(By.cssSelector("#enabled"));
			if(myButton.getClass().toString().contains("checked")!=true)
				myButton.click();
				
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(3) > a > i")).click();
			
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div.fc-bgevent-container > div:nth-child(1)")).click();
			Sleep(3);
			driver.findElement(By.cssSelector("div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope")).click();
			Sleep(3);
			driver.findElement(By.name("customerSearch")).sendKeys("Arsal");

			Sleep(5);
			List<WebElement> listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/vm-manage-manual-appointment-context/div/div[2]/vm-client-autocomplete/ng-form/div[2]/ul/li/a"));
			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(3);
			listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/ng-form/vm-manage-manual-appointment-booking-item/div/div[1]/div[1]/div/div[1]/div/div/ul/li/a"));
			listItems.get(0).click();
 
			 driver.findElement(By.id("note")).sendKeys(ClientName);
			
			new Select(driver.findElement(By.id("time"))).selectByVisibleText("01:30 AM");
			new Select(driver.findElement(By.id("duration"))).selectByVisibleText("1h00m");

			
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button > span"))
					.click();
			
		 	Sleep(300);
			
			if(VerifyNotificationEmail(CurrentDate,"01:30 am","Thank you for booking an appointment") !=true)
				throw new Exception ("The email was not received");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Notifications_TestCase_2() // Disable confirmation email and verify that the email is not received (Manual appointment)
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
 			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(9) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > span > ul > li:nth-child(3) > a > span > i")).click();
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			
			WebElement myButton =driver.findElement(By.cssSelector("#enabled"));
			if(myButton.getClass().toString().contains("checked")!=true)
				myButton.click();
				
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(3) > a > i")).click();
			
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div.fc-bgevent-container > div:nth-child(1)")).click();
			Sleep(3);
			driver.findElement(By.cssSelector("div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope")).click();
			Sleep(3);
			driver.findElement(By.name("customerSearch")).sendKeys("Arsal");

			Sleep(5);
			List<WebElement> listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/vm-manage-manual-appointment-context/div/div[2]/vm-client-autocomplete/ng-form/div[2]/ul/li/a"));
			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(3);
			listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/ng-form/vm-manage-manual-appointment-booking-item/div/div[1]/div[1]/div/div[1]/div/div/ul/li/a"));
			listItems.get(0).click();
 
			 driver.findElement(By.id("note")).sendKeys(ClientName);
			
			new Select(driver.findElement(By.id("time"))).selectByVisibleText("01:30 AM");
			new Select(driver.findElement(By.id("duration"))).selectByVisibleText("1h00m");

			
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button > span"))
					.click();
			
		 	Sleep(300);
			
			if(VerifyNotificationEmail(CurrentDate,"01:30 am","Thank you for booking an appointment") !=true)
				throw new Exception ("The email was not received");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Notifications_TestCase_3() // Enable Reschedule email and verify that the email is received (Manual appointment)
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
 			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(9) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > span > ul > li:nth-child(3) > a > span > i")).click();
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			
			WebElement myButton =driver.findElement(By.cssSelector("#enabled"));
			if(myButton.getClass().toString().contains("checked")!=true)
				myButton.click();
				
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(3) > a > i")).click();
			
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div.fc-bgevent-container > div:nth-child(1)")).click();
			Sleep(3);
			driver.findElement(By.cssSelector("div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope")).click();
			Sleep(3);
			driver.findElement(By.name("customerSearch")).sendKeys("Arsal");

			Sleep(5);
			List<WebElement> listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/vm-manage-manual-appointment-context/div/div[2]/vm-client-autocomplete/ng-form/div[2]/ul/li/a"));
			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(3);
			listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/ng-form/vm-manage-manual-appointment-booking-item/div/div[1]/div[1]/div/div[1]/div/div/ul/li/a"));
			listItems.get(0).click();
 
			 driver.findElement(By.id("note")).sendKeys(ClientName);
			
			new Select(driver.findElement(By.id("time"))).selectByVisibleText("01:30 AM");
			new Select(driver.findElement(By.id("duration"))).selectByVisibleText("1h00m");

			
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button > span"))
					.click();
			
		 	Sleep(300);
			
			if(VerifyNotificationEmail(CurrentDate,"01:30 am","Thank you for booking an appointment") !=true)
				throw new Exception ("The email was not received");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Notifications_TestCase_4() // Disable reschedule email and verify that the email is not received (Manual appointment)
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
 			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(9) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > span > ul > li:nth-child(3) > a > span > i")).click();
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			
			WebElement myButton =driver.findElement(By.cssSelector("#enabled"));
			if(myButton.getClass().toString().contains("checked")!=true)
				myButton.click();
				
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(3) > a > i")).click();
			
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div.fc-bgevent-container > div:nth-child(1)")).click();
			Sleep(3);
			driver.findElement(By.cssSelector("div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope")).click();
			Sleep(3);
			driver.findElement(By.name("customerSearch")).sendKeys("Arsal");

			Sleep(5);
			List<WebElement> listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/vm-manage-manual-appointment-context/div/div[2]/vm-client-autocomplete/ng-form/div[2]/ul/li/a"));
			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(3);
			listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/ng-form/vm-manage-manual-appointment-booking-item/div/div[1]/div[1]/div/div[1]/div/div/ul/li/a"));
			listItems.get(0).click();
 
			 driver.findElement(By.id("note")).sendKeys(ClientName);
			
			new Select(driver.findElement(By.id("time"))).selectByVisibleText("01:30 AM");
			new Select(driver.findElement(By.id("duration"))).selectByVisibleText("1h00m");

			
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button > span"))
					.click();
			
		 	Sleep(300);
			
			if(VerifyNotificationEmail(CurrentDate,"01:30 am","Thank you for booking an appointment") !=true)
				throw new Exception ("The email was not received");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Notifications_TestCase_5() // Enable cancellation email and verify that the email is received (Manual appointment)
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
 			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(9) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > span > ul > li:nth-child(3) > a > span > i")).click();
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			
			WebElement myButton =driver.findElement(By.cssSelector("#enabled"));
			if(myButton.getClass().toString().contains("checked")!=true)
				myButton.click();
				
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(3) > a > i")).click();
			
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div.fc-bgevent-container > div:nth-child(1)")).click();
			Sleep(3);
			driver.findElement(By.cssSelector("div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope")).click();
			Sleep(3);
			driver.findElement(By.name("customerSearch")).sendKeys("Arsal");

			Sleep(5);
			List<WebElement> listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/vm-manage-manual-appointment-context/div/div[2]/vm-client-autocomplete/ng-form/div[2]/ul/li/a"));
			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(3);
			listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/ng-form/vm-manage-manual-appointment-booking-item/div/div[1]/div[1]/div/div[1]/div/div/ul/li/a"));
			listItems.get(0).click();
 
			 driver.findElement(By.id("note")).sendKeys(ClientName);
			
			new Select(driver.findElement(By.id("time"))).selectByVisibleText("01:30 AM");
			new Select(driver.findElement(By.id("duration"))).selectByVisibleText("1h00m");

			
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button > span"))
					.click();
			
		 	Sleep(300);
			
			if(VerifyNotificationEmail(CurrentDate,"01:30 am","Thank you for booking an appointment") !=true)
				throw new Exception ("The email was not received");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Notifications_TestCase_6() // Disable cancellation email and verify that the email is not received (Manual appointment)
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
 			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(9) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > span > ul > li:nth-child(3) > a > span > i")).click();
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			
			WebElement myButton =driver.findElement(By.cssSelector("#enabled"));
			if(myButton.getClass().toString().contains("checked")!=true)
				myButton.click();
				
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(3) > a > i")).click();
			
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div.fc-bgevent-container > div:nth-child(1)")).click();
			Sleep(3);
			driver.findElement(By.cssSelector("div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope")).click();
			Sleep(3);
			driver.findElement(By.name("customerSearch")).sendKeys("Arsal");

			Sleep(5);
			List<WebElement> listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/vm-manage-manual-appointment-context/div/div[2]/vm-client-autocomplete/ng-form/div[2]/ul/li/a"));
			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(3);
			listItems = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[2]/div/form/ng-form/vm-manage-manual-appointment-booking-item/div/div[1]/div[1]/div/div[1]/div/div/ul/li/a"));
			listItems.get(0).click();
 
			 driver.findElement(By.id("note")).sendKeys(ClientName);
			
			new Select(driver.findElement(By.id("time"))).selectByVisibleText("01:30 AM");
			new Select(driver.findElement(By.id("duration"))).selectByVisibleText("1h00m");

			
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button > span"))
					.click();
			
		 	Sleep(300);
			
			if(VerifyNotificationEmail(CurrentDate,"01:30 am","Thank you for booking an appointment") !=true)
				throw new Exception ("The email was not received");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Notifications_TestCase_7() // Enable confirmation email and verify that the email is received (Marketplace appointment)
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
 			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(9) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > span > ul > li:nth-child(3) > a > span > i")).click();
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			
			WebElement myButton =driver.findElement(By.cssSelector("#enabled"));
			if(myButton.getClass().toString().contains("checked")!=true)
				{
					myButton.click();
				}
				
			Sleep(5);
			SignInMarketPlace();
			Sleep(3);

			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__add-action.hide-on-med-and-down > a > span > span > i")).click();
			
			Sleep(5);

			driver.findElement(By.cssSelector("#section-salon > div.vaniday-sections-salon__main-info-wrapper > div.vaniday-sections-salon__main-info-wrapper__right-side > div.vaniday-sections-salon__book-now-box > vaniday-salon-block-book-now > div > a")).click();
		 
			Sleep(5);
			WebElement Date = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__monthly-carousel > div.vaniday-timepicker__monthly-carousel__carousel.ng-scope > ul > li:nth-child(1) > div:nth-child(4) > div.day.ng-binding"));
			Date.click();
			String DateValue = Date.getText();
			
			String Month = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[0].toString();
			String Year = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[1].toString().replace(",", "");

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 3);
			String Starting = new SimpleDateFormat("yyyy-Mmm-dd").format(cal.getTime());
			
			 
			Sleep(2);
			List<WebElement> TimeItems = driver.findElements(By.className("time"));
			
			TimeItems.get(4).click();
			String Time=TimeItems.get(4).getText().toLowerCase();
			
			Sleep(5);
			
			
			

			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > div > div:nth-child(1) > vaniday-payment-tabs > ul > li:nth-child(2) > a > span > strong")).click();
			Sleep(5);

			driver.findElement(By.name("message")).sendKeys(ClientName);
			
			Sleep(5);
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-recap-booking-bar.ng-scope > vaniday-booking-recap-sidebar > aside > div:nth-child(4) > div > a")).click(); 
			// Verifying the appointment here.	
			
		 	 Sleep(300);
			
		 	if(Time.charAt(0)=='0')
		 	{
		 		Time= Time.substring(1);
		 	}
			if(VerifyNotificationEmail(new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime()),Time,"Thank you for booking an appointment") !=true)
				throw new Exception ("The email was not received");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Notifications_TestCase_8() // Disable confirmation email and verify that the email is not received (Marketplace appointment)
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
 			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(9) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > span > ul > li:nth-child(3) > a > span > i")).click();
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			
			WebElement myButton =driver.findElement(By.cssSelector("#enabled"));
			if(myButton.getClass().toString().contains("checked")==true)
				{
					myButton.click();
				}
				
			Sleep(5);
			SignInMarketPlace();
			Sleep(3);

			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__add-action.hide-on-med-and-down > a > span > span > i")).click();
			
			Sleep(5);

			driver.findElement(By.cssSelector("#section-salon > div.vaniday-sections-salon__main-info-wrapper > div.vaniday-sections-salon__main-info-wrapper__right-side > div.vaniday-sections-salon__book-now-box > vaniday-salon-block-book-now > div > a")).click();
		 
			Sleep(5);
			WebElement Date = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__monthly-carousel > div.vaniday-timepicker__monthly-carousel__carousel.ng-scope > ul > li:nth-child(1) > div:nth-child(4) > div.day.ng-binding"));
			Date.click();
			String DateValue = Date.getText();
			
			String Month = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[0].toString();
			String Year = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[1].toString().replace(",", "");

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 3);
			String Starting = new SimpleDateFormat("yyyy-Mmm-dd").format(cal.getTime());
			
			 
			Sleep(2);
			List<WebElement> TimeItems = driver.findElements(By.className("time"));
			
			TimeItems.get(4).click();
			String Time=TimeItems.get(4).getText().toLowerCase();
			
			Sleep(5);
			
			
			

			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > div > div:nth-child(1) > vaniday-payment-tabs > ul > li:nth-child(2) > a > span > strong")).click();
			Sleep(5);

			driver.findElement(By.name("message")).sendKeys(ClientName);
			
			Sleep(5);
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-recap-booking-bar.ng-scope > vaniday-booking-recap-sidebar > aside > div:nth-child(4) > div > a")).click(); 
			// Verifying the appointment here.	
			
		 	 Sleep(300);
			
		 	if(Time.charAt(0)=='0')
		 	{
		 		Time= Time.substring(1);
		 	}
			if(VerifyNotificationEmail(new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime()),Time,"Thank you for booking an appointment") ==true)
				throw new Exception ("The email was   received");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Notifications_TestCase_9() // Enable Reschedule email and verify that the email is received (Marketplace appointment)
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
 			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(9) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > span > ul > li:nth-child(3) > a > span > i")).click();
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > ul > li:nth-child(4) > a")).click();
			Sleep(3);
			
			WebElement myButton =driver.findElement(By.cssSelector("#enabled"));
			if(myButton.getClass().toString().contains("checked")!=true)
				{
					myButton.click();
				}
				
			Sleep(5);
			SignInMarketPlace();
			Sleep(3);

			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__add-action.hide-on-med-and-down > a > span > span > i")).click();
			
			Sleep(5);

			driver.findElement(By.cssSelector("#section-salon > div.vaniday-sections-salon__main-info-wrapper > div.vaniday-sections-salon__main-info-wrapper__right-side > div.vaniday-sections-salon__book-now-box > vaniday-salon-block-book-now > div > a")).click();
		 
			Sleep(5);
			WebElement Date = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__monthly-carousel > div.vaniday-timepicker__monthly-carousel__carousel.ng-scope > ul > li:nth-child(1) > div:nth-child(4) > div.day.ng-binding"));
			Date.click();
			String DateValue = Date.getText();
			
			String Month = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[0].toString();
			String Year = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[1].toString().replace(",", "");

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 3);
			String Starting = new SimpleDateFormat("yyyy-Mmm-dd").format(cal.getTime());
			
			 
			Sleep(2);
			List<WebElement> TimeItems = driver.findElements(By.className("time"));
			
			TimeItems.get(4).click();
			String Time=TimeItems.get(4).getText().toLowerCase();
			
			Sleep(5);
			
			
			

			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > div > div:nth-child(1) > vaniday-payment-tabs > ul > li:nth-child(2) > a > span > strong")).click();
			Sleep(5);

			driver.findElement(By.name("message")).sendKeys(ClientName);
			
			Sleep(5);
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-recap-booking-bar.ng-scope > vaniday-booking-recap-sidebar > aside > div:nth-child(4) > div > a")).click(); 
			
			Sleep(5);
			
			driver.get("http://my-dev.vaniday.com.au/#/vanidateau/calendar?view=day&date="+new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
			
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(3) > div > div:nth-child(2) > a:nth-child(1) > div.fc-content")).click();
			
			Sleep(5);

			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.manage-automatic-booking-modal.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div.manage-calendar-modal__dialog__content__body__column-left > div > div > a")).click();
			Sleep(3);

			driver.findElement(By.cssSelector("body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary")).click();
			Sleep(3);

			new Select(driver.findElement(By.id("startAt"))).selectByVisibleText("09:00 AM");

			Sleep(3);

			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.manage-automatic-booking-modal.ng-scope.in.is-rescheduling > div > div > div.manage-calendar-modal__dialog__content__body > div.manage-calendar-modal__dialog__content__body__column-left > div > div > a.btn.btn-primary > span")).click();
			// Verifying the appointment here.	
			
		 	//Sleep(300);
			
		 	if(Time.charAt(0)=='0')
		 	{
		 		Time= Time.substring(1);
		 	}
			if(VerifyNotificationEmail(new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime()),Time,"Thank you for booking an appointment") !=true)
				throw new Exception ("The email was not received");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Notifications_TestCase_10() // Disable Reschedule email and verify that the email is not received (Marketplace appointment)
	{
		try {
			String ClientName = new Object() {
			}.getClass().getEnclosingMethod().getName();
 			driver.findElement(By.cssSelector("body > ui-view > vm-navbar > div > ul > li:nth-child(9) > a > i")).click();
			Sleep(5);
			
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > span > ul > li:nth-child(3) > a > span > i")).click();
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > ul > li:nth-child(4) > a")).click();
			Sleep(3);
			
			WebElement myButton =driver.findElement(By.cssSelector("#enabled"));
			if(myButton.getClass().toString().contains("checked")!=true)
				{
					myButton.click();
				}
				
			Sleep(5);
			SignInMarketPlace();
			Sleep(3);

			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__add-action.hide-on-med-and-down > a > span > span > i")).click();
			
			Sleep(5);

			driver.findElement(By.cssSelector("#section-salon > div.vaniday-sections-salon__main-info-wrapper > div.vaniday-sections-salon__main-info-wrapper__right-side > div.vaniday-sections-salon__book-now-box > vaniday-salon-block-book-now > div > a")).click();
		 
			Sleep(5);
			WebElement Date = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__monthly-carousel > div.vaniday-timepicker__monthly-carousel__carousel.ng-scope > ul > li:nth-child(1) > div:nth-child(4) > div.day.ng-binding"));
			Date.click();
			String DateValue = Date.getText();
			
			String Month = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[0].toString();
			String Year = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[1].toString().replace(",", "");

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 3);
			String Starting = new SimpleDateFormat("yyyy-Mmm-dd").format(cal.getTime());
			
			 
			Sleep(2);
			List<WebElement> TimeItems = driver.findElements(By.className("time"));
			
			TimeItems.get(4).click();
			String Time=TimeItems.get(4).getText().toLowerCase();
			
			Sleep(5);
			
			
			

			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > div > div:nth-child(1) > vaniday-payment-tabs > ul > li:nth-child(2) > a > span > strong")).click();
			Sleep(5);

			driver.findElement(By.name("message")).sendKeys(ClientName);
			
			Sleep(5);
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-recap-booking-bar.ng-scope > vaniday-booking-recap-sidebar > aside > div:nth-child(4) > div > a")).click(); 
			
			Sleep(5);
			
			driver.get("http://my-dev.vaniday.com.au/#/vanidateau/calendar?view=day&date="+new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
			
			Sleep(5);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(3) > div > div:nth-child(2) > a:nth-child(1) > div.fc-content")).click();
			
			Sleep(5);

			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.manage-automatic-booking-modal.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div.manage-calendar-modal__dialog__content__body__column-left > div > div > a")).click();
			Sleep(3);

			driver.findElement(By.cssSelector("body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary")).click();
			Sleep(3);

			new Select(driver.findElement(By.id("startAt"))).selectByVisibleText("09:00 AM");

			Sleep(3);

			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.manage-automatic-booking-modal.ng-scope.in.is-rescheduling > div > div > div.manage-calendar-modal__dialog__content__body > div.manage-calendar-modal__dialog__content__body__column-left > div > div > a.btn.btn-primary > span")).click();
			// Verifying the appointment here.	
			
		 	//Sleep(300);
			
		 	if(Time.charAt(0)=='0')
		 	{
		 		Time= Time.substring(1);
		 	}
			if(VerifyNotificationEmail(new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime()),Time,"Thank you for booking an appointment") ==true)
				throw new Exception ("The email was   received");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
}