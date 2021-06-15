package com.hexaware.resortmanagement.factory;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.hexaware.resortmanagement.model.Amenities;
import com.hexaware.resortmanagement.persistence.AmenitiesDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * test class for AmenitiesFactory.
 */
@RunWith(JMockit.class)
public class AmenitiesFactoryTest {
  /**
   * test for protected constructor.
   */
  @Test
  public final void testAmenitiesFactory() {
    AmenitiesFactory am =  new AmenitiesFactory();
    AmenitiesFactory am1 = new AmenitiesFactory();
    assertNotEquals(am, am1);
  }

  /**
   * test for findLastRow.
   * @param dao for AmenitiesDAO
   */
  @Test
  public final void testFindLastRow(@Mocked final AmenitiesDAO dao) {
    Amenities am1 = new Amenities(4015, 1002, "SPA Pack 2", 2000, "SPA");

    new Expectations() {
      {
        dao.findLastRow();
        result = am1;
      }
    };

    new MockUp<AmenitiesFactory>() {
      @Mock
      AmenitiesDAO dao() {
        return dao;
      }
    };

    Amenities am = AmenitiesFactory.findLastRow();
    assertEquals(am1, am);
  }

  /**
   * test for addNewAmenity.
   * @param dao for AmenitiesDAO
   */
  @Test
  public final void testAddNewAmenity(@Mocked final AmenitiesDAO dao) {
    new Expectations() {
      {
        dao.addNewAmenity(4016, 1166, "SPA Pack 3", 1600, "SPA");
        result = 1;
      }
    };

    new MockUp<AmenitiesFactory>() {
      @Mock
      AmenitiesDAO dao() {
        return dao;
      }
    };

    int res = AmenitiesFactory.addNewAmenity(4016, 1166, "SPA Pack 3", 1600, "SPA");
    assertEquals(1, res);
  }

  /**
   * test for listAllAmenities.
   * @param dao for AmenitiesDAO
   */
  @Test
  public final void testListAllAmenities(@Mocked final AmenitiesDAO dao) {
    Amenities[] alist = new Amenities[4];
    alist[0] = new Amenities(4001, 1002, "SPA PAck 2", 2000, "SPA");
    alist[1] = new Amenities(4002, 1076, "Pool Day", 1600, "POOL");
    alist[2] = new Amenities(4003, 1166, "Local Tour Pack 1", 1200, "TOUR");
    alist[3] = new Amenities(4004, 1076, "Room Service", 1350, "RESTUARANT");

    new Expectations() {
      {
        dao.listAllAmenities();
        result = alist;
      }
    };

    new MockUp<AmenitiesFactory>() {
      @Mock
      AmenitiesDAO dao() {
        return dao;
      }
    };

    Amenities[] list = AmenitiesFactory.listAllAmenities();
    assertArrayEquals(alist, list);
  }

  /**
   * test for showDetails.
   * @param dao for AmenitiesDAO
   */
  @Test
  public final void testShowDetails(@Mocked final AmenitiesDAO dao) {
    Amenities am1 = new Amenities(4015, 1002, "SPA Pack 2", 2000, "SPA");

    new Expectations() {
      {
        dao.showDetails(4015);
        result = am1;
      }
    };

    new MockUp<AmenitiesFactory>() {
      @Mock
      AmenitiesDAO dao() {
        return dao;
      }
    };

    Amenities am = AmenitiesFactory.showDetails(4015);
    assertEquals(am1, am);
  }

  /**
   * test for listByCategory.
   * @param dao for AmenitiesDAO
   */
  @Test
  public final void testListByCategory(@Mocked final AmenitiesDAO dao) {
    Amenities[] alist = new Amenities[4];
    alist[0] = new Amenities(4001, 1002, "SPA PAck 2", 2000, "SPA");
    alist[1] = new Amenities(4002, 1076, "SPA PAck 3", 1600, "SPA");
    alist[2] = new Amenities(4003, 1166, "Special SPA HR", 1200, "SPA");
    alist[3] = new Amenities(4004, 1076, "SPA Care Pack", 1350, "SPA");

    new Expectations() {
      {
        dao.listByCategory("SPA");
        result = alist;
      }
    };

    new MockUp<AmenitiesFactory>() {
      @Mock
      AmenitiesDAO dao() {
        return dao;
      }
    };

    Amenities[] list = AmenitiesFactory.listByCategory("SPA");
    assertArrayEquals(alist, list);
  }

  /**
   * test for listByEmployee.
   * @param dao for AmenitiesDAO
   */
  @Test
  public final void testListByEmployee(@Mocked final AmenitiesDAO dao) {
    Amenities[] alist = new Amenities[4];
    alist[0] = new Amenities(4001, 1002, "SPA PAck 2", 2000, "SPA");
    alist[1] = new Amenities(4004, 1002, "SPA PAck 3", 1600, "SPA");
    alist[2] = new Amenities(4005, 1002, "Special SPA HR", 1200, "SPA");
    alist[3] = new Amenities(4008, 1002, "SPA Care Pack", 1350, "SPA");

    new Expectations() {
      {
        dao.listByEmployee(1002);
        result = alist;
      }
    };

    new MockUp<AmenitiesFactory>() {
      @Mock
      AmenitiesDAO dao() {
        return dao;
      }
    };

    Amenities[] list = AmenitiesFactory.listByEmployee(1002);
    assertArrayEquals(alist, list);
  }

  /**
   * test for updatePrice.
   * @param dao for AmenitiesDAO
   */
  @Test
  public final void testUpdatePrice(@Mocked final AmenitiesDAO dao) {
    new Expectations() {
      {
        dao.updatePrice(4001, 2300);
        result = 1;
      }
    };

    new MockUp<AmenitiesFactory>() {
      @Mock
      AmenitiesDAO dao() {
        return dao;
      }
    };

    int res = AmenitiesFactory.updatePrice(4001, 2300);
    assertEquals(1, res);
  }

  /**
   * test for listAmenityByName
   * @param dao for AmenitiesDAO
   */
  @Test
  public final void testListAmenityByName(@Mocked final AmenitiesDAO dao) {
    Amenities am1 = new Amenities(4015, 1002, "SPA Pack 2", 2000, "SPA");

    new Expectations() {
      {
        dao.listByName("SPA Pack 2");
        result = am1;
      }
    };

    new MockUp<AmenitiesFactory>() {
      @Mock
      AmenitiesDAO dao() {
        return dao;
      }
    };

    Amenities am = AmenitiesFactory.listAmenityByName("SPA Pack 2");
    assertEquals(am1, am);
  }
}
