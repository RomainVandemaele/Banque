package be.bf.banque;

import be.bf.banque.models.*;
import be.bf.banque.ui.BanqueInterface;
import be.bf.banque.utils.Config;
import com.google.gson.Gson;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.metamodel.Metamodel;
import org.hibernate.*;
import org.hibernate.Cache;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.stat.Statistics;
import org.json.JSONObject;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

//import static method //can use the method as if it was part of the class

public class Main {
    //signature main(String[] args)
    //prototype public static void main(String[] args)

    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //System.out.println(Config.Db.getUrl());
        //BanqueInterface bi = new BanqueInterface();
        //bi.menu();

        Bank b = new Bank("Picsou");
        b.add(new CurrentAccount("BE12 1234 1234 1234",new AccountOwner(),10,25));
        b.get("BE12 1234 1234 1234").ifPresent(a -> a.withdraw(27));
        //new Account.AccountBuilder().balance(100.5).

    }



    public static void jpaTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
        EntityManager em = emf.createEntityManager(); //standard JPA persistent context
        //Session session = em.unwrap(Session.class); //Session is the entituManager for Hibernate
        //Add all the list  as managed entities in session so update are automatically send to DB
        //List<AccountOwner> titulaires = em.createQuery("SELECT o from AccountOwner o", AccountOwner.class).getResultList();
        //AccountOwner accountOwner =  titulaires.stream().filter( p -> p.getId()==4).findFirst().get();
        //accountOwner.setBirthday(1992,4,1);
        //owner.setId(2L);
        //session.merge(owner); //for detached ( existing but not managed)
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();

        AccountOwner owner = new AccountOwner(AccountOwner.generateSSIN(),"Ovyn","Flavian");
        owner.setBirthday(1991,7,19);
        owner.setId(4L);
        em.merge(owner);

        //SavingAccount sa = new SavingAccount("BE 1234 1234 1234",owner,100);
        //em.persist(sa);

        entityTransaction.commit();
        emf.close();
        em.close();

//        em.persist(owner);
//        entityTransaction.commit();

        //session.persist(owner);//if element does not already exists (transient)

        //em.merge(owner); //for detached ( existing but not managed) AUTO UPDATE
        //em.persist(owner);//if element does not already exists (transient) INSERT
        //em.detach(owner); //remove from managed entity NOT AUTO UPDATE
        //em.remove(owner); //delete entity from dDB DELETE

//        Transaction transaction = session.getTransaction();
//        transaction.begin();
//        transaction.commit();
//
        //em.flush(); //write all modification of managed enitites to DB
        //em.clear() // Detach all maneged entities

        //entityManager.find(User.class, userId);
//        emf.close();
//        em.close();
    }



}
