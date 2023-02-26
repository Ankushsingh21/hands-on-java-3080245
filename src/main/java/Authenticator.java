import javax.security.auth.login.LoginException;

import bank.Customer;
import bank.DataSource;

public class Authenticator {

  public static Customer login(String username, String password) throws LoginException{
    Customer customer= DataSource.getCustomer(username);
    if(customer == null){
     throw new LoginException("Username not found"); 
    }
    if(password.equals(customer.getPassword())){
      customer.setAuthenticated(true);
      return customer;
    }
    else throw new LoginException("password Incorrect");

  }

  public static void loginOut(Customer customer){
    customer.setAuthenticated(false);
  }

}
