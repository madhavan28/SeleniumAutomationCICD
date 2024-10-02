package MPTech.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import MPTech.TestComponent.BaseTest;
import MPTech.pageobject.Landingpage;
import MPTech.pageobject.ProductCatalogue;
import MPTech.pageobject.cartpage;
import MPTech.pageobject.checkoutpage;
import MPTech.pageobject.confirmationpage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest {
	public Landingpage landingpage;
	public ProductCatalogue productCatalogue;
	public confirmationpage confirmpage;
	@Given("I landed on the ecommerce page")
	public void I_landed_on_the_ecommerce_page() throws IOException {
		landingpage=launchapplicatoin();
	}

	@Given("^Logged in with username(.+) and password(.+)$")
	public void Logged_in_with_username_and_password(String username,String password){
	
		productCatalogue=landingpage.loginapplication(username,password);
	}
	
	@When ("^I add product(.+) to cart$")
	public void I_add_product_to_cart(String productname) throws InterruptedException {
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productname);
		Thread.sleep(2000);
	
	}
	
	@And ("^Checkout(.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productname) throws InterruptedException {
		cartpage CartPage=productCatalogue.gotoCartPage(); 
		Boolean match=CartPage.VerifyProductDisplay(productname);
		Assert.assertTrue(match);
		CartPage.goToCheckout();
		Thread.sleep(5000);
		checkoutpage outpage=new checkoutpage(driver);
		outpage.selectcountry("India");
		confirmpage=outpage.submitorder();
	}
	
	@Then ("{string} message is displayed on confirmation page")
	public void message_displayed_on_confirmationpage(String string) {
		
		String confirmessage=confirmpage.getconfirmationmessage();
		Assert.assertTrue(confirmessage.equalsIgnoreCase(string));
	}
	
}
