Feature: View Logged Hours
	Description: A user can see how many hours they have logged in activities
	Actors: User

Background:
	Given that user "huba" is logged in
	And an activity "data refinement" exists

Scenario: User checks logged hours in an activity
	And the user has logged 37 hours in the activity
	When the user views logged hours for the activity
	Then "37 hours" is given

Scenario: User checks logged hours in an activity
	And the user has logged 0 hours in the activity
	When the user views logged hours for the activity
	Then "0 hours" is given

Scenario: User checks logged hours for a specific date in an activity
	And the user has logged 6 hours in the activity on the date "01-01-2024"
	When the user views logged hours for the activity on the date "01-01-2024"
	Then "6 hours" is given