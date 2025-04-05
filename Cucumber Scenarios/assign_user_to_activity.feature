Feature: Assign User To Activity
    Description: A user assigns a user to an Activity
    Actors: User, Project leader

Background:
    Given a user "huba" exists
    And an activity "data refinement" exists
    And the activity runs from week 10 to week 11 in the year 2025

Scenario: A user is assigned to an activity
    And the user is not assigned to the activity
    And the user is assigned to 0 activities in week 10 and 11 in the year 2025
    When the user is assigned to the activity
    Then the user is assigned to the activity "data refinement"

Scenario: A user is assigned to a non-existing activity
    When the user is assigned to the activity "not a real activity"
    Then the error message "Activity does not exist" is given

Scenario: A user is already part of the activity
    And the user is assigned to the activity
    When the user is assigned to the activity
    Then the error message "User is already part of the activity" is given

Scenario: A user is assigned to their 11th activity
    And the user is assigned to 10 activities in week 10 in the year 2025
    When the user is assigned to the activity
    Then the error message "User has too many activities" is given

Scenario: A peak user is assigned to their 11th activity
    And the user is assigned to 10 activities in week 10 in the year 2025
    And the user is peak
    When the user is assigned to the activity
    Then the user is assigned to the activity "data refinement"
    And the user is assigned to 11 activities in week 10 in the year 2025

Scenario: A peak user is assigned to their 21st activity
    And the user is peak
    And the user is assigned to 20 activities in week 10 in the year 2025
    When the user is assigned to the activity
    Then the error message "User has to many activities" is given