package be.bf.banque.repository;

import be.bf.banque.models.AccountOwner;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class AccountOwnerRepository  {
    private EntityManagerFactory emFactory;
    EntityManager em;

    protected void connect() {
        this.emFactory = Persistence.createEntityManagerFactory("jpa-demo");
        this.em = this.emFactory.createEntityManager();
    }

    protected void query() {

    }

    protected List<AccountOwner> findAll() {
        this.em.createQuery("SELECT o from AccountOwner o", AccountOwner.class).getResultList();
        return null;
    }

    protected AccountOwner findById(Long id) {
        //Query jpqlQuery = this.em.createQuery("select a FROM AccountOwner a where a.id=:id ");
        TypedQuery<AccountOwner> typedQuery = this.em.createQuery("select a FROM AccountOwner a where a.id=:id",AccountOwner.class);
        //jpqlQuery.setParameter("id",id);
        typedQuery.setParameter("id",id);
        AccountOwner owner = typedQuery.getSingleResult();

        Query namedQuery = this.em.createNamedQuery("AccountOwner.findByOwnerId");
        namedQuery.setParameter("ownerId",id);
        owner = (AccountOwner) namedQuery.getSingleResult();


        return null;
    }

    protected ArrayList<AccountOwner> findByBank(String bankName) {
        return null;
    }


    protected void closeConnect() {
        this.em.flush();
        this.em.clear();
        this.em.close();
    }
}
