package be.bf.banque.models;

import be.bf.banque.models.Titulaire;

/**
 * represente un compte courant pouvant aller en négatif lié à un titulaire
 * FA: CompteCourant{numero,solde,ligneDeCredit}
 * @attribute numero String
 * @attribute solde double
 * @attribute ligneDeCredit double
 * @attribute titulaire Titulaire
 *
 * @invariant numero !=null &&  Format IBAN "BEXX XXXX XXXXX XXXX"
 * @invariant solde >=-ligneDeCredit throws Exception otherwise
 * @invariant ligneDeCredit >= 0
 * @invariant titulaie !=null
 */
public class CompteCourant {

    private String numero;
    private double solde;
    private double ligneDeCredit;

    private Titulaire titulaire;

    public CompteCourant(String numero,double solde,double ligneDeCredit, Titulaire titulaire) {
        if(numero.length()==19) {
            this.numero = numero;
            this.solde = solde;
            this.ligneDeCredit = ligneDeCredit;
            this.titulaire = titulaire;
        }

    }

    /**
     * Méthode permettant d'ajouter un montant au solde
     * @param montant > 0
     * @modify this.solde  & this.solde = this.solde + montant
     */
    public void depot(double montant) {
        if(montant <=0) return;
        this.solde += montant;
    }

    /**
     * Méthode prmettant de soustraire un montant au solde
     * @param montant > 0
     * @modify this.solde  | this.solde = this.solde - montant ssi solde-montant >= - ligneDeCredit
     */
    public void retrait(double montant)  {
       if(montant <= 0 ) return; //prog defensive return + rapide que if-else
       if( this.solde - montant < -this.ligneDeCredit) return;

       //if(montant >0 && this.solde - montant > -this.ligneDeCredit)

       solde -=montant;
    }

    public void setLigneDeCredit(double ligneDeCredit) {
        this.ligneDeCredit = ligneDeCredit;
    }

    public double getSolde() {
        return solde;
    }

    public String getNumero() {
        return numero;
    }

    public double getLigneDeCredit() {
        return ligneDeCredit;
    }

    public Titulaire getTitulaire() {
        return titulaire;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Solde : ");
        sb.append(solde);
        sb.append("\n");
        return sb.toString();
    }
}
