package dtu.example;

import dtu.example.dto.EmployeeInfo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateSpecialActivitySteps {

    App app;
    TestHelper testHelper;
    ErrorMessageHolder errorMessageHolder;


    public CreateSpecialActivitySteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
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
        EmployeeInfo employee = app.getEmployeeInfo(testHelper.getUser());
        assert(EmployeeTestHelper.employeeIsAssignedSpecialActivity(employee, string));
    }
}
