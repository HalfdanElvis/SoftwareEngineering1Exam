package dtu.example;

import static org.junit.Assert.assertFalse;

import dtu.example.Controller.App;
import dtu.example.dto.EmployeeInfo;
import dtu.example.dto.ProjectInfo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RemoveUserFromActivitySteps {

    App app;
    ErrorMessageHolder errorMessageHolder;
    TestHelper testHelper;
    
    public RemoveUserFromActivitySteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }

    @When("the user gets removed from the activity")
    public void theUserGetsRemovedFromTheActivity() {
        try {
            app.removeEmployeeFromActivity(testHelper.getUser(), testHelper.getActivityName());
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }

    @Then("the user is not assigned the activity {string}")
    public void theUserIsNotAssignedTheActivity(String string) {
        EmployeeInfo employee = app.getEmployeeInfo(testHelper.getUser());
        assertFalse(EmployeeTestHelper.employeeIsAssignedActivity(employee, string));
    }

    @Given("the user is not assigned the special activity {string}")
    public void theUserIsNotAssignedTheSpecialActivity(String string) {
        theUserIsNotAssignedToTheSpecialActivity(string);
    }

    @When("the user gets removed from the special activity {string}")
    public void theUserGetsRemovedFromTheSpecialActivity(String string) {
        try {
            app.removeEmployeeFromSpecialActivity(testHelper.getUser(), string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the user is not assigned to the special activity {string}")
    public void theUserIsNotAssignedToTheSpecialActivity(String string) {
        EmployeeInfo employee = app.getEmployeeInfo(testHelper.getUser());
        assertFalse(EmployeeTestHelper.employeeIsAssignedSpecialActivity(employee, string));
    }

}
