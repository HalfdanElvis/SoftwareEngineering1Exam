package dtu.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
    


@Given("the user {string} exist")
public void theUserExist(String string) {
    Employee employee = new Employee(string);
    App.addEmployee(employee);
}

@Given("the user {string} doesn't exist")
public void theUserDoesnTExist(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}



@When("then user {string} tries to log in")
public void thenUserTriesToLogIn(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("the user {string} confirms")
public void theUserConfirms(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("then user {string} tries log in")
public void thenUserTriesLogIn(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}




@Then("the user {string} is logged in")
public void theUserIsLoggedIn(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the user {string} is prompted to create the user {string}")
public void theUserIsPromptedToCreateTheUser(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the user {string} exists")
public void theUserExists(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}



@Then("the user {string} is not logged in")
public void theUserIsNotLoggedIn(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

}
