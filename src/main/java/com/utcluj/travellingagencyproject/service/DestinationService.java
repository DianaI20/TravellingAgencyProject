package com.utcluj.travellingagencyproject.service;

import com.utcluj.travellingagencyproject.exceptions.EmptyFieldException;
import com.utcluj.travellingagencyproject.model.Destination;
import com.utcluj.travellingagencyproject.repository.DestinationRepository;

import javax.persistence.NoResultException;
import java.util.List;

public class DestinationService {

    private DestinationRepository dp;

    public void addDestination(Destination destination) throws EmptyFieldException {
        validateInput(destination);
        dp.insertDestination(destination);

    }

    public void deleteDestination(Long id) throws NoResultException {
        Destination dst = dp.getDestinationById(id);
        dp.deleteDestination(dst);
    }

    public List<Destination> getAllDestinations() {
        return dp.getAllDestinations();
    }

    private void validateInput(Destination destination) throws EmptyFieldException {
        if (destination.getName().isEmpty()) {
            throw new EmptyFieldException();
        }
    }

    public Destination getDestinationById(Long id) {
        return dp.getDestinationById(id);
    }

    public DestinationService() {
        this.dp = new DestinationRepository();
    }
}
