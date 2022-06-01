package be.bf.banque;

import be.bf.banque.models.CompteCourant;
import be.bf.banque.models.Titulaire;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args)  {
        Titulaire titulaire = new Titulaire("Vandemaele","Romain", LocalDate.of(1994,10,9));
        CompteCourant compte = new CompteCourant("BE05412498741254",0,20,titulaire);
//        Titulaire titulaire = new Titulaire();
//        titulaire.setNom("Vandemaele")
//                .setPrenom("Romain")
//                .setDateDeNaissance(LocalDate.of(1994,10,9));
//
//        CompteCourant compte = new CompteCourant();

        compte.depot(50.5);
        System.out.println(compte.getSolde());
        compte.retrait(20.5);
        System.out.println(compte.getSolde());
        compte.setTitulaire(titulaire);
        System.out.println(compte.getTitulaire().getAge());

        String s = "ok";
    }

//    public static int add(int... termes) { //termes is an array
//        int total =0;
//        for(int it : termes) {
//            total += it;
//        }
//        return total;
//    }

}
