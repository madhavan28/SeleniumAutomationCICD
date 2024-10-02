package MPTech.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import MPTech.pageobject.Landingpage;

public class BaseTest {

	public WebDriver driver;
	public Landingpage landingpage;
	
	public WebDriver InitializeDriver() throws IOException {
		
		
		//Properties class
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\MPTech\\resources\\GlobalData.properties"); 
		prop.load(fis);
		String browsername=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		//prop.getProperty("browser");
		
		if (browsername.contains("chrome")) {			
			ChromeOptions options = new ChromeOptions();			
			//WebDriverManager.chromedriver().setup();			
			if(browsername.contains("headless")){			
			options.addArguments("--headless");					
			driver = new ChromeDriver(options);			
			}				
			driver = new ChromeDriver();			
			//driver.manage().window().setSize(new Dimension(1440,900));//full screen
					}
		
		else if(browsername.equalsIgnoreCase("FireFox")) {
		//	System.setProperty("webdriver.gecko.driver","C:\\Users\\mparthasarathi\\Documents\\geckodriver-v0.34.0-win64\\geckodriver.exe");
			driver= new FirefoxDriver();
		}
		
		else if (browsername.equalsIgnoreCase("Edge")) {
			driver=new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public String getScreenshot(String testcasename,WebDriver driver) throws IOException {
		
		TakesScreenshot ts=	(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+ testcasename+".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir")+"//reports//"+ testcasename+".png";
	}
	
	
	
	@BeforeMethod(alwaysRun=true)
	public Landingpage launchapplicatoin() throws IOException {
			
		driver=InitializeDriver();
		landingpage=new Landingpage(driver);
		landingpage.goTo();
		return landingpage;
		
	}


	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();	
	}
	
	public class DataReader {

		public List<HashMap<String, String>> getJsonDataToMap(String string) throws IOException {
			
			String jsonContent = FileUtils.readFileToString(new File(string),

					StandardCharsets.UTF_8);
		
		//String to HashMap Jackson Databind
			
			ObjectMapper mapper=new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	});
		
			return data;
		
}
}

}
