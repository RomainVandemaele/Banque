package be.bf.banque.models;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Représente une Banque qui a des comptes
 * FA Banque(nom,nombre de Comptes)
 *
 * @attribute nom nom de la banque
 * @attribute comptes Map key = String && value = CompteCourant
 *
 * @invariant nom !=null &&  >0
 * @comptes comptes!=null
 */
public class Banque {
    private String nom;
    private Map<String,Compte> comptes = new HashMap<String,Compte>();

    public Banque(String nom) {
        this.setNom(nom);
    }

    private Banque setNom(String nom) {
        if(nom==null) return this;
        this.nom = nom;
        return this;
    }

    public String getNom() {
        return this.nom;
    }

    public Optional<Compte> get(String numero) {
        if (!this.containsAccount(numero)) return Optional.empty();
        return  Optional.of( this.comptes.get(numero));
    }

    /**
     * Fonction permttant l'ajout d'un compte dans la banque
     * @param compte compte à ajouter !=null
     * @return this
     * @modify comptes tq comptes/.length = comptes.length+1
     */
    public Banque add(Compte compte) {
        if(compte==null) return this;
        if(this.containsAccount(compte.getNumero())) return  this;
        this.comptes.put(compte.getNumero(),compte);
        return this;
    }

    private boolean containsAccount(String numero) {
        return this.comptes.containsKey(numero);
    }

    /**
     * onction permettant la récupération d'un compte s'il existe
     * @param numero String numero de compte à enlever si exixtant
     * @return CompteCourant tq existe avec le numéro sinon null
     * @modify compte avec numéro est enlevé de comptes si existant
     */
    public Banque remove(String numero) {
        if(!this.containsAccount(numero)) return this;
        this.comptes.remove(numero);
        return this;
    }


    public Map.Entry<String, Compte>[] getComptes() {
        Map<String, Compte> copy = new HashMap<String, Compte>();
        for(Map.Entry<String, Compte> entry: this.comptes.entrySet()) {
            copy.put(entry.getKey(), new CompteCourant(entry.getValue()));
        }
        return copy.entrySet().toArray(new Map.Entry[0]);
    }


}
