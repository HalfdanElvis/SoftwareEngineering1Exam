Feature: Log hours
  Description: User can log, see and rearrange hours spent on activities
  Actors: User

Scenario: User logs their hours for an activity for the first time
  Given an activity exists
  And a user exists
  And a user has 10 unassigned hours
  And the user has logged 0 hours in the activity
  When the user logs 10 hours in the activity
  Then the user has logged 10 hours in the activity
  And the user has 0 unassigned hours

Scenario: User logs their hours for an activity they have previously been active in
  Given an activity exists
  And a user exists
  And the user has logged 37 hours in the activity
  And a user has 10 unassigned hours
  When the user logs 7 hours in the activity
  Then the user has logged 44 hours in the activity
  And the user has 3 unassigned hours


Scenario: User logs their hours for an that non-existent activity
  Given an activity "free money" does not exist
  And a user exists
  And a user has 7 unassigned hours
  When the user logs 7 hours in the activity "free money"
  Then the error message "Activity doesn't exist" is given
  And the user has 0 unassigned hours

Scenario: User logs hours they don't have
  Given an activity exists
  And a user exists
  And a user has 0 unassigned hours
  When the user logs 7 hours in the activity
  Then the error message "You don't have enough unassigned hours" is given
  And the user has 0 unassigned hours

Scenario: User removes hours logged from an activity
  Given an activity exists
  And a user exists
  And a user has 0 unassigned hours
  And the user has logged 37 hours in the activity
  When the user removes 7 hours from the activity
  Then the user has logged 30 hours in the activity
  And the user has 7 unassigned hours

Scenario: User removes hours logged from an activity they have not worked in
  Given an activity exists
  And a user exists
  And a user has 0 unassigned hours
  And the user has logged 0 hours in the activity
  When the user removes 7 hours from the activity
  Then the error message "You havn't worked that long in this activity" is given
  And the user has 0 unassigned hours




