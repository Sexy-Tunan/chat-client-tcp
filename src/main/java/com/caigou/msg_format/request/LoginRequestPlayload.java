package com.caigou.msg_format.request;

public class LoginRequestPlayload {

    private String user;
    private String password;

    public LoginRequestPlayload() {
    }

    public LoginRequestPlayload(String userName, String password) {
        this.user = userName;
        this.password = password;
    }

    public String getUserName() {
        return user;
    }

    public void setUserName(String userName) {
        this.user = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
