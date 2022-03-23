package com.utcluj.travellingagencyproject.controller;


import com.utcluj.travellingagencyproject.exceptions.InvalidInputException;
import com.utcluj.travellingagencyproject.exceptions.LimitOfPeopleReachedException;
import com.utcluj.travellingagencyproject.exceptions.PasswordTooShortException;
import com.utcluj.travellingagencyproject.model.User;
import com.utcluj.travellingagencyproject.model.VacationPackage;
import com.utcluj.travellingagencyproject.service.UserService;


public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public User login(String username, String password){
        return userService.getUserByUsernameAndPassword(username, password);
    }

    public void register(String username, String password) throws PasswordTooShortException, InvalidInputException {
        userService.register(username, password);
    }

    public void bookVacation(User user, VacationPackage vp) throws LimitOfPeopleReachedException {
        this.userService.bookVacationPackage(user, vp);
    }

    public User getUserByUsername(String username){
        return userService.getUserByUsername(username);
    }

    public void UserController(){
        this.userService = new UserService();
    }



}
