package Perficient.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Perficient.AbstractComponents.abstractComponents;

public class CartPage extends abstractComponents {

WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	@FindBy(xpath = "//li[@class='totalRow']/button")
	WebElement checkout_btn;
	
	
	public Boolean matchCartList(String productname)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productname));
		return match;
	}
	public CheckoutPage goToCheckoutPage()
	{
		checkout_btn.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
	
	
	
}
