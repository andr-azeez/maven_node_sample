package com.hexaware.resortmanagement.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hexaware.resortmanagement.factory.BookingFactory;
import com.hexaware.resortmanagement.factory.EmployeeFactory;
import com.hexaware.resortmanagement.factory.MemberFactory;
import com.hexaware.resortmanagement.persistence.BookingDAO;
import com.hexaware.resortmanagement.persistence.EmployeeDAO;
import com.hexaware.resortmanagement.persistence.MembersDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * test class for Employee.
 */
@RunWith(JMockit.class)
public class EmployeeTest {

  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  /**
   * test for default constructor.
   */
  @Test
  public final void testEmployee() {
    Employee e = new Employee();
    e.setEmployeeId(1002);
    e.setEmployeeName("Diane Murphy");
    e.setEmail("dmurphy@blackstoneresorts.com");
    e.setPhone("384579292");
    e.setPassKey("diane123");
    assertEquals(1002, e.getEmployeeId());
    assertEquals("Diane Murphy", e.getEmployeeName());
    assertEquals("dmurphy@blackstoneresorts.com", e.getEmail());
    assertEquals("384579292", e.getPhone());
    assertEquals("diane123", e.getPassKey());
  }

  /**
   * test for parameterized constructor.
   */
  @Test
  public final void testEmployeeParameterizedConstructor() {
    Employee e = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    assertEquals(1002, e.getEmployeeId());
    assertEquals("Diane Murphy", e.getEmployeeName());
    assertEquals("dmurphy@blackstoneresorts.com", e.getEmail());
    assertEquals("384579292", e.getPhone());
    assertEquals("diane123", e.getPassKey());
  }

  /**
   * test for getEmployeeId.
   */
  @Test
  public final void testGetEmployeeId() {
    Employee e = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    assertEquals(1002, e.getEmployeeId());
  }

  /**
   * test for getEmployeeName.
   */
  @Test
  public final void testGetEmployeeName() {
    Employee e = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    assertEquals("Diane Murphy", e.getEmployeeName());
  }

  /**
   * test for getEmail.
   */
  @Test
  public final void testGetEmail() {
    Employee e = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    assertEquals("dmurphy@blackstoneresorts.com", e.getEmail());
  }

  /**
   * test for getPhone.
   */
  @Test
  public final void testGetPhone() {
    Employee e = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    assertEquals("384579292", e.getPhone());
  }

  /**
   * test for getPassKey.
   */
  @Test
  public final void testGetPassKey() {
    Employee e = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    assertEquals("diane123", e.getPassKey());
  }

  /**
   * test for hashCode.
   */
  @Test
  public final void testHashCode() {
    Employee e1 = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    Employee e2 = new Employee(1165, "Leslie Jennings", "32934241", "ljennings@blackstoneresorts.com", "leslie123");
    Employee e3 = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");

    assertEquals(e1.hashCode(), e3.hashCode());
    assertNotEquals(e1.hashCode(), e2.hashCode());
    assertNotEquals(e2.hashCode(), e3.hashCode());
  }

  /**
   * test for equals.
   */
  @Test
  public final void testEquals() {
    Employee e1 = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    Employee e2 = new Employee(1165, "Leslie Jennings", "32934241", "ljennings@blackstoneresorts.com", "leslie123");
    Employee e3 = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");

    assertTrue(e1.equals(e3));
    assertFalse(e1.equals(e2));
    assertFalse(e2.equals(e3));
  }

  /**
   * test for setEmployeeId.
   */
  @Test
  public final void testSetEmployeeId() {
    Employee e = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    e.setEmployeeId(1004);
    assertEquals(1004, e.getEmployeeId());
  }

  /**
   * test for setEmployeeName.
   */
  @Test
  public final void testSetEmployeeName() {
    Employee e = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    e.setEmployeeName("Kit Walker");
    assertEquals("Kit Walker", e.getEmployeeName());
  }

  /**
   * test for setPhone.
   */
  @Test
  public final void testSetPhone() {
    Employee e = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    e.setPhone("2389292");
    assertEquals("2389292", e.getPhone());
  }

  /**
   * test for setPassKey.
   */
  @Test
  public final void testSetPassKey() {
    Employee e = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    e.setPassKey("murph123");
    assertEquals("murph123", e.getPassKey());
  }

  /**
   * test for setEmail.
   */
  @Test
  public final void testSetEmail() {
    Employee e = new Employee(1002, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    e.setEmail("mDiane@blackstoreresorts.com");
    assertEquals("mDiane@blackstoreresorts.com", e.getEmail());
  }

  /**
   * test for updatePhone.
   * @param eDao for EmployeeDAO
   */
  @Test
  public final void testUpdatePhone(@Mocked final EmployeeDAO eDao) {
    new Expectations() {
      {
        eDao.updatePhone(1002, "84784884");
        result = 1;
      }
    };

    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return eDao;
      }
    };

    Employee e = new Employee();
    String str = e.updatePhone(1002, "84784884");
    assertEquals("Phone updated successfully", str);

    String res = e.updatePhone(2001, "20933993");
    assertEquals("Unable to update phone", res);
  }

  /**
   * test for updatePassword.
   * @param eDao for EmployeeDAO
   */
  @Test
  public final void testUpdatePassword(@Mocked final EmployeeDAO eDao) {
    new Expectations() {
      {
        eDao.updatePassword(1002, "pass123");
        result = 1;
      }
    };

    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return eDao;
      }
    };

    Employee e = new Employee();
    String str = e.updatePassword(1002, "pass123");
    assertEquals("Password updated successfully", str);

    String res = e.updatePassword(2001, "pass123");
    assertEquals("Unable to update password", res);
  }

  /**
   * test for registerEmployee.
   * @param eDao for EmployeeDAO
   */
  @Test
  public final void testRegisterEmployee(@Mocked final EmployeeDAO eDao) {
    Employee e = new Employee(1216, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");

    new Expectations() {
      {
        eDao.findLastRow();
        result = e;
      }
    };

    new Expectations() {
      {
        eDao.registerEmployee(1217, "Kiran Bedi", "9834893", "kiran@blackstoneresorts.com", "kiran123");
        result = 1;
      }
    };

    new MockUp<EmployeeFactory>(){
      @Mock
      EmployeeDAO dao() {
        return eDao;
      }
    };

    Employee e1 = new Employee();
    String res = e1.registerEmployee("Kiran Bedi", "9834893", "kiran@blackstoneresorts.com", "kiran123");
    assertEquals("Registration Successful", res);

    String str = e1.registerEmployee("Katie Lowland", "5834985", "katie@blackstoneresorts.com", "katie123");
    assertEquals("Registration Unsuccessful! Please try again", str);
  }

  /**
   * test for acceptDenyBooking.
   * @param mDao for MemberDAO
   * @param bDao for BookingDAO
   */
  @Test
  public final void testAcceptDenyBooking(@Mocked final MembersDAO mDao, @Mocked final BookingDAO bDao ) throws ParseException{
    Member m = new Member(119, "Akanksha Sinha", "wi@we.c", "8394843", 45230, "aksinha123", sdf.parse("2021-01-12"));

    String s = sdf.format(new Date());
    
    Booking b = new Booking(3005, sdf.parse("2021-03-14"), 1, 1002, 119, 4001, BookingStatus.PENDING);
    Booking b1 = new Booking(3006, sdf.parse(s), 1, 1076, 119, 4003, BookingStatus.PENDING);

    new Expectations() {
      {
        bDao.showBookingDetails(3005);
        result = b;
      }
    };

    new Expectations() {
      {
        bDao.showBookingDetails(3006);
        result = b1;
      }
    };

    new Expectations() {
      {
        mDao.findById(119);
        result = m;
      }
    };

    new Expectations() {
      {
        bDao.updateStatus(BookingStatus.ACCEPTED.name(), 3006);
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return mDao;
      }
    };

    new MockUp<BookingFactory>(){
      @Mock
      BookingDAO dao() {
        return bDao;
      }
    };

    Employee e = new Employee();
    String str = e.acceptDenyBooking(3005, BookingStatus.ACCEPTED.name(), 2000);
    assertEquals("Booking Status: Unchanged. Not Considered due to date mismatch.", str);

    String str1 = e.acceptDenyBooking(3006, BookingStatus.ACCEPTED.name(), 2000);
    assertEquals("Booking Status: Accepted. Status Updated", str1);

    String str2 = e.acceptDenyBooking(400, BookingStatus.DENIED.name(), 2000);
    assertEquals("Invalid Booking", str2);

    String str3 = e.acceptDenyBooking(3006, BookingStatus.DENIED.name(), 2000);
    assertEquals("Booking Status: Denied.", str3);

    String str4 = e.acceptDenyBooking(3006, BookingStatus.PENDING.name(), 2000);
    assertEquals("Booking Status: Unchanged.", str4);
  }
}
