package pages;

import java.io.File;
import java.time.Duration;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import helper.AppiumUtils;
import helper.DateClass;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;

public class iOSRecordSessionTest 
{
	IOSDriver driver;

	@Test
	public void test1() throws Exception 
	{	
		try 
		{
			driver.startRecordingScreen(
					new IOSStartScreenRecordingOptions()
					.withVideoScale("1280x720")
					.withTimeLimit(Duration.ofSeconds(100)));

			WebElement e = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Add']"));
			e.click();
			AppiumUtils.sleep(5);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			System.out.println(driver.getPageSource());
		} 
		finally 
		{
			String video = driver.stopRecordingScreen();
			byte[] decode = Base64.getDecoder().decode(video);
			String currentDate = DateClass.dateFormat();
			String destination = System.getProperty("user.dir")+"//iOSVideos//"+"iOSclip"+" "+currentDate+".mp4";
			FileUtils.writeByteArrayToFile(new File(destination), decode);
		}
	}
}
