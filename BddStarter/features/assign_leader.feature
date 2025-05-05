Feature: Assign Leader
    Description: A user assigns a project leader
    Actors: User

Background: 
    Given that a user "huba" is logged in
    And a project with ID 25001 exists
    
Scenario: a project leader is assigned succesfully
    When the user assigns an employee with intials "huba" as project leader for the project with ID 25001
    Then the project has a project leader "huba"

Scenario: a user assigns a project leader when one is already assigned
    And the project has a project leader "bahu"
    When the user assigns an employee with intials "huba" as project leader for the project with ID 25001
    Then the error message "Already has project leader" is given
    And the project has a project leader "bahu"

Scenario: a project leader assigns a project leader when one is already assigned
    And the project has a project leader "huba"
    When the user assigns an employee with intials "bahu" as project leader for the project with ID 25001
    Then the project has a project leader "bahu"


    
