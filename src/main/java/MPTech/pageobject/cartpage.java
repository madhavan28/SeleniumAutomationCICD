package MPTech.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MPTech.AbstractComponents.abstractcomponents;

public class cartpage extends abstractcomponents{
	
	WebDriver driver;
	public cartpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	public Boolean VerifyProductDisplay(String productName) {
	Boolean match=productTitles.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
	return(match);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public cartpage goToCheckout() {
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkoutEle);	
		return new cartpage(driver);
		
	}

}
