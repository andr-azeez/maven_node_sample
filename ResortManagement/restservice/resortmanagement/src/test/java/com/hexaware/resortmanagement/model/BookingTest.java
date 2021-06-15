package com.hexaware.resortmanagement.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

/**
 * test class for Booking.
 */
public class BookingTest {
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * test for default constructor.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testBooking() throws ParseException {
    Booking b = new Booking();
    b.setBookingId(3001);
    b.setBookingDate(sdf.parse("2021-01-10"));
    b.setQuantity(1);
    b.setTotalAmt(2000);
    b.setMemberId(119);
    b.setEmployeeId(1002);
    b.setAmenityId(4001);
    b.setStatus(BookingStatus.ACCEPTED);

    assertEquals(3001, b.getBookingId());
    assertEquals(sdf.parse("2021-01-10"), b.getBookingDate());
    assertEquals(1, b.getQuantity());
    assertEquals(2000, b.getTotalAmt(), 1);
    assertEquals(119, b.getMemberId());
    assertEquals(1002, b.getEmployeeId());
    assertEquals(4001, b.getAmenityId());
    assertEquals(BookingStatus.ACCEPTED, b.getStatus());
  }

  /**
   * test for parameterized constructor.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testBookingParameterizedConstructor() throws ParseException {
    Booking b = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    assertEquals(3001, b.getBookingId());
    assertEquals(sdf.parse("2021-01-10"), b.getBookingDate());
    assertEquals(1, b.getQuantity());
    assertEquals(119, b.getMemberId());
    assertEquals(1002, b.getEmployeeId());
    assertEquals(4001, b.getAmenityId());
    assertEquals(BookingStatus.ACCEPTED, b.getStatus());
  }

  /**
   * test for getBookingId.
   * @throws ParseException for parse Exception
   */
  @Test
  public final void testGetBookingId() throws ParseException {
    Booking b = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    assertEquals(3001, b.getBookingId());
  }

  /**
   * test for getBookingDate.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetBookingDate() throws ParseException {
    Booking b = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    assertEquals(sdf.parse("2021-01-10"), b.getBookingDate());
  }

  /**
   * test for getQuantity.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetQuantity() throws ParseException {
    Booking b = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    assertEquals(1, b.getQuantity());
  }

  /**
   * test for getMemberId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetMemberId() throws ParseException {
    Booking b = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    assertEquals(119, b.getMemberId());
  }

  /**
   * test for getEmployeeId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetEmployeeId() throws ParseException {
    Booking b = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    assertEquals(1002, b.getEmployeeId());
  }

  /**
   * test for getAmenityId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetAmenityId() throws ParseException {
    Booking b = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    assertEquals(4001, b.getAmenityId());
  }

  /**
   * test for getStatus.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetStatus() throws ParseException {
    Booking b = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    assertEquals(BookingStatus.ACCEPTED, b.getStatus());
  }

  /**
   * test for getTotalAmount.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetTotalAmount() throws ParseException {
    Booking b = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    b.setTotalAmt(2000);
    assertEquals(2000, b.getTotalAmt(), 1);
  }

  /**
   * test for hashCode.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testHashCode() throws ParseException {
    Booking b1 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    Booking b2 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    Booking b3 = new Booking(3004, sdf.parse("2021-03-12"), 2, 4001, 112, 1002, BookingStatus.CANCELLED);

    assertEquals(b1.hashCode(), b2.hashCode());
    assertNotEquals(b1.hashCode(), b3.hashCode());
    assertNotEquals(b2.hashCode(), b3.hashCode());
  }

  /**
   * test for equals.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testEquals() throws ParseException {
    Booking b1 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    Booking b2 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    Booking b3 = new Booking(3004, sdf.parse("2021-03-12"), 2, 4001, 112, 1002, BookingStatus.CANCELLED);

    assertTrue(b1.equals(b2));
    assertFalse(b1.equals(b3));
    assertFalse(b2.equals(b3));
  }

  /**
   * test for setBookingId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetBookingId() throws ParseException {
    Booking b1 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    b1.setBookingId(3003);
    assertEquals(3003, b1.getBookingId());
  }

  /**
   * test for setBookingDate.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetBookingDate() throws ParseException {
    Booking b1 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    b1.setBookingDate(sdf.parse("2021-02-10"));
    assertEquals(sdf.parse("2021-02-10"), b1.getBookingDate());
  }

  /**
   * test for setStatus.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetStatus() throws ParseException {
    Booking b1 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    b1.setStatus(BookingStatus.DENIED);
    assertEquals(BookingStatus.DENIED, b1.getStatus());
  }

  /**
   * test for setQuantity.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetQuantity() throws ParseException {
    Booking b1 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    b1.setQuantity(2);
    assertEquals(2, b1.getQuantity());
  }

  /**
   * test for setAmenityId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetAmenityId() throws ParseException {
    Booking b1 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    b1.setAmenityId(4003);
    assertEquals(4003, b1.getAmenityId());
  }

  /**
   * test for setMemberId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetMemberId() throws ParseException {
    Booking b1 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    b1.setMemberId(133);
    assertEquals(133, b1.getMemberId());
  }

  /**
   * test for setEmployeeId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetEmployeeId() throws ParseException {
    Booking b1 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    b1.setEmployeeId(1166);
    assertEquals(1166, b1.getEmployeeId());
  }

  /**
   * test for setTotalAmt.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetTotalAmt() throws ParseException {
    Booking b1 = new Booking(3001, sdf.parse("2021-01-10"), 1, 1002, 119, 4001, BookingStatus.ACCEPTED);
    b1.setTotalAmt(2000);
    assertEquals(2000, b1.getTotalAmt(), 1);
  }
}
