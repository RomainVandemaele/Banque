package be.bf.banque;

import be.bf.banque.models.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;


public class Main {

    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {
        //type declaré, type effectif Compte = CompteEpargne
        Titulaire titulaire = new Titulaire("Vandemaele", "Romain", LocalDate.of(1994, 10, 9));
        CompteCourant compteC = new CompteCourant("BE43 0689 9999 9501", titulaire, 100, 20);
        CompteCourant compteC2 = new CompteCourant("BE43 0689 9999 9502", titulaire, 100, 20);

        Titulaire titulaire2 = new Titulaire("Bandemaele", "Romain", LocalDate.of(1993, 10, 9));
        CompteEpargne compteE = new CompteEpargne("BE 1234 1234 1234",titulaire2,1000);
        CompteEpargne compteE2 = new CompteEpargne("BE 1234 1234 1235",titulaire2,100);
        Banque b = new Banque("Picsou");
        //b.add(compteC).add(compteE).add(compteE2).add(compteC2);


    }

    public static void interface() {

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
