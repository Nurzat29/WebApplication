
package db;
import JavaBeans.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HSQLDB {
 
 Connection connection = null;
 
 public static void startHSQLDB(){ 
     
  
  HSQLDB db = new HSQLDB();
  if (!db.loadDriver()) return;
  if (!db.getConnection()) return;     
  
  db.createTable();
  db.printTable();
  db.closeConnection();    
 }
 
 private boolean loadDriver() {
    try {   
        Class.forName("org.hsqldb.jdbcDriver");
    } catch (ClassNotFoundException e) {
    System.out.println("Драйвер не найден");
    e.printStackTrace(); 
    return false;
    }  
    return true;
 }

 private boolean getConnection() {
  
  try {
   String path = "mypath/";
   String dbname = "user";
   String connectionString = "jdbc:hsqldb:file:"+path+dbname;
   String login = "admin";
   String password = "password";
   connection = DriverManager.getConnection(connectionString, login, password);
   
  } catch (SQLException e) {
   System.out.println("Соединение не создано");
   e.printStackTrace();
   return false;
  }
  return true;
 }
 
 private void createTable() {
  try {
   Statement statement = connection.createStatement();   
   String sql = "CREATE TABLE user (id IDENTITY, name VARCHAR(255), password VARCHAR(255))";  
   statement.executeUpdate(sql);
  } catch (SQLException e) {
   
  }
 }
 
 public void fillTable(User user) {
     getConnection();
  Statement statement;
  try {
   statement = connection.createStatement();
   String sql = "INSERT INTO user (name, password) VALUES('" + user.getUsername() + "', '" + user.getPassword() + "')";
   statement.executeUpdate(sql);
   
  } catch (SQLException e) {   
   e.printStackTrace();
  }  
  printTable();
  closeConnection();
 }
 
 private void printTable() {
     getConnection();
  Statement statement;
  try {
   statement = connection.createStatement();
   String sql = "SELECT id, name, password FROM user";
   ResultSet resultSet = statement.executeQuery(sql);
    
   while (resultSet.next()) {
    System.out.println(resultSet.getInt(1) + " "
    + resultSet.getString(2) + " " + resultSet.getString(3));
   }
   
  } catch (SQLException e) {   
   e.printStackTrace();
  }     
 }
 
 public ArrayList<User> getAllUsers() {
     getConnection();
     ArrayList<User> userList = new ArrayList<>();
     Statement statement;
  try {
   statement = connection.createStatement();
   String sql = "SELECT name, password FROM user";
   ResultSet resultSet = statement.executeQuery(sql);
    
   while (resultSet.next()) {
       User user = new User();
       user.setUsername(resultSet.getString("name"));
       user.setPassword(resultSet.getString("password"));
       userList.add(user);
   }
   
  } catch (SQLException e) {   
   e.printStackTrace();
  } 
  closeConnection();
  return userList;
 }

 private void closeConnection() {
  
  Statement statement;
  try {
   statement = connection.createStatement();
   String sql = "SHUTDOWN";
   statement.execute(sql);
   
  } catch (SQLException e) {
   e.printStackTrace();
  }  
 }
 
}
