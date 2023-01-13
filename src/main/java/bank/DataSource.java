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
      System.out.println("we' are connected");
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

  public static void main(String[] args) {
    Customer customer = getCustomer("twest8o@friendfeed.com");
    System.out.println(customer.getName());
  }
}
