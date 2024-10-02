package MPTech.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MPTech.AbstractComponents.abstractcomponents;

public class ProductCatalogue extends abstractcomponents {
	
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(css=".mb-3")
	List<WebElement>products;
	
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=By.cssSelector(".mb-3");
	By addtocart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitforElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productname) {
		
		WebElement prod=getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productname) {
		
		WebElement prod=getProductByName(productname);
		prod.findElement(addtocart).click();
		waitforElementToAppear(toastMessage);
		waitforElementToDisappear(spinner);
		
	}
	
	
	

}
