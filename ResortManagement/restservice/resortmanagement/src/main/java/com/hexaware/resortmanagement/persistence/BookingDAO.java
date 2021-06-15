package com.hexaware.resortmanagement.persistence;

import java.util.Date;
import java.util.List;

import com.hexaware.resortmanagement.model.Booking;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * DAO class for bookings.
 */
public interface BookingDAO {
  /**
   * book a room.
   * @param id for booking id
   * @param bookingDate for booking date
   * @param qty for quantity
   * @param empId for employee id
   * @param serId for amenities id
   * @param memId for member id
   * @param stat for booking status
   * @return int
   */
//   @SqlUpdate("INSERT INTO BOOKINGS (BOOKINGID, BOOKINGDATE, NUMROOMS, "
//       + "EMPLOYEEID, SERVICEID, MEMBERID, BOOKINGSTATUS) VALUES (:id, DATE_ADD(:bookingDate, INTERVAL 2 MONTH), "
//       + ":qty, :empId, :serId, :memId, :stat)")
  @SqlUpdate("INSERT INTO BOOKINGS (BOOKINGID, BOOKINGDATE, NUMROOMS, "
      + "EMPLOYEEID, SERVICEID, MEMBERID, BOOKINGSTATUS) VALUES (:id, DATE_ADD(:bookingDate, INTERVAL 1 DAY), "
      + ":qty, :empId, :serId, :memId, :stat)")
  int bookAmenity(@Bind("id") final int id, @Bind("bookingDate") final Date bookingDate,
      @Bind("qty") final int qty, @Bind("empId") final int empId,
      @Bind("serId") final int serId, @Bind("memId") final int memId, @Bind("stat") final String stat);
  /**
   * to update booking amount.
   * @param id for booking id
   * @param amt for booking amount
   * @return int
   */
  @SqlUpdate("UPDATE BOOKINGS SET TOTALAMT = :amt WHERE BOOKINGID = :id")
  int updateBookingAmount(@Bind("id") final int id, @Bind("amt") final double amt);

  /**
   * list all bookings by a member.
   * @param id member id
   * @return list
   */
  @SqlQuery("SELECT * FROM BOOKINGS WHERE MEMBERID = :id")
  @Mapper(BookingMapper.class)
  List<Booking> memberBookingHistory(@Bind("id") final int id);

   /**
   * list all bookings serviced by an employee.
   * @param id employee id
   * @return list
   */
  @SqlQuery("SELECT * FROM BOOKINGS WHERE EMPLOYEEID = :id")
  @Mapper(BookingMapper.class)
  List<Booking> empBookingHistory(@Bind("id") final int id);

  /**
   * to get details for a booking.
   * @param id for booking id
   * @return booking object
   */
  @SqlQuery("SELECT * FROM BOOKINGS WHERE BOOKINGID = :id")
  @Mapper(BookingMapper.class)
  Booking showBookingDetails(@Bind("id") final int id);

  /**
   * to get the booking amount.
   * @param id for booking id
   * @return booking amount
   */
  @SqlQuery("SELECT TOTALAMT FROM BOOKINGS WHERE BOOKINGID = :id")
  double retrieveBookingAmount(@Bind("id") final int id);

  /**
   * to find the last booking id.
   * @return booking object
   */
  @SqlQuery("SELECT * FROM BOOKINGS WHERE BOOKINGID = (SELECT MAX(BOOKINGID) FROM BOOKINGS)")
  @Mapper(BookingMapper.class)
  Booking findLastRow();

  /**
   * to retrieve all pending bookings.
   * @param id for employee id
   * @return list of pending bookings
   */
  @SqlQuery("SELECT * FROM BOOKINGS WHERE EMPLOYEEID = :id AND BOOKINGSTATUS = 'PENDING'"
        + " AND BOOKINGDATE >= CURRENT_DATE()")
  @Mapper(BookingMapper.class)
  List<Booking> showAllPendingBookings(final int id);

  /**
   * query to update booking status.
   * @param status for booking status
   * @param id for booking id
   * @return int
   */
  @SqlUpdate("UPDATE BOOKINGS SET BOOKINGSTATUS = :status WHERE BOOKINGID = :id")
  int updateStatus(@Bind("status") final String status, @Bind("id") final int id);

  /**
   * to get list of current bookings.
   * @param id for member id
   * @return list
   */
  @SqlQuery("SELECT * FROM BOOKINGS WHERE BOOKINGDATE = CURRENT_DATE() AND BOOKINGSTATUS = 'PENDING'"
      + " AND MEMBERID = :id")
  @Mapper(BookingMapper.class)
  List<Booking> listBookingsByToday(@Bind("id") final int id);

  /**
   * to edit the booking.
   * @param qty Quantity
   * @param id BookingId
   * @return int
   */
  @SqlUpdate("UPDATE BOOKINGS SET NUMROOMS = :qty WHERE BOOKINGID = :id")
  int updateQuantity(@Bind("qty") final int qty, @Bind("id") final int id);
}
