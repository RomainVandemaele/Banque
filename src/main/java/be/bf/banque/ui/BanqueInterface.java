package be.bf.banque.ui;

import be.bf.banque.models.*;
import be.bf.banque.repository.AccountOwnerRepository;
import be.bf.banque.repository.AccountRepository;
import be.bf.banque.repository.BankRepository;

import java.util.List;
import java.util.Scanner;

public class BanqueInterface {

    private AccountOwnerRepository ownerRepo;
    private AccountRepository accountRepo;
    private BankRepository bankRepo;

    private Bank bank;
    private AccountOwner currentOwner;
    private Account currentAccount;


    private Scanner myScanner = new Scanner(System.in);

    public BanqueInterface() {
        this.ownerRepo = new AccountOwnerRepository();
        this.accountRepo = new AccountRepository();
        this.bankRepo = new BankRepository();
        this.bank = this.bankRepo.findAll().get(0);
    }

    public void menu() {
        System.out.println("Welcome to bank "+ this.bank.getName());
        System.out.println("What do you want to do?");
        StringBuilder menu = new StringBuilder();
        final int nOption = 3;
        menu.append("1.Register\n")
                .append("2.Login\n");
        System.out.println(menu.toString());
        String input = "";
        while (! myScanner.hasNext("[1-2]") ) {
            System.out.println("Your choice ?");
            myScanner.next();
        }
        input = myScanner.next();
        switch (Integer.valueOf(input)) {
            case 1 :
                register();
                break;
            case 2 :
                login();
                break;
        }
    }

    public void register() {
        System.out.println("Tell us more info about you : ");
        System.out.println("First name :");
        String fname = myScanner.next();
        System.out.println("Last name :");
        String lname = myScanner.next();
        AccountOwner newOwner = new AccountOwner(lname,fname);
        this.ownerRepo.insert(newOwner);
        System.out.println("Welcome " + newOwner + " Take note of your ssin");
        menu();
    }

    public void login() {
        System.out.println("Enter your ssin : ");
        while (currentOwner == null) {
            String ssin = myScanner.next();
            currentOwner = ownerRepo.findBySSIN(ssin);
            if(currentOwner == null) {
                System.out.println("Enter a correct ssin");
            }
        }
        loginMenu();
    }

    public void loginMenu() {
        System.out.println("Welcome "+ currentOwner.getFirstname() + " " + currentOwner.getLastname());
        System.out.println("What do you want to do?");
        StringBuilder menu = new StringBuilder();
        menu.append("1.List your accounts\n")
                .append("2.Create new account\n");
        System.out.println(menu.toString());

        while (! myScanner.hasNext("[1-2]") ) {
            System.out.println("Your chopice ?");
            myScanner.next();
        }
        String input = myScanner.next();
        switch (Integer.valueOf(input)) {
            case 1 :
                listAccount();
                break;
            case 2 :
                newAccount();
                break;
        }
    }

    public void listAccount() {
        List<Account> ownerAccounts= this.accountRepo.findByOwner(currentOwner);
        final int n = ownerAccounts.size();
        if(n==0) {
            System.out.println("you have no account in this bank");
            loginMenu();
        }
        for(int i=0;i<n;++i) {
            System.out.println(i+1 + " : " + ownerAccounts.get(i));
        }
        System.out.println("Operation (del|with|dep + account number + [amount] )");

        while (! myScanner.hasNext("['del''wit''dep'] [0-"+n+"]"+"") ) {
            System.out.println("Your choice ?");
            myScanner.next();
        }
        String input = myScanner.next();
    }

    public void newAccount() {
        Account account = null;
        System.out.println("We need some information : ");
        System.out.println("saving(S) or current(C) ? ");
        String type = myScanner.next();
        if(type.equals("C")) {
            System.out.println("credit line ");
            int creditLine = myScanner.nextInt();
            account = new CurrentAccount(Account.IBANGenerator(),currentOwner,creditLine);
        }else {
            account = new SavingAccount(Account.IBANGenerator(),currentOwner);
        }
        account.setBank(this.bank);
        this.accountRepo.insert(account);
        loginMenu();
    }

    public void deleteAccount(Account account) {
        this.accountRepo.remove(account);
    }

    public void accountOp(String op, int amount) {

    }

    public int createMenu(List<String> options) {
        return 0;
    }

    public void quit() {
        this.accountRepo.closeConnect();
        this.ownerRepo.closeConnect();
        this.bankRepo.closeConnect();
    }
}
