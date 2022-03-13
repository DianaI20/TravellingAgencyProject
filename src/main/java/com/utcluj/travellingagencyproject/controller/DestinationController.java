package com.utcluj.travellingagencyproject.controller;

import com.utcluj.travellingagencyproject.model.Destination;
import com.utcluj.travellingagencyproject.service.DestinationService;

public class DestinationController {

    private DestinationService ds;

    public void addDestination(Destination d){
        ds.addDestination(d);
    }

    public void deleteDestination(Long id){
        ds.deleteDestination(id);
    }

    public void viewAllDestinations(){
        ds.();
    }
    public DestinationController(){
        this.ds = new DestinationService();
    }
}
