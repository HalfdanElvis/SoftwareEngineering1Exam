# Written by Marcus

Feature: Create special activity
	Description: A user creates a special new activity
	Actors: User

Background:
	Given that user "huba" is logged in
 	
Scenario: A user creates a special activity
	And the user is assigned 0 activities in week 5 in the year 2025
 	When the user creates a special activity "Holidays" in week 5 of the year 2025
 	Then the user is assigned to the special activity "Holidays"

Scenario: A user tries to create a special activity that overlaps with other activities
	And the user is assigned a special activity "Sick" in week 5 in the year 2025
	When the user creates a special activity "Holidays" in week 5 of the year 2025
 	Then the error message "Special activities cannot overlap with other special activities" is given
