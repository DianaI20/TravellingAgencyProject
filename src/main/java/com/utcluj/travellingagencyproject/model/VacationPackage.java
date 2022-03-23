package com.utcluj.travellingagencyproject.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private int noAvailableSeats;

    @Column(name = "np_maximum_seats")
    private int noMaximumSeats;

    @Column
    private LocalDate startingDate;

    @Column
    private LocalDate endingDate;

    @ManyToMany(mappedBy = "bookedVacationPackages", cascade = CascadeType.REMOVE)
    List<User> userList;


    public VacationPackage(String name, float price, Destination destination, int noMaximumSeats, LocalDate startingDate, LocalDate endingDate) {
        this.name = name;
        this.price = price;
        this.status = VacationStatus.NOT_BOOKED;
        this.destination = destination;
        this.noAvailableSeats = noMaximumSeats;
        this.noMaximumSeats = noMaximumSeats;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.userList = new ArrayList<>();
    }

    public VacationPackage(Long id, String name, float price, Destination destination, int noMaximumSeats, LocalDate startingDate, LocalDate endingDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = VacationStatus.NOT_BOOKED;
        this.destination = destination;
        this.noAvailableSeats = noMaximumSeats;
        this.noMaximumSeats = noMaximumSeats;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.userList = new ArrayList<>();
    }

    public VacationPackage(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public VacationPackage() {

    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public VacationStatus getStatus() {
        return status;
    }

    public Destination getDestination() {
        return destination;
    }

    public int getNoAvailableSeats() {
        return noAvailableSeats;
    }

    public int getNoMaximumSeats() {
        return noMaximumSeats;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setNoAvailableSeats(int noAvailableSeats) {
        this.noAvailableSeats = noAvailableSeats;
        this.setStatus();
    }

    public void setStatus() {

        if (noAvailableSeats == noMaximumSeats) {
            this.status = VacationStatus.NOT_BOOKED;
        } else {
            if (noAvailableSeats == 0) {
                this.status = VacationStatus.BOOKED;
            } else {
                this.status = VacationStatus.IN_PROGRESS;
            }
        }
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setNoMaximumSeats(int noMaximumSeats) {
        this.noMaximumSeats = noMaximumSeats;
    }


}
