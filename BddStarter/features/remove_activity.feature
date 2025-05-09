
Feature: Remove activity
	Description: A user removes an existing activity
	Actors: User, project leader

Background:
	Given that user "huba" is logged in
	And that there exists a project "projectX" with project ID 25001

Scenario: A user removes an activity in a project
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