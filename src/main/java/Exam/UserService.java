package Exam;

public class UserService {

    private static User[] users = new User[10];

    public static User[] getAllUsers() {
        return users;
    }

    public static boolean addUser(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                return true;
            }
        }
        return false;
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user != null && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static boolean validateUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }


    public static boolean addPostToUser(String username, String post) {
        User user = getUserByUsername(username);
        if (user == null) return false;
        for (int i = 0; i < user.getPosts().length; i++) {
            if (user.getPosts()[i] == null) {
                user.getPosts()[i] = post;
                return true;
            }
        }
        return false;
    }
}
