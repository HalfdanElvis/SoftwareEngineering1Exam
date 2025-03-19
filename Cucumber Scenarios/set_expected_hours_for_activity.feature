Feature: Set activities expected hours
  Description: a project leader can set the expected amount of hours of work an activity will take
  Actor: project leader

Scenario: a project leader sets an activity's expected work hours
  Given a project exists
  And a an activity exists
  And a user exists
  And the project contains the activity
  And the user is the project leader on the project
  And the activity does not have an expected total work hours
  When the user sets the activity's expected total work hours to 80
  Then the activity's expected total work hours is 80

Scenario: a project leader changes an activity's expected work hours
  Given a project exists
  And a an activity exists
  And a user exists
  And the project contains the activity
  And the user is the project leader on the project
  And the activity's expected toatl work hours is 110
  When the user sets the activity's expected total work hours to 80
  Then the activity's expected total work hours is 80


Scenario: a user tries to set an activity's expected work hours
  Given a project exists
  And a an activity exists
  And a user exists
  And the project contains the activity
  And the user is not the project leader on the project
  And the activity does not have an expected total work hours
  When the user sets the activity's expected total work hours to 80
  Then the error message "Only the project leader can set the activity's expected work hours" is given
  And the activity's expected total work hours is null

