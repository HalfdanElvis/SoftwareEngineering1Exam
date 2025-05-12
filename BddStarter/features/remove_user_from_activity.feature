
Feature: Remove User From An Activity
    Description: A user removes a user from an activity
    Actors: User, Project leader

Background:
    Given that user "huba" is logged in
    
Scenario: A user is removed from an activity
    And an activity "data refinement" exists
    And the user is assigned the activity
    When the user gets removed from the activity
    Then the user is not assigned the activity "data refinement"

Scenario: A user is removed from an activity they are not assigned to
    And an activity "data refinement" exists
    And the user is not assigned the activity
    When the user gets removed from the activity
    Then the error message "User is not assigned that activity" is given

Scenario: A user is removed from a special activity
    And the user is assigned a special activity "Sick" in week 5 in the year 2025
    When the user gets removed from the special activity "Sick"
    Then the user is not assigned to the special activity "Holidays"

Scenario: A user is removed from a special activity they are not assigned to
    And the user is not assigned the special activity "Sick"
    When the user gets removed from the special activity "Sick"
    Then the error message "User is not assigned that special activity" is given