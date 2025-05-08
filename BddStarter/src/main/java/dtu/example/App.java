package dtu.example;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class App {

    private int year;

    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    private CalendarHelper ch = new CalendarHelper();
    private Employee signedInEmployee;
    private Employee selectedEmployee;
    private Boolean registrationConfirmation = null;

    private Activity selectedSpecialActivity;



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
        // Should probably be this but our code is reliant on the return of null.
        //throw new NoSuchElementException("No employee found with username: " + string);
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

    public List<String> viewAvailableEmployees(int startYear, int startWeek, int endYear, int endWeek) {
        List<String> availableEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.isAvailable(startYear, startWeek, endYear, endWeek)) {
                availableEmployees.add(employee.getUsername());
            }
        }
        return availableEmployees;
    }

    public List<String> viewAvailableEmployees(int year, int week) {
        return viewAvailableEmployees(year, week, year, week);
    }

    public void setRegistrationConfirmation(boolean registrationConfirmation) {
        this.registrationConfirmation = registrationConfirmation;
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

    public Employee getSignedInEmployee() {
        return signedInEmployee;
    }


    public void setSignedInEmployee(String signedInEmployee) {
        if (stringToEmployee(signedInEmployee) == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        this.signedInEmployee = stringToEmployee(signedInEmployee);
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
    public void printAllActivities() {

    }

    public void setSelectedEmployee(String username) {
        selectedEmployee = stringToEmployee(username);
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void addActivity(int projectID, String activityName) throws IllegalAccessException {
        intToProject(projectID).createActivity(activityName, signedInEmployee);
    }

    public void addSpecialActivity(String activityName, String username, int startYear, int startWeek, int endYear, int endWeek){
        Employee employee = stringToEmployee(username);
        if (employee == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        employee.assignSpecialActivity(activityName, startYear, startWeek, endYear, endWeek);
    }

    public void addSpecialActivity(String activityName, int startYear, int startWeek, int endYear, int endWeek){
        signedInEmployee.assignSpecialActivity(activityName, startYear, startWeek, endYear, endWeek);
    }

    public Project createProject(String name) {

        int id = generateProjectID();
        Project project = new Project(name, id);
        projects.add(project);
        return project;
    }

    private int generateProjectID() {
        year = Year.now().getValue();
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
        return intToProject(id).containsActivity(activity);
    }

    public boolean employeeIsAssignedActivity(String employee, String activity) {
        return stringToEmployee(employee).isAssignedActivity(activity);
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
        if (employee == null) {
            throw new IllegalArgumentException("Employee does not exist");
        }
        Project project = intToProject(id);
        if (project == null) {
            throw new IllegalArgumentException("Project does not exist");
        }
        project.assignLeader(employee, signedInEmployee);
    }

    public boolean projectHasLeader(int id) {
        return intToProject(id).hasProjectLeader();
    }
    
    public String getProjectLeaderName(int id) {
        return intToProject(id).getProjectLeader().getUsername();
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

    public void setActivityExpectedHours(int ProjectID, String activityName, float hours) throws IllegalAccessException {
        intToProject(ProjectID).setActivityExpectedHours(activityName, hours, signedInEmployee);
    }

    public float getActivityExpectedHours(int ProjectID, String activityName) {
        return intToProject(ProjectID).getActivityExpectedHours(activityName);
    }

    public void assignEmployeeToActivity(String username, int projectID, String activtyName) throws Exception {
        Employee employee = stringToEmployee(username);
        Project project = intToProject(projectID);
        if (!projectExists(project.getName(), projectID)) {
            throw new IllegalArgumentException("Project does not exist");
        }
        project.assignEmployeeToActivity(employee, activtyName);
    }

    public void removeEmployeeFromActivity(String username, String activtyName) {
        Employee employee = stringToEmployee(username);
        if (employee == null) {
            throw new IllegalArgumentException("Employee does not exist");
        }
        employee.removeActivity(activtyName);
    }

    public void setActivitiyStartAndEndWeek(int projectID, String activityName, int startYear, int startWeek, int endYear, int endWeek) {
        Project project = intToProject(projectID);
        if (!projectExists(project.getName(), projectID)) {
            throw new IllegalArgumentException("Project does not exist");
        }
        project.setActivitiyStartAndEndWeek(activityName, startYear, startWeek, endYear, endWeek);
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
        
        int weeksInYear = Week.getWeeksinYear(year);

        if (temp != null && (temp > 0 && temp <= weeksInYear)) {
            return true;
        } else{
            throw new IllegalArgumentException("not a valid weeknumber.");
        }
    }

    public void setSelectedSpecialActivity(Activity selectedSpecialActivity) {
        this.selectedSpecialActivity = selectedSpecialActivity;
    }

    public Activity getSelectedSpecialActivity() {
        return selectedSpecialActivity;
    }

    public void logHours(int projectID, String activityName, String dateAsString, float hours) {
        Project project = intToProject(projectID);
        if (project == null) {
            throw new IllegalArgumentException("Project does not exist");
        }
        // MOVE TO CALENDARHELPER PERHAPS?
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(sdf.parse(dateAsString));
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        project.logHours(activityName, date, hours, signedInEmployee.getUsername());
    }

    public void setWorkDataForActivity(float hours, Calendar date, String activityName, String username ){   
        Employee employee = stringToEmployee(username);
        List<Activity> activities=employee.getActivities();
        for (int i=0; i<activities.size(); i++){
            if (activities.get(i).getName() == activityName){
                //WorkData workData = activities.get(i).makeWorkData(date, employee, hours);
                //activities.get(i).getEmployeeWorkData(employee).add(workData);
            }
        }


    }
    /*
    public float getEmployeeTotalHoursInActivity(String username, String activityName){
        Employee employee = stringToEmployee(username);
        Activity activity = employee.getActivity(activityName);
        
        return activity.getEmployeeTotalHoursOnActivity(employee, activityName);
    }
    */
    

}
