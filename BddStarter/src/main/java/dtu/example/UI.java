package dtu.example;

import java.util.Scanner;

public class UI {

    static App app = new App();
    static Scanner console = new Scanner(System.in);

    public static void main(String args[]){
        app.addEmployee("huba");


        String username = "";

        // Starting Program
        while (true){
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
                                        int id = app.createProject(projectName);
                                        
                                        System.out.println("\nSuccesfully created project \""+projectName+"\" with ID: "+id);
                                        System.out.println("-------------------------");
                                        System.out.println("Want to create another project Y/N?"); 
                                        exit = app.yesOrNo(console.nextLine()); 
                                    } while(exit);

                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                                case 4:
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



}
