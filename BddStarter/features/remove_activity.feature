# Written by Simon

Feature: Remove activity
	Description: A user removes an existing activity
	Actors: User, project leader

Background:
	Given that user "huba" is logged in
	And that there exists a project "projectX"

Scenario: A user removes an activity in a project
    And the project does not have a project leader
	And the project contains an activity "Data Refinement"
 	When the user removes the activity "Data Refinement" in the project
 	Then the project should not contain an activity "Data Refinement"

Scenario: A user removes a non-existent activity in a project
    And the project does not have a project leader
	When the user removes the activity "not a real activity" in the project
 	Then the error message "Activity does not exist" is given

Scenario: A user fails to remove an activity in a project with a project leader
	And the project contains an activity "Data Refinement"
    And the user "bahu" is the project leader
 	When the user removes the activity "Data Refinement" in the project
 	Then the error message "Only the project leader can remove an activity" is given
 	
Scenario: A project leader removes an activity in a project
	And the project contains an activity "Data Refinement"
    And the user "huba" is the project leader
 	When the user removes the activity "Data Refinement" in the project
 	Then the project should not contain an activity "Data Refinement"