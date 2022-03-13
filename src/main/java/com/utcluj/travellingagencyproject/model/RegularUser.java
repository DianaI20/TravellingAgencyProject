package com.utcluj.travellingagencyproject.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RegularUser extends User {

    @Id
    private String username;
    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String mailAddress;

    @Column
    private String phoneNumber;
}
