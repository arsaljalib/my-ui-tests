import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

import org.json.*;

public class myCommon {

	public WebDriver driver;
	public static JavascriptExecutor js;
	public static String AuthToken;
	public static String MKTAuthToken;
	public int TestsPassed=0;
	public int TestsFailed=0;
	public int TotalTests=0;
	
	//Environment Variables like Where to test, which saloon, some prerequisites etc.
	
	static String TestingEnvironment = "staging";
	static String CountryCode ="com";
	static String SaloonID="83da9e51-4bb4-4f7d-a8b7-e5472ac02b22";    // VANIDATE AU (DEV) = 24ccfb06-5a18-4c8a-af93-3962e7005fe1
	static String SaloonName="Vanidate AU";
	
 
	public static void GetProductsBySelenium() throws InterruptedException {

		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "/Users/vaniday/eclipse-workspace/chromedriver");

		driver = new ChromeDriver();
		driver.get("http://rocket:rock4me@my-staging.vaniday.com.au/");

		Thread.sleep(5000);
		driver.get("http://my-staging.vaniday.com.au/");
		Thread.sleep(5000);

		driver.findElement(By.name("email")).sendKeys("my@vaniday.com");
		driver.findElement(By.name("password")).sendKeys("vaniday");

		// driver.findElement(By.name("login")).click();

		driver.findElement(By.className("btn-sm")).click();

		Thread.sleep(8000);

		driver.findElement(By.className("icon-v4-inventory")).click();

		 
	}
	
	public static boolean ProductExists(String ProductName, String AuthToken) throws IOException {

		// System.out.println("getting products");

		String query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?limit=10&terms="
				+ ProductName;

		if (ExtractDocumentsFromQuery(query, AuthToken).toString().contains(ProductName)==true)
			return true;
		else
			return false;

		//
	}

	public static boolean CategoryExists(String CategoryName, String AuthToken) throws IOException {

		// System.out.println("getting Categories");

		String query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/categories";

		if (ExtractDocumentsFromQuery(query, AuthToken).toString().contains(CategoryName) == true)
			return true;
		else
			return false;

		//
	}

	public static boolean BrandExists(String BrandName, String AuthToken) throws IOException {

		// System.out.println("getting Categories");

		String query = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/brands";

		if (ExtractDocumentsFromQuery(query, AuthToken).toString().contains(BrandName) == true)
			return true;
		else
			return false;

		//
	}

	public static String GetAuthenticationToken(JavascriptExecutor js) throws JSONException {

		String sItem = (String) js.executeScript(String.format("return window.localStorage.getItem('%s');", "user"));

		JSONObject jsonObj = new JSONObject(sItem);
		sItem = jsonObj.getString("token");

		//System.out.println(sItem);

		return sItem;
	}

	public void ExtractNetworkCalls(WebDriver driver) throws JSONException {
		LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			// do something useful with the data
		}
	}

	public void TestFailed(String text) {
		TestsFailed++;

		System.out.println(text);
		Sleep(5);
	}

	public void TestFailed(Exception e) {
		
		TestsFailed++;
		System.err.println("The Test Failed Because " + e.getMessage());
		
		Sleep(5);
	}

	public void TestPassed(String text) {
		TestsPassed++;

		System.out.println(text);
	}

	public void TestPassed() {
		
		TestsPassed++;
		System.out.println("The Test Executed Succuessfully");
	}

	public void PrintTestResults()
	{
		System.out.println("===================================================");
		System.out.println("Total Tests Executed:"+TotalTests);
		System.out.println("Total Tests Failed:"+ TestsFailed);
		System.out.println("Total Tests Passed:"+ TestsPassed);
		System.out.println("===================================================");


	}
	
	public static void Sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ExecuteAllTests(WebDriver driver, Class ModuleClass) {

		Method[] x = ModuleClass.getDeclaredMethods();

		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i].getName() + " : ");
			try {

				driver.get("https://my-staging.vaniday.com.au/");
				Sleep(5);
				
				
				new Select(driver.findElement(By.cssSelector("#selectedSalon"))).selectByVisibleText("Vanidate AU");
				
				if(x[i].getName().contains("Calender") )
				{
					ResetAppointments(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				}
				
				if(x[i].getName().contains("Client") )
				{
						ResetClients(AuthToken);					
				}

				TotalTests++;
				x[i].invoke(this, null);
				
				

			} catch (Exception e) {
				
					System.out.println(" Issue in 'Execute All methods' due to "+e.getMessage());
				
			}  
		}
		
		PrintTestResults();

	}

	public void ExecuteSpecificTest(String TestName, Class ModuleClass) {
		System.out.println("Invoking method via Specific Test Name!");
		try {
			Method method = ModuleClass.getDeclaredMethod(TestName);

			method.invoke(this, null);
		} catch (Exception e) {
			
			System.out.println("The program threw an Exception in the EXECUTE SPECIFIC TEST method due to: "+e.getMessage());
		}
	}

	public void ExecuteSimilarTests(String TestName, Class ModuleClass, WebDriver driver) {
		System.out.println("Invoking methods with similar Names!");
		try {
			Method[] methods = ModuleClass.getDeclaredMethods();

			for (Method method : methods) {

				if (method.getName().contains(TestName)) {

					System.out.print(method.getName() + " : ");
					driver.get("http://my-staging.vaniday.com.au/");
					Sleep(5);
					new Select(driver.findElement(By.name("selectedSalon"))).selectByVisibleText("Vanidate AU");
					Sleep(5);
					
					if(TestName.contains("Calender") )
					{
						ResetAppointments(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					}
					
					if(TestName.contains("Client") )
					{
							ResetClients(AuthToken);					
					}
					
					method.invoke(this, null);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("The program threw an Exception in the Execute Similar Tests due to: "+e.getMessage());
		}
	}

	public int[] ExtractIntArray(JSONArray JArray, String Attribute) {
		try {
			int ArrayLength = JArray.length();

			int[] AttributeArray = new int[ArrayLength];

			for (int i = 0; i < ArrayLength; i++) {
				AttributeArray[i] = JArray.getJSONObject(i).getInt(Attribute);
			}
			return AttributeArray;

		} catch (Exception e) {
			System.out.println("The program threw an Exception in the Extract Int Array due to: "+e.getMessage());
		}
		return null;

	}

	public String[] ExtractStringArray(JSONArray JArray, String Attribute) {
		try {
			int ArrayLength = JArray.length();

			String[] AttributeArray = new String[ArrayLength];

			for (int i = 0; i < ArrayLength; i++)
			{
				//System.out.println(JArray.getJSONObject(i).get(Attribute).toString());

				if (JArray.getJSONObject(i).get(Attribute).getClass().equals(String.class))
					{
					AttributeArray[i] = JArray.getJSONObject(i).getString(Attribute);
					}
				if (JArray.getJSONObject(i).get(Attribute).toString().contains("null"))
				{
			//		System.out.println(JArray.getJSONObject(i).get(Attribute).toString());
					AttributeArray[i] = "";
				}
				if (JArray.getJSONObject(i).get(Attribute).getClass().equals(JSONObject.class))
				{
					JSONObject tempObj = JArray.getJSONObject(i).getJSONObject(Attribute);
					AttributeArray[i] = tempObj.getString("name");

				}
			}

			return AttributeArray;

		} catch (Exception e) {
			System.out.println("The program threw an Exception in the String Array due to: "+e.getMessage());
		}
		return null;
	}

	public boolean VerifyIntegerOrder(int[] Array, boolean Ascending)
	{
		if (Ascending == true) 
		{
			int i;
			for (i = 0; i < Array.length - 1; i++)
			{

				if (Array[i] <= Array[i + 1]) {

					return true;
				} else {
					return false;

				}

			}
		} 
		else 
		{
			int i;
			for (i = 0; i < Array.length - 1; i++)
			{

				if (Array[i] >= Array[i + 1]) {

					return true;
				} else {
					return false;

				}

			}
		}
		return false;
	}

	public boolean VerifyStringOrder(String[] Array, boolean Ascending) {
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(Array));

		boolean sorted = true;
		for (int i = 1; i < arrayList.size(); i++) {
			if (arrayList.get(i - 1).compareTo(arrayList.get(i)) > 0)
				sorted = false;
		}

		if (Ascending == true && sorted == true)
			return true;
		if (Ascending == true && sorted == false)
			return false;
		if (Ascending == false && sorted == false)
			return true;
		if (Ascending == false && sorted == true)
			return false;
		else
			return false;

	}

	public static JSONArray ExtractDocumentsFromQuery(String Query, String AuthToken) {

		Query = Query;
		Sleep(3);
		try {
			// System.out.println("getting products");

			String result = ExecuteQuery(Query);


			// System.out.println(result);
			JSONArray JData;
			if (result.startsWith("{")) {
				JSONObject jobj = new JSONObject(result);

				JData = jobj.getJSONArray("data");

			} else {
				JData = new JSONArray(result);
			}

			// ExtractAttributeArray(JData,"sku","string");
			return JData;

		} catch (Exception e) {
			System.out.println("The program threw an Exception in the Extract Documents From Query due to: "+e.getMessage());
		}
		return null;
	}

	public static JSONObject ExtractDocumentFromQuery(String Query, String AuthToken) {

		Sleep(3);

		try {
			// System.out.println("getting products");

			String result = ExecuteQuery(Query);


			// System.out.println(result);

			JSONObject jobj = new JSONObject(result);

			// ExtractAttributeArray(JData,"sku","string");
			return jobj;

		} catch (Exception e) {
			System.out.println("The program threw an Exception in the Extract Document From QUERY due to: "+e.getMessage());
		}
		return null;
	}

	public void ResetInventory(String AuthToken) {

		try {
			// DELETING PRODUCTS NOW

			String ProductQuery = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products?offset=0&limit=100";
			JSONArray products = ExtractDocumentsFromQuery(ProductQuery, AuthToken);

			for (int i = 0; i < products.length(); i++) {
				if (products.getJSONObject(i).get("name").toString().contains("TestCase") == true) {

					URL url = new URL(
							"http://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/products/"
									+ products.getJSONObject(i).get("id").toString());
					HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
					urlConnection.setRequestProperty("Authorization", "Bearer " + AuthToken);
					urlConnection.setRequestMethod("DELETE");
					InputStream is = urlConnection.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);

					System.out.println("Deleting product: " + products.getJSONObject(i).get("name").toString());
				}
			}

			// DELETING CATEGORIES NOW

			String CategoryQuery = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/categories";
			JSONArray Category = ExtractDocumentsFromQuery(CategoryQuery, AuthToken);

			for (int i = 0; i < Category.length(); i++) {
				if (Category.getJSONObject(i).get("name").toString().contains("TestCase") == true) {

					URL url = new URL(
							"https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/categories/"
									+ Category.getJSONObject(i).get("id").toString());
					HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
					urlConnection.setRequestProperty("Authorization", "Bearer " + AuthToken);
					urlConnection.setRequestMethod("DELETE");
					InputStream is = urlConnection.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);

					System.out.println("Deleting Category: " + Category.getJSONObject(i).get("name").toString());
				}
			}

			// DELETING BRANDS NOW
			String BrandQuery = "https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/brands";
			JSONArray Brand = ExtractDocumentsFromQuery(BrandQuery, AuthToken);

			for (int i = 0; i < Brand.length(); i++) {
				if (Brand.getJSONObject(i).get("name").toString().contains("TestCase") == true) {

					URL url = new URL(
							"https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/brands/"
									+ Brand.getJSONObject(i).get("id").toString());
					HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
					urlConnection.setRequestProperty("Authorization", "Bearer " + AuthToken);
					urlConnection.setRequestMethod("DELETE");
					InputStream is = urlConnection.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);

					System.out.println("Deleting Brand: " + Brand.getJSONObject(i).get("name").toString());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("The program threw an Exception in the Reset Inventory due to: "+e.getMessage());
		}
	}

	public void ResetClients(String AuthToken) {
		
		try {

			String Query ="https://my-api-staging.vaniday.com/contacts?salonId=83da9e51-4bb4-4f7d-a8b7-e5472ac02b22";

			String result = ExecuteQuery(Query);

			// System.out.println(result);
			JSONObject jobj = new JSONObject(result);
			JSONArray JData;
			JData = jobj.getJSONArray("c"); // Because all our temporary contacts will start with  ClientTest_TestCase...
			
			// DELETING Clients NOW

			
			for (int i = 0; i < JData.length(); i++) {
				if(JData.getJSONObject(i).getString("firstName").contains("TestCase"))
				{
				 
					URL url2 = new URL("https://my-api-staging.vaniday.com/contacts/"+ JData.getJSONObject(i).getString("id"));
					HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
					urlConnection2.setRequestProperty("Authorization", "Bearer " + AuthToken);
					urlConnection2.setRequestMethod("DELETE");
					InputStream is2 = urlConnection2.getInputStream();
					InputStreamReader isr2 = new InputStreamReader(is2);

					System.out.println("Deleting CLient: " + JData.getJSONObject(i).getString("firstName"));
					Sleep(1);
				 
				}
			}
			
			Sleep(10);
		}
		catch (Exception e) {
			System.out.println("The program threw an Exception in the Reset Clients due to: "+e.getMessage());

		}
		
	}
	
	public void ResetSaloon(String AuthToken) {
		
		try {

			String Query ="https://my-api-staging.vaniday.com/professionals?salonId=83da9e51-4bb4-4f7d-a8b7-e5472ac02b22";

			String result = ExecuteQuery(Query);

			// System.out.println(result);
			JSONArray JData = new JSONArray(result);
			 
			
			// DELETING Clients NOW

			
			for (int i = 0; i < JData.length(); i++) {
				if(JData.getJSONObject(i).getString("firstName").contains("TestCase"))
				{
				 
					URL url2 = new URL("https://my-api-staging.vaniday.com/professionals/"+ JData.getJSONObject(i).getString("id"));
					HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
					urlConnection2.setRequestProperty("Authorization", "Bearer " + AuthToken);
					urlConnection2.setRequestMethod("DELETE");
					InputStream is2 = urlConnection2.getInputStream();
					InputStreamReader isr2 = new InputStreamReader(is2);

					System.out.println("Deleting Professional: " + JData.getJSONObject(i).getString("firstName"));
					Sleep(1);
				 
				}
			}
		}
		catch (Exception e) {
			System.out.println("The program threw an Exception in the Reset Saloon due to: "+e.getMessage());

		}
		
	}
	
	public JSONArray SearchEventByNote(String Note,String CurrentDate)
	{
		String Query ="https://my-api-staging.vaniday.com/calendar/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/?from=2017-08-10&to="+CurrentDate;
		try {
			// System.out.println("getting products");

			 
			String result = ExecuteQuery(Query);

			// System.out.println(result);
			JSONObject jobj = new JSONObject(result);
			JSONArray JData;
			JData = jobj.getJSONArray("events");
			
			JSONArray Orders = new JSONArray();
			
			for(int i=0; i< JData.length();i++)
			{
				if(JData.getJSONObject(i).getString("type").contains("appointment"))
				{
				if (JData.getJSONObject(i).getJSONObject("appointment").get("message").toString().contains(Note))
				{
					//return JData.getJSONObject(i);
					Orders.put(JData.getJSONObject(i));
				}
				}
				if(JData.getJSONObject(i).getString("type").contains("booking"))
				{
				if (JData.getJSONObject(i).getJSONObject("booking").get("message").toString().contains(Note))
				{
					//return JData.getJSONObject(i);
					Orders.put(JData.getJSONObject(i));
				}
				}
				if(JData.getJSONObject(i).getString("type").contains("blockOffTime"))
				{
				if (JData.getJSONObject(i).getJSONObject("blockOffTime").get("reason").toString().contains(Note))
				{
					//return JData.getJSONObject(i);
					Orders.put(JData.getJSONObject(i));
				}
				if(JData.getJSONObject(i).getJSONObject("blockOffTime").get("reason").toString().contains("null"))
				{
					if(Note.contains("Client")!=true && Note.contains("Calender")!=true)
						{
						String StartAt=Note.split("-")[0];
						
					String EndAt=Note.split("-")[1];
					String Professional=Note.split("-")[2];
					
					JSONObject obj= JData.getJSONObject(i);
					
					if(obj.getString("startAt").contains(StartAt) && obj.getString("endAt").contains(EndAt) && FindProfessionalByID(obj.getString("professionalId")).getString("firstName").contains(Professional) && obj.getString("startAt").contains(CurrentDate))
					{
						Orders.put(JData.getJSONObject(i));
					}
					}
					
				}
				}
			}

			// ExtractAttributeArray(JData,"sku","string");
			return Orders;

		} catch (Exception e) {
			System.out.println("The program threw an Exception in the SearchEvent By Note due to: "+e.getMessage());

		}
		
		return null;
	}
	
	public void ResetAppointments(String CurrentDate)
	{
		try {

		//	String ProductQuery = "http://my-api.dev.vaniday.com/salons/24ccfb06-5a18-4c8a-af93-3962e7005fe1/products?offset=0&limit=100";
			String Query ="https://my-api-staging.vaniday.com/calendar/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/?from=2017-08-10&to="+CurrentDate;

			String result = ExecuteQuery(Query);

			// System.out.println(result);
			JSONObject jobj = new JSONObject(result);
			JSONArray JData;
			JData = jobj.getJSONArray("events");
			
			// DELETING APPOINTMENTS NOW

			
			for (int i = 0; i < JData.length(); i++) {
				if(JData.getJSONObject(i).getString("type").contains("appointment"))
				{
				if (JData.getJSONObject(i).getJSONObject("appointment").get("message").toString().contains("TestCase") == true) {

					URL url2 = new URL("https://my-api-staging.vaniday.com/salons/83da9e51-4bb4-4f7d-a8b7-e5472ac02b22/my-orders/"+ JData.getJSONObject(i).getJSONObject("appointment").getString("myOrderId").toString());
					HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
					urlConnection2.setRequestProperty("Authorization", "Bearer " + AuthToken);
					urlConnection2.setRequestMethod("DELETE");
					InputStream is2 = urlConnection2.getInputStream();
					InputStreamReader isr2 = new InputStreamReader(is2);

					System.out.println("Deleting Appointment: " + JData.getJSONObject(i).getJSONObject("appointment").get("message").toString());
					Sleep(3);
				}
				}
			}

			// DELETING BLOCKED TIMES NOW

			for (int i = 0; i < JData.length(); i++) {
				if(JData.getJSONObject(i).getString("type").contains("blockOffTime"))
				{
				 
					URL url2 = new URL("https://my-api-staging.vaniday.com/block-off-time/"+ JData.getJSONObject(i).getJSONObject("blockOffTime").getString("id").toString());
					HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
					urlConnection2.setRequestProperty("Authorization", "Bearer " + AuthToken);
					urlConnection2.setRequestMethod("DELETE");
					InputStream is2 = urlConnection2.getInputStream();
					InputStreamReader isr2 = new InputStreamReader(is2);

					System.out.println("Deleting Blocked off time: " + JData.getJSONObject(i).toString());
					Sleep(3);
				 
				}
			}
			//DELETING MARKETPLACE APPOINTMENTS

			for (int i = 0; i < JData.length(); i++) {
				if(JData.getJSONObject(i).getString("type").contains("booking"))
				{
				if (JData.getJSONObject(i).getJSONObject("booking").get("message").toString().contains("TestCase") == true) {

					URL url2 = new URL("https://v3-staging.vaniday.com/orders/"+ JData.getJSONObject(i).getJSONObject("booking").getString("orderId").toString());
					HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
					urlConnection2.setRequestProperty("Authorization", "Bearer " + MKTAuthToken);
					urlConnection2.setRequestMethod("DELETE");
					urlConnection2.setRequestProperty("Content-Type", "application/json");
					urlConnection2.setRequestProperty("Connection", "keep-alive");
					urlConnection2.setDoOutput(true);
					urlConnection2.setReadTimeout(15*1000);
					urlConnection2.connect();
					BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection2.getInputStream()));
						InputStream is2;
					
					if (urlConnection2.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
					     is2 = urlConnection2.getInputStream();
					} else {
					     /* error from server */
					     is2 = urlConnection2.getErrorStream();
					}
					// is2 = urlConnection2.getInputStream();
					
					System.out.println(is2.toString());
					InputStreamReader isr2 = new InputStreamReader(is2);

					System.out.println("Deleting MarketPlace Appointment: " + JData.getJSONObject(i).getJSONObject("booking").get("message").toString());
					Sleep(3);
				}
				}
			}
			
			
			 
		} catch (Exception e) {
			System.out.println("The program threw an Exception in the Reset Appointments due to: "+e.getMessage());

		}
	}
	
	public JSONObject FindProfessionalByID(String ID)
	{
		try {

			//	String ProductQuery = "http://my-api.dev.vaniday.com/salons/24ccfb06-5a18-4c8a-af93-3962e7005fe1/products?offset=0&limit=100";
				String Query ="https://my-api-staging.vaniday.com/professionals?salonId=83da9e51-4bb4-4f7d-a8b7-e5472ac02b22";

				URL url = new URL(Query);
				URLConnection urlConnection = url.openConnection();
				urlConnection.setRequestProperty("Authorization", "Bearer " + AuthToken);
				InputStream is = urlConnection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);

				int numCharsRead;
				char[] charArray = new char[1024];
				StringBuffer sb = new StringBuffer();
				while ((numCharsRead = isr.read(charArray)) > 0) {
					sb.append(charArray, 0, numCharsRead);
				}
				String result = sb.toString();

				// System.out.println(result);
				JSONArray JData = new JSONArray(result);
				 
				for (int i = 0; i < JData.length(); i++) {
					
					if(JData.getJSONObject(i).getString("id").contains(ID))
					{
						return JData.getJSONObject(i);
					}
					if(JData.getJSONObject(i).getString("firstName").contains(ID))
					{
						return JData.getJSONObject(i);
					}
				}
				
		}
		catch(Exception e)
		{
			TestFailed(e);
		}
		
		return null;

	}
	
	public void SignInMarketPlace()
	{

try {
		
		driver.get("https://rocket:rock4me@www-staging.vaniday.com.au/");
		Sleep(5);
		driver.get("https://www-staging.vaniday.com.au/login/");
		
		Sleep(5 );
		
		

		driver.findElement(By.name("email")).sendKeys("arsal.jalib@vaniday.com");
		
		if(driver.findElements(By.name("password")).size() != 0) // Checking if the user is already signed in or not
		{
			driver.findElement(By.name("password")).sendKeys("vaniday");

			// driver.findElement(By.name("login")).click();

			driver.findElement(By.cssSelector("#app > div.ng-scope.main > section > div > login-form > div > form > fieldset > div.input-group > button")).click();

			}		
		
		Sleep(8);
	
		MKTAuthToken =  GetAuthenticationToken(((JavascriptExecutor) driver));
		driver.get("https://www-staging.vaniday.com.au/salons/vanidateau");
		Sleep(5);
}
catch(Exception e)
{
	TestFailed(e);
}
		 
	}
	
	public static String ExecuteQuery(String Query)
	{
try {
	
		URL url = new URL(Query);
		URLConnection urlConnection = url.openConnection();
		urlConnection.setRequestProperty("Authorization", "Bearer " + AuthToken);
		InputStream is = urlConnection.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);

		int numCharsRead;
		char[] charArray = new char[1024];
		StringBuffer sb = new StringBuffer();
		while ((numCharsRead = isr.read(charArray)) > 0) {
			sb.append(charArray, 0, numCharsRead);
		}
		String result = sb.toString();
		
		return result;
}
catch(Exception e)
{
	e.printStackTrace();
}
return null;
	}
	
	public static void ExecuteBlockTimeQuery(String StartAt, String EndAt, String ProfID)
	{
try {
	
		URL url = new URL("http://my-api-staging.vaniday.com/block-off-time");
	 
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestProperty("Authorization", "Bearer " + AuthToken);
		urlConnection.setDoInput(true);
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");

		urlConnection.connect();

		OutputStream os = urlConnection.getOutputStream();
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
		
		String Payload="{\"startAt\":\"2017-08-23 17:30:00\",\"endAt\":\"2017-08-23 19:30:00\",\"professionalId\":\"1982e260-c1dd-41d4-8acf-e3f4a05481d9\"} ";
		pw.write(Payload);
		pw.close();
		
		
}
catch(Exception e)
{
	System.out.println("The program threw an Exception in the Execute Block Time Query due to: "+e.getMessage());
}

	}

	public JSONObject FindClient(String ID)
{
	try {
	String Query ="https://my-api-staging.vaniday.com/contacts?salonId=83da9e51-4bb4-4f7d-a8b7-e5472ac02b22";
	String result = ExecuteQuery(Query);
	
	JSONObject jobj = new JSONObject(result);
	JSONArray jArray= jobj.getJSONArray("c");
 for(int i=0; i<jArray.length();i++)
	{
	if(jArray.getJSONObject(i).getString("firstName").contains(ID))
	{
		
 	return jArray.getJSONObject(i);
	}
	}
	return null;
	}
	catch(Exception e)
	{
		System.out.println("The program threw an Exception in the FIND CLIENT due to: "+e.getMessage());
	}
	return null;
}

	public ArrayList<JSONObject> VerifyClients(String ID)
{
	try {
	String Query ="https://my-api-staging.vaniday.com/contacts?salonId=83da9e51-4bb4-4f7d-a8b7-e5472ac02b22";
	String result = ExecuteQuery(Query);
	
	ArrayList<JSONObject> returnList= new ArrayList();
	
	JSONObject jobj = new JSONObject(result);
	JSONArray jArray= jobj.getJSONArray("c");
	for(int i=0; i<jArray.length();i++)
	{
	if(jArray.getJSONObject(i).getString("firstName").contains(ID))
	{
		returnList.add(jArray.getJSONObject(i));
 	}
	}
	return returnList;
	}
	catch(Exception e)
	{
		System.out.println("The program threw an Exception in Verify Clients due to: "+e.getMessage());

	}
	return null;
}

public JSONObject FindServiceByID(String ID)
{
	try {

		//	String ProductQuery = "http://my-api.dev.vaniday.com/salons/24ccfb06-5a18-4c8a-af93-3962e7005fe1/products?offset=0&limit=100";
			String Query ="http://my-api-staging.vaniday.com/sections?salonId=24ccfb06-5a18-4c8a-af93-3962e7005fe1";

			URL url = new URL(Query);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Bearer " + AuthToken);
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			String result = sb.toString();

			// System.out.println(result);
			JSONArray JData = new JSONArray(result);
			 
			for (int i = 0; i < JData.length(); i++) {
				
				 JSONArray Services= JData.getJSONObject(i).getJSONArray("services");
				 
				 for (int j = 0; j < Services.length(); j++)
				 {
					 if(Services.getJSONObject(j).getString("name").contains(ID))
					 	{
						 	return Services.getJSONObject(j);
					 	}
				 }
			}
			
	}
	catch(Exception e)
	{
		TestFailed(e);
	}
	
	return null;

}

public boolean VerifyNotificationEmail(String  Date, String Time,String EmailType)
{
	try {
		String DateTime= Date.replace("-", " ")+" "+Time;
		 if(DateTime.charAt(0)=='0')
		 	{
     	 	DateTime= DateTime.substring(1);
		 	}
		
		 //create properties field
		Properties properties = new Properties();

	      properties.put("mail.pop3.host", "pop.gmail.com");
	      properties.put("mail.pop3.port", "995");
	      properties.put("mail.pop3.starttls.enable", "true");
	      Session emailSession = Session.getDefaultInstance(properties);
	  
	      //create the POP3 store object and connect with the pop server
	      Store store = emailSession.getStore("pop3s");

	      store.connect("pop.gmail.com", "arsal.jalib@vaniday.com", "lenovo-4530s");
	      

	      //create the folder object and open it
	      Folder emailFolder = store.getFolder("INBOX");
	      emailFolder.open(Folder.READ_ONLY);

	      SearchTerm term = new SearchTerm() {
	    	    public boolean match(Message message) {
	    	        try {
	    	            if (message.getFrom()[0].toString().contains("info.vaniday")) {
	    	                return true;
	    	            }
	    	        } catch (MessagingException ex) {
	    	            ex.printStackTrace();
	    	        }
	    	        return false;
	    	    }
	    	};
	      
	      
	      // retrieve the messages from the folder in an array and print it
	    //  Message messages[] = emailFolder.search(new FlagTerm(new Flags( Flags.Flag.SEEN), false));
	      Message messages[] = emailFolder.search(term);

	      System.out.println("messages.length---" + messages.length);

	      for (int i = 0, n = messages.length; i < n; i++) {
	         Message message = messages[i];
	     
	         
	         
	         String result = "";
	         if (message.isMimeType("text/plain")) {
	             result = message.getContent().toString();
	         } else if (message.isMimeType("multipart/*")) {
	             MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	             int count = mimeMultipart.getCount();
	             for (int j = 0; j < count; j++) {
	                 BodyPart bodyPart = mimeMultipart.getBodyPart(j);
	                 if (bodyPart.isMimeType("text/plain")) {
	                     result = result + "\n" + bodyPart.getContent();
	                     break; // without break same text appears twice in my tests
	                 } else if (bodyPart.isMimeType("text/html")) {
	                     String html = (String) bodyPart.getContent();
	                     result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	                 } 
	                 //else if (bodyPart.getContent() instanceof MimeMultipart){
	                //     result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	                 }
	         }
	       System.out.println("Text: " +result);
	         
	        
	         if(result.contains(EmailType)==true && result.replace(".", "").contains(DateTime)== true)
	         {
	        	 return true;
	         }

	      }

	      //close the store and folder objects
	      emailFolder.close(false);
	      store.close();

   
    } catch (Exception e) {
		System.out.println("The program threw an Exception in Verify Email Notification due to: "+e.getMessage());
    }
	
	
	
	return false;
}

}
