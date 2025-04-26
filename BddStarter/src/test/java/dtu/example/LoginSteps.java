package dtu.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    App app = TestHelper.app;


    @Given("the user {string} exist")
    public void theUserExist(String string) {
        app.addEmployee(string);
    }

    @Given("the user {string} doesn't exist")
    public void theUserDoesnTExist(String string) {
        for (Employee employee : app.employees){
            if (employee.getUsername().equals(string)){
                app.deleteEmployee(string);
            }
        }
    }



    @When("then user {string} tries to log in")
    public void thenUserTriesToLogIn(String string) {
        app.login(string);
    }

    @When("the user {string} confirms")
    public void theUserConfirms(String string) {
        app.setRegistrationConfirmation(true);
    }




    @Then("the user {string} is logged in")
    public void theUserIsLoggedIn(String string) {
        assert(app.getSignedInEmployeeUsername().equals(string));
    }

    @Then("the user {string} is prompted to create the user {string}")
    public void theUserIsPromptedToCreateTheUser(String string, String string2) {
        // System.out.println("Employee not found, create new employee with name "+string+" Y/N?");
        // 
        // Not sure what should go here.
        
    }

    @Then("the user {string} exists")
    public void theUserExists(String string) {
        assert(app.employeeExists(string));
    }

    @Then("the user {string} is not logged in")
    public void theUserIsNotLoggedIn(String string) {
        assert(!(app.signedInEmployee.getUsername().equals(string)));
    }

}
