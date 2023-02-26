package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(db_file);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;

  }

  public static Customer getCustomer(String username) {
    String sql = "select * from customers where username = ?";
    Customer customer = null;
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, username);
      try (ResultSet resultset = statement.executeQuery()) { // it will return a result set
        customer = new Customer(
            resultset.getInt("id"),
            resultset.getString("name"),
            resultset.getString("username"),
            resultset.getString("password"),
            resultset.getInt("account_id"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customer;
  }

  public static Account getAccount(int id) {
    String sql = "select * from Accounts where id = ?";
    Account account = null;
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, id);
      try (ResultSet resultset = statement.executeQuery()) { // it will return a result set
        account = new Account(
            resultset.getInt("id"),
            resultset.getString("type"),
            resultset.getDouble("balance"));

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return account;
  }

  public static void main(String[] args) {
    Customer customer = getCustomer("clillea8@nasa.gov");
    System.out.println(customer.getName());
    Account account = getAccount(47373);
    System.out.println(account.getBalance());
  }
}
