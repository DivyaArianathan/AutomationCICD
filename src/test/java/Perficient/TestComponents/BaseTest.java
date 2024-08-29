package Perficient.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Perficient.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Perficient//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}
		if(browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		if(browserName.equalsIgnoreCase("firefox"))
		{
			driver = new EdgeDriver();
		}
		
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
		
	
	public List<HashMap<String, String>> getJsonData(String Filepath) throws IOException
		{		
			//Json file content to String
			String JsonContent = FileUtils.readFileToString(new File(Filepath), StandardCharsets.UTF_8);
			
			//String to Hashmap
			
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){
			});
			return data;
		}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
		{
			driver = initializeDriver();
			landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
			
		}
	@AfterMethod(alwaysRun =true)
	public void quitDriver()
	{
		driver.quit();
	}
	
	public void getJsonToTest() {
		
	}
	
   public String getScreenshot(String filename, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"//reports//"+ filename + ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+"//reports//"+ filename + ".png";
		
	}
		
	

}
