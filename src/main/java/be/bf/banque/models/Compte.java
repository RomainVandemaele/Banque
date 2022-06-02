package be.bf.banque.models;

import java.util.Objects;

/**
 * Représente un compte bancaire
 * FA: CompteCourant{numero,solde,ligneDeCredit}
 * @attribute numero {@link String String}
 * @attribute solde {@link double double}
 * @attribute titulaire {@link Titulaire Titulaire}
 *
 * @invariant numero !=null &&  Format IBAN "BEXX XXXX XXXXX XXXX"
 * @invariant titulaire !=null
 */
public class Compte {
    private String numero;
    private Titulaire titulaire;
    private double solde;

    public Compte(String numero,Titulaire titulaire) {
        this.setNumero(numero);
        this.setTitulaire(titulaire);
        this.solde = 0;
    }

    public Compte(String numero,Titulaire titulaire,double solde) {
        this(numero,titulaire);
        this.solde = solde;
    }

    public  Compte(Compte compte) {
        this(compte.numero,compte.titulaire,compte.solde);
    }

    /**
     * Procédure prmettant de soustraire un montant au solde
     * @param montant > 0
     * @modify this.solde  | this.solde = this.solde - montant ssi solde-montant >= - ligneDeCredit
     */
    public void retrait(double montant) {
        if(montant <= 0 ) return; //prog defensive return + rapide que if-else
        this.solde -= montant;
        //setSolde(this.solde-montant);
    }

    /**
     * Procédure permettant d'ajouter un montant au solde
     * @param montant > 0
     * @modify this.solde  & this.solde = this.solde + montant
     */
    public void depot(double montant) {
        if(montant <=0) return;
        this.solde +=montant;
        //setSolde(this.solde+montant);
    }

    public String getNumero() {
        return numero;
    }

    /**
     * Fonction intene pour initialiser le numéro de compte
     * @param numero String
     * @return this
     * @modify numero si respecte le format
     */
    private Compte setNumero(String numero) {
        if(numero.length()!=19) return this;
        if(!this.checkIBAN(numero)) return this;
        this.numero = numero;
        return this;
    }

    /**
     * Fonction permettant de vérifier si le numéo de compte respecte le format IBAN
     * @param numero String
     * @return true si IBAN respecté false sinon
     */
    private boolean checkIBAN(String numero) {
        StringBuilder sb = new StringBuilder();
        String copy = numero;
        // IBAN validation
        copy = copy.replace(" ","");
        String countryCode = numero.substring(0,2);
        sb.append( String.valueOf ( (int) countryCode.charAt(0)  -55 ) );
        sb.append( String.valueOf ( (int) countryCode.charAt(1)  -55 ) );
        copy = copy.substring(4,copy.length()) + sb.toString() + copy.substring(2,4);
        long mod = Long.valueOf(copy)%97;
        return mod==1;
    }

    public double getSolde() {
        return this.solde;
    }

//    private Compte setSolde(double montant) {
//        this.solde = montant;
//        return this;
//    }


    final public Titulaire getTitulaire() {
        return new Titulaire(titulaire);
    }

    public Compte setTitulaire(Titulaire titulaire) {
        if(titulaire==null) return this;
        this.titulaire = new Titulaire(titulaire);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return numero.equals(compte.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Compte{");
        sb.append("titulaire='").append(titulaire).append('\'');
        sb.append("numero='").append(numero).append('\'');
        sb.append(", solde=").append(solde);
        sb.append('}');
        return sb.toString();
    }
}
