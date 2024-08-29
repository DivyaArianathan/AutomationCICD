package Perficient.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	
	public static ExtentReports getExtentReports() {
		String path = System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter Es = new ExtentSparkReporter(path);
		Es.config().setReportName("Web Automation Results");
		Es.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(Es);
		extent.setSystemInfo("Divya", "Perficient");
		return extent;

	}

}
