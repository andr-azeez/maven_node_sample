package com.hexaware.resortmanagement.factory;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hexaware.resortmanagement.model.Booking;
import com.hexaware.resortmanagement.model.BookingStatus;
import com.hexaware.resortmanagement.persistence.BookingDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/**
 * test class for BookingFactory.
 */
@RunWith(JMockit.class)
public class BookingFactoryTest {
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  /**
   * test for protected constructor.
   */
  @Test
  public final void testBookingFactory() {
    BookingFactory bf = new BookingFactory();
    BookingFactory bf1 = new BookingFactory();
    assertNotEquals(bf, bf1);
  }

  /**
   * test for findLastRow.
   * @param dao for BookingDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testFindLastRow(@Mocked final BookingDAO dao) throws ParseException {
    Booking b = new Booking(3006, sdf.parse("2021-03-18"), 1, 1216, 132, 4008, BookingStatus.ACCEPTED);

    new Expectations() {
      {
        dao.findLastRow();
        result = b;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return dao;
      }
    };

    Booking b1 = BookingFactory.findLastRow();
    assertEquals(b, b1);
  }

  /**
   * test for bookAmenity.
   * @param dao for BookingDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testBookAmenity(@Mocked final BookingDAO dao) throws ParseException {
    String s = sdf.format(new Date());

    new Expectations() {
      {
        dao.bookAmenity(3007, sdf.parse(s), 1, 1216, 4008, 132, BookingStatus.PENDING.name());
        result = 1;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return dao;
      }
    };

    int res = BookingFactory.bookAmenity(3007, sdf.parse(s), 1, 1216, 4008, 132, BookingStatus.PENDING.name());
    assertEquals(1, res);
  }

  /**
   * test for updateOrderAmount.
   * @param dao for BookingDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testUpdateBookingAmount(@Mocked final BookingDAO dao) throws ParseException {
    new Expectations() {
      {
        dao.updateBookingAmount(3006, 7000);
        result = 1;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return dao;
      }
    };

    int res = BookingFactory.updateBookingAmount(3006, 7000);
    assertEquals(1, res);
  }

  /**
   * test for retrieveBookingAmount.
   * @param dao for BookingDAO
   */
  @Test
  public final void testRetrieveBookingAmount(@Mocked final BookingDAO dao) {
    new Expectations() {
      {
        dao.retrieveBookingAmount(3002);
        result = 2400;
      }
    };

    new MockUp<BookingFactory>(){
      @Mock
      BookingDAO dao() {
        return dao;
      }
    };

    double res = BookingFactory.retrieveBookingAmount(3002);
    assertEquals(2400, res, 1);
  }

  /**
   * test for showBookingDetails.
   * @param dao for BookingDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testShowBookingDetails(@Mocked final BookingDAO dao) throws ParseException {
    Booking b = new Booking(3006, sdf.parse("2021-03-18"), 1, 1216, 132, 4008, BookingStatus.ACCEPTED);

    new Expectations() {
      {
        dao.showBookingDetails(3006);
        result = b;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return dao;
      }
    };

    Booking b1 = BookingFactory.showBookingDetails(3006);
    assertEquals(b, b1);
  }

  /**
   * test for listCurrentBookings.
   * @param dao for BookingDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testListCurrentBookings(@Mocked final BookingDAO dao) throws ParseException {
    Date d = new Date();
    String s = sdf.format(d);

    Booking[] blist = new Booking[2];
    blist[0] = new Booking(3007, sdf.parse(s), 2, 1166, 119, 4002, BookingStatus.PENDING);
    blist[1] = new Booking(3008, sdf.parse(s), 1, 1076, 119, 4001, BookingStatus.PENDING);

    new Expectations() {
      {
        dao.listBookingsByToday(119);
        result = blist;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return dao;
      }
    };

    Booking[] list = BookingFactory.listCurrentBookings(119);
    assertArrayEquals(blist, list);
  }

  /**
   * test for updateStatus.
   * @param dao for BookingDAO
   */
  @Test
  public final void testUpdateStatus(@Mocked final BookingDAO dao) {
    new Expectations() {
      {
        dao.updateStatus("ACCEPTED", 3006);
        result = 1;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return dao;
      }
    };

    int res = BookingFactory.updateStatus(3006, "ACCEPTED");
    assertEquals(1, res);
  }

  /**
   * test for showAllPendingBookings.
   * @param dao for BookingDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testShowAllPendingBookings(@Mocked final BookingDAO dao) throws ParseException {
    Date d = new Date();
    String s = sdf.format(d);

    Booking[] blist = new Booking[2];
    blist[0] = new Booking(3007, sdf.parse(s), 2, 1166, 119, 4002, BookingStatus.PENDING);
    blist[1] = new Booking(3008, sdf.parse(s), 1, 1166, 124, 4001, BookingStatus.PENDING);

    new Expectations() {
      {
        dao.showAllPendingBookings(1166);
        result = blist;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return dao;
      }
    };

    Booking[] list = BookingFactory.showAllPendingBookings(1166);
    assertArrayEquals(blist, list);
  }

  /**
   * test for empBookingHistory.
   * @param dao for BookingDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testEmpBookingHistory(@Mocked final BookingDAO dao) throws ParseException {
    Booking[] blist = new Booking[4];
    blist[0] = new Booking(3002, sdf.parse("2021-03-12"), 2, 1166, 119, 4002, BookingStatus.CANCELLED);
    blist[1] = new Booking(3004, sdf.parse("2021-03-17"), 1, 1166, 124, 4001, BookingStatus.PENDING);
    blist[2] = new Booking(3005, sdf.parse("2021-03-21"), 1, 1166, 102, 4003, BookingStatus.DENIED);
    blist[3] = new Booking(3006, sdf.parse("2021-03-22"), 1, 1166, 124, 4001, BookingStatus.ACCEPTED);

    new Expectations() {
      {
        dao.empBookingHistory(1166);
        result = blist;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return dao;
      }
    };

    Booking[] list = BookingFactory.empBookingHistory(1166);
    assertArrayEquals(blist, list);
  }

  /**
   * test for memberBookingHistory.
   * @param dao for BookingDAO
   * @throws ParseException for parse exception
   */
  @Test
  public final void testMemberBookingHistory(@Mocked final BookingDAO dao) throws ParseException {
    Booking[] blist = new Booking[4];
    blist[0] = new Booking(3002, sdf.parse("2021-03-12"), 2, 1166, 124, 4002, BookingStatus.CANCELLED);
    blist[1] = new Booking(3004, sdf.parse("2021-03-17"), 1, 1002, 124, 4001, BookingStatus.PENDING);
    blist[2] = new Booking(3005, sdf.parse("2021-03-21"), 1, 1076, 124, 4003, BookingStatus.DENIED);
    blist[3] = new Booking(3006, sdf.parse("2021-03-22"), 1, 1002, 124, 4001, BookingStatus.ACCEPTED);

    new Expectations() {
      {
        dao.memberBookingHistory(124);
        result = blist;
      }
    };

    new MockUp<BookingFactory>() {
      @Mock
      BookingDAO dao() {
        return dao;
      }
    };

    Booking[] list = BookingFactory.memberBookingHistory(124);
    assertArrayEquals(blist, list);
  }
}
