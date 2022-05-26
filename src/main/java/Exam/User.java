package Exam;

public class User {

    private String name;
    private String username;
    private String password;
    private String posts;


    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        boolean check = true;
        String niceName = name.toLowerCase();
        while (check) {

            for (int i = 0; i < niceName.length(); i++) {

                if (name.charAt(i) < 97 || name.charAt(i) > 122) {
                    System.out.println("Name should only include letters.");
                    break;
                }
            }
            check = false;
        }
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length() < 5) {
            System.out.println("Username should have at least 5 characters.");
        } else {
            this.username = username;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }
}
