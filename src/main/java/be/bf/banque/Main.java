package be.bf.banque;

import be.bf.banque.models.*;
import be.bf.banque.repository.CompteRepository;
import be.bf.banque.repository.TitulaireRepository;

import java.lang.reflect.Parameter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;



public class Main {

    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //Main.class.getName().
        Class.forName("org.sqlite.JDBC");
        String DB_PATH = Main.class.getClassLoader().getResource("bank.sqlite3").toString();

        //System.out.printf("%s\n",DB_PATH);

        final String PATH  = "/home/rvdemael/IdeaProjects/Banque/src/main/resources/bank.sqlite3";


        hasshMapToParamArray();

//        ArrayList<Titulaire> titulaires =  new TitulaireRepository(DB_PATH).findAll();
//        titulaires.stream().forEach(System.out::println);
//        ArrayList<Compte> comptes = new CompteRepository(DB_PATH).findAll();
//        comptes.stream().forEach(System.out::println);


    }

    public static void hasshMapToParamArray() throws Exception {
        HashMap map = new HashMap();
        map.put("id",1);
        map.put("ssin","901002-456");
        map.put("nom", "Vandemaele");
        map.put("prenom","Romain");

        Parameter[] parameters = Titulaire.class.getConstructor(String.class,String.class).getParameters();
        System.out.println(parameters[0].isNamePresent());
        Arrays.stream(parameters).map( e -> e.getName()).forEach(System.out::println);
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
