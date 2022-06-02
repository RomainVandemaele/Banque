package be.bf.banque;

import be.bf.banque.models.Banque;
import be.bf.banque.models.CompteCourant;
import be.bf.banque.models.CompteEpargne;
import be.bf.banque.models.Titulaire;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class Main {



    public static void main(String[] args) {
        Titulaire titulaire = new Titulaire("Vandemaele", "Romain", LocalDate.of(1994, 10, 9));
        CompteCourant compteC = new CompteCourant("BE43 1689 9999 9501", titulaire, 100, 20);
        Banque b = new Banque("Picsou");
        b.add(compteC);
        Optional<CompteCourant> c = b.get("BE43 1689 9999 9500");
        Titulaire titulaire2 = new Titulaire("Bandemaele", "Romain", LocalDate.of(1993, 10, 9));
        CompteEpargne compteE = new CompteEpargne("BE 1234 1234 1234",titulaire2,100);

        System.out.println(compteC);
        compteC.depot(500.3);
        System.out.println(compteC);
        compteC.retrait(620.3);
        System.out.println(compteC.getTitulaire());
        //autoboxing
//        Integer i = 42;
//        int a = i; //unboxing
//        Integer d = a; //boxing
//        //demo(i);
//        //System.out.println(i);
//        System.out.println(titulaire);
//        Titulaire t2 = new Titulaire("Vandemaele","Romain");
//        System.out.printf("%b",titulaire.equals(t2));
    }




}
