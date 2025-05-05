package dtu.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivityExpectedHoursSteps {

<<<<<<< Updated upstream
    private App app;
    private ErrorMessageHolder errorMessage;
    private TestHelper testHelper;
    
=======

    private App app = new App();
>>>>>>> Stashed changes
    private int pID;
    private String username;
    private String aName;

<<<<<<< Updated upstream

    public ActivityExpectedHoursSteps (App app, ErrorMessageHolder errorMessage, TestHelper testHelper) {
        this.app = app;
        this.errorMessage = errorMessage;
        this.testHelper = testHelper;
    }


    
=======
>>>>>>> Stashed changes
    @Given("that there exists a project with id {int} and name {string}")
    public void thatThereExistsAProjectWithIdAndName(Integer int1, String string) {
        pID = int1;
        app.createProject(string).setID(pID);

    }

    @Given("the project with id {int} contains an activity with name {string}")
    public void theProjectWithIdContainsAnActivityWithName(Integer int1, String string) {
<<<<<<< Updated upstream
        aName = string;
        app.intToProject(pID).createActivity(aName);
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

        if (!(app.getSignedInEmployee().equals(app.intToProject(pID).getProjectLeader()))){
            Employee temp1 = app.getSignedInEmployee();
            app.setSignedInEmployee(app.intToProject(pID).getProjectLeader());
            app.intToProject(pID).stringToActivity(aName).setExpectedHours(int1);
            app.setSignedInEmployee(temp1);
        } else {
            app.intToProject(pID).stringToActivity(aName).setExpectedHours(int1);
        }
        
=======
        app.intToProject(pID).stringToActivity(aName).setExpectedHours(int1);
>>>>>>> Stashed changes
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
