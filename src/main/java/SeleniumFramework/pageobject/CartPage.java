package SeleniumFramework.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumFramework.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	
WebDriver driver;
	
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
@FindBy(xpath = "//ul/li[4]/button[@class ='btn btn-custom']" )	
WebElement cartButton;

@FindBy(css = "[routerlink*='cart']")
WebElement cartheader;

@FindBy(xpath = "//div[@class='cartSection']/h3")	
List<WebElement> cartProducts;

@FindBy(xpath= "//ul/li[3]/button[@class ='btn btn-primary']")
WebElement checkout;


     public void goToCart() {
	     cartButton.click();
      }
	
	
	public boolean verifyProduct(String productName) {
	
	for(WebElement cartProduct:cartProducts) {
	boolean match =	cartProduct.getText().equalsIgnoreCase(productName);
	return match;
	}
	return false;

}
	public void checkout() {
		checkout.click();
	}
}