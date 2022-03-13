package com.utcluj.travellingagencyproject.controller;


import com.utcluj.travellingagencyproject.service.UserService;

public class UserController {
    private UserService userService;
    private VacationPackageController vacationController;

    public void logIn(String username, String password){
       userService.logIn(username, password);
    }
    public void register(String username, String password){

    }
    public void UserController(){
        this.userService = new UserService();
    }

    public UserController() {
        this.userService = new UserService();
    }
}
