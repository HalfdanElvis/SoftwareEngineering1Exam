package dtu.example;

import java.util.Scanner;

public class UI {

    static App app = new App();
    static Scanner input = new Scanner(System.in);

    public static void main(String args[]){
        app.addEmployee("huba");


        String username = "youreabitch";

        // Starting Program
        while (true){
            // Login method
            while (true) {

                if (app.getSignedInEmployee() == null){
                    System.out.println("Login:");
                    username = input.nextLine();
                }
                
                try {
                    if (app.login(username)) {
                        System.out.println("We locked in");
                        while (true) {
                            
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
                String userInput = input.nextLine();
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

    private static boolean promtUserForRegistration(String input) {
        return app.yesOrNo(input);
    }



}
