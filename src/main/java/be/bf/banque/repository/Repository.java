package be.bf.banque.repository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public abstract class Repository {

    private EntityManagerFactory emFactory;
    EntityManager em;

    protected void connect() {
        this.emFactory = Persistence.createEntityManagerFactory("jpa-demo");
        this.em = this.emFactory.createEntityManager();
    }

    protected abstract <T> ArrayList<T> findAll() ;


    protected void closeConnect() {
        this.em.flush();
        this.em.clear();
        this.em.close();
    }


}
