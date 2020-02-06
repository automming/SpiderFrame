@Google
Feature: Google

Scenario: User types selenium in Google
	Given user loads the browser
	When user opens the Google site 
	And types "Selenium" in the search 
	Then user should view the results