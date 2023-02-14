package helper;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Listeners extends AppiumUtils implements ITestListener 
{
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ExtentTest test;
	AppiumDriver driver;
	AndroidDriver androidDriver;
	
	public void onTestStart(ITestResult result) 
	{
		test = extent.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) 
	{
		test.log(LogStatus.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) 
	{
		test.log(LogStatus.FAIL, result.getThrowable());
		
		try
		{
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			androidDriver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			test.addScreenCapture(getScreenshotPath(result.getMethod().getMethodName(), driver));
	        test.addScreencast(getAndroidRecordSession(result.getMethod().getMethodName(), androidDriver));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) 
	{

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{

	}

	public void onTestFailedWithTimeout(ITestResult result) 
	{


	}

	public void onStart(ITestContext context,ITestResult result) 
	{
		try
		{
			androidDriver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
	        test.addScreencast(getAndroidRecordSession(result.getMethod().getMethodName(), androidDriver));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}

}
