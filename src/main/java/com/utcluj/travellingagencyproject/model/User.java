package com.utcluj.travellingagencyproject.model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    private String username;

    @Column(name = "user_type")
    private UserType userType;

    @Column
    private String password;


    public UserType getUserType() {
        return userType;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, UserType userType, String password) {
        this.username = username;
        this.userType = userType;
        this.password = password;
    }

    public User() {

    }
}


