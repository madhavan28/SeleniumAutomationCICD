package MPTech.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import MPTech.AbstractComponents.abstractcomponents;

public class checkoutpage extends abstractcomponents{
	
	WebDriver driver;
	public checkoutpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	By results=By.cssSelector(".ta-results");
	
	@FindBy(xpath="//div[@class='form__cc']/div[2]/div[2]/input")
	WebElement cvv;
	
	@FindBy(xpath="//div[@class='form__cc']/div[3]/div/input")
	WebElement nameoncard;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement Country;
	
	
	//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	
//	WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
//	@FindBy(css=".action__submit")
//	WebElement placeOrder;
	
//	JavascriptExecutor js1 = (JavascriptExecutor) driver;
//	js1.executeScript("arguments[0].click();", placeOrder);
	
	
	public void selectcountry(String countryName) throws InterruptedException {
	
		cvv.sendKeys("477");
		nameoncard.sendKeys("Madhavan Parthasarathi");
		Thread.sleep(5000);
		Actions a = new Actions(driver);
	    a.sendKeys(Country, countryName).build().perform();
	    waitforElementToAppear(results);
	    selectCountry.click();
		
		
	}
	
	public confirmationpage submitorder() {
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement ele = driver.findElement(By.cssSelector(".action__submit"));
		jse.executeScript("arguments[0].click()", ele);
		return new confirmationpage(driver);
		
	}
	

}
