package Perficient.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Perficient.PageObjects.CartPage;
import Perficient.PageObjects.CheckoutPage;
import Perficient.PageObjects.ConfirmationPage;
import Perficient.PageObjects.OrderPage;
import Perficient.PageObjects.ProductCatalogue;
import Perficient.TestComponents.BaseTest;


public class pageObject  extends BaseTest{
	//private String prodname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		ProductCatalogue productCatalogue = landingPage.loginPage(input.get("email"), input.get("password"));
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));	
		CartPage cartpage = productCatalogue.goToCart();
		Boolean match = cartpage.matchCartList(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckoutPage();
		checkoutPage.SelectCountry("India");
		productCatalogue.JavascriptToScroll("window.scrollBy(0,300)");
		ConfirmationPage confirmPage = checkoutPage.goToConfirmationPage();
		String ConfirmationMessage = confirmPage.VerifyThankyouMessage();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	

	}
	
	@Test(dependsOnMethods = "submitOrder")
	public void OrderList() {
		String prodname = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginPage("divyak@gmail.com", "Test@123");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Boolean match = orderPage.getOrderList(prodname);
		Assert.assertTrue(match);
		
	}
	
	public void takeScreenshot() throws IOException {
		getScreenshot("PageObject", driver);
	}
	
	/**@DataProvider
	public Object[][] getdata()
	{
		return new Object[][] {{"divyak@gmail.com", "Test@123", "ZARA COAT 3"}, {"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}, {"shetty@gmail.com", "Iamking@000", "IPHONE 13 PRO"}};
	}**/
	
	/**@DataProvider
	public Object[][] getdataHashMap()
	{
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("email", "divyak@gmail.com");
		map.put("password", "Test@123");
		map.put("products", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String, String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("products", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map}, {map1}};
	}**/
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//Perficient//DataReader//PurchaseData.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
		
	} 
	

}
