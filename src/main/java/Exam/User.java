package Exam;

public class User {
    private String username;
    private String password;
    private String[] posts = new String[10];

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getPosts() {
        return posts;
    }

    public void setPosts(String[] posts) {
        this.posts = posts;
    }
}