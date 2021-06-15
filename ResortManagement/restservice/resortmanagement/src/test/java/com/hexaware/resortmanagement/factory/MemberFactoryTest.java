package com.hexaware.resortmanagement.factory;

import com.hexaware.resortmanagement.model.Member;
import com.hexaware.resortmanagement.persistence.MembersDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * test class for MemberFactory.
 */
@RunWith(JMockit.class)
public class MemberFactoryTest {
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * test for protected constructor.
   */
  @Test
  public final void testMemberFactory() {
    MemberFactory m1 = new MemberFactory();
    MemberFactory m2 = new MemberFactory();
    assertNotEquals(m1, m2);
  }

  /**
   * test for getMEmberById.
   * @param mDao for MembersDAO
   */
  @Test
  public final void testGetMemberById(@Mocked final MembersDAO mDao) {
    Date md = new Date();

    try {
      md = sdf.parse("2020-01-05");
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
    }

    final Member m = new Member(112, "Jean King", "jking@xyz.com", "7025551838", 67900, "king123", md);

    new Expectations() {
      {
        mDao.findById(112);
        result = m;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return mDao;
      }
    };

    Member m1 = MemberFactory.getMemberById(112);
    assertEquals(m, m1);
  }

  /**
   * test for getMemberByEmail.
   * @param mDao for MembersDAO
   */
  @Test
  public final void testGetMemberByEmail(@Mocked final MembersDAO mDao) {
    Date md = new Date();

    try {
      md = sdf.parse("2020-01-05");
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
    }

    final Member m = new Member(112, "Jean King", "jking@xyz.com", "7025551838", 67900, "king123", md);

    new Expectations() {
      {
        mDao.getMemberByEmail("jking@xyz.com");
        result = m;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return mDao;
      }
    };

    Member m1 = MemberFactory.getMemberByEmail("jking@xyz.com");
    assertEquals(m, m1);
  }

  /**
   * test for registerMember.
   * @param mDao for MembersDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testRegisterMember(@Mocked final MembersDAO mDao) throws ParseException {

    new Expectations() {
      {
        mDao.registerMember(123, "Calvin Klein", "calvink@abc.com", "87388272", 45000, "calcin123", sdf.parse("2020-02-06"));
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return mDao;
      }
    };

    int res = MemberFactory.registerMember(123, "Calvin Klein", "calvink@abc.com", "87388272", 45000, "calcin123", sdf.parse("2020-02-06"));
    assertEquals(1, res);
  }

  /**
   * test for updatePhone.
   * @param dao for MembersDAO
   */
  @Test
  public final void testUpdatePhone(@Mocked final MembersDAO dao) {
    new Expectations() {
      {
        dao.updatePhone(103, "239847247");
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    int res = MemberFactory.updatePhone(103, "239847247");
    assertEquals(1, res);
  }

  /**
   * test for updatePassword.
   * @param dao for MembersDAO
   */
  @Test
  public final void testUpdatePassword(@Mocked final MembersDAO dao) {
    new Expectations() {
      {
        dao.updatePassword(103, "passkey123");
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    int res = MemberFactory.updatePassword(103, "passkey123");
    assertEquals(1, res);
  }

  /**
   * test for findLastRow.
   * @param dao for MembersDAO
   */
  @Test
  public final void testFindLastRow(@Mocked final MembersDAO dao) {
    Date md = new Date();

    try {
      md = sdf.parse("2020-03-18");
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
    }

    final Member m = new Member(134, "Jean King", "jking@xyz.com", "7025551838", 67900, "king123", md);

    new Expectations() {
      {
        dao.findLastRow();
        result = m;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    Member m1 = MemberFactory.findLastRow();
    assertEquals(m, m1);
  }

  /**
   * test for decrementWallet.
   * @param dao for MembersDAO
   */
  @Test
  public final void testDecrementWallet(@Mocked final MembersDAO dao) {
    new Expectations() {
      {
        dao.decrementWallet(119, 3400, 1200);
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    int res = MemberFactory.decrementWallet(119, 3400, 1200);
    assertEquals(1, res);
  }

  /**
   * test for incrementWallet.
   * @param dao for MembersDAO
   */
  @Test
  public final void testIncrementWallet(@Mocked final MembersDAO dao) {
    new Expectations() {
      {
        dao.incrementWallet(119, 3400, 1200);
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    int res = MemberFactory.incrementWallet(119, 3400, 1200);
    assertEquals(1, res);
  }

  /**
   * test for updateWallet.
   * @param dao for MembersDAO
   */
  @Test
  public final void testUpdateWallet(@Mocked final MembersDAO dao) {
    new Expectations() {
      {
        dao.updateWalletAmount(119, 3400, 2000);
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    int res = MemberFactory.updateWalletAmount(119, 3400, 2000);
    assertEquals(1, res);
  }

  /**
   * test for updateBirthDate.
   * @param dao for MembersDAO
   * @throws ParseException foe parse exception
   */
  @Test
  public final void testUpdateBirthDate(@Mocked final MembersDAO dao) throws ParseException {
    new Expectations() {
      {
        dao.updateBirthDate(sdf.parse("2001-10-01"), 124);
        result = 1;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    int res = MemberFactory.updateBirthDate(124, sdf.parse("2001-10-01"));
    assertEquals(1, res);
  }

  /**
   * test for retrieveBirthDate.
   * @param dao for MembersDAO
   */
  @Test
  public final void testRetrieveBirthDate(@Mocked final MembersDAO dao) {

    new Expectations() {
      {
        dao.retrieveBirthDate(124);
        result = 3;
      }
    };

    new MockUp<MemberFactory>() {
      @Mock
      MembersDAO dao() {
        return dao;
      }
    };

    int res = MemberFactory.retrieveBirthDate(124);
    assertEquals(3, res);
  }
}
