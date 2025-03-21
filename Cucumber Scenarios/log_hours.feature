Feature: Log hours
  Description: User can log, see and rearrange hours spent on activities
  Actors: User

Scenario: User logs their hours for an activity for the first time
  Given an activity exists
  And a user exists
  And the user has logged 0 hours in the activity
  When the user logs 10 hours in the activity on the date "01-01-2024"
  Then the user has logged 10 hours in the activity
  And on the date "01-01-2024" the user has logged 10 hours in the activity

Scenario: User logs their hours for an activity they have previously been active in
  Given an activity exists
  And a user exists
  And the user has logged 37 hours in the activity
  And on the date "01-01-2024" the user has logged 0 hours
  When the user logs 7 hours in the activity on the date "01-01-2024"
  Then the user has logged 44 hours in the activity
  And on the date "01-01-2024" the user has logged 7 hours in the activity


Scenario: User logs their hours for an non-existent activity
  Given an activity "free money" does not exist
  And a user exists
  When the user logs 7 hours in the activity "free money" on the date "01-01-2024"
  Then the error message "Activity doesn't exist" is given

Scenario: User removes hours logged from an activity
  Given an activity exists
  And a user exists
  And the user has logged 37 hours in the activity
  And on the date "01-01-2024" the user has logged 7
  When the user removes 7 hours from the activity on the date "01-01-2024"
  Then the user has logged 30 hours in the activity
  And on the date "01-01-2024" the user has logged 0 hours in the activity


Scenario: User removes hours logged from an activity they have not worked in
  Given an activity exists
  And a user exists
  And the user has logged 0 hours in the activity
  When the user removes 7 hours from the activity on the date "01-01-2024"
  Then the error message "You havn't worked that long in this activity" is given
  And on the date "01-01-2024" the user has logged 0 hours in the activity




