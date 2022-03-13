package com.utcluj.travellingagencyproject.model;

import javax.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    @OneToOne(mappedBy = "hotel", cascade = CascadeType.DETACH)
    private VacationPackage vacationPackage;
}
