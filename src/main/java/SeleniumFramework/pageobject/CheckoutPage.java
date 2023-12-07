package SeleniumFramework.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	
WebDriver driver;
	
	
	public CheckoutPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By countryele = By.className("ta-results");
	
	@FindBy(xpath = "//div[@class='form-group']/input)")
	WebElement Country;
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryfield;
	
	@FindBy(xpath = "//div[@class='form-group']/section/button[2]")
	WebElement selectCountry;
	
	@FindBy(css = "a[class*='action__submit']")
	WebElement submit;
	
	
	public void selectCountry(String countryName) {
		
	/*Actions a = new Actions(driver);
	a.sendKeys(Country, countryName).build().perform();*/
	
	countryfield.sendKeys(countryName);
	waitForElementToAppear(countryele);
	selectCountry.click();
	}
	
	public void submitOrder() {
		submit.click();
		
	}
}
