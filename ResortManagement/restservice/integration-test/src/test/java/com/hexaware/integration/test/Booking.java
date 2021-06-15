package com.hexaware.integration.test;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Booking {
  /**
   * to store bookingId.
   */
  private int bookingId;
  /**
   * to store bookingDate.
   */
  private Date bookingDate;
  /**
   * to store number of rooms.
   */
  private int quantity;
  /**
   * to store total amount.
   */
  private double totalAmt;
  /**
   * to store amenityId.
   */
  private int amenityId;
  /**
   * to store memberId.
   */
  private int memberId;
  /**
   * to store employeeId.
   */
  private int employeeId;

  /**
   * to store the booking status.
   */
  private BookingStatus status;

  /**
   *
   * @return booking status.
   */
  public final BookingStatus getStatus() {
    return status;
  }

  /**
   *
   * @param argStatus for status.
   */
  public final void setStatus(final BookingStatus argStatus) {
    this.status = argStatus;
  }

  /**
   *
   * @return bookingId.
   */
  public final int getBookingId() {
    return bookingId;
  }

  /**
   *
   * @param argBookingId for bookingId.
   */
  public final void setBookingId(final int argBookingId) {
    this.bookingId = argBookingId;
  }

  /**
   *
   * @return bookingDate.
   */
  public final Date getBookingDate() {
    return bookingDate;
  }

  /**
   *
   * @param argBookingDate for bookingDate.
   */
  public final void setBookingDate(final Date argBookingDate) {
    this.bookingDate = argBookingDate;
  }

  /**
   *
   * @return quantity.
   */
  public final int getQuantity() {
    return quantity;
  }

  /**
   *
   * @param argQuantity for quantity.
   */
  public final void setQuantity(final int argQuantity) {
    this.quantity = argQuantity;
  }

  /**
   *
   * @return totalAmt.
   */
  public final double getTotalAmt() {
    return totalAmt;
  }

  /**
   *
   * @param argTotalAmt for totalAmt.
   */
  public final void setTotalAmt(final double argTotalAmt) {
    this.totalAmt = argTotalAmt;
  }

  /**
   *
   * @return amenityId.
   */
  public final int getAmenityId() {
    return amenityId;
  }

  /**
   *
   * @param argAmenityId for amenityId.
   */
  public final void setAmenityId(final int argAmenityId) {
    this.amenityId = argAmenityId;
  }

  /**
   *
   * @return memberId.
   */
  public final int getMemberId() {
    return memberId;
  }

  /**
   *
   * @param argMemberId for memberId.
   */
  public final void setMemberId(final int argMemberId) {
    this.memberId = argMemberId;
  }

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
   * hashCode method.
   * @return int
   */
  @Override
  public final int hashCode() {
    return Objects.hash(bookingId, bookingDate, quantity, amenityId, status, employeeId, memberId, totalAmt);
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
    Booking other = (Booking) obj;
    if (Objects.equals(bookingId, other.bookingId)
        && Objects.equals(bookingDate, other.bookingDate)
        && Objects.equals(quantity, other.quantity)
        && Objects.equals(totalAmt, other.totalAmt)
        && Objects.equals(employeeId, other.employeeId)
        && Objects.equals(memberId, other.memberId)
        && Objects.equals(amenityId, other.amenityId)
        && Objects.equals(status, other.status)) {
      return true;
    }
    return false;
  }

  /**
   * default constructor.
   */
  public Booking() {

  }

  /**
   * parameterized constructor.
   * @param argBookingId for booking id
   * @param argBookingDate for booking date
   * @param argQuantity for number of rooms
   * @param argEmployeeId for employee id
   * @param argMemberId for member id
   * @param argAmenityId for amenity id
   * @param argStatus for booking status
   */
  public Booking(final int argBookingId, final Date argBookingDate, final int argQuantity,
      final int argEmployeeId, final int argMemberId, final int argAmenityId,
      final BookingStatus argStatus) {
    this.bookingId = argBookingId;
    this.bookingDate = argBookingDate;
    this.quantity = argQuantity;
    this.employeeId = argEmployeeId;
    this.memberId = argMemberId;
    this.amenityId = argAmenityId;
    this.status = argStatus;
  }
  
  public String toString() {
    String response = "";
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    try {
      response = ow.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return response;
  }
}
