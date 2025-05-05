package dtu.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivityExpectedHoursSteps {


    private App app = new App();
    private int pID;
    private String username;
    
    @Given("that user {string} is logged in")
    public void thatUserIsLoggedIn(String string) {
        username = string;

        if (app.employeeExists(username)){
            app.setSignedInEmployee(app.stringToEmployee(string));
        } else {
            app.setSignedInEmployee(new Employee(string));
        }  
    }

    @Given("that there exists a project with id {int} and name {string}")
    public void thatThereExistsAProjectWithIdAndName(Integer int1, String string) {
        pID = int1;
        app.createProject(string).setID(pID);

    }

    @Given("the project with id {int} contains an activity with name {string}")
    public void theProjectWithIdContainsAnActivityWithName(Integer int1, String string) {
        app.intToProject(pID).addActivity(string);
    }

    @Given("the user is the project leader")
    public void theUserIsTheProjectLeader() {
        app.intToProject(pID).setProjectLeader(projectLeader);
        
    }

    @When("the user sets the activity's expected total work hours to {int}")
    public void theUserSetsTheActivitySExpectedTotalWorkHoursTo(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the activity's expected total work hours is already {int}")
    public void theActivitySExpectedTotalWorkHoursIsAlready(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the user is not the project leader")
    public void theUserIsNotTheProjectLeader() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the activity's expected total work hours is {int}")
    public void theActivitySExpectedTotalWorkHoursIs(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
