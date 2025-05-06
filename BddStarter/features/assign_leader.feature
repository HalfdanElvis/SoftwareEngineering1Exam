Feature: Assign Leader
    Description: A user assigns a project leader
    Actors: User

Background: 
    Given that user "huba" is logged in
    And a project with ID 25001 exists
    
Scenario: a project leader is assigned succesfully
    When the user assigns an employee with initials "huba" as project leader for the project with ID 25001
    Then the project should have a project leader "huba"

Scenario: a user assigns a project leader when one is already assigned
    Given the user "bahu" exists in the system
    And the project has a project leader set to "bahu"
    When the user assigns an employee with initials "huba" as project leader for the project with ID 25001
    Then the error message "Only project leader can change project leader." is given
    And the project should have a project leader "bahu"

Scenario: a project leader assigns a project leader when one is already assigned
    And the project has a project leader set to "huba"
    Given the user "bahu" exists in the system
    When the user assigns an employee with initials "bahu" as project leader for the project with ID 25001
    Then the project should have a project leader "bahu"