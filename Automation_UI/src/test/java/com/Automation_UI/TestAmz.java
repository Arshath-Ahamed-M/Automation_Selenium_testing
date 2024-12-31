package com.Automation_UI;

import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.net.HttpURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TestAmz {
	
	WebDriver Drv;
	public int GetResponseCode(String Link) throws IOException {
		URL AmzLink = new URL(Link);
		  HttpURLConnection conc = (HttpURLConnection) AmzLink.openConnection();
		  conc.setRequestMethod("GET");
		  conc.connect();
		  int ResCode = conc.getResponseCode();
		  conc.disconnect();
		return ResCode;
		
	}
    @BeforeMethod
	public void Setup() {
		Drv = new ChromeDriver();
		Drv.manage().window().maximize();
		Drv.get("https://www.amazon.in");
	}
//    *******CASE 1******* 
    @Test(priority=1)
    public void TestUrl() throws IOException {
//    	Drv.get("https://www.amazon.in");
    	try {
    		String CurUrl = "https://www.amazon.in";
        	System.out.println("\nThis is the URL : "+CurUrl+"\n");
        	int response_Code = GetResponseCode(CurUrl);
        	System.out.println("Response Code : "+response_Code+"\n");
        	List<Integer> Codes = Arrays.asList(200 , 301 , 302 , 305);
        	Assert.assertTrue(Codes.contains(response_Code),"Page not working of this Response_Code : "+response_Code);
        	if(response_Code == 200 || response_Code ==301 || response_Code ==302 || response_Code == 305 ) {
        		System.out.println("page will work properly in server side\n");
        	}
        	else {
        		System.out.println("page will not work properly in server side\n");
        		
        	}
        	String CurTitle = Drv.getTitle();
        	System.out.println("This is the Title : "+CurTitle+"\n");
        	if(CurTitle.equals("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in")) {
        		System.out.println("The page title will show correctly");
        	}
        	else {
        		System.out.println("The page title will not show correctly\n");
        		
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
       }
    
    @Test(priority=2)
    public void Login() throws Exception {
    	try {
    		WebElement Lgbutton = Drv.findElement(By.xpath("//span[text()='Hello, sign in']"));
        	Lgbutton.click();
        	WebElement UserCred = Drv.findElement(By.xpath("//input[@id='ap_email']"));
        	UserCred.sendKeys("ahamedarshath920@gmail.com");
        	WebElement UserCredButt = Drv.findElement(By.xpath("//input[@id='continue']"));
        	UserCredButt.click();
        	WebElement UserPass = Drv.findElement(By.xpath("//input[@id='ap_password']"));
        	UserPass.sendKeys("AutomationTesting");
        	WebElement UserPassButt = Drv.findElement(By.xpath("//input[@id='signInSubmit']"));
        	UserPassButt.click();    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    //CASE-3
    @Test(priority=3)
    public void LogOut() throws Exception{
    	try {
    		WebElement Lgbutton = Drv.findElement(By.xpath("//a[@id='nav-link-accountList']"));
        	Lgbutton.click();
        	WebElement UserCred = Drv.findElement(By.xpath("//input[@id='ap_email']"));
        	UserCred.sendKeys("ahamedarshath920@gmail.com");
        	WebElement UserCredButt = Drv.findElement(By.xpath("//input[@id='continue']"));
        	UserCredButt.click();
        	WebElement UserPass = Drv.findElement(By.xpath("//input[@id='ap_password']"));
        	UserPass.sendKeys("AutomationTesting");
        	WebElement UserPassButt = Drv.findElement(By.xpath("//input[@id='signInSubmit']"));
        	UserPassButt.click();    	
     
            Lgbutton = Drv.findElement(By.xpath("//a[@id='nav-link-accountList']"));
            Actions actions = new Actions(Drv);
            actions.moveToElement(Lgbutton);
            actions.perform();
        	WebElement signout = Drv.findElement(By.xpath("//span[text()='Sign Out']"));
        	signout.click();
        	Drv.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
		}	
    }
    @Test(priority=4)
    public void Search() {
    	try {
			WebElement searchBar = Drv.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
			searchBar.sendKeys("poco f3 gt");
			searchBar.sendKeys(Keys.ENTER);  // ****here we can use this method or the below method****
//			WebElement searchIcon = Drv.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
//			searchIcon.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    @Test(priority=5)
    public void AddToCart() {
    	try {
    		WebElement searchBar = Drv.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
			searchBar.sendKeys("Moto edge 50 fusion");
			searchBar.sendKeys(Keys.ENTER);
			WebElement AddtocartButt = Drv.findElement(By.xpath("//span[@id='a-autoid-1']"));
			AddtocartButt.click();
			WebElement Addtocartview = Drv.findElement(By.xpath("//a[@id='nav-cart']"));
			Addtocartview.click();
			Drv.navigate().to("https://www.amazon.in");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	@Test(priority=6)
    public void PlaceOrder() {
    	try {
    		WebElement Lgbutton = Drv.findElement(By.xpath("//a[@id='nav-link-accountList']"));
        	Lgbutton.click();
        	WebElement UserCred = Drv.findElement(By.xpath("//input[@id='ap_email']"));
        	UserCred.sendKeys("ahamedarshath920@gmail.com");
        	WebElement UserCredButt = Drv.findElement(By.xpath("//input[@id='continue']"));
        	UserCredButt.click();
        	WebElement UserPass = Drv.findElement(By.xpath("//input[@id='ap_password']"));
        	UserPass.sendKeys("AutomationTesting");
        	WebElement UserPassButt = Drv.findElement(By.xpath("//input[@id='signInSubmit']"));
        	UserPassButt.click();    	
    		
    		WebElement searchBar = Drv.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
			searchBar.sendKeys("Realme P1 5g");
			searchBar.sendKeys(Keys.ENTER);
			WebElement AddtocartButt = Drv.findElement(By.xpath("//span[@id='a-autoid-1']"));
			AddtocartButt.click();
			WebElement Addtocartview = Drv.findElement(By.xpath("//a[@id='nav-cart']"));
			Addtocartview.click();
			WebElement ViewProdButt = Drv.findElement(By.xpath("//img[@alt='realme P1 5G (Phonix Red, 6GB RAM, 128GB Storage), Opens in a new tab']"));
			ViewProdButt.click();
//			String originalWindow = Drv.getWindowHandle();  // Store the original window handle
			Set<String> allWindows = Drv.getWindowHandles(); // Get all open windows
			List<String>AllWindows = new ArrayList<>(allWindows);
	        Drv.switchTo().window(AllWindows.get(1));  // Switch to the new tab
//			System.out.println("here wrk0");
			Thread.sleep(5000);
			WebElement Foract = Drv.findElement(By.xpath("//h5[text()='Offers']"));
			JavascriptExecutor js = (JavascriptExecutor) Drv;
			js.executeScript("arguments[0].scrollIntoView(true);", Foract);
			WebElement BuyProdButt = Drv.findElement(By.xpath("//input[@id='buy-now-button']"));
			BuyProdButt.click();
			WebElement Anotherpaybutt = Drv.findElement(By.xpath(" //span[text()=' Another payment method']"));
//			JavascriptExecutor js1 = (JavascriptExecutor) Drv;
//			js1.executeScript("arguments[0].scrollIntoView(true);", Anotherpaybutt);
			System.out.println("here wrk1");
			WebElement UsethisPaymentButt = Drv.findElement(By.xpath("(//input[@name='ppw-widgetEvent:SetPaymentPlanSelectContinueEvent'])[1]"));
			System.out.println("here wrk2");
			Actions ac = new Actions(Drv);
			ac.scrollToElement(UsethisPaymentButt).perform();
			UsethisPaymentButt.click();
			
//			System.out.println("here wrk2");
//			Drv.navigate().to("https://www.amazon.in");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    @Test(priority = 7)
	public void PaymentGateway() {
		try {
    		WebElement Lgbutton = Drv.findElement(By.xpath("//a[@id='nav-link-accountList']"));
        	Lgbutton.click();
        	WebElement UserCred = Drv.findElement(By.xpath("//input[@id='ap_email']"));
        	UserCred.sendKeys("ahamedarshath920@gmail.com");
        	WebElement UserCredButt = Drv.findElement(By.xpath("//input[@id='continue']"));
        	UserCredButt.click();
        	WebElement UserPass = Drv.findElement(By.xpath("//input[@id='ap_password']"));
        	UserPass.sendKeys("AutomationTesting");
        	WebElement UserPassButt = Drv.findElement(By.xpath("//input[@id='signInSubmit']"));
        	UserPassButt.click();    	
    		
    		WebElement searchBar = Drv.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
			searchBar.sendKeys("Realme P1 5g");
			searchBar.sendKeys(Keys.ENTER);
			WebElement AddtocartButt = Drv.findElement(By.xpath("//span[@id='a-autoid-1']"));
			AddtocartButt.click();
			WebElement Addtocartview = Drv.findElement(By.xpath("//a[@id='nav-cart']"));
			Addtocartview.click();
			WebElement ViewProdButt = Drv.findElement(By.xpath("//img[@alt='realme P1 5G (Phonix Red, 6GB RAM, 128GB Storage), Opens in a new tab']"));
			ViewProdButt.click();
//			String originalWindow = Drv.getWindowHandle();  // Store the original window handle
			Set<String> allWindows = Drv.getWindowHandles(); // Get all open windows
			List<String>AllWindows = new ArrayList<>(allWindows);
	        Drv.switchTo().window(AllWindows.get(1));  // Switch to the new tab
//			System.out.println("here wrk0");
			Thread.sleep(5000);
			WebElement Foract = Drv.findElement(By.xpath("//h5[text()='Offers']"));
			JavascriptExecutor js = (JavascriptExecutor) Drv;
			js.executeScript("arguments[0].scrollIntoView(true);", Foract);
			WebElement BuyProdButt = Drv.findElement(By.xpath("//input[@id='buy-now-button']"));
			BuyProdButt.click();
			
			WebElement ClickRadioButt = Drv.findElement(By.xpath("//input[@value='SelectableAddCreditCard']"));
			ClickRadioButt.click();
			WebElement ClickPaymentLink = Drv.findElement(By.linkText("Enter card details"));
			ClickPaymentLink.click();
			Thread.sleep(5000);
//		************ ERROR NoSuchElement 	WebElement INpaymentCancelButt = Drv.findElement(By.xpath("//span[@class='a-button a-button-base pmts-button-input']"));
//			INpaymentCancelButt.click();     *********** ERROR NoSuchElement 
//			WebElement UsethisPaymentButt = Drv.findElement(By.xpath("(//input[@name='ppw-widgetEvent:SetPaymentPlanSelectContinueEvent'])[1]"));
//			Actions ac = new Actions(Drv);
//			ac.scrollToElement(UsethisPaymentButt).perform();
//			UsethisPaymentButt.click();
			
			Drv.navigate().to("https://www.amazon.in");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    @Test(priority = 8)
    public void CheckCartItemsPresent() {
    	try {
    		WebElement Lgbutton = Drv.findElement(By.xpath("//a[@id='nav-link-accountList']"));
        	Lgbutton.click();
        	WebElement UserCred = Drv.findElement(By.xpath("//input[@id='ap_email']"));
        	UserCred.sendKeys("ahamedarshath920@gmail.com");
        	WebElement UserCredButt = Drv.findElement(By.xpath("//input[@id='continue']"));
        	UserCredButt.click();
        	WebElement UserPass = Drv.findElement(By.xpath("//input[@id='ap_password']"));
        	UserPass.sendKeys("AutomationTesting");
        	WebElement UserPassButt = Drv.findElement(By.xpath("//input[@id='signInSubmit']"));
        	UserPassButt.click();    	
        	WebElement searchBar = Drv.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
			searchBar.sendKeys("Realme P1 5g");
			searchBar.sendKeys(Keys.ENTER);
			WebElement AddtocartButt = Drv.findElement(By.xpath("//span[@id='a-autoid-1']"));
			AddtocartButt.click();
			  Lgbutton = Drv.findElement(By.xpath("//a[@id='nav-link-accountList']"));
            Actions actions = new Actions(Drv);
            actions.moveToElement(Lgbutton);
            actions.perform();
        	WebElement signout = Drv.findElement(By.xpath("//span[text()='Sign Out']"));
        	signout.click();
        	Drv.navigate().back();
        	Lgbutton = Drv.findElement(By.xpath("//a[@id='nav-link-accountList']"));
        	Lgbutton.click();
        	UserCred = Drv.findElement(By.xpath("//input[@id='ap_email']"));
        	UserCred.sendKeys("ahamedarshath920@gmail.com");
        	UserCredButt = Drv.findElement(By.xpath("//input[@id='continue']"));
        	UserCredButt.click();
        	UserPass = Drv.findElement(By.xpath("//input[@id='ap_password']"));
        	UserPass.sendKeys("AutomationTesting");
        	UserPassButt = Drv.findElement(By.xpath("//input[@id='signInSubmit']"));
        	UserPassButt.click();    	
        	WebElement Addtocartview = Drv.findElement(By.xpath("//a[@id='nav-cart']"));
			Addtocartview.click();
			WebElement CartItem = Drv.findElement(By.xpath("//span[@class='a-truncate sc-grid-item-product-title a-size-base-plus']"));
			Assert.assertTrue(CartItem.isDisplayed(),"this prod is not visible");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	@Test (priority = 9)
    public void VerifyOrders() {
    	try {
    		WebElement Lgbutton = Drv.findElement(By.xpath("//a[@id='nav-link-accountList']"));
        	Lgbutton.click();
        	WebElement UserCred = Drv.findElement(By.xpath("//input[@id='ap_email']"));
        	UserCred.sendKeys("ahamedarshath920@gmail.com");
        	WebElement UserCredButt = Drv.findElement(By.xpath("//input[@id='continue']"));
        	UserCredButt.click();
        	WebElement UserPass = Drv.findElement(By.xpath("//input[@id='ap_password']"));
        	UserPass.sendKeys("AutomationTesting");
        	WebElement UserPassButt = Drv.findElement(By.xpath("//input[@id='signInSubmit']"));
        	UserPassButt.click();   
        	WebElement OdersButt = Drv.findElement(By.xpath("//a[@href='/gp/css/order-history?ref_=nav_orders_first']"));
        	OdersButt.click();
        	WebElement ChangeTimeperiod = Drv.findElement(By.xpath("//select[@id='time-filter']"));
        	Select Changetime = new Select(ChangeTimeperiod);
        	Changetime.selectByVisibleText("2023");
        	WebElement PastOders = Drv.findElement(By.xpath("//a[@href='/gp/product/B09RV5XNH3/ref=ppx_yo_dt_b_asin_title_o00_s00?ie=UTF8&psc=1']"));
        	Assert.assertTrue(PastOders.isDisplayed(),"the orders are displayed");
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	@Test(priority = 10)
	public void VerifyProductSearch() {
		try {
			WebElement Lgbutton = Drv.findElement(By.xpath("//a[@id='nav-link-accountList']"));
        	Lgbutton.click();
        	WebElement UserCred = Drv.findElement(By.xpath("//input[@id='ap_email']"));
        	UserCred.sendKeys("ahamedarshath920@gmail.com");
        	WebElement UserCredButt = Drv.findElement(By.xpath("//input[@id='continue']"));
        	UserCredButt.click();
        	WebElement UserPass = Drv.findElement(By.xpath("//input[@id='ap_password']"));
        	UserPass.sendKeys("AutomationTesting");
        	WebElement UserPassButt = Drv.findElement(By.xpath("//input[@id='signInSubmit']"));
        	UserPassButt.click();   
        	WebElement SearchBar = Drv.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        	SearchBar.sendKeys("Mobile Phones");
//        	SearchBar.submit();
        	Thread.sleep(5000); // Wait for the results to refresh
        	List<WebElement> sugg = Drv.findElements(By.xpath("//div[@class=\"s-suggestion s-suggestion-ellipsis-direction\"]"));
        	Assert.assertTrue(sugg.size() > 0, "No search suggestions appeared");
        	boolean same = sugg.stream().allMatch(suggs->suggs.getText().toLowerCase().contains("mobile"));
        	Assert.assertTrue(same 	, "No same matches are there");
        	sugg.get(0).click();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
    @AfterMethod
    public void ClosePage() {
//    	Thread.sleep(7000);
    	Drv.quit();
    }
}










//   ************P2 case 10************
//WebElement priceJs = Drv.findElement(By.xpath("//span[text()='Price']"));
//JavascriptExecutor js = (JavascriptExecutor) Drv;
//js.executeScript("arguments[0].scrollIntoView(true);",priceJs );
//Thread.sleep(5000); // Wait for the results to refresh
//// WebElement priceFilter = Drv.findElement(By.xpath("//input[@id=\'p_36/range-slider_slider-item_lower-bound-slider\']"));
// Thread.sleep(5000); // Wait for the results to refresh
////	 Actions Act = new Actions(Drv);
////	Act.dragAndDropBy(priceFilter, 200, 0).perform();
//// JavascriptExecutor js1 = (JavascriptExecutor) Drv;
//// js1.executeScript("arguments[0].setAttribute('value', '5000')", priceFilter);
// List<WebElement> iframes = Drv.findElements(By.tagName("iframe"));
// for (WebElement iframe : iframes) {
//	    Drv.switchTo().frame(iframe);
//	    List<WebElement> slider = Drv.findElements(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div[3]/span/div[1]/span/div/div/div[3]/div/div[2]/div[1]/form/div[2]/div[1]/div[1]/input"));
//	    if (!slider.isEmpty()) {
//	        System.out.println("Slider found inside an iframe.");
//	        break;
//	    }


