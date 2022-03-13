package com.utcluj.travellingagencyproject.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL) //  the mapping is done by passing as parameter the name of the attribute inside the Vacation Package class
    private List<VacationPackage> vacationPackages;
}
