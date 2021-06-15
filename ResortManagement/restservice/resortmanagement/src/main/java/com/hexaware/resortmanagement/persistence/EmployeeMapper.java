package com.hexaware.resortmanagement.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hexaware.resortmanagement.model.Employee;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * mapper class for employee.
 */
public class EmployeeMapper implements ResultSetMapper<Employee> {
    /**
   * map method.
   * @param idx for index
   * @param rs for Resultset
   * @param stmt for StatementContext
   * @return Employee
   * @throws SQLException for SQLException
   */
    public Employee map(final int idx, final ResultSet rs, final StatementContext stmt) throws SQLException {
      return new Employee(rs.getInt("EMPLOYEEID"), rs.getString("EMPLOYEENAME"), rs.getString("PHONE"),
          rs.getString("EMAIL"), rs.getString("PASSKEY"));
    }
}
