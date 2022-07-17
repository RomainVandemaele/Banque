package be.bf.banque;


import be.bf.banque.models.Account;

@FunctionalInterface
public interface IAction {
    public void invoke(Account account);
}
