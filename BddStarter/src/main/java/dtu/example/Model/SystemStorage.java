package dtu.example.Model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class SystemStorage {

    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    public Employee getEmployee(String string) {
        for (Employee employee : employees){
            if (employee.getUsername().equals(string)){
                return employee;
            }
        }
        throw new IllegalArgumentException("User does not exist.");
    }

    public Project getProject(int id) {
        for (Project project : projects){
            if (project.getID() == (id)){
                return project;
            }
        }
        throw new IllegalArgumentException("Project does not exist.");
    }

    public void addEmployee(String username) {
        if (employeeExists(username)) {
            throw new IllegalArgumentException("User already exists. Try another username.");
        }
        employees.add(new Employee(username));
    }

    public void deleteEmployee(String username){
        employees.remove(getEmployee(username));
    }

    public boolean employeeExists(String username){
        try {
            getEmployee(username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int createProject(String name, int year) {

        int id = generateProjectID(year);
        Project project = new Project(name, id);
        projects.add(project);
        return id;
    }

    private int generateProjectID(int year) {
        year %= 100;
        year *= 1000;
        
        int projectAmount = 0;

        for (Project p : projects) {
            if (p.getID() >= year && p.getID() < year+1000) {
                projectAmount++;
            }
        }

        if (projectAmount >= 999) {
            throw new IllegalArgumentException("Maximum projects for this year has been reached");
        }
        
        return year+projectAmount+1;
    }

    public void deleteProject(int projectID) {
        projects.remove(getProject(projectID));
    }

    public List<Project> getAllProjects() {
        return projects;
    }
    public List<Employee> getAllEmployees(){
        return employees;
    }

}
