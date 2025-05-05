package dtu.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivityExpectedHoursSteps {


    private App app = new App();
    private int pID;
    private String username;
    private String aName;
    
    @Given("that user {string} is logged in")
    public void thatUserIsLoggedIn(String string) {
        username = string;

        if (app.employeeExists(username)){
            app.setSignedInEmployee(app.stringToEmployee(username));
        } else {
            app.addEmployee(username);
            app.setSignedInEmployee(app.stringToEmployee(username));
        }  
    }

    @Given("that there exists a project with id {int} and name {string}")
    public void thatThereExistsAProjectWithIdAndName(Integer int1, String string) {
        pID = int1;
        app.createProject(string).setID(pID);

    }

    @Given("the project with id {int} contains an activity with name {string}")
    public void theProjectWithIdContainsAnActivityWithName(Integer int1, String string) {
        aName = string;
        app.intToProject(pID).createActivity(aName);
    }

    @Given("the user is the project leader")
    public void theUserIsTheProjectLeader() {
        app.intToProject(pID).setProjectLeader(app.stringToEmployee(username));
    }

    @Given("the user is not the project leader")
    public void theUserIsNotTheProjectLeader() {
        app.intToProject(pID).setProjectLeader(null);
    }

    @Given("the activity's expected total work hours is already {int}")
    public void theActivitySExpectedTotalWorkHoursIsAlready(Integer int1) {
        app.intToProject(pID).stringToActivity(aName).setExpectedHours(int1);
    }

    @When("the user sets the activity's expected total work hours to {int}")
    public void theUserSetsTheActivitySExpectedTotalWorkHoursTo(Integer int1) {
        app.intToProject(pID).stringToActivity(aName).setExpectedHours(int1);
    }

    @Then("the activity's expected total work hours is {int}")
    public void theActivitySExpectedTotalWorkHoursIs(Integer int1) {
        assert(int1 == app.intToProject(pID).stringToActivity(aName).getExpectedHours());
    }
}
