package SeleniumFramework.testcomponent;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumFramework.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
 ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//thread safe
	
	public void onTestStart(ITestResult result) {
		System.out.println("test start");
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);// unique thtread id 
		System.out.println("startlistener:" + Thread.currentThread().getId());
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("test success");
		extentTest.get().log(Status.PASS,"Test Passed");
		System.out.println("successlistener:" + Thread.currentThread().getId());
	}

	public void onTestFailure(ITestResult result) {
		
		System.out.println("test fails");
		
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		String filepath = null;
		try {
			filepath= takeScreenshot(result.getMethod().getMethodName(),driver);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}
	

	
	public void onFinish(ITestContext context) {
		System.out.println("test finish");  
	extent.flush();
}
}
