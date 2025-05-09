package dtu.example;

import java.util.List;
import java.util.Scanner;

import dtu.example.DTO.ProjectInfo;

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
                            

                            int choice = -1;  // initialize with invalid default
                            String input = "";
                            try {
                                input = console.nextLine();
                                choice = Integer.parseInt(input);
                            } catch (NumberFormatException e) {
                                System.out.println();
                                System.out.println("Invalid input. Please enter a number from the menu.");
                                continue; // skip the rest of the loop and prompt again
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

                                    //CreateSpecialActivity
                                    CreateSpecialActivityUI();
                                    
                                    break;

                                case 4:

                                    // Manage your special activites

                                    // Checks if you have any special activites
                                    if(app.getSignedInEmployee().howManySpecialActivities() == 0){
                                        System.out.println("you have no special activities, returning to main menu.");
                                        break;
                                    }

                                    manageSpecialActivitiesUI();

                                    
                                    break;

                                case 5:
                                    
                                    // Create User:
                                    createUserUI();

                                    break;
                                
                                case 6:
                                    
                                    // Manage User
                                    manageUserUI();

                                    break;

                                case 7:
                                    break;

                                
                                case 8:
                                    
                                    // Closes Program:
                                    System.exit(0);

                                    break;

                                
                                case 9:

                                    // For Testing:
                                    testingUI();
                                    
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

    public static void printManageProjectMenu(int id) {
        System.out.println("-------------------------");
        System.out.println("Selected project "+ id);
        System.out.println("-------------------------");

        System.out.println();
        System.out.println("Manage Project Menu:");
        System.out.println("-------------------------");
        System.out.println("1. Make activity");
        System.out.println("2. View activities");
        System.out.println("3. Assign Project-leader");
        System.out.println("4. Delete activity");
        System.out.println("5. Delete project");
        System.out.println("6. Exit menu");
        System.out.println("-------------------------");

        System.out.println();

        System.out.println("Select a number from the list above to proceed.");
    }


    public static void CreateSpecialActivityUI() {
        boolean saCreator = true;
                                
        do {
            boolean choice = true;
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
                
                choice = app.yesOrNo(console.nextLine());
                goesIntoNextyear = true;
            }

            // Creates activity unless, user decided not to, by not continueing it into next year.
            if (choice) {
                SpecialActivity a = new SpecialActivity(activityName);
                
                if (goesIntoNextyear) {
                    app.getSignedInEmployee().assignSpecialActivity(activityName, activityYearInt, activityStartWeekInt, activityYearInt+1, activityEndWeekInt);
                } else {
                    app.getSignedInEmployee().assignSpecialActivity(activityName, activityYearInt, activityStartWeekInt, activityYearInt, activityEndWeekInt);
                }
                
                
                System.out.println();
                System.out.println("Succesfully created special activity \""+activityName+"\".");
                System.out.println("-------------------------");
            } else {
                System.out.println();
                System.out.println("Activity was not created, since timeline wasn't possible.");
                System.out.println("-------------------------");
            }

            System.out.println("Want to create another special activity Y/N?"); 
            saCreator = app.yesOrNo(console.nextLine());

        } while(saCreator);
    }


    // Manage Special Activity
    public static void manageSpecialActivitiesUI() {
        int choice = -1;
        while (!(choice<=app.getSignedInEmployee().howManySpecialActivities() && choice >= 1)) {
            // Select Activity
            System.out.println();
            System.out.println("Special Activites:");
            System.out.println("-------------------------");
            app.getSignedInEmployee().printAllSpecialActivities();
            System.out.println("-------------------------");
            System.out.println();
            System.out.println("select the special activity you want to manage by entering the number in front of it:");
            
            try {
                String input = console.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid input. Please enter a number from the menu.");
                continue; // skip the rest of the loop and prompt again
            }
            app.setSelectedSpecialActivity(app.getSignedInEmployee().selectSpecialActivityNumber(choice));
        }

        while (true) {
            // Manage Special Activity
            System.out.println();
            System.out.println("Manage Special Activity: " + app.getSelectedSpecialActivity().getName());
            System.out.println("-------------------------");
            System.out.println("1. Views Active Weeks");
            System.out.println("2. Assign User");
            System.out.println("3. Change Active Weeks");
            System.out.println("4. Delete Activity");
            System.out.println("5. Back");
            System.out.println("-------------------------");
            
            System.out.println();

            System.out.println("Select a number from the list above to proceed.");

            try {
                String input = console.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid input. Please enter a number from the menu.");
                continue; // skip the rest of the loop and prompt again
            }

            boolean back = false;
            switch (choice) {
                // View Active Weeks:
                case 1:
                    
                    System.out.println();
                    System.out.println("Special Activity \"" + app.getSelectedSpecialActivity().getName() + "\" is active in the following weeks:");
                    
                    int startYear = app.getSelectedSpecialActivity().getStartWeek().getYear();
                    System.out.println(startYear+":");
                    for (Week week : app.getSelectedSpecialActivity().getActiveWeeks()) {
                        if (startYear != week.getYear()) {
                            startYear = week.getYear();
                            System.out.println();
                            System.out.println(startYear+":");
                        }
                        System.out.print(week.getWeek()+" ");
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

    public static void createProjectUI() {
        boolean exit = true;
        do {
            printCreateProjectMenu();
            String projectName = console.nextLine();
            int id = app.createProject(projectName);
            
            System.out.println("\nSuccesfully created project \""+projectName+"\" with ID: "+id);
            System.out.println("-------------------------");
            System.out.println("Want to create another project Y/N?"); 
            exit = app.yesOrNo(console.nextLine()); 
        } while(exit);
    }


    public static void manageProjectUI() throws IllegalAccessException {
        boolean exit = true;
        System.out.println("Enter year of the project:");
        String input = console.nextLine();
        int year = Integer.parseInt(input);

        while (true) {
            if (app.getProjectAmountFromYear(year) == 0) {
            System.out.println("-------------------------");
            System.out.println("No projects found");
            System.out.println("-------------------------");
            System.out.println();
            System.out.println("Press \'Enter\' to return");
            input = console.nextLine();
            break;
            }

            List<ProjectInfo> projectList = app.getDTOProjectList(year);

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
            
            input = console.nextLine();
            int projectID = Integer.parseInt(input);
            ProjectInfo project = app.createDTOProject(projectID);
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
                switch (choice) {
                    case 1:
                        boolean makenew = true;
                        do {
                            System.out.println("Enter name for activity:");
                            String activityName = console.nextLine();
                            app.addActivity(projectID, activityName);

                            System.out.println("Enter Start Week for "+activityName);
                            input = console.nextLine();
                            int startWeek = Integer.parseInt(input);

                            System.out.println("Enter End Week for "+activityName);
                            input = console.nextLine();
                            int endWeek = Integer.parseInt(input);

                            System.out.println("What is the due year for "+ activityName);
                            input = console.nextLine();
                            int endYear = Integer.parseInt(input);

                            app.setActivitiyStartAndEndWeek(projectID, activityName, 2025, startWeek, endYear, endWeek);


                            System.out.println("\nSuccesfully created activity \""+activityName+"\"");
                            System.out.println("-------------------------");
                            System.out.println("Want to create another activity Y/N?"); 
                            makenew = app.yesOrNo(console.nextLine()); 
                        } while(makenew);
                        break;
                    case 2:
                        
                        project = app.createDTOProject(projectID);    

                        System.out.println("-------------------------");
                        System.out.println("List of activities");
                        System.out.println("-------------------------");
                        System.out.println();
                        
                        for (int i = 0; i < project.getActivities().size(); i++) {
                            System.out.print(project.getActivities().get(i).getName()+", StartWeek: ");
                            System.out.print(project.getActivities().get(i).getStartWeek().getWeek()+", Endweek: ");
                            System.out.println(project.getActivities().get(i).getEndWeek().getWeek());
                        }

                        System.out.println();
                        System.out.println("-------------------------");
                        System.out.println("Press \'Enter\' to return");
                        input = console.nextLine();
                        break;
                    case 3:
                        System.out.println("Enter username of the to be assigned leader:");
                        String username = console.nextLine();
                        app.assignLeader(username, projectID);

                        System.out.println();
                        System.out.println("Employee "+username+" was succesfully assigned as projectleader.");
                        System.out.println();
                        break;
                    case 4:
                        break;
                    case 5:
                        
                        break;
                    case 6:
                        exit = false;
                        break;
            
                    
                    default:
                        break;
                    
                }
            } while (exit);

            break;
        }
    }

    public static void createUserUI() {
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("What should the username be?");
        
        while (true) { 
            String username = console.nextLine();
            try {
                app.legalUsername(username);
                app.addEmployee(username);
                System.out.println();
                System.out.println("succesfully created user with name: " + username);
                System.out.println();
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void manageUserUI() {
        // Select User
        System.out.println();
        System.out.println("Users:");
        System.out.println("-------------------------");
        
        System.out.println("-------------------------");
        System.out.println();
        System.out.println("Select a user from the list above by inserting their username:");
        
        while (true) { 
            String username = console.nextLine();
            try {
                if(app.legalUsername(username)){
                    app.setSelectedEmployee(username);
                    break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            
        }
    
        
        while (true) {
             // Manage User
            System.out.println();
            System.out.println("Manage User: " + app.getSelectedEmployee().getUsername());
            System.out.println("-------------------------");
            System.out.println("1. Assign to Activity");
            System.out.println("2. Assign to Special Activity");
            System.out.println("3. Set/Unset Peak Performance");
            System.out.println("4. Delete User");
            System.out.println("5. Back");
            System.out.println("-------------------------");
            
            System.out.println();

            System.out.println("Select a number from the list above to proceed.");

            int choice = -1;
            try {
                String input = console.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid input. Please enter a number from the menu.");
                continue; // skip the rest of the loop and prompt again
            }
            boolean back = false;
            switch (choice) {
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
                    break;
            
                default:
                    break;

            }

            if (back) {
                break;
            }
        }
    }

    public static void testingUI() {
        while (true) {
            // Printing all users, projects .etc
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
            
            int choice = -1;
            try {
                String input = console.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid input. Please enter a number from the menu.");
                continue; // skip the rest of the loop and prompt again
            }

            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.println("Users:");
                    System.out.println("-------------------------");
                    
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
                    break;
            
                default:
                    break;

            }

            if (back) {
                break;
            }

        }
    }

}


