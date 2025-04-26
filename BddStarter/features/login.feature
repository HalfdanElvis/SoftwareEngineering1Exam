Feature: Login to application
    Description: User tries to login
    Actors: User

Scenario: A user logs in
    Given the user "huba" exist
    When then user "huba" tries to log in
    Then the user "huba" is logged in

Scenario: A non-existent user tries to log in and registers as a user.
    Given the user "bahu" doesn't exist
    When then user "bahu" tries to log in
    Then the user "bahu" registers "bahu"
    Then the user "bahu" exists
    And the user "bahu" is logged in

Scenario: A non-existent user tries to log in and doesn't register 
    Given the user "bahu" doesn't exist
    When then user "bahu" tries to log in
    Then the user "bahu" is prompted to create the user "bahu"
    When the user "bahu" confirms
    Then the user "bahu" doesn't exist
    And the user "bahu" is not logged in
