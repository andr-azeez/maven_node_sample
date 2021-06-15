package com.hexaware.resortmanagement.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hexaware.resortmanagement.model.Booking;
import com.hexaware.resortmanagement.model.BookingStatus;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * Mapper class for Booking.
 */
public class BookingMapper implements ResultSetMapper<Booking> {
  /**
   * map method.
   * @param idx for index
   * @param rs for Resultset
   * @param stmt for StatementContext
   * @return Booking object
   * @throws SQLException for sql exception
   */
  public final Booking map(final int idx, final ResultSet rs, final StatementContext stmt) throws SQLException {
    BookingStatus stat = BookingStatus.valueOf(rs.getString("BOOKINGSTATUS"));
    
    return new Booking(rs.getInt("BOOKINGID"), rs.getDate("BOOKINGDATE"), rs.getInt("NUMROOMS"),
        rs.getInt("EMPLOYEEID"), rs.getInt("MEMBERID"), rs.getInt("SERVICEID"), stat);
  }
}
