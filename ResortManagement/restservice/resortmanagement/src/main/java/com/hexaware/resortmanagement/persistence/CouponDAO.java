package com.hexaware.resortmanagement.persistence;

import java.util.Date;
import java.util.List;

import com.hexaware.resortmanagement.model.Coupon;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * DAO class for Coupon.
 */
public interface CouponDAO {
  /**
   * list all coupons.
   * @return a list
   */
  @SqlQuery("SELECT * FROM COUPON")
  @Mapper(CouponMapper.class)
  List<Coupon> listAll();

  /**
   * find a particular coupon.
   * @param id for coupon id
   * @return coupon
   */
  @SqlQuery("SELECT * FROM COUPON WHERE COUPONID = :id")
  @Mapper(CouponMapper.class)
  Coupon findById(@Bind("id") final String id);

  /**
   * find all coupons generated for a particular amenity.
   * @param id for amenityid.
   * @return list
   */
  @SqlQuery("SELECT * FROM COUPON WHERE AMENITYID = :id")
  @Mapper(CouponMapper.class)
  List<Coupon> findByAmenity(@Bind("id") final int id);

  /**
   * add a new coupon.
   * @param id for coupon id
   * @param expiry for expiry date
   * @param amt for discount amount
   * @param amId for amenity id
   * @return int
   */
  @SqlUpdate("INSERT INTO COUPON VALUES (:id, :expiry, :amt, :amId)")
  int addNewCoupon(@Bind("id") final String id, @Bind("expiry") final Date expiry,
      @Bind("amt") final double amt, @Bind("amId") final int amId);

  /**
   * to extend the expiry date.
   * @param id for coupon id.
   * @param newDate for the new exiry date
   * @return int
   */
  @SqlUpdate("UPDATE COUPON SET EXPIRYDATE = :newDate WHERE COUPONID = :id")
  int reviveExpiry(@Bind("id") final String id, @Bind("newDate") final Date newDate);

  /**
   * to list all coupons availed by a member.
   * @param mem for member id
   * @return list
   */
  @SqlQuery("SELECT COUPON.COUPONID, COUPON.EXPIRYDATE, COUPON.DISCOUNT, COUPON.AMENITYID "
      + "FROM COUPON LEFT JOIN AVAILED ON COUPON.COUPONID = AVAILED.COUPONID "
      + "WHERE AVAILED.MEMBERID = :mem")
  List<Coupon> availedByMember(@Bind("mem")final int mem);

  /**
   * to insert into availed table.
   * @param id for coupon id
   * @param mem for member id
   * @return int
   */
  @SqlUpdate("INSERT INTO AVAILED VALUES (:id, :mem)")
  int availCoupon(@Bind("id") final String id, @Bind("mem") final int mem);

  /**
   * to list all available unexpired coupons for a member.
   * @param mem for member id
   * @param amId for amenity id
   * @param exp for expiry date
   * @return list
   */
  @SqlQuery("SELECT COUPON.COUPONID, COUPON.EXPIRYDATE, COUPON.DISCOUNT, COUPON.AMENITYID "
      + "FROM COUPON LEFT JOIN AVAILED ON COUPON.COUPONID = AVAILED.COUPONID "
      + "WHERE AVAILED.MEMBERID <> :mem AND COUPON.AMENITYID = :amId "
      + "AND COUPON.EXPIRYDATE > :exp")
  @Mapper(CouponMapper.class)
  List<Coupon> availableCoupons(@Bind("mem") final int mem, @Bind("amId") final int amId,
      @Bind("exp") final Date exp);
}
