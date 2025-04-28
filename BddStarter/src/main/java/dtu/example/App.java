package dtu.example;

import java.util.ArrayList;
import java.util.List;

public class App {

    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<Activity> specialActivites = new ArrayList<>();

    private CalenderHelper ch = new CalenderHelper();
    private Employee signedInEmployee;
    private Employee selectedEmployee;
    private Boolean registrationConfirmation = null;



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

    }

    // WIP
    public void printAllActivities() {

    }

    public void setSelectedEmployee(String username) {
        selectedEmployee = stringToEmployee(username);
    }
    


}
