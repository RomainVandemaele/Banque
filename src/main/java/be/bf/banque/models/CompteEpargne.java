package be.bf.banque.models;

import java.time.LocalDate;

/** Represente un ocmpte épargne
 * FA CompteEpargne{numero,solde,dateDernierRetrait}
 *
 * @attribute dateDernierRetrait LocalDate
 *
 * @invariant dateDernierRetrait
 * @invariant solde >=0
 * @see be.bf.banque.models.Compte 
 */
public class CompteEpargne extends Compte {

    private LocalDate dateDernierRetrait;

    public  CompteEpargne(String numero,Titulaire titulaire)  {
        super(numero,titulaire);
    }

    public  CompteEpargne(String numero,Titulaire titulaire,double solde)  {
        super(numero,titulaire,solde);
    }

    public CompteEpargne(CompteEpargne compte) {
        super(compte.getNumero(),compte.getTitulaire(),compte.getSolde());
    }

    public CompteEpargne(Compte compte) {
        super(compte.getNumero(),compte.getTitulaire(), compte.getSolde());
    }

    public LocalDate getDateDernierRetrait() {
        return dateDernierRetrait;
    }

    /**
     * Redefinition retrait pour verifier si solde >=0 propre
     * @param montant > 0
     * @modify solde = solde-montant ssi solde-montant >0
     * @see Compte#retrait(double) 
     */
    @Override
    public void retrait(double montant) {
        if(this.getSolde()-montant < 0 ) return;
        super.retrait(montant);
        this.dateDernierRetrait = LocalDate.now();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompteEpargne{");
        sb.append(super.toString());
        sb.append("dateDernierRetrait=").append(dateDernierRetrait);
        sb.append('}');
        return sb.toString();
    }
}

