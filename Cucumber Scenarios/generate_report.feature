Feature: Generate Report
  Description: A project leader can generate a report to see the progress of the activity they're project leader for
  Actors: Project leader

Scenario: Project leader generates a report
Given a project exists
  And an activity "activity1" exists
  And a user exists
  And the user is the project leader on the project
  And the project contains the activity
  And activity "activity1" has an expected time of 80 hours
  And activity "activity1" total worked hours is 57
  When the user generates a report
  Then the report returned is "57/80 hours worked on activity 1\n 57/80 hours worked in total"

Scenario: non-Project leader generates a report
  Given a project exists
  And a user exists
  And the user is not project leader on the project
  When the user generates a report
  Then the error message "Only project leader can generate report" is given


