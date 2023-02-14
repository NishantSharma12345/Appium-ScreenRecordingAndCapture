package helper;

import java.io.IOException;

import java.util.Map;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.html5.Location;

import baseClass.BaseClassAndroid;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;

public class Features extends BaseClassAndroid
{
	AndroidDriver driver;
	
	public void a() throws IOException 
	{
		//Retrieve the Appium server status through the driver instance
		driver.getStatus();
		
		//You can get the mobile orientation information
		driver.getOrientation();
		
		//Set the device orientation to Landscape or Portrait
		driver.rotate(ScreenOrientation.LANDSCAPE);
		driver.rotate(ScreenOrientation.PORTRAIT);
		
		 // Get current device location.
		Location location = driver.location();
		System.out.println("Locations : "+location);
		
		//Get the Device settings using the driver instance, which will return the current device settings
		Map<String, Object> settings = driver.getSettings();
		
		//Set the Device settings using the driver instance, which will set the current device settings
		driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 5000);
		
		//Check whether the Device keyboard is Opened or not
		driver.isKeyboardShown();
		
		//Close the App from a real device
		driver.terminateApp("com.android.settings");
		
		//To uninstall the Application from the real device
		driver.removeApp("com.example.AppName");
		
		//Check whether the application is installed or not
		driver.isAppInstalled("com.samsung.android.messaging");
	}
}
