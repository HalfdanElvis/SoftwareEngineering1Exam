package dtu.example;

public class TestHelper {
    
    private String user;
    private int projectID;
    private String activityName;
    private Employee employee;

    public TestHelper() {

    }

    public Employee getUser() {
        return employee;
    }

    public void setUser(String user) {

        this.user = user;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

}
