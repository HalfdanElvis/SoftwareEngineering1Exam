package dtu.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateUserSteps {

    App app = TestHelper.app;

    
    @Given("that user {string} is logged in")
    public void thatUserIsLoggedIn(String string) {
        TestHelper.username = string;
        String username = TestHelper.username;

        if (app.employeeExists(username)){
            app.setSignedInEmployee(app.stringToEmployee(username));
        } else {
            app.addEmployee(username);
            app.setSignedInEmployee(app.stringToEmployee(username));
        }

        TestHelper.app = app;
    }

    @When("the User creates a new User with name {string}")
    public void theUserCreatesANewUserWithName(String string) {
        app.addEmployee(string);
    }


    @Then("the User {string} is successfully created")
    public void theUserIsSuccessfullyCreated(String string) {
        app.employeeExists(string);
    }

    @Then("the {string} exception is thrown")
    public void theExceptionIsThrown(String string) {
    // IDK HOW TO WRITE THIS
}
}
