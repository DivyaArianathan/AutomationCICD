package Perficient.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Perficient.AbstractComponents.abstractComponents;

public class LandingPage extends abstractComponents {
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
		@FindBy(id = "userEmail")
		WebElement userEmail;
		
		@FindBy(id = "userPassword")
		WebElement userPassword;
		
		@FindBy(id = "login")
		WebElement userLogin;
		
		@FindBy(css = "[class*='flyInOut']")
		WebElement errorMessage;
		
		
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		
		public ProductCatalogue loginPage(String email, String password)
		{
			userEmail.sendKeys(email);
			userPassword.sendKeys(password);
			userLogin.click();
			ProductCatalogue productCatalogue = new ProductCatalogue(driver);
			return productCatalogue;
		}
		
		public String getErrorMessage()
		{
			waitForWebElement(errorMessage);
			return errorMessage.getText();
		}
		
	

}
