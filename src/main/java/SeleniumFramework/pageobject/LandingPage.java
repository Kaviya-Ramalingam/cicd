package SeleniumFramework.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;		
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id ="userPassword")
	WebElement PasswordEle;
	
	@FindBy(id = "login")
	WebElement Login;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public void loginApplication(String email,String password) {
		userEmail.sendKeys(email);
		PasswordEle.sendKeys(password);
		Login.click();
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	

	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
		
	}

}
