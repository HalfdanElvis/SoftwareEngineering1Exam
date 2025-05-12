# Written by Marcus

Feature: Create user
Description: User creates a new User
Actors: User

Background:
    Given that user "huba" is logged in

Scenario: User creates a new user
    When the User creates a new User with name "bahu"
    Then the User "bahu" is successfully created

Scenario: User creates a user that already exists
    When the User creates a new User with name "huba"
    Then the error message "User already exists. Try another username." is given

Scenario: User creates a user with too long username
    When the User creates a new User with name "toolong"
    Then the error message "Username cannot be longer than 4 characters." is given

Scenario: User creates a user with spaces in username
    When the User creates a new User with name "s  p"
    Then the error message "Username can't contain spaces." is given

Scenario: User creates a user with empty username
    When the User creates a new User with name ""
    Then the error message "Username can't be empty." is given