package dtu.example;

import java.util.Scanner;

public class UI {

    static Scanner input = new Scanner(System.in);

    public static void main(String args[]){
        App app = new App();
        System.out.println("Login:");
        app.login(input.next());
        while (app.signedInEmployee == null) {
            System.out.println();
            System.out.println("Login:");
            app.login(input.next());
        }   
    }
}
