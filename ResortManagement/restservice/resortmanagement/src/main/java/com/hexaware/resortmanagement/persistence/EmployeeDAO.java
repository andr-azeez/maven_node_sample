package com.hexaware.resortmanagement.persistence;

import com.hexaware.resortmanagement.model.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * DAO class for Employees.
 */
public interface EmployeeDAO {
  /**
   * find an employee's details.
   * @param id for employee id
   * @return employee
   */
  @SqlQuery("SELECT * FROM EMPLOYEES WHERE EMPLOYEEID = :id")
  @Mapper(EmployeeMapper.class)
  Employee findById(@Bind("id") final int id);

  /**
   * find the employee by email -> for login.
   * @param email for email
   * @return employee
   */
  @SqlQuery("SELECT * FROM EMPLOYEES WHERE EMAIL = :email")
  @Mapper(EmployeeMapper.class)
  Employee findByEmail(@Bind("email") final String email);

  /**
   * to register an employee.
   * @param id employee id
   * @param name employee name
   * @param phone phone number
   * @param email email
   * @param pass password
   * @return int
   */
  @SqlUpdate("INSERT INTO EMPLOYEES VALUES (:id, :name, :phone, :email, :pass)")
  int registerEmployee(@Bind("id") final int id, @Bind("name") final String name, @Bind("phone") final String phone,
      @Bind("email") final String email, @Bind("pass") final String pass);

  /**
   * to update phone.
   * @param id employee id
   * @param phone new phone number
   * @return int
   */
  @SqlUpdate("UPDATE EMPLOYEES SET PHONE = :phone WHERE EMPLOYEEID = :id")
  int updatePhone(@Bind("id") final int id, @Bind("phone") final String phone);

  /**
   * to update password.
   * @param id employee id.
   * @param pass new password.
   * @return int
   */
  @SqlUpdate("UPDATE EMPLOYEES SET PASSKEY = :pass WHERE EMPLOYEEID = :id")
  int updatePassword(@Bind("id") final int id, @Bind("pass") final String pass);

  /**
   * find the last row in table.
   * @return employee
   */
  @SqlQuery("SELECT * FROM EMPLOYEES WHERE EMPLOYEEID = (SELECT MAX(EMPLOYEEID) FROM EMPLOYEES)")
  @Mapper(EmployeeMapper.class)
  Employee findLastRow();
}
