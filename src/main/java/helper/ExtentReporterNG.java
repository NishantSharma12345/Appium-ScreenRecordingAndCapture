package helper;

import com.relevantcodes.extentreports.ExtentReports;

/*import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;*/

public class ExtentReporterNG 
{
	public static ExtentReports extent;

	public static ExtentReports getReporterObject()
	{
		String path = System.getProperty("user.dir")+"//Reports//index.html";
		extent = new ExtentReports(path);

		extent.addSystemInfo("Tester", "Nishant Sharma");
		extent.addSystemInfo("Project Name", "Screen Recoding");
		extent.addSystemInfo("Time Zone", System.getProperty("user.timezone"));
		extent.addSystemInfo("User Location", System.getProperty("user.country"));
		extent.addSystemInfo("OS Version", System.getProperty("os.version"));
		extent.addSystemInfo("Jave Version", System.getProperty("java.version"));
		return extent;		
	}
/*	@Override
	public ExtentTest addScreencastFromPath(String screencastPath) throws IOException {
	  Screencast sc = new Screencast();
	  sc.setPath(screencastPath);
	  sc.setMediaType(MediaType.VID);
	  
	  test.setScreencast(sc);
	  
	  if (test.getObjectId() != null) {
	    int sequence = test.getScreencastList().size();
	    sc.setTestObjectId(test.getObjectId());
	    sc.setSequence(sequence);
	  }
	  
	  extent.addScreencast(test, sc);
	  
	  return this;
	}*/
}
