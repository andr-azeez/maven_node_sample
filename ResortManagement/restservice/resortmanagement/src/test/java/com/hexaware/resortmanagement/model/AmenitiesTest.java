package com.hexaware.resortmanagement.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.hexaware.resortmanagement.factory.AmenitiesFactory;
import com.hexaware.resortmanagement.persistence.AmenitiesDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * test suite for Amenities.
 */
@RunWith(JMockit.class)
public class AmenitiesTest {
  /**
   * test for default constructor.
   */
  @Test
  public final void testAmenities() {
    Amenities am = new Amenities();
    am.setAmenityId(4001);
    am.setEmployeeId(1002);
    am.setAmenityName("SPA Pack 2");
    am.setPrice(2000);
    am.setAmenityCategory("SPA");

    assertEquals(4001, am.getAmenityId());
    assertEquals(1002, am.getEmployeeId());
    assertEquals("SPA Pack 2", am.getAmenityName());
    assertEquals(2000, am.getPrice(), 1);
    assertEquals("SPA", am.getAmenityCategory());
  }

  /**
   * test for parameterized constructor.
   */
  @Test
  public final void testAmenitiesParameterizedConstructor() {
    Amenities am = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");

    assertEquals(4001, am.getAmenityId());
    assertEquals(1002, am.getEmployeeId());
    assertEquals("SPA Pack 2", am.getAmenityName());
    assertEquals(2000, am.getPrice(), 1);
    assertEquals("SPA", am.getAmenityCategory());
  }

  /**
   * test for getAmenityId.
   */
  @Test
  public final void testGetAmenityId() {
    Amenities am = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    assertEquals(4001, am.getAmenityId());
  }

  /**
   * test for getAmenityName.
   */
  @Test
  public final void testGetAmenityName() {
    Amenities am = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    assertEquals("SPA Pack 2", am.getAmenityName());
  }

  /**
   * test for getAmenityCategory.
   */
  @Test
  public final void testGetAmenityCategory() {
    Amenities am = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    assertEquals("SPA", am.getAmenityCategory());
  }

  /**
   * test for getEmployeeId.
   */
  @Test
  public final void testGetEmployeeId() {
    Amenities am = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    assertEquals(2000, am.getPrice(), 1);
  }

  /**
   * test for getPrice.
   */
  @Test
  public final void testGetPrice() {
    Amenities am = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    assertEquals(1002, am.getEmployeeId());
  }

  /**
   * test for hashCode.
   */
  @Test
  public final void testHashCode() {
    Amenities am1 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    Amenities am2 = new Amenities(4004, 1076, "Pool Day", 1600, "POOL");
    Amenities am3 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");

    assertEquals(am1.hashCode(), am3.hashCode());
    assertNotEquals(am1.hashCode(), am2.hashCode());
    assertNotEquals(am3.hashCode(), am2.hashCode());
  }

  /**
   * test for equals.
   */
  @Test
  public final void testEquals() {
    Amenities am1 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    Amenities am2 = new Amenities(4004, 1076, "Pool Day", 1600, "POOL");
    Amenities am3 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");

    assertTrue(am1.equals(am3));
    assertFalse(am1.equals(am2));
    assertFalse(am2.equals(am3));
  }

  /**
   * test for setAmenityId.
   */
  @Test
  public final void testSetAmenityId() {
    Amenities am1 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    am1.setAmenityId(4005);
    assertEquals(4005, am1.getAmenityId());
  }

  /**
   * test for setAmenityName.
   */
  @Test
  public final void testSetAmenityName() {
    Amenities am1 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    am1.setAmenityName("POOL 2");
    assertEquals("POOL 2", am1.getAmenityName());
  }

  /**
   * test for setAmenityCategory.
   */
  @Test
  public final void testSetAmenityCategory() {
    Amenities am1 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    am1.setAmenityCategory("POOL");
    assertEquals("POOL", am1.getAmenityCategory());
  }

  /**
   * test for setPrice.
   */
  @Test
  public final void testSetPrice() {
    Amenities am1 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    am1.setPrice(500);
    assertEquals(500, am1.getPrice(), 1);
  }

  /**
   * test for setEmployeeId.
   */
  @Test
  public final void testSetEmployeeId() {
    Amenities am1 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");
    am1.setEmployeeId(1166);
    assertEquals(1166, am1.getEmployeeId());
  }

  /**
   * test for addNewAmenity.
   * @param dao for AmenitiesDAO
   */
  @Test
  public final void testAddNewAmenity(@Mocked final AmenitiesDAO dao) {
    Amenities am1 = new Amenities(4001, 1002, "SPA Pack 2", 2000, "SPA");

    new Expectations() {
      {
        dao.findLastRow();
        result = am1;
      }
    };

    new Expectations() {
      {
        dao.addNewAmenity(4002, 1166, "POOL 3 hr", 600, "POOL");
        result = 1;
      }
    };

    new MockUp<AmenitiesFactory>() {
      @Mock
      AmenitiesDAO dao() {
        return dao;
      }
    };

    Amenities a = new Amenities();
    String str = a.addNewAmenity("POOL 3 hr", 600, 1166, "POOL");
    assertEquals("New Amenity added successfully", str);

    String res = a.addNewAmenity("TOUR PACK 2", 2300, 3737, "TOUR");
    assertEquals("Unable to add the Amenity. Try again", res);
  }
}
