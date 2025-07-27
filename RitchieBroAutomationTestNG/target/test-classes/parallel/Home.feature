
Feature: Tests related to Home Page
Background:
Given user navigats to rbAuction

Scenario: Check Title of the website
When user gets the title
Then title of the page should be "Used Heavy Equipment for Sale | Heavy Equipment Auctions | Ritchie Bros. Auctioneers"



Scenario: Check some options available under Browse By Category
When clicks on "Browse By category"
Then subcategories should be displayed
|Construction|
|Trailers|
|Aggregate|

Scenario: Check options available under Browse By Category - Fail case
When clicks on "Browse By category"
Then subcategories should be displayed
|Construction|
|Trailers|
|Aggregate1|