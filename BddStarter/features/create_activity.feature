
Feature: Create activity
	Description: A user creates a new activity
	Actors: User, project leader

Background:
	Given that user "huba" is logged in

Scenario: A user creates an activity in a project
 	Given that there exists a project "projectX" with project ID 25001
    And the project does not have a project leader
 	When the user creates an activity "Data Refinement" in the project
 	Then the project should contain an activity "Data Refinement"

Scenario: A user creates an activity that already exists in a project
 	Given that there exists a project "projectX" with project ID 25001
    And the project does not have a project leader
    And the project contains an activity "Data Refinement"
 	When the user creates an activity "Data Refinement" in the project
 	Then the error message "Activity already exists in project" is given

Scenario: A user fails to create an activity in a project with a project leader
 	Given that there exists a project "projectX" with project ID 25001
    And the user "bahu" is the project leader
 	When the user creates an activity "Data Refinement" in the project
 	Then the error message "Only the project leader can create an activity" is given
 	
Scenario: A project leader creates an activity in a project
 	Given that there exists a project "projectX" with project ID 25001
    And the project has a project leader "huba"
 	When the user creates an activity "Data Refinement" in the project
 	Then the project contains an activity "Data Refinement"
 	
Scenario: A user creates a special activity
	And the user is assigned 0 activities in week 5 in the year 2025
 	When the user creates a special activity "Holidays" in week 5 of the year 2025
 	Then the user is assigned to the special activity "Holidays"

Scenario: A user tries to create a special activity that overlaps with other activities
	Given that there exists a project "projectX" with project ID 25001
	And the user is assigned 1 activities in week 5 in the year 2025
	When the user creates a special activity "Holidays" in week 5 of the year 2025
 	Then the error message "Special activities cannot overlap with other activities" is given
