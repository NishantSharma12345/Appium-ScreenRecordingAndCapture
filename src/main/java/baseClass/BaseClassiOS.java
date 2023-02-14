package baseClass;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import helper.AppiumUtils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClassiOS extends AppiumUtils
{
	AppiumDriverLocalService service;
	public Properties prop;
	public IOSDriver driver; 
	
	public BaseClassiOS()
	{
		try
		{
			prop = new Properties();
			FileInputStream fis = new  FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/Config.properties");
			prop.load(fis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@BeforeClass(alwaysRun=true)	
	public void configureAppium() throws MalformedURLException
	{
		/*String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		String port = prop.getProperty("prop");
		
		service = startAppiumServer(ipAddress, Integer.parseInt(port));
		
		Assert.assertTrue(service.isRunning());
		
		if(service.isRunning())
		{*/
		
		    XCUITestOptions options = new XCUITestOptions();
			options.setDeviceName(prop.getProperty("androidDeviceName"));
			options.setBundleId("com.apple.MobileAddressBook");
					
			driver = new IOSDriver(/*service.getUrl() != null ? service.getUrl() : */new URL("http://127.0.0.1:4723/"), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));			
		//}			
	}
	
	@AfterClass(alwaysRun=true)	
	public void tearDown()
	{
		driver.quit();
		//service.stop();
	}
}
