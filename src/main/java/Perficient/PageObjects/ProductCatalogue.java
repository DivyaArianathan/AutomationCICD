package Perficient.PageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Perficient.AbstractComponents.abstractComponents;

public class ProductCatalogue extends abstractComponents {
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	By product = By.cssSelector(".mb-3");
	By prodbutton = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.id("toast-container");
	@FindBy(css = ".ng-animating")
	WebElement element;
	
	
	
	
	public List<WebElement> getProductList()
	{
		waitForElement(product);
		return products;
		
	}
	
	public WebElement finalProduct(String productname)
	{
		WebElement finalprod = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return finalprod;
	}
	
	public void addProductToCart(String productname)
	{
		WebElement prod = finalProduct(productname);
		prod.findElement(prodbutton).click();
		waitForElement(toastContainer);
		waitForElementToDisappear(element);
	}
					
		
	

}
