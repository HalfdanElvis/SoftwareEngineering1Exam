package dtu.example.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import dtu.example.Model.DateServer;
import dtu.example.Model.Employee;
import dtu.example.Model.Project;
import dtu.example.Model.SpecialActivity;
import dtu.example.Model.SystemStorage;
import dtu.example.Utility.CalendarHelper;
import dtu.example.dto.*;

public class App {

    private DateServer dateServer = new DateServer();
    private SystemStorage systemStorage = new SystemStorage();
    private Employee signedInEmployee;


    public boolean login(String username) {
        legalUsername(username);
        try {
            signedInEmployee = stringToEmployee(username);
            return true;
        } catch (Exception e) {
            return false;
        }
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

    public boolean legalUsername(String username) {
        if (username.length() > 4) {
            throw new IllegalArgumentException("Username cannot be longer than 4 characters.");
        } else if (username.contains(" ")) {
            throw new IllegalArgumentException("Username can't contain spaces.");
        } else if (username.contentEquals("")) {
            throw new IllegalArgumentException("Username can't be empty.");
        } else {
            return true;
        }
    }

    public EmployeeInfo getSignedInEmployeeInfo() {
        return new EmployeeInfo(signedInEmployee);
    }

    public void setSignedInEmployee(String signedInEmployee) {
        this.signedInEmployee = stringToEmployee(signedInEmployee);
    }

    public void removeSignedInEmployee() {
        this.signedInEmployee = null;
    }

    public void addEmployee(String username) {
        if (legalUsername(username)) {
            systemStorage.addEmployee(username);
        }
    }

    public Employee stringToEmployee(String string) {
        return systemStorage.getEmployee(string);
    }

    public void deleteEmployee(String username){
        if (employeeExists(username)){
            systemStorage.deleteEmployee(username);
        }
    }

    public boolean aUserIsLoggedIn() {
        return signedInEmployee != null;
    }

    public void setEmployeePeak(String username, boolean peak) {
        stringToEmployee(username).setPeak(peak);
    }

    public List<String> viewAvailableEmployees(int startYear, int startWeek, int endYear, int endWeek) {
        List<String> availableEmployees = new ArrayList<>();
        for (Employee employee : systemStorage.getAllEmployees()) {
            if (employee.isAvailable(startYear, startWeek, endYear, endWeek)) {
                availableEmployees.add(employee.getUsername());
            }
        }
        return availableEmployees;
    }

    public boolean employeeExists(String employee) {
        return systemStorage.employeeExists(employee);
    }

    public void addActivity(int projectID, String activityName) throws IllegalAccessException {
        systemStorage.getProject(projectID).createActivity(activityName, signedInEmployee);
    }

    public void deleteActivity(int projectID, String activityName) throws IllegalAccessException {
        systemStorage.getProject(projectID).removeActivity(activityName, signedInEmployee);
    }

    public void addSpecialActivity(String activityName, String username, int startYear, int startWeek, int endYear, int endWeek){
        Employee employee = systemStorage.getEmployee(username);
        employee.assignSpecialActivity(activityName, startYear, startWeek, endYear, endWeek);
    }

    public void deleteSpecialActivity(String activityName, String username){
        stringToEmployee(username).removeSpecialActivity(activityName);
    }

    public int createProject(String name) {
        return systemStorage.createProject(name, dateServer.getYear());
    }

    public void assignLeader(String username, Integer id) {
        Employee employee = systemStorage.getEmployee(username);
        Project project = systemStorage.getProject(id);
        project.assignLeader(employee, signedInEmployee);
    }

    public void setActivityExpectedHours(int projectID, String activityName, float hours) throws IllegalAccessException {
        systemStorage.getProject(projectID).setActivityExpectedHours(activityName, hours, signedInEmployee);
    }

    public void assignEmployeeToActivity(String username, int projectID, String activtyName) throws Exception {
        Employee employee = stringToEmployee(username);
        Project project = systemStorage.getProject(projectID);
        project.assignEmployeeToActivity(employee, activtyName);
    }

    public void removeEmployeeFromActivity(String username, String activtyName) {
        Employee employee = systemStorage.getEmployee(username);
        employee.removeActivity(activtyName);
    }

    public void removeEmployeeFromSpecialActivity(String username, String activtyName) {
        Employee employee = systemStorage.getEmployee(username);
        employee.removeActivity(activtyName);
    }

    public void setActivitiyStartAndEndWeek(int projectID, String activityName, int startYear, int startWeek, int endYear, int endWeek) {
        Project project = systemStorage.getProject(projectID);
        project.setActivitiyStartAndEndWeek(activityName, startYear, startWeek, endYear, endWeek);
    }

    public void logHours(int projectID, String activityName, String dateAsString, float hours) throws ParseException {
        Project project = systemStorage.getProject(projectID);
        Calendar date = CalendarHelper.parseStringAsCalendar(dateAsString);
        project.logHours(activityName, date, hours, signedInEmployee.getUsername());
    }

    public float getUserLoggedHoursInActivityOnDate(int projectID, String activityName, String username, String dateAsString) throws ParseException {
        Project project = systemStorage.getProject(projectID);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        date.setTime(sdf.parse(dateAsString));
        return project.getUserLoggedHoursInActivityOnDate(activityName, username, date);
    }

    public float[] generateReport(int projectID) throws IllegalAccessException {
        Project project = systemStorage.getProject(projectID);
        return project.generateReport(signedInEmployee);
    }

    public int getProjectAmountFromYear(int year) {
        return systemStorage.getProjectAmountFromYear(year);
    }

    public EmployeeInfo getEmployeeInfo(String username) {
        return new EmployeeInfo(systemStorage.getEmployee(username));
    }
    
    public List<EmployeeInfo> getAllEmployeeInfo() {
        List<Employee> employees = systemStorage.getAllEmployees();
        List<EmployeeInfo> employeeInfos = new ArrayList<>();
        for (Employee employee : employees) {
            employeeInfos.add(new EmployeeInfo(employee));
        }
        return employeeInfos;
    }

    // Utility Methods

    public static boolean isPositiveInt(String input) {
        try {
            Integer temp = Integer.parseInt(input);
            if (temp > 0){
                return true;
            } else {
                throw new IllegalArgumentException("The integer can't be negative.");
            }
            
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not a valid integer.");
        }
    }

    public static boolean isWeek(String week, Integer year) {
        Integer temp = null;

        try {
            isPositiveInt(week);
            temp = Integer.parseInt(week);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        int weeksInYear = CalendarHelper.getWeeksInYear(year);

        if (temp != null && (temp > 0 && temp <= weeksInYear)) {
            return true;
        } else{
            throw new IllegalArgumentException("not a valid weeknumber.");
        }
    }

     


    public ProjectInfo createDTOProject(int projectID) {
        ProjectInfo project = new ProjectInfo(systemStorage.getProject(projectID));
        return project;
    }



    public List<ProjectInfo> getDTOProjectList(int year) {

        year %= 100;
        year *= 1000;

        List<Project> projectList = systemStorage.getAllProjects();
        List<ProjectInfo> dtoProjects = new ArrayList<>();
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getID() > year && projectList.get(i).getID() < year + 1000) {
                dtoProjects.add(createDTOProject(projectList.get(i).getID()));
            }
        }
        return dtoProjects;
    }

    public void deleteProject(int projectID) {
        systemStorage.deleteProject(projectID);
    }

    public List<ProjectInfo> getallProjectInfos(){
        List<ProjectInfo> projectInfos = new ArrayList<>();
        List<Project> allProjects = systemStorage.getAllProjects();
        for (int i = 0; i<allProjects.size(); i++){
            ProjectInfo projectInfo = new ProjectInfo(allProjects.get(i));
            projectInfos.add(projectInfo);
        }
        return projectInfos;
    }

    //For tests

    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

}
