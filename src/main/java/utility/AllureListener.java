package utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseClass;
import io.qameta.allure.Attachment;

/**
 * @author vandit.nagar
 *
 */
public class AllureListener implements ITestListener {
	
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart Method "+ iTestContext.getName());
		iTestContext.setAttribute("WebDriver", BaseClass.getDriver());
	}
	
	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish Method "+ iTestContext.getName());
	}
	
	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart Method "+ getTestMethodName(iTestResult));
	}
	
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSucess Method "+ getTestMethodName(iTestResult));
	}
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure Method "+ getTestMethodName(iTestResult));
		
		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseClass.getDriver();
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot Captured for testcase "+ getTestMethodName(iTestResult));
			saveFailureScreenShot(driver);
		}
		saveTextLog(getTestMethodName(iTestResult)+" Failed and screenshot taken");
	}
	
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped Method "+ getTestMethodName(iTestResult));
	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult)
	{
		System.out.println("On Test Fail");
	}

}
