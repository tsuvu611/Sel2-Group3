package data;


import common.Constant;
import common.Utility;

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


    public User getRandomUser(){
        this.username = Utility.randomString(3);
        this.password = Utility.randomString(3);
        return this;
    }
}
