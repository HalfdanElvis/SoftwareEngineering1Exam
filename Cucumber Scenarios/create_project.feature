Feature: Create project
	Description: A user creates a new project
	Actors: User

Scenario: A user creates a project
 	Given that there are 0 projects in year 2025
    And the current year is 2025 
 	When a user creates a project with name "Project X"
 	Then a project with name "Project X" and ID "25001" exists
 	
Scenario: A user creates additional project
	Given that there are 45 projects in year 2089
    And the current year is 2089 
 	When a user creates a project with name "Project X"
 	Then a project with name "Project X" and ID "89046"
 	
Scenario: A user creates a project in another year
	Given that there are 45 projects in year 2003
    And that there are 0 projects in year 2004
    And the current year is 2004
 	When a user creates a project with name "Project X"
 	Then a project with name "Project X" and ID "04001"


Scenario: A user creates a 1000th project
	Given that there are 999 projects in year 2003
    And the current year is 2003
 	When a user creates a project with name "Project X"
 	Then the error message "Maximum projects for this year has been reached" is given

 	


