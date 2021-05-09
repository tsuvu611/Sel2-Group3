package data;


import common.Constant;

public class User {
    private String username;
    private String password;

    public User() {
        this.username = Constant.USERNAME;
        this.password = Constant.PASSWORD;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
