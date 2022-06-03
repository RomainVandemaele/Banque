package be.bf.banque;

import be.bf.banque.models.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;


public class Main {

    public static void main(String[] args) {
        //type declaré, type effectif Compte = CompteEpargne
        Titulaire titulaire = new Titulaire("Vandemaele", "Romain", LocalDate.of(1994, 10, 9));
        CompteCourant compteC = new CompteCourant("BE43 0689 9999 9501", titulaire, 100, 20);
        CompteCourant compteC2 = new CompteCourant("BE43 0689 9999 9502", titulaire, 100, 20);

        Titulaire titulaire2 = new Titulaire("Bandemaele", "Romain", LocalDate.of(1993, 10, 9));
        CompteEpargne compteE = new CompteEpargne("BE 1234 1234 1234",titulaire2,100);
        CompteEpargne compteE2 = new CompteEpargne("BE 1234 1234 1235",titulaire2,100);
        Banque b = new Banque("Picsou");
        b.add(compteC).add(compteE).add(compteE2).add(compteC2);

        System.out.println(b.assetCalculator(titulaire));
        System.out.println(b.assetCalculator(titulaire2));

        b.addInterest();

        System.out.println(b.assetCalculator(titulaire));
        System.out.println(b.assetCalculator(titulaire2));

        //b.get("BE43 0689 9999 9501").ifPresent(System.out::println);
    }




}
