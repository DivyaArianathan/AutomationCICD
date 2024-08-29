package Perficient.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Perficient.PageObjects.CartPage;
import Perficient.PageObjects.OrderPage;

public class abstractComponents {
	
	WebDriver driver;
	public abstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cart_btn;
	
	By title = By.xpath("//div[@class='heading cf']/h1");
	By tittle_order = By.cssSelector(".ng-star-inserted");
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement order_btn;

	public void waitForElement(By FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitForWebElement(WebElement FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	public void waitForElementToDisappear(WebElement Element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(Element));
	}
	
	public CartPage goToCart()
	{
		cart_btn.click();
		waitForElement(title);
		CartPage cartpage = new CartPage(driver);
		return cartpage;
		
	}
	
	public OrderPage goToOrderPage()
	{
		order_btn.click();
		waitForElement(tittle_order);
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	public void JavascriptToScroll(String script)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(script);
		
	}
}
