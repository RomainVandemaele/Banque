package be.bf.banque.models;

import java.time.LocalDate;
import java.time.Period;
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


    public Titulaire() {}
    public Titulaire(String nom,String prenom,LocalDate dateDeNaissance) {
        this(nom,prenom);
        this.setDateDeNaissance(dateDeNaissance);
    }

    public Titulaire(String nom,String prenom,int year,int month,int day) {
        this(nom,prenom);
        this.setDateDeNaissance(year,month,day);
    }

    public Titulaire(String nom,String prenom) {
        this.setNom(nom);
        this.setPrenom(prenom);
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public LocalDate getDateDeNaissance() {
        return this.dateDeNaissance;
    }

    public int getAge() {
        Period p = Period.of(dateDeNaissance.getYear(),dateDeNaissance.getMonthValue(),dateDeNaissance.getDayOfMonth());
        return LocalDate.now().minus(p).getYear();
    }

    public void ajoutCompte(CompteCourant compteCourant) {
        if(compteCourant != null) {
            this.comptesCourant.add(compteCourant);
        }
    }




    private Titulaire setNom(String nom) {
        if(nom==null) return this;
        if(nom.length()==0) return this;
        this.nom = nom;
        return this;
    }

    private Titulaire setPrenom(String prenom) {
        if(nom==null) return this;
        if(nom.length()==0) return this;
        this.prenom = prenom;
        return this;
    }

    public Titulaire setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
        return this;
    }


    public Titulaire setDateDeNaissance(int year,int month,int day) {
        this.dateDeNaissance = LocalDate.of(year,month,day);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Nom : "+nom+", Prenom : "+prenom);
        return sb.toString();
    }
}
