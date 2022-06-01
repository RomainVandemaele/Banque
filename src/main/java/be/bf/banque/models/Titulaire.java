package be.bf.banque.models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class mutable représente un titulaire de compte dans une banque
 * fonction d'abstraction
 * FA titulaire{nom,prénom}
 * @attribute nom Sting
 * @attribute prenom Sting
 * @attribute dateDeNaissance LocalDate
 * @attribute comptesCourant ArrayList
 *
 * @invariant nom!=null and nom.length >0
 * @invariant prenom!=null and prenom.length >0
 * @invariant dateDeNaissance!=null and >= '1900-01-01'
 */
public class Titulaire {

    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    ArrayList<CompteCourant> comptesCourant = new ArrayList<>();

    public Titulaire(String nom,String prenom,LocalDate dateDeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
    }

    public void ajoutCompte(CompteCourant compteCourant) {
        if(compteCourant != null) {
            this.comptesCourant.add(compteCourant);
        }
    }

}
