package com.utcluj.travellingagencyproject.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //  the mapping is done by passing as parameter the name of the attribute inside the Vacation Package class
    private List<VacationPackage> vacationPackages;

    public Destination(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    public Destination() {
    }
}
