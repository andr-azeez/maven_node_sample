package com.hexaware.resortmanagement.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hexaware.resortmanagement.model.Coupon;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * mapper class for coupon.
 */
public class CouponMapper implements ResultSetMapper<Coupon> {
   /**
   * map method.
   * @param idx for index
   * @param rs for Resultset
   * @param stmt for StatementContext
   * @return Coupon
   * @throws SQLException for SQLException
   */
  public final Coupon map(final int idx, final ResultSet rs, final StatementContext stmt) throws SQLException {
    return new Coupon(rs.getString("COUPONID"), rs.getDate("EXPIRYDATE"), rs.getDouble("DISCOUNT"), rs.getInt("AMENITYID"));
  }
}
