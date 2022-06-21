package be.bf.banque.models;

import com.google.common.base.Objects;
import jakarta.persistence.*;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

/** Mutable class that represents a saving accounts which cannot have a negative balance
 * @attributes lastWithdrawDate double
 *
 * @see be.bf.banque.models.Account
 */
@Entity
@DiscriminatorValue("SAVING")
public class SavingAccount extends Account {

    @Temporal(value = TemporalType.DATE)
    @PastOrPresent
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SavingAccount that = (SavingAccount) o;
        return Objects.equal(lastWithdrawDate, that.lastWithdrawDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), lastWithdrawDate);
    }
}
