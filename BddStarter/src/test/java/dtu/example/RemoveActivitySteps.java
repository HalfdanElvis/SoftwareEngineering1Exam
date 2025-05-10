package dtu.example;

import static org.junit.Assert.assertFalse;

import dtu.example.DTO.ProjectInfo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RemoveActivitySteps {

    App app;
    ErrorMessageHolder errorMessageHolder;
    TestHelper testHelper;
    
    public RemoveActivitySteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }
    
    @When("the user removes the activity {string} in the project")
    public void theUserRemovesTheActivityInTheProject(String string) {
        try {
            app.deleteActivity(testHelper.getProjectID(), string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }
    @Then("the project should not contain an activity {string}")
    public void theProjectShouldNotContainAnActivity(String string) {
        ProjectInfo project = app.createDTOProject(testHelper.getProjectID());
        assertFalse(ProjectTestHelper.projectContainsActivity(project, string));
    }
}
