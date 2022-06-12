package be.bf.banque;

import be.bf.banque.models.*;
import be.bf.banque.repository.CompteRepository;
import be.bf.banque.repository.TitulaireRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {

    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Main.class.getName().
        Class.forName("org.sqlite.JDBC");
        String DB_PATH = Main.class.getClassLoader().getResource("bank.sqlite3").toString();

        System.out.printf("%s\n",DB_PATH);

        final String PATH  = "/home/rvdemael/IdeaProjects/Banque/src/main/resources/bank.sqlite3";
        ArrayList<Titulaire> titulaires =  new TitulaireRepository(PATH).findAll();
        titulaires.stream().forEach(System.out::println);
        ArrayList<Compte> comptes = new CompteRepository(PATH).findAll();
        comptes.stream().forEach(System.out::println);


    }



    public static Titulaire creerUtilisateur() {
        System.out.println("Bienvenue dans la banque Picsou SARL");
        System.out.println("Quel est votre nom ?");
        String nom = myScanner.nextLine();
        System.out.println("Quel est votre Prenom ?");
        String prenom = myScanner.nextLine();
        System.out.println("Quel est votre date de naissance dd/mm/yyyy ?");
        String dateS = myScanner.nextLine();
        LocalDate birthday = LocalDate.parse(dateS, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Merci pour vos infos, nous vous avons créer un profil chez nous.");
        return new Titulaire(nom,prenom,birthday);
    }




}
