package be.bf.banque.models;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.ToString;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Class mutable that represents an account in a bank that has an owner fram AccountOwner
 * FA : Account  {number,amount}
 *
 * @attribute id Long
 * @attribute number String Format IBAN BEXX XXXX XXXX XXXX
 * @attribute balance double
 * @attribute accountOwner AccountOwner
 *
 * @invariant number != null and number.length = 19
 * @invariant titulaire!=null
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //default strategy
@DiscriminatorColumn(name = "accounttype", discriminatorType = DiscriminatorType.STRING)
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
@NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id=:id")
@NamedQuery(name = "Account.findByBank", query = "SELECT a FROM Account a WHERE a.bank=:bankId")
@NamedQuery(name = "Account.findByOwner", query = "SELECT a FROM Account a WHERE a.accountOwner=:ownerId")
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false) //insertable = false? maybe error with detached
    private Long id;

    @NotNull
    @Length(min = 19,max = 19)
    @Unique
    @Column(name = "number", nullable = false, length = 19,unique = true)
    @Pattern(regexp = "BE[0,9]{2} [0,9]{4} [0,9]{4} [0,9]{4}")
    private String number;

    @NotNull
    @Column( name = "balance")
    private double balance;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private AccountOwner accountOwner;

    @ManyToOne
    @NotNull
    //@MapsId
    @JoinColumn(name = "bank_id", referencedColumnName = "id") //only needed in the entity with foreign column
    private Bank bank;


    public Account() {}

    public Account(String number, double balance) {}
    public Account(String number, AccountOwner accountOwner) {}
    public Account(String number, AccountOwner accountOwner,double balance) {}

    public String getNumber() {
        return this.number;
    }

    public double getBalance() {
        return this.balance;
    }
    private Account setBalance(String number) {
        if (number==null) return this;
        if(number.length() != 19) return this;
        if (!number.matches( "BE[0,9]{2} [0,9]{4} [0,9]{4} [0,9]{4}" )  ) return this;

        this.number = number;
        return this;
    }


    public AccountOwner getAccountOwner() {
        return this.accountOwner;
    }

    private Account setAccountOwner(AccountOwner accountOwner) {
        if(accountOwner == null) return this;
        this.accountOwner = new AccountOwner(accountOwner);
        return this;
    }

    public Long getId() {
        return this.id;
    }

    /**
     * Process of deposit money to an account
     * @param amount amount to deposit > 0
     */
    public void deposit(double amount) {
        if (amount <= 0) return;
        this.balance += amount;
    }

    /**
     * Process of withdrawing money from an account
     * @param amount amount to withdraw > 0
     *
     */
    public void withdraw( double amount) {
        if (amount <= 0 ) return;
        this.balance -= amount;
    }

    public abstract double computeInterest();

    public void applyInterst() { this.balance += this.computeInterest();}

    public double add( Account account) { return this.balance + account.balance;}
    public double add( double amount) { return this.balance + amount;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 && Objects.equal(id, account.id) && Objects.equal(number, account.number) && Objects.equal(accountOwner, account.accountOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, number, balance, accountOwner);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("number='").append(number).append('\'');
        sb.append(", amount=").append(balance);
        sb.append(", accountOwner=").append(accountOwner);
        sb.append('}');
        return sb.toString();
    }
}
