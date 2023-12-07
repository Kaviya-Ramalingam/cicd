package SeleniumFramework.SeleniumFramework;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.CheckoutPage;
import SeleniumFramework.pageobject.ConfirmationPage;
import SeleniumFramework.pageobject.LandingPage;
import SeleniumFramework.pageobject.ProductCatalogue;
import SeleniumFramework.pageobject.orderPage;
import SeleniumFramework.testcomponent.BaseTest;

public class SubmitOrderTest extends BaseTest{
	//WebDriver driver;
	//String productName = "ZARA COAT 3";
	String countryName = "india";
	
	//orderPage op = new orderPage(driver);
	
@Test(dataProvider="getData",groups= {"Purchase"})	
public void login(HashMap<String,String>input) throws IOException, InterruptedException {
		
		
	//LandingPage lp = launchApplication();
		//lp.goTo();
    // Step 1: Login
		lp.loginApplication(input.get("email"),input.get("password"));
		Assert.assertEquals("Login Successfully", lp.getErrorMessage());
		
		   // Step 2: Add product to cart	
        ProductCatalogue pc = new ProductCatalogue(driver);
       pc.getProductByName(input.get("productName"));
       pc.addProductToCart(input.get("productName"));
       //pc.getProductByName(input);
      // pc.addProductToCart(input);
       
       // Step 3: Go to Cart
      CartPage cp = new CartPage(getDriver());
      cp.goToCart();
     boolean match = cp.verifyProduct(input.get("productName"));
     Assert.assertTrue(match);
     
     // Step 4: Proceed to Checkout
        cp.checkout();
        
        // Step 5: Checkout process
      CheckoutPage checkPage = new CheckoutPage(getDriver());
    	    checkPage.selectCountry(countryName);
    	checkPage.submitOrder();

        // Step 6: Confirm order
      ConfirmationPage confirmPage = new ConfirmationPage(driver);
      String confirmMessage = confirmPage.getConfirmationMassage();
      Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    

}
@Test(dependsOnMethods = {"login"})
public void orderHistoryTest(String productName) {
	
	lp.loginApplication("dhaksh@gmail.com", "Dhaksh@25");
	System.out.println("successfully logged in");
	orderPage op = new orderPage(getDriver());
	
	op.goToOrder();
	Assert.assertTrue(op.verifyOrderDisplay(productName));
	
}

/**@throws IOException 
 * @DataProvider
public Object[][] getData() {
	
	HashMap<String,String>map = new HashMap<String,String>();
	map.put("email", "dhaksh@gmail.com");
	map.put("password", "Dhaksh@25");
	map.put("productName", "ZARA COAT 3");
	HashMap<String,String>map1 = new HashMap<String,String>();
	map1.put("email", "kavi05@gmail.com");
	map1.put("password", "Dhaksh@25");
	map1.put("productName", "ADIDAS ORIGINAL");
	
	return new Object[][] {{map},{map1}};
	
}**/
@DataProvider
public Object[][] getData() throws IOException

{
	List<HashMap<String,String>> data = getJsonDataMap("/Users/uvaraj/eclipse-workspace/SeleniumFramework/src/test/java/SeleniumFramework/data/purchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
}


}


