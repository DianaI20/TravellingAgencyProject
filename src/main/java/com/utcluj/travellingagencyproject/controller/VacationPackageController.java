package com.utcluj.travellingagencyproject.controller;

import com.utcluj.travellingagencyproject.model.VacationPackage;
import com.utcluj.travellingagencyproject.service.VacationPackageService;

import java.util.List;

public class VacationPackageController {

    private VacationPackageService vps;

   public List<VacationPackage> getAllVacationPackages(){
        return vps.getAllVacationPackages();
    }

    public void deleteVacationPackage(Long id){
        vps.deleteVacationPackage(id);
    }

    public void editVacationPackage(VacationPackage vp){
       vps.editVacationPackagee(vp);
    }


}
