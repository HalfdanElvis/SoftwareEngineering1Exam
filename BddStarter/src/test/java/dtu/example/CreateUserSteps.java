package dtu.example;

import static org.junit.Assert.assertFalse;

import java.util.Scanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateUserSteps {

    App app = TestHelper.app;
    
    @Given("that user {string} is logged in")
    public void thatUserIsLoggedIn(String string) {
        app.setSignedInEmployee(new Employee(string));

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
