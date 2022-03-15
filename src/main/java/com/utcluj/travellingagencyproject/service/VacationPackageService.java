package com.utcluj.travellingagencyproject.service;

import com.utcluj.travellingagencyproject.exceptions.InvalidInputException;
import com.utcluj.travellingagencyproject.exceptions.InvalidPeriodException;
import com.utcluj.travellingagencyproject.exceptions.LimitOfPeopleReachedException;
import com.utcluj.travellingagencyproject.model.Destination;
import com.utcluj.travellingagencyproject.model.VacationPackage;
import com.utcluj.travellingagencyproject.repository.VacationPackageRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VacationPackageService {
    private VacationPackageRepo vpr;


    public void deleteVacationPackage(Long id) {
        vpr.deleteVacationPackage(id);
    }

    public void addNewVacationPackage(String name, String price, Destination destination, String noMaximumSeats, LocalDate startingDate, LocalDate endingDate)
            throws InvalidInputException, InvalidPeriodException, NumberFormatException {

        if(startingDate == null || endingDate == null || price.isEmpty() || destination == null || noMaximumSeats.isEmpty() || name.isEmpty()){
            throw new InvalidInputException();
        }

        float pr = Float.parseFloat(price);
        int people = Integer.parseInt(noMaximumSeats);

        validateNumber(people);
        validateNumber(pr);

        validateDate(startingDate, endingDate);
        VacationPackage vp = new VacationPackage(name, pr, destination, people, startingDate, endingDate);
        vpr.insertVacationPackage(vp);
    }

    public void  editVacationPackage(VacationPackage vp, String name, Destination dst, String price, String noOfPeople, LocalDate startingDate, LocalDate endingDate)
            throws InvalidPeriodException {

        this.updateVacationPackageFields(vp, name, dst, price, noOfPeople, startingDate, endingDate);
        vpr.updateVacationPackage(vp);

    }

    public void bookVacationPackage(VacationPackage vp) throws LimitOfPeopleReachedException {
        validateNoOfPeople(vp.getNoAvailableSeats());
        vp.setNoAvailableSeats(vp.getNoAvailableSeats() - 1);
        vpr.updateVacationPackage(vp);
    }


    public List<VacationPackage> getAllVacationPackages() {
        return vpr.getAllVacationPackages();
    }

    public List<VacationPackage> getAvailableVacationPackages() {
        return vpr.getAvailableVacationPackages();
    }


    // filters

    public List<VacationPackage> filterVacationPackage(String higherPrice, String lowerPrice, String dst, LocalDate startingDate, LocalDate endingDate)
            throws InvalidPeriodException, InvalidInputException {

        Float h, l;
        List<VacationPackage> vp = getAvailableVacationPackages();
        validateDate(startingDate, endingDate);

        if (startingDate != null) {
            vp = vp.stream().filter(e -> (e.getStartingDate()).equals(startingDate) || e.getStartingDate().isAfter(startingDate)).collect(Collectors.toList());
        }
        if (endingDate != null) {
            vp = vp.stream().filter(e -> (e.getEndingDate()).equals(endingDate) || e.getEndingDate().isBefore(endingDate)).collect(Collectors.toList());
        }

        if (!lowerPrice.isEmpty() && !higherPrice.isEmpty()) {
            l = Float.parseFloat(lowerPrice);
            h = Float.parseFloat(higherPrice);
            validatePrice(l, h);
            vp = vp.stream().filter(v -> (Float.compare(v.getPrice(), l) >= 0 && Float.compare(v.getPrice(), h) <= 0)).collect(Collectors.toList());
        } else {
            if (!lowerPrice.isEmpty()) {
                l = Float.parseFloat(lowerPrice);
                validateNumber(l);
                vp = vp.stream().filter(v -> (Float.compare(v.getPrice(), l) >= 0)).collect(Collectors.toList());
            }
            if (!higherPrice.isEmpty()) {
                h = Float.parseFloat(higherPrice);
                validateNumber(h);
                vp = vp.stream().filter(v -> (Float.compare(v.getPrice(), h) <= 0)).collect(Collectors.toList());
            }
        }
        if(!dst.isEmpty()){
            System.out.println("I am here");
            vp = vp.stream().filter(v -> (v.getDestination().getName().toLowerCase().contains(dst.toLowerCase()))).collect(Collectors.toList());
        }
        return vp;

    }


    private void validateDate(LocalDate startingDate, LocalDate endingDate) throws InvalidPeriodException {
        if (startingDate != null && endingDate != null) {
            if (startingDate.isAfter(endingDate))
                throw new InvalidPeriodException();
        }
    }

    private void validateNumber(Number p) throws InvalidInputException {
        if (p.floatValue() < 0) throw new InvalidInputException();
    }

    private void validatePrice(Float price1, Float price2) throws InvalidInputException {

        if (price1 != null && price2 != null) {
            validateNumber(price1);
            validateNumber(price2);
            if (price1 > price2) {
                throw new InvalidInputException();
            }
        }
    }

    private void validateNoOfPeople(Integer p) throws LimitOfPeopleReachedException {
        if (p.equals(0)) throw new LimitOfPeopleReachedException();
    }

    // updates the new fields
    private void updateVacationPackageFields(VacationPackage vp, String name, Destination dst, String price, String noOfPeople, LocalDate startingDate, LocalDate endingDate) throws InvalidPeriodException {
        if (!name.equals("")) {
            vp.setName(name);
        }
        if (dst != null) {

            vp.setDestination(dst);
        }
        if (!price.isEmpty() ) {
            vp.setPrice(Float.parseFloat(price));
        }
        if (!noOfPeople.isEmpty()) {
            vp.setNoMaximumSeats(Integer.parseInt(noOfPeople));
        }
        if (startingDate != null & endingDate == null) {
            validateDate(startingDate, vp.getEndingDate());
        } else {
            if (startingDate == null & endingDate != null) {
                validateDate(vp.getStartingDate(), endingDate);
            } else {
                validateDate(startingDate, endingDate);
            }
        }

    }


    public VacationPackageService() {
        this.vpr = new VacationPackageRepo();
    }


}
