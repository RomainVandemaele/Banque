package be.bf.banque.repository;

import be.bf.banque.models.AccountOwner;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class AccountOwnerRepository  extends Repository {

    public AccountOwnerRepository() {
        super();
    }


    public void insert(AccountOwner accountOwner) {
        this.startTransaction();
        try {
            this.getEM().persist(accountOwner);
            this.commitTransaction(false);
        }catch (Exception e) {
            this.commitTransaction(true);
        }
    }

    public void remove(AccountOwner accountOwner) {
        this.startTransaction();
        try {
            this.getEM().persist(accountOwner);
            this.getEM().remove(accountOwner);
            this.commitTransaction(false);
        }catch (Exception e) {
            this.commitTransaction(true);
        }
    }

    public List<AccountOwner> findAll() {
        return this.createNamedQuery("AccountOwner.findAll").getResultList();
    }

    public AccountOwner findById(Long id) {
        Query query =  this.createNamedQuery("AccountOwner.findById");
        query.setParameter("id",id);
        return (AccountOwner) query.getSingleResult();
    }

    //findBySSIN
    public AccountOwner findBySSIN(String ssin) {
        Query query =  this.createNamedQuery("AccountOwner.findBySSIN");
        query.setParameter("ssin",ssin);
        return (AccountOwner) query.getSingleResult();
    }

}
