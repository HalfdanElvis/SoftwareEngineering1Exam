package dtu.example;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private int ID;
    private List<Activity> activites = new ArrayList<>();
    private Employee projectLeader;

    public Project (String name){
        this.name = name;
    }

    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }

    public void setID(int id) {
        ID = id;
    }

    public void setProjectLeader(Employee projectLeader) {
        this.projectLeader = projectLeader;
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public boolean hasProjectLeader() {
        if (projectLeader == null) {
            return false;
        }
        return true;
    }

    public String printProject() {
        return name+", "+ID;
    }
    
    public boolean containsActivity(String string) {
        for (Activity activity : activites) {
            if (activity.getName().equals(string)) {
                return true;
            }
        }
        throw new IllegalArgumentException("Activity doesn't exist.");
    }

}
