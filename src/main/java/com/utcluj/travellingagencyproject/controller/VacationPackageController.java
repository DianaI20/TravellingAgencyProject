package com.utcluj.travellingagencyproject.controller;

import com.utcluj.travellingagencyproject.exceptions.InvalidInputException;
import com.utcluj.travellingagencyproject.exceptions.InvalidPeriodException;
import com.utcluj.travellingagencyproject.exceptions.LimitOfPeopleReachedException;
import com.utcluj.travellingagencyproject.model.Destination;
import com.utcluj.travellingagencyproject.model.VacationPackage;
import com.utcluj.travellingagencyproject.service.VacationPackageService;

import java.time.LocalDate;
import java.util.List;

public class VacationPackageController {

    private VacationPackageService vacationPackageService;

    public VacationPackageController() {
        this.vacationPackageService = new VacationPackageService();
    }

    public List<VacationPackage> getAllVacationPackages(){
        return vacationPackageService.getAllVacationPackages();
    }

    public void deleteVacationPackage(Long id){
        vacationPackageService.deleteVacationPackage(id);
    }


    public void editVacationPackage(VacationPackage vp, String name, Destination destId, String price, String noOfPeople, LocalDate startingDate, LocalDate endingDate) throws InvalidPeriodException {
       vacationPackageService.editVacationPackage(vp, name, destId, price, noOfPeople, startingDate, endingDate);
    }

    public void bookVacationPackage(VacationPackage vp) throws LimitOfPeopleReachedException {
        vacationPackageService.bookVacationPackage(vp);
    }

    public void insertVacationPackage(String name, String price, Destination destination, String noMaximumSeats, LocalDate startingDate, LocalDate endingDate) throws InvalidInputException, InvalidPeriodException {
        vacationPackageService.addNewVacationPackage( name,  price,  destination,  noMaximumSeats,  startingDate, endingDate);

    }


    public List<VacationPackage> getAvailableVacationPackages(){
      return vacationPackageService.getAvailableVacationPackages();
    }

    public List<VacationPackage> applyFilters(String higherPrice, String lowerPrice, String dst,LocalDate startingDate, LocalDate endingDate) throws InvalidPeriodException, InvalidInputException {
        return vacationPackageService.filterVacationPackage(higherPrice,lowerPrice,dst,startingDate,endingDate);
    }

}
