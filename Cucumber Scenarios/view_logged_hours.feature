Feature: View Logged Hours
  Description: A user can see how many hours they have logged in activities
  Actors: User

Scenario: User checks logged hours in an activity
  Given an activity exists
  And a user exists
  And the user has logged 37 hours in the activity
  When the user views logged hours for the activity
  Then "37 hours" is given

Scenario: User checks logged hours in an activity
  Given an activity exists
  And a user exists
  And the user has logged 0 hours in the activity
  When the user views logged hours for the activity
  Then "0 hours" is given
