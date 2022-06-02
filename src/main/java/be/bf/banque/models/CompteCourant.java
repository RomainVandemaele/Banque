package be.bf.banque.models;

import be.bf.banque.models.Titulaire;

/**
 * represente un compte courant pouvant aller en négatif lié à un titulaire
 * FA: CompteCourant{numero,solde,ligneDeCredit}
 * @attribute ligneDeCredit double
 *
 * @invariant ligneDeCredit >= 0
 * @invariant solde > - ligneDeCredit
 * @see be.bf.banque.models.Compte
 */
public class CompteCourant extends  Compte{


    private double ligneDeCredit;



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
        this.setLigneDeCredit(0);
    }

    public CompteCourant(CompteCourant compte) {
        super(compte.getNumero(),compte.getTitulaire(), compte.getSolde());
        this.setLigneDeCredit(compte.ligneDeCredit);
    }

    public CompteCourant(Compte compte) {
        super(compte.getNumero(),compte.getTitulaire(), compte.getSolde());
        this.setLigneDeCredit(0);
    }


    public CompteCourant setLigneDeCredit(double ligneDeCredit) {
        if(ligneDeCredit < 0) return this;
        this.ligneDeCredit = ligneDeCredit;
        return this;
    }

    public double getLigneDeCredit() {
        return ligneDeCredit;
    }

    /**
     * @see Compte#retrait(double)
     * @param montant > 0
     * @modify solde si solde-montant > -ligneDeCredit
     */
    @Override
    public void retrait(double montant) {
        if(this.getSolde()-montant < -this.ligneDeCredit) return;
        super.retrait(montant);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompteCourant{");
        sb.append(super.toString());
        sb.append("ligneDeCredit=").append(ligneDeCredit);
        sb.append('}');
        return sb.toString();
    }
}
