Feature: Assign User To Activity
    Description: A user assigns a user to an Activity
    Actors: User, Project leader

Scenario: A user is assigned to an activity
    Given there exists an activity "data refinement"
    And a user "huba" exists
    And the activity does not contain the user
    When the user is assigned to the activity
    Then the activity contains a user "huba"
    
Scenario: A user is assigned to activity in project
    Given there exists a project
    And there exists an activity "data refinement"
    And the project contains the activity
    And a user "huba" exists
    And the project contains the user
    When the user is assigned to the activity
    Then the activity contains a user "huba"

Scenario: A user is assigned to a non-existing activity
    Given there does not exist an activity "nan"
    And a user "huba" exists
    When the user is assigned to the activity
    Then the error message "Activity does not exist" is given

Scenario: A user is already part of the activity
    Given there exists a project with activity "data refinement"
    And a user "huba" exists
    And the activity contains the user
    When the user is assigned to the activity
    Then the error message "User is already part of the activity" is given

Scenario: A user is assigned to their 11th activity
    Given there exists an activity "data refinement"
    And a user "huba" exists
    And the user is assigned to 10 activities
    When the user is assigned to the activity
    Then the error message "User has too many activities" is given

Scenario: A peak user is assigned to their 19th activity
    Given there exists an activity "data refinement"
    And a user "jesp" exists
    And the user is assigned to 18 activities
    And the user is peak
    When the user is assigned to the activity
    Then the activty contains the user "jesp"
    And the user is assigned to 19 activities

Scenario: A peak user is assigned to their 21th activity
    Given there exists an activity "data refinement"
    And a user "jesp" exists
    And the user is assigned to 20 activities
    And the user is peak
    When the user is assigned to the activity
    Then the error message "User has to many activities" is given