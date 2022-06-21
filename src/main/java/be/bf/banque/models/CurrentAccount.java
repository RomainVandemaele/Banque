package be.bf.banque.models;

import com.google.common.base.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Class mutable that represents a current account that can havee a negative balance until the creditLine
 * @attribute creditLine double
 *
 *
 * @invariant creditLine >= 0
 * @invariant balance >= -creditLine
 * @see be.bf.banque.models.Account
 */
@Entity
@DiscriminatorValue("CURRENT")
public class CurrentAccount extends Account {
    @Min( value = 0)
    @Column(nullable = true)
    private double creditLine;

    public CurrentAccount() {super();}
    public CurrentAccount(String number, double balance) {super(number,balance);}
    public CurrentAccount(String number, AccountOwner accountOwner) {super(number,accountOwner);}
    public CurrentAccount(String number, AccountOwner accountOwner,double creditLine) {
        super(number,accountOwner);

    }

    public CurrentAccount(String number, AccountOwner accountOwner,double creditLine, double balance) {
        super(number,accountOwner,balance);
    }

    public double getCreditLine() {
        return this.creditLine;
    }

    public void setCreditLine(double creditLine) {
        if (creditLine < 0) return;
        this.creditLine = creditLine;
    }

    @Override
    public double computeInterest() {
        return this.getBalance() * (this.getBalance() >= 0 ? 0.01 : 0.075);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CurrentAccount that = (CurrentAccount) o;
        return Double.compare(that.creditLine, creditLine) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), creditLine);
    }
}
