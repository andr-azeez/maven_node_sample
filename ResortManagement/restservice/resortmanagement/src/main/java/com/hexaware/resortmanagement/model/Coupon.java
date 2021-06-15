package com.hexaware.resortmanagement.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
// import java.time.ZoneId;
// import java.time.format.DateTimeFormatter;
// import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import com.hexaware.resortmanagement.factory.AmenitiesFactory;
import com.hexaware.resortmanagement.factory.CouponFactory;


/**
 * Coupon class.
 */
public class Coupon {
  /**
   * couponId.
   */
  private String couponId;
  /**
   * expiry date for coupon.
   */
  private Date expiryDate;
  /**
   * discount amount.
   */
  private double discAmount;
  /**
   * employee id.
   */
  private int amenityId;

  /**
   *
   * @return couponid.
   */
  public final String getCouponId() {
    return couponId;
  }

  /**
   *
   * @param argCouponId for couponId.
   */
  public final void setCouponId(final String argCouponId) {
    this.couponId = argCouponId;
  }

  /**
   *
   * @return expiry date.
   */
  public final Date getExpiryDate() {
    return expiryDate;
  }

  /**
   *
   * @param argExpiryDate for expiry date.
   */
  public final void setExpiryDate(final Date argExpiryDate) {
    this.expiryDate = argExpiryDate;
  }

  /**
   *
   * @return discount amount.
   */
  public final double getDiscAmount() {
    return discAmount;
  }

  /**
   *
   * @param argAmt for discount amount.
   */
  public final void setDiscAmount(final double argAmt) {
    this.discAmount = argAmt;
  }

  /**
   *
   * @return employeeId.
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
   * default constructor.
   */
  public Coupon() {

  }

  /**
   * parameterized constructor.
   * @param argCouponId for coupon id
   * @param argExpiryDate for expiry date
   * @param argAmt for amount
   * @param argAmenityId for employee id
   */
  public Coupon(final String argCouponId, final Date argExpiryDate, final double argAmt,
      final int argAmenityId) {
    this.couponId = argCouponId;
    this.expiryDate = argExpiryDate;
    this.discAmount = argAmt;
    this.amenityId = argAmenityId;
  }

  /**
   * hashcode method.
   * @return int
   */
  @Override
  public final int hashCode() {
    return Objects.hash(couponId, expiryDate, discAmount, amenityId);
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

    Coupon c = (Coupon) obj;
    if (Objects.equals(couponId, c.couponId) && Objects.equals(expiryDate, c.expiryDate)
        && Objects.equals(discAmount, c.discAmount) && Objects.equals(amenityId, c.amenityId)) {
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
    return "Coupon Details: [Id: " + this.couponId + ", Expiry Date: " + this.expiryDate + ", Discount: "
        + this.discAmount + ", Amenity Id: " + this.amenityId + "]";
  }

  /**
   * to add a new coupon.
   * @param cId for coupon id
   * @param exp for expiry date
   * @param amt for discount amount
   * @param amName for amenity name
   * @return string
   */
  public final String addCoupon(final String cId, final String exp, final double amt, final String amName) {
    String msg = "Unable to Add New Coupon";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date expDate = new Date();
    Date today = new Date();

    String temp = sdf.format(today);
    try {
      expDate = sdf.parse(exp);
      today = sdf.parse(temp);
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
    }

    // DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // LocalDate expDate = LocalDate.parse(exp, form);

    // LocalDate today = LocalDate.now();
    // Calendar cal = Calendar.getInstance();
    // cal.set(expDate.getYear(), expDate.getMonthValue() - 1, expDate.getDayOfMonth());
    // Date date = cal.getTime();

    Amenities am = AmenitiesFactory.listAmenityByName(amName);
    //if (am != null && expDate.isAfter(today)) {
    if (am != null && expDate.compareTo(today) > 0) {
      int x = CouponFactory.addNewCoupon(cId, expDate, amt, am.getAmenityId());
      if (x > 0) {
        msg = "Added a New Coupon Successfully";
      }
    }
    return msg;
  }

  /**
   * to extend the expiry date.
   * @param cName for coupon id
   * @param newDate for new expiry date
   * @return string
   */
  public final String updateExpiryDate(final String cName, final String newDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    String msg = "Expiry Date update unsuccessful";

    Date d = new Date();
    Date curDate = new Date();

    Coupon coup = CouponFactory.findById(cName);

    if (coup != null) {
      curDate = coup.getExpiryDate();

      try {
        d = sdf.parse(newDate);
      } catch (ParseException ex) {
        ex.getStackTrace();
      }

      if (d.after(curDate)) {
        int x = CouponFactory.extendExpiryDate(cName, d);
        if (x > 0) {
          msg = "Expiry Date updated Successfully";
        }
      } else {
        msg = "Date extension mismatch! New Date should be greater than Current Expiry Date!";
      }
    }
    return msg;
  }
}
