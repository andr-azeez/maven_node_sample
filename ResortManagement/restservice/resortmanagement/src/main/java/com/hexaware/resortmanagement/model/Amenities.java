package com.hexaware.resortmanagement.model;

import java.util.Objects;

import com.hexaware.resortmanagement.factory.AmenitiesFactory;

/**
 * Amenities class.
 */
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

  /**
   * to add a new Amenity.
   * @param argName for amenity name
   * @param argPrice for price
   * @param empId for employee id
   * @param argCat for category
   * @return String
   */
  public final String addNewAmenity(final String argName, final double argPrice, final int empId,
      final String argCat) {
    String msg = "Unable to add the Amenity. Try again";

    Amenities am = AmenitiesFactory.findLastRow();
    int id = 4001;
    if (am != null) {
      id = am.getAmenityId() + 1;
    }

    int res = AmenitiesFactory.addNewAmenity(id, empId, argName, argPrice, argCat);
    if (res > 0) {
      msg = "New Amenity added successfully";
    }
    return msg;
  }
}
