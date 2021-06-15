package com.hexaware.resortmanagement.factory;

import java.util.Date;
import java.util.List;

import com.hexaware.resortmanagement.model.Coupon;
import com.hexaware.resortmanagement.persistence.CouponDAO;
import com.hexaware.resortmanagement.persistence.DBConnection;

/**
 * factory class for Coupon.
 */
public class CouponFactory {
  /**
    * protected constructor.
    */
  protected CouponFactory() {

  }

  /**
   * dao method.
   * @return CouponDAO
   */
  private static CouponDAO dao() {
    DBConnection db = new DBConnection();
    return db.getConnect().onDemand(CouponDAO.class);
  }

  /**
   * list all coupons.
   * @return array
   */
  public static Coupon[] listAll() {
    List<Coupon> list = dao().listAll();
    return list.toArray(new Coupon[list.size()]);
  }

  /**
   * find a coupon by id.
   * @param id for coupon id
   * @return coupon
   */
  public static Coupon findById(final String id) {
    Coupon c = dao().findById(id);
    return c;
  }

  /**
   * list all coupons by a particular amenity.
   * @param id for amenity id
   * @return array
   */
  public static Coupon[] findByAmenity(final int id) {
    List<Coupon> list = dao().findByAmenity(id);
    return list.toArray(new Coupon[list.size()]);
  }

  /**
   * list of coupons availed by a member.
   * @param mem for member id
   * @return array
   */
  public static Coupon[] availedByMember(final int mem) {
    List<Coupon> list = dao().availedByMember(mem);
    return list.toArray(new Coupon[list.size()]);
  }

  /**
   * add a new coupon.
   * @param id coupon id
   * @param expiry expiry date
   * @param amt discount amount
   * @param amId employee id
   * @return int
   */
  public static int addNewCoupon(final String id, final Date expiry, final double amt, final int amId) {
    int res = dao().addNewCoupon(id, expiry, amt, amId);
    return res;
  }

  /**
   * to extend the expiry date.
   * @param id for coupon id
   * @param expiry for new expiry date
   * @return int
   */
  public static int extendExpiryDate(final String id, final Date expiry) {
    int res = dao().reviveExpiry(id, expiry);
    return res;
  }

  /**
   * to add the availed coupon.
   * @param id coupon id
   * @param mem member id
   * @return int
   */
  public static int availCoupon(final String id, final int mem) {
    int res = dao().availCoupon(id, mem);
    return res;
  }

  /**
   * list of coupons available to a member.
   * @param mem for member id
   * @param amId for amenityId
   * @param exp for expiry date
   * @return array
   */
  public static Coupon[] availableCoupons(final int mem, final int amId, final Date exp) {
    List<Coupon> list = dao().availableCoupons(mem, amId, exp);
    return list.toArray(new Coupon[list.size()]);
  }
}
