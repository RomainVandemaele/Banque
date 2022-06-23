package be.bf.banque;

import be.bf.banque.models.Account;
import be.bf.banque.models.AccountOwner;
import be.bf.banque.models.Bank;
import be.bf.banque.models.SavingAccount;
import be.bf.banque.ui.BanqueInterface;
import be.bf.banque.utils.Config;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.metamodel.Metamodel;
import org.hibernate.*;
import org.hibernate.Cache;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.stat.Statistics;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class Main {

    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //System.out.println(Config.Db.getUrl());
        BanqueInterface bi = new BanqueInterface();
        bi.menu();

//        final int n = 6;
//
//        System.out.println("Operation (del|with|dep + account number + [amount] )");
//        Scanner myScanner = new Scanner(System.in);
//        //myScanner.next("(del|wit|dep) *[0-"+n+"]");
//        String pattern = "(del|wit|dep) *[0-"+n+"]"+" *([0-9]*){0,1}";
//        String input = "";
//        while ( ! input.matches(pattern) ) {
//            System.out.println("Your choice ?");
//            input = myScanner.nextLine();
//        }


    }

//
//
//
//
//
//
//
//    public static Titulaire creerUtilisateur() {
//        System.out.println("Bienvenue dans la banque Picsou SARL");
//        System.out.println("Quel est votre nom ?");
//        String nom = myScanner.nextLine();
//        System.out.println("Quel est votre Prenom ?");
//        String prenom = myScanner.nextLine();
//        System.out.println("Quel est votre date de naissance dd/mm/yyyy ?");
//        String dateS = myScanner.nextLine();
//        LocalDate birthday = LocalDate.parse(dateS, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//        System.out.println("Merci pour vos infos, nous vous avons créer un profil chez nous.");
//        return new Titulaire(nom,prenom,birthday);
//    }
//
//
//
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
