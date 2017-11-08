import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InventoryTests extends myCommon {

	 

	InventoryTests(WebDriver mydriver,String myAuthToken)  {
		
		 driver=mydriver;
		 AuthToken=myAuthToken;

		 ResetInventory(AuthToken);

		  ExecuteAllTests(driver, InventoryTests.class);

		// ExecuteSpecificTest("Inventory_Product_TestCase_2",InventoryTests.class);
		// ExecuteSimilarTests("Inventory_Categor_TestCase_",InventoryTests.class,driver);
		  PrintTestResults();

	}

	// Inventory Test cases start here
	public void Inventory_Product_TestCase_1() // Create a simple product with minimum requirements

	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();


			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");
			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("New Product");
			driver.findElement(By.name("price")).sendKeys("1212");
			// new
			// Select(driver.findElement(By.id("brand"))).selectByVisibleText("Maybeline");
			// new Select(driver.findElement(By.id("category"))).selectByVisibleText("Foot
			// Products");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) != true)
				throw new Exception("Product does not exist Exists were it should have");
			
			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
					+ ProductName;

			JSONObject Doc = ExtractDocumentsFromQuery(Query, AuthToken).getJSONObject(0);
			if(Doc.getString("sku").contains("New Product")!=true)	
				throw new Exception("SKU not added");
			if(Doc.getInt("price")!=1212)	
				throw new Exception("Price not added");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_2() // Create a simple product with all requirements
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(2);
 			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1212");
			driver.findElement(By.name("supplyPrice")).sendKeys("1313");
			driver.findElement(By.name("promotionalPrice")).sendKeys("1414");
			driver.findElement(By.name("tax")).sendKeys("1515");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) != true)
				throw new Exception("Product does not exist Exists were it should have");
			
			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
					+ ProductName;

			JSONObject Doc = ExtractDocumentsFromQuery(Query, AuthToken).getJSONObject(0);
			if(Doc.getString("sku").contains("Test Product")!=true)	
				throw new Exception("SKU not added");
			if(Doc.getInt("price")!=1212)	
				throw new Exception("Price not added");
		//	if(Doc.getInt("supplyPrice")!=1313)	
			//	throw new Exception("Supply price not added");
			if(Doc.getInt("promotionalPrice")!=1414)	
				throw new Exception("Promo price not added");
			//if(Doc.getInt("tax")!=1515)	
				//throw new Exception("Tax not added");
			//if(Doc.getString("description").contains("Lorum Ipsum")!=true)	
			//	throw new Exception("Description not added");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_3() // Create a simple product where a product with the specified name already exists

	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();


			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");
			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("New Product");
			driver.findElement(By.name("price")).sendKeys("1212");
			// new
			// Select(driver.findElement(By.id("brand"))).selectByVisibleText("Maybeline");
			// new Select(driver.findElement(By.id("category"))).selectByVisibleText("Foot
			// Products");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);
			
			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");
			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("New Product SKU");
			driver.findElement(By.name("price")).sendKeys("1212");
			// new
			// Select(driver.findElement(By.id("brand"))).selectByVisibleText("Maybeline");
			// new Select(driver.findElement(By.id("category"))).selectByVisibleText("Foot
			// Products");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);
			

			if (ProductExists(ProductName, AuthToken) != true)
				throw new Exception("Product does not exist Exists were it should have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}

	}
	
	public void Inventory_Product_TestCase_4() // Create a simple product where a product with the specified SKU already exists

	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();


			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");
			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Same SKU");
			driver.findElement(By.name("price")).sendKeys("1212");
			// new
			// Select(driver.findElement(By.id("brand"))).selectByVisibleText("Maybeline");
			// new Select(driver.findElement(By.id("category"))).selectByVisibleText("Foot
			// Products");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);
			
			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");
			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Same SKU");
			driver.findElement(By.name("price")).sendKeys("1212");
			// new
			// Select(driver.findElement(By.id("brand"))).selectByVisibleText("Maybeline");
			// new Select(driver.findElement(By.id("category"))).selectByVisibleText("Foot
			// Products");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);
			

			if (ProductExists(ProductName, AuthToken) != true)
				throw new Exception("Product does not exist Exists were it should have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}

	}
	
	public void Inventory_Product_TestCase_5() // Create a simple product where special price is more than the retail
												// price
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(2);
 			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("100");
			driver.findElement(By.name("supplyPrice")).sendKeys("1212");
			driver.findElement(By.name("promotionalPrice")).sendKeys("2000");
			driver.findElement(By.name("tax")).sendKeys("1212");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) != true)
				throw new Exception("Product does not exist Exists were it should have");

			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_6() // Create a simple product where supply price is more than the retail
												// price
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(2);
 			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("100");
			driver.findElement(By.name("supplyPrice")).sendKeys("1212");
			driver.findElement(By.name("promotionalPrice")).sendKeys("10");
			driver.findElement(By.name("tax")).sendKeys("1212");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) != true)
				throw new Exception("Product does not exist Exists were it should have");
			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_7() // Create a simple product where SKU is not provided
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(2);
 			driver.findElement(By.name("name")).sendKeys(ProductName);
			// driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("100");
			driver.findElement(By.name("supplyPrice")).sendKeys("1212");
			driver.findElement(By.name("promotionalPrice")).sendKeys("10");
			driver.findElement(By.name("tax")).sendKeys("1212");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) == true)
				throw new Exception("Product   Exists were it should not have");
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_8() // Create a simple product where Retail price is not provided
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(2);
 			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			// driver.findElement(By.name("price")).sendKeys("100");
			driver.findElement(By.name("supplyPrice")).sendKeys("1212");
			driver.findElement(By.name("promotionalPrice")).sendKeys("10");
			driver.findElement(By.name("tax")).sendKeys("1212");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) == true)
				throw new Exception("Product Exists were it should not have");
			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_9() // Create a simple product where Product Name is not provided
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(2);
 			// driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("100");
			driver.findElement(By.name("supplyPrice")).sendKeys("1212");
			driver.findElement(By.name("promotionalPrice")).sendKeys("10");
			driver.findElement(By.name("tax")).sendKeys("1212");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) == true)
				throw new Exception("Product Exists were it should not have");
			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_10() // Create a simple product where Retail price is not provided
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(2);
 			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("StringValue");
			driver.findElement(By.name("supplyPrice")).sendKeys("1212");
			driver.findElement(By.name("promotionalPrice")).sendKeys("10");
			driver.findElement(By.name("tax")).sendKeys("1212");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) == false)
				throw new Exception("Product not Exists were it should  have");
			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Inventory_Product_TestCase_11() // Create a simple product where Supply price is set as String
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(2);
 			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1212");
			driver.findElement(By.name("supplyPrice")).sendKeys("StringValue");
			driver.findElement(By.name("promotionalPrice")).sendKeys("10");
			driver.findElement(By.name("tax")).sendKeys("1212");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) == false)
				throw new Exception("Product not Exists were it should  have");
			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Inventory_Product_TestCase_12() // Create a simple product where Promotional  price is set as String
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(2);
 			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("12221");
			driver.findElement(By.name("supplyPrice")).sendKeys("1221");
			driver.findElement(By.name("promotionalPrice")).sendKeys("StringValue");
			driver.findElement(By.name("tax")).sendKeys("1212");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) == false)
				throw new Exception("Product not Exists were it should  have");
			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_13() // Create a simple product where TAX  is set as String
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(2);
 			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1221");
			driver.findElement(By.name("supplyPrice")).sendKeys("1221");
			driver.findElement(By.name("promotionalPrice")).sendKeys("10");
			driver.findElement(By.name("tax")).sendKeys("StringValue");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) == false)
				throw new Exception("Product not Exists were it should  have");
			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Inventory_Product_TestCase_14() // Create a simple product where Initial Quantity is set as String
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(2);
 			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1221");
			driver.findElement(By.name("supplyPrice")).sendKeys("1221");
			driver.findElement(By.name("promotionalPrice")).sendKeys("10");
			driver.findElement(By.name("tax")).sendKeys("1212");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("String Value");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			if (ProductExists(ProductName, AuthToken) == true)
				throw new Exception("Product exist were it should not have");
			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}
	
	public void Inventory_Product_TestCase_15() // Order the Productlist according to ProductName in Ascending order
	{

		try {
			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			// ExtractNetworkCalls();
			driver.findElement(By.cssSelector(".ui-grid-cell-contents.ui-grid-header-cell-primary-focus")).click();
			// driver.findElement(By.xpath("//*[@id=\"1502108675392-grid-container\"]/div[1]/div/div/div/div/div/div[1]")).click();

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&sortField=name&sortDirection=ASC";
			JSONArray JData = ExtractDocumentsFromQuery(Query, AuthToken);
			String[] ProductNames = ExtractStringArray(JData, "name");

			if (VerifyStringOrder(ProductNames, true) != true)
				throw new Exception("The Product Names were not in the correct Order");

			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_16() // Order the Productlist according to ProductName in Descending order
	{
		try {
			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			// ExtractNetworkCalls();
			driver.findElement(By.cssSelector(".ui-grid-cell-contents.ui-grid-header-cell-primary-focus")).click();
			Sleep(2);
			driver.findElement(By.cssSelector(".ui-grid-cell-contents.ui-grid-header-cell-primary-focus")).click();

			// driver.findElement(By.xpath("//*[@id=\"1502108675392-grid-container\"]/div[1]/div/div/div/div/div/div[1]")).click();

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&sortField=name&sortDirection=DESC";

			JSONArray JData = ExtractDocumentsFromQuery(Query, AuthToken);
			String[] ProductNames = ExtractStringArray(JData, "name");

			if (VerifyStringOrder(ProductNames, false) != true)
				throw new Exception("The Product Names were not in the correct Order");

			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_17() // Order the Products according to StockOnHand in Ascending order
	{
		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			// ExtractNetworkCalls();
			// driver.findElement(By.cssSelector(".ui-grid-header-cell.ui-grid-clearfix.ng-scope
			// ng-isolate-scope.ui-grid-coluiGrid-0006")).click();
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0006 > div > div > span"))
					.click();

			// driver.findElement(By.xpath("//*[@id=\"1502108675392-grid-container\"]/div[1]/div/div/div/div/div/div[1]")).click();

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&sortField=currentStock&sortDirection=ASC";

			JSONArray JData = ExtractDocumentsFromQuery(Query, AuthToken);
			int[] CurrentStock = ExtractIntArray(JData, "currentStock");

			if (VerifyIntegerOrder(CurrentStock, true) != true)
				throw new Exception("The Product Names were not in the correct Order");
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_18() // Order the Products according to StockOnHand in DSC order
	{
		try {
			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			// ExtractNetworkCalls();
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0006 > div > div > span"))
					.click();
			Sleep(2);
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0006 > div > div > span"))
					.click();

			// driver.findElement(By.xpath("//*[@id=\"1502108675392-grid-container\"]/div[1]/div/div/div/div/div/div[1]")).click();

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&sortField=currentStock&sortDirection=DESC";

			JSONArray JData = ExtractDocumentsFromQuery(Query, AuthToken);
			int[] CurrentStock = ExtractIntArray(JData, "currentStock");

			if (VerifyIntegerOrder(CurrentStock, false) != true)
				throw new Exception("The Product Names were not in the correct Order");

			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_19() // Order the Products according to Retail Price in ASC order
	{
		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			// ExtractNetworkCalls();
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0007 > div > div > span"))
					.click();

			// driver.findElement(By.xpath("//*[@id=\"1502108675392-grid-container\"]/div[1]/div/div/div/div/div/div[1]")).click();

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&sortField=price&sortDirection=ASC";

			JSONArray JData = ExtractDocumentsFromQuery(Query, AuthToken);
			int[] Price = ExtractIntArray(JData, "price");

			if (VerifyIntegerOrder(Price, true) != true)
				throw new Exception("The Product Names were not in the correct Order");
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_20() // Order the Products according to Retail Price in DSC order
	{
		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			// ExtractNetworkCalls();
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0007 > div > div > span"))
					.click();
			Sleep(2);
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0007 > div > div > span"))
					.click();

			// driver.findElement(By.xpath("//*[@id=\"1502108675392-grid-container\"]/div[1]/div/div/div/div/div/div[1]")).click();

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&sortField=price&sortDirection=DESC";

			JSONArray JData = ExtractDocumentsFromQuery(Query, AuthToken);
			int[] Price = ExtractIntArray(JData, "price");

			if (VerifyIntegerOrder(Price, false) != true)
				throw new Exception("The Price were not in the correct Order");

			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_21() // Order the Products according to Brand in ASC order
	{
		try {
			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			// ExtractNetworkCalls();
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0008 > div > div > span"))
					.click();

			// driver.findElement(By.xpath("//*[@id=\"1502108675392-grid-container\"]/div[1]/div/div/div/div/div/div[1]")).click();

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&sortField=brand&sortDirection=ASC";

			JSONArray JData = ExtractDocumentsFromQuery(Query, AuthToken);
			String[] Brand = ExtractStringArray(JData, "brand");

			if (VerifyStringOrder(Brand, true) != true)
				throw new Exception("The Product Names were not in the correct Order");
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_22() // Order the Products according to Brand in DSC order
	{
		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			// ExtractNetworkCalls();
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0008 > div > div > span"))
					.click();
			Sleep(2);
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0008 > div > div > span"))
					.click();

			// driver.findElement(By.xpath("//*[@id=\"1502108675392-grid-container\"]/div[1]/div/div/div/div/div/div[1]")).click();

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&sortField=brand&sortDirection=DESC";

			JSONArray JData = ExtractDocumentsFromQuery(Query, AuthToken);
			String[] Brand = ExtractStringArray(JData, "brand");

			List<String> listSorted = Arrays.asList(Brand);

			// Sorts the new list.
			Collections.sort(listSorted);
			Collections.reverse(listSorted);

			if (listSorted.equals(Arrays.asList(Brand)) == false)
				throw new Exception("The results were not ordered");
			
			
			TestPassed();

		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_23() // Order the Products according to Category in ASC order
	{
		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			// ExtractNetworkCalls();
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0009 > div > div > span"))
					.click();

			// driver.findElement(By.xpath("//*[@id=\"1502108675392-grid-container\"]/div[1]/div/div/div/div/div/div[1]")).click();

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&sortField=category&sortDirection=ASC";

			JSONArray JData = ExtractDocumentsFromQuery(Query, AuthToken);
			String[] category = ExtractStringArray(JData, "category");

			if (VerifyStringOrder(category, true) != true)
				throw new Exception("The Product Names were not in the correct Order");

			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_24() // Order the Products according to Category in DESC order
	{
		try {
			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			// ExtractNetworkCalls();
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0009 > div > div > span"))
					.click();
			Sleep(2);
			driver.findElement(By.cssSelector(
					"div > div > div > div > div.ui-grid-header-cell.ui-grid-clearfix.ng-scope.ng-isolate-scope.ui-grid-coluiGrid-0009 > div > div > span"))
					.click();

			// driver.findElement(By.xpath("//*[@id=\"1502108675392-grid-container\"]/div[1]/div/div/div/div/div/div[1]")).click();

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&sortField=category&sortDirection=DESC";

			JSONArray JData = ExtractDocumentsFromQuery(Query, AuthToken);
			String[] category = ExtractStringArray(JData, "category");

			List<String> listSorted = Arrays.asList(category);

			// Sorts the new list.
			Collections.sort(listSorted);
			Collections.reverse(listSorted);

			if (listSorted.equals(Arrays.asList(category)) == false)
				throw new Exception("The results were not ordered");

			TestPassed();
		} catch (Exception e) {
			TestFailed(e);
		}
	}

	public void Inventory_Product_TestCase_25() // List all Products against a certain Category
	{
		try {

			String TestCategory = "Foot Products";

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			new Select(driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > form > div > div.form-group.col-lg-9 > div.products-list-filter__categories.form-group-inner-wrapper.form-control-alternative.custom-select-alternative > select")))
							.selectByVisibleText(TestCategory);
Sleep(3);
			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			for (WebElement element : elements) {
				if (element.getText().replace("\\", "").contains(TestCategory) == false) {
					throw new Exception("Results returned contain products which dont have the specified category");
				}
			}

			TestPassed();

		} catch (Exception e) {

			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_26() // List all Products against a certain brand
	{
		try {

			String TestBrand = "Maybeline";

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			new Select(driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > form > div > div.form-group.col-lg-9 > div.products-list-filter__brands.form-group-inner-wrapper.form-control-alternative.custom-select-alternative > select")))
							.selectByVisibleText(TestBrand);
Sleep(3);
			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			for (WebElement element : elements) {
				if (element.getText().replace("\\", "").contains(TestBrand) == false) {
					throw new Exception("Results returned contain products which dont have the specified brand");
				}
			}

			TestPassed();
		} catch (Exception e) {

			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_27() // List all Products against a certain brand and a certain category
	{
		try {

			String TestBrand = "Maybeline";
			String TestCategory = "Foot Products";

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			new Select(driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > form > div > div.form-group.col-lg-9 > div.products-list-filter__brands.form-group-inner-wrapper.form-control-alternative.custom-select-alternative > select")))
							.selectByVisibleText(TestBrand);
			new Select(driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > form > div > div.form-group.col-lg-9 > div.products-list-filter__categories.form-group-inner-wrapper.form-control-alternative.custom-select-alternative > select")))
							.selectByVisibleText(TestCategory);

			Sleep(4);
			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			for (WebElement element : elements) {
				if (element.getText().replace("\\", "").contains(TestBrand) == false
						|| element.getText().replace("\\", "").contains(TestCategory) == false) {
					throw new Exception(
							"Results returned contain products which dont have the specified brand or category");
				}
			}

			TestPassed();
		} catch (Exception e) {

			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_29() // Edit Required attributes of an Existing Product.
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();



 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);

			
			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1212");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
					+ ProductName;

			String DocID = ExtractDocumentsFromQuery(Query, AuthToken).getJSONObject(0).getString("id");

			driver.get("http://my-staging.vaniday.com.au/#/vanidateau/inventory/products/" + DocID);

			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.section-header > a"))
					.click();
			Sleep(2);
			ProductName = "New" + ProductName;
			driver.findElement(By.name("name")).clear();
			driver.findElement(By.name("sku")).clear();
			driver.findElement(By.name("price")).clear();

			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Edited Product");
			driver.findElement(By.name("price")).sendKeys("100");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Sleep(3);
			Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products/" + DocID;
			JSONObject JObj = ExtractDocumentFromQuery(Query, AuthToken);

			if (JObj.getString("sku").contains("Edited Product") != true)
				throw new Exception("Product Not edited");
			if (JObj.getInt("price") != 100)
				throw new Exception("Product Not edited");

			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_30() // Edit All attributes of an Existing Product.
	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");
			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1212");
			driver.findElement(By.name("supplyPrice")).sendKeys("1212");
			driver.findElement(By.name("promotionalPrice")).sendKeys("1212");
			driver.findElement(By.name("tax")).sendKeys("1212");
			driver.findElement(By.id("description")).sendKeys("Lorum Ipsum");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
					+ ProductName;
			String DocID = ExtractDocumentsFromQuery(Query, AuthToken).getJSONObject(0).getString("id");

			driver.get("http://my-staging.vaniday.com.au/#/vanidateau/inventory/products/" + DocID);

			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.section-header > a"))
					.click();
			Sleep(2);

			driver.findElement(By.name("name")).clear();
			driver.findElement(By.name("sku")).clear();
			driver.findElement(By.name("price")).clear();
			driver.findElement(By.name("supplyPrice")).clear();
			driver.findElement(By.name("promotionalPrice")).clear();
			driver.findElement(By.name("tax")).clear();
			driver.findElement(By.id("description")).clear();

			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Edited Product");
			driver.findElement(By.name("price")).sendKeys("100");
			driver.findElement(By.name("supplyPrice")).sendKeys("200");
			driver.findElement(By.name("promotionalPrice")).sendKeys("300");
			driver.findElement(By.name("tax")).sendKeys("10");
			driver.findElement(By.id("description")).sendKeys("Edited Description");

			// driver.findElement(By.className("switch-text")).click();
			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			// driver.findElement(By.name("initialStock")).sendKeys("100");

			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(1) > div > ng-transclude > div > select")))
							.selectByVisibleText("Foot Products");
			new Select(driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(3) > div:nth-child(2) > div > ng-transclude > div > select")))
							.selectByVisibleText("Maybeline");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Sleep(3);
			Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products/" + DocID;
			JSONObject JObj = ExtractDocumentFromQuery(Query, AuthToken);

			if (JObj.getString("sku").contains("Edited Product") != true)
				throw new Exception("Product Not edited");
			if (JObj.getString("description").contains("Edited Description") != true)
				throw new Exception("Product Not edited");
			if (JObj.getInt("price") != 100)
				throw new Exception("Product Not edited");
			if (JObj.getInt("supplyPrice") != 200)
				throw new Exception("Product Not edited");
			if (JObj.getDouble("tax") != 0.1)
				throw new Exception("Product Not edited");
			if (JObj.getInt("promotionalPrice") != 300)
				throw new Exception("Product Not edited");

			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_31() // Increase the Stock of a product by '+' button where the product
												// already has some intial stock
	{
		try {


			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");

			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1212");

			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
					+ ProductName;

			String DocID = ExtractDocumentsFromQuery(Query, AuthToken).getJSONObject(0).getString("id");

			driver.get("http://my-staging.vaniday.com.au/#/vanidateau/inventory/products/" + DocID);

			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.section-header > a"))
					.click();
			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(7) > div > div.form-group.initial-stock-form-control.ng-scope > div.initial-stock-form-control__controls.ng-scope > a.initial-stock-form-control__controls__increase > i"))
					.click();
			Sleep(3);

			driver.findElement(By.name("amount")).sendKeys("100");

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > vm-manage-stock-modal > div > div > div > form > div.manage-stock-modal__footer > vm-button-loading > button > span"))
					.click();

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products/" + DocID;
			JSONObject JObj = ExtractDocumentFromQuery(Query, AuthToken);

			if (JObj.getInt("currentStock") != 200)
				throw new Exception("Product Not edited");

			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_32() // Decrease the Stock of a product by '-' button where the product
												// already has some intial stock
	{
		try {
 
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");

			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1212");

			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
					+ ProductName;

			String DocID = ExtractDocumentsFromQuery(Query, AuthToken).getJSONObject(0).getString("id");

			driver.get("http://my-staging.vaniday.com.au/#/vanidateau/inventory/products/" + DocID);

			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.section-header > a"))
					.click();
			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(7) > div > div.form-group.initial-stock-form-control.ng-scope > div.initial-stock-form-control__controls.ng-scope > a.initial-stock-form-control__controls__decrease > i"))
					.click();
			Sleep(3);

			driver.findElement(By.name("amount")).sendKeys("50");

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > vm-manage-stock-modal > div > div > div > form > div.manage-stock-modal__footer > vm-button-loading > button > span"))
					.click();

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			
			Sleep(3);

			Query = "http://https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products/" + DocID;
			JSONObject JObj = ExtractDocumentFromQuery(Query, AuthToken);

			if (JObj.getInt("currentStock") != 50)
				throw new Exception("Product Not edited");

			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_33() // Increase the Stock of a product by '-' button and then by using the
												// Increase tab

	{
		try {
 
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");

			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1212");

			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
					+ ProductName;

			String DocID = ExtractDocumentsFromQuery(Query, AuthToken).getJSONObject(0).getString("id");

			driver.get("http://my-staging.vaniday.com.au/#/vanidateau/inventory/products/" + DocID);

			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.section-header > a"))
					.click();
			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(7) > div > div.form-group.initial-stock-form-control.ng-scope > div.initial-stock-form-control__controls.ng-scope > a.initial-stock-form-control__controls__decrease > i"))
					.click();
			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > vm-manage-stock-modal > div > div > div > div > div.manage-stock-modal__tabs.btn-group > div.btn-group.first > button"))
					.click();

			driver.findElement(By.name("amount")).sendKeys("100");

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > vm-manage-stock-modal > div > div > div > form > div.manage-stock-modal__footer > vm-button-loading > button > span"))
					.click();

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products/" + DocID;
			JSONObject JObj = ExtractDocumentFromQuery(Query, AuthToken);

			if (JObj.getInt("currentStock") != 200)
				throw new Exception("Product Not edited");

			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_34() // Decrease the Stock of a product by '+' button and then by using the
												// Decrease tab

	{
		try {
 
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");

			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1212");

			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
					+ ProductName;

			String DocID = ExtractDocumentsFromQuery(Query, AuthToken).getJSONObject(0).getString("id");

			driver.get("http://my-staging.vaniday.com.au/#/vanidateau/inventory/products/" + DocID);

			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.section-header > a"))
					.click();
			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(7) > div > div.form-group.initial-stock-form-control.ng-scope > div.initial-stock-form-control__controls.ng-scope > a.initial-stock-form-control__controls__increase > i"))
					.click();
			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > vm-manage-stock-modal > div > div > div > div > div.manage-stock-modal__tabs.btn-group > div.btn-group.last > button"))
					.click();

			driver.findElement(By.name("amount")).sendKeys("50");

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > vm-manage-stock-modal > div > div > div > form > div.manage-stock-modal__footer > vm-button-loading > button > span"))
					.click();

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products/" + DocID;
			JSONObject JObj = ExtractDocumentFromQuery(Query, AuthToken);

			if (JObj.getInt("currentStock") != 50)
				throw new Exception("Product Not edited");

			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_35() // "Change the Stock of a product and save price for next time

	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");

			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1212");
			driver.findElement(By.name("supplyPrice")).sendKeys("1000");

			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
					+ ProductName;

			String DocID = ExtractDocumentsFromQuery(Query, AuthToken).getJSONObject(0).getString("id");

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/inventory/products/" + DocID);

			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.section-header > a"))
					.click();
			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(7) > div > div.form-group.initial-stock-form-control.ng-scope > div.initial-stock-form-control__controls.ng-scope > a.initial-stock-form-control__controls__increase > i"))
					.click();
			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > vm-manage-stock-modal > div > div > div > form > div.modal-body > div.row.ng-scope > div:nth-child(2) > div > ng-transclude > div > label"))
					.click();

			driver.findElement(By.name("amount")).sendKeys("50");
			driver.findElement(By.name("supplyPrice")).clear();

			driver.findElement(By.name("supplyPrice")).sendKeys("5000");

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > vm-manage-stock-modal > div > div > div > form > div.manage-stock-modal__footer > vm-button-loading > button > span"))
					.click();

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products/" + DocID;
			JSONObject JObj = ExtractDocumentFromQuery(Query, AuthToken);

			if (JObj.getInt("currentStock") != 150)
				throw new Exception("Product Not edited");
			if (JObj.getInt("supplyPrice") != 5000)
				throw new Exception("Product Not edited");

			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_36() // Change the stock of the product where the initial stock of the
												// product was not set while creating the product

	{
		try {
			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");

			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1212");
			driver.findElement(By.name("supplyPrice")).sendKeys("1000");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
					+ ProductName;

			String DocID = ExtractDocumentsFromQuery(Query, AuthToken).getJSONObject(0).getString("id");

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/inventory/products/" + DocID);

			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.section-header > a"))
					.click();
			Sleep(2);

			driver.findElement(By.name("stockEnabled")).click();

			Sleep(1);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(7) > div > div.form-group.initial-stock-form-control.ng-scope > div.initial-stock-form-control__controls.ng-scope > a.initial-stock-form-control__controls__increase > i"))
					.click();
			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > vm-manage-stock-modal > div > div > div > form > div.modal-body > div.row.ng-scope > div:nth-child(2) > div > ng-transclude > div > label"))
					.click();

			driver.findElement(By.name("amount")).sendKeys("50");

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > vm-manage-stock-modal > div > div > div > form > div.manage-stock-modal__footer > vm-button-loading > button > span"))
					.click();

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products/" + DocID;
			JSONObject JObj = ExtractDocumentFromQuery(Query, AuthToken);

			if (JObj.getInt("currentStock") != 50)
				throw new Exception("Product Not edited");

			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Product_TestCase_37() // Decrease the stock more than the stock is available

	{
		try {

			String ProductName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			ProductName = "" + ProductName;

 			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);

			driver.findElement(By.className("btn-primary")).click();
			Sleep(1);
			// System.out.println("new code");

			driver.findElement(By.name("name")).sendKeys(ProductName);
			driver.findElement(By.name("sku")).sendKeys("Test Product");
			driver.findElement(By.name("price")).sendKeys("1212");

			driver.findElement(By.cssSelector("#stockEnabled > small")).click();
			driver.findElement(By.name("initialStock")).sendKeys("100");

			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			Sleep(2);

			String Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
					+ ProductName;

			String DocID = ExtractDocumentsFromQuery(Query, AuthToken).getJSONObject(0).getString("id");

			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/inventory/products/" + DocID);

			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.section-header > a"))
					.click();
			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-body > div:nth-child(7) > div > div.form-group.initial-stock-form-control.ng-scope > div.initial-stock-form-control__controls.ng-scope > a.initial-stock-form-control__controls__decrease > i"))
					.click();
			Sleep(3);

			driver.findElement(By.name("amount")).sendKeys("150");

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > vm-manage-stock-modal > div > div > div > form > div.manage-stock-modal__footer > vm-button-loading > button > span"))
					.click();

			Query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products/" + DocID;
			JSONObject JObj = ExtractDocumentFromQuery(Query, AuthToken);

			if (JObj.getInt("currentStock") != 100)
				throw new Exception("Product Not edited");

			TestPassed();
			
		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	// ================================================== Category Cases
	// ======================================================

	public void Inventory_Category_TestCase_1() // Create a new Category with a valid name
	{

		try {
			String CategoryName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(2) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);
			driver.findElement(By.name("name")).sendKeys(CategoryName);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			if (CategoryExists(CategoryName, AuthToken) == false)
				throw new Exception("The Category was not created");

			TestPassed();
			
		} catch (Exception e) { 			
 			TestFailed(e);

		}

	}

	public void Inventory_Category_TestCase_2()// Create a new Category with a name that already exists

	{

		try {
			String CategoryName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(2) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);
			driver.findElement(By.name("name")).sendKeys(CategoryName);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Sleep(2);
			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(2) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);
			driver.findElement(By.name("name")).sendKeys(CategoryName);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			if (CategoryExists(CategoryName, AuthToken) == false)
				throw new Exception("The Category was not created");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Category_TestCase_3()// Create a category without specifying a name

	{

		try {
			String CategoryName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(2) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			driver.get("https://my-staging.vaniday.com.au/#/vanidateau/inventory/categories/list");

			if (CategoryExists(CategoryName, AuthToken) == true)
				throw new Exception("The Category was created where it should not have been");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Category_TestCase_4() // Edit the a certain Category Name

	{

		try {
			String CategoryName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(2) > a")).click();
			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);
			driver.findElement(By.name("name")).sendKeys(CategoryName);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.form-group.col-lg-3 > div > input"))
					.sendKeys(CategoryName);
			Sleep(2);

 			driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope > div > div > div")).click();
			Sleep(2);

			driver.findElement(By.name("name")).clear();
			driver.findElement(By.name("name")).sendKeys("New" + CategoryName);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			if (CategoryExists("New" + CategoryName, AuthToken) == false)
				throw new Exception("The Category was not edited");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Category_TestCase_5() // Delete a certain Category
	{

		try {
			String CategoryName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(2) > a")).click();
			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);
			driver.findElement(By.name("name")).sendKeys(CategoryName);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.form-group.col-lg-3 > div > input"))
					.sendKeys(CategoryName);

 			driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope > div > div > div")).click();
			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__left-column.col-xs-6 > span.ng-scope > a"))
					.click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary"))
					.click();

			if (CategoryExists("New" + CategoryName, AuthToken) == true)
				throw new Exception("The Category was not deleted");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Category_TestCase_6() // Search the category list for a certain category
	{

		try {
			String CategoryName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(2) > a")).click();
			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);
			driver.findElement(By.name("name")).sendKeys(CategoryName);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.form-group.col-lg-3 > div > input"))
					.sendKeys(CategoryName);

			Sleep(2);

			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			for (WebElement element : elements) {
				// System.out.println(element.getText().toString());

				if (element.getText().replace("\\", "").contains(CategoryName) == false) {
 					//System.out.println(element.getText().toString());
					throw new Exception("Results returned contain Categories that dont match the criteria");
				}
			}
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Category_TestCase_7() // Order the Category list by Category name in Ascending order
	{

		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(2) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"div.ui-grid-header.ng-scope > div > div > div > div > div > div"))
					.click();

			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			List<String> Categories = new ArrayList<String>();

			for (WebElement element : elements) {
				// System.out.println(element.getText().toString());
				String[] temp = element.getText().split("\\n");
				Categories.add(temp[0]);

			}

			List<String> listSorted = Categories;

			// Sorts the new list.
			Collections.sort(listSorted);

			if (listSorted.equals(Categories) == false)
				throw new Exception("The results were not ordered");

			TestPassed();
			
		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Category_TestCase_8()// Order the Category List by Category name in Descending order
	{

		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(2) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"div.ui-grid-header.ng-scope > div > div > div > div > div > div"))
					.click();
			driver.findElement(By.cssSelector(
					"div.ui-grid-header.ng-scope > div > div > div > div > div > div"))
					.click();

			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			List<String> Categories = new ArrayList<String>();

			for (WebElement element : elements) {
				// System.out.println(element.getText().toString());
				String[] temp = element.getText().split("\\n");
				Categories.add(temp[0]);

			}

			List<String> listSorted = Categories;

			// Sorts the new list.
			Collections.sort(listSorted);
			Collections.reverse(listSorted);

			if (listSorted.equals(Categories) == false)
				throw new Exception("The results were not ordered");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Category_TestCase_9() // Order the Category list by "Products Assigned" in Ascending order
	{

		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(2) > a")).click();
			Sleep(3);
			List<WebElement> ColumnList = driver
					.findElements(By.cssSelector("div.ui-grid-header.ng-scope > div > div > div > div > div > div"));
			ColumnList.get(1).click();

			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			List<String> Categories = new ArrayList<String>();

			for (WebElement element : elements) {
				// System.out.println(element.getText().toString());
				String[] temp = element.getText().split("\\n");
				if (temp.length == 2) {
					Categories.add(temp[1]);
				}
			}

			List<String> listSorted = Categories;

			// Sorts the new list.
			Collections.sort(listSorted);

			if (listSorted.equals(Categories) == false)
				throw new Exception("The results were not ordered");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Category_TestCase_10() // Order the Category List by "Products Assigned" in Descending order
	{

		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(2) > a")).click();
			Sleep(3);
			List<WebElement> ColumnList = driver
					.findElements(By.cssSelector("div.ui-grid-header.ng-scope > div > div > div > div > div > div"));
			ColumnList.get(1).click();
			ColumnList.get(1).click();

			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			List<String> Categories = new ArrayList<String>();

			for (WebElement element : elements) {
				// System.out.println(element.getText().toString());
				String[] temp = element.getText().split("\\n");
				if (temp.length == 2) {
					Categories.add(temp[1]);
				}
			}

			List<String> listSorted = Categories;

			// Sorts the new list.
			Collections.sort(listSorted);
			Collections.reverse(listSorted);

			if (listSorted.equals(Categories) == false)
				throw new Exception("The results were not ordered");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	// ================================================= Brand TestCases
	// =====================================================

	public void Inventory_Brand_TestCase_1() // Create a new Brand with a valid name
	{

		try {
			String BrandName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);
			driver.findElement(By.name("name")).sendKeys(BrandName);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			if (BrandExists(BrandName, AuthToken) == false)
				throw new Exception("The Brand was not created");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}
	}

	public void Inventory_Brand_TestCase_2()// Create a new Brand with a name that already exists

	{

		try {
			String BrandName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);
			driver.findElement(By.name("name")).sendKeys(BrandName);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Sleep(2);
			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);
			driver.findElement(By.name("name")).sendKeys(BrandName);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			if (BrandExists(BrandName, AuthToken) == false)
				throw new Exception("The Brand was not created");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Brand_TestCase_3()// Create a Brand without specifying a name

	{

		try {
			String BrandName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			driver.get("http://my-dev.vaniday.com.au/#/vanidateau/inventory/categories/list");

			if (BrandExists(BrandName, AuthToken) == true)
				throw new Exception("The Brand was created where it should not have been");

			TestPassed();
			
		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Brand_TestCase_4() // Edit the a certain Brand Name

	{

		try {
			String BrandName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(3) > a")).click();
			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);
			driver.findElement(By.name("name")).sendKeys(BrandName);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.form-group.col-lg-3 > div > input"))
					.sendKeys(BrandName);
			Sleep(3);
 			driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope > div > div > div")).click();
			Sleep(2);

			driver.findElement(By.name("name")).clear();
			driver.findElement(By.name("name")).sendKeys("New" + BrandName);
			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();

			if (BrandExists("New" + BrandName, AuthToken) == false)
				throw new Exception("The Category was not edited");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Brand_TestCase_5() // Delete a certain Brand
	{

		try {
			String BrandName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(3) > a")).click();
			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(2);
			driver.findElement(By.name("name")).sendKeys(BrandName);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.form-group.col-lg-3 > div > input"))
					.sendKeys(BrandName);
			Sleep(3);

 			driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope > div > div > div")).click();
			Sleep(2);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__left-column.col-xs-6 > span.ng-scope > a"))
					.click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > div.bootbox.modal.fade.bootbox-confirm.in > div > div > div.modal-footer > button.btn.btn-primary"))
					.click();
			Sleep(3);

			if (BrandExists("New" + BrandName, AuthToken) == true)
				throw new Exception("The Category was not deleted");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Brand_TestCase_6() // Search the Brand list for a certain category
	{

		try {
			String BrandName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(3) > a")).click();
			Sleep(3);

			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.col-lg-9 > button > i"))
					.click();
			Sleep(3);
			driver.findElement(By.name("name")).sendKeys(BrandName);

			driver.findElement(By.cssSelector(
					"body > div.modal.fade.ng-scope.in > div > div > form > div.modal-footer > div > div.modal-footer__right-column.col-xs-6 > vm-button-loading > button > span"))
					.click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > div > div > div > div > div > div > div.inventory-filters > div > div.form-group.col-lg-3 > div > input"))
					.sendKeys(BrandName);

			Sleep(2);

			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			for (WebElement element : elements) {
				// System.out.println(element.getText().toString());

				if (element.getText().replace("\\", "").contains(BrandName) == false) {
 					//System.out.println(element.getText().toString());
					throw new Exception("Results returned contain Brand which dont match the criteria");
				}
			}
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Brand_TestCase_7() // Order the Brand list by brand name in Ascending order
	{

		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"div.ui-grid-header.ng-scope > div > div > div > div > div > div"))
					.click();

			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			List<String> Brands = new ArrayList<String>();

			for (WebElement element : elements) {
				//System.out.println(element.getText().toString());
				String[] temp = element.getText().split("\\n");
				Brands.add(temp[0]);

			}

			List<String> listSorted = Brands;

			// Sorts the new list.
			Collections.sort(listSorted);

			if (listSorted.equals(Brands) == false)
				throw new Exception("The results were not ordered");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Brand_TestCase_8()// Order the Brand List by brand name in Descending order
	{

		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			driver.findElement(By.cssSelector(
					"div.ui-grid-header.ng-scope > div > div > div > div > div > div"))
					.click();
			driver.findElement(By.cssSelector(
					"div.ui-grid-header.ng-scope > div > div > div > div > div > div"))
					.click();

			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			List<String> Brands = new ArrayList<String>();

			for (WebElement element : elements) {
				//System.out.println(element.getText().toString());
				String[] temp = element.getText().split("\\n");
				Brands.add(temp[0]);

			}

			List<String> listSorted = Brands;

			// Sorts the new list.
			Collections.sort(listSorted);
			Collections.reverse(listSorted);

			if (listSorted.equals(Brands) == false)
				throw new Exception("The results were not ordered");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Brand_TestCase_9() // Order the Brand list by "Products Assigned" in Ascending order
	{

		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(3) > a")).click();
			Sleep(3);

			List<WebElement> ColumnList = driver
					.findElements(By.cssSelector("div.ui-grid-header.ng-scope > div > div > div > div > div > div"));
			ColumnList.get(1).click();
			
			 

			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			List<String> Brands = new ArrayList<String>();

			for (WebElement element : elements) {
				//System.out.println(element.getText().toString());
				String[] temp = element.getText().split("\\n");
				if (temp.length == 2) {
					Brands.add(temp[1]);
				}
			}

			List<String> listSorted = Brands;

			// Sorts the new list.
			Collections.sort(listSorted);

			if (listSorted.equals(Brands) == false)
				throw new Exception("The results were not ordered");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}

	public void Inventory_Brand_TestCase_10() // Order the Brand List by "Products Assigned" in Descending order
	{

		try {

			driver.findElement(By.className("icon-v4-inventory")).click();
			Sleep(5);
			driver.findElement(By.cssSelector(
					"body > ui-view > ui-view > section > div.inventory-content > ul > li:nth-child(3) > a")).click();
			Sleep(3);
			List<WebElement> ColumnList = driver
					.findElements(By.cssSelector("div.ui-grid-header.ng-scope > div > div > div > div > div > div"));
			ColumnList.get(1).click();
			ColumnList.get(1).click();

			WebElement rowGroup = driver.findElement(By.cssSelector("div.ui-grid-viewport.ng-isolate-scope"));

			List<WebElement> elements = rowGroup
					.findElements(By.cssSelector(" div.ui-grid-viewport.ng-isolate-scope > div >div "));

			List<String> Brands = new ArrayList<String>();

			for (WebElement element : elements) {
				//System.out.println(element.getText().toString());
				String[] temp = element.getText().split("\\n");
				if (temp.length == 2) {
					Brands.add(temp[1]);
				}
			}

			List<String> listSorted = Brands;

			// Sorts the new list.
			Collections.sort(listSorted);
			Collections.reverse(listSorted);

			if (listSorted.equals(Brands) == false)
				throw new Exception("The results were not ordered");
			
			TestPassed();

		} catch (Exception e) {
 			TestFailed(e);
		}

	}
}
