Feature: View Available Employees
    Description: A user can view all available employees
    Actors: User

Background:
    Given a user "huba" exists
    Given a user "bahu" exists

Scenario: Two employees are available
    And the user "huba" is assigned 0 activities in week 10 and 11 in the year 2025
    And the user "bahu" is assigned 0 activities in week 10 and 11 in the year 2025
    When the user "huba" views available employees for week 10 and 11 in the year 2025
    Then the users "huba" and "bahu" are listed as available

Scenario: One employees is available, one is unavailable
    And the user "huba" is assigned 10 activities in week 10 and 11 in the year 2025
    And the user "bahu" is assigned 0 activities in week 10 and 11 in the year 2025
    When the user "huba" views available employees for week 10 and 11 in the year 2025
    Then the user "bahu" is listed as available

Scenario: Two employees are unavailable
    And the user "huba" is assigned 10 activities in week 10 and 11 in the year 2025
    And the user "bahu" is assigned 10 activities in week 10 and 11 in the year 2025
    When the user "huba" views available employees for week 10 and 11 in the year 2025
    Then no employee is listed as available