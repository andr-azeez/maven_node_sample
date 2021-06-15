package com.hexaware.resortmanagement.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.hexaware.resortmanagement.factory.AmenitiesFactory;
import com.hexaware.resortmanagement.factory.CouponFactory;
import com.hexaware.resortmanagement.persistence.AmenitiesDAO;
import com.hexaware.resortmanagement.persistence.CouponDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * test class for Coupon.
 */
@RunWith(JMockit.class)
public class CouponTest {
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * test for default constructor.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testCoupon() throws ParseException {
    Coupon c = new Coupon();
    c.setCouponId("FIRST50");
    c.setExpiryDate(sdf.parse("2021-03-09"));
    c.setDiscAmount(50);
    c.setAmenityId(4001);

    assertEquals("FIRST50", c.getCouponId());
    assertEquals(sdf.parse("2021-03-09"), c.getExpiryDate());
    assertEquals(50, c.getDiscAmount(), 1);
    assertEquals(4001, c.getAmenityId());
  }

  /**
   * test for default constructor.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testCouponParameterizedConstructor() throws ParseException {
    Coupon c = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);

    assertEquals("FIRST50", c.getCouponId());
    assertEquals(sdf.parse("2021-03-09"), c.getExpiryDate());
    assertEquals(50, c.getDiscAmount(), 1);
    assertEquals(4001, c.getAmenityId());
  }

  /**
   * test for getCouponId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetCouponId() throws ParseException {
    Coupon c = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    assertEquals("FIRST50", c.getCouponId());
  }

  /**
   * test for getExpiryDate.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetExpiryDate() throws ParseException {
    Coupon c = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    assertEquals(sdf.parse("2021-03-09"), c.getExpiryDate());
  }

  /**
   * test for getDiscAmount.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetDiscAmount() throws ParseException {
    Coupon c = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    assertEquals(50, c.getDiscAmount(), 1);
  }

  /**
   * test for getAmenityId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testGetAmenityId() throws ParseException {
    Coupon c = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    assertEquals(4001, c.getAmenityId());
  }

  /**
   * test for hashCode.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testHashCode() throws ParseException {
    Coupon c1 = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    Coupon c2 = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    Coupon c3 = new Coupon("NEW100", sdf.parse("2021-03-10"), 100, 4003);

    assertEquals(c1.hashCode(), c2.hashCode());
    assertNotEquals(c2.hashCode(), c3.hashCode());
    assertNotEquals(c1.hashCode(), c3.hashCode());
  }

  /**
   * test for equals.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testEquals() throws ParseException {
    Coupon c1 = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    Coupon c2 = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    Coupon c3 = new Coupon("NEW100", sdf.parse("2021-03-10"), 100, 4003);

    assertTrue(c1.equals(c2));
    assertFalse(c2.equals(c3));
    assertFalse(c1.equals(c3));
  }

  /**
   * test for setCouponId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetCouponId() throws ParseException {
    Coupon c1 = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    c1.setCouponId("FREEMARCH");
    assertEquals("FREEMARCH", c1.getCouponId());
  }

  /**
   * test for setExpiryDate.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetExpiryDate() throws ParseException {
    Coupon c1 = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    c1.setExpiryDate(sdf.parse("2021-04-03"));
    assertEquals(sdf.parse("2021-04-03"), c1.getExpiryDate());
  }

  /**
   * test for setDiscAmount.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetDiscAmount() throws ParseException {
    Coupon c1 = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    c1.setDiscAmount(100);
    assertEquals(100, c1.getDiscAmount(), 1);
  }

  /**
   * test for setAmenityId.
   * @throws ParseException for parse exception
   */
  @Test
  public final void testSetAmenityId() throws ParseException {
    Coupon c1 = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    c1.setAmenityId(4003);
    assertEquals(4003, c1.getAmenityId());
  }

  /**
   * test for addCoupon.
   * @param dao for CouponDAO
   * @param aDao for AmenitiesDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testAddCoupon(@Mocked final CouponDAO dao, @Mocked final AmenitiesDAO aDao) throws ParseException {
    Amenities am1 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");

    new Expectations() {
      {
        aDao.listByName("SPA Pack 2");
        result = am1;
      }
    };

    new Expectations() {
      {
        dao.addNewCoupon("FREE2021", sdf.parse("2021-04-02"), 200, 4001);
        result = 1;
      }
    };

    new MockUp<AmenitiesFactory>() {
      @Mock
      AmenitiesDAO dao() {
        return aDao;
      }
    };

    new MockUp<CouponFactory>() {
      @Mock
      CouponDAO dao() {
        return dao;
      }      
    };

    Coupon c = new Coupon();
    String str = c.addCoupon("FREE2021", "2021-03-02", 200, "SPA Pack 2");
    assertEquals("Unable to Add New Coupon", str);

    String str1 = c.addCoupon("FREE2021", "2021-04-02", 200, "SPA Pack 2");
    assertEquals("Added a New Coupon Successfully", str1);
  }

  /**
   * test for updateExpiryDate.
   * @param dao for CouponDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testUpdateExpiryDate(@Mocked final CouponDAO dao) throws ParseException {
    Coupon c1 = new Coupon("FIRST50", sdf.parse("2021-03-18"), 50, 4001);

    new Expectations() {
      {
        dao.findById("FIRST50");
        result = c1;
      }
    };

    new Expectations() {
      {
        dao.reviveExpiry("FIRST50", sdf.parse("2021-04-02"));
        result = 1;
      }
    };

    new MockUp<CouponFactory>() {
      @Mock
      CouponDAO dao() {
        return dao;
      }
    };

    Coupon c = new Coupon();
    String str = c.updateExpiryDate("FIRST50", "2021-04-02");
    assertEquals("Expiry Date updated Successfully", str);

    String str1 = c.updateExpiryDate("FIRST50", "2021-03-02");
    assertEquals("Date extension mismatch! New Date should be greater than Current Expiry Date!", str1);

    String str2 = c.updateExpiryDate("FREEMARCH", "2021-04-02");
    assertEquals("Expiry Date update unsuccessful", str2);
  }
}
