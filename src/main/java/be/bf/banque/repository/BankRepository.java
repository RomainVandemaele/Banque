package be.bf.banque.repository;

import be.bf.banque.models.AccountOwner;
import be.bf.banque.models.Bank;
import jakarta.persistence.Query;

import java.util.List;

public class BankRepository extends Repository {

    public BankRepository() {
        super();
    }

    public List<Bank> findAll() {
        List<Bank> banks = this.createNamedQuery("Bank.findAll").getResultList();
        AccountRepository accountRepo = new AccountRepository();
        for (Bank bank : banks) {
            bank.loadAccounts( accountRepo.findByBank(bank.getId()) );
        }
        return banks;
    }

    public Bank findById(Long id) {
        Query query =  this.createNamedQuery("Bank.findById");
        query.setParameter("id",id);
        return (Bank) query.getSingleResult();
    }
}
