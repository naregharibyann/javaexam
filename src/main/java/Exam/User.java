package Exam;

public class User {

    private String name;
    private String username;
    private String password;
    private String[] posts = new String[posMaxCount];
    static int posMaxCount = 10;


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

    public void getPosts() {
        int j = 0;

        for (int i = 0; i < posMaxCount; i++) {
            if (this.posts[i] != null) {
                if (j == 0) {
                    System.out.println(getUsername() + " has following posts ");
                }
                System.out.println("- " + this.posts[i]);
                j++;
            }
        }
        if (j == 0) {
            System.out.println(getUsername() + " has no posts.");
        }
    }

    public void setPosts(String post) {
        for (int i = 0; i < posMaxCount; i++) {
            if (this.posts[i] == null) {
                this.posts[i] = post;
                break;
            } else {
                System.out.println("You cannot post anymore");
            }
        }
    }
}
