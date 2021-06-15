package com.hexaware.integration.test;

import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Employee {
  /**
   * to store employeeId.
   */
  private int employeeId;
  /**
   * to store employeeName.
   */
  private String employeeName;
  /**
   * to store phone.
   */
  private String phone;
  /**
   * to store email.
   */
  private String email;
  /**
   * to store passkey.
   */
  private String passKey;

   /**
   *
   * @return employeeId.
   */
  public final int getEmployeeId() {
    return employeeId;
  }

  /**
   *
   * @param argEmployeeId for employeeId.
   */
  public final void setEmployeeId(final int argEmployeeId) {
    this.employeeId = argEmployeeId;
  }

  /**
   *
   * @return employeeName.
   */
  public final String getEmployeeName() {
    return employeeName;
  }

  /**
   *
   * @param argEmployeeName for employeeName.
   */
  public final void setEmployeeName(final String argEmployeeName) {
    this.employeeName = argEmployeeName;
  }

  /**
   *
   * @return phone.
   */
  public final String getPhone() {
    return phone;
  }

  /**
   *
   * @param argPhone for phone.
   */
  public final void setPhone(final String argPhone) {
    this.phone = argPhone;
  }

  /**
   *
   * @return email.
   */
  public final String getEmail() {
    return email;
  }

  /**
   *
   * @param argEmail for email.
   */
  public final void setEmail(final String argEmail) {
    this.email = argEmail;
  }

  /**
   *
   * @return passKey.
   */
  public final String getPassKey() {
    return passKey;
  }

  /**
   *
   * @param argPassKey for passKey.
   */
  public final void setPassKey(final String argPassKey) {
    this.passKey = argPassKey;
  }

  /**
   * default constructor.
   */
  public Employee() {

  }

  /**
   * parameterized constructor.
   * @param argEmployeeId   for employeeId
   * @param argEmployeeName for employeeName
   * @param argPhone        for phone
   * @param argEmail        for email
   * @param argPassKey      for passkey
   */
  public Employee(final int argEmployeeId, final String argEmployeeName, final String argPhone, final String argEmail,
      final String argPassKey) {
    this.employeeId = argEmployeeId;
    this.employeeName = argEmployeeName;
    this.phone = argPhone;
    this.email = argEmail;
    this.passKey = argPassKey;
  }

  /**
   * hashcode method.
   * @return int
   */
  @Override
  public final int hashCode() {
    return Objects.hash(employeeId, employeeName, phone, email, passKey);
  }

  /**
   * equals method.
   * @param obj Object
   * @return boolean
   */
  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    Employee emp = (Employee) obj;
    if (Objects.equals(employeeId, emp.employeeId) && Objects.equals(employeeName, emp.employeeName)
        && Objects.equals(phone, emp.phone) && Objects.equals(email, emp.email)
        && Objects.equals(passKey, emp.passKey)) {
      return true;
    }
    return false;
  }

  public String toString() {
    String response = "";

    try {
      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
      response = ow.writeValueAsString(this);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return response;
  }
}
