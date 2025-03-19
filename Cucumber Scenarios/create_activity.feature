Feature: Create activity
	Description: A user creates a new activity
	Actors: User, project leader

Scenario: A user creates an activity
 	When a user creates an with name "Holidays"
 	Then an activity with name "Holidays"

Scenario: A user creates an activity that already exists
    Given an activity "Data Refinement" exists
 	When a user creates a activity "Data Refinement"
 	Then the error message "Activity already exists"

Scenario: A user creates an activity that already exists in a project
 	Given that there exists a project
    And the project does not have a project leader
    And the project contains an activity "Data Refinement"
 	When a user creates a activity "Data Refinement" in the project
 	Then the error message "Activity already exists in project"

Scenario: A user creates an activity in a project
 	Given that there exists a project
    And the project does not have a project leader
 	When a user creates a activity "Data Refinement" in the project
 	Then the project contains an activity "Data Refinement"
 	
Scenario: A user fails to create an activity in a project
 	Given that there exists a project
    And the project has a project leader
    And a user "huba" exists
    And the user is not the project leader
 	When a user creates a activity "Data Refinement" in the project
 	Then the error message "Only the project leader can create an activity"
 	
Scenario: A project leader creates an activity in a project
 	Given that there exists a project
    And the has a project leader
    And a user "huba" exists
    And the user is the project leader
 	When a user creates a activity "Data Refinement" in the project
 	Then the project contains an activity "Data Refinement"
 	

