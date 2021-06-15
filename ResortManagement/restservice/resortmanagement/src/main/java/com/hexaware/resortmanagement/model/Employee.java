package com.hexaware.resortmanagement.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.hexaware.resortmanagement.factory.BookingFactory;
import com.hexaware.resortmanagement.factory.EmployeeFactory;
import com.hexaware.resortmanagement.factory.MemberFactory;

/**
 * Employees class.
 */
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

  // private Employee[] empList = new Employee[5];

  // /**
  //  *
  //  * @return empList.
  //  */
  // public final Employee[] getEmpList() {
  //   return empList;
  // }

  // /**
  //  *
  //  * @param argEmpList employees list
  //  */
  // public final void setEmpList(final Employee[] argEmpList) {
  //   this.empList = argEmpList;
  // }

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

  /**
   * toString method.
   * @return string
   */
  @Override
  public final String toString() {
    return "Employee Details: [Id: " + this.employeeId + " Name: " + this.employeeName + " Phone: " + this.phone
        + " Email: " + this.email + "]";
  }

  /**
   * to register an employee.
   * @param argName  for employee name
   * @param argPhone for phone
   * @param argEmail for email
   * @param argPass  for password
   * @return string
   */
  public final String registerEmployee(final String argName, final String argPhone, final String argEmail,
      final String argPass) {
    Employee e = EmployeeFactory.findLastRow();

    int id = 1001;
    if (e != null) {
      id = e.getEmployeeId() + 1;
    }

    String res = "Registration Unsuccessful! Please try again";
    int i = EmployeeFactory.registerEmployee(id, argName, argPhone, argEmail, argPass);
    if (i > 0) {
      res = "Registration Successful";
    }

    return res;
  }

  /**
   * accept or deny booking.
   * @param argBookingId for booking id
   * @param stat         for status
   * @param amt          for amount
   * @return string
   */
  public final String acceptDenyBooking(final int argBookingId, final String stat, final double amt) {
    String msg = "Booking Status: Unchanged.";
    Booking b = BookingFactory.showBookingDetails(argBookingId);

    // for date manipulation
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    if (b.getBookingId() != 0) {
      int memId = b.getMemberId();

      Member m = MemberFactory.getMemberById(memId);
      double amount = m.getWalletbalance();

      Date bDate = b.getBookingDate();
      String temp = sdf.format(new Date());
      Date today = new Date();

      try {
        today = sdf.parse(temp);
      } catch (ParseException ex) {
        System.out.println(ex.getMessage());
      }

      if (bDate.compareTo(today) >= 0) {
        if (stat.equals(BookingStatus.DENIED.toString())) {
          int i = MemberFactory.incrementWallet(m.getMemberId(), amount, amt);
  
          if (i > 0) {
            System.out.println("Booking Amount Credited to Wallet");
          }
          msg = "Booking Status: Denied.";
        }
        if (stat.equals(BookingStatus.ACCEPTED.name())) {
          msg = "Booking Status: Accepted.";
        }

        int r = BookingFactory.updateStatus(argBookingId, stat);
        if (r > 0) {
          msg = msg + " Status Updated";
        }
      } else {
        msg = "Booking Status: Unchanged. Not Considered due to date mismatch.";
      }
    } else {
      msg = "Invalid Booking";
    }
    return msg;
  }

  /**
   * to update phone.
   * @param id for member id
   * @param ph for new phone number
   * @return string
   */
  public final String updatePhone(final int id, final String ph) {
    String res = "Unable to update phone";

    int i = EmployeeFactory.updatePhone(id, ph);
    if (i > 0) {
      res = "Phone updated successfully";
    }

    return res;
  }

  /**
   * to update password.
   * @param id for id
   * @param pass for new password
   * @return string
   */
  public final String updatePassword(final int id, final String pass) {
    String res = "Unable to update password";

    int i = EmployeeFactory.updatePassword(id, pass);
    if (i > 0) {
      res = "Password updated successfully";
    }

    return res;
  }
}
