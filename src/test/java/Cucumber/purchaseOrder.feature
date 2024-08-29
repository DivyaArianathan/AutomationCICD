
@tag
Feature: Purchase order from the Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting order
    Given Logged in with username <name> password <password>
    When I add <productname> to Cart
    And Checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | name  					      | password        | productname  |
      | divyak@gmail.com      |     Test@123    | ZARA COAT 3  |
      
