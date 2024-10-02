package MPTech.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MPTech.AbstractComponents.abstractcomponents;

public class Landingpage extends abstractcomponents {
	
	
	WebDriver driver;
	public Landingpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	public ProductCatalogue loginapplication(String email,String password) {
		
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();	
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	
	public String gettheErrormessage() {
		
		waitforWebElementToAppear(errormessage);
		return errormessage.getText();
		
	}
}
