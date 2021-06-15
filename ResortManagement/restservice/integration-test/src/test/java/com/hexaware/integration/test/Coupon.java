package com.hexaware.integration.test;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
