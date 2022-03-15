package com.utcluj.travellingagencyproject.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    private String username;

    @Column(name = "user_type")
    private UserType userType;

    @Column
    private String password;

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable( name = "booked_vacations",
                joinColumns =  @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "vacationPck_id"))
    private List<VacationPackage> bookedVacationPackages;

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

    public List<VacationPackage> getBookedVacationPackages() {
        return bookedVacationPackages;
    }

    public User(String username, UserType userType, String password) {
        this.username = username;
        this.userType = userType;
        this.password = password;
        this.bookedVacationPackages = new ArrayList<>();
    }

    public User() {
        this.bookedVacationPackages = new ArrayList<>();

    }
}


