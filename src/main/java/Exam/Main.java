package Exam;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static boolean isLoggedIn = false;
    public static String currentUserName;
    public static Scanner scn = new Scanner(System.in);
    public static boolean isFinished = false;
    public static ArrayList<User>  users = new ArrayList<>();
    public static BufferedWriter bw = null;
    public static BufferedReader br = null;
    public static String projectRootPath = "/Users/naregharibyan/Desktop/src/main/java/Exam/";


    public static void main(String[] args) throws IOException {
        readAllUsers();
        while (!isFinished) {
            if (isLoggedIn) {
                handleLogedinFlow();
            } else {
                handleLogedoutFlow();
            }
        }
    }

    private static void readAllUsers() {
        ArrayList<String> userStrings = readFromFile();
        for (String userString: userStrings){
            User user = new User();
            String[] split = userString.trim().split(" ");
            user.setUsername(split[0]);
            user.setPassword(split[1]);
            ArrayList<String> posts = new ArrayList<>();
            for (int j = 2; j < split.length; j++) {
                posts.add(split[j]);
            }
            user.setPosts(posts);
            users.add(user);
        }
    }

    private static void writeAllUsers() {
        ArrayList<String> userStrings = new ArrayList<>();
        for (User user: users) {
            if (user == null) continue;
            StringBuilder userPosts = new StringBuilder();
            for (String post: user.getPosts()){

                if (post == null) continue;
                userPosts.append(post);
                userPosts.append(" ");
            }

            String userString = user.getUsername() +
                    " " +
                    user.getPassword() +
                    " " +
                    userPosts;
            userStrings.add(userString);
        }
        writeToFile(userStrings);
    }


    private static void handleLogedoutFlow() {
        printLogedOutMenu();
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

    private static void exitProgram() {
        writeAllUsers();
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

    private static User registerUser() {
        System.out.println("Type Username");
        String username = scn.next();
        System.out.println("Type password");
        String password = scn.next();
        User user = new User(username, password);
        boolean isAdded = UserService.addUser(user);
        if (isAdded) {
            System.out.println("you have registered successfully");
            return user;
        } else {
            System.out.println("something went wrong, try again");
            return null;
        }
    }


    private static void handleLogedinFlow() throws IOException {
        printLogedInMenu();
        switch (scn.nextInt()) {
            case 1:
                writePost();

                break;
            case 2:
                showAllPosts();
                readFromFile();
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

    private static void logout() {
        isLoggedIn = false;
        currentUserName = null;
    }

    private static void showAllPosts() {
        ArrayList<User> allUsers = UserService.getAllUsers();
        for (User user : allUsers) {
            for (String post : user.getPosts()) {
                System.out.println(user.getUsername() + " : " + post);
            }
        }
    }

    private static void writePost() {
        System.out.println("Write post");
        String post = scn.next();
        boolean isAdded = UserService.addPostToUser(currentUserName, post);
        if (isAdded) System.out.println("Your post have been added successfully");
    }


    private static void printLogedInMenu() {
        System.out.println("1 for write post");
        System.out.println("2 for read all posts");
        System.out.println("3 for logout");
        System.out.println("4 for exit");
    }


    private static void printLogedOutMenu() {
        System.out.println("1 for reg");
        System.out.println("2 for login");
        System.out.println("3 for exit");
    }


    public static void writeToFile(ArrayList<String> elements) {
        try {
            bw = new BufferedWriter(new FileWriter(projectRootPath + "test.txt", true));
            for (String element: elements) {
//                if (elements[i] == null) continue;
                bw.append(element + "\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static ArrayList<String> readFromFile() {
        ArrayList<String> result = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(projectRootPath + "test.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}