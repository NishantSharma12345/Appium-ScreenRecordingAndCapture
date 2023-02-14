package pages;

import java.io.File;
import java.time.Duration;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import baseClass.BaseClassAndroid;
import helper.AppiumUtils;
import helper.DateClass;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;

public class AndroidRecordSessionTest extends BaseClassAndroid
{
	@Test	
	public void androidRecordSession() throws Exception 
	{
		try 
		{
			//Recording the Screen Android
			driver.startRecordingScreen(
					new AndroidStartScreenRecordingOptions()
					.withVideoSize("1280x720")
					.withTimeLimit(Duration.ofSeconds(200)));

			// Do the actions
			WebElement battery = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Battery')]"));
			battery.click();
			AppiumUtils.sleep(5);
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
			System.out.println(driver.getPageSource());
		} 
		finally 
		{
			// Stop the Recording screen function
			String video = driver.stopRecordingScreen();
			//Since it creates recording in base64 format, you need to decode it to view
			byte[] decode = Base64.getDecoder().decode(video);
			String currentDate = DateClass.dateFormat();
			String destination = System.getProperty("user.dir")+"//AndroidVideos//"+"androidclip"+" "+currentDate+".mp4";
			FileUtils.writeByteArrayToFile(new File(destination), decode);
		}
	}
	
	@Test
	public void toogleWifi()
	{
		driver.activateApp("com.android.settings");
		driver.openNotifications();
		driver.toggleWifi();
	}
}
