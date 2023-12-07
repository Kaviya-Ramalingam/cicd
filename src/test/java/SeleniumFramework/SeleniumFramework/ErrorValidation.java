package SeleniumFramework.SeleniumFramework;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.LandingPage;
import SeleniumFramework.pageobject.ProductCatalogue;
import SeleniumFramework.testcomponent.BaseTest;
import SeleniumFramework.testcomponent.RetryAnalyser;

public class ErrorValidation extends BaseTest{
	String productName = "ZARA COAT 3";
	String countryName = "india";
	//LandingPage lp = new LandingPage(driver);
	  //ProductCatalogue pc = new ProductCatalogue(driver);
      
	
@Test(retryAnalyzer = RetryAnalyser.class)

public void loginErrorValidation() throws IOException, InterruptedException {
		
		//launchApplication();
		//lp.goTo();
		lp.loginApplication("kdu@gmail.com", "Dhaksh@25");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
		
}
@Test
public void productErrorValidation() throws InterruptedException, IOException {
	
	//launchApplication();
	//lp.goTo();
	lp.loginApplication("kavi05@gmail.com", "Dhaksh@25");
	ProductCatalogue pc = new ProductCatalogue(getDriver());
       pc.getProductList();
       pc.getProductByName(productName);
       pc.addProductToCart(productName);
      CartPage cp = new CartPage(getDriver());
      cp.goToCart();
     boolean match = cp.verifyProduct("ZARA COAT 33");
     Assert.assertFalse(match);
}

}
