# Written by Marcus

Feature: Log hours
	Description: User can log, see and edit hours spent on activities
	Actors: User

Background:
	Given that user "huba" is logged in
	And an activity "data refinement" exists

Scenario: User logs their hours for an activity for the first time
	When the user logs 10 hours in the activity on the date "01-01-2024"
	Then the user should have logged 10 hours in the activity
	And on the date "01-01-2024" the user has logged 10 hours in the activity

Scenario: User logs their hours for an activity they have previously been active in
	And the user has logged 3 hours in the activity on the date "01-01-2024"
	And the user has logged 0 hours in the activity on the date "01-02-2024"
	When the user logs 7 hours in the activity on the date "01-02-2024"
	Then the user should have logged 10 hours in the activity
	And on the date "01-02-2024" the user has logged 7 hours in the activity

Scenario: User logs their hours for a non-existent activity
	When the user logs 7 hours in the activity "not an activity" on the date "01-01-2024"
	Then the error message "Activity does not exist" is given

Scenario: User removes hours logged from an activity
	And the user has logged 8 hours in the activity on the date "01-01-2024"
	And the user has logged 7 hours in the activity on the date "01-02-2024"
	When the user removes 7 hours from the activity on the date "01-02-2024"
	Then the user should have logged 8 hours in the activity
	And on the date "01-02-2024" the user has logged 0 hours in the activity

Scenario: User removes hours logged from an activity they have not worked in
	And the user has logged 4 hours in the activity on the date "01-01-2024"
	When the user removes 7 hours from the activity on the date "01-01-2024"
	Then the error message "You haven't worked that long in this activity" is given
	And on the date "01-01-2024" the user has logged 4 hours in the activity

Scenario: User logs their hours more than 24 hours
	When the user logs 25 hours in the activity on the date "01-02-2024"
	Then the error message "You can't log more than 24 hours a day" is given
	And on the date "01-02-2024" the user has logged 0 hours in the activity