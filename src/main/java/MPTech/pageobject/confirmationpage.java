package MPTech.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MPTech.AbstractComponents.abstractcomponents;

public class confirmationpage extends abstractcomponents{
	WebDriver driver;
	public confirmationpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".hero-primary")
	WebElement confirmessage;
	
	public String getconfirmationmessage(){
		
		String message=confirmessage.getText();
		return message;
		
	}
	
}
