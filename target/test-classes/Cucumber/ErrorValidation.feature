@tag
Feature: Error Validation on the Ecommerce website
  I want to use this template for my feature file

 
  @errorvalidation
  Scenario Outline: Negative test on Login to ecommerce site
    Given I landed on ecommerce Page
    When Logged in with username <name> password <password>
    Then "Incorrect email or password." message is displayed on the on the page.

    Examples: 
       | name  					       | password        |
       | divyak@gmail.com      |     Test@12     |
