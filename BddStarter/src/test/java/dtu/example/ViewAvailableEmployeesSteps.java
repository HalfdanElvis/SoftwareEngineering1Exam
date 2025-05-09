package dtu.example;

import static org.junit.Assert.assertEquals;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewAvailableEmployeesSteps {

    App app;
    ErrorMessageHolder errorMessageHolder;
    TestHelper testHelper;
    List<String> availableEmployees;
    
    public ViewAvailableEmployeesSteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }

    @Given("the user {string} is assigned {int} activities in week {int} and {int} in the year {int}")
    public void theUserIsAssignedActivitiesInWeekAndInTheYear(String string, Integer int1, Integer int2, Integer int3, Integer int4) {
        for (int i = 0; i < int1; i++) {
            try {
                String activityName = "activity"+i;
                if (!app.projectContainsActivity(testHelper.getProjectID(), activityName)) {
                    app.addActivity(testHelper.getProjectID(), activityName);
                    app.setActivitiyStartAndEndWeek(testHelper.getProjectID(), activityName, int4, int2, int4, int3);
                }
                app.assignEmployeeToActivity(string, testHelper.getProjectID(), activityName);
            } catch (Exception e) {
                errorMessageHolder.setErrorMessage(e.getMessage());
            }
        }
    }

    @When("the user {string} views available employees for week {int} and {int} in the year {int}")
    public void theUserViewsAvailableEmployeesForWeekAndInTheYear(String string, Integer int1, Integer int2, Integer int3) {
        availableEmployees = app.viewAvailableEmployees(int3, int1, int3, int2);
    }

    @Then("the users {string} and {string} are listed as available")
    public void theUsersAndAreListedAsAvailable(String string, String string2) {
        assertEquals(availableEmployees.get(0), string);
        assertEquals(availableEmployees.get(1), string2);
        assertEquals(availableEmployees.size(), 2);
    }

    @Then("the user {string} is listed as available")
    public void theUserIsListedAsAvailable(String string) {
        assertEquals(availableEmployees.get(0), string);
        assertEquals(availableEmployees.size(), 1);
    }

    @Then("no employee is listed as available")
    public void noEmployeeIsListedAsAvailable() {
        assertEquals(availableEmployees.size(), 0);
    }
}
