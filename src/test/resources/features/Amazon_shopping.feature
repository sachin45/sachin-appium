@amazonshoping
Feature: Amazon automation

  Scenario: TC001_Search 65-inch TV and purchase a random unit
    Given I launch the app and verify I am on Initial screen
    When I selected language as "English"
    Then I verify that I am on sign-up page
    And I click on skip sign-in button
    And I verify I am on home screen
    Then I tap on search text bar
    And I search product for "sachinsaxena45@yahoo.com"
    Then I swipe screen till product is found
    And I tap on product
    Then I verify product name and product price on product details page
    And I click on Buy now