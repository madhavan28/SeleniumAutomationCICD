package MPTech.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MPTech.TestComponent.BaseTest;
import MPTech.pageobject.Landingpage;
import MPTech.pageobject.Orderpage;
import MPTech.pageobject.ProductCatalogue;
import MPTech.pageobject.cartpage;
import MPTech.pageobject.checkoutpage;
import MPTech.pageobject.confirmationpage;

public class Originalcode2 extends BaseTest {
	//	new comments are added here
	public String productName="ADIDAS ORIGINAL";
	
	@Test(dataProvider="getData",groups= {"Purchaseorder"})
	
	public void submitorder(HashMap<String,String> input) throws InterruptedException, IOException{
		// TODO Auto-generated method stub

		//this is a latest code change for demo
		String countryName="Ind";
		ProductCatalogue productCatalogue=landingpage.loginapplication(input.get("email"),input.get("password"));
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		Thread.sleep(2000);
		//Accessing the gotocartpage from abstractcomponent class using product class since 
		//it is the child class of abstractcomponent class , child class will have access to all parent classes
		cartpage CartPage=productCatalogue.gotoCartPage(); 
		Boolean match=CartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CartPage.goToCheckout();
		Thread.sleep(5000);
		checkoutpage outpage=new checkoutpage(driver);
		outpage.selectcountry(countryName);
		confirmationpage confirmpage=outpage.submitorder();
		String confirmessage=confirmpage.getconfirmationmessage();
		Assert.assertTrue(confirmessage.equalsIgnoreCase("Thankyou for the order."));
		
	}

	@Test(dependsOnMethods="submitorder")
	public void productvalidation() {
		
		ProductCatalogue pp=landingpage.loginapplication("madhavan@gmail.com", "Madhavan@28");
		Orderpage orderpage=pp.gotoOrderspage();
		Assert.assertTrue(orderpage.VerifyProductDisplay(productName));
		
		
	}
	
	
	@DataProvider
	public Object[][]  getData() throws IOException {
		

		
		DataReader dr=new DataReader();
		List<HashMap<String, String>> data=dr.getJsonDataToMap("C:\\Users\\mparthasarathi\\eclipse-workspace\\Selfd\\src\\test\\java\\MPTech\\Data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
}


//HashMap<String,String> map=new HashMap<String,String>();
//map.put("email", "madhavan@gmail.com");
//map.put("password", "Madhavan@28");
//map.put("product", "ZARA COAT 3");
//
//HashMap<String,String> map1=new HashMap<String,String>();
//map1.put("email", "bm@gmail.com");
//map1.put("password", "Stonecold@0");
//map1.put("product", "IPHONE 13 PRO");