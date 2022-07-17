package be.bf.banque.models;

@FunctionalInterface
public interface IGoingNegativeBalance {
    void triggerEvent(Account account);
}
