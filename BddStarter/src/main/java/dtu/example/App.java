package dtu.example;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en_old.Ac;
import io.cucumber.java.lu.a;

public class App {

    private int year;

    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<SpecialActivity> specialActivites = new ArrayList<>();

    private CalendarHelper ch = new CalendarHelper();
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

    public void addActivity(int projectID, String activityName) throws IllegalAccessException {
        intToProject(projectID).createActivity(activityName, signedInEmployee);
    }

    public Project createProject(String name) {
        year = Year.now().getValue();
        year %= 100;
        year *= 1000;

        System.out.println(year);
        
        int projectAmount = 0;
        

        for (Project p : projects) {
            if (p.getID() >= year && p.getID() < year+1000) {
                projectAmount++;
            }
        }

        if (projectAmount >= 999) {
            throw new IllegalArgumentException("Maximum projects for this year has been reached");
        }
        Project project = new Project(name, year+projectAmount+1);
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
        return intToProject(id).containsActivity(activity);
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

        if (!project.hasProjectLeader()) {
            project.setProjectLeader(employee);
        } else if (signedInEmployee.equals(project.getProjectLeader())) {
            project.setProjectLeader(employee);
        } else {
            throw new IllegalArgumentException("Only project leader can change project leader.");
        }
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


    public boolean specialActivityNameTaken(String activityName) {
        for(SpecialActivity sa : specialActivites) {
            if (sa.getName().equals(activityName)){
                throw new IllegalArgumentException("Special Activity with that name already exists.");
            }
            
        }
        return true;
    }

    public void setActivityExpectedHours(int ProjectID, String activityName, float hours) throws IllegalAccessException {
        intToProject(ProjectID).setActivityExpectedHours(activityName, hours, signedInEmployee);
    }

    public float getActivityExpectedHours(int ProjectID, String activityName) {
        return intToProject(ProjectID).getActivityExpectedHours(activityName);
    }

    public Activity fetchActivity(int projectID, String activityName) {
        for (Project p : projects) {
            if (p.getID() == projectID) {
                return p.stringToActivity(activityName);
            }
        }
        throw new IllegalAccessError("Either activity or Project does not exist.");
    }

    public void assignUserActivity(String username, int projectID, String activtyName) throws Exception {
        Employee employee = stringToEmployee(username);
        Project project = intToProject(projectID);
        Activity activity = project.stringToActivity(activtyName);

        if (!projectExists(project.getName(), projectID)) {
            throw new IllegalAccessError("Project does not exist");
        }

        if (!projectContainsActivity(projectID, activtyName)) {
            throw new IllegalAccessError("Activity is not part of the given project");
        }
        
        
        boolean endsInStartYear = endsInStartYear(activity);


        ArrayList<Activity> possibleOverlapping = new ArrayList<>();
        int year = activity.getYear();

        ArrayList<Integer> activtiesInWeeks = new ArrayList<>(); 
            for (int i = 0; i < activity.getActiveWeeks().size(); i++) {
                activtiesInWeeks.add(0);
            }

        // stays in 1 year
        if (endsInStartYear) {
            for (Activity a : employee.getActivities()) {
                if (a.getYear() == year) {
                    possibleOverlapping.add(a);
                }
            }

            int index = 0;
            for (Integer week : activity.getActiveWeeks()) {
                for (Activity a : possibleOverlapping) {
                    for (Integer week2 : a.getActiveWeeks()) {
                        if (week == week2) {
                            activtiesInWeeks.set(index, activtiesInWeeks.get(index)+1);
                        }
                    }
                }
                index++;
            }
            
            for (Integer i : activtiesInWeeks) {
                if ((employee.isPeak() && i > 20) || (!employee.isPeak() && i > 10)) {
                    int overflowingWeek = activity.getActiveWeeks().get(index);
                    throw new Exception("User has too many activties assigned in week "+overflowingWeek+".");
                }
            }
            employee.assignActivity(activity);

        // goes into next year
        } else {
            
            ArrayList<Activity> possibleOverlappingStartYear = new ArrayList<>();
            for (Activity a : employee.getActivities()) {
                if (a.getYear() == year && a.getEndWeek()>=activity.getStartWeek()) {
                    possibleOverlappingStartYear.add(a);
                }
            }
            ArrayList<Activity> possibleOverlappingNextYear = new ArrayList<>();
            for (Activity a : employee.getActivities()) {
                if (a.getYear() == year+1 && a.getStartWeek()<=activity.getEndWeek()) {
                    possibleOverlappingStartYear.add(a);
                }
            }

            for (Activity a : possibleOverlappingStartYear) {
                if (!endsInStartYear(a)) {
                    possibleOverlappingNextYear.add(a);
                }
            }
            
            int prevWeek = -1;
            int index = 0;
            for (Integer week : activity.getActiveWeeks()) {
                boolean inStartYear = true;
                if (inStartYear) {
                    for (Activity a : possibleOverlappingStartYear) {
                        for (Integer week2 : a.getActiveWeeks()) {
                            if (week == week2) {
                                activtiesInWeeks.set(index, activtiesInWeeks.get(index)+1);
                            }
                        }
                    }
                } else {
                    for (Activity a : possibleOverlappingNextYear) {
                        for (Integer week2 : a.getActiveWeeks()) {
                            if (week == week2) {
                                activtiesInWeeks.set(index, activtiesInWeeks.get(index)+1);
                            }
                        }
                    }
                }
                if (prevWeek > week) {
                    inStartYear = false;
                }
                prevWeek = week;
            }
            
            for (Integer i : activtiesInWeeks) {
                if ((employee.isPeak() && i > 20) || (!employee.isPeak() && i > 10)) {
                    int overflowingWeek = activity.getActiveWeeks().get(index);
                    throw new Exception("User has too many activties assigned in week "+overflowingWeek+".");
                }
            }

            employee.assignActivity(activity);
        }
        
    }

    public boolean endsInStartYear(Activity a) {
        int prevWeek = -1;
        boolean endsInStartYear = true;
        for (Integer week : a.getActiveWeeks()) {
            if (week<prevWeek){
                endsInStartYear = false;
            }
            prevWeek = week;
        }
        return endsInStartYear;
    }

    public void removeUserActivity(String username, String activtyName) {
        Employee employee = stringToEmployee(username);
         if (employee.isAssignedActivity(activtyName)) {
            employee.removeActivity(activtyName);
         }      
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

    public static boolean isWeek(String input) {
        Integer temp = null;

        try {
            isPositiveInt(input);
            temp = Integer.parseInt(input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (temp != null && (temp > 0 && temp < 52)) {
            return true;
        } else{
            throw new IllegalArgumentException("not a valid weeknumber.");
        }
    }

}
