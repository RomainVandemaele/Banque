package be.bf.banque.repository;

import jakarta.persistence.*;

import java.util.ArrayList;

public abstract class Repository {

    private EntityManagerFactory emFactory;
    private EntityManager em;
    private EntityTransaction et;

    public Repository() {
        this.connect();
    }

    public EntityManager getEM() {
        return this.em;
    }

    protected Query createNamedQuery(String name) {
        return this.em.createNamedQuery(name);
    }

    protected void connect() {
        this.emFactory = Persistence.createEntityManagerFactory("jpa-demo");
        this.em = this.emFactory.createEntityManager();
    }

    protected void startTransaction() {
        this.et = em.getTransaction();
        this.et.begin();
    }

    protected void commitTransaction(boolean rollback) {
        if(rollback) {this.et.rollback();}
        else {this.et.commit();}
    }


    public void closeConnect() {
        //this.em.flush();
        this.em.clear();
        this.em.close();
    }
}



