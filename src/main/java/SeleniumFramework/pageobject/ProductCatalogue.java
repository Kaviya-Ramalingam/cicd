package SeleniumFramework.pageobject;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	WebElement product;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = "..ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By toastMessage = By.cssSelector("#toast-container");
	By addToCart = By.cssSelector(".mb-3:nth-child(1) .btn.w-10.rounded");

		
	public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        List<WebElement> products = getProductList();
    	if(products!=null && !products.isEmpty()){
    	for (WebElement product : getProductList()) {
            String productText = product.findElement(By.cssSelector("b")).getText();
           // System.out.println(productText);
            if (productText!=null && productText.contains(productName)) {
                System.out.println(productText);
                //product.findElement(By.cssSelector(".mb-3:nth-child(1) .btn.w-10.rounded")).click();
                return product;
            }
    	}
        
        }
		return null;
    }

    public void addProductToCart(String productName) throws InterruptedException {
    	WebElement product = getProductByName(productName);
      
            product.findElement(addToCart).click();
            waitForElementToAppear(toastMessage);
            
       
        
     
		waitForElementToDisappear();
    }

 








}
