package Perficient.SeleniumFrameworkDesign;

import org.testng.Assert;
import org.testng.annotations.Test;

import Perficient.PageObjects.CartPage;
import Perficient.PageObjects.ProductCatalogue;
import Perficient.TestComponents.BaseTest;
import Perficient.TestComponents.Retry;

public class LoginError extends BaseTest {
	
	public String prodname = "ZARA COAT 3";

	@Test(groups = "ErrorHandling")
	public void loginErrorValidation()
	{
		landingPage.loginPage("divyak@gmail.com", "Test@@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test(retryAnalyzer = Retry.class)
	public void productErrorValidation()
	{
		ProductCatalogue productCatalogue = landingPage.loginPage("divyak@gmail.com", "Test@123");
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(prodname);	
		CartPage cartpage = productCatalogue.goToCart();
		Boolean match = cartpage.matchCartList("ZARA COAT 4");
		Assert.assertTrue(match);
	}
}
