package com.utcluj.travellingagencyproject.repository;


import com.utcluj.travellingagencyproject.model.VacationPackage;

import javax.persistence.*;
import java.util.List;

public class VacationPackageRepo {
    private EntityManagerFactory entityManagerFactory ;
    private EntityManager em ;



    public void insertVacationPackage(VacationPackage vacationPackage){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
        em.persist(vacationPackage); // insert in database
        em.getTransaction().commit();
        em.close();
        System.out.println("Vacation package added! ");
    }

    public List<VacationPackage> getAllVacationPackages(){
       // The EntityManager.createQuery and EntityManager.createNamedQuery methods are used to query the datastore by using Java Persistence query language queries.
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
        try{
           return em.createQuery(
                           "SELECT vp FROM VacationPackage vp")
                                .getResultList();
        }catch(NoResultException e){
           System.out.println("No Vacation Package found");
       }
        System.out.println("Vacation package returned!");
       em.getTransaction().commit();;
       return null;
    }

    public void deleteVacationPackage(Long id){

        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }try{
            em.createQuery("DELETE FROM VacationPackage vp WHERE vp.id =:id").setParameter("id", id).executeUpdate(); // deletes the entry with id id
            // set parameter will tell what the parameter ":id" will be replaced with
            // in order to get the modifications done, we have to execute executeUpdate() method.
        }catch(NoResultException e){
            System.out.println("No Vacation Package found");
        }
        em.getTransaction().commit();
        em.close();
        System.out.println("Vacation package with id " + id + " deleted");
    }

    public void updateVacationPackage(VacationPackage vacationPackage){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
        em.createQuery("UPDATE VacationPackage vp SET vp.name = :name, vp.destination = :destination WHERE vp.id = :id").
                                                                                                        setParameter("id", vacationPackage.getId()).
                                                                                                        setParameter("name", vacationPackage.getName()).
                                                                                                        executeUpdate();
        em.getTransaction().commit();
        em.close();
        System.out.println("Vacation package with id " + vacationPackage.getId() + " updated");
    }

    public VacationPackageRepo() {
        this.entityManagerFactory =  Persistence.createEntityManagerFactory("com/travellingAgency");
        this.em = entityManagerFactory.createEntityManager();

    }

}
