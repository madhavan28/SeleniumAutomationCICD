@tag
Feature: Purchase the order from Ecommerece Website
  I want to use this template for my feature file

	Background: 
	Given I landed on the ecommerce page


  @tag2
  Scenario Outline: Positive test of purchasing the order
    Given Logged in with username<username> and password <password> 
    When I add product<productname> to cart
    And Checkout <productname> and submit the order
    Then "Thankyou for the order." message is displayed on confirmation page

    Examples: 
      |username  				 |password   |productname    |
      |madhavan@gmail.com|Madhavan@28|ADIDAS ORIGINAL|
      
    