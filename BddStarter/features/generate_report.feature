# Written by Marcus

Feature: Generate Report
  Description: A project leader can generate a report to see the progress of the activity they're project leader for
  Actors: Project leader

Background:
	Given that user "huba" is logged in
	Given that there exists a project "projectX"

Scenario: Project leader generates a report
	And the user "huba" is the project leader
	And the project contains an activity "activity1"
	And the activity's expected total work hours is already 80
	And the activity's total worked hours is 17
	When the user generates a report
	Then the report returned is 17 hours out of 80 hours

Scenario: non-Project leader generates a report
	And the user "bahu" is the project leader
	When the user generates a report
	Then the error message "Only project leader can generate report" is given


