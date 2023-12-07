package SeleniumFramework.SeleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {
	
	public static void main (String args[]) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("dhaksh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Dhaksh@25");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement>products = driver.findElements(By.cssSelector(".mb-3"));
		//System.out.println(products.get(1).getText());
	for(WebElement Product:products) {
		//if(product co)
			Product.findElement(By.cssSelector(".mb-3:nth-child(1) .btn.w-10.rounded")).click();
			break;
	
	}
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	driver.findElement(By.xpath("//ul/li[4]/button[@class ='btn btn-custom']")).click();
	List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	for(WebElement cartProduct:cartProducts) {
	boolean match =	cartProduct.getText().equalsIgnoreCase("ZARA COAT 3");
	Assert.assertTrue(match);		
	
	}
	
	driver.findElement(By.xpath("//ul/li[3]/button[@class ='btn btn-primary']")).click();
	
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.xpath("//div[@class='form-group']/input")), "india").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ta-results")));
	driver.findElement(By.xpath("//div[@class='form-group']/section/button[2]")).click();
	driver.findElement(By.cssSelector("a[class*='action__submit']")).click();
	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
}
	
}
//div[@class='form-group']/section/button[2]