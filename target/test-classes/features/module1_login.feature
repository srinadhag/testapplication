#Author: Srinadha
@testSampleApplication @regression2
Feature: Register,Login the application using credentials, add a product to card  
and validate on the Payments page is Product details are correct or not
 
  	Background: Navigated to the Application
    
    Given I navigate to given url "http://automationpractice.com/index.php" 
 
     Scenario Outline: Register on the website
     When I Click on the ".login" button
     Then I should enter "test123_sri1@gmail.com" to "#email_create"
     And I Click on the "#SubmitCreate" button
     #And I Click on the "#product-responsive" button
     
     Examples:
     |username|url|
     |test123_sri1@gmail.com|http://automationpractice.com/index.php|
   #  |zakir123_sri1@gmail.com|http://automationpractice.com/index.php|