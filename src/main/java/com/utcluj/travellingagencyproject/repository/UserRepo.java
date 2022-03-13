package com.utcluj.travellingagencyproject.repository;


import com.utcluj.travellingagencyproject.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class UserRepo {

    private EntityManagerFactory entityManagerFactory ;
    private EntityManager em ;

    public User getUser(){
        return  null;
    }

    public User getUserByUsernameAndPassword (String username, String password) throws NoResultException {

        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }

        return  em.createQuery("SELECT u FROM User u WHERE u.username =:username AND u.password = :password", User.class).
                        setParameter("password", password).
                        setParameter("username", username).
                        getSingleResult();
    }

    public void register(User user){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
        em.persist(user); // insert in database
        em.getTransaction().commit();
        em.close();
        System.out.println("User inserted!");
    }
    public void closeTransaction(){
        em.close();
    }
    public UserRepo(){
       this.entityManagerFactory = Persistence.createEntityManagerFactory("TravellingAgency");
        this.em =  entityManagerFactory.createEntityManager();
    }
}
