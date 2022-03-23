package com.utcluj.travellingagencyproject.repository;


import com.utcluj.travellingagencyproject.model.VacationPackage;

import javax.persistence.*;
import java.util.List;

public class VacationPackageRepository {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;


    public void insertVacationPackage(VacationPackage vacationPackage) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(vacationPackage); // insert in database
        em.getTransaction().commit();
        System.out.println("Vacation package added!");
    }

    public List<VacationPackage> getAllVacationPackages() {
        // The EntityManager.createQuery and EntityManager.createNamedQuery methods are used to query the datastore by using Java Persistence query language queries.

//        if(!em.getTransaction().isActive()){
//            em.getTransaction().begin();
//        }
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            System.out.println("Vacation package returned!");
            return em.createQuery("SELECT vp FROM VacationPackage vp").getResultList();
        } catch (NoResultException e) {
            System.out.println("No Vacation Package found");
        }

        em.getTransaction().commit();
        em.close();
        return null;
    }

    public List<VacationPackage> getAvailableVacationPackages() {
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        return em.createQuery("SELECT vp FROM VacationPackage vp WHERE vp.status = 1 OR vp.status = 2").getResultList();

    }

    public void deleteVacationPackage(Long id) {

        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        // set parameter will tell what the parameter ":id" will be replaced with
        // in order to get the modifications done, we have to execute executeUpdate() method.

        em.createQuery("DELETE FROM VacationPackage vp WHERE vp.id =:id").setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
        System.out.println("Vacation package with id " + id + " deleted");
    }

    public void updateVacationPackage(VacationPackage vacationPackage) {

        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
//        em.createQuery("UPDATE VacationPackage vp SET vp.name = :name, vp.destination = :destination, vp.price = :price, vp.noMaximumSeats = :noMax, vp.startingDate = :sDate, vp.endingDate =:eDate WHERE vp.id = :id").
//                                                                                                        setParameter("id", vacationPackage.getId()).
//                                                                                                        setParameter("name", vacationPackage.getName()).
//                                                                                                        setParameter("destination", vacationPackage.getDestination()).
//                                                                                                        setParameter("price", vacationPackage.getPrice()).
//                                                                                                        setParameter("noMax", vacationPackage.getNoMaximumSeats()).
//                                                                                                        setParameter("sDate", vacationPackage.getStartingDate()).
//                                                                                                        setParameter("eDate", vacationPackage.getEndingDate()).
//                                                                                                        executeUpdate();

        em.getTransaction().commit();
        em.clear(); // clear cache pretty much
        System.out.println("Vacation package with id " + vacationPackage.getId() + " updated");
        em.close();
    }

    public VacationPackageRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("TravellingAgency");
        this.em = entityManagerFactory.createEntityManager();
    }

}
