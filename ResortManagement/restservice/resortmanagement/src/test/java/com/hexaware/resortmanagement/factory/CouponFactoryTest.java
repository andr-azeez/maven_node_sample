package com.hexaware.resortmanagement.factory;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.hexaware.resortmanagement.model.Coupon;
import com.hexaware.resortmanagement.persistence.CouponDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * test class for CouponFactory.
 */
@RunWith(JMockit.class)
public class CouponFactoryTest {
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  /**
   * test for protected constructor.
   */
  @Test
  public final void testCouponFactory() {
    CouponFactory cf = new CouponFactory();
    CouponFactory cf1 = new CouponFactory();
    assertNotEquals(cf, cf1);
  }

  /**
   * test for listAll.
   * @param dao for CouponDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testListAll(@Mocked final CouponDAO dao) throws ParseException {
    Coupon[] list = new Coupon[4];
    list[0] = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    list[1] = new Coupon("FREE100", sdf.parse("2021-03-10"), 100, 4001);
    list[2] = new Coupon("FREEMARCH", sdf.parse("2021-03-31"), 100, 4011);
    list[3] = new Coupon("NEW100", sdf.parse("2021-03-10"), 100, 4003);

    new Expectations() {
      {
        dao.listAll();
        result = list;
      }
    };

    new MockUp<CouponFactory>() {
      @Mock
      CouponDAO dao() {
        return dao;
      }
    };

    Coupon[] list1 = CouponFactory.listAll();
    assertArrayEquals(list, list1);
  }

  /**
   * test for findById.
   * @param dao for CouponDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testFindById(@Mocked final CouponDAO dao) throws ParseException {
    Coupon c = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);

    new Expectations() {
      {
        dao.findById("FIRST50");
        result = c;
      }
    };

    new MockUp<CouponFactory>() {
      @Mock
      CouponDAO dao() {
        return dao;
      }
    };

    Coupon c1 = CouponFactory.findById("FIRST50");
    assertEquals(c, c1);
  }

  /**
   * test for findByAmenity.
   * @param dao for CouponDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testFindByAmenity(@Mocked final CouponDAO dao) throws ParseException {
    Coupon[] list = new Coupon[2];
    list[0] = new Coupon("FIRST50", sdf.parse("2021-03-09"), 50, 4001);
    list[1] = new Coupon("FREE100", sdf.parse("2021-03-10"), 100, 4001);
    
    new Expectations() {
      {
        dao.findByAmenity(4001);
        result = list;
      }
    };

    new MockUp<CouponFactory>() {
      @Mock
      CouponDAO dao() {
        return dao;
      }
    };

    Coupon[] list1 = CouponFactory.findByAmenity(4001);
    assertArrayEquals(list, list1);
  }

  /**
   * test for availedByMember.
   * @param dao for CouponDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testAvailedByMember(@Mocked final CouponDAO dao) throws ParseException {
    Coupon[] list = new Coupon[3];
    list[0] = new Coupon("FIRST50", sdf.parse("2021-03-12"), 50, 4003);
    list[1] = new Coupon("FREE100", sdf.parse("2021-03-10"), 100, 4001);
    list[2] = new Coupon("FREEMARCH", sdf.parse("2021-03-31"), 100, 4011);

    new Expectations() {
      {
        dao.availedByMember(119);
        result = list;
      }
    };

    new MockUp<CouponFactory>() {
      @Mock
      CouponDAO dao() {
        return dao;
      }
    };

    Coupon[] list1 = CouponFactory.availedByMember(119);
    assertArrayEquals(list, list1);
  }

  /**
   * test for addNewCoupon.
   * @param dao for CouponDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testAddNewCoupon(@Mocked final CouponDAO dao) throws ParseException {
    new Expectations() {
      {
        dao.addNewCoupon("RESORT500", sdf.parse("2021-04-02"), 500, 4013);
        result = 1;
      }
    };

    new MockUp<CouponFactory>() {
      @Mock
      CouponDAO dao() {
        return dao;
      }
    };

    int res = CouponFactory.addNewCoupon("RESORT500", sdf.parse("2021-04-02"), 500, 4013);
    assertEquals(1, res);
  }

  /**
   * test for extendExpiryDate.
   * @param dao for couponDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testExtendExpiryDate(@Mocked final CouponDAO dao) throws ParseException {
    new Expectations() {
      {
        dao.reviveExpiry("FIRST50", sdf.parse("2021-03-25"));
        result = 1;
      }
    };

    new MockUp<CouponFactory>() {
      @Mock
      CouponDAO dao() {
        return dao;
      }
    };

    int res = CouponFactory.extendExpiryDate("FIRST50", sdf.parse("2021-03-25"));
    assertEquals(1, res);
  }

  /**
   * test for availCoupon.
   * @param dao for CouponDAO
   */
  @Test
  public final void testAvailCoupon(@Mocked final CouponDAO dao) {
    new Expectations() {
      {
        dao.availCoupon("FREEMARCH", 119);
        result = 1;
      }
    };

    new MockUp<CouponFactory>() {
      @Mock
      CouponDAO dao() {
        return dao;
      }
    };

    int actual = CouponFactory.availCoupon("FREEMARCH", 119);
    assertEquals(1, actual);
  }

  /**
   * test for availableCoupons.
   * @param dao for CouponDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testAvailableCoupons(@Mocked final CouponDAO dao) throws ParseException {
    Coupon[] list = new Coupon[3];
    list[0] = new Coupon("FIRST50", sdf.parse("2021-03-27"), 50, 4001);
    list[1] = new Coupon("FREE100", sdf.parse("2021-04-10"), 100, 4001);
    list[2] = new Coupon("FREEMARCH", sdf.parse("2021-03-31"), 100, 4001);

    new Expectations() {
      {
        dao.availableCoupons(119, 4001, sdf.parse("2021-03-22"));
        result = list;
      }
    };

    new MockUp<CouponFactory>() {
      @Mock
      CouponDAO dao() {
        return dao;
      }
    };

    Coupon[] list1 = CouponFactory.availableCoupons(119, 4001, sdf.parse("2021-03-22"));
    assertArrayEquals(list, list1);
  }
}
