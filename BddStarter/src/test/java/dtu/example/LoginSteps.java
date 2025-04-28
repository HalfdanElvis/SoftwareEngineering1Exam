package dtu.example;

import static org.junit.Assert.assertFalse;

import java.util.Scanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    App app = TestHelper.app;


    @Given("the user {string} exist")
    public void theUserExist(String string) {
        if (app.employeeExists(string)){
            assert(true);
        } else {
            app.addEmployee(string);
        }
        
    }

    @Given("the user {string} doesn't exist")
    public void theUserDoesnTExist(String string) {
        app.deleteEmployee(string);
    }



    @When("the user {string} tries to log in")
    public void theUserTriesToLogIn(String string) {
        app.login(string);
    }

    @When("the user {string} does confirm")
    public void theUserDoesConfirm(String string) {
        app.addEmployee(string);
        app.login(string);
    }

    @When("the user {string} does not confirm")
    public void theUserDoesNotConfirm(String string) {
        while (true) {
            break;
        }
    }

    @Then("the user {string} is prompted to create the user {string}")
    public void theUserIsPromptedToCreateTheUser(String string, String string2) {
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
        assert(app.getSignedInEmployee() == null || !app.getSignedInEmployeeUsername().equals(string));
    }

    @Then("the user {string} does not exist")
    public void theUserDoesNotExist(String string) {
    }


}
