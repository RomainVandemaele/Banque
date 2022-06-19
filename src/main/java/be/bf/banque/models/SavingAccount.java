package be.bf.banque.models;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;

/** Mutable class that represents a saving accounts which cannot have a negative balance
 * @attributes lastWithdrawDate double
 *
 * @see be.bf.banque.models.Account
 */
public class SavingAccount extends Account {

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = true)
    private LocalDate lastWithdrawDate;

    public SavingAccount() {super();}

    public SavingAccount(String number, double balance) {super(number,balance);}
    public SavingAccount(String number, AccountOwner accountOwner) {super(number,accountOwner);}
    public SavingAccount(String number, AccountOwner accountOwner,double balance) {super(number,accountOwner,balance);}

    public LocalDate getLastWithdrawDate() { return this.lastWithdrawDate;}

    @Override
    public void withdraw(double amount) {
        if (this.getBalance() - amount < 0) return;
        super.withdraw(amount);
        this.lastWithdrawDate = LocalDate.now();
    }

    @Override
    public double computeInterest() {
        return this.getBalance() * 0.1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SavingAccount{");
        sb.append("lastWithdrawDate=").append(lastWithdrawDate);
        sb.append('}');
        return sb.toString();
    }
}
