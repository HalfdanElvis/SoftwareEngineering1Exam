package dtu.example.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dtu.example.Model.DateServer;
import dtu.example.Model.Employee;
import dtu.example.Model.Project;
import dtu.example.Model.SystemStorage;
import dtu.example.Utility.CalendarHelper;
import dtu.example.dto.EmployeeInfo;
import dtu.example.dto.ProjectInfo;
//Marcus
public class App {

    private DateServer dateServer = new DateServer();
    private SystemStorage systemStorage = new SystemStorage();
    private Employee signedInEmployee;

    //Simon
    public boolean login(String username) {
        legalUsername(username);
        try {
            signedInEmployee = stringToEmployee(username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //Jesper
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
    //Halfdan
    public boolean legalUsername(String username) {

        // Precondition
        assert(username != null);

        boolean result = false;
        if (username.length() > 4) {                              
            throw new IllegalArgumentException("Username cannot be longer than 4 characters.");                                    
        } else if (username.contains(" ")) {                      
            throw new IllegalArgumentException("Username can't contain spaces.");                                               
        } else if (username.contentEquals("")) {                  
            throw new IllegalArgumentException("Username can't be empty.");                                          
        } else {
            result = true;                                          
        }

        // Postcondition
        assert result == username.length() >= 1 && username.length() <= 4 && !username.contains(" ");

        return result;
    }
    //Marcus
    public EmployeeInfo getSignedInEmployeeInfo() {
        return new EmployeeInfo(signedInEmployee);
    }
    //Marcus
    public void setSignedInEmployee(String signedInEmployee) {
        this.signedInEmployee = stringToEmployee(signedInEmployee);
    }
    //Marcus
    public void logout() {
        this.signedInEmployee = null;
    }
    //Jesper
    public void addEmployee(String username) {
        if (legalUsername(username)) {
            systemStorage.addEmployee(username);
        }
    }
    //Jesper
    public Employee stringToEmployee(String string) {
        return systemStorage.getEmployee(string);
    }
    //Jesper
    public void deleteEmployee(String username){
        systemStorage.deleteEmployee(username);
    }
    //Marcus
    public boolean aUserIsLoggedIn() {
        return signedInEmployee != null;
    }
    //Marcus
    public void setEmployeePeak(String username, boolean peak) {
        stringToEmployee(username).setPeak(peak);
    }
    //Halfdan
    public List<String> viewAvailableEmployees(int startYear, int startWeek, int endYear, int endWeek) {
        List<String> availableEmployees = new ArrayList<>();
        for (Employee employee : systemStorage.getAllEmployees()) {
            if (employee.isAvailable(startYear, startWeek, endYear, endWeek)) {
                availableEmployees.add(employee.getUsername());
            }
        }
        return availableEmployees;
    }
    //Halfdan
    public boolean employeeExists(String employee) {
        return systemStorage.employeeExists(employee);
    }
    //Simon
    public void addActivity(int projectID, String activityName) throws IllegalAccessException {
        systemStorage.getProject(projectID).createActivity(activityName, signedInEmployee);
    }
    //Simon
    public void deleteActivity(int projectID, String activityName) throws IllegalAccessException {
        systemStorage.getProject(projectID).removeActivity(activityName, signedInEmployee);
    }
    //Simon
    public void addSpecialActivity(String activityName, String username, int startYear, int startWeek, int endYear, int endWeek){
        Employee employee = systemStorage.getEmployee(username);
        employee.assignSpecialActivity(activityName, startYear, startWeek, endYear, endWeek);
    }
    //Marcus
    public int createProject(String name) {
        return systemStorage.createProject(name, dateServer.getYear());
    }
    //Marcus
    public void assignLeader(String username, Integer id) {
        Employee employee = systemStorage.getEmployee(username);
        Project project = systemStorage.getProject(id);
        project.assignLeader(employee, signedInEmployee);
    }
    //Marcus
    public void removeLeader(Integer id) {
        Project project = systemStorage.getProject(id);
        project.assignLeader(null, signedInEmployee);
    }
    //Marcus
    public void setActivityExpectedHours(int projectID, String activityName, float hours) throws IllegalAccessException {
        systemStorage.getProject(projectID).setActivityExpectedHours(activityName, hours, signedInEmployee);
    }
    //Marcus
    public void assignEmployeeToActivity(String username, int projectID, String activtyName) throws Exception {
        Employee employee = stringToEmployee(username);
        Project project = systemStorage.getProject(projectID);
        project.assignEmployeeToActivity(employee, activtyName);
    }
    //Halfdan
    public void removeEmployeeFromActivity(String username, String activtyName) {
        Employee employee = systemStorage.getEmployee(username);
        employee.removeActivity(activtyName);
    }
    //Halfdan
    public void removeEmployeeFromSpecialActivity(String username, String activtyName) {
        Employee employee = systemStorage.getEmployee(username);
        employee.removeSpecialActivity(activtyName);
    }
    //Jesper
    public void setActivitiyStartAndEndWeek(int projectID, String activityName, int startYear, int startWeek, int endYear, int endWeek) {
        Project project = systemStorage.getProject(projectID);
        project.setActivitiyStartAndEndWeek(activityName, startYear, startWeek, endYear, endWeek);
    }
    //Jesper
    public void logHours(int projectID, String activityName, String dateAsString, float hours) throws ParseException {
        Project project = systemStorage.getProject(projectID);
        Calendar date = CalendarHelper.parseStringAsCalendar(dateAsString);
        project.logHours(activityName, date, hours, signedInEmployee.getUsername());
    }
    //Jesper
    public float getUserLoggedHoursInActivityOnDate(int projectID, String activityName, String username, String dateAsString) throws ParseException {
        Project project = systemStorage.getProject(projectID);
        Calendar date = CalendarHelper.parseStringAsCalendar(dateAsString);
        return project.getUserLoggedHoursInActivityOnDate(activityName, username, date);
    }
    //Jesper
    public float[] generateReport(int projectID) throws IllegalAccessException {
        Project project = systemStorage.getProject(projectID);
        return project.generateReport(signedInEmployee);
    }
    //Marcus
    public EmployeeInfo getEmployeeInfo(String username) {
        return new EmployeeInfo(systemStorage.getEmployee(username));
    }
    //Marcus
    public List<EmployeeInfo> getAllEmployeeInfo() {
        List<Employee> employees = systemStorage.getAllEmployees();
        List<EmployeeInfo> employeeInfos = new ArrayList<>();
        for (Employee employee : employees) {
            employeeInfos.add(new EmployeeInfo(employee));
        }
        return employeeInfos;
    }

    // Utility Methods
    //Marcus
    public static boolean isPositiveInt(String input) {

        // Precondtion
        assert input != null;

        boolean result = false;
        try {
            Integer temp = Integer.parseInt(input);
            if (temp > 0){                               
                result = true;                            
            } else {
                throw new IllegalArgumentException("The integer can't be negative.");                     
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not a valid integer.");
        }

        // Postcondtion
        assert result == Integer.parseInt(input) > 0;

        return result;
    }
    //Simon
    public static boolean isWeek(String input, Integer year) {
        Integer temp = null;

        // precondition
        assert isPositiveInt(input);
        assert year > 0 : "Year has to be positive";

        temp = Integer.parseInt(input);
        int weeksInYear = CalendarHelper.getWeeksInYear(year);
        boolean result = false ;
        
        if ( temp >= 1 && temp <= weeksInYear ) {
            result = true ;
        } else {
            throw new IllegalArgumentException("not a valid weeknumber.");
        }

        // postcondition
        assert temp >= 1 && temp <= weeksInYear;

        return result;
    }
    //Halfdan
    public ProjectInfo getProjectInfo(int projectID) {
        ProjectInfo project = new ProjectInfo(systemStorage.getProject(projectID));
        return project;
    }
    //Jesper
    public List<ProjectInfo> getProjectInfosFromYear(int year) {

        year %= 100;
        year *= 1000;

        List<Project> projectList = systemStorage.getAllProjects();
        List<ProjectInfo> dtoProjects = new ArrayList<>();
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getID() > year && projectList.get(i).getID() < year + 1000) {
                dtoProjects.add(getProjectInfo(projectList.get(i).getID()));
            }
        }
        return dtoProjects;
    }
    //Halfdan
    public void deleteProject(int projectID) {
        systemStorage.deleteProject(projectID);
    }
    //Marcus
    public List<ProjectInfo> getallProjectInfos(){
        List<ProjectInfo> projectInfos = new ArrayList<>();
        List<Project> allProjects = systemStorage.getAllProjects();
        for (int i = 0; i < allProjects.size(); i++){
            ProjectInfo projectInfo = new ProjectInfo(allProjects.get(i));
            projectInfos.add(projectInfo);
        }
        return projectInfos;
    }

    //For tests
    //Marcus
    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

    public void isEmptyString(String string) {
        if (string.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

}
