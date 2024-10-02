package MPTech.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import MPTech.TestComponent.BaseTest;
import MPTech.TestComponent.retry;
import MPTech.pageobject.Landingpage;
import MPTech.pageobject.ProductCatalogue;
import MPTech.pageobject.cartpage;
import MPTech.pageobject.checkoutpage;
import MPTech.pageobject.confirmationpage;

public class Errorvalidation extends BaseTest {

	@Test(retryAnalyzer=retry.class)
	
	public void asubmitorder() throws InterruptedException, IOException{
		// TODO Auto-generated method stub


		landingpage.loginapplication("madhavan@gmail.com", "Badhavan@28");
		Assert.assertEquals("Incorrect email or password.", landingpage.gettheErrormessage());

		}
	
	@Test(retryAnalyzer=retry.class)
	public void productvalidation() throws InterruptedException {
		String productName="ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue=landingpage.loginapplication("madhavan@gmail.com", "Madhavan@28");
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		Thread.sleep(2000);
		cartpage CartPage=productCatalogue.gotoCartPage(); 
		Boolean match=CartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
	}

}
