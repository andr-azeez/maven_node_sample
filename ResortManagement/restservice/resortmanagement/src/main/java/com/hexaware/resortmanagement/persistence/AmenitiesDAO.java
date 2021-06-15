package com.hexaware.resortmanagement.persistence;

import java.util.List;

import com.hexaware.resortmanagement.model.Amenities;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * DAO class for Amenities.
 */
public interface AmenitiesDAO {
  /**
   * list of all amenities.
   * @return list
   */
  @SqlQuery("SELECT * FROM AMENITIES")
  @Mapper(AmenitiesMapper.class)
  List<Amenities> listAllAmenities();

  /**
   * show details for an amenity.
   * @param id for amenity id
   * @return amenity
   */
  @SqlQuery("SELECT * FROM AMENITIES WHERE AMENITYID = :id")
  @Mapper(AmenitiesMapper.class)
  Amenities showDetails(@Bind("id") final int id);

  /**
   * adding new amenity.
   * @param id for amenity id
   * @param empId for employee id
   * @param name for name
   * @param price for price
   * @param cat for category
   * @return int
   */
  @SqlUpdate("INSERT INTO AMENITIES VALUES (:id, :empId, :name, :price, :cat)")
  int addNewAmenity(@Bind("id") final int id, @Bind("empId") final int empId,
      @Bind("name") final String name, @Bind("price") final double price,
      @Bind("cat") final String cat);

  /**
   * to update price of amenity.
   * @param id for amenity id
   * @param newPrice for new price
   * @return int
   */
  @SqlUpdate("UPDATE AMENITIES SET PRICE = :newPrice WHERE AMENITYID = :id")
  int updatePrice(@Bind("id") final int id, @Bind("newPrice") final double newPrice);

  /**
   * list all amenities based on category.
   * @param cat for category
   * @return list
   */
  @SqlQuery("SELECT * FROM AMENITIES WHERE AMENITYCATEGORY = :cat")
  @Mapper(AmenitiesMapper.class)
  List<Amenities> listByCategory(@Bind("cat") final String cat);

  /**
   * list all amenities based on employee id.
   * @param id for employee id
   * @return list
   */
  @SqlQuery("SELECT * FROM AMENITIES WHERE EMPLOYEEID = :id")
  @Mapper(AmenitiesMapper.class)
  List<Amenities> listByEmployee(@Bind("id") final int id);

  /**
   * to find the last amenity id.
   * @return amenities object
   */
  @SqlQuery("SELECT * FROM AMENITIES WHERE AMENITYID = (SELECT MAX(AMENITYID) FROM AMENITIES)")
  @Mapper(AmenitiesMapper.class)
  Amenities findLastRow();

  /**
   * to get an amenity by name.
   * @param name amenity name
   * @return amenity
   */
  @SqlQuery("SELECT * FROM AMENITIES WHERE AMENITYNAME = :name")
  @Mapper(AmenitiesMapper.class)
  Amenities listByName(@Bind("name") final String name);
}
