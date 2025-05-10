package dtu.example;

import dtu.example.dto.ProjectInfo;
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

    @Given("that there exists a project {string}")
    public void thatThereExistsAProjectWithProjectID(String string) {
        testHelper.setProjectID(app.createProject(string));
    }

    @Given("the project does not have a project leader")
    public void theProjectDoesNotHaveAProjectLeader() {
        ProjectInfo project = app.createDTOProject(testHelper.getProjectID());
        assert(project.getProjectLeaderUsername() == "");
    }

    @Given("the project has a project leader {string}")
    public void theProjectHasAProjectLeader(String string) {
        app.assignLeader(string, testHelper.getProjectID());
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
        ProjectInfo project = app.createDTOProject(testHelper.getProjectID());
        assert(ProjectTestHelper.projectContainsActivity(project, string));
    }

}
