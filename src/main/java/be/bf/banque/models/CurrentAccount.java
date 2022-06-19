package be.bf.banque.models;

/**
 * Class mutable that represents a current account that can havee a negative balance until the creditLine
 * @attribute creditLine double
 *
 *
 * @invariant creditLine >= 0
 * @invariant balance >= -creditLine
 * @see be.bf.banque.models.Account
 */
public class CurrentAccount extends Account {

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
}
