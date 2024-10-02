package MPTech.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MPTech.AbstractComponents.abstractcomponents;

public class Orderpage extends abstractcomponents{
	
	WebDriver driver;
	public Orderpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	
	@FindBy(xpath="//tr[contains(@class,'star')]/td[2]")
	List<WebElement> cartproducts;
	
	public Boolean VerifyProductDisplay(String productName) {
	
	Boolean match=cartproducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
	return match;
	
	}
	


}
