Feature: Set activities expected hours
	Description: a project leader can set the expected amount of work hours an activity will take
	Actor: project leader

Background:
	Given that a user "huba" is logged in
	And that there exists a project "projectX"
	And an activity "data refinement" exists
	And the project contains the activity

Scenario: A project leader sets an activity's expected work hours
	And the user is the project leader
	When the user sets the activity's expected total work hours to 80
	Then the activity's expected total work hours is 80

Scenario: A user tries to set an activity's expected work hours
	And the user is not the project leader
	And the activity's expected total work hours is 50
	When the user sets the activity's expected total work hours to 80
	Then the error message "Only the project leader can set the activity's expected work hours" is given
	And the activity's expected total work hours is 50

