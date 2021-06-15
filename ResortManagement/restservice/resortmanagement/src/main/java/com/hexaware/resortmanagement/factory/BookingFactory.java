package com.hexaware.resortmanagement.factory;

import java.util.Date;
import java.util.List;

import com.hexaware.resortmanagement.model.Booking;
import com.hexaware.resortmanagement.persistence.BookingDAO;
import com.hexaware.resortmanagement.persistence.DBConnection;

/**
 * Factory class for booking.
 */
public class BookingFactory {
  /**
   * protected constructor.
   */
  protected BookingFactory() {

  }

  /**
   * dao method.
   * @return BookingDAO
   */
  private static BookingDAO dao() {
    DBConnection db = new DBConnection();
    return db.getConnect().onDemand(BookingDAO.class);
  }

  /**
   * to get the last row.
   * @return Booking
   */
  public static Booking findLastRow() {
    Booking b = dao().findLastRow();
    return b;
  }

  /**
   * to book an amenity.
   * @param id for booking id
   * @param bookingDate for date
   * @param qty for quantity
   * @param empId for employeeid
   * @param serId for amenity id
   * @param memId for member id
   * @param stat for status
   * @return int
   */
  public static int bookAmenity(final int id, final Date bookingDate, final int qty,
      final int empId, final int serId, final int memId, final String stat) {
    int res = dao().bookAmenity(id, bookingDate, qty, empId, serId, memId, stat);
    return res;
  }

  /**
   * to update booking amount.
   * @param id for booking id
   * @param amt for booking amount
   * @return int
   */
  public static int updateBookingAmount(final int id, final double amt) {
    int res = dao().updateBookingAmount(id, amt);
    return res;
  }

  /**
   * return a list of previous bookings for a member.
   * @param memId for member id
   * @return array
   */
  public static Booking[] memberBookingHistory(final int memId) {
    List<Booking> list = dao().memberBookingHistory(memId);
    return list.toArray(new Booking[list.size()]);
  }

  /**
   * return list of bookings handled by an employee.
   * @param empId for employee id
   * @return array
   */
  public static Booking[] empBookingHistory(final int empId) {
    List<Booking> list = dao().empBookingHistory(empId);
    return list.toArray(new Booking[list.size()]);
  }

  /**
   * to list all pending bookings.
   * @param id for employee id
   * @return array
   */
  public static Booking[] showAllPendingBookings(final int id) {
    List<Booking> list = dao().showAllPendingBookings(id);
    return list.toArray(new Booking[list.size()]);
  }

  /**
   * to update status of booking.
   * @param id for booking id
   * @param stat for status
   * @return int
   */
  public static int updateStatus(final int id, final String stat) {
    int res = dao().updateStatus(stat, id);
    return res;
  }

  /**
   * to retrieve the booking amount.
   * @param id for booking id
   * @return double
   */
  public static double retrieveBookingAmount(final int id) {
    double amt = dao().retrieveBookingAmount(id);
    return amt;
  }

  /**
   * to get the details of a particular booking.
   * @param id for booking id
   * @return booking object
   */
  public static Booking showBookingDetails(final int id) {
    Booking b = dao().showBookingDetails(id);
    return b;
  }

  /**
   * to get current bookings.
   * @param id for member id
   * @return array
   */
  public static Booking[] listCurrentBookings(final int id) {
    List<Booking> list = dao().listBookingsByToday(id);
    return list.toArray(new Booking[list.size()]);
  }

  /**
   * to update quantity.
   * @param id booking id
   * @param qty quantity
   * @return int
   */
  public static int updateQuantity(final int id, final int qty) {
    int res = dao().updateQuantity(qty, id);
    return res;
  }
}
