package com.utcluj.travellingagencyproject.service;

import com.utcluj.travellingagencyproject.model.VacationPackage;
import com.utcluj.travellingagencyproject.repository.VacationPackageRepo;

import java.util.List;

public class VacationPackageService {
    private VacationPackageRepo vpr;


    public void deleteVacationPackage(Long id){
        vpr.deleteVacationPackage(id);
    }

    public void addVacationPackage(VacationPackage vp){
        vpr.insertVacationPackage(vp);

    }
    public void editVacationPackagee(VacationPackage vp){
        vpr.updateVacationPackage(vp);

    }

    public List<VacationPackage> getAllVacationPackages(){
        return vpr.getAllVacationPackages();
    }

    public VacationPackageService(){
        this.vpr = new VacationPackageRepo();
    }
}
