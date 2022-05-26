package Exam;

import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    static int accountMaxCount = 10;
    static User[] registeredUsers = new User[accountMaxCount];
    static String currentUsername;
    static String userPost;

    public static void main(String[] args) {
        int regCount = 0;
        boolean toggle = true;
        boolean isSignedIn = false;

        while (toggle) {
            printMenu();
            int selection = input.nextInt();
            switch (selection) {
                case 1:
                    if (regCount > accountMaxCount) {
                        System.out.println("Registration is full. You can only sign in.");
                    } else {
                        User userReg = registration();
                        if (userReg != null) {
                            registeredUsers[regCount] = userReg;
                            regCount++;
                        }
                    }
                    break;
                case 2:
                    isSignedIn = signIn(registeredUsers, regCount);
                    System.out.println(isSignedIn);
                    break;
                case 3:
                    if (isSignedIn) {
                        writePost();
                        for (int i = 0; i < accountMaxCount; i++) {
                            if(!registeredUsers[i].getUsername().equals(null) && registeredUsers[i].getUsername().equals(currentUsername)){
                                registeredUsers[i].getPosts().equals(userPost);
                            }

                        }
                    } else {
                        System.out.println("You cannot create or view posts.");
                    }
                    break;
                default:
                    toggle = false;
                    break;

            }
        }
    }

    private static void writePost() {
        Scanner postInput = new Scanner(System.in);
        System.out.println("Please type your post.");
        String post = postInput.next();
        userPost = post;
    }


    private static boolean signIn(User[] registeredUser, int regCount) {
        Scanner signIn = new Scanner(System.in);
        System.out.println("Please enter username. ");
        String username = signIn.next();
        System.out.println("Please enter password. ");
        String password = signIn.next();

        if (regCount == 0) {
            System.out.println("You cannot log in.");
            return false;
        }

        for (int i = 0; i < regCount; i++) {
            if (registeredUser[i].getUsername().equals(username) && registeredUser[i].getPassword().equals(password)) {
                System.out.println("Login successful. ");
                currentUsername = username;
                return true;
            }

        }
        System.out.println("Username or password is wrong. ");
        return false;
    }

    private static User registration() {
        Scanner registration = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String name = registration.next();
        System.out.println("Please enter username: ");
        String username = registration.next();
        System.out.println("Please enter password: ");
        String password = registration.next();
        System.out.println("Please confirm password: ");
        String passwordConf = registration.next();

        if (!password.equals(passwordConf)) {
            System.out.println("Passwords don't match. Registration failed. ");
            return null;
        } else {
            User user = new User(name, username, password);
            System.out.println("Successfully registered. ");
            return user;
        }
    }


    public static void printMenu() {
        System.out.println("Press 1 to register, \n" +
                "Press 2 to sign in, \n" +
                "Press 3 to add or view posts, \n" +
                "Press any key to exit.");
    }
}
