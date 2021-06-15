package com.hexaware.resortmanagement.factory;

import com.hexaware.resortmanagement.model.Employee;
import com.hexaware.resortmanagement.persistence.DBConnection;
import com.hexaware.resortmanagement.persistence.EmployeeDAO;

/**
 * Factory class for Employee.
 */
public class EmployeeFactory {
  /**
   * protected constructor.
   */
  protected EmployeeFactory() {

  }

  /**
   * dao method represents the EmployeeDAO.
   * @return EmployeeDAO
   */
  private static EmployeeDAO dao() {
    DBConnection db = new DBConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

  /**
   * to find employee by id.
   * @param id for employee id
   * @return Employee
   */
  public static Employee findById(final int id) {
    Employee e = dao().findById(id);
    return e;
  }

  /**
   * to find employee by email.
   * @param argEmail for email
   * @return Employee
   */
  public static Employee findByEmail(final String argEmail) {
    Employee e = dao().findByEmail(argEmail);
    return e;
  }

  /**
   * to register a employee.
   * @param id for employee id
   * @param name for employee name
   * @param phone for phone
   * @param email for email
   * @param pass for password
   * @return int
   */
  public static int registerEmployee(final int id, final String name,
      final String phone, final String email, final String pass) {
    int res = dao().registerEmployee(id, name, phone, email, pass);
    return res;
  }

  /**
   * to update phone.
   * @param id for employee id
   * @param phone for new phone number
   * @return int
   */
  public static int updatePhone(final int id, final String phone) {
    int res = dao().updatePhone(id, phone);
    return res;
  }

  /**
   * to get the last row.
   * @return Employee
   */
  public static Employee findLastRow() {
    Employee e = dao().findLastRow();
    return e;
  }

  /**
   * to update password.
   * @param id for employee id
   * @param pass for password
   * @return int
   */
  public static int updatePassword(final int id, final String pass) {
    int res = dao().updatePassword(id, pass);
    return res;
  }
}
