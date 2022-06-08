package Exam;

import java.util.Scanner;

public class Main {

    public static boolean isLoggedIn = false;
    public static String currentUserName;
    public static Scanner scn = new Scanner(System.in);
    public static boolean isFinished = false;

    public static void main(String[] args) {


        while (!isFinished) {
            if (isLoggedIn) {
                handleLoggedInFlow();
            } else {
                handleLoggedOutFlow();
            }
        }
    }

    private static void handleLoggedOutFlow() {
        printLoggedOutMenu();
        try{
        int choice = scn.nextInt();
        switch (choice) {
            case 1:
                registerUser();
                break;
            case 2:
                loginUser();
                break;
            case 3:
                exitProgram();
                break;
            default:
                System.out.println("Invalid choice");
        }
        }
        catch (Exception e){
            System.out.println("Incorrect choice.");
            exitProgram();
        }

    }

    private static void exitProgram() {
        isFinished = true;
    }

    private static void loginUser() {
        System.out.println("Type Username");
        String username = scn.next();
        System.out.println("Type password");
        String password = scn.next();
        boolean isValid = UserService.validateUser(username, password);
        if (isValid) {
            currentUserName = username;
            isLoggedIn = true;
        } else {
            System.out.println("invalid credentials");
        }

    }

    private static void registerUser() {
        System.out.println("Type Username");
        String username = scn.next();
        System.out.println("Type password");
        String password = scn.next();
        User user = new User(username, password);
        boolean isAdded = UserService.addUser(user);
        if (isAdded) {
            System.out.println("you have registered successfully");
        } else {
            System.out.println("something went wrong, try again");
        }
    }

    private static void handleLoggedInFlow() {
        printLoggedInMenu();
        try{
        int choice = scn.nextInt();
        switch (choice) {
            case 1:
                writePost();
                break;
            case 2:
                showAllPosts();
                break;
            case 3:
                logout();
                break;
            case 4:
                exitProgram();
                break;
            default:
                System.out.println("Invalid choice");
        }
        }
        catch (Exception e){
            System.out.println("Incorrect input");
        }


    }

    private static void logout() {
        isLoggedIn = false;
        currentUserName = null;
    }

    private static void showAllPosts() {
        User[] allUsers = UserService.getAllUsers();
        for (User user : allUsers) {
            if (user != null) {
                for (String post : user.getPosts()) {
                    if (post != null) {
                        System.out.println(user.getUsername() + " : " + post);
                    }
                }
            }
        }
    }

    private static void writePost() {
        System.out.println("Write post");
        String post = scn.next();
        boolean isAdded = UserService.addPostToUser(currentUserName, post);
        if (isAdded) System.out.println("Your post have been added successfully");
        else System.out.println("Something went wrong");
    }

    private static void printLoggedInMenu() {
        System.out.println("1 for write post");
        System.out.println("2 for read all posts");
        System.out.println("3 for logout");
        System.out.println("4 for exit");
    }


    private static void printLoggedOutMenu() {
        System.out.println("1 for reg");
        System.out.println("2 for login");
        System.out.println("3 for exit");
    }

}