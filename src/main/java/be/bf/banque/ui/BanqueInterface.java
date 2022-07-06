package be.bf.banque.ui;

import be.bf.banque.models.*;
import be.bf.banque.repository.AccountOwnerRepository;
import be.bf.banque.repository.AccountRepository;
import be.bf.banque.repository.BankRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

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
            System.out.println("Your choice ?");
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

    public void newAccount() {
        Account account;
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



    public void listAccount() {
        List<Account> ownerAccounts= this.accountRepo.findByOwner(currentOwner);
        final int n = ownerAccounts.size();
        if(n==0) {
            System.out.println("you have no account in this bank");
            loginMenu();
        }

        IntStream.range(0, n).forEach(i -> System.out.printf("%d : %s\n",i+1,ownerAccounts.get(i).getNumber()) );

        System.out.println("Operation (del|with|dep + account number + [amount] )");
        String input = new String();
        while (! input.matches("del [0-9]+"+"|wit [0-9]+ [0-9]+"+"|dep [0-9]+ [0-9]+|q") ) {
            System.out.println("Your choice ?");
            input = myScanner.nextLine();
        }
        accountOperation(ownerAccounts,input);

//        String pattern = "(del|wit|dep) [1-"+(n+1)+"]"+"( [0-9]*){0,1}";
//        String[] tokens = createMenu(new ArrayList<String>(),pattern);
//        Account account = ownerAccounts.get(Integer.valueOf(tokens[1])-1);
//
//        if (tokens[0].equals("del")) {
//            deleteAccount(account);
//        } else if (tokens.length == 3) {
//            final int amount = Integer.valueOf( tokens[2] );
//            if(tokens[0].equals("wit")) { account.withdraw(amount);}
//            else { account.deposit(amount); }
//        }else {
//            System.out.println("For withdraw and deposit you new to specify an amount");
//        }
//        listAccount();
    }

    public void deleteAccount(Account account) {
        this.accountRepo.remove(account);
    }
    public void accountOperation(List<Account> ownerAccounts,String op) {
        if(op.length()==1) {this.quit();}
        else {
            String[] tokens = op.split(" ");
            int accountIndex = Integer.valueOf(tokens[1]) - 1;
            if (accountIndex >= ownerAccounts.size() || accountIndex < 0) {
                System.out.println("That account does not exists try again.");
                listAccount();
            }
            switch (tokens[0]) {
                case "del":
                    this.deleteAccount(ownerAccounts.get(accountIndex));
                    break;
                case "dep":
                    final int depositAmount = Integer.valueOf(tokens[2]);
                    ownerAccounts.get(accountIndex).deposit(depositAmount);
                    break;
                default:
                    final int withdrawAmount = Integer.valueOf(tokens[2]);
                    ownerAccounts.get(accountIndex).withdraw(withdrawAmount);
                    break;
            }
            accountRepo.update(ownerAccounts.get(accountIndex));
            listAccount();
        }
    }

    public String[] createMenu(List<String> options, String pattern) {
        final int n = options.size();
        StringBuilder menu = new StringBuilder("What do you want to do?\n");
        for (int i= 0;i<n;++i) {
            menu.append(i).append(" : ").append(options.get(i)).append("\n");
        }
        String input = "";
        while ( ! input.matches(pattern) ) {
            System.out.println("Your choice " + pattern + " ?");
            input = myScanner.nextLine();
        }
        System.out.println(input);
        return input.split(" ");
    }

    public void quit() {
        this.accountRepo.closeConnect();
        this.ownerRepo.closeConnect();
        this.bankRepo.closeConnect();
    }

}
