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
