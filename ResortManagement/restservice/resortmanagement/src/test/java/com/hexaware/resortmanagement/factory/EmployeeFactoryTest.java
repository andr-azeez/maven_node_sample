package com.hexaware.resortmanagement.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.hexaware.resortmanagement.model.Employee;
import com.hexaware.resortmanagement.persistence.EmployeeDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * test class for EmployeeFactory.
 */
@RunWith(JMockit.class)
public class EmployeeFactoryTest {
  /**
   * test for protected constructor.
   */
  @Test
  public final void testEmployeeFactory() {
    EmployeeFactory ef = new EmployeeFactory();
    EmployeeFactory ef1 = new EmployeeFactory();
    assertNotEquals(ef, ef1);
  }

  /**
   * test for findById.
   * @param eDao for EmployeeDAO
   */
  @Test
  public final void testFindById(@Mocked final EmployeeDAO eDao) {
    Employee e = new Employee(1216, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");

    new Expectations() {
      {
        eDao.findById(1216);
        result = e;
      }
    };

    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return eDao;
      }
    };

    Employee e1 = EmployeeFactory.findById(1216);
    assertEquals(e, e1);
  }

  /**
   * test for findByEmail.
   * @param eDao for EmployeeDAO
   */
  @Test
  public final void testFindByEmail(@Mocked final EmployeeDAO eDao) {
    Employee e = new Employee(1216, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");

    new Expectations() {
      {
        eDao.findByEmail("dmurphy@blackstoneresorts.com");
        result = e;
      }
    };

    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return eDao;
      }
    };

    Employee e1 = EmployeeFactory.findByEmail("dmurphy@blackstoneresorts.com");
    assertEquals(e, e1);
  }

  /**
   * test for registerEmployee.
   * @param dao for EmployeeDAO
   */
  @Test
  public final void testRegisterEmployee(@Mocked final EmployeeDAO dao) {

    new Expectations() {
      {
        dao.registerEmployee(1217, "Bruce Wayne", "84883993", "bwayne@blackstoneresorts.com", "bruce123");
        result = 1;
      }
    };

    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };

    int res = EmployeeFactory.registerEmployee(1217, "Bruce Wayne", "84883993", "bwayne@blackstoneresorts.com", "bruce123");
    assertEquals(1, res);
  }

  /**
   * test for updatePhone.
   * @param dao for EmployeeDAO
   */
  @Test
  public final void testUpdatePhone(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        dao.updatePhone(1216, "99881001");
        result = 1;
      }
    };

    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };

    int res = EmployeeFactory.updatePhone(1216, "99881001");
    assertEquals(1, res);
  }

  /**
   * test for updatePassword.
   * @param dao for EmployeeDAO
   */
  @Test
  public final void testUpdatePassword(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        dao.updatePassword(1216, "pass123");
        result = 1;
      }
    };

    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };

    int res = EmployeeFactory.updatePassword(1216, "pass123");
    assertEquals(1, res);
  }

  public final void testFindLastRow(@Mocked final EmployeeDAO dao) {
    Employee e = new Employee(1216, "Diane Murphy", "384579292", "dmurphy@blackstoneresorts.com", "diane123");
    
    new Expectations() {
      {
        dao.findLastRow();
        result = e;
      }
    };

    new MockUp<EmployeeFactory>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };

    Employee e1 = EmployeeFactory.findLastRow();
    assertEquals(e, e1);
  }
}
