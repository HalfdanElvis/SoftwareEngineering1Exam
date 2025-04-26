package dtu.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    Scanner input = new Scanner(System.in);

    
    Employee signedInEmployee;
    List<Employee> employees = new ArrayList<>();

    public void login(String username) {
        if (username.length() > 4) {
            System.out.println("Error, username cannot be longer than 4 characters.");
            return;
        }

        signedInEmployee = stringToEmployee(username);
        if (signedInEmployee != null) {
            return;
        }

        if (promtUserForRegistration(username)){
            addEmployee(username);
            signedInEmployee = stringToEmployee(username);
        }

        System.out.println("Printing all users in system:");
        for (Employee employee : employees) { System.out.print(employee.getUsername()); }
    }

    private boolean promtUserForRegistration(String username) {
        System.out.println("Employee not found, create new employee with name " + username + " Y/N?");
        while (true) {
            String string = input.next().toUpperCase();
            if (string.equals("Y")){
                return true;
            } else if (string.equals("N")){
                return false;
            }
        }
    }

    public void addEmployee(String username) {
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

}
