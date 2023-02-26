package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to Global international bank");
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    menu.scanner.close();

  }

  private Customer authenticatUser() {
    System.out.println("Please enter the username");
    Scanner username = scanner.next();
    System.out.println("Please enter the password");
    Scanner password = scanner.next();
    Customer customer = null;
    try {
      
      customer = Authenticator.login(username, password);
      
    } catch (LoginException e) {
      System.out.println("there was an error "+ e.getMessage());
    }
    
    return customer;
    
  }
  
}
