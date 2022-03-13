package com.utcluj.travellingagencyproject.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Vacation_Packages")
public class VacationPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Column
    private String name;

    @Column
    private float price;

    @Column
    private VacationStatus status;

    @ManyToOne
    @JoinColumn(name = "destination_id") // name that will appear in the table
    private Destination destination;

    @Column(name = "no_booked_seats")
    private int noBookedSeats;

    @Column(name = "np_maximum_seats")
    private int noMaximumSeats;

    @Column
    private Date startingDate;

    @Column
    private Date endingDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public VacationPackage(String name, float price) {
        this.name = name;
        this.price = price;
    }
    public VacationPackage(){

    }
}
