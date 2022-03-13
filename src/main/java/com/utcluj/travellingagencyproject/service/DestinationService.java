package com.utcluj.travellingagencyproject.service;

import com.utcluj.travellingagencyproject.model.Destination;
import com.utcluj.travellingagencyproject.repository.DestinationRepo;

import java.util.List;

public class DestinationService {

    private DestinationRepo dp;

    public void addDestination(Destination destination){
        dp.insertDestination(destination);
    }
    public void deleteDestination(Long id){
        dp.deleteDestination(id);
    }

    public List<Destination> getAllDestinations(){
        return dp.getAllDestinations();
    }
    public DestinationService() {
        this.dp = new DestinationRepo();
    }
}
