# Written by Jesper

Feature: Assign User An Activity
    Description: A user assigns a user an Activity
    Actors: User, Project leader

Background:
    Given that user "huba" is logged in
    And an activity "data refinement" exists
    And the activity runs from week 10 to week 11 in the year 2025
    

Scenario: A user is assigned an activity
    And the user is not assigned the activity
    And the user is assigned 0 activities in week 10 to week 11 in the year 2025
    When the user gets assigned the activity
    Then the user is assigned the activity "data refinement"

Scenario: A user is assigned a non-existing activity
    When the user gets assigned the activity "not a real activity"
    Then the error message "Activity does not exist" is given

Scenario: A user is already assigned an activity
    And the user is assigned the activity
    When the user gets assigned the activity
    Then the error message "User is already part of the activity" is given

Scenario: A user is assigned their 11th activity
    And the user is assigned 10 activities in week 10 in the year 2025
    When the user gets assigned the activity
    Then the error message "User has too many activities" is given

Scenario: A peak user is assigned their 11th activity
    And the user is assigned 10 activities in week 10 in the year 2025
    And the user is peak
    When the user gets assigned the activity
    Then the user is assigned the activity "data refinement"
    And the user is assigned 11 activities in week 10 in the year 2025

Scenario: A peak user is assigned their 21st activity
    And the user is peak
    And the user is assigned 20 activities in week 10 in the year 2025
    When the user gets assigned the activity
    Then the error message "User has too many activities" is given

Scenario: A user is assigned an activity that overlaps with a special activity
    And the user is assigned a special activity "Sick" in week 10 in the year 2025
    When the user gets assigned the activity
    Then the error message "User has too many activities" is given