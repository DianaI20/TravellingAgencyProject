package com.utcluj.travellingagencyproject.controller;

import com.utcluj.travellingagencyproject.exceptions.InvalidDestinationIdException;
import com.utcluj.travellingagencyproject.exceptions.InvalidInputException;
import com.utcluj.travellingagencyproject.exceptions.InvalidPeriodException;
import com.utcluj.travellingagencyproject.exceptions.LimitOfPeopleReachedException;
import com.utcluj.travellingagencyproject.model.Destination;
import com.utcluj.travellingagencyproject.model.VacationPackage;
import com.utcluj.travellingagencyproject.service.VacationPackageService;

import java.time.LocalDate;
import java.util.List;

public class VacationPackageController {

    private VacationPackageService vps;


    public List<VacationPackage> getAllVacationPackages(){
        return vps.getAllVacationPackages();
    }

    public void deleteVacationPackage(Long id){
        vps.deleteVacationPackage(id);
    }


    public void editVacationPackage(VacationPackage vp, String name, Destination destId, String price, String noOfPeople, LocalDate startingDate, LocalDate endingDate) throws InvalidPeriodException {
       vps.editVacationPackage(vp, name, destId, price, noOfPeople, startingDate, endingDate);
    }

    public void bookVacationPackage(VacationPackage vp) throws LimitOfPeopleReachedException {
        vps.bookVacationPackage(vp);
    }

    public void insertVacationPackage(String name, String price, Destination destination, String noMaximumSeats, LocalDate startingDate, LocalDate endingDate) throws InvalidInputException, InvalidPeriodException {
        vps.addNewVacationPackage( name,  price,  destination,  noMaximumSeats,  startingDate, endingDate);

    }


    public List<VacationPackage> getAvailableVacationPackages(){
      return vps.getAvailableVacationPackages();
    }

    public List<VacationPackage> applyFilters(String higherPrice, String lowerPrice, String dst,LocalDate startingDate, LocalDate endingDate) throws InvalidPeriodException, InvalidInputException {
        return vps.filterVacationPackage(higherPrice,lowerPrice,dst,startingDate,endingDate);
    }

    public VacationPackageController() {
       this.vps = new VacationPackageService();
    }
}
