import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SaloonTests extends myCommon {

	SaloonTests(WebDriver mydriver, String myAuthToken) {

		driver = mydriver;
		AuthToken = myAuthToken;

		ResetSaloon(AuthToken);
		driver.manage().window().maximize();

		  ExecuteAllTests(driver, SaloonTests.class);

		//ExecuteSpecificTest("Saloon_Service_TestCase_6", SaloonTests.class);
		// ExecuteSimilarTests("Inventory_Product_TestCase_15",SaloonTests.class,driver);
		PrintTestResults();
		ResetSaloon(AuthToken);
	}

	public void Saloon_Staff_TestCase_1() // Add new staff without specifying the First Name
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("lastName")).sendKeys(StaffName);
			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Male");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc != null)
				throw new Exception("The Professional was created where it should not have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_2() // Add new staff without specifying the Profession
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Male");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc != null)
				throw new Exception("The Professional was created where it should not have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_3() // Add new staff without specifying the Gender
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc != null)
				throw new Exception("The Professional was created where it should not have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_4() // Add a new staff member without specifying at least one service
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc != null)
				throw new Exception("The Professional was created where it should not have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_5() // Add a new staff member without specifying at least one working day
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc != null)
				throw new Exception("The Professional was created where it should not have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_6() // Add a new staff member with minimum requirements
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			Sleep(5);
			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc == null)
				throw new Exception("The Professional was not created where it should have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_7() // Add a new staff member with all possible option
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			driver.findElement(By.id("description")).sendKeys("Add a new staff member with all possible option");

			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();
			Sleep(5);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			Sleep(5);
			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc == null)
				throw new Exception("The Professional was not created where it should have");
			if (Doc.getString("lastName").contains("Testing") != true)
				throw new Exception("The last name was not set");

			if (Doc.getString("profession").contains("Barber") != true)
				throw new Exception("The profession was not set");
			if (Doc.getString("gender").contains("female") != true)
				throw new Exception("The gender was not set");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_8() // Delete an existing staff member
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			driver.findElement(By.id("description")).sendKeys("Delete and existing member");

			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();

			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			Sleep(5);
			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc == null)
				throw new Exception("The Professional was not created where it should have");
			Sleep(10);

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");

			Sleep(3);
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(3);

			List<WebElement> elements = driver.findElements(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > vm-staff-table > table > tbody > tr"));

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > vm-staff-table > table > tbody > tr:nth-child("
							+ elements.size() + ") > td.table-actions > a:nth-child(2) > i"))
					.click();

			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary"))
					.click();
			Sleep(5);

			JSONObject Doc2 = FindProfessionalByID(StaffName);

			if (Doc2 != null)
				throw new Exception("The Professional was not deleted where it should have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_9() // Create a staff member with a name that already exists
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			driver.findElement(By.id("description")).sendKeys("Create a staff member with a name that already exists");

			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();
			Sleep(5);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			Sleep(5);
			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc == null)
				throw new Exception("The Professional was not created where it should have");

			Sleep(5);

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing2");
			driver.findElement(By.id("description")).sendKeys("Create a staff member with a name that already exists");

			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();
			Sleep(5);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			Sleep(5);
			JSONObject Doc2 = FindProfessionalByID(StaffName);

			if (Doc2 == null)
				throw new Exception("The Professional was not created where it should have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_10() // Edit all the details of the Staff member
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			driver.findElement(By.id("description")).sendKeys("Add a new staff member with all possible option");

			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();
			Sleep(5);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			Sleep(5);
			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc == null)
				throw new Exception("The Professional was not created where it should have");
			if (Doc.getString("lastName").contains("Testing") != true)
				throw new Exception("The last name was not set");

			if (Doc.getString("profession").contains("Barber") != true)
				throw new Exception("The profession was not set");
			if (Doc.getString("gender").contains("female") != true)
				throw new Exception("The gender was not set");

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/update/" + Doc.getString("id"));
			Sleep(3);

			driver.findElement(By.name("lastName")).sendKeys("2");
			driver.findElement(By.id("description")).sendKeys("2");

			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Others");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Male");

			Sleep(5);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			Sleep(5);
			JSONObject Doc2 = FindProfessionalByID(StaffName);

			if (Doc2 == null)
				throw new Exception("The Professional was not created where it should have");
			if (Doc2.getString("lastName").contains("Testing2") != true)
				throw new Exception("The last name was not set");

			if (Doc2.getString("profession").contains("Others") != true)
				throw new Exception("The profession was not set");
			if (Doc2.getString("gender").contains("male") != true)
				throw new Exception("The gender was not set");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_11() // Add All services to the Staff member's profile

	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			driver.findElement(By.id("description")).sendKeys("Add a new staff member with all possible option");

			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(2) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(3) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(4) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(5) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(6) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(7) > div > label"))
					.click();

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();
			Sleep(5);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			Sleep(5);
			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc == null)
				throw new Exception("The Professional was not created where it should have");
			if (Doc.getString("lastName").contains("Testing") != true)
				throw new Exception("The last name was not set");

			if (Doc.getString("profession").contains("Barber") != true)
				throw new Exception("The profession was not set");
			if (Doc.getString("gender").contains("female") != true)
				throw new Exception("The gender was not set");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_12() // Add All working days to the staff member's profile

	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			driver.findElement(By.id("description")).sendKeys("Add a new staff member with all possible option");

			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(2) > td:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(3) > td:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(4) > td:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(5) > td:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(6) > td:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(7) > td:nth-child(1) > div > label"))
					.click();

			Sleep(5);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			Sleep(5);
			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc == null)
				throw new Exception("The Professional was not created where it should have");
			if (Doc.getString("lastName").contains("Testing") != true)
				throw new Exception("The last name was not set");

			if (Doc.getString("profession").contains("Barber") != true)
				throw new Exception("The profession was not set");
			if (Doc.getString("gender").contains("female") != true)
				throw new Exception("The gender was not set");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_13() // Make a professional Offline
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			driver.findElement(By.id("description")).sendKeys("Delete and existing member");

			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();

			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			Sleep(5);
			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc == null)
				throw new Exception("The Professional was not created where it should have");
			Sleep(10);

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");

			Sleep(3);
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(3);

			List<WebElement> elements = driver.findElements(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > vm-staff-table > table > tbody > tr"));

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > vm-staff-table > table > tbody > tr:nth-child("
							+ elements.size() + ") > td.first-action > div > label"))
					.click();

			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary"))
					.click();
			Sleep(5);

			JSONObject Doc2 = FindProfessionalByID(StaffName);

			if (Doc2.getBoolean("isActive") == true)
				throw new Exception("The Professional was not deactivated where it should have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Saloon_Staff_TestCase_14() // Make a professional Offline and then online
	{
		try {
			String StaffName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(1) > h3 > vm-add-staff > button"))
					.click();
			Sleep(3);

			driver.findElement(By.name("firstName")).sendKeys(StaffName);
			driver.findElement(By.name("lastName")).sendKeys("Testing");
			driver.findElement(By.id("description")).sendKeys("Delete and existing member");

			new Select(driver.findElement(By.name("profession"))).selectByVisibleText("Barber");
			new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Female");
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(2) > vm-services-list-form-group > div.content-form.col-lg-8 > div:nth-child(1) > div > label"))
					.click();
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(3) > vm-working-dates-and-hours-form-group > div.content-form.col-lg-8 > vm-working-hours > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > label"))
					.click();

			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button"))
					.click();

			Sleep(5);
			JSONObject Doc = FindProfessionalByID(StaffName);

			if (Doc == null)
				throw new Exception("The Professional was not created where it should have");
			Sleep(10);

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");

			Sleep(3);
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(3);

			List<WebElement> elements = driver.findElements(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > vm-staff-table > table > tbody > tr"));

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > vm-staff-table > table > tbody > tr:nth-child("
							+ elements.size() + ") > td.first-action > div > label"))
					.click();

			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary"))
					.click();
			Sleep(5);

			JSONObject Doc2 = FindProfessionalByID(StaffName);

			if (Doc2.getBoolean("isActive") == true)
				throw new Exception("The Professional was not deactivated where it should have");

			Sleep(10);

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");

			Sleep(3);
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/staff/list");
			Sleep(3);

			elements = driver.findElements(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > vm-staff-table > table > tbody > tr"));

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > vm-staff-table > table > tbody > tr:nth-child("
							+ elements.size() + ") > td.first-action > div > label"))
					.click();

			Sleep(3);

			JSONObject Doc3 = FindProfessionalByID(StaffName);

			if (Doc3.getBoolean("isActive") != true)
				throw new Exception("The Professional was not activated where it should have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}

	}

	
	
	public void Saloon_Service_TestCase_1() //Rearrange the Main Services
	{
		try {
			String ServiceName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/services/list");
			Sleep(5);
			
			List<WebElement> elements= driver.findElements(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div > div > span"));
			 
			Actions builder = new Actions(driver);

builder.clickAndHold(elements.get(0)).moveToElement(elements.get(2)).click().build().perform();

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Saloon_Service_TestCase_3() //Make a sub service offline
	{
		try {
			String ServiceName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/services/list");
			Sleep(5);
			
		WebElement elem =	driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr:nth-child(1) > td.service-name-cel.ng-binding"));
			
		String ItemName= elem.getText();
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr:nth-child(1) > td.first-action > div > label")).click();

			 Sleep(5);
			 
			 JSONObject Doc = FindServiceByID(ItemName );
			 
			 if(Doc.getBoolean("isActive")==true)
				 throw new Exception("The service was not deactivated");
			 
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr:nth-child(1) > td.first-action > div > label")).click();

			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Saloon_Service_TestCase_4() //Make a sub service offline
	{
		try {
			String ServiceName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/services/list");
			Sleep(5);
			
		WebElement elem =	driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr:nth-child(1) > td.service-name-cel.ng-binding"));
			
		String ItemName= elem.getText();
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr:nth-child(1) > td.first-action > div > label")).click();

			 Sleep(5);
			 
			 JSONObject Doc = FindServiceByID(ItemName );
			 
			 if(Doc.getBoolean("isActive")==true)
				 throw new Exception("The service was not deactivated");
			 
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr:nth-child(1) > td.first-action > div > label")).click();

			 
Sleep(5);
			 
			  Doc = FindServiceByID(ItemName );
			 
			 if(Doc.getBoolean("isActive")!=true)
				 throw new Exception("The service was not  activated");
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Saloon_Service_TestCase_5() //Update the Price of a sub-service
	{
		try {
			String ServiceName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/services/list");
			Sleep(5);
			
		WebElement elem =	driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr:nth-child(1) > td.service-name-cel.ng-binding"));
			
		String ItemName= elem.getText();
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr.ng-scope.as-sortable-item.inactive > td.table-actions > a > i")).click();

			 Sleep(5);
			 
			 String oldPrice = driver.findElement(By.name("price")).getAttribute("value").replace(".", "");
			 
			 driver.findElement(By.name("price")).clear();
			 driver.findElement(By.name("price")).sendKeys("3000");

			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button > span")).click();
			 
			 
			 Sleep(5);
			 JSONObject Doc = FindServiceByID(ItemName );
			 
			 if(Doc.getInt("price")!=3000)
				 throw new Exception("The price was not updated");
			 
			 
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr.ng-scope.as-sortable-item.inactive > td.table-actions > a > i")).click();

			 Sleep(5);
			 
			 
			 
			 driver.findElement(By.name("price")).clear();
			 driver.findElement(By.name("price")).sendKeys(oldPrice);

			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button > span")).click();
			 
			 
			 Sleep(5);
			  Doc = FindServiceByID(ItemName );
			 
			 if(Doc.getInt("price")!=Integer.parseInt(oldPrice))
				 throw new Exception("The price was not updated");
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Saloon_Service_TestCase_7() //Update the duration of a sub-service
	{
		try {
			String ServiceName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/settings/services/list");
			Sleep(5);
			
		WebElement elem =	driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr:nth-child(1) > td.service-name-cel.ng-binding"));
			
		String ItemName= elem.getText();
			
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr.ng-scope.as-sortable-item.inactive > td.table-actions > a > i")).click();

			 Sleep(5);
			 
			 String selectedOption = new Select(driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(1) > vm-add-a-service-form-group > div.content-form.col-lg-8.row > div:nth-child(2) > div.form-group.col-lg-6.hidden-lg.ng-isolate-scope > ng-transclude > div >select"))).getFirstSelectedOption().getText();
 
			// driver.findElement(By.id("duration")).click();
			 new Select(driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(1) > vm-add-a-service-form-group > div.content-form.col-lg-8.row > div:nth-child(2) > div.form-group.col-lg-6.hidden-lg.ng-isolate-scope > ng-transclude > div >select"))).selectByVisibleText("1h30m");

			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button > span")).click();
			 
			 
			 Sleep(5);
			 JSONObject Doc = FindServiceByID(ItemName );
			 
			 if(Doc.getInt("duration")!=90)
				 throw new Exception("The duration was not updated");
			 
			 
			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(1) > vm-services-table > table > tbody > tr.ng-scope.as-sortable-item.inactive > td.table-actions > a > i")).click();

			 Sleep(5);
			 
			 
			 
			 new Select(driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-items.col-lg-12 > div:nth-child(1) > vm-add-a-service-form-group > div.content-form.col-lg-8.row > div:nth-child(2) > div.form-group.col-lg-6.hidden-lg.ng-isolate-scope > ng-transclude > div >select"))).selectByVisibleText(selectedOption);


			 driver.findElement(By.cssSelector("body > ui-view > ui-view > section > div.tab-content.ng-scope > div > div > div > form > div.tab-content-action > vm-button-loading > button > span")).click();
			 
			 
			 Sleep(5);
			  Doc = FindServiceByID(ItemName );
			 
			  if(Doc.getInt("duration")!=45)
					 throw new Exception("The duration was not updated");
			 
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	
}