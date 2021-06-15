package com.hexaware.resortmanagement.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;

import com.hexaware.resortmanagement.factory.AmenitiesFactory;
import com.hexaware.resortmanagement.factory.BookingFactory;
//import com.hexaware.resortmanagement.factory.CouponFactory;
import com.hexaware.resortmanagement.factory.MemberFactory;
import com.hexaware.resortmanagement.persistence.AmenitiesDAO;
import com.hexaware.resortmanagement.persistence.BookingDAO;
//import com.hexaware.resortmanagement.persistence.CouponDAO;
import com.hexaware.resortmanagement.persistence.MembersDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * test suite for member.
 */
@RunWith(JMockit.class)
public class MemberTest {
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * test case for default constructor.
   * @throws ParseException parse exception
   */
  @Test
  public final void testMember() throws ParseException {
    Member m = new Member();
    m.setMemberId(103);
    m.setMemberName("Pavan Pai");
    m.setEmail("pavan@abc.com");
    m.setPhone("8383772");
    m.setPassKey("pavan123");
    m.setMembershipDate(sdf.parse("2021-02-23"));
    m.setWalletbalance(45000);
    assertEquals(103, m.getMemberId());
    assertEquals("Pavan Pai", m.getMemberName());
    assertEquals("pavan@abc.com", m.getEmail());
    assertEquals("8383772", m.getPhone());
    assertEquals("pavan123", m.getPassKey());
    assertEquals(sdf.parse("2021-02-23"), m.getMembershipDate());
    assertEquals(45000, m.getWalletbalance(), 1);
  }

  /**
   * testing parameterized constructor.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testMemberParameterizedConstructor() throws ParseException {
    Member m = new Member(103, "Pavan Pai", "pavan@abc.com", "8383772", 45000, "pavan123", sdf.parse("2021-02-23"));
    assertEquals(103, m.getMemberId());
    assertEquals("Pavan Pai", m.getMemberName());
    assertEquals("pavan@abc.com", m.getEmail());
    assertEquals("8383772", m.getPhone());
    assertEquals("pavan123", m.getPassKey());
    assertEquals(sdf.parse("2021-02-23"), m.getMembershipDate());
    assertEquals(45000, m.getWalletbalance(), 1);
  }

  /**
   * test for getMemberId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetMemberId() throws ParseException {
    Member m = new Member(103, "Pavan Pai", "pavan@abc.com", "8383772", 45000, "pavan123", sdf.parse("2021-02-23"));
    assertEquals(103, m.getMemberId());
  }

  /**
   * test for getMemberName.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetMemberName() throws ParseException {
    Member m = new Member(103, "Pavan Pai", "pavan@abc.com", "8383772", 45000, "pavan123", sdf.parse("2021-02-23"));
    assertEquals("Pavan Pai", m.getMemberName());
  }

  /**
   * test for getPhone.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetPhone() throws ParseException {
    Member m = new Member(103, "Pavan Pai", "pavan@abc.com", "8383772", 45000, "pavan123", sdf.parse("2021-02-23"));
    assertEquals("8383772", m.getPhone());
  }

  /**
   * test for getEmail.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetEmail() throws ParseException {
    Member m = new Member(103, "Pavan Pai", "pavan@abc.com", "8383772", 45000, "pavan123", sdf.parse("2021-02-23"));
    assertEquals("pavan@abc.com", m.getEmail());
  }

   /**
   * test for getPassKey.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetEmailAddr() throws ParseException {
    Member m = new Member(103, "Pavan Pai", "pavan@abc.com", "8383772", 45000, "pavan123", sdf.parse("2021-02-23"));
    assertEquals("pavan123", m.getPassKey());
  }

  /**
   * test for getWalletBalance.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetWalletBalance() throws ParseException {
    Member m = new Member(103, "Pavan Pai", "pavan@abc.com", "8383772", 45000, "pavan123", sdf.parse("2021-02-23"));
    assertEquals(45000, m.getWalletbalance(), 2);
  }

  /**
   * test for getMembershipDate.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetMemberShipDate() throws ParseException {
    Member m = new Member(103, "Pavan Pai", "pavan@abc.com", "8383772", 45000, "pavan123", sdf.parse("2021-02-23"));
    assertEquals(sdf.parse("2021-02-23"), m.getMembershipDate());
  }

  /**
   * test for hashCode method.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testHashCode() throws ParseException {
    final Member m1 = new Member(123, "Akanksha Sinha", "wi@we.c", "8394843", 45230, "aksinha123", sdf.parse("2021-01-03"));
    final Member m2 = new Member(103, "Pavan Pai", "pavan@abc.com", "8383772", 45000, "pavan123", sdf.parse("2021-02-23"));
    final Member m3 = new Member(123, "Akanksha Sinha", "wi@we.c", "8394843", 45230, "aksinha123", sdf.parse("2021-01-03"));

    assertEquals(m1.hashCode(), m3.hashCode());
    assertNotEquals(m1.hashCode(), m2.hashCode());
    assertNotEquals(m2.hashCode(), m3.hashCode());
  }

  /**
   * test for equals method.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testEquals() throws ParseException {
    final Member m1 = new Member(123, "Akanksha Sinha", "wi@we.c", "8394843", 45230, "aksinha123", sdf.parse("2021-01-03"));
    final Member m2 = new Member(103, "Pavan Pai", "pavan@abc.com", "8383772", 45000, "pavan123", sdf.parse("2021-02-23"));
    final Member m3 = new Member(123, "Akanksha Sinha", "wi@we.c", "8394843", 45230, "aksinha123", sdf.parse("2021-01-03"));

    assertTrue(m1.equals(m3));
    assertFalse(m2.equals(m1));
    assertFalse(m2.equals(m3));

    Member m = null;
    assertFalse(m1.equals(m));
  }

  /**
   * test to retrieve booking history of member.
   * @param dao for BookingDAO
   */
  @Test
  public final void testBookingHistory(@Mocked final BookingDAO dao) { //throws ParseException {
    Booking[] bList = new Booking[3];

    Date d1 = new Date();
    Date d2 = new Date();
    Date d3 = new Date();

    try {
      d1 = sdf.parse("2021-03-12");
      d2 = sdf.parse("2021-03-14");
      d3 = sdf.parse("2021-03-11");
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
    }

    bList[0] = new Booking(3001, d1, 1, 1002, 123, 4001, BookingStatus.PENDING);
    bList[1] = new Booking(3003, d2, 1, 1076, 123, 4002, BookingStatus.PENDING);
    bList[2] = new Booking(3004, d3, 1, 1166, 123, 4003, BookingStatus.ACCEPTED);

    new Expectations() {
      {
        dao.memberBookingHistory(123);
        result = bList;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return dao;
      }
    };

    Member m = new Member();
    Booking[] list = m.bookingHistory(123);
    assertArrayEquals(bList, list);
  }

  /**
   * test for cancelBooking.
   * @param mDao for MembersDAO
   * @param bDao for BookingDAO
   */
  @Test
  public final void testCancelBooking(@Mocked final MembersDAO mDao, @Mocked final BookingDAO bDao) {
    //Date d1 = new Date();
    Date md =  new Date();

  //  String temp = sdf.format(d1);

    try {
     // d1 = sdf.parse(temp);
      md = sdf.parse("2021-01-03");
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
    }

    Booking b = new Booking(3005, md, 1, 1002, 123, 4001, BookingStatus.PENDING);
    //Booking b1 = new Booking(3004, d1, 1, 1002, 123, 4001, BookingStatus.PENDING);

    Member m = new Member(123, "Akanksha Sinha", "wi@we.c", "8394843", 45230, "aksinha123", md);

    new Expectations() {
      {
        bDao.showBookingDetails(3005);
        result = b;
      }
    };

    // new Expectations() {
    //   {
    //     bDao.showBookingDetails(3004);
    //     result = b1;
    //   }
    // };

    new Expectations() {
      {
        mDao.findById(123);
        result = m;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return bDao;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return mDao;
      }
    };

    Member m1 = new Member();

    String str = m1.cancelBooking(3005);
    assertEquals("Cancellation was unsuccessful", str);

    // String str1 = m1.cancelBooking(3004);
    // assertEquals("Booking Cancelled Successfully", str1);
  }

  /**
   * test for booking an amenity.
   * @param aDao for AmenitiesDAO
   * @param mDao for MembersDAO
   * @param bDao for BookingDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testBookAnAmenity(@Mocked final AmenitiesDAO aDao, @Mocked final MembersDAO mDao,
      @Mocked final BookingDAO bDao) throws ParseException {

    String today = sdf.format(new Date());

    Amenities am1 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    Amenities am2 = new Amenities(4004, 1076, "POOL DAY", 1600, "POOL");

    Booking b = new Booking(3005, sdf.parse("2021-03-16"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);

    Member m = new Member(119, "Akanksha Sinha", "wi@we.c", "8394843", 45230, "aksinha123", sdf.parse("2021-01-12"));
    Member m2 = new Member(103, "Pavan Pai", "pavan@abc.com", "8383772", 450, "pavan123", sdf.parse("2021-02-23"));
    new Expectations() {
      {
        aDao.showDetails(4001);
        result = am1;
        aDao.showDetails(4004);
        result = am2;
      }
    };

    new Expectations() {
      {
        aDao.showDetails(5000);
        result = null;
      }
    };

    new Expectations() {
      {
        bDao.findLastRow();
        result = b;
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
        mDao.findById(103);
        result = m2;
      }
    };

    new Expectations() {
      {
        bDao.bookAmenity(3006, sdf.parse(today), 1, 1076, 4004, 119, BookingStatus.PENDING.name());
        result = 1;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return bDao;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return mDao;
      }
    };

    new MockUp<AmenitiesFactory>() {
      @Mock
      AmenitiesDAO dao() {
        return aDao;
      }
    };

    Member m1 = new Member();
    String res = m1.bookAnAmenity(4001, 0, 119);
    assertEquals("Unable to book the Amenity", res);

    String res1 = m1.bookAnAmenity(5000, 2, 103);
    assertEquals("Amenity not found. Please check and book again!", res1);

    String res2 = m1.bookAnAmenity(4001, 2, 103);
    assertEquals("Insufficient Balance. Please update your wallet", res2);

    String str = m1.bookAnAmenity(4004, 1, 119);
    assertEquals("Amenity Booked Successfully", str);
  }

  /**
   * test for registering a member.
   * @param dao for MembersDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testRegisterMember(@Mocked final MembersDAO dao) throws ParseException {
    Member m = new Member(134, "Akanksha Sinha", "wi@we.c", "8394843", 45230, "aksinha123", sdf.parse("2021-01-12"));
    Member m2 = new Member(132, "Anamika", "we@wo.c", "2839472", 34234, "anam123", sdf.parse("2020-09-10"));
    String d = sdf.format(new Date());

    new Expectations() {
      {
        dao.getMemberByEmail("we@wo.c");
        result = m2;
      }
    };

    new Expectations() {
      {
        dao.findLastRow();
        result = m;
      }
    };

    new Expectations() {
      {
        dao.registerMember(135, "Anirudh", "anirudh@abc.com", "394839843", 10000, "ani123", sdf.parse(d));
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    Member m1 = new Member();
    String res = m1.registerMember("Anamika", "2839472", "we@wo.c", "anam123", "2020-09-10");
    assertEquals("This Email Id already exists. Please try again!", res);

    String str = m1.registerMember("Anirudh", "394839843", "anirudh@abc.com", "ani123", "1989-09-23");
    assertEquals("Member Registered Successfully! Please login to continue", str);

    String res1 = m1.registerMember("Bhuvan Patil", "3242424", "bhuvan@dfe.c", "bhuvan123", "1990-03-03");
    assertEquals("Unable to Register! Please try again!", res1);
  }

  /**
   * test for updatePhone.
   * @param dao for MembersDAO
   */
  @Test
  public final void testUpdatePhone(@Mocked final MembersDAO dao) {
    new Expectations() {
      {
        dao.updatePhone(119, "92482022");
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    Member m1 = new Member();
    String res = m1.updatePhone(119, "92482022");
    assertEquals("Phone updated successfully", res);

    String str = m1.updatePhone(120, "23423424");
    assertEquals("Unable to update phone", str);
  }

  /**
   * test for updatePassword.
   * @param dao for MembersDAO
   */
  @Test
  public final void testUpdatePassword(@Mocked final MembersDAO dao) {
    new Expectations() {
      {
        dao.updatePassword(119, "pass123");
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    Member m1 = new Member();
    String res = m1.updatePassword(119, "pass123");
    assertEquals("Password updated successfully", res);

    String str = m1.updatePassword(120, "passv123");
    assertEquals("Unable to update password", str);
  }

  /**
   * test for updateWallet.
   * @param dao for MembersDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testUpdateWallet(@Mocked final MembersDAO dao) throws ParseException {
    Member m1 = new Member(132, "Akanksha Sinha", "wi@we.c", "8394843", 8000, "aksinha123", sdf.parse("2021-01-12"));

    new Expectations() {
      {
        dao.findById(132);
        result = m1;
      }
    };

    new Expectations() {
      {
        dao.updateWalletAmount(132, 8000, 4000);
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    Member m = new Member();
    String str = m.updateWallet(132, 4000);
    assertEquals("Balance updated", str);

    String res = m.updateWallet(143, 3000);
    assertEquals("Update unsuccessful", res);
  }
}
