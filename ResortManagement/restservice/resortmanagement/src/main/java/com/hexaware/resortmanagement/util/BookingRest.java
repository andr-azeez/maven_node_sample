package com.hexaware.resortmanagement.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hexaware.resortmanagement.factory.BookingFactory;
import com.hexaware.resortmanagement.model.Booking;
import com.hexaware.resortmanagement.model.BookingStatus;

/**
 * rest services for Booking class.
 */
@Path("/bookings")
public class BookingRest {
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * to show booking details.
   * @param id for booking id
   * @return booking object
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public final Booking showBookingDetails(@PathParam("id") final int id) {
    Booking b = BookingFactory.showBookingDetails(id);
    if (b == null) {
      throw new NotFoundException("Booking Not Found");
    } else {
      double amt = BookingFactory.retrieveBookingAmount(id);
      b.setTotalAmt(amt);
    }

    return b;
  }

  /**
   * to book an amenity.
   * @param id for bookingid
   * @param qty for quantity
   * @param empId for employeeid
   * @param amId for amenity id
   * @param memId for member id
   * @return string
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/book/{id}/{qty}/{empId}/{amId}/{memId}")
  public final String bookAnAmenity(@PathParam("id") final int id, @PathParam("qty") final int qty,
      @PathParam("empId") final int empId, @PathParam("amId") final int amId,
      @PathParam("memId") final int memId) {
    String msg = "Booking was Unsuccessful";
    String stat = "PENDING";
    Date bookingDate = new Date();
    try {
      String s = sdf.format(bookingDate);
      bookingDate = sdf.parse(s);
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
    }
    int i = BookingFactory.bookAmenity(id, bookingDate, qty, empId, amId, memId, stat);

    if (i > 0) {
      msg = "Amenity Booked Successfully";
    }

    return msg;
  }

  /**
   * to get all pending bookings.
   * @param empId employee id
   * @return booking list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/pending/{empId}")
  public final Booking[] listAllPendiBookings(@PathParam("empId") final int empId) {
    Booking[] bookings = BookingFactory.showAllPendingBookings(empId);

    for (int i = 0; i < bookings.length; i++) {
      double amt = BookingFactory.retrieveBookingAmount(bookings[i].getBookingId());
      bookings[i].setTotalAmt(amt);
    }

    return bookings;
  }

  /**
   * to get a member's booking history.
   * @param memId for member id
   * @return booking list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/mHistory/{memId}")
  public final Booking[] memberBookingHistory(@PathParam("memId") final int memId) {
    Booking[] history = BookingFactory.memberBookingHistory(memId);

    for (int i = 0; i < history.length; i++) {
      double amt = BookingFactory.retrieveBookingAmount(history[i].getBookingId());
      history[i].setTotalAmt(amt);
    }

    return history;
  }

  /**
   * to get employee's booking history.
   * @param empId for employee id
   * @return booking list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/eHistory/{empId}")
  public final Booking[] employeeBookingHistory(@PathParam("empId") final int empId) {
    Booking[] history = BookingFactory.empBookingHistory(empId);

    for (int i = 0; i < history.length; i++) {
      double amt = BookingFactory.retrieveBookingAmount(history[i].getBookingId());
      history[i].setTotalAmt(amt);
    }

    return history;
  }

  /**
   * to update amount into new order.
   * @param id for booking id
   * @param amt for amount
   * @return string
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/total/{id}/{amt}")
  public final String updateAmount(@PathParam("id") final int id, @PathParam("amt") final double amt) {
    String msg = "Unable to update amount";

    int i  = BookingFactory.updateBookingAmount(id, amt);

    if (i > 0) {
      msg = "Amount updated successfully";
    }

    return msg;
  }

  /**
   * to update status.
   * @param id for booking id
   * @param stat for status
   * @return string
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/status/{id}/{stat}")
  public final String updateStatus(@PathParam("id") final int id, @PathParam("stat") final String stat) {
    String msg = "Unable to update status";

    boolean flag = false;

    int i = 0;

    for (BookingStatus b: BookingStatus.values()) {
      if (b.name().equals(stat)) {
        flag = true;
      }
    }

    if (flag) {
      i = BookingFactory.updateStatus(id, stat);

      if (i > 0) {
        msg = "Status updated successfully";
      }
    }

    return msg;
  }

  /**
   * to get the total amount for a booking.
   * @param id for booking id
   * @return double
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/getAmount/{id}")
  public final double retrieveTotalAmount(@PathParam("id") final int id) {
    //String msg = "Unable to retrieve amount";
    double amt = BookingFactory.retrieveBookingAmount(id);

    // if (amt > 0) {
    //   msg = "Total Amount for Booking Id: " + id + " is " + amt;
    // }

    return amt;
  }

  /**
   * to get the current bookings.
   * @param id member id
   * @return booking list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/current/{id}")
  public final Booking[] listCurrentBookings(@PathParam("id") final int id) {
    Booking[] list = BookingFactory.listCurrentBookings(id);
    for (Booking b : list) {
      double amt = BookingFactory.retrieveBookingAmount(b.getBookingId());
      b.setTotalAmt(amt);
    }

    return list;
  }

  /**
   * to update quantity of existing booking.
   * @param id for booking id
   * @param qty for quantity
   * @return string
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/quantity/{id}/{qty}")
  public final String updateQuantity(@PathParam("id") final int id, @PathParam("qty") final int qty) {
    String msg = "Unable to update quantity";

    int i  = BookingFactory.updateQuantity(id, qty);

    if (i > 0) {
      msg = "Quantity updated successfully";
    }

    return msg;
  }
}
