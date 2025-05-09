package dtu.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateActivitySteps {

    App app;
    TestHelper testHelper;
    ErrorMessageHolder errorMessageHolder;


    public CreateActivitySteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }

    @Given("that there exists a project {string} with project ID {int}")
    public void thatThereExistsAProjectWithProjectID(String string, Integer id) {
        app.createProject(string);
        testHelper.setProjectID(id);
    }

    @Given("the project does not have a project leader")
    public void theProjectDoesNotHaveAProjectLeader() {
        assert(!app.projectHasLeader(testHelper.getProjectID()));
    }

    @Given("the project has a project leader {string}")
    public void theProjectHasAProjectLeader(String string) {
        app.assignLeader(string, testHelper.getProjectID());
    }

    @When("the user creates an activity {string} in the project")
    public void theUserCreatesAnActivityInTheProject(String string) {
        try {
            app.addActivity(testHelper.getProjectID(), string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    
    @Then("the project should contain an activity {string}")
    public void theProjectShouldContainAnActivity(String string) {
        assert(app.projectContainsActivity(testHelper.getProjectID(), string));
    }

    @Given("the project contains an activity {string}")
    public void theProjectContainsAnActivity(String string) {
        try {
            app.addActivity(testHelper.getProjectID(), string);
            testHelper.setActivityName(string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("the user is assigned {int} activities in week {int} in the year {int}")
    public void theUserIsAssignedActivitiesInWeekInTheYear(Integer int1, Integer int2, Integer int3) {
        for (int i = 0; i < int1; i++) {
            try {
                String activityName = "activity"+i;
                app.addActivity(testHelper.getProjectID(), activityName);
                app.setActivitiyStartAndEndWeek(testHelper.getProjectID(), activityName, int3, int2, int3, int2);
                app.assignEmployeeToActivity(testHelper.getUser(), testHelper.getProjectID(), activityName);
            } catch (Exception e) {
                errorMessageHolder.setErrorMessage(e.getMessage());
            }
        }
    }

    @Given("the user is assigned a special activity {string} in week {int} in the year {int}")
    public void theUserIsAssignedASpecialActivityInWeekInTheYear(String string, Integer int1, Integer int2) {
        app.addSpecialActivity(string, int2, int1, int2, int1);
    }

    @When("the user creates a special activity {string} in week {int} of the year {int}")
    public void theUserCreatesASpecialActivityInWeekOfTheYear(String string, Integer int1, Integer int2) {
        try {
            app.addSpecialActivity(string, int2, int1, int2, int1);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }
    
    @Then("the user is assigned to the special activity {string}")
    public void theUserIsAssignedToTheSpecialActivity(String string) {
        app.employeeIsAssignedActivity(testHelper.getUser(), testHelper.getActivityName());
    }
}
