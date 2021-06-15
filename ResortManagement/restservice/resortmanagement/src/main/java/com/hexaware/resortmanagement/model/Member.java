package com.hexaware.resortmanagement.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.ArrayList;
//import java.time.LocalDate;
//import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
import java.util.Objects;

import com.hexaware.resortmanagement.factory.AmenitiesFactory;
import com.hexaware.resortmanagement.factory.BookingFactory;
import com.hexaware.resortmanagement.factory.CouponFactory;
import com.hexaware.resortmanagement.factory.MemberFactory;


/**
 * Members class.
 */
public class Member {
  /**
   * to store memberId.
   */
  private int memberId;

  /**
   * to store memberName.
   */
  private String memberName;

  /**
   * to store email.
   */
  private String email;

  /**
   * to store phone.
   */
  private String phone;

  /**
   * to store wallet balance.
   */
  private double walletbalance;

  /**
   * to store the password.
   */
  private String passKey;

  /**
   * to store membership date.
   */
  private Date membershipDate;

  /**
   * to store date od birth.
   */
  private Date dateOfBirth;

  //---------------------------------------
  //getters and setters
  //---------------------------------------
  /**
   *
   * @return memberId.
   */
  public final int getMemberId() {
    return memberId;
  }

  /**
   *
   * @param argMemberId for memberId.
   */
  public final void setMemberId(final int argMemberId) {
    this.memberId = argMemberId;
  }

  /**
   *
   * @return memberName.
   */
  public final String getMemberName() {
    return memberName;
  }

  /**
   *
   * @param argMemberName for memberName.
   */
  public final void setMemberName(final String argMemberName) {
    this.memberName = argMemberName;
  }

  /**
   *
   * @return email.
   */
  public final String getEmail() {
    return email;
  }

  /**
   *
   * @param argEmail for email.
   */
  public final void setEmail(final String argEmail) {
    this.email = argEmail;
  }

  /**
   *
   * @return phone.
   */
  public final String getPhone() {
    return phone;
  }

  /**
   *
   * @param argPhone for phone.
   */
  public final void setPhone(final String argPhone) {
    this.phone = argPhone;
  }

  /**
   *
   * @return wallet balance.
   */
  public final double getWalletbalance() {
    return walletbalance;
  }

  /**
   *
   * @param argWalletbalance for wallet balance.
   */
  public final void setWalletbalance(final double argWalletbalance) {
    this.walletbalance = argWalletbalance;
  }

  /**
   *
   * @return passkey.
   */
  public final String getPassKey() {
    return passKey;
  }

  /**
   *
   * @param argPassKey for passkey.
   */
  public final void setPassKey(final String argPassKey) {
    this.passKey = argPassKey;
  }

  /**
   *
   * @return membershipDate.
   */
  public final Date getMembershipDate() {
    return membershipDate;
  }

  /**
   *
   * @param argMembershipDate for membership date.
   */
  public final void setMembershipDate(final Date argMembershipDate) {
    this.membershipDate = argMembershipDate;
  }

  /**
   *
   * @return dateOfBirth.
   */
  public final Date getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   *
   * @param dob for dateOfBirth.
   */
  public final void setDateOfBirth(final Date dob) {
    this.dateOfBirth = dob;
  }

  /**
   * default constructor.
   */
  public Member() {

  }

  /**
   * parameterized constructor.
   * @param argMemberId for memberId
   * @param argMemberName for memberName
   * @param argEmail for email
   * @param argPhone for phone
   * @param argBalance for wallet balance
   * @param argPass for passKey
   * @param argDate for membershipDate
   */
  public Member(final int argMemberId, final String argMemberName, final String argEmail,
      final String argPhone, final double argBalance, final String argPass, final Date argDate) {
    this.memberId = argMemberId;
    this.memberName = argMemberName;
    this.email = argEmail;
    this.phone = argPhone;
    this.walletbalance = argBalance;
    this.passKey = argPass;
    this.membershipDate = argDate;
  }
  /**
   * hashCode method.
   * @return int
   */
  @Override
  public final int hashCode() {
    return Objects.hash(memberId, memberName, phone, email, passKey, walletbalance, membershipDate);
  }

  /**
   * equals method.
   * @param obj Object
   * @return boolean
   */
  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    Member other = (Member) obj;
    if (Objects.equals(memberId, other.memberId) && Objects.equals(memberName, other.memberName)
        && Objects.equals(phone, other.phone) && Objects.equals(email, other.email)
        && Objects.equals(walletbalance, other.walletbalance)
        && Objects.equals(passKey, other.passKey) && Objects.equals(membershipDate, other.membershipDate)) {
      return true;
    }
    return false;
  }

  /**
   * toString method.
   * @return string
   */
  @Override
  public final String toString() {
    return "Member Details: [Id: " + this.memberId + ", Name: " + this.memberName + ", phone: " + this.phone
        + ", Email: " + this.email + ", walletbalance=" + walletbalance + ", Membership Date: " + this.membershipDate
        + "]";
  }

  /**
   * to register a new member.
   * @param argMemberName for membername
   * @param argPhone for phone
   * @param argEmail for email
   * @param argPass for password
   * @param dob for dateOfBirth
   * @return string
   */
  public final String registerMember(final String argMemberName, final String argPhone, final String argEmail,
      final String argPass, final String dob) {
    String str = "Unable to Register! Please try again!";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    int id = 101;

    Member m1 = MemberFactory.getMemberByEmail(argEmail);

    if (m1.memberId != 0) {
      str = "This Email Id already exists. Please try again!";
    } else {
      Member m = MemberFactory.findLastRow();
      if (m != null) {
        id = m.getMemberId() + 1;
      }
      double balance = 10000;
      try {
        Date today = new Date();
        String temp = sdf.format(today);
        today = sdf.parse(temp);
        Date birthDate = sdf.parse(dob);

        int i = MemberFactory.registerMember(id, argMemberName, argEmail, argPhone, balance, argPass, today);
        if (i > 0) {
          MemberFactory.updateBirthDate(id, birthDate);
          str = "Member Registered Successfully! Please login to continue";
        }
      } catch (ParseException ex) {
        System.out.println(ex.getMessage());
      }
    }

    return str;
  }

  /**
   * to update phone.
   * @param id for member id
   * @param ph for new phone number
   * @return string
   */
  public final String updatePhone(final int id, final String ph) {
    String res = "Unable to update phone";

    int i = MemberFactory.updatePhone(id, ph);
    if (i > 0) {
      res = "Phone updated successfully";
    }

    return res;
  }

  /**
   * to update password.
   * @param id for id
   * @param pass for new password
   * @return string
   */
  public final String updatePassword(final int id, final String pass) {
    String res = "Unable to update password";

    int i = MemberFactory.updatePassword(id, pass);
    if (i > 0) {
      res = "Password updated successfully";
    }

    return res;
  }

  /**
   * to update wallet amount.
   * @param id for memberid
   * @param amt for amount to be credited
   * @return string
   */
  public final String updateWallet(final int id, final double amt) {
    String res = "Update unsuccessful";

    Member m = MemberFactory.getMemberById(id);

    if (m != null) {
      double curamt = m.getWalletbalance();

      int i = MemberFactory.updateWalletAmount(id, curamt, amt);
      if (i > 0) {
        res = "Balance updated";
      }
    }

    return res;
  }

  /**
   * to book an amenity.
   * @param amId for amenity id
   * @param qty for quantity
   * @param memId for memberid
   * @return string
   */
  public final String bookAnAmenity(final int amId, final int qty, final int memId) {

    String res = "Unable to book the Amenity";

    //retrieves the amenity
    Amenities am = AmenitiesFactory.showDetails(amId);
    if (am != null) {
      System.out.println(am.getAmenityId() + ", " + am.getAmenityName()
          + ", " + am.getPrice() + ", " + am.getAmenityCategory());
      System.out.println();

      int bId = 3001;

      Booking b = BookingFactory.findLastRow();
      if (b != null) {
        bId = b.getBookingId() + 1;
      }

      //date manipulations
      Date bDate = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

      //to get yesterday's date
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.DATE, -1);
      Date yester = cal.getTime();
      //System.out.println(yester);

      try {
        String temp = sdf.format(bDate);
        bDate = sdf.parse(temp);

        temp = sdf.format(yester);
        yester = sdf.parse(temp);
      } catch (ParseException ex) {
        System.out.println(ex.getMessage());
      }

      boolean flag = false;
      double disAmt = 0.0;
      Coupon[] avail = CouponFactory.availableCoupons(memId, amId, yester);
      if (avail.length > 0) {
        for (Coupon c: avail) {
          //first available discount for the amenity
          disAmt = c.getDiscAmount();
          flag = true;

          if (flag) {
            break;
          }
        }
      }

      // Date birthDate = MemberFactory.retrieveBirthDate(memId);
      // java.sql.Date date = new java.sql.Date(birthDate.getTime());
      // LocalDate lDate = date.toLocalDate();
      // int month = lDate.getMonthValue();

      int month = MemberFactory.retrieveBirthDate(memId);

      double total = am.getPrice() * qty;
      if (month == 3) {
        disAmt = disAmt + (total / 10);
      }

      total = am.getPrice() * qty - disAmt;
      String status = BookingStatus.PENDING.name();

      Member m = MemberFactory.getMemberById(memId);
      double bal = m.getWalletbalance();

      if (bal > total) {
        int i = BookingFactory.bookAmenity(bId, bDate, qty, am.getEmployeeId(), amId,
            memId, status);
        if (i > 0) {
          BookingFactory.updateBookingAmount(bId, total);
          int x = MemberFactory.decrementWallet(memId, m.getWalletbalance(), total);
          if (x > 0) {
            System.out.println("Wallet Balance Updated");
          }
          res = "Amenity Booked Successfully";
        }
      } else {
        res = "Insufficient Balance. Please update your wallet";
      }
    } else {
      res = "Amenity not found. Please check and book again!";
    }
    return res;
  }

  /**
   * to cancel a booking.
   * @param bId for booking id
   * @return string
   */
  public final String cancelBooking(final int bId) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Booking b = BookingFactory.showBookingDetails(bId);

    Member m = MemberFactory.getMemberById(b.getMemberId());

    String msg = "Cancellation was unsuccessful";

    //LocalDate today = LocalDate.now();

    if (b != null) {
      Date bDate = b.getBookingDate();
      Date today = new Date();

      try {
        String s = sdf.format(today);
        today = sdf.parse(s);
      } catch (ParseException ex) {
        System.out.println(ex.getMessage());
      }
      // java.sql.Date sDate = new java.sql.Date(bDate.getTime());
      // LocalDate lDate = sDate.toLocalDate();
      //if (today.equals(lDate)) {
      if (bDate.compareTo(today) >= 0) {
        int x = BookingFactory.updateStatus(bId, BookingStatus.CANCELLED.name());

        if (x > 0) {
          double amt = BookingFactory.retrieveBookingAmount(bId);
          msg = "Booking Cancelled Successfully";
          int i = MemberFactory.incrementWallet(b.getMemberId(), m.getWalletbalance(), amt);
          if (i > 0) {
            System.out.println("Booking Amount Credited to Wallet");
          }
        }
      }
    }
    return msg;
  }

  /**
   * to accept multiple bookings.
   * @param amId for amenityId
   * @param qty for qunatity
   * @param memId for memberId
   * @return for string
   */
  public final String bookMultiple(final int amId, final int qty, final int memId, boolean flag) {
    String res = "Unable to take the bookings";

    ArrayList<Booking> bList = new ArrayList<Booking>();

    if (flag) {
      Booking b = new Booking();
      b.setAmenityId(amId);
      b.setQuantity(qty);
      b.setMemberId(memId);

      Amenities am = AmenitiesFactory.showDetails(amId);
      if (am != null) {
        b.setEmployeeId(am.getEmployeeId());
        double total = am.getPrice() * qty;
        b.setTotalAmt(total);

        // Booking b1 = BookingFactory.findLastRow();
        // int id = b1.getBookingId() + 1;
        b.setBookingId(0);
        bList.add(b);
      }
    } else {
      for (int i = 0; i < bList.size(); i++) {
        //bubblesort
        //inside the if
        Booking b1 = BookingFactory.findLastRow();
        int id = b1.getBookingId() + 1;
        Date bDate = new Date();
        //check if month is april
        int res1 = BookingFactory.bookAmenity(id, bDate , qty, bList.get(i).getAmenityId(), amId, memId, "PENDING");
        System.out.println(res1);
      }
    }
    

    // HashMap<Booking, Double> bookings = new HashMap<Booking, Double>();

    // Amenities am = AmenitiesFactory.showDetails(amId);
    // if (am != null) {
    //   System.out.println(am.getAmenityId() + ", " + am.getAmenityName()
    //       + ", " + am.getPrice() + ", " + am.getAmenityCategory());
    //   System.out.println();

    //   int bId = 3001;

    //   Booking b = BookingFactory.findLastRow();
    //   if (b != null) {
    //     bId = b.getBookingId() + 1;
    //   }

    //   boolean another = false;

    //   Date bDate1 = new Date();
    //   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    //   //to get yesterday's date
    //   Calendar cal = Calendar.getInstance();
    //   cal.add(Calendar.DATE, -1);
    //   Date yester = cal.getTime();

    //   try {
    //     String temp = sdf1.format(bDate1);
    //     bDate1 = sdf1.parse(temp);

    //     temp = sdf1.format(yester);
    //     yester = sdf1.parse(temp);
    //   } catch (ParseException ex) {
    //     System.out.println(ex.getMessage());
    //   }

    //   boolean flag1 = false, flag2 = false;
    //   double discAmt = 0.0;
    //   Coupon[] avail = CouponFactory.availableCoupons(memId, amId, yester);
    //   if (avail.length > 0) {
    //     for (Coupon c: avail) {
    //       //first available discount for the amenity
    //       discAmt = c.getDiscAmount();
    //       flag1 = true;

    //       if (flag1) {
    //         break;
    //       }
    //     }
    //   }

    //   Date today = new Date();

    //   // Date birthDate1 = MemberFactory.retrieveBirthDate(memId);
    //   // java.sql.Date date1 = new java.sql.Date(birthDate1.getTime());
    //   // LocalDate lDate = date1.toLocalDate();
    //   // int month = lDate.getMonthValue();

    //   double total = am.getPrice() * qty;
    //   if (month == 2) {
    //     flag2 = true;
    //     discAmt = discAmt + (total / 10);
    //   }
    //   double maxAmt = 0.0;
    //   total = am.getPrice() * qty - discAmt;
    //   String status = BookingStatus.PENDING.name();

    //   bookings.put(new Booking(bId, bDate1, qty, am.getEmployeeId(), memId, amId, BookingStatus.PENDING), total);
    //   int index = 3;

    //   if (bookings.size() > 1 && bookings.size() < 4) {
    //     another = true;
    //     ArrayList<Double> values = new ArrayList<Double>(bookings.values());

    //     if (values.get(0).compareTo(values.get(1)) > 0) {
    //       double d1 = values.get(0);
    //       if (Double.compare(d1, values.get(2)) > 0) {
    //         maxAmt = d1;
    //       } else {
    //         maxAmt = values.get(2);
    //       //  index = 2;
    //       }
    //     } else {
    //       if (values.get(1).compareTo(values.get(2)) > 0) {
    //         maxAmt = values.get(1);
    //       //  index = 1;
    //       }
    //     }
    //     maxAmt = maxAmt - (maxAmt / 2);

    //     total = total - maxAmt;
    //   }

    //   if (flag2 && another) {
    //     total = total - (total * 5 / 100);
    //   }

    //   Member m = MemberFactory.getMemberById(memId);
    //   double bal = m.getWalletbalance();

    //   for (Map.Entry<Booking, Double> map : bookings.entrySet()) {
    //     Booking book = (Booking) map.getKey();

    //     if (bal > total) {
    //       int i = BookingFactory.bookAmenity(book.getBookingId(), bDate1, book.getQuantity(), book.getEmployeeId(), amId,
    //           memId, status);
    //       if (i > 0) {
    //         BookingFactory.updateBookingAmount(book.getBookingId(), total);
    //         int x = MemberFactory.decrementWallet(memId, m.getWalletbalance(), total);
    //         if (x > 0) {
    //           index--;
    //           System.out.println("Wallet Balance Updated");
    //         }
    //         res = "Amenity Booked Successfully";
    //       } else {
    //         res = index + " amenity booking(s) unsuccessful";
    //       }
    //     } else {
    //       res = "Insufficient Balance. Please update your wallet";
    //     }
    //   }
    // } else {
    //   res = "Amenity not found. Please check and book again!";
    // }
    return res;
  }

  /**
   * to get the booking history.
   * @param id for member id
   * @return booking array
   */
  public final Booking[] bookingHistory(final int id) {
    Booking[] his = BookingFactory.memberBookingHistory(id);
    return his;
  }
}
