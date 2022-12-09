package com.example.UserAccountService.model;


public class LoginUser {
// parameters
private String username;
private String password;

// parameterized constructor
public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
}
//Default constructors

public LoginUser() {}

// getters and setters methods 

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
// to string method

@Override
public String toString() {
        return "LoginUser [username=" + username + ", password=" + password + "]";
}        

}