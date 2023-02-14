package helper;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils 
{
	public AppiumDriverLocalService service;
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port)
	{
		service = new AppiumServiceBuilder().withAppiumJS(new File("/home/users/nishant.sharma/.appium/node_modules/appium/build/lib/main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
		
		service.start();
		return service;				
	}

	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException
	{
		File source = driver.getScreenshotAs(OutputType.FILE);
		String currentDate = DateClass.dateFormat();
		String destination = System.getProperty("user.dir")+"//Screenshots//"+testCaseName+" "+currentDate+".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
	
	public String getAndroidRecordSession(String testCaseName, AndroidDriver driver) throws Exception 
	{
		String destination;
		try 
		{
			driver.startRecordingScreen(
					new AndroidStartScreenRecordingOptions()
					.withVideoSize("1280x720")
					.withTimeLimit(Duration.ofSeconds(200)));
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
			System.out.println(driver.getPageSource());
		} 
		finally 
		{
			String video = driver.stopRecordingScreen();
			byte[] decode = Base64.getDecoder().decode(video);
			String currentDate = DateClass.dateFormat();
			destination = System.getProperty("user.dir")+"//AndroidVideos//"+testCaseName+" "+currentDate+".mp4";
			FileUtils.writeByteArrayToFile(new File(destination), decode);		
		}
		return destination;
	}
	
	public static void sleep(int sec) 
	{
	    try
	    {
	    	Thread.sleep(sec*1000);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
}	
