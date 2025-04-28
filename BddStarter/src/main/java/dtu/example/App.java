package dtu.example;

import java.util.ArrayList;
import java.util.List;

public class App {

    private Employee signedInEmployee;
    
    public Employee getSignedInEmployee() {
        return signedInEmployee;
    }


    public void setSignedInEmployee(Employee signedInEmployee) {
        this.signedInEmployee = signedInEmployee;
    }

    private List<Employee> employees = new ArrayList<>();

    private Boolean registrationConfirmation = null;



    public boolean login(String username) {
        if (username.length() > 4) {
            throw new IllegalArgumentException("Error, username cannot be longer than 4 characters.");
        }

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

    public void legalUsername(String username) {
        if (username.length() > 4) {
            throw new IllegalArgumentException("Error, username cannot be longer than 4 characters.");
        }
    }


    // For testing:
    public void printAllEmployees() {
        for (Employee employee : employees){
            System.out.println(employee.getUsername());
        }
    }


}
