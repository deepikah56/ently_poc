@Login
Feature: As a User I should be able to login 

@Login 
Scenario: user able to login
Given I am on React login page
When I enter email and parssword credential
Then I should see the React home page

