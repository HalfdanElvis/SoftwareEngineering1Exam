Feature: Generate Report
	Description: A project leader generates a report containing information about a project
	Actors: Project leader

Scenario: project leader generates report
    Given a project exists
    And a user "huba" exists
    And the user is the project leader
    When the user generates a report
    Then the report exists

Scenario: user generates report
    Given a project exists
    And a user "huba" exists
    And the user is not the project leader
    When the user generates a report
    Then the error message "Only the project leader can generate a report"



