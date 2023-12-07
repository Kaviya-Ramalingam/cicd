package SeleniumFramework.testcomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFramework.pageobject.LandingPage;

public class BaseTest {
	
 public  WebDriver driver;
	public static LandingPage lp;
	
	protected static 
    ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initializedriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("/Users/uvaraj/eclipse-workspace/SeleniumFramework/src/test/java/SeleniumFramework/resources/globaldata.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		//threadLocalDriver.set(driver);
		if(browserName.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
		threadLocalDriver.set(driver);
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		getDriver().manage().window().maximize();
		}
		else if(browserName.equalsIgnoreCase("firefox"))  {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))  {
			// edge
		}
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	public List<HashMap<String, String>> getJsonDataMap(String filepath) throws IOException {
		//read json to string
	String jsonContent =	FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
	//string to hashmap jackson databind
	
	ObjectMapper mapper = new ObjectMapper();
	List <HashMap<String,String >> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>(){	
	});
	return data;
	}
	

public  String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {

	TakesScreenshot screenshot = (TakesScreenshot) driver;
	File src = screenshot.getScreenshotAs(OutputType.FILE);
	String dir = System.getProperty("user.dir");
	String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
	File target = new File(dir + "//reports//" + testCaseName + "_" + timestamp + ".jpeg");
	FileUtils.copyFile(src, target);
	return dir + "//reports//" + testCaseName + ".jpeg";
	//return target.getAbsolutePath();

}

public static WebDriver getDriver(){
    return threadLocalDriver.get();
}
@BeforeMethod(alwaysRun = true)	

public LandingPage launchApplication() throws IOException {
	
     initializedriver();
     //threadLocalDriver.set(driver);
     System.out.println("Before Test Thread ID: "+Thread.currentThread().getId());
      lp = new LandingPage(getDriver());
      getDriver().get("https://rahulshettyacademy.com/client");
     //lp.goTo();
     return lp;
}

@AfterMethod(alwaysRun = true)	
public void teardown() {
	getDriver().quit();
	 System.out.println("After Test Thread ID: "+Thread.currentThread().getId());
	 threadLocalDriver.remove();
}
}
