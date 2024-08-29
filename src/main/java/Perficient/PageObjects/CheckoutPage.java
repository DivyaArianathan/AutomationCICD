package Perficient.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Perficient.AbstractComponents.abstractComponents;

public class CheckoutPage extends abstractComponents {

WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country_dropdown;
	By countryList  = By.cssSelector(".ta-item");
	@FindBy(css = ".ta-item:nth-child(3)")
	WebElement countrySelected;
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	public void SelectCountry(String countryName)
	{
		
		Actions a = new Actions(driver);
		a.sendKeys(country_dropdown, countryName).build().perform();
		waitForElement(countryList);
		countrySelected.click();
	}
	
	public ConfirmationPage goToConfirmationPage() throws InterruptedException
	{
		Thread.sleep(1000);
		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
	

}
