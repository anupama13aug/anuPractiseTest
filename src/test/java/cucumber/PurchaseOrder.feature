@Authentication @Regression
Feature: Purchase the order for Ecommerce application


  Background: User navigates to the login page
    Given Landed on Ecommerce page

  @SmokeTest
  Scenario Outline: Positive test of submitting the order
    Given Logged in with "<username>" and "<password>"
    When Add "<product>" to the cart
    And checkout "<product>" and submit the order
    Then validate "Thank you for your order!" message is displayed

    Examples:
      | username            | password          | product      |
      | anuTest@gmail.com   | Welcome@123       | ZARA COAT 3  |