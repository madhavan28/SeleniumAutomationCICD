package MPTech.tests;

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

public class Originalcode {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName="ZARA COAT 3";
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("madhavan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Madhavan@28");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		WebElement  element = driver.findElement(By.cssSelector(".totalRow button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		driver.findElement(By.xpath("//div[@class='form__cc']/div[2]/div[2]/input")).sendKeys("447");
		driver.findElement(By.xpath("//div[@class='form__cc']/div[3]/div/input")).sendKeys("Madhavan");
		Thread.sleep(5000);
	    Actions a = new Actions(driver);
	    a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "ind").build().perform();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		//driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].click();", placeOrder);
		String confirmessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();		
	}

}
