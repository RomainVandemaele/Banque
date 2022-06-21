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

//        //Query jpqlQuery = this.em.createQuery("select a FROM AccountOwner a where a.id=:id ");
//        TypedQuery<AccountOwner> typedQuery = this.em.createQuery("select a FROM AccountOwner a where a.id=:id",AccountOwner.class);
//        //jpqlQuery.setParameter("id",id);
//        typedQuery.setParameter("id",id);
//        AccountOwner owner = typedQuery.getSingleResult();
//
//        Query namedQuery = this.em.createNamedQuery("AccountOwner.findByOwnerId");
//        namedQuery.setParameter("ownerId",id);
//        owner = (AccountOwner) namedQuery.getSingleResult();






}
