package be.bf.banque.models;

import be.bf.banque.models.Titulaire;

/**
 * represente un compte courant pouvant aller en négatif lié à un titulaire
 * FA: CompteCourant{numero,solde,ligneDeCredit}
 * @attribute ligneDeCredit double
 *
 * @invariant ligneDeCredit >= 0
 */
public class CompteCourant extends  Compte{

    private String numero;
    private double solde;
    private double ligneDeCredit;

    private Titulaire titulaire;

    public CompteCourant(String numero, Titulaire titulaire,double solde,double ligneDeCredit) {
        super(numero,titulaire,solde);
        this.setLigneDeCredit(ligneDeCredit);
    }

    public CompteCourant(String numero, Titulaire titulaire,double ligneDeCredit) {
        this(numero,titulaire);
        this.setLigneDeCredit(ligneDeCredit);
    }


    public CompteCourant(String numero, Titulaire titulaire) {
        super(numero,titulaire);
    }

    public CompteCourant(CompteCourant compte) {
        super(compte.numero,compte.titulaire, compte.solde);
        this.setLigneDeCredit(compte.ligneDeCredit);
    }

    public CompteCourant(Compte compte) {
        super(compte.getNumero(),compte.getTitulaire(), compte.getSolde());
    }


    @Override
    public void retrait(double montant) {
        if(this.getSolde()-montant < -ligneDeCredit) return;
        super.retrait(montant);
    }


    public CompteCourant setLigneDeCredit(double ligneDeCredit) {
        if(ligneDeCredit < 0) return this;
        this.ligneDeCredit = ligneDeCredit;
        return this;
    }

    public double getLigneDeCredit() {
        return ligneDeCredit;
    }



}
