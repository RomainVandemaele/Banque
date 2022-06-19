package be.bf.banque.models;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
@ToString
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "number", nullable = false, length = 19,unique = true)
    @Pattern(regexp = "BE[0,9]{2} [0,9]{4} [0,9]{4} [0,9]{4}")
    private String number;

    @NotNull
    @Column( name = "balance")
    private double balance;

    @ManyToOne
    @Column(name = "accountOwner", nullable = false)
    private AccountOwner accountOwner;


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
