package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import baseClass.BaseClassAndroid;
import helper.AppiumUtils;

public class RecordSessionTest extends BaseClassAndroid
{
	@Test	
	public void androidRecordSession() throws Exception 
	{
		WebElement battery = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Battery')]"));
		battery.click();
		AppiumUtils.sleep(5);
	} 
}
