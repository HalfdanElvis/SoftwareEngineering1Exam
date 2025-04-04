Feature: Assign Leader
    Description: A user assigns a project leader
    Actors: User
Background: 
    Given  that a user "huba is logged in

Scenario: a project leader is assigned succesfully
    And the user is part of a project with name "project x"
    When the user assigns a user with intials "huba" as project leader for the project
    Then the project has a project leader "huba"

Scenario: a user assigns a project leader When One Is Already Assigned
    And the user is part of a project with name "project x"
    And the project has a project leader "bahu"
    When the user assigns a user "huba" as project leader for the project
    Then the project has a project leader "bahu"

Scenario: a project leader assigns a project leader When One Is Already Assigned
    And the user is part of a project with name "project x"
    And the project has a project leader "huba"
    When the user assigns a user with intials "bahu" as project leader for the project
    Then the project has a project leader "bahu"


    
