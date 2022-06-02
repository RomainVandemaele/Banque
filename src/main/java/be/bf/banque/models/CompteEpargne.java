package be.bf.banque.models;

import java.time.LocalDate;

/** Represente un ocmpte épargne
 * FA CompteEpargne{numero,solde}
 *
 * @attribute dateDernierRetrait LocalDate
 *
 * @invariant dateDernierRetrait
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

    @Override
    public void retrait(double montant) {
        if(this.getSolde()-montant < 0 ) return;
        super.retrait(montant);
        dateDernierRetrait = LocalDate.now();
    }

    public LocalDate getDateDernierRetrait() {
        return dateDernierRetrait;
    }
}

//Class Compte reunissant le comportement global des comptes
//Faire en sorte que le programme continue de tourner
