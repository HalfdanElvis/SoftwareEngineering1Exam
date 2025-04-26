Feature: Generate Report
  Description: A project leader can generate a report to see the progress of the activity they're project leader for
  Actors: Project leader

Background:
	Given that a user "huba" is logged in
	And a project "projectX" exists

Scenario: Project leader generates a report
	And an activity "activity1" exists
	And the user is the project leader on the project
	And the project contains the activity
	And the activity has an expected time of 80 hours
	And the activity's total worked hours is 57
	When the user generates a report
	Then the report returned is "57/80 hours worked on activity 1\n 57/80 hours worked in total"

Scenario: non-Project leader generates a report
	And the user is not project leader on the project
	When the user generates a report
	Then the error message "Only project leader can generate report" is given


