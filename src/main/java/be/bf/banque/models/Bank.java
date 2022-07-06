package be.bf.banque.models;

import be.bf.banque.IAction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.*;
//import javax.validation.constraints.*;

/**
 * Bank class repsonsible to represents a bank that holds accounts from several owners.
 * It is also an entity of the database
 * @attributes id of the bank
 * @attributes name of the bank
 *
 * @invariant
 */
@Entity
@Table(name = "bank")
@Getter @Setter
@NamedQuery(name = "Bank.findById", query = "SELECT b FROM Bank b WHERE b.id=:id")
@NamedQuery(name = "Bank.findAll", query = "SELECT b FROM Bank b")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY IS AUTOINCREMEHNT
    @NotNull
    @Column(name = "id", nullable = false )
    private Long id;

    @Column(name = "name", nullable = false , length = 32)
    @NotBlank
    @Pattern(regexp ="[a-z A-Z 0-9.]*")
    private String name;

    @Transient
    private HashMap<String,Account> accountsMap = new HashMap<String,Account>();

    public Bank() {this.name = "Picsou";}

    public Bank(String name) { this.setName(name);}



    public String getName() {
        return this.name;
    }

    public Bank setName(String name) {
        if(name==null) return null;
        if (name.isBlank()) return null;
        this.name = name;
        return this;
    }

    public void loadAccounts(List<Account> accounts) {
        this.accountsMap.clear();
        for (Account account : accounts) {
            this.accountsMap.put(account.getNumber(),account);
        }
    }

    public boolean containsAccount(String number) { return this.accountsMap.containsKey(number);}

    /**
     * Fonction that allows to get an account by its number if it exists in the bank
     * @param number String
     * @return account
     */
    public Optional<Account> get(String number) {
        if (!this.containsAccount(number)) return Optional.empty();
        return Optional.of(this.accountsMap.get(number));
    }

    public ArrayList<Account> get(AccountOwner accountOwner) {
        ArrayList<Account> accounts = new ArrayList<>();
        for(Account a: this.accountsMap.values()) {
            if (!a.getAccountOwner().equals(accountOwner)) continue;
            accounts.add(a);
        }
        return accounts;
    }

    public Bank add(Account account) {
        if( this.containsAccount(account.getNumber()) ) return this;
        account.setGoingInNegative(this::triggerNegativeAccountEvent);
        this.accountsMap.put(account.getNumber(),account);
        return this;
    }

    public Bank remove(String number) {
        if( !this.containsAccount( number ) ) return this;
        this.accountsMap.remove(number);
        return this;
    }

    public Map.Entry<String,Account>[] getAccountsMap() {
        Map<String,Account> copy = new HashMap<String,Account>();
        for (Map.Entry<String, Account> entry : this.accountsMap.entrySet()) {
            copy.put(entry.getKey(),entry.getValue());
        }
        return copy.entrySet().toArray(new Map.Entry[0]);
    }



    public void applyInterest() {
        for (Account a : this.accountsMap.values()) {
            a.applyInterst();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bank{");
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void applyInterest(AccountOwner accountOwner) {
        for (Account a : this.get(accountOwner)) {
                a.applyInterst();
        }
    }


    public void triggerNegativeAccountEvent(Account account) {
        System.out.printf("%s has passed in negative at %s\n",account, LocalDate.now());
    }


}