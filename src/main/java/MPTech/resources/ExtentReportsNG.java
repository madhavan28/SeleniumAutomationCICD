package MPTech.resources;
import java.io.File;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	@Test
	public static ExtentReports getReportObject() {
	
		File file=new File(System.getProperty("user.dir")+"\\reports\\Index.html");
		ExtentSparkReporter reporter=new ExtentSparkReporter(file);
		reporter.config().setDocumentTitle("Framework Test Results");
		reporter.config().setReportName("Ecommerce Test cases");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA", "Madhavan");
		return extent;

		
		
		
	}
	

}
