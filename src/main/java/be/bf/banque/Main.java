package be.bf.banque;

import be.bf.banque.models.CompteCourant;
import be.bf.banque.models.Titulaire;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws Exception {
        Titulaire titulaire = new Titulaire("Vandemaele","Romain", LocalDate.of(1994,10,9));
        CompteCourant compte = new CompteCourant("BE05412498741254",0,20,titulaire);
        System.out.println(compte);
        compte.depot(50.5);
        System.out.println(compte);
        try {
            compte.retrait(72.6);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println(compte);
        }
    }

}
