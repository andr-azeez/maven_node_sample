package com.hexaware.integration.test;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Amenities {
  /**
   * to store amenityId.
   */
  private int amenityId;
  /**
   * to store employeeId.
   */
  private int employeeId;
  /**
   * to store amenityName.
   */
  private String amenityName;
  /**
   * to store price.
   */
  private double price;
  /**
   * to store amenityCategory.
   */
  private String amenityCategory;

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
   *
   * @return amenityName.
   */
  public final String getAmenityName() {
    return amenityName;
  }

  /**
   *
   * @param argAmenityName for amenityName.
   */
  public final void setAmenityName(final String argAmenityName) {
    this.amenityName = argAmenityName;
  }

  /**
   *
   * @return price.
   */
  public final double getPrice() {
    return price;
  }

  /**
   *
   * @param argPrice for price.
   */
  public final void setPrice(final double argPrice) {
    this.price = argPrice;
  }

  /**
   *
   * @return amenityCategory.
   */
  public final String getAmenityCategory() {
    return amenityCategory;
  }

  /**
   *
   * @param argAmenityCategory for amenityCategory.
   */
  public final void setAmenityCategory(final String argAmenityCategory) {
    this.amenityCategory = argAmenityCategory;
  }

  /**
   * hashCode method.
   * @return int
   */
  @Override
  public final int hashCode() {
    return Objects.hash(amenityId, employeeId, amenityName, price, amenityCategory);
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
    Amenities ser = (Amenities) obj;
    if (Objects.equals(amenityId, ser.amenityId) && Objects.equals(employeeId, ser.employeeId)
        && Objects.equals(amenityName, ser.amenityName) && Objects.equals(price, ser.price)
        && Objects.equals(amenityCategory, ser.amenityCategory)) {
      return true;
    }
    return false;
  }

  /**
   * default constructor.
   */
  public Amenities() {

  }

  /**
   * parameterized constructor.
   * @param argAmenityId for amenity Id
   * @param argEmployeeId for employee id
   * @param argAmenityName for amenity name
   * @param argPrice for price
   * @param argAmenityCategory for serice category
   */
  public Amenities(final int argAmenityId, final int argEmployeeId, final String argAmenityName,
      final double argPrice, final String argAmenityCategory) {
    this.amenityId = argAmenityId;
    this.employeeId = argEmployeeId;
    this.amenityName = argAmenityName;
    this.price = argPrice;
    this.amenityCategory = argAmenityCategory;
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
