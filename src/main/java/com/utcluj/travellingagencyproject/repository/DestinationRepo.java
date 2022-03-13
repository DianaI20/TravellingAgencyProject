package com.utcluj.travellingagencyproject.repository;
import com.utcluj.travellingagencyproject.model.Destination;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;

public class DestinationRepo {

    private EntityManagerFactory entityManagerFactory ;
    private EntityManager em ;

    public void deleteDestination(Long id){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
        em.createQuery("DELETE FROM Destination d WHERE d.id = :id").setParameter("id", id).executeUpdate();
        em.getTransaction().commit();

    }

    public void insertDestination(Destination destination){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
        em.persist(destination); // insert in database
        em.getTransaction().commit();
        System.out.println("Destination added! ");
    }

    public List<Destination> getAllDestinations(){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
        try{
            return em.createQuery(
                            "SELECT dst FROM Destination dst")
                    .getResultList();
        }catch(NoResultException e){
            System.out.println("No destination found");
        }
        System.out.println("c returned!");
        em.getTransaction().commit();;
        return null;
    }

    public void closeTransaction(){
        em.close();
    }
    public DestinationRepo() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("TravellingAgency");
        this.em = entityManagerFactory.createEntityManager();
    }
}
