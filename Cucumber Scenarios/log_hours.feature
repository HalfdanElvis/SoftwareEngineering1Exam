Feature: Log hours
  Description: User can log hours spent on activities
  Actors: User

Scenario: User logs their hours for an activity for the first time
  Given an activity exists
  And a user exists
  And the user has logged 0 hours in the activity
  When the user logs 10 hours in the activity
  Then the user has logged 10 hours in the activity

Scenario: User logs their hours for an activity they have previously been active in
  Given an activity exists
  And a user exists
  And the user has logged 37 hours in the activity
  When the user logs 7 hours in the activity
  Then the user has logged 44 hours in the activity

  Scenario: User logs their hours for an that non-existent activity
  Given an activity "no fun" does not exist
  And a user exists
  When the user logs 7 hours in the activity "no fun"
  Then the error message "Activity doesn't exist" is given


