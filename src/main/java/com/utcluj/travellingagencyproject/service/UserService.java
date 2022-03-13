package com.utcluj.travellingagencyproject.service;


import com.utcluj.travellingagencyproject.model.User;
import com.utcluj.travellingagencyproject.repository.UserRepo;

import javax.persistence.NoResultException;

public class UserService {
    private UserRepo userRepo;

    public void logIn(String username, String password){
        try{
            User u  = userRepo.getUserByUsernameAndPassword(username,password);
            System.out.println("Username: " + u.getUsername());
            System.out.println("Pass:" + u.getPassword());
        }catch(NoResultException n){
            System.out.println("Check your username or password");
        }

    }
    public void UserService(){
        this.userRepo = new UserRepo();
    }

    public UserService() {
        this.userRepo = new UserRepo();
    }
}
