Feature: Assign User To Project
    Description: A user assigns a user to an project
    Actors: User, Project leader

Scenario: A user is assigned to a project
    Given there exists a project "data refinement project"
    And a user "huba" exists
    When the user is assigned to the project
    Then the project contains a user "huba"

Scenario: A user is assigned to a non-existing project
    Given there does not exist a project "nan"
    And a user "huba" exists
    When the user is assigned to the project
    Then the error message "Project does not exist" is given

Scenario: A user is already part of the project
    Given there exists a project "data refinement project"
    And a user "huba" exists
    And the project contains the user
    When the user is assigned to the project
    Then the error message "User is already part of the project" is given
