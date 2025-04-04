Feature: Assign Leader
    Description: A user assigns a project leader
    Actors: User

Background: 
    Given  that a user "huba" is logged in
    And a project with ID "0124" exists
    
Scenario: a project leader is assigned succesfully
    When the user assigns an employee with intials "huba" as project leader for the project
    Then the project has a project leader "huba"

Scenario: a user assigns a project leader When One Is Already Assigned
    And the project has a project leader "bahu"
    When the user assigns an employee "huba" as project leader for the project
    Then the "Already has projectleader" execption is thrown

Scenario: a project leader assigns a project leader When One Is Already Assigned
    And the project has a project leader "huba"
    When the user assigns a user with intials "bahu" as project leader for the project
    Then the project has a project leader "bahu"


    
