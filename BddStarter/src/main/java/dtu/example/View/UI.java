package dtu.example.View;

import java.util.List;
import java.util.Scanner;

import dtu.example.Controller.App;
import dtu.example.Model.SpecialActivity;
import dtu.example.dto.ActivityInfo;
import dtu.example.dto.EmployeeInfo;
import dtu.example.dto.ProjectInfo;

public class UI {

    static App app = new App();
    static Scanner console = new Scanner(System.in);
    private static boolean loggedIn = false;
    private static EmployeeInfo loggedInEmployee = null;
    private static EmployeeInfo selectedEmployee = null;
    private static SpecialActivity selectedSpecialActivity = null;
    private static String username = "";
    private static String input = "";
    private static int choice = -1;

    public static void main(String args[]) throws IllegalAccessException {
        app.addEmployee("huba");
        
        // Starting Program
        while (true) {
            // Login method
            if (!loggedIn){
                loginUI();
            }
            
            if (loggedIn) {
                // Main Menu:
                printMainMenu();
                if (!getUserChoice()) {
                    continue; // Resets loop if user doesn't pick a valid option.
                }

                switch (choice) {
                    case 1:
                        // Create a new Project
                        createProjectUI();
                        break;
                    case 2:
                        // Manage a selected Project
                        manageProjectUI();
                        break;
                    case 3:
                        // Create User:
                        createUserUI();
                        break;
                    case 4:
                        // Manage User:
                        manageUserUI();
                        break;
                    case 5:
                        // Log Hours:
                        LogHours();
                        break;
                    case 6:
                        // View Available employees
                        viewAvailableEmployeesUI();
                        break;
                    case 7:
                        // Logout
                        loggedIn = false;
                        app.logout();
                        break;
                    case 8:
                        // Closes Program:
                        System.exit(0);
                        break;
                    default:
                        break;  
                }
            } 
        }
    }
    







    //Login
    public static void loginUI(){
        if (!app.aUserIsLoggedIn()){
                System.out.println();
                System.out.println("Login:");
                username = console.nextLine();
            }

            try {
                if (app.login(username)){
                    loggedInEmployee = app.getSignedInEmployeeInfo();
                    loggedIn = true;
                } else {
                    // New User registration
                    System.out.println();
                    System.out.println("User doesn't exist. Create employee with username: "+username+" Y/N?");
                    while (true) {
                        input = console.nextLine();
                        try {
                            if (app.yesOrNo(input)) {
                                app.addEmployee(username);
                                app.login(username);
                                loggedInEmployee = app.getSignedInEmployeeInfo();
                            }
                            break;
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }  
            } catch (Exception e) {
                //User does not exist
                System.err.println(e.getMessage());
            }
    }


    // MainMenu
    public static void printMainMenu() {
        System.out.println();

        System.out.println("-------------------------");
        System.out.print("Logged in user: "+loggedInEmployee.getName());
        if (loggedInEmployee.isPeak()){
            System.out.print(" (peak) ");
        }
        System.out.println();
        System.out.println("-------------------------");
        
        System.out.println();
        System.out.println();
        System.out.println("Main Menu:");
        System.out.println("-------------------------");
        System.out.println("1. Create Project");
        System.out.println("2. Manage Project");
        System.out.println("3. Create Employee");
        System.out.println("4. Manage Employee");
        System.out.println("5. Log hours");
        System.out.println("6. View Avaiable Employees");
        System.out.println("7. Logout");
        System.out.println("8. Exit Program");
        System.out.println("-------------------------");

        System.out.println();

        System.out.println("Select a number from the list above to proceed.");
    }

    public static boolean getUserChoice() {
        choice = -1;  // initialize with invalid default
        try {
            input = console.nextLine();
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Invalid input. Please enter a number from the menu.");
            return false;
        }
        return true;
    }


    // Create Project
    public static void createProjectUI() {
        boolean exit = true;
        String projectName = "";
        int id = -1;
        do {
            printCreateProjectMenu();
            try {
                projectName = console.nextLine();
                id = app.createProject(projectName);
                newPage();
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            
            System.out.println("\nSuccesfully created project \""+projectName+"\" with ID: "+id);
            System.out.println("-------------------------");
            System.out.println("Want to create another project Y/N?");
            while (true) {
                try {
                    exit = app.yesOrNo(console.nextLine());
                    newPage();
                    break; 
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            
            
        } while(exit);
    }

    public static void printCreateProjectMenu() {
        System.out.println();
        System.out.println("Create Project");
        System.out.println("-------------------------");
        System.out.println("Enter Project name:");
    }


    // Manage Project
    public static void printManageProjectMenu(int id) {
        System.out.println("-------------------------");
        System.out.println("Selected project "+ id);
        System.out.println("-------------------------");

        System.out.println();
        System.out.println("Manage Project Menu:");
        System.out.println("-------------------------");
        System.out.println("1. Make activity");
        System.out.println("2. View activities");
        System.out.println("3. Set expected hours for activity");
        System.out.println("4. Assign Project-leader");
        System.out.println("5. Generate report");
        System.out.println("6. Delete activity");
        System.out.println("7. Delete project");
        System.out.println("8. Back");
        System.out.println("-------------------------");

        System.out.println();

        System.out.println("Select a number from the list above to proceed.");
    }

    public static void manageProjectUI() throws IllegalAccessException {
        boolean exit = true;
        System.out.println("-------------------------");
        System.out.println("Enter year of the project:");
        outerloop:
        while (true) {
            try {
        
                String input = console.nextLine();
                App.isPositiveInt(input);
                int year = Integer.parseInt(input);
                newPage();
                
                List<ProjectInfo> projectList = app.getProjectInfosFromYear(year);
                while (true) {
                    if (projectList.size() == 0) {
                    System.out.println("-------------------------");
                    System.out.println("No projects found");
                    System.out.println("-------------------------");
                    System.out.println();
                    System.out.println("Press \'Enter\' to return");
                    input = console.nextLine();
                    newPage();
                    break outerloop;
                    }

                    System.out.println();
                    System.out.println("-------------------------");
                    System.out.println("List of Projects");
                    System.out.println("-------------------------");
                    System.out.println();

                    for (int i = 0; i < projectList.size(); i++) {
                        System.out.println(projectList.get(i).getName()+", "+projectList.get(i).getID());
                    }

                    System.out.println();
                    System.out.println("-------------------------");
                    System.out.println("Enter project id:");

                    int projectID;
                    ProjectInfo project;

                    while (true) {
                        try {
                            input = console.nextLine();
                            App.isPositiveInt(input);
                            projectID = Integer.parseInt(input);
                            project = app.getProjectInfo(projectID);
                            newPage();
                            break;
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    

                    do {
                        printManageProjectMenu(projectID);

                        int choice = -1;
                        try {
                            input = console.nextLine();
                            choice = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            System.out.println();
                            System.out.println("Invalid input. Please enter a number from the menu.");
                            continue; // skip the rest of the loop and prompt again
                        }
                        newPage();
                        switch (choice) {
                            case 1:

                                project = app.getProjectInfo(projectID);

                                if(!project.getProjectLeaderUsername().equals("")) {
                                    if (!project.getProjectLeaderUsername().equals(loggedInEmployee.getName())) {
                                        System.out.println("Project already has a project leader.");
                                        System.out.println("Only project leader can create activities.");
                                        System.out.println("-------------------------");
                                        System.out.println("Press \'Enter\' to return");
                                        input = console.nextLine();
                                        newPage();
                                        break;
                                    }
                                }
                                boolean makenew = true;
                                do {
                                    System.out.println("-------------------------");
                                    System.out.println("Enter name for activity:");
                                    String activityName = "";
                                    while(true) {
                                        try {
                                            activityName = console.nextLine();
                                            app.addActivity(projectID, activityName);
                                            newPage();
                                            break;
                                        } catch (Exception e) {
                                            System.err.println(e.getMessage());
                                        }
                                    }

                                    
                                    System.out.println("-------------------------");
                                    System.out.println("Activity "+activityName+" is created");
                                    System.out.println("-------------------------");
                                    System.out.println("Do you want to specify a Start and End week Y/N?");
                                while(true) {
                                    try {
                                        input = console.nextLine();
                                        if(app.yesOrNo(input)) {
                                            int startYear = -1;
                                            int endYear = -1;
                                            int startWeek = -1;
                                            int endWeek = -1;

                                            while (true) {
                                                try {
                                                    System.out.println("-------------------------");
                                                    System.out.println("What is the starting year for "+ activityName);
                                                    input = console.nextLine();
                                                    App.isPositiveInt(input);
                                                    startYear = Integer.parseInt(input);
                                                    newPage();

                                                    System.out.println("-------------------------");
                                                    System.out.println("What is the due year for "+ activityName);
                                                    input = console.nextLine();
                                                    App.isPositiveInt(input);
                                                    endYear = Integer.parseInt(input);
                                                    newPage();

                                                    System.out.println("-------------------------");
                                                    System.out.println("Enter Start week for "+activityName);
                                                    input = console.nextLine();
                                                    App.isWeek(input,endYear);
                                                    startWeek = Integer.parseInt(input);
                                                    newPage();

                                                    System.out.println("-------------------------");
                                                    System.out.println("Enter End Week for "+activityName);
                                                    input = console.nextLine();
                                                    App.isWeek(input,endYear);
                                                    endWeek = Integer.parseInt(input);
                                                    app.setActivitiyStartAndEndWeek(projectID, activityName, startYear, startWeek, endYear, endWeek);
                                                    newPage();
                                                    break;

                                                } catch (Exception e) {
                                                    System.err.println(e.getMessage());
                                                }
                                            }
                                        }
                                        System.out.println("-----------------------------------------------------");
                                        System.out.println("\nSuccesfully created activity \""+activityName+"\"");
                                        System.out.println("-----------------------------------------------------");
                                        System.out.println("Want to create another activity Y/N?");
                                        while(true) {
                                            try {
                                                makenew = app.yesOrNo(console.nextLine());
                                                break;
                                            } catch (Exception e) {
                                                System.err.println(e.getMessage());
                                            } 
                                        }
                                        break;   
                                        
                                    } catch (Exception e) {
                                        System.err.println(e.getMessage());
                                    }
                                } 
                                } while(makenew);
                                break;
                            case 2:
                                project = app.getProjectInfo(projectID);

                                if (project.getActivities().size() == 0) {
                                    System.out.println("-------------------------");
                                    System.out.println("No activites in project");
                                    System.out.println("-------------------------");
                                    System.out.println("Press \'Enter\' to return");
                                    input = console.nextLine();
                                    break;
                                }    

                                System.out.println("-------------------------");
                                System.out.println("List of activities");
                                System.out.println("-------------------------");
                                System.out.println();
                                
                                for (int i = 0; i < project.getActivities().size(); i++) {
                                    if (project.getActivities().get(i).getStartWeek() != null) {

                                        System.out.println(project.getActivities().get(i).getName()+", Active from: " 
                                        +project.getActivities().get(i).getStartWeek().getYear()+"-W"
                                        +project.getActivities().get(i).getStartWeek().getWeek()+" to "
                                        +project.getActivities().get(i).getEndWeek().getYear()+"-W"
                                        +project.getActivities().get(i).getEndWeek().getWeek());

                                    } else {
                                        System.out.println(project.getActivities().get(i).getName());
                                    }
                                    
                                }

                                System.out.println();
                                System.out.println("-------------------------");
                                System.out.println("Press \'Enter\' to return");
                                input = console.nextLine();
                                break;
                            case 3:
                                project = app.getProjectInfo(projectID);

                                if (!project.getProjectLeaderUsername().equals(loggedInEmployee.getName())) {
                                        System.out.println("Only project leader can set expected hours.");
                                        System.out.println("-------------------------");
                                        System.out.println("Press \'Enter\' to return");
                                        input = console.nextLine();
                                        newPage();
                                        break;
                                    }

                                if (project.getActivities().size() == 0) {
                                    System.out.println("-------------------------");
                                    System.out.println("No activites in project");
                                    System.out.println("-------------------------");
                                    System.out.println("Press \'Enter\' to return");
                                    input = console.nextLine();
                                    break;
                                } 

                                System.out.println("-------------------------");
                                System.out.println("List of activities");
                                System.out.println("-------------------------");
                                System.out.println();
                                
                                for (int i = 0; i < project.getActivities().size(); i++) {
                                    if (project.getActivities().get(i).getStartWeek() != null) {

                                        System.out.println(project.getActivities().get(i).getName()+", Active from: " 
                                        +project.getActivities().get(i).getStartWeek().getYear()+"-W"
                                        +project.getActivities().get(i).getStartWeek().getWeek()+" to "
                                        +project.getActivities().get(i).getEndWeek().getYear()+"-W"
                                        +project.getActivities().get(i).getEndWeek().getWeek());

                                    } else {
                                        System.out.println(project.getActivities().get(i).getName());
                                    }
                                    
                                }

                                System.out.println();
                                System.out.println("-------------------------");
                                String activityName;
                                int hours;
                                while (true) {
                                    try {
                                        System.out.println("Which activity do you want to set hours for?");
                                        activityName = console.nextLine();
                                        System.out.println();
                                        System.out.println("How many hours should the activity take to finish?");
                                        input = console.nextLine();
                                        App.isPositiveInt(input);
                                        hours = Integer.parseInt(input);

                                        app.setActivityExpectedHours(projectID, activityName, hours );
                                        newPage();
                                        break;
                                    } catch (Exception e) {
                                        System.err.println(e.getMessage());
                                    }
                                }
                                
                                System.out.println("Succesfully set hours for "+activityName);
                                System.out.println("-------------------------");
                                System.out.println("Press \'Enter\' to return");
                                input = console.nextLine();
                                newPage();
                                break;
                            case 4:
                                project = app.getProjectInfo(projectID);
                                if(!project.getProjectLeaderUsername().equals("")) {
                                    if (!project.getProjectLeaderUsername().equals(loggedInEmployee.getName())) {
                                        System.out.println("Project already has a project leader.");
                                        System.out.println("Only project leader can assign a new leader.");
                                        System.out.println("-------------------------");
                                        System.out.println("Press \'Enter\' to return");
                                        input = console.nextLine();
                                        newPage();
                                        break;
                                    }
                                }
                                System.out.println("Employees:");
                                System.out.println("-------------------------");
                                List<EmployeeInfo> allEmployees = app.getAllEmployeeInfo();
                                for (EmployeeInfo employeeInfo : allEmployees) {
                                    System.out.println(employeeInfo.getName());
                                }
                                System.out.println("--------------------------------------------");
                                System.out.println("Enter username of the user to be assigned leader:");
                                
                                String username;

                                while(true) {
                                    try {
                                        username = console.nextLine();
                                        app.assignLeader(username, projectID);
                                        newPage();
                                        break;
                                    } catch (Exception e) {
                                        System.err.println(e.getMessage());
                                    }
                                }
                                
                                System.out.println("----------------------------------------------------------------");
                                System.out.println("Employee "+username+" was succesfully assigned as projectleader.");
                                System.out.println("----------------------------------------------------------------");
                                System.out.println("Press \'Enter\' to return");
                                input = console.nextLine();
                                newPage();
                                break;
                            case 5:
                                project = app.getProjectInfo(projectID);
                                try {
                                    float[] tal = app.generateReport(projectID);

                                    System.out.println("-------------------------");
                                    System.out.println("Project Report");
                                    System.out.println("-------------------------");
                                    System.out.println();

                                    System.out.println("Hours completed: "+tal[0]+"/"+tal[1]);

                                    System.out.println();
                                    System.out.println("-------------------------");
                                    System.out.println("Press \'Enter\' to return");
                                    input = console.nextLine();
                                    newPage();
                                    break;
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                    System.out.println("-------------------------");
                                    System.out.println("Press \'Enter\' to return");
                                    input = console.nextLine();
                                    newPage();
                                }
                                
                                break;
                            case 6:
                                project = app.getProjectInfo(projectID);
                                
                                if(!project.getProjectLeaderUsername().equals("")) {
                                    if (!project.getProjectLeaderUsername().equals(loggedInEmployee.getName())) {
                                        System.out.println("Project already has a project leader.");
                                        System.out.println("Only project delete activites.");
                                        System.out.println("-------------------------");
                                        System.out.println("Press \'Enter\' to return");
                                        input = console.nextLine();
                                        newPage();
                                        break;
                                    }
                                }

                                if (project.getActivities().size() == 0) {
                                    System.out.println("-------------------------");
                                    System.out.println("No activites in project");
                                    System.out.println("-------------------------");
                                    System.out.println("Press \'Enter\' to return");
                                    input = console.nextLine();
                                    newPage();
                                    break;
                                }

                                
                                
                                String activityString;
                                while (true) {

                                    System.out.println("-------------------------");
                                    System.out.println("List of activities");
                                    System.out.println("-------------------------");
                                    System.out.println();
                                    for (int i = 0; i < project.getActivities().size(); i++) {
                                    if (project.getActivities().get(i).getStartWeek() != null) {
                                        System.out.print(project.getActivities().get(i).getName()+", StartWeek: ");
                                        System.out.print(project.getActivities().get(i).getStartWeek().getWeek()+", Endweek: ");
                                        System.out.println(project.getActivities().get(i).getEndWeek().getWeek()); 
                                    } else {
                                        System.out.println(project.getActivities().get(i).getName());
                                    }
                                    
                                    }

                                    System.out.println();
                                    System.out.println("-------------------------");
                                    System.out.println("Which activity do you wish to delete?");
                                    
                                    try {
                                        activityString = console.nextLine();
                                        app.deleteActivity(projectID, activityString);
                                        newPage();
                                        break;
                                    } catch (Exception e) {
                                        System.out.println();
                                        System.err.println(e.getMessage());
                                        System.out.println();
                                    }
                                }
                                
                                System.out.println("Succesfully removed "+activityString);
                                System.out.println("-------------------------");
                                System.out.println("Press \'Enter\' to return");
                                input = console.nextLine();
                                newPage();
                                break;
                            case 7:
                                boolean leave = false;
                            
                                do {
                                    try {
                                        System.out.println("Are you sure you want to delete this project Y/N?"); 
                                        leave = app.yesOrNo(console.nextLine());
                                        if (leave) {
                                            app.deleteProject(projectID);
                                            exit = false;
                                            break outerloop;
                                        } 
                                    } catch (Exception e) {
                                        System.err.println(e.getMessage());
                                    }
                                } while (leave);
                                    
                                
                                break;
                            case 8:
                                exit = false;
                                break outerloop;
                    
                            
                            default:
                                break;
                            
                        }
                    } while (exit);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // Create User
    public static void createUserUI() {
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("What should the username be?");
        
        while (true) { 
            String username = console.nextLine();
            try {
                newPage();
                app.legalUsername(username);
                app.addEmployee(username);
                System.out.println("-------------------------");
                System.out.println("succesfully created user with name: " + username);
                System.out.println("-------------------------");
                waitTillEnter();
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // Manage User
    public static void manageUserUI() {
        // Select User
        selectUser();

        boolean back = false;
        do {
            selectedEmployee = app.getEmployeeInfo(username);
            // Manage User

            printManageUserMenu();

            choice = -1;
            if (!getUserChoice()) {
                continue; // Resets loop if user doesn't pick a valid option.
            }
            
            switch (choice) {
                case 1:
                    List<ProjectInfo> allProjectInfos = app.getallProjectInfos();
                    boolean activityExist = false;
                    for (ProjectInfo projectInfo : allProjectInfos) {
                        if (projectInfo.getActivities().size() > 0) {
                            activityExist = true;
                            System.out.println("-------------------------");
                            System.out.println("List of all activities for Project "+projectInfo.getName()+", with ID "+projectInfo.getID());
                            System.out.println("-------------------------");
                            printActivities(projectInfo.getActivities());
                            System.out.println();
                        }
                    }

                    if (!activityExist) {
                        System.out.println("Error, No activities exist");
                        System.out.println();
                        waitTillEnter();
                        System.out.println();
                        break;
                    }
                    assignSelectedEmployeeToActivtiy();
                    waitTillEnter();
                    break;

                case 2:
                    CreateSpecialActivityUI();
                    break;

                case 3:
                    newPage();
                    if (selectedUserHasSpecialActivties()) {
                        manageSpecialActivitiesUI();
                    } else {
                        System.out.println(selectedEmployee.getName()+ " has no special activities.");
                        System.out.println("------------------");
                        waitTillEnter();
                    }
                    break;

                case 4:
                    setUserPeakUI();
                    break;
                
                case 5:
                    back = deleteUserUI();
                    break;

                case 6:
                    back = true;
                    break;

                default:
                    break;
            }

        } while (!back);
    }

    public static String selectUser() {
        System.out.println();
        System.out.println("Employees:");
        System.out.println("-------------------------");
        List<EmployeeInfo> allEmployees = app.getAllEmployeeInfo();
        for (EmployeeInfo employeeInfo : allEmployees) {
            System.out.println(employeeInfo.getName());
        }
        System.out.println("-------------------------");
        System.out.println();
        System.out.println("Select an employee from the list above by inserting their username:");
        
        while (true) { 
            username = console.nextLine();
            try {
                if(app.legalUsername(username)){
                    selectedEmployee = app.getEmployeeInfo(username);
                    break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return username;
    }

    public static void printManageUserMenu() {
        System.out.println();
        System.out.print("Manage Employee: " + selectedEmployee.getName());
        if (selectedEmployee.isPeak()){
            System.out.print(" (peak) ");
        }
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("1. Assign to Activity");
        System.out.println("2. Assign to Special Activity");
        System.out.println("3. Manage Special Activities") ;
        System.out.println("4. Set/Unset Peak Performance");
        System.out.println("5. Delete Employee");
        System.out.println("6. Back");
        System.out.println("-------------------------");
        System.out.println();
        System.out.println("Select a number from the list above to proceed.");
    }

    public static void assignSelectedEmployeeToActivtiy() {
        int projectID = -1;
        String activityName = "";
        
        while(true) {
            try {
            System.out.println("Write the projectID of the activity");
            projectID = Integer.parseInt(console.nextLine());
            break;
            } catch (Exception e) {
            System.out.println("--------------------");
            System.err.println("Invalid projectID");
            System.out.println("--------------------");
            }

        }
        
        while (true) {
            try {
                System.out.println("Write the name of the activity you would like to assign the employee");
                activityName = console.nextLine();
                System.out.println();
                app.assignEmployeeToActivity(selectedEmployee.getName(), projectID, activityName);
                System.out.println("Employee has been succesfully assigned to activity "+activityName );
                System.out.println("--------------------");
                break;
            } catch(Exception e) {
                System.out.println("--------------------");
                System.out.println("An error occurred while processing input: " + e.getMessage());
                System.out.println("--------------------");
                if (e.getMessage().equals("User has too many activities")) {
                    break;                    
                }
            }
        }
    }

    public static void CreateSpecialActivityUI() {
        boolean saCreator = true;
                                
        do {
            boolean yesOrNo = true;
            System.out.println();
            System.out.println("Create Special Activity");
            System.out.println("-------------------------");
            

            String activityName = null;
            Integer activityYearInt = null;
            Integer activityStartWeekInt = null;
            Integer activityEndWeekInt = null;

            // Gets Name:
            while (activityName == null) {
                System.out.println("Enter Activity name:");    
                activityName = console.nextLine();
            }
            

            // Gets Year:
            System.out.println();
            System.out.println("What year is the activity in?");
            while (activityYearInt == null || activityYearInt < 0){
                try {
                    String activityYears = console.nextLine();
                    if (app.isPositiveInt(activityYears)) {
                        activityYearInt = Integer.parseInt(activityYears);
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

            System.out.println();
            System.out.println("What is the start week of the activity?");
            

            // Gets StartWeek:
            while (activityStartWeekInt == null || activityStartWeekInt < 0){
                try {
                    String activityStartWeek = console.nextLine();
                    if (App.isWeek(activityStartWeek, activityYearInt)) {
                        activityStartWeekInt = Integer.parseInt(activityStartWeek);
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

            System.out.println();
            System.out.println("What is the end week of the activity?");

            // Gets EndWeek:
            while (activityEndWeekInt == null || activityEndWeekInt < 0){
                try {
                    String activityEndWeek = console.nextLine();
                    if (App.isWeek(activityEndWeek, activityYearInt)) {
                        activityEndWeekInt = Integer.parseInt(activityEndWeek);
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            
            // checks if you go into next year.
            boolean goesIntoNextyear = false;
            if (activityStartWeekInt > activityEndWeekInt) {
                System.out.println();
                System.out.println("Your end week for the activity is earlier in the year than your start week.");
                System.out.println("Do you want to continue the activity into "+(activityYearInt+1)+" Y/N?");
                
                while(true) {
                    try {
                        yesOrNo = app.yesOrNo(console.nextLine());
                        goesIntoNextyear = true;
                        break;
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
            } 
                
            }

            // Creates activity unless, user decided not to, by not continueing it into next year.
            if (yesOrNo) {
                //SpecialActivity a = new SpecialActivity(activityName);
                
                if (goesIntoNextyear) {
                    app.addSpecialActivity(activityName, selectedEmployee.getName(), activityYearInt, activityStartWeekInt, activityYearInt+1, activityEndWeekInt);
                } else {    
                    app.addSpecialActivity(activityName, selectedEmployee.getName(), activityYearInt, activityStartWeekInt, activityYearInt, activityEndWeekInt);
                }

                System.out.println();
                System.out.println("Succesfully assigned " + selectedEmployee.getName() + " to special activity \""+activityName+"\".");
                System.out.println("-------------------------");
            } else {
                System.out.println();
                System.out.println("Activity was not created, since timeline wasn't possible.");
                System.out.println("-------------------------");
            }

            System.out.println("Want to create another special activity Y/N?");
            while(true) {
                try {
                    saCreator = app.yesOrNo(console.nextLine());
                    break;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } 

        } while(saCreator);
    }

    public static boolean selectedUserHasSpecialActivties() {
        return selectedEmployee.getSpecialActivities().size() != 0;
    }

    public static void manageSpecialActivitiesUI() {

        selectSpecialActivity();

        while (true) {
            // Manage Special Activity
            System.out.println();
            System.out.println("Manage Special Activity: " + selectedSpecialActivity.getName());
            System.out.println("-------------------------");
            System.out.println("1. Views Active Weeks");
            System.out.println("2. Change Active Weeks");
            System.out.println("3. Delete Activity");
            System.out.println("4. Back");
            System.out.println("-------------------------");
            System.out.println();
            System.out.println("Select a number from the list above to proceed.");

            if (!getUserChoice()) {
                continue; // Resets loop if user doesn't pick a valid option.
            }

            boolean back = false;
            switch (choice) {
                // View Active Weeks:
                case 1:
                    newPage();
                    System.out.println();
                    System.out.println("Special Activity \"" + selectedSpecialActivity.getName() + "\"");
                    System.out.println("Starts in year: " + selectedSpecialActivity.getStartWeek().getYear() +
                     ", week: " + selectedSpecialActivity.getStartWeek().getWeek()); 
                    System.out.println("Starts in year: " + selectedSpecialActivity.getEndWeek().getYear() +
                     ", week: " + selectedSpecialActivity.getEndWeek().getWeek()); 
                    System.out.println("------------------");
                    waitTillEnter();   
                    break;

                case 2:

                    Integer activityYearInt = null;
                    Integer activityStartWeekInt = null;
                    Integer activityEndWeekInt = null;
                    boolean yesOrNo = true;

                    // Gets Year:
                    newPage();
                    System.out.println("What year is the activity in?");
                    while (activityYearInt == null || activityYearInt < 0){
                        try {
                            String activityYears = console.nextLine();
                            if (App.isPositiveInt(activityYears)) {
                                activityYearInt = Integer.parseInt(activityYears);
                            }
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }

                    System.out.println();
                    System.out.println("What is the start week of the activity?");
                    

                    // Gets StartWeek:
                    while (activityStartWeekInt == null || activityStartWeekInt < 0){
                        try {
                            String activityStartWeek = console.nextLine();
                            if (App.isWeek(activityStartWeek, activityYearInt)) {
                                activityStartWeekInt = Integer.parseInt(activityStartWeek);
                            }
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }

                    System.out.println();
                    System.out.println("What is the end week of the activity?");

                    // Gets EndWeek:
                    while (activityEndWeekInt == null || activityEndWeekInt < 0){
                        try {
                            String activityEndWeek = console.nextLine();
                            if (App.isWeek(activityEndWeek, activityYearInt)) {
                                activityEndWeekInt = Integer.parseInt(activityEndWeek);
                            }
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    
                    // checks if you go into next year.
                    boolean goesIntoNextyear = false;
                    if (activityStartWeekInt > activityEndWeekInt) {
                        System.out.println();
                        System.out.println("Your end week for the activity is earlier in the year than your start week.");
                        System.out.println("Do you want to continue the activity into "+(activityYearInt+1)+" Y/N?");
                        
                        while (true) {
                            try {
                                yesOrNo = app.yesOrNo(console.nextLine());
                                goesIntoNextyear = true;
                                break;
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                        
                    }

                    // Creates activity unless, user decided not to, by not continueing it into next year.
                    if (yesOrNo) {
                        if (goesIntoNextyear) {
                            selectedSpecialActivity.setStartAndEndWeek(activityYearInt, activityStartWeekInt, activityYearInt+1, activityEndWeekInt);
                        } else {    
                            selectedSpecialActivity.setStartAndEndWeek(activityYearInt, activityStartWeekInt, activityYearInt+1, activityEndWeekInt);
                        }
                        System.out.println();
                        System.out.println("Succesfully changed start and end weeks for activtity: " + selectedSpecialActivity.getName());
                        System.out.println("-------------------------");
                        waitTillEnter();
                    } else {
                        System.out.println();
                        System.out.println("Activity was not changed, since timeline wasn't possible.");
                        System.out.println("-------------------------");
                        waitTillEnter();
                    }

                    break;

                case 3:
                    newPage();
                    try {
                        selectedEmployee.getSpecialActivities().remove(selectedSpecialActivity);
                        back = true;
                        System.out.println("Successfully removed activitiy.");
                        System.out.println("--------------------");
                        waitTillEnter();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("--------------------");
                        waitTillEnter();
                    }
                    break;

                case 4:
                    back = true;
                    break;
                
                default:
                    System.out.println("Invalid input. Please enter a number from the menu.");
                    break;
            }

            if (back) {
                break;
            }

        }
    }

    public static void selectSpecialActivity() {
        choice = -1;
        while (!(choice <= selectedEmployee.getSpecialActivities().size() && choice >= 1)) {
            // Select Activity
            System.out.println();
            System.out.println("Special Activites:");
            System.out.println("-------------------------");
            printSelectedEmployeeSpecialActivities();
            System.out.println("-------------------------");
            System.out.println();
            System.out.println("select the special activity you want to manage by entering the number in front of it:");
            
            try {
                input = console.nextLine();
                choice = Integer.parseInt(input);
                assert(choice <= selectedEmployee.getSpecialActivities().size() && choice >= 1);
            } catch (Exception e) {
                System.out.println();
                System.out.println("Invalid input. Please enter a number from the menu.");
                continue; // skip the rest of the loop and prompt again
            }
            selectedSpecialActivity = (getSpecialActivityNumber(choice));
        }
    }

    public static void printSelectedEmployeeSpecialActivities() {

        List<SpecialActivity> specialActivities = selectedEmployee.getSpecialActivities();
        
        for (int i = 1; i-1 < selectedEmployee.getSpecialActivities().size(); i++){
            System.out.println(i+": "+specialActivities.get(i-1).getName()+" - stating in year: " + specialActivities.get(i-1).getStartWeek().getYear() 
            + " week: " + specialActivities.get(i-1).getStartWeek().getWeek() + " to year: " + specialActivities.get(i-1).getEndWeek().getYear() 
            + " week: " + specialActivities.get(i-1).getEndWeek().getWeek());
        }
    }

    public static SpecialActivity getSpecialActivityNumber(int index) {
        List<SpecialActivity> specialActivities = selectedEmployee.getSpecialActivities();

        //SortByDate(specialActivities):

        return specialActivities.get(index-1);

    }

    public static void deleteSpecialActivityUI() {
        System.out.println();
        System.out.println("Are you sure you want to delete special activity: " + selectedSpecialActivity.getName() + "? Y/N");
        while (true) {
            input = console.nextLine();
            try {
                if (app.yesOrNo(input)) {
                    app.removeEmployeeFromSpecialActivity(selectedEmployee.getName(), selectedSpecialActivity.getName());    
                }
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static boolean  deleteUserUI() {
        System.out.println();
        System.out.println("Are you sure you want to delete employee: " + selectedEmployee.getName() + "? Y/N");
        while (true) {
            input = console.nextLine();
            try {
                if (app.yesOrNo(input)) {
                    
                    for (ProjectInfo p : app.getallProjectInfos()) {
                        if (p.getProjectLeaderUsername().equals(selectedEmployee.getName())){
                            app.removeLeader(p.getID());
                        }
                    }

                    if (selectedEmployee.getName().equals(loggedInEmployee.getName())) {
                        loggedIn = false;
                        app.logout();
                    }
                    app.deleteEmployee(selectedEmployee.getName());
                    System.out.print("Employee deleted");

                    return true;
                } else {
                    System.out.println("Employee wasn't deleted.");
                    return false;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void setUserPeakUI() {
        System.out.println();
        System.out.println("do you want to set employee: " + selectedEmployee.getName() + " as a peak performing Employee? Y/N");
        while (true) {
            input = console.nextLine();
            try {
                if (app.yesOrNo(input)) {
                    app.setEmployeePeak(selectedEmployee.getName(), true);
                    System.out.println("Employee, " + selectedEmployee.getName() + " is now peak.");
                } else {
                    app.setEmployeePeak(selectedEmployee.getName(), false);
                    System.out.println("Employee, " + selectedEmployee.getName() + " is not peak.");
                }
                break;

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // LogHours()

    public static void LogHours(){
        boolean back = false;
        while(true){
            System.out.println();
            System.out.println("Log Hours Menu:");
            System.out.println("-------------------------");
            System.out.println("1. Log Hours for your activities");
            System.out.println("2. Log Hours for all activities");
            System.out.println("3. Back");
            System.out.println("-------------------------");
            
            System.out.println();

            System.out.println("Select a number from the list above to proceed.");
            int choice = 0;
            
            try {
                String input = console.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid input. Please enter a number from the menu.");
                continue; // skip the rest of the loop and prompt again
            }
            switch(choice){
                case 1:
                    loggedInEmployee = app.getSignedInEmployeeInfo();
                    if (loggedInEmployee.getActivityInfos().size() == 0) {
                        System.out.println("Erm actually, You have no assigned activities (o_o-)");
                        System.out.println();
                        waitTillEnter();
                        System.out.println();
                        break;
                    }
                    System.out.println("-------------------------");
                    System.out.println("List of "+loggedInEmployee.getName()+"'s activities");
                    System.out.println("-------------------------");
                    printActivities(loggedInEmployee.getActivityInfos());
                    
                    while (true){
                        System.out.println("Write the name of the activity you would like to log hours for");
                        try {
                            String input = console.nextLine();
                            System.out.println("Please write the date of the activity in which you would like to log hours in the format: dd-MM-yyyy");
                            String inputdate = console.nextLine();
                            System.out.println("Please write the project ID of your activity");
                            Integer projectID = Integer.parseInt(console.nextLine());
                            System.out.println("Please write the date of the activity in which you would like to log hours in the format: dd-MM-yyyy");
                            System.out.println("Your current hours on the activity on  " +inputdate + " is:");
                            System.out.println(app.getUserLoggedHoursInActivityOnDate(projectID, input, loggedInEmployee.getName(), inputdate));
                            System.out.println("Please write the hours you would like to log. Negative numbers are removed hours, positive are added");
                            Float hours = Float.parseFloat(console.nextLine());
                            app.logHours(projectID, input, inputdate, hours);
                            System.out.println("The new amount of hours is:");
                            System.out.println(app.getUserLoggedHoursInActivityOnDate(projectID, input, loggedInEmployee.getName(), inputdate));
                            System.out.println();
                            waitTillEnter();
                            break;
                        } catch(Exception e) {
                            System.out.println("An error occurred while processing input: " + e.getMessage());
                            System.out.println("Try again or go back? Y/N");
                            while (true) {
                                input = console.nextLine();
                                try {
                                    app.yesOrNo(input);
                                    break;
                                } catch (Exception e2) {
                                    System.err.println(e2.getMessage());
                                }
                            }
                            if (app.yesOrNo(input)) {
                                continue;
                            }
                            break;
                        }
                    }
                    break;
                case 2:
                    List<ProjectInfo> allProjectInfos = app.getallProjectInfos();
                    boolean activityExist = false;
                    for (ProjectInfo projectInfo : allProjectInfos) {
                        if (projectInfo.getActivities().size() > 0) {
                            activityExist = true;
                            System.out.println("-------------------------");
                            System.out.println("List of all activities for Project "+projectInfo.getName()+", with ID "+projectInfo.getID());
                            System.out.println("-------------------------");
                            printActivities(projectInfo.getActivities());
                            System.out.println();
                        }
                    }

                    if (!activityExist) {
                        System.out.println("Error, No activities exist");
                        System.out.println();
                        waitTillEnter();
                        System.out.println();
                        break;
                    }

                    while (true){
                        System.out.println("Write the name of the activity you would like to log hours for");
                        try {
                            String input = console.nextLine();
                            System.out.println("Please write the date of the activity in which you would like to log hours in the format: dd-MM-yyyy");
                            String inputdate = console.nextLine();
                            System.out.println("Please write the project ID of your activity");
                            Integer projectID = Integer.parseInt(console.nextLine());
                            System.out.println("Please write the date of the activity in which you would like to log hours in the format: dd-MM-yyyy");
                            System.out.println("Your current hours on the activity on the date " +inputdate + " is:");
                            System.out.println(app.getUserLoggedHoursInActivityOnDate(projectID, input, loggedInEmployee.getName(), inputdate));
                            System.out.println("Please write the hours you would like to log. Negative numbers are removed hours, positive are added");
                            Float hours = Float.parseFloat(console.nextLine());
                            app.logHours(projectID, input, inputdate, hours);
                            System.out.println("The new amount of hours is:");
                            System.out.println(app.getUserLoggedHoursInActivityOnDate(projectID, input, loggedInEmployee.getName(), inputdate));
                            System.out.println();
                            waitTillEnter();
                            System.out.println();
                            break;
                        } catch(Exception e) {
                            System.out.println("An error occurred while processing input: " + e.getMessage());
                            System.out.println("Try again or go back? Y/N");
                            while (true) {
                                input = console.nextLine();
                                try {
                                    app.yesOrNo(input);
                                    break;
                                } catch (Exception e2) {
                                    System.err.println(e2.getMessage());
                                }
                            }
                            if (app.yesOrNo(input)) {
                                continue;
                            }
                            break;
                        }
                    }
                    break;
                    
                case 3:
                    back = true;
                    break;

                default:
                    break;
            }

            if (back) {
                break;    
            } 
            }
    }

    public static void newPage() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public static void waitTillEnter() {
        System.out.println("Press \'Enter\' to return");
        input = console.nextLine();
        newPage();
    }

    public static void printActivities(List<ActivityInfo> activities) {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getStartWeek() != null) {

                System.out.println(activities.get(i).getName()+", Active from: " 
                +activities.get(i).getStartWeek().getYear()+"-W"
                +activities.get(i).getStartWeek().getWeek()+" to "
                +activities.get(i).getEndWeek().getYear()+"-W"
                +activities.get(i).getEndWeek().getWeek());

            } else {
                System.out.println(activities.get(i).getName());
            }
            
        }
    }

    // ViewAvailableEmployees
    public static void viewAvailableEmployeesUI() {
        while (true) {
            try {
                System.out.println("Select the range of weeks, you want to view available employees for:");
                System.out.println("-------------------------");
                System.out.println("Enter start year:");
                input = console.nextLine();
                App.isPositiveInt(input);
                int startYear = Integer.parseInt(input);
                newPage();

                System.out.println("-------------------------");
                System.out.println("Enter end year:");
                input = console.nextLine();
                App.isPositiveInt(input);
                int endYear = Integer.parseInt(input);
                newPage();

                System.out.println("-------------------------");
                System.out.println("Enter Start week:");
                input = console.nextLine();
                App.isWeek(input,endYear);
                int startWeek = Integer.parseInt(input);
                newPage();

                System.out.println("-------------------------");
                System.out.println("Enter End Week: ");
                input = console.nextLine();
                App.isWeek(input,endYear);
                int endWeek = Integer.parseInt(input);
                System.out.println();
                System.out.println("-------------------------");
                System.out.println();
                System.out.println("The avaialable employees from "+startYear+" W"+startWeek+" to "+endYear+" W"+endWeek+" are:");
                System.out.println("-------------------------");
                for (String e : app.viewAvailableEmployees(startYear, startWeek, endYear, endWeek)) {
                    System.out.println(e);
                }
                System.out.println("-------------------------");
                waitTillEnter();
                newPage();
                break;

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}  


