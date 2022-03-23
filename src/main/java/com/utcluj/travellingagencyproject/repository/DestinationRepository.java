package com.utcluj.travellingagencyproject.repository;

import com.utcluj.travellingagencyproject.model.Destination;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DestinationRepository {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;

    public void deleteDestination(Destination dst) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.remove(dst);
        // em.createQuery("DELETE FROM Destination d WHERE d.id = :id").setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.clear();

    }

    public void insertDestination(Destination destination) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(destination); // insert in database
        em.getTransaction().commit();
        System.out.println("Destination added! ");
    }

    public List<Destination> getAllDestinations() {
        em.clear();
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        return em.createQuery("SELECT dst FROM Destination dst").getResultList();

    }

    public Destination getDestinationById(Long id) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        return (Destination) em.createQuery("SELECT dst FROM Destination dst WHERE dst.id =:id ").
                    setParameter("id", id).
                    getSingleResult();
    }

    public void closeTransaction() {
        em.close();
    }

    public DestinationRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("TravellingAgency");
        this.em = entityManagerFactory.createEntityManager();
    }
}
