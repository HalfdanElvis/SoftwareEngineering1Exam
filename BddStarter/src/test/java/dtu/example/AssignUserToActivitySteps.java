package dtu.example;

import dtu.example.dto.EmployeeInfo;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignUserToActivitySteps {

    App app;
    ErrorMessageHolder errorMessageHolder;
    TestHelper testHelper;

    public AssignUserToActivitySteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }

    @Given("an activity {string} exists")
    public void anActivityExists(String string) throws IllegalAccessException {
        testHelper.setProjectID(app.createProject("test"));
        testHelper.setActivityName(string);
        app.addActivity(testHelper.getProjectID(),string);
    }

    @Given("the user is peak")
    public void theUserIsPeak() {
        app.stringToEmployee(testHelper.getUser()).setPeak(true);
        EmployeeInfo employee = app.getEmployeeInfo(testHelper.getUser());
        assert(employee.isPeak() == true);
    }

    @Given("the activity runs from week {int} to week {int} in the year {int}")
    public void theActivityRunsFromWeekWeekInTheYear(Integer startWeek, Integer endWeek, Integer year) {
        app.setActivitiyStartAndEndWeek(testHelper.getProjectID(), testHelper.getActivityName(), year, startWeek, year, endWeek);
    }

    @Given("the user is not assigned the activity")
    public void theUserIsNotAssignedTheActivity() {
        try {
            app.removeEmployeeFromActivity(testHelper.getUser(), testHelper.getActivityName());
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }

    @Given("the user is assigned {int} activities in week {int} to week {int} in the year {int}")
    public void theUserIsAssignedActivitiesInWeekToWeekInTheYear(Integer int1, Integer int2, Integer int3, Integer int4) throws Exception {
        for (int i = 0; i < int1; i++) {
            try {
                String activityName = "activity"+i;
                app.addActivity(testHelper.getProjectID(), activityName);
                app.setActivitiyStartAndEndWeek(testHelper.getProjectID(), activityName, int4, int2, int4, int3);
                app.assignEmployeeToActivity(testHelper.getUser(), testHelper.getProjectID(), activityName);
            } catch (Exception e) {
                errorMessageHolder.setErrorMessage(e.getMessage());
            }
        }
    }

    @Given("the user is assigned the activity")
    public void theUserIsAssignedTheActivity() {
        try {
            app.assignEmployeeToActivity(testHelper.getUser(), testHelper.getProjectID(), testHelper.getActivityName());
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }

    @When("the user gets assigned the activity")
    public void theUserGetsAssignedTheActivity() {
        try {
            app.assignEmployeeToActivity(testHelper.getUser(), testHelper.getProjectID(), testHelper.getActivityName());
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the user gets assigned the activity {string}")
    public void theUserGetsAssignedTheActivity(String string) {
        try {
            app.assignEmployeeToActivity(testHelper.getUser(), testHelper.getProjectID(), string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the user is assigned the activity {string}")
    public void theUserIsAssignedTheActivity(String string) {
        EmployeeInfo employee = app.getEmployeeInfo(testHelper.getUser());
        assert(EmployeeTestHelper.employeeIsAssignedActivity(employee, string));
    }
}
