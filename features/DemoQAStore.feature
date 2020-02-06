@DemoQAStore
Feature: DemoQA Store 

Scenario: DemoQA store registration 
	Given user opens DemoQA store 
	When user clicks on registration link 
	And user adds registration details 
	Then user should complete registration 