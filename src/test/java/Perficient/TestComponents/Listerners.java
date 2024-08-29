package Perficient.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Perficient.resources.ExtentReportNG;

public class Listerners extends BaseTest implements ITestListener{
	
	ExtentReports extent = ExtentReportNG.getExtentReports();	
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	

    @Override		
    public void onTestFailure(ITestResult result) {					
        // TODO Auto-generated method stub		
    	String filepath = null;
    	try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	extentTest.get().fail(result.getThrowable());
    	try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
        		
    }		

    @Override		
    public void onTestSkipped(ITestResult result) {					
        // TODO Auto-generated method stub	
    	try {
    		extentTest.get().log(Status.SKIP, "Test was Skipped");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
        		
    }		

    @Override		
    public void onTestStart(ITestResult result) {					
        // TODO Auto-generated method stub	
    	
    	test = extent.createTest(result.getMethod().getMethodName());
    	extentTest.set(test); // sets unique id to the thread
    }		

    @Override		
    public void onTestSuccess(ITestResult result) {					
        // TODO Auto-generated method stub				
        extentTest.get().log(Status.PASS, "Test was Passed");
    }	
    
    @Override
    public void onFinish(ITestContext context) {
    	// TODO Auto-generated method stub
    	extent.flush();
    	
    }

}
