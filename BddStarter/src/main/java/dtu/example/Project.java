package dtu.example;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private int ID;
    private List<Activity> activites = new ArrayList<>();

    public Project (String name){
        this.name = name;
    }

    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    
}
