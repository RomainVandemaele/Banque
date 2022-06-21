package be.bf.banque.repository;

import be.bf.banque.models.Account;
import be.bf.banque.models.AccountOwner;
import be.bf.banque.models.Bank;
import jakarta.persistence.Query;

import java.util.List;

public class AccountRepository extends Repository {

    public AccountRepository() {
        super();
    }

    public void insert(Account account) {
        this.startTransaction();
        try {
            this.getEM().persist(account);
            this.getEM().flush();
            this.commitTransaction(false);
        }catch (Exception e) {
            this.commitTransaction(true);
        }
    }

    public void remove(Account account) {
        this.startTransaction();
        try {
            this.getEM().persist(account);
            this.getEM().remove(account);
            this.commitTransaction(false);
        }catch (Exception e) {
            this.commitTransaction(true);
        }
    }

    public List<Account> findAll() {
        return this.createNamedQuery("Account.findAll").getResultList();
    }

    public Account findById(Long id) {
        Query query =  this.createNamedQuery("Account.findById");
        query.setParameter("id",id);
        return (Account) query.getSingleResult();
    }

    public List<Account> findByBank(Bank bank) {
        Query query =  this.createNamedQuery("Account.findByBank");
        query.setParameter("bankId",bank);
        return query.getResultList();
    }
    public List<Account> findByOwner(AccountOwner owner) {
        Query query =  this.createNamedQuery("Account.findByOwner");
        query.setParameter("ownerId",owner);
        return query.getResultList();
    }


}
