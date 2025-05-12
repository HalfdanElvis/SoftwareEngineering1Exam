# Written by Halfdan

Feature: Login to application
    Description: User tries to login
    Actors: User

Scenario: A user logs in
    Given the user "huba" exist
    When the user "huba" tries to log in
    Then the user "huba" is logged in

Scenario: A non-existent user tries to log in and registers as a user.
    Given the user "bahu" doesn't exist
    When the user "bahu" tries to log in
    When the user "bahu" confirms the prompt to create a new user "bahu"
    Then the user "bahu" exists
    And the user "bahu" is logged in
    And a user is logged in

Scenario: A non-existent user tries to log in and doesn't register 
    Given the user "bahu" doesn't exist
    When the user "bahu" tries to log in
    When the user "bahu" does not confirm the prompt to create a new user "bahu"
    Then the user "bahu" does not exist
    And the user "bahu" is not logged in
    And a user is not logged in

Scenario: A non-existent user tries to log in and fails the prompt
    Given the user "bahu" doesn't exist
    When the user "bahu" tries to log in
    When the user "bahu" gives invalid input to the prompt
    Then the error message "Must be Y/N" is given
