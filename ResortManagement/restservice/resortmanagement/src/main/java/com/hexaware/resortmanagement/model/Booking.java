package com.hexaware.resortmanagement.model;

import java.util.Date;
import java.util.Objects;

/**
 * Bookings class.
 */
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
  private int numRooms;
  /**
   * to store total amount.
   */
  private double totalAmt;
  /**
   * to store serviceId.
   */
  private int serviceId;
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
   * @return numRooms.
   */
  public final int getNumRooms() {
    return numRooms;
  }

  /**
   *
   * @param argNumRooms for numRooms.
   */
  public final void setNumRooms(final int argNumRooms) {
    this.numRooms = argNumRooms;
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
   * @return serviceId.
   */
  public final int getServiceId() {
    return serviceId;
  }

  /**
   *
   * @param argServiceId for serviceId.
   */
  public final void setServiceId(final int argServiceId) {
    this.serviceId = argServiceId;
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
    return Objects.hash(bookingId, bookingDate, numRooms, serviceId, status, employeeId, memberId, totalAmt);
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
        && Objects.equals(numRooms, other.numRooms)
        && Objects.equals(totalAmt, other.totalAmt)
        && Objects.equals(employeeId, other.employeeId)
        && Objects.equals(memberId, other.memberId)
        && Objects.equals(serviceId, other.serviceId)
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
   * @param argNumRooms for number of rooms
   * @param argEmployeeId for employee id
   * @param argMemberId for member id
   * @param argServiceId for service id
   * @param argStatus for booking status
   */
  public Booking(final int argBookingId, final Date argBookingDate, final int argNumRooms,
      final int argEmployeeId, final int argMemberId, final int argServiceId,
      final BookingStatus argStatus) {
    this.bookingId = argBookingId;
    this.bookingDate = argBookingDate;
    this.numRooms = argNumRooms;
    this.employeeId = argEmployeeId;
    this.memberId = argMemberId;
    this.serviceId = argServiceId;
    this.status = argStatus;
  }

  /**
   * toString method.
   * @return string
   */
  @Override
  public final String toString() {
    return "Booking Details: [Id: " + this.bookingId + ", Date: " + this.bookingDate + ", No. Of Rooms: " + this.numRooms
        + ", ServiceId: " + this.serviceId + ", MemberId: " + this.memberId + ", EmployeeId: " + this.employeeId
        + ", Total Amount: " + this.totalAmt + ", Booking Status: " + this.status + "]";
  }
}
