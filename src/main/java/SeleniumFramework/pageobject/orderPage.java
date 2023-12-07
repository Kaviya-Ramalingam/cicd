package SeleniumFramework.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class orderPage extends AbstractComponent{
WebDriver driver;
	
	
	public orderPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderheader;
	
	@FindBy(xpath = "//tr/td[2]")
	List<WebElement >productNames;
	
	 public void goToOrder() {
	     orderheader.click();
      }
	public boolean verifyOrderDisplay(String productName) {
		
		for(WebElement Product:productNames) {
		boolean match =	Product.getText().equalsIgnoreCase(productName);
		return match;
		}
		return false;

	}
}
