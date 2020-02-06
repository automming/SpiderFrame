@TricentisAutomobileInsurance
Feature: Tricentis Automobile Vehicle Insurance 

Scenario: Complete Tricentis Automobile Vehicle Insurance quote
	Given user opens Tricentis Automobile Insurance Application 
	When fills Vehicle Data details
	And fills Insurance Data details 
	And fills Product Data details
	And selects Price Option
	And fills Send Quote details
	Then user should successfully sent quote and verifies success alert