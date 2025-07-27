Feature: Tests related to search engine
Background:
Given user has navigated to rbAuction
Scenario Outline: Assert Search Results 
When User Searches for "<equipment>"
Then Capture the number of results
And Verifies that the first result on the page has the word "<equipment>" in it
When User Applies the “Year” filter to be from “2010” to current year
Then Verifies the number of results returned is different
	Examples: 
	|equipment|
	|Ford F-150|