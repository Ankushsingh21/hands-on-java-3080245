package bank;

import java.util.Scanner;
import javax.security.auth.login.LoginException;

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
    
  }
}
