package be.bf.banque;

import be.bf.banque.models.AccountOwner;
import be.bf.banque.models.Bank;
import be.bf.banque.ui.BanqueInterface;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
        EntityManager em = emf.createEntityManager(); //standard JPA persistent context
        Session session = em.unwrap(Session.class); //Session is the entituManager for Hibernate

        //Add all the list  as managed entities in session so update are automatically send to DB
        List<AccountOwner> titulaires = em.createQuery("SELECT o from AccountOwner o", AccountOwner.class).getResultList();

        //AccountOwner accountOwner =  titulaires.stream().filter( p -> p.getId()==4).findFirst().get();
        //accountOwner.setBirthday(1992,4,1);

        AccountOwner owner = new AccountOwner("900508-45216","Ricardo","Rutabare");
        owner.setBirthday(1997,2,19);
        owner.setId(2L);
        //em.persist(owner);
        session.merge(owner); //for detached ( existing but not managed)

        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();

        AccountOwner owner2 = new AccountOwner();
        em.persist(owner2);

        entityTransaction.commit();

        //session.persist(owner);//if element does not already exists (transient)

        //em.merge(owner); //for detached ( existing but not managed) AUTO UPDATE
        //em.persist(owner);//if element does not already exists (transient) INSERT
        //em.detach(owner); //remove from managed entity NOT AUTO UPDATE
        //em.remove(owner); //delete entity from dDB DELETE

//        Transaction transaction = session.getTransaction();
//        transaction.begin();
//        transaction.commit();
//


        em.close();


        Bank b = new Bank();

        b.setName("PISCOU SARL");
        System.out.println(b.getName());


        //Main.class.getName().
//        Class.forName("org.sqlite.JDBC");
//        String DB_PATH = Main.class.getClassLoader().getResource("bank.sqlite3").toString();
//        //final String PATH  = "/home/rvdemael/IdeaProjects/Banque/src/main/resources/bank.sqlite3";

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
//    public static void jpaTest() {
//        EntityManagerFactory entityManagerFactory = null;
//        EntityManager entityManager = null;
//        try {
//            entityManagerFactory = Persistence.createEntityManagerFactory("jpa-demo");
//            entityManager = entityManagerFactory.createEntityManager();
//
//            System.out.println("- Lecture de tous les titulaire -----------");
//
//            List<Titulaire> titulaires = entityManager.createQuery("from Titulaire", Titulaire.class)
//                    .getResultList();
//            for (Titulaire titulaire : titulaires) {
//                System.out.println(titulaire);
//            }
//        }finally {
//            if ( entityManager != null ) entityManager.close();
//            if ( entityManagerFactory != null ) entityManagerFactory.close();
//        }
//    }



}
