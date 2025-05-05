package dtu.example;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class App {

    private int year;

    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<SpecialActivity> specialActivites = new ArrayList<>();

    private CalenderHelper ch = new CalenderHelper();
    private Employee signedInEmployee;
    private Employee selectedEmployee;
    private Boolean registrationConfirmation = null;

    private SpecialActivity selectedSpecialActivity;



    public boolean login(String username) {
        legalUsername(username);

        signedInEmployee = stringToEmployee(username);
        return signedInEmployee != null;
    }
    

    public boolean yesOrNo(String userInput){
        userInput = userInput.toUpperCase();
        if (userInput.equals("Y")){
            return true;
        } else if (userInput.equals("N")){
            return false;
        } else {
            throw new IllegalArgumentException("Must be Y/N");
        }
    }

    public void addEmployee(String username) {
        if (employeeExists(username)) {
            throw new IllegalArgumentException("User already exists. Try another username.");
        }
        employees.add(new Employee(username));
    }

    public Employee stringToEmployee(String string) {
        for (Employee employee : employees){
            if (employee.getUsername().equals(string)){
                return employee;
            }
        }
        return null;
    }

    public void deleteEmployee(String username){
        employees.remove(stringToEmployee(username));
    }

    public String getSignedInEmployeeUsername() {
        return signedInEmployee.getUsername();
    }

    
    public boolean employeeExists(String username){
        return stringToEmployee(username) != null;
    }

    public void setRegistrationConfirmation(boolean registrationConfirmation) {
        this.registrationConfirmation = registrationConfirmation;
    }

    public boolean legalUsername(String username) {
        if (username.length() > 4) {
            throw new IllegalArgumentException("Error, username cannot be longer than 4 characters.");
        } else {
            return true;
        }
    }

    public Employee getSignedInEmployee() {
        return signedInEmployee;
    }


    public void setSignedInEmployee(Employee signedInEmployee) {
        this.signedInEmployee = signedInEmployee;
    }

    // For testing:
    public void printAllEmployees() {
        for (Employee employee : employees){
            System.out.println(employee.getUsername());
        }
    }

    public void printAllProjects() {
        for (Project project : projects){
            System.out.println(project.getName()+": "+project.getID());
        }
    }

    // WIP
    public void printAllSpecialActivities() {
        for (SpecialActivity activity : specialActivites){
            System.out.println(activity.getName());
        }
    }

    // WIP
    public void printAllActivities() {

    }

    public void setSelectedEmployee(String username) {
        selectedEmployee = stringToEmployee(username);
    }

    public void setSelectedSpecialActivity(String username) {
        selectedSpecialActivity = stringToSpecialActivity(username);
    }

    public SpecialActivity getSelectedSpecialActivity() {
        return selectedSpecialActivity;
    }

    public SpecialActivity stringToSpecialActivity(String string) {
        for (SpecialActivity activity : specialActivites){
            if (activity.getName().equals(string)){
                return activity;
            }
        }
        return null;
    }

    public boolean specialActivityExists(String string) {
        if (stringToSpecialActivity(string) == null) {
            throw new IllegalArgumentException("Activity doesn't exist.");
        }
        return true;
    }

    public void addSpecialActivity(SpecialActivity sa){
        specialActivites.add(sa);
    }

    public Project createProject(String name) {
        year = Year.now().getValue();
        year %= 100;
        year *= 1000;
        
        int projectNumber = 0;
        
        Project project = new Project(name);
        

        for (Project p : projects) {
            if (p.getID() >= year && p.getID() < year+100) {
                projectNumber++;
            }
        }

        project.setID(year+projectNumber+1);

        projects.add(project);
        
        return project;
    }


    public void setYear(int year) {
        this.year = year;
    }
    
    public boolean projectExists(String string, int id) {
        for (Project p : projects) {
            if (p.getName().equals(string) && p.getID() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean projectContainsActivity(int id, String activity) {
        if (intToProject(id).stringToActivity(activity) == null) {
            return false;
        }
        return true;
    }

    public Project intToProject(int id) {
        for (Project project : projects){
            if (project.getID() == (id)){
                return project;
            }
        }
        return null;
    }


    public void assignLeader(String username, Integer id) {
        Employee employee = stringToEmployee(username);
        Project project = intToProject(id);

        if (!project.hasProjectLeader()) {
            project.setProjectLeader(employee);
        } else if (signedInEmployee.equals(project.getProjectLeader())) {
            project.setProjectLeader(employee);
        } else {
            throw new IllegalArgumentException("Only project leader can change project leader");
        }
    }

    public boolean projectHasLeader(int id) {
        return intToProject(id).hasProjectLeader();
    }

    public void printProjectList(int year) {
        year %= 100;

        for (Project project : projects) {
            int id = project.getID();
            while (id >= 100) {
                id /= 10;
            }
            if (year == id) {
                System.out.println(project.printProject());
            }
        }
    }


    public boolean specialActivityNameTaken(String activityName) {
        for(SpecialActivity sa : specialActivites) {
            if (sa.getName().equals(activityName)){
                throw new IllegalArgumentException("Special Activity with that name already exists.");
            }
            
        }
        return true;
    }
}
