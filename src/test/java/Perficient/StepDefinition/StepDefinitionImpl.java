package Perficient.StepDefinition;

import java.io.IOException;

import org.testng.Assert;

import Perficient.PageObjects.CartPage;
import Perficient.PageObjects.CheckoutPage;
import Perficient.PageObjects.ConfirmationPage;
import Perficient.PageObjects.LandingPage;
import Perficient.PageObjects.ProductCatalogue;
import Perficient.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	LandingPage landingPage;
	ProductCatalogue productCatalogue;
	CheckoutPage checkoutPage;
	ConfirmationPage confirmPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
		
	}
	
	@Given("^Logged in with username (.+) password (.+)$")
	public void Logged_in_with_username_password(String username, String password) {
		
		productCatalogue = landingPage.loginPage(username, password);
		
		
	}
	
	@When("^I add (.+) to Cart$")
	public void I_add_Product_to_Cart(String productname) {
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productname);
	}
	
	@When("^Checkout (.+) and submit the order")
	
	public void Checkout_Submit_Order(String productname) throws InterruptedException {
		CartPage cartpage = productCatalogue.goToCart();
		Boolean match = cartpage.matchCartList(productname);
		Assert.assertTrue(match);
		checkoutPage = cartpage.goToCheckoutPage();
		checkoutPage.SelectCountry("India");
		productCatalogue.JavascriptToScroll("window.scrollBy(0,300)");
		confirmPage = checkoutPage.goToConfirmationPage();
		
	}
	
	@Then("{string} message is displayed on confirmationPage")
	public void thankyou_message_confirmation(String string) {
		String ConfirmationMessage = confirmPage.VerifyThankyouMessage();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase(string));	
		driver.quit();
	}
	
	@Then("{string} message is displayed on the on the page")
	public void error_message_displayed(String string) {
		
		Assert.assertEquals(string, landingPage.getErrorMessage());
		
	}
	
	
	

}
