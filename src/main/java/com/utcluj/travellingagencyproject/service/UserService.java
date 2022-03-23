package com.utcluj.travellingagencyproject.service;


import com.utcluj.travellingagencyproject.exceptions.InvalidInputException;
import com.utcluj.travellingagencyproject.exceptions.LimitOfPeopleReachedException;
import com.utcluj.travellingagencyproject.model.User;
import com.utcluj.travellingagencyproject.model.UserType;
import com.utcluj.travellingagencyproject.model.VacationPackage;
import com.utcluj.travellingagencyproject.exceptions.PasswordTooShortException;
import com.utcluj.travellingagencyproject.repository.UserRepository;

import javax.persistence.NoResultException;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    };

    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }

    public User getUserByUsername(String username) throws NoResultException {

        return userRepository.getUserByUsername(username);

    }

    public void register(String username, String password) throws PasswordTooShortException, InvalidInputException {
        validateUsername(username);
        validatePassword(password);
        User user = new User(username, UserType.REGULAR, password);
        userRepository.insertNewUser(user);
    }


    public void bookVacationPackage(User user, VacationPackage vp) throws LimitOfPeopleReachedException {
        validateNumber(vp.getNoAvailableSeats());
        userRepository.bookVacation(user, vp);
    }

    public void UserService() {
        this.userRepository = new UserRepository();
    }

    private void validateUsername(String username) throws InvalidInputException {
        if(username.isEmpty())
            throw new InvalidInputException();
    }

    private void validatePassword(String password) throws PasswordTooShortException {
        if (password.length() < 7)
            throw (new PasswordTooShortException());
    }

    private void validateNumber(Integer p) throws LimitOfPeopleReachedException {
        if (p.equals(0)) throw new LimitOfPeopleReachedException();
    }


}
