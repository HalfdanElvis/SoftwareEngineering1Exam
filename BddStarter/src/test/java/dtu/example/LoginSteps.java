package dtu.example;

import static org.junit.Assert.assertFalse;

import java.util.Scanner;

import dtu.example.Controller.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    App app;
    ErrorMessageHolder errorMessageHolder;
    TestHelper testHelper;
    
    public LoginSteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }


    @Given("the user {string} exist")
    public void theUserExist(String string) {
        app.addEmployee(string);
        
    }

    @Given("the user {string} doesn't exist")
    public void theUserDoesnTExist(String string) {
        assertFalse(app.employeeExists(string));
    }

    @When("the user {string} tries to log in")
    public void theUserTriesToLogIn(String string) {
        try {
            app.login(string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }

    @When("the user {string} confirms the prompt to create a new user {string}")
    public void theUserConfirmsThePromptToCreateANewUser(String string, String string2) {
        assert(app.yesOrNo("Y"));
        app.addEmployee(string);
        app.login(string);
    }

    @When("the user {string} does not confirm the prompt to create a new user {string}")
    public void theUserDoesNotConfirmThePromptToCreateANewUser(String string, String string2) {
        assert(!app.yesOrNo("N"));
    }

    @When("the user {string} gives invalid input to the prompt")
    public void theUserGivesInvalidInputToThePrompt(String string) {
        // Write code here that turns the phrase above into concrete actions
        try {
            assert(app.yesOrNo("invalid"));
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }

    @Then("the user {string} is logged in")
    public void theUserIsLoggedIn(String string) {
        assert(app.getSignedInEmployeeUsername().equals(string));
    }

    @Then("the user {string} exists")
    public void theUserExists(String string) {
        assert(app.employeeExists(string));
    }

    @Then("the user {string} is not logged in")
    public void theUserIsNotLoggedIn(String string) {
        try {
            app.getSignedInEmployee();
            assert(false);
        } catch (Exception e) {
            assert(true);
        }
    }

    @Then("the user {string} does not exist")
    public void theUserDoesNotExist(String string) {
        assert(!app.employeeExists(string));
    }

    @Then("a user is logged in")
    public void aUserIsLoggedIn() {
        assert(app.aUserIsLoggedIn());
    }

    @Then("a user is not logged in")
    public void aUserIsNotLoggedIn() {
        assert(!app.aUserIsLoggedIn());
    }

}
