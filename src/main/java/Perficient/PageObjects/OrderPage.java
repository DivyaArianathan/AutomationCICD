package Perficient.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Perficient.AbstractComponents.abstractComponents;

public class OrderPage extends abstractComponents {
	
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	 private List<WebElement> orderList;
	
	
	public Boolean getOrderList(String prodname)
	{
		Boolean matchProduct = orderList.stream().anyMatch(product -> product.getText().equalsIgnoreCase(prodname));
		return matchProduct;
	}
	
	

}
