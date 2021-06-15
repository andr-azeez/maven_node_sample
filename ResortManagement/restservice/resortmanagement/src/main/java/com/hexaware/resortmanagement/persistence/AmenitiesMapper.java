package com.hexaware.resortmanagement.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hexaware.resortmanagement.model.Amenities;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * mapper class for Amenities.
 */
public class AmenitiesMapper implements ResultSetMapper<Amenities> {
  /**
   * map method.
   * @param idx for index
   * @param rs for Resultset
   * @param stmt for StatementContext
   * @return Amenities object
   * @throws SQLException for sql exception
   */
  public final Amenities map(final int idx, final ResultSet rs, final StatementContext stmt) throws SQLException {
    return new Amenities(rs.getInt("AMENITYID"), rs.getInt("EMPLOYEEID"), rs.getString("AMENITYNAME"),
        rs.getDouble("PRICE"), rs.getString("AMENITYCATEGORY"));
  }
}
