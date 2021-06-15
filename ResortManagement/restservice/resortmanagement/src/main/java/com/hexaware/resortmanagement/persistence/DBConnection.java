package com.hexaware.resortmanagement.persistence;

import org.skife.jdbi.v2.DBI;

/**
 * DBConnection class.
 */
public class DBConnection {
  /**
   * method to connect to MySQL database.
   * @return dbi
   */
  public final DBI getConnect() {
    DBI dbi = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");

      dbi = new DBI("jdbc:mysql://localhost:3306/resortdb?useSSL=false", "root", "root");
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException(ex);
    } catch (Exception ex) {
      System.out.println("Exception thrown");
    }
    return dbi;
  }
}
