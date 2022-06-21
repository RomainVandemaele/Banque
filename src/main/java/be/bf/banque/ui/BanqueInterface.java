package be.bf.banque.ui;

import be.bf.banque.models.Account;
import be.bf.banque.models.Bank;
import be.bf.banque.repository.AccountOwnerRepository;
import be.bf.banque.repository.AccountRepository;
import be.bf.banque.repository.BankRepository;

import java.util.Scanner;

public class BanqueInterface {

    private AccountOwnerRepository ownerRepo;
    private AccountRepository accountRepo;
    private BankRepository bankRepo;
    private Bank bank;


    private Scanner myScanner = new Scanner(System.in);

    public BanqueInterface() {
        this.ownerRepo = new AccountOwnerRepository();
        this.accountRepo = new AccountRepository();
        this.bankRepo = new BankRepository();
        this.bank = this.bankRepo;
    }

    public void menu() {
        System.out.println("Bienvenue dans la banque "+ this.bank);
        System.out.println("Que voulez-vous faire ?");
        StringBuilder menu = new StringBuilder();
        final int nOption = 3;
        menu.append("1.S'inscrire à la banque\n")
                .append("2.Se connecter à la banque\n")
                .append("3.Ajouter un compte\n");
        System.out.println(menu.toString());
        while (! myScanner.hasNext("[1-3]") ) {
            System.out.println("Choissisez ce que vous voule-faire ?");
            myScanner.next();
        }
    }
}
