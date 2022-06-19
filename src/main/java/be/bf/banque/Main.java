package be.bf.banque;

import be.bf.banque.models.AccountOwner;
import be.bf.banque.ui.BanqueInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class Main {

    static Scanner myScanner = new Scanner(System.in);


    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
        EntityManager em = emf.createEntityManager();

        List<AccountOwner> titulaires = em.createQuery("SELECT o from AccountOwner o", AccountOwner.class).getResultList();
        titulaires.forEach(System.out::println);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(new AccountOwner("Ovyn","Flavian"));
        transaction.commit();
        em.close();

        //Main.class.getName().
//        Class.forName("org.sqlite.JDBC");
//        String DB_PATH = Main.class.getClassLoader().getResource("bank.sqlite3").toString();
//        //final String PATH  = "/home/rvdemael/IdeaProjects/Banque/src/main/resources/bank.sqlite3";
//
//
//        TitulaireRepository tr = new TitulaireRepository();
//        ArrayList<Titulaire> titulaires = tr.findAll();
//        //titulaires.forEach(System.out::println);
//
//        CompteRepository cr = new CompteRepository();
//        ArrayList<Compte> comptes = cr.findAll();
//        //comptes.forEach(System.out::println);
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
