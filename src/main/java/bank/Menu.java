package bank;

import java.util.Scanner;
import javax.security.auth.login.LoginException;

import bank.expections.AmountException;

public class Menu {
  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to Global international bank");
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);
    Customer customer = menu.authenticatUser();
    if (customer != null) {
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }
    else System.out.println("Customer dose not  exsist ");
    menu.scanner.close();

  }

  private Customer authenticatUser() {
    System.out.println("Please enter the username");
    String username = scanner.next();
    System.out.println("Please enter the password");
    String password = scanner.next();
    Customer customer = null;
    try {
      customer = Authenticator.login(username, password);
 
    } catch (LoginException e) {
      System.out.println("there was an error "+ e.getMessage());
    }
    
    return customer;
    
  }

  private void showMenu(Customer customer, Account account){
    int selection = 0;
    while(selection != 4 && customer.isAuthenticated()){

    System.out.println("=========================================");
    System.out.println("Please select one of the following ");
    System.out.println("1: Deposit");
    System.out.println("2: Withdarw");
    System.out.println("3: check balance");
    System.out.println("4: Exit");
    System.out.println("=========================================");
    
    selection = scanner.nextInt();
    double amount=0;

    switch (selection) {
      case 1:
        System.out.println("how much money would you like to deposit");
        amount = scanner.nextDouble();
        try {
          account.deposit(amount);
        } catch (AmountException e) {
          System.out.println(e.getMessage());
          System.out.println("Please try again ");
        }
        
        break;
      case 2:
        System.out.println("how much would you like to withdraw");
        amount = scanner.nextDouble();
        try {
          account.withdraw(amount);
        } catch (Exception e) {
          System.out.println(e.getMessage());
          System.out.println("Please try again ");
        }
        

      case 3:
        System.out.println("Current balance:" + account.getBalance());
        break;
      case 4:
        Authenticator.loginOut(customer);
        System.out.println("thankyou for banking with global internation bank");

      default:
        System.out.println("invalid option please try again");
    }
      
  }

  }
}
