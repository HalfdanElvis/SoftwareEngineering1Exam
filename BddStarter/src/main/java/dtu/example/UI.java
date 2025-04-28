package dtu.example;

import java.util.Scanner;

public class UI {

    static App app = new App();
    static Scanner console = new Scanner(System.in);

    public static void main(String args[]){
        app.addEmployee("huba");


        String username = "youreabitch";

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
                            System.out.println("How would you like to proceed?");    

                            System.out.println("1. Create Project");
                            System.out.println("2. Manage Project");
                            System.out.println("3. Create Special Activity");
                            System.out.println("4. Manage Special Activities");
                            System.out.println("5. Create Employee");
                            System.out.println("6. Manage Employee");
                            System.out.println("7. Log hours");

                            System.out.println("Select a number from the list above to proceed.");
                            
                            String userInput = console.nextLine();
                            int num = Integer.parseInt(userInput);
                            
                            switch (num) {
                                case 1:
                                    // Create Project
                                case 2:
                                    // Create Project
                                case 3:
                                    // Create Project
                                case 4:
                                    // Create Project
                                case 5:
                                    // Create Project
                                case 6:
                                    // Create Project
                                case 7:
                                    // Create Project
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



}
