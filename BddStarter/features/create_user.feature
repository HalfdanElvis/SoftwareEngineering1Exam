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
    Then the "User already exists" exception is thrown

