package dtu.example;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    static App app = new App();
    static Scanner console = new Scanner(System.in);

    public static void main(String args[]){
        app.addEmployee("huba");


        String username = "";

        // Starting Program
        while (true) {
            // Login method
            while (true) {

                if (app.getSignedInEmployee() == null){
                    System.out.println("Login:");
                    username = console.nextLine();
                }
                
                try {
                    if (app.login(username)) {
                        while (true) {
                            // Main Menu:
                            printMainMenu();
                            
                            String userInput = console.nextLine();
                            int num = Integer.parseInt(userInput);
                            
                            switch (num) {
                                case 1:
                                    boolean exit = true;

                                    do {
                                        printCreateProjectMenu();
                                        String projectName = console.nextLine();
                                        int id = app.createProject(projectName).getID();
                                        
                                        System.out.println("\nSuccesfully created project \""+projectName+"\" with ID: "+id);
                                        System.out.println("-------------------------");
                                        System.out.println("Want to create another project Y/N?"); 
                                        exit = app.yesOrNo(console.nextLine()); 
                                    } while(exit);
                                    

                                    break;
                                case 2:
                                    exit = true;
                                    System.out.println("Enter year of the project:");
                                    userInput = console.nextLine();
                                    int year = Integer.parseInt(userInput);

                                    System.out.println();
                                    System.out.println("-------------------------");
                                    System.out.println("List of Projects");
                                    System.out.println("-------------------------");
                                    System.out.println();

                                    app.printProjectList(year);

                                    System.out.println();
                                    System.out.println("-------------------------");
                                    System.out.println("Enter project id:");

                                    userInput = console.nextLine();
                                    Project project = app.intToProject(Integer.parseInt(userInput));

                                    while (true) {
                                        do {
                                            printManagaeProjectMenu(project);
                                            userInput = console.nextLine();
                                            num = Integer.parseInt(userInput);

                                            switch (num) {
                                                case 1:
                                                    break;
                                                case 2:
                                                    break;
                                                case 3:
                                                    break;
                                                case 4:
                                                    System.out.println("Enter username of the to be assigned leader:");
                                                    username = console.nextLine();
                                                    app.assignLeader(username, project.getID());

                                                    System.out.println();
                                                    System.out.println("Employee "+username+" was succesfully assigned as projectleader.");
                                                    System.out.println();
                                                    
                                                    break;
                                                case 5:
                                                    exit = false;
                                                    break;
                                                
                                                default:
                                                    break;
                                                
                                            }
                                        } while (exit);

                                        break;
                                    }

                                    break;
                                case 3:
                                    
                                    boolean saCreator = true;
                                    
                                    do {
                                        boolean choice = true;
                                        System.out.println();
                                        System.out.println("Create Special Activity");
                                        System.out.println("-------------------------");
                                        

                                        String activityName = null;
                                        while (activityName == null) {
                                            System.out.println("Enter Activity name:");    
                                            try {
                                                String temp = console.nextLine();
                                                if (app.specialActivityNameTaken(temp)) {
                                                    activityName = temp;
                                                }
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                                System.out.println();
                                            }
                                        }
                                        

                                        System.out.println();
                                        System.out.println("What year is the activity in?");
                                        
                                        ArrayList<Integer> activityYearsInt = new ArrayList<>();
                                        
                                        while (activityYearsInt.isEmpty() || activityYearsInt.get(0) < 0){
                                            try {
                                                String activityYears = console.nextLine();
                                                if (isPositiveInt(activityYears)) {
                                                    activityYearsInt.add(Integer.parseInt(activityYears));
                                                }
                                            } catch (Exception e) {
                                                System.err.println(e.getMessage());
                                            }
                                        }

                                        System.out.println();
                                        System.out.println("What is the start week of the activity?");
                                        

                                        Integer activityStartWeekInt = null;
                                        while (activityStartWeekInt == null || activityStartWeekInt < 0){
                                            try {
                                                String activityStartWeek = console.nextLine();
                                                if (isWeek(activityStartWeek)) {
                                                    activityStartWeekInt = Integer.parseInt(activityStartWeek);
                                                }
                                            } catch (Exception e) {
                                                System.err.println(e.getMessage());
                                            }
                                        }

                                        System.out.println();
                                        System.out.println("What is the start end of the activity?");

                                        Integer activityEndWeekInt = null;
                                        while (activityEndWeekInt == null || activityEndWeekInt < 0){
                                            try {
                                                String activityEndWeek = console.nextLine();
                                                if (isWeek(activityEndWeek)) {
                                                    activityEndWeekInt = Integer.parseInt(activityEndWeek);
                                                }
                                            } catch (Exception e) {
                                                System.err.println(e.getMessage());
                                            }
                                        }
                                        
                                        if (activityStartWeekInt > activityEndWeekInt) {
                                            System.out.println("Your end week for the activity is earlier in the year than your start week.");
                                            System.out.println("Do you want to continue the activity into "+(activityYearsInt.get(0)+1)+" Y/N?");
                                            choice = app.yesOrNo(console.nextLine());
                                            if (choice){
                                                activityYearsInt.add(activityYearsInt.get(0)+1);
                                                System.out.println("test");
                                            }
                                        }

                                        if (choice) {
                                            SpecialActivity a = new SpecialActivity(activityName);
                                            a.setYears(activityYearsInt);
                                            ArrayList<Integer> activeWeeks = new ArrayList<>();
                                            for (int i = activityStartWeekInt; i != (activityEndWeekInt + 1); i = (i % 52 + 1)){
                                                activeWeeks.add(i);
                                            }
                                            a.setActiveWeeks(activeWeeks);
                                            app.addSpecialActivity(a);
                                            System.out.println();
                                            System.out.println("Succesfully created project \""+activityName+"\".");
                                            System.out.println("-------------------------");
                                        } else {
                                            System.out.println();
                                            System.out.println("Activity was not created, since timeline wasn't possible.");
                                            System.out.println("-------------------------");
                                        }

                                        System.out.println("Want to create another project Y/N?"); 
                                        saCreator = app.yesOrNo(console.nextLine());

                                    } while(saCreator);
                                    
                                    break;

                                case 4:
                                    // Select Activity
                                    System.out.println();
                                    System.out.println("Special Activites:");
                                    System.out.println("-------------------------");
                                    app.printAllSpecialActivities();
                                    System.out.println("-------------------------");
                                    System.out.println();
                                    System.out.println("Select an activity from the list above by inserting it's name:");
                                    
                                    while (true) {
                                        app.setSelectedSpecialActivity(null);

                                        String activityName = console.nextLine();
                                        try {
                                            if(app.specialActivityExists(activityName)){
                                                app.setSelectedSpecialActivity(activityName);
                                                break;
                                            }
                                        } catch (Exception e) {
                                            System.err.println(e.getMessage());
                                            break;
                                        }                        
                                    }

                                    if (app.getSelectedSpecialActivity() == null) { 
                                        System.out.println();
                                        System.out.println("Returning to Main Menu...");
                                        break;
                                    }
                                    
                                    while (true) {
                                        // Manage Special Activity
                                        System.out.println();
                                        System.out.println("Manage Special Activity:");
                                        System.out.println("-------------------------");
                                        System.out.println("1. Views Active Weeks");
                                        System.out.println("2. Assign User");
                                        System.out.println("3. Change Active Weeks");
                                        System.out.println("4. Delete Activity");
                                        System.out.println("5. Back");
                                        System.out.println("-------------------------");
                                        
                                        System.out.println();

                                        System.out.println("Select a number from the list above to proceed.");

                                        String choice = console.nextLine();
                                        int tempNum = Integer.parseInt(choice);
                                        boolean back = false;
                                        switch (tempNum) {

                                            // View Active Weeks:
                                            case 1:
                                                System.out.println();
                                                System.out.println("Special Activity \"" + app.getSelectedSpecialActivity().getName() + "\" is active in the following weeks:");
                                                System.out.println("Year " + app.getSelectedSpecialActivity().getYears().get(0) + ":");
                                                Integer prevWeek = null;
                                                for (Integer week : app.getSelectedSpecialActivity().getActiveWeeks()){
                                                    if (prevWeek == null) {
                                                        prevWeek = week;
                                                    }
                                                    if (prevWeek>week && app.getSelectedSpecialActivity().getYears().size() > 1){
                                                        // Hardcoded, kan kun strække sig over 2 år for nu
                                                        System.out.println("Year " + app.getSelectedSpecialActivity().getYears().get(1) + ":");
                                                        System.out.println();
                                                        prevWeek = week;
                                                    }
                                                    System.out.print(week+" ");
                                                }
                                                System.out.println();

                                                break;

                                            case 2:
                                                break;

                                            case 3:
                                                break;

                                            case 4:
                                                break;

                                            case 5:
                                                back = true;
                                        
                                            default:
                                                break;

                                        }

                                        if (back) {
                                            break;
                                        }
                                    }


                                    break;

                                
                                // Create User:
                                case 5:
                                    System.out.println();
                                    System.out.println("-------------------------");
                                    System.out.println("What should the username be?");
                                    
                                    while (true) { 
                                        username = console.nextLine();
                                        try {
                                            app.legalUsername(username);
                                            app.addEmployee(username);
                                            break;
                                        } catch (Exception e) {
                                            System.err.println(e.getMessage());
                                        }
                                    }

                                    break;
                                
                                // Manage User
                                case 6:
                                    // Select User
                                    System.out.println();
                                    System.out.println("Users:");
                                    System.out.println("-------------------------");
                                    app.printAllEmployees();
                                    System.out.println("-------------------------");
                                    System.out.println();
                                    System.out.println("Select a user from the list above by inserting their username:");
                                    
                                    while (true) { 
                                        username = console.nextLine();
                                        try {
                                            if(app.legalUsername(username)){
                                                if(app.employeeExists(username)){
                                                    app.setSelectedEmployee(username);
                                                    break;
                                                }
                                            }
                                        } catch (Exception e) {
                                            System.err.println(e.getMessage());
                                        }
                                        
                                    }
                                    
                                    // Manage User
                                    System.out.println();
                                    System.out.println("Manage User:");
                                    System.out.println("-------------------------");
                                    System.out.println("1. Assign to Activity");
                                    System.out.println("2. Assign to Special Activity");
                                    System.out.println("3. Set/Unset Peak Performance");
                                    System.out.println("4. Delete User");
                                    System.out.println("5. Back");
                                    System.out.println("-------------------------");
                                    
                                    System.out.println();

                                    System.out.println("Select a number from the list above to proceed.");
                                    
                                    while (true) {
                                        String choice = console.nextLine();
                                        int tempNum = Integer.parseInt(choice);
                                        boolean back = false;
                                        switch (tempNum) {
                                            case 1:
                                                break;

                                            case 2:
                                                break;

                                            case 3:
                                                break;

                                            case 4:
                                                break;

                                            case 5:
                                                back = true;
                                        
                                            default:
                                                break;

                                        }

                                        if (back) {
                                            break;
                                        }
                                    }

                                case 7:
                                    break;

                                // Closes Program:
                                case 8:
                                    System.exit(0);
                                    break;

                                // For Testing:
                                case 9:
                                    while (true) {
                                        System.out.println();
                                        System.out.println("View Menu:");
                                        System.out.println("-------------------------");
                                        System.out.println("1. View all Employees");
                                        System.out.println("2. View all Projects");
                                        System.out.println("3. View all Actvities");
                                        System.out.println("4. View all Special Activites");
                                        System.out.println("5. Back");
                                        System.out.println("-------------------------");
                                        
                                        System.out.println();

                                        System.out.println("Select a number from the list above to proceed.");
                                        
                                        boolean back = false;
                                        
                                        // Printing all users, projects .etc
                                        String string = console.nextLine();
                                        int choice = Integer.parseInt(string);
                                        switch (choice) {
                                            case 1:
                                                System.out.println();
                                                System.out.println("Users:");
                                                System.out.println("-------------------------");
                                                app.printAllEmployees();
                                                System.out.println("-------------------------");
                                                break;

                                            case 2:
                                                break;

                                            case 3:
                                                break;

                                            case 4:
                                                break;

                                            case 5:
                                                back = true;
                                        
                                            default:
                                                break;

                                        }

                                        if (back) {
                                            break;
                                        }

                                    }
                                    
                                default:
                                    break;
                                
                            }




                        }
                    }
                    break;

                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            
            // New User registration
            System.out.println();
            System.out.println("User doesn't exist. Create user with username: "+username+" Y/N?");
            while (true) {
                String userInput = console.nextLine();
                try {
                    if (app.yesOrNo(userInput)) {
                        app.addEmployee(username);
                        app.login(username);
                    }
                    break;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                
            }
        }
    }





















    
    public static void printMainMenu() {
        System.out.println();

        System.out.println("-------------------------");
        System.out.println("Logged in user: "+app.getSignedInEmployeeUsername());
        System.out.println("-------------------------");

        System.out.println();
        System.out.println("Main Menu:");
        System.out.println("-------------------------");
        System.out.println("1. Create Project");
        System.out.println("2. Manage Project");
        System.out.println("3. Create Special Activity");
        System.out.println("4. Manage Special Activities");
        System.out.println("5. Create Employee");
        System.out.println("6. Manage Employee");
        System.out.println("7. Log hours");
        System.out.println("8. Exit Program");
        System.out.println("9. View all (FOR TESTING)");
        System.out.println("-------------------------");

        System.out.println();

        System.out.println("Select a number from the list above to proceed.");
    }

    public static void printCreateProjectMenu() {
        System.out.println();
        System.out.println("Create Project");
        System.out.println("-------------------------");
        System.out.println("Enter Project name:");
    }

    public static void printManagaeProjectMenu(Project project) {
        System.out.println("-------------------------");
        System.out.println("Selected project "+ project.printProject());
        System.out.println("-------------------------");

        System.out.println();
        System.out.println("Manage Project Menu:");
        System.out.println("-------------------------");
        System.out.println("1. Make activity");
        System.out.println("2. Add employee");
        System.out.println("3. Assign activity");
        System.out.println("4. Assign Project-leader");
        System.out.println("5. Exit menu");
        System.out.println("-------------------------");

        System.out.println();

        System.out.println("Select a number from the list above to proceed.");
    }

    public static boolean isPositiveInt(String input) {
        try {
            Integer temp = Integer.parseInt(input);
            if (temp >= 0){
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
