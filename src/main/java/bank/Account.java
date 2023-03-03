package bank;

import bank.expections.AmountException;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, Double balance) {
    setId(id);
    setType(type);
    setBalance(balance);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(Double amount) throws AmountException {
    if (amount < 1) {
      throw new AmountException("the minmum deposit is 1.00");
    }
    else{
      double newBalance = balance + amount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }
  }
  
  public void withdraw(Double amount)throws AmountException {
    if (amount < 0) {
      throw new AmountException("withdrawal amount must be greator then zero");
    }
    else if (amount > getBalance()) {
      throw new AmountException("you dont have sufficent fund for this withdrawal.");
    }
    else {
      double newBalance = balance -amount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }

  }

}
