Feature: See Project Progress
  Description: A project leader kan see the progress of the activity they're project leader for
  Actors: Project leader

Scenario: Project leader requests the project progress
  Given a project exists
  And an activity exists
  And a user exists
  And the project contains the activity
  And the activity has an expected time of 80 hours
  And the activity total worked hours is 57
  And the user is the project leader on the project
  When the user requests the project progress
  Then the progress returned is "57/80 hours worked"

Scenario: non-Project leader user requests the project progress
  Given a project exists
  And an activity exists
  And a user exists
  And the project contains the activity
  And the activity has an expected time of 80 hours
  And the activity total worked hours is 57
  And the user is not project leader on the project
  When the user requests the project progress
  Then the error message "Project progress is only accessible by the project leader" is given


