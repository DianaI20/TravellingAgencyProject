package com.utcluj.travellingagencyproject.repository;


import com.utcluj.travellingagencyproject.model.User;
import com.utcluj.travellingagencyproject.model.VacationPackage;

import javax.persistence.*;

public class UserRepository {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;

    public User getUserByUsernameAndPassword(String username, String password) throws NoResultException {

        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }

        return em.createQuery("SELECT u FROM User u WHERE u.username =:username AND u.password = :password", User.class).
                setParameter("password", password).
                setParameter("username", username).
                getSingleResult();
    }

    public User getUserByUsername(String username) throws NoResultException {

        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        return em.find(User.class, username);
    }

    public void insertNewUser(User user) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(user); // insert in database
        em.getTransaction().commit();
    }

    public void bookVacation(User user, VacationPackage vp) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        User b = em.find(User.class, user.getUsername());
        b.getBookedVacationPackages().add(vp);
        em.getTransaction().commit();
    }

    public void closeTransaction() {
        em.close();
    }

    public UserRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("TravellingAgency");
        this.em = entityManagerFactory.createEntityManager();
    }
}
