import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CalenderTests extends myCommon {

	// PRE-REQUIREMENTS: There exists a customer named Julia Roberts
	// You have to create a Marketplace account for Vanidate AU for testing. Replace the email and Password of the account in the method named "SignInMarketPlace" in the myCommon.java class
	
	String CurrentDate;

	CalenderTests(WebDriver mydriver, String myAuthToken) {

		CurrentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Calendar cal = Calendar.getInstance();  
	    cal.setTime(new Date());  
	    cal.add(Calendar.DATE, 10);     
	  
	    
		driver = mydriver;
		AuthToken = myAuthToken;

	 	ResetAppointments(new SimpleDateFormat("yyyy-MM-dd").format( cal.getTime()));
	 	driver.get("http://my-staging.vaniday.com.au/");
		Sleep(5);
		driver.manage().window().maximize();
	 	
		   //ExecuteAllTests(driver, CalenderTests.class);

		// ExecuteSpecificTest("Calender_BlockedTime_TestCase_1", CalenderTests.class);
		  ExecuteSimilarTests("Calender_Appointment_TestCase_30",CalenderTests.class,driver);

	}

	public void Calender_Appointment_TestCase_1() // Create a simple appointment with the minimum required fields
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(1);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));
			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("serviceName").contains("Manicure") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("customerName").contains("Julia Roberts") != true)
				throw new Exception("The service was not set in the appointment");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Calender_Appointment_TestCase_2() // Create an appointment without specifying the Client
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(45) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			List<WebElement> listItems = driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/div/form/ng-form/vm-manage-manual-appointment-booking-item/div/div[1]/div[1]/div/div[1]/div/div/ul/li/a"));
			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");
			
			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.length() != 0)
				throw new Exception("The appointment was created where it should not have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_3() // Create an appointment without specifying the Service
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(1);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();
 

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.length() != 0)
				throw new Exception("The appointment was created where it should not have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_4() // Create  an appointment with multiple bookings  for the same Professional where the time does not overlap
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(3);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");
			
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements = driver.findElements(By.name("service"));
			ServiceElements.get(1).sendKeys("Women's haircut");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);

			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("serviceName").contains("Manicure") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("customerName").contains("Julia Roberts") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(1).getJSONObject("appointment").getString("serviceName").contains("Women's haircut") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(1).getJSONObject("appointment").getString("customerName").contains("Julia Roberts") != true)
				throw new Exception("The service was not set in the appointment");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_5() // Create an appointment with multiple bookings for the same Professional where the time duration overlap
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(78) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(3);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");
			
			new Select(driver.findElement(By.name("time"))).selectByVisibleText("08:00 PM");
			
			//ADDING SECOND BOOKING AFTER THIS
			
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements = driver.findElements(By.name("service"));
			ServiceElements.get(1).sendKeys("Women's haircut");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
			driver.findElement(By.cssSelector("#note")).click();
			
			
			listItems = driver.findElements(By.name("time"));
			new Select(listItems.get(1)).selectByVisibleText("08:15 PM");

			
			driver.findElement(By.id("note")).sendKeys(AppointmentID);

			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("serviceName").contains("Manicure") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("customerName").contains("Julia Roberts") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(0).getString("startAt").contains("20:00") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(1).getJSONObject("appointment").getString("serviceName").contains("Women's haircut") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(1).getJSONObject("appointment").getString("customerName").contains("Julia Roberts") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(1).getString("startAt").contains("20:15") != true)
				throw new Exception("The Time was not set in the appointment");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_6() // Create an appointment with multiple bookings for a client with different professionals
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(74) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(3);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));
			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));
			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");
			
			new Select(driver.findElement(By.name("time"))).selectByVisibleText("08:00 PM");
			
			//ADDING SECOND BOOKING AFTER THIS
			
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements = driver.findElements(By.name("service"));
			ServiceElements.get(1).sendKeys("Women's haircut");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
			driver.findElement(By.cssSelector("#note")).click();
			
			

			listItems = driver.findElements(By.name("time"));
			new Select(listItems.get(1)).selectByVisibleText("08:15 PM");
			
			listItems = driver.findElements(By.name("professional"));
			new Select(listItems.get(1)).selectByVisibleText("Camila");

			
			driver.findElement(By.id("note")).sendKeys(AppointmentID);

			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(1).getJSONObject("appointment").getString("serviceName").contains("Manicure") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(1).getJSONObject("appointment").getString("customerName").contains("Julia Roberts") != true)
				throw new Exception("The Customer was not set in the appointment");
			if (Docs.getJSONObject(1).getString("startAt").contains("20:00") != true)
				throw new Exception("The Time was not set in the appointment");
			if (FindProfessionalByID(Docs.getJSONObject(1).getString("professionalId")).getString("firstName").contains("Asia") != true)
				throw new Exception("The Professional was not set in the appointment");
			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("serviceName").contains("Women's haircut") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("customerName").contains("Julia Roberts") != true)
				throw new Exception("The Customer was not set in the appointment");
			if (Docs.getJSONObject(0).getString("startAt").contains("20:15") != true)
				throw new Exception("The Time was not set in the appointment");
			if (FindProfessionalByID(Docs.getJSONObject(0).getString("professionalId")).getString("firstName").contains("Camila") != true)
				throw new Exception("The Professional was not set in the appointment");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_7() // While creating an appointment add another booking and then remove the booking
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(74) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(3);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));
			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(3);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));
			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");
			
			new Select(driver.findElement(By.name("time"))).selectByVisibleText("08:00 PM");
			
			//ADDING SECOND BOOKING AFTER THIS
			
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements = driver.findElements(By.name("service"));
			ServiceElements.get(1).sendKeys("Women's haircut");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
			driver.findElement(By.cssSelector("#note")).click();
			

			listItems = driver.findElements(By.name("time"));
			new Select(listItems.get(1)).selectByVisibleText("08:15 PM");
			
			listItems = driver.findElements(By.name("professional"));
			new Select(listItems.get(1)).selectByVisibleText("Camila");

			
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			
			 driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form:nth-child(3) > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.add-new-appointment-form__footer > div > div > a > i")).click();
			 
			
			Sleep(3);
			
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("serviceName").contains("Manicure") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("customerName").contains("Julia Roberts") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(0).getString("startAt").contains("20:00") != true)
				throw new Exception("The Time was not set in the appointment");
			if (FindProfessionalByID(Docs.getJSONObject(0).getString("professionalId")).getString("firstName").contains("Asia") != true)
				throw new Exception("The Time was not set in the appointment");
			 if(Docs.length()>1)
				 throw new Exception("Multiple Bookings created where they should no have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Calender_Appointment_TestCase_8() // Create a client while creating a new appointment
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > div > vm-manage-client-button > a > span")).click();
			Sleep(2);
			driver.findElement(By.name("firstName")).sendKeys("New Appointment");
			driver.findElement(By.name("lastName")).sendKeys("New Customer");
			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button")).click();

			Sleep(5);

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(5);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));
			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button > span"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("serviceName").contains("Manicure") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("customerName").contains("New Appointment New Customer") != true)
				throw new Exception("The service was not set in the appointment");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_9() // Create a client while editing an appointment
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(1);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));
			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));
			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			driver.get("https://my-staging.vaniday.com.au/");
			Sleep(5);
			// REPOPENING THE NEWLY CREATED APPOINTMENT HERE
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved")).click();
			
			Sleep(3);
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div.manage-calendar-modal__dialog__content__body__column-left > form > vm-manage-manual-appointment-context > div > div.client-holder > div > div.client-modal-actions.pull-right.ng-scope > a")).click();
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div.manage-calendar-modal__dialog__content__body__column-left > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > div > vm-manage-client-button > a > span")).click();
			Sleep(2);
			driver.findElement(By.name("firstName")).sendKeys("Edit Appointment");
			driver.findElement(By.name("lastName")).sendKeys("New Customer");
			
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-client-modal.ng-scope.in > div > div > form > div.modal-footer > vm-button-loading > button > span")).click();
			Sleep(4);
		//	driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div.manage-calendar-modal__dialog__content__body__column-left > div > button > span")).click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("serviceName").contains("Manicure") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("customerName").contains("Edit Appointment New Customer") != true)
				throw new Exception("The Customer was not set in the appointment");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_10() // Delete an Existing appointment with single booking
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(1);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));
			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			driver.get("https://my-staging.vaniday.com.au/");
			Sleep(5);
			// REPOPENING THE NEWLY CREATED APPOINTMENT HERE
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved")).click();
			
			Sleep(3);
			 driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div.manage-calendar-modal__dialog__content__body__column-left > div > a > span")).click();
			 Sleep(3);
			 driver.findElement(By.cssSelector("body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary")).click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if(Docs.length()>0)
				throw new Exception("The Appointment was not deleted");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_11() // Delete an existing appointment with multiple bookings
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(78) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(3);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");
			
			new Select(driver.findElement(By.name("time"))).selectByVisibleText("08:00 PM");
			
			//ADDING SECOND BOOKING AFTER THIS
			
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements = driver.findElements(By.name("service"));
			ServiceElements.get(1).sendKeys("Women's haircut");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
			driver.findElement(By.cssSelector("#note")).click();
			
			
			listItems = driver.findElements(By.name("time"));
			new Select(listItems.get(1)).selectByVisibleText("08:15 PM");

			
			driver.findElement(By.id("note")).sendKeys(AppointmentID);

			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			driver.get("https://my-staging.vaniday.com.au/");
			Sleep(5);
			// REPOPENING THE NEWLY CREATED APPOINTMENT HERE
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved")).click();
			
			Sleep(3);
			 driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div.manage-calendar-modal__dialog__content__body__column-left > div > a > span")).click();
			 Sleep(3);
			 driver.findElement(By.cssSelector("body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary")).click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if(Docs.length()>0)
				throw new Exception("The Appointment was not deleted");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_12() // ADD A New BOOKING TO AN EXISTING APPOINTMENT
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(1);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			driver.get("https://my-staging.vaniday.com.au/");

			Sleep(5);
// REPOPENING THE NEWLY CREATED APPOINTMENT HERE
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved")).click();
			
			Sleep(3);
			
driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements = driver.findElements(By.name("service"));
			ServiceElements.get(1).sendKeys("Women's haircut");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
				  			Sleep(4);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if(Docs.length()!=2)
				throw new Exception ("The second booking was not added to the appointment");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Calender_Appointment_TestCase_13() // Create a new appointment through marketplace
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			SignInMarketPlace();
			
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__content.is-first.is-desktop.hide-on-med-and-down")).click();
			
			Sleep(3);
			driver.findElement(By.cssSelector("#section-salon > div.vaniday-sections-salon__main-info-wrapper > div.vaniday-sections-salon__main-info-wrapper__right-side > div.vaniday-sections-salon__book-now-box > vaniday-salon-block-book-now > div > a > span.cart-text")).click();

			Sleep(15);
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
			
			

			Sleep(2);
			
			List<WebElement> TimeItems = driver.findElements(By.className("time"));
			
			TimeItems.get(4).click();
			String Time=TimeItems.get(2).getText();
			
			Sleep(3);
			
			
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > div > div:nth-child(1) > vaniday-payment-tabs > ul > li:nth-child(2) > a > span > strong")).click();
			Sleep(3);

			driver.findElement(By.name("message")).sendKeys(AppointmentID);
			Sleep(3);
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-recap-booking-bar.ng-scope > vaniday-booking-recap-sidebar > aside > div:nth-child(4) > div > a")).click(); 
			// Verifying the appointment here.
			Sleep(10);
			JSONArray Docs = SearchEventByNote(AppointmentID,new SimpleDateFormat("yyyy-MM-dd").format(FinalDate).toString() );
			
			if(Docs.length()==0)
				throw new Exception("The booking was not created");
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_14() // Create a new appointment through marketplace with multiple bookings
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			SignInMarketPlace();
			
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__content.is-first.is-desktop.hide-on-med-and-down")).click();
			Sleep(2);
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane:nth-child(2) > v-pane-header > div > strong")).click();
			Sleep(3);
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane:nth-child(2) > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__misc-action > div")).click();
			Sleep(3);
			driver.findElement(By.cssSelector("#section-salon > div.vaniday-sections-salon__main-info-wrapper > div.vaniday-sections-salon__main-info-wrapper__right-side > div.vaniday-sections-salon__book-now-box > vaniday-salon-block-book-now > div > a > span.cart-text")).click();

			Sleep(10);
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
			
			

			Sleep(2);
			
			List<WebElement> TimeItems = driver.findElements(By.className("time"));
			
			TimeItems.get(4).click();
			String Time=TimeItems.get(2).getText();
			
			Sleep(3);
			
			
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-recap-booking-bar.ng-scope > vaniday-booking-recap-sidebar > aside > div.vaniday-booking-recap-sidebar__item.ng-scope > div > a > span.cart-text")).click();
			Sleep(7);
 			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > div > div:nth-child(1) > vaniday-payment-tabs > ul > li:nth-child(2) > a")).click();
			Sleep(7);
			driver.findElement(By.name("message")).sendKeys(AppointmentID);
			Sleep(7);
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-recap-booking-bar.ng-scope > vaniday-booking-recap-sidebar > aside > div:nth-child(4) > div > a")).click(); 
			// Verifying the appointment here.
			Sleep(10);
			JSONArray Docs = SearchEventByNote(AppointmentID,new SimpleDateFormat("yyyy-MM-dd").format(FinalDate).toString() );
			if(Docs.length()!=2)
				throw new Exception("The booking was not created");
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_15() // Create a new appointment through marketplace for timings where the time is blocked
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
 
			Calendar cal = Calendar.getInstance();  
		    cal.setTime(new Date());  
		    cal.add(Calendar.DATE, 3); 
		    Date date = cal.getTime();
		    
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/calendar?view=day&date="+new SimpleDateFormat("yyyy-MM-dd").format(date).toString());
			  

			driver.findElement(By.cssSelector("body > ui-view > div > div.toolbar__calendarbar.vw-toolbar__content.is-expanded > div > section > vm-add-new-appointment-button > div > a:nth-child(2)")).click();
			Sleep(3);
			
		  
		   
			Sleep(3); 

			new Select(driver.findElement(By.id("professional"))).selectByVisibleText("Eduarda");
			new Select(driver.findElement(By.id("time"))).selectByVisibleText("12:00 PM");

			new Select(driver.findElement(By.id("duration"))).selectByVisibleText("4h00m");
			driver.findElement(By.id("reason")).sendKeys(AppointmentID);

			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__footer.modal-footer > button")).click();
			
			SignInMarketPlace();
			
			//ExecuteBlockTimeQuery("","","");
			
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__content.is-first.is-desktop.hide-on-med-and-down")).click();
			Sleep(2);
			driver.findElement(By.cssSelector("#section-salon > div.vaniday-sections-salon__main-info-wrapper > div.vaniday-sections-salon__main-info-wrapper__right-side > div.vaniday-sections-salon__book-now-box > vaniday-salon-block-book-now > div > a.btn.btn-booking.btn-block.btn-size-lg.book-now-button.has-bookings > span.cart-text")).click();
			Sleep(15);
			WebElement Date = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__monthly-carousel > div.vaniday-timepicker__monthly-carousel__carousel.ng-scope > ul > li:nth-child(1) > div:nth-child(4) > div.day.ng-binding"));
			Date.click();
			String DateValue = Date.getText();
			
			String Month = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[0].toString();
			String Year = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[1].toString().replace(",", "");

			Date FinalDate = new SimpleDateFormat("MMMMMMMMMM").parse(Month);
			FinalDate.setDate(Integer.parseInt(DateValue));
			FinalDate.setYear(Integer.parseInt(Year)-1900);
 
			Sleep(2);
			
			List<WebElement> TimeItems = driver.findElements(By.className("time"));
			
			for(WebElement Time: TimeItems)
			{
				if(Time.getText().contains("12:00 PM") || Time.getText().contains("02:00 PM")|| Time.getText().contains("03:15 PM"))
					throw new Exception ("The timeslots for blocked times are available");
			}
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_16() // Create an appointment through the marketplace for a time slot where appointment already exists which was created using MyVaniday
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();
		
			Calendar cal = Calendar.getInstance();  
		    cal.setTime(new Date());  
		    cal.add(Calendar.DATE, 3); 
		    Date date = cal.getTime();
		    
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/calendar?view=day&date="+new SimpleDateFormat("yyyy-MM-dd").format(date).toString());
			  
 

			driver.findElement(By.cssSelector(
					"body > ui-view > div > div.toolbar__calendarbar.vw-toolbar__content.is-expanded > div > section > vm-add-new-appointment-button > div > a:nth-child(1)"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(5);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

//		 
			Sleep(3); 
			
			driver.findElement(By.name("price")).sendKeys("50");
			new Select(driver.findElement(By.id("time"))).selectByVisibleText("04:15 PM");

			new Select(driver.findElement(By.id("duration"))).selectByVisibleText("2h00m");
			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			new Select(driver.findElement(By.id("professional"))).selectByVisibleText("Eduarda");

			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			SignInMarketPlace();
			
			//ExecuteBlockTimeQuery("","","");
			
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__content.is-first.is-desktop.hide-on-med-and-down")).click();
			Sleep(2);
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(2) > div > div.salon-block-service-list__item__wrapper__add-action.hide-on-med-and-down > a > span > span > i")).click();
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
 
			Sleep(2);
			
			List<WebElement> TimeItems = driver.findElements(By.className("time"));
 
			
			for(WebElement Time: TimeItems )
			{
				if(Time.getText().contains("04:30 PM") || Time.getText().contains("05:00 PM")|| Time.getText().contains("05:15 PM"))
					throw new Exception ("The timeslots for blocked times are available");
			}			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_17() // Create an appointment through the marketplace for a time slot where appointment already exists which was created using the Marketplace
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			SignInMarketPlace();
			
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__content.is-first.is-desktop.hide-on-med-and-down")).click();
			
			Sleep(3);
			driver.findElement(By.cssSelector("#section-salon > div.vaniday-sections-salon__main-info-wrapper > div.vaniday-sections-salon__main-info-wrapper__right-side > div.vaniday-sections-salon__book-now-box > vaniday-salon-block-book-now > div > a > span.cart-text")).click();

			Sleep(15);
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
			
			

			Sleep(2);
			
			List<WebElement> TimeItems = driver.findElements(By.className("time"));
			
	 
			
			for(WebElement Time: TimeItems)
			{
				if(Time.getText().contains("07:00 PM"))
					Time.click();
			}
			Sleep(10);
			
			
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > div > div:nth-child(1) > vaniday-payment-tabs > ul > li:nth-child(2) > a > span > strong")).click();
			Sleep(3);

			driver.findElement(By.name("message")).sendKeys(AppointmentID);
			Sleep(3);
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-recap-booking-bar.ng-scope > vaniday-booking-recap-sidebar > aside > div:nth-child(4) > div > a")).click(); 
			// Verifying the appointment here.
			Sleep(15);
			JSONArray Docs = SearchEventByNote(AppointmentID,new SimpleDateFormat("yyyy-MM-dd").format(FinalDate).toString() );
			
			if(Docs.length()==0)
				throw new Exception("The booking was not created");
			
			//CREATING THE SAME APPOINTMENT AGAIN FOR THE SAME TIME
			
			driver.get("http://www-dev.vaniday.com.au/salons/vanidateau/");
			
			//ExecuteBlockTimeQuery("","","");
			Sleep(2);
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__content.is-first.is-desktop.hide-on-med-and-down")).click();
			Sleep(2);
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(2) > div > div.salon-block-service-list__item__wrapper__add-action.hide-on-med-and-down > a > span > span > i")).click();
			Sleep(3);
			driver.findElement(By.cssSelector("#section-salon > div.vaniday-sections-salon__main-info-wrapper > div.vaniday-sections-salon__main-info-wrapper__right-side > div.vaniday-sections-salon__book-now-box > vaniday-salon-block-book-now > div > a > span.cart-text")).click();

			Sleep(5);
			WebElement Date2 = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__monthly-carousel > div.vaniday-timepicker__monthly-carousel__carousel.ng-scope > ul > li:nth-child(1) > div:nth-child(4) > div.day.ng-binding"));
			Date2.click();
			String DateValue2 = Date2.getText();
			
			String Month2 = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[0].toString();
			String Year2 = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[1].toString().replace(",", "");

			Date FinalDate2 = new SimpleDateFormat("MMMMMMMMMM").parse(Month2);
			FinalDate2.setDate(Integer.parseInt(DateValue2));
			FinalDate2.setYear(Integer.parseInt(Year2)-1900);
 
			Sleep(2);
			
			List<WebElement> TimeItems2 = driver.findElements(By.className("time"));
			
			for(WebElement Time: TimeItems2)
			{
				if(Time.getText().contains("07:00 PM") || Time.getText().contains("08:00 PM")|| Time.getText().contains("08:15 PM"))
					throw new Exception ("The timeslots for blocked times are available");
			}
			 
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_18() // Create and appointment using the link under date picker
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

 

			driver.findElement(By.cssSelector(
					"body > ui-view > div > div.toolbar__calendarbar.vw-toolbar__content.is-expanded > div > section > vm-add-new-appointment-button > div > a:nth-child(1)"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(1);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("serviceName").contains("Manicure") != true)
				throw new Exception("The service was not set in the appointment");
			if (Docs.getJSONObject(0).getJSONObject("appointment").getString("customerName").contains("Julia Roberts") != true)
				throw new Exception("The service was not set in the appointment");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_19() // Remove multiple bookings from the an appointment with multiple bookings
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(3);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");
			//ADD ANOTHER BOOKING
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements = driver.findElements(By.name("service"));
			ServiceElements.get(1).sendKeys("Women's haircut");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);

			
			//ADD ANOTHER BOOKING

			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements2 = driver.findElements(By.name("service"));
			ServiceElements2.get(2).sendKeys("Women's haircut");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
			//ADD ANOTHER BOOKING

			
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements3 = driver.findElements(By.name("service"));
			ServiceElements3.get(3).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form:nth-child(5) > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));
			listItems.get(0).click();
			
		

			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
		 
			Sleep(5);
			

			//OPENING THE NEWLY CREATED APPOINTMENT
driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved")).click();
			
			Sleep(3);
			
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div.manage-calendar-modal__dialog__content__body__column-left > form > ng-form:nth-child(2) > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.add-new-appointment-form__footer > div > div > a > span")).click();
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div.manage-calendar-modal__dialog__content__body__column-left > form > ng-form:nth-child(3) > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.add-new-appointment-form__footer > div > div > a > span")).click();

			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
		 
			Sleep(5);
		
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if(Docs.length()!=2)
				throw new Exception("The bookings were not removed successfully");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_20() // Delete an Existing appointment with single booking
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(5);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			driver.get("https://my-staging.vaniday.com.au/");

			Sleep(5);
			// REPOPENING THE NEWLY CREATED APPOINTMENT HERE
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved")).click();
			Sleep(5);

			listItems = driver.findElements(By.name("professional"));
			new Select(listItems.get(0)).selectByVisibleText("Camila");
			
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (FindProfessionalByID(Docs.getJSONObject(0).getString("professionalId")).getString("firstName").contains("Camila") != true)
				throw new Exception("The Professional was not set in the appointment");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Calender_Appointment_TestCase_21() // Edit the Professional of an existing appointment with multiple bookings
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(3);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");
			//ADD ANOTHER BOOKING
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements = driver.findElements(By.name("service"));
			ServiceElements.get(1).sendKeys("Women's haircut");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);

			
			//ADD ANOTHER BOOKING

			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements2 = driver.findElements(By.name("service"));
			ServiceElements2.get(2).sendKeys("Women's haircut");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
			//ADD ANOTHER BOOKING

			
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements3 = driver.findElements(By.name("service"));
			ServiceElements3.get(3).sendKeys("Manicure");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form:nth-child(5) > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));
			listItems.get(0).click();
			
		

			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
		 
			Sleep(5);
			

			//OPENING THE NEWLY CREATED APPOINTMENT
driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved")).click();
			
			Sleep(3);
			
			listItems = driver.findElements(By.name("professional"));
			new Select(listItems.get(0)).selectByVisibleText("Camila");
			listItems = driver.findElements(By.name("professional"));
			new Select(listItems.get(1)).selectByVisibleText("Camila");
			
			listItems = driver.findElements(By.name("professional"));
			new Select(listItems.get(2)).selectByVisibleText("Camila");
			
			listItems = driver.findElements(By.name("professional"));
			new Select(listItems.get(3)).selectByVisibleText("Camila");
			 
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
		 
			Sleep(5);
		
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (FindProfessionalByID(Docs.getJSONObject(0).getString("professionalId")).getString("firstName").contains("Camila") != true)
				throw new Exception("The Professional was not set in the appointment");
			if (FindProfessionalByID(Docs.getJSONObject(1).getString("professionalId")).getString("firstName").contains("Camila") != true)
				throw new Exception("The Professional was not set in the appointment");
			if (FindProfessionalByID(Docs.getJSONObject(2).getString("professionalId")).getString("firstName").contains("Camila") != true)
				throw new Exception("The Professional was not set in the appointment");
			if (FindProfessionalByID(Docs.getJSONObject(3).getString("professionalId")).getString("firstName").contains("Camila") != true)
				throw new Exception("The Professional was not set in the appointment");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Calender_Appointment_TestCase_22() // Edit the Time and duration of an appointment with single booking
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(1);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			driver.get("https://my-staging.vaniday.com.au/");

			Sleep(5);
// REPOPENING THE NEWLY CREATED APPOINTMENT HERE
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved")).click();
			
			Sleep(3);
	 
			new Select(driver.findElement(By.name("time"))).selectByVisibleText("08:00 PM");
			new Select(driver.findElement(By.name("duration"))).selectByVisibleText("2h00m");

			
			
				  			Sleep(4);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getString("startAt").contains("20:00") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(0).getString("endAt").contains("22:00") != true)
				throw new Exception("The Time was not set in the appointment");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_23() // Edit the time and duration of an existing appointment with multiple bookings
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(3);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");
			//ADD ANOTHER BOOKING
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements = driver.findElements(By.name("service"));
			ServiceElements.get(1).sendKeys("Women's haircut");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a > strong "));
			listItems.get(0).click();
			
			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);

			
			//ADD ANOTHER BOOKING

			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements2 = driver.findElements(By.name("service"));
			ServiceElements2.get(2).sendKeys("Women's haircut");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form:nth-child(4) > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
			//ADD ANOTHER BOOKING

			
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements3 = driver.findElements(By.name("service"));
			ServiceElements3.get(3).sendKeys("Manicure");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form:nth-child(5) > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));
			listItems.get(0).click();
			
		

			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
		 
			Sleep(5);
			

			//OPENING THE NEWLY CREATED APPOINTMENT
driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved")).click();
			
			Sleep(3);
			
			listItems = driver.findElements(By.name("time"));
			new Select(listItems.get(0)).selectByVisibleText("01:00 PM");
			
			listItems = driver.findElements(By.name("time"));
			new Select(listItems.get(2)).selectByVisibleText("04:00 PM");
			
			listItems = driver.findElements(By.name("time"));
			new Select(listItems.get(3)).selectByVisibleText("11:00 AM");
			
			listItems = driver.findElements(By.name("duration"));
			new Select(listItems.get(0)).selectByVisibleText("0h15m");
			
			listItems = driver.findElements(By.name("duration"));
			new Select(listItems.get(2)).selectByVisibleText("3h45m");
			
			listItems = driver.findElements(By.name("duration"));
			new Select(listItems.get(3)).selectByVisibleText("0h45m");
			 
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
		 
			Sleep(5);
		
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getString("startAt").contains("13:00") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(0).getString("endAt").contains("13:15") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(2).getString("startAt").contains("16:00") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(2).getString("endAt").contains("19:45") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(3).getString("startAt").contains("11:00") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(3).getString("endAt").contains("11:45") != true)
				throw new Exception("The Time was not set in the appointment");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_24() // Create multiple appointments for the same professional for the same time
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(3);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");
			//ADD ANOTHER BOOKING
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements = driver.findElements(By.name("service"));
			ServiceElements.get(1).sendKeys("Women's haircut");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form.ng-scope.ng-invalid.ng-invalid-required.ng-dirty.ng-valid-parse > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li:nth-child(1) > a"));
			listItems.get(0).click();
			
			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);

			
			//ADD ANOTHER BOOKING

			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements2 = driver.findElements(By.name("service"));
			ServiceElements2.get(2).sendKeys("Manicure");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form:nth-child(4) > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));
			listItems.get(0).click();
			
			//ADD ANOTHER BOOKING

			
			driver.findElement(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-add-another-booking-button > a > span > span")).click();
			
			List<WebElement> ServiceElements3 = driver.findElements(By.name("service"));
			ServiceElements3.get(3).sendKeys("Manicure");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form:nth-child(5) > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));
			listItems.get(0).click();
			
		

			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
		 
			Sleep(5);
			

			//OPENING THE NEWLY CREATED APPOINTMENT
			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved")).click();
			
			Sleep(3);
			
			listItems = driver.findElements(By.name("time"));
			new Select(listItems.get(0)).selectByVisibleText("01:00 PM");
			
			listItems = driver.findElements(By.name("time"));
			new Select(listItems.get(2)).selectByVisibleText("01:00 PM");
			
			listItems = driver.findElements(By.name("time"));
			new Select(listItems.get(3)).selectByVisibleText("01:00 PM");
			
			listItems = driver.findElements(By.name("duration"));
			new Select(listItems.get(0)).selectByVisibleText("0h45m");
			
			listItems = driver.findElements(By.name("duration"));
			new Select(listItems.get(2)).selectByVisibleText("0h45m");
			
			listItems = driver.findElements(By.name("duration"));
			new Select(listItems.get(3)).selectByVisibleText("0h45m");
			 
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
		 
			Sleep(5);
		
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getString("startAt").contains("13:00") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(0).getString("endAt").contains("13:45") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(2).getString("startAt").contains("13:00") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(2).getString("endAt").contains("13:45") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(3).getString("startAt").contains("13:00") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(3).getString("endAt").contains("13:45") != true)
				throw new Exception("The Time was not set in the appointment");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_25() // Change the time of an existing appointment (Created using MyVaniday) by dragging it around but the Duration and the Professional remains the same
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(5);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(5);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			driver.get("https://my-staging.vaniday.com.au/");

			Sleep(5);
			// REPOPENING THE NEWLY CREATED APPOINTMENT HERE
			WebElement From =driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved"));
			WebElement To = driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(51) > td:nth-child(2)"));
			
			Actions builder = new Actions(driver);
			
			builder.moveToElement(From, 0,35).dragAndDropBy(From, 0,100).release(To).build().perform();
			 
 			
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getString("startAt").contains("18:30") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(0).getString("endAt").contains("19:15") != true)
				throw new Exception("The Time was not set in the appointment");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_26() // Change the duration of an existing appointment (Created using MyVaniday) by click the lower edge of the appointment and dragging it down
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(1);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			driver.get("https://my-staging.vaniday.com.au/");

			Sleep(5);
			// REPOPENING THE NEWLY CREATED APPOINTMENT HERE
			WebElement From =driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved"));
			WebElement To = driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(51) > td:nth-child(2)"));
			
			Actions builder = new Actions(driver);
			
			//builder.dragAndDrop(From, To).build().perform();
			builder.moveToElement(From, 0,100).dragAndDropBy(From, 0,100).build().perform();
			 
			//UNABLE TO KEEP THE PROFESSIONAL THE SAME USING SELENIUM FOR SOME REASON. TWEAKING REQUIRED
			
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (Docs.getJSONObject(0).getString("startAt").contains("12:15") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(0).getString("endAt").contains("13:00") != true)
				throw new Exception("The Time was not set in the appointment");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_27() // Change the Professional of an existing appointment (Created using MyVaniday) but the Time and duration remains the same
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(1);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			driver.get("https://my-staging.vaniday.com.au/");

			Sleep(5);
			// REPOPENING THE NEWLY CREATED APPOINTMENT HERE
			WebElement From =driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved"));
			WebElement To = driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(73) > td:nth-child(2)"));
			
			Actions builder = new Actions(driver);
			
			builder.dragAndDrop(From, To).build().perform();
			 
			//UNABLE TO DRAG AND DROP BY CLICKING ON THE EDGE USING SELENIUM FOR SOME REASON. TWEAKING REQUIRED
			
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (FindProfessionalByID(Docs.getJSONObject(0).getString("professionalId")).getString("firstName").contains("Camila") != true)
				throw new Exception("The Professional was not set in the appointment");
			if (Docs.getJSONObject(0).getString("startAt").contains("17:45") != true)
				throw new Exception("The Time was not set in the appointment");
			if (Docs.getJSONObject(0).getString("endAt").contains("18:30") != true)
				throw new Exception("The Time was not set in the appointment");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_Appointment_TestCase_28() // Change both the Professional AND the Time of an existing appointment (Created using MyVaniday) by dragging it around
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			WebElement x = driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

			Actions builder1 = new Actions(driver);
			builder1.moveToElement(x, 0, 0).click().perform();

			driver.findElement(By.cssSelector(
					"div.popover-content > vm-calendar-popover > div > a.btn.btn-primary.btn-appointment.with-icon.ng-scope"))
					.click();
			Sleep(2);
			driver.findElement(By.name("customerSearch")).sendKeys("Julia Roberts");

			Sleep(1);
			List<WebElement> listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > vm-manage-manual-appointment-context > div > div.client-holder > vm-client-autocomplete > ng-form > div.form-group.required.customer-autocomplete > ul > li > a > i"));

			listItems.get(0).click();

			driver.findElement(By.name("service")).sendKeys("Manicure");
			Sleep(1);
			listItems = driver.findElements(By.cssSelector("body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > form > ng-form > vm-manage-manual-appointment-booking-item > div > div:nth-child(1) > div.form-group.add-new-appointment-form__main-form-group > div > div.col-lg-12 > div > div > ul > li > a"));

			listItems.get(0).click();

			driver.findElement(By.name("price")).sendKeys("50");

			driver.findElement(By.cssSelector("#note")).click();
			driver.findElement(By.id("note")).sendKeys(AppointmentID);
			driver.findElement(By.cssSelector(
					"body > div.manage-calendar-modal.modal.fade.ng-scope.in > div > div > div.manage-calendar-modal__dialog__content__body > div > div > button"))
					.click();
			Sleep(5);
			driver.get("https://my-staging.vaniday.com.au/");

			Sleep(5);
			// Dragging the appointment here
			WebElement From =driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(2) > div > div:nth-child(2) > a.fc-time-grid-event.fc-v-event.fc-event.fc-start.fc-end.fc-draggable.fc-resizable.duration-45.booking__manual--approved"));
			WebElement To = driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(54n) > td:nth-child(2)"));
			
			Actions builder = new Actions(driver);
			
			builder.dragAndDrop(From, To).build().perform();
			 
			//UNABLE TO KEEP THE PROFESSIONAL THE SAME USING SELENIUM FOR SOME REASON. TWEAKING REQUIRED
			
			Sleep(5);
			JSONArray Docs = SearchEventByNote(AppointmentID, CurrentDate);

			if (FindProfessionalByID(Docs.getJSONObject(0).getString("professionalId")).getString("firstName").contains("Camila") != true)
				throw new Exception("The Professional was not set in the appointment");
			if (Docs.getJSONObject(0).getString("startAt").contains("4:30") != true)
				throw new Exception("The Time was not set in the appointment");
			

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Calender_Appointment_TestCase_30() // Change the booking status of an appointment (Created via Marketplace) for a future date from Pending to Approved
	{
		try {
			String AppointmentID = new Object() {
			}.getClass().getEnclosingMethod().getName();

			SignInMarketPlace();
			
			driver.findElement(By.cssSelector("#servicesListAccordion > v-pane.vaniday-salon-block-services-list__accordion__pane.ng-scope.ng-isolate-scope.is-expanded > v-pane-content > div > ul > li:nth-child(1) > div > div.salon-block-service-list__item__wrapper__content.is-first.is-desktop.hide-on-med-and-down")).click();
			
			Sleep(3);
			driver.findElement(By.cssSelector("#section-salon > div.vaniday-sections-salon__main-info-wrapper > div.vaniday-sections-salon__main-info-wrapper__right-side > div.vaniday-sections-salon__book-now-box > vaniday-salon-block-book-now > div > a > span.cart-text")).click();

			Sleep(20);
			WebElement Date = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__monthly-carousel > div.vaniday-timepicker__monthly-carousel__carousel.ng-scope > ul > li:nth-child(1) > div:nth-child(3) > div.day.ng-binding"));
			Date.click();
			String DateValue = Date.getText();
			
			String Month = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[0].toString();
			String Year = driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > vaniday-time-picker > article > div.vaniday-timepicker__wrapper-header.ng-scope > div > div.vaniday-timepicker__month.ng-binding")).getText().split(" ")[1].toString().replace(",", "");

			Date FinalDate = new SimpleDateFormat("MMMMMMMMMM").parse(Month);
			FinalDate.setDate(Integer.parseInt(DateValue));
			FinalDate.setYear(Integer.parseInt(Year)-1900);

			Sleep(2);
			
			List<WebElement> TimeItems = driver.findElements(By.className("time"));

			for(WebElement Time: TimeItems)
			{
				if(Time.getText().contains("11:30 AM"))
					Time.click();
			}
			Sleep(3);
			
			Sleep(3);
			
			
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-sections-checkout-view-wrapper.ng-scope > div > div > div > div:nth-child(1) > vaniday-payment-tabs > ul > li:nth-child(2) > a > span > strong")).click();
			Sleep(3);

			driver.findElement(By.name("message")).sendKeys(AppointmentID);
			Sleep(3);
			
			driver.findElement(By.cssSelector("#app > div.ng-scope.main.no-fixed-main > section > div.vaniday-recap-booking-bar.ng-scope > vaniday-booking-recap-sidebar > aside > div:nth-child(4) > div > a")).click(); 
			// Verifying the appointment here.
			Sleep(3);
			JSONArray Docs = SearchEventByNote(AppointmentID,new SimpleDateFormat("yyyy-MM-dd").format(FinalDate).toString() );
			
			if(Docs.length()==0)
				throw new Exception("The booking was not created");
			Sleep(3);
			//OPENING THE NEWLY CREATED APPOINTMENT HERE
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/calendar?view=day&date="+new SimpleDateFormat("yyyy-MM-dd").format(FinalDate).toString() );
			Sleep(10);

			driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-content-skeleton > table > tbody > tr > td:nth-child(4) > div > div:nth-child(2) > a > div.fc-content > div.title")).click();
			Sleep(5);
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-automatic-booking-modal.ng-scope.in > div > div > div.modal-header > div > div.status-combo.is-pending > a > span.status-combo__active-status__action-change-status.ng-scope > span")).click();
			Sleep(2);
			driver.findElement(By.cssSelector("body > div.modal.fade.manage-automatic-booking-modal.ng-scope.in > div > div > div.modal-header > div > div.status-combo.is-pending > ul > li.status-combo__list__item.ng-scope.is-approved > a")).click();

			Docs = SearchEventByNote(AppointmentID,new SimpleDateFormat("yyyy-MM-dd").format(FinalDate).toString() );
			
			if(Docs.getJSONObject(0).getJSONObject("booking").get("status").toString().contains("confirmed_by_professional")!=true)
				throw new Exception("The status of the booking was not changed");
			TestPassed();

			
		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Calender_BlockedTime_TestCase_1()
	{
		try {
		String AppointmentID = new Object() {
		}.getClass().getEnclosingMethod().getName();

		WebElement x = driver.findElement(By.cssSelector(
				"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(68) > td:nth-child(2)"));

		WebElement y = driver.findElement(By.cssSelector(
				"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(68) > td:nth-child(2)"));

		Actions builder = new Actions(driver);
		builder.clickAndHold(x).moveToElement(y).release(y).build().perform();
		
		driver.findElement(By.cssSelector("div.popover-content > vm-calendar-popover > div > a.btn.btn-default.btn-block-time.with-icon")).click();
		
		JSONArray Docs = SearchEventByNote("16:45-17:00-Camila", CurrentDate);
		
		if(Docs.length()==0)
			throw new Exception("Time was not blocked");
		
		TestPassed();
		
		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_BlockedTime_TestCase_2()
	{
		try {
		String AppointmentID = new Object() {
		}.getClass().getEnclosingMethod().getName();

		WebElement x = driver.findElement(By.cssSelector(
				"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(72) > td:nth-child(2)"));

		WebElement y = driver.findElement(By.cssSelector(
				"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(82) > td:nth-child(2)"));

		Actions builder = new Actions(driver);
		builder.clickAndHold(x).moveToElement(y).release(y).build().perform();
		
		driver.findElement(By.cssSelector("div.popover-content > vm-calendar-popover > div > a.btn.btn-default.btn-block-time.with-icon")).click();
		
		JSONArray Docs = SearchEventByNote("17:45-20:30-Camila", CurrentDate);
		
		if(Docs.length()==0)
			throw new Exception("Time was not blocked");
		
		TestPassed();
		
		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Calender_BlockedTime_TestCase_3()
	{
		try {
		String AppointmentID = new Object() {
		}.getClass().getEnclosingMethod().getName();

		new Select(driver.findElement(By.cssSelector("body > ui-view > ui-view > section > vm-calendar-navbar > div > div > div.misc-calendar-controls > div.hidden-xs.hidden-sm.col-md-3.form-group.text-right > div > select"))).selectByVisibleText("Camila");

		Sleep(5);
		
		WebElement x = driver.findElement(By.cssSelector(
				"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(63) > td:nth-child(2)"));

		WebElement y = driver.findElement(By.cssSelector(
				"body > ui-view > ui-view > section > div > div > div > table > tbody > tr > td > div > div > div.fc-slats > table > tbody > tr:nth-child(82) > td:nth-child(2)"));

		Actions builder = new Actions(driver);
		builder.clickAndHold(x).moveToElement(y).release(y).build().perform();
		
		driver.findElement(By.cssSelector("div.popover-content > vm-calendar-popover > div > a.btn.btn-default.btn-block-time.with-icon")).click();
		
		JSONArray Docs = SearchEventByNote("17:45-20:30-Camila", CurrentDate);
		
		if(Docs.length()==0)
			throw new Exception("Time was not blocked");
		
		TestPassed();
		
		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
}
