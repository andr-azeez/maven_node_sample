package com.hexaware.resortmanagement.factory;

import java.util.List;

import com.hexaware.resortmanagement.model.Amenities;
import com.hexaware.resortmanagement.persistence.AmenitiesDAO;
import com.hexaware.resortmanagement.persistence.DBConnection;

/**
 * factory class for Amenities.
 */
public class AmenitiesFactory {
  /**
   * protected constructor.
   */
  protected AmenitiesFactory() {

  }

  /**
   * dao method.
   * @return AmenitiesDAO
   */
  private static AmenitiesDAO dao() {
    DBConnection db = new DBConnection();
    return db.getConnect().onDemand(AmenitiesDAO.class);
  }

  /**
   * to find the last row.
   * @return Amenities object
   */
  public static Amenities findLastRow() {
    Amenities am = dao().findLastRow();
    return am;
  }

  /**
   * adding new amenity.
   * @param id for amenity id
   * @param empId for employee id
   * @param name for amenity name
   * @param price for price
   * @param cat for category
   * @return int
   */
  public static int addNewAmenity(final int id, final int empId, final String name, final double price, final String cat) {
    int res = dao().addNewAmenity(id, empId, name, price, cat);
    return res;
  }

  /**
   * to list all Amenities.
   * @return array
   */
  public static Amenities[] listAllAmenities() {
    List<Amenities> list = dao().listAllAmenities();
    return list.toArray(new Amenities[list.size()]);
  }

  /**
   * to show amenity details.
   * @param id for amenity id
   * @return amenity
   */
  public static Amenities showDetails(final int id) {
    Amenities am = dao().showDetails(id);
    return am;
  }

  /**
   * to list amenities by category.
   * @param cat for category
   * @return array
   */
  public static Amenities[] listByCategory(final String cat) {
    List<Amenities> list = dao().listByCategory(cat);
    return list.toArray(new Amenities[list.size()]);
  }

  /**
   * to list amenities by employee.
   * @param id for employee id
   * @return array
   */
  public static Amenities[] listByEmployee(final int id) {
    List<Amenities> list = dao().listByEmployee(id);
    return list.toArray(new Amenities[list.size()]);
  }

  /**
   * to update the amenity price.
   * @param id for amenity id
   * @param price for new price
   * @return int
   */
  public static int updatePrice(final int id, final double price) {
    int res = dao().updatePrice(id, price);
    return res;
  }

  /**
   * list amenity by name.
   * @param name for amenity name
   * @return amenity
   */
  public static Amenities listAmenityByName(final String name) {
    Amenities am = dao().listByName(name);
    return am;
  }
}
