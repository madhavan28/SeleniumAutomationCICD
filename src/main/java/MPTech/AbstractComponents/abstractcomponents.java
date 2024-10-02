package MPTech.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MPTech.pageobject.Orderpage;
import MPTech.pageobject.cartpage;

public class abstractcomponents {

	WebDriver driver;


	public abstractcomponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	
	public void waitforElementToAppear(By findBy) {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	
	public void waitforWebElementToAppear(WebElement findBy) {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	
	}
	
	public void waitforElementToDisappear(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	
	public cartpage gotoCartPage() {
		
		cartHeader.click();
		cartpage CartPage=new cartpage(driver);
		return CartPage;
	}
	
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement myorders;
	
	
	public Orderpage gotoOrderspage() {
		
		myorders.click();
		//Orderpage orderpage=new Orderpage(driver);
		return new Orderpage(driver);
	}
	
}
