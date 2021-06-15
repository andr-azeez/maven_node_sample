package com.hexaware.resortmanagement.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.hexaware.resortmanagement.factory.AmenitiesFactory;
import com.hexaware.resortmanagement.factory.BookingFactory;
import com.hexaware.resortmanagement.factory.CouponFactory;
import com.hexaware.resortmanagement.factory.EmployeeFactory;
import com.hexaware.resortmanagement.factory.MemberFactory;
import com.hexaware.resortmanagement.model.Amenities;
import com.hexaware.resortmanagement.model.Booking;
import com.hexaware.resortmanagement.model.Coupon;
import com.hexaware.resortmanagement.model.Employee;
import com.hexaware.resortmanagement.model.Member;

/**
 * utility class for employee.
 */
public class EmployeeUtil {
  private Scanner scan = new Scanner(System.in, "UTF-8");
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
  /**
   * main menu for members.
   */
  public final void empMenu() {
    System.out.println("Welcome to Black Stone Resorts Employee Log");
    System.out.println("1. Signin");
    System.out.println("2. Signup");
    System.out.println("3. Exit");
    System.out.print("Your Choice? ");
    int ch = scan.nextInt();
    subMenu(ch);
  }

  private void subMenu(final int ch) {
    switch (ch) {
      case 1:
        signin();
        break;
      case 2:
        register();
        break;
      case 3:
        Runtime.getRuntime().exit(0);
      default:
        System.out.println("Wrong Choice");
        empMenu();
        break;
    }
  }

  //---------------------------------------------------------------------------
  private void signin() {
    System.out.println("Welcome To Black Stone Resorts");
    System.out.println("==============================");
    System.out.print("Username: ");
    String em = scan.next();
    System.out.print("Password: ");
    String pass = scan.next();

    Employee e = EmployeeFactory.findByEmail(em);
    if (e != null && pass.equals(e.getPassKey())) {
      empSubMenu(e.getEmployeeId(), e.getEmployeeName());
    } else {
      System.out.println("Wrong Credentials! Please try again!");
      empMenu();
    }
  }

  //------------------------------------------------------------------------------
  private void register() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Employee e = new Employee();
    Employee e1;
    System.out.println("Enter your details please");
    // System.out.print("ID: ");
    // int id = scan.nextInt();
    try {
      System.out.print("Full Name: ");
      String name = br.readLine();
      System.out.print("Phone: ");
      String ph = scan.next();
      System.out.print("Email: ");
      String em = scan.next();
      e1 = EmployeeFactory.findByEmail(em);
      if (e1 == null) {
        System.out.print("Password: ");
        String pass = scan.next();
        System.out.print("Confirm Password: ");
        String confirm = scan.next();
        if (pass.equals(confirm)) {
          String res = e.registerEmployee(name, ph, em, pass);
          System.out.println(res);
        } else {
          System.out.println("Please Re-enter details! Password Mismatch!");
          register();
        }
      } else {
        System.out.println("Email already exists. Please enter your details again!");
        register();
      }
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

  //----------------------------------------------------------------------------------
  private void empSubMenu(final int id, final String name) {
    System.out.println("Welcome " + name);
    System.out.println("1. Bookings");
    System.out.println("2. Amenities");
    System.out.println("3. Coupons");
    System.out.println("4. Personal Details");
    System.out.println("5. Sign Out");
    System.out.print("Your choice: ");
    int ch = scan.nextInt();
    switch (ch) {
      case 1:
        bookingsMenu(id, name);
        break;
      case 2:
        amenitiesMenu(id, name);
        break;
      case 3:
        couponsMenu(id, name);
        break;
      case 4:
        myDetails(id, name);
        break;
      case 5:
        Runtime.getRuntime().exit(0);
      default:
        System.out.println("Please choose again!");
        break;
    }
    empSubMenu(id, name);
  }

  //--------------------------------------------------------------------------
  private void bookingsMenu(final int empId, final String name) {
    System.out.println();
    System.out.println("Welcome To Your Bookings Menu");
    System.out.println("=============================");
    System.out.println("1. View All My Bookings");
    System.out.println("2. View Pending Bookings");
    System.out.println("3. Accept or Deny Booking");
    System.out.print("Option? ");
    int ch = scan.nextInt();

    switch (ch) {
      case 1:
        bookingHistory(empId);
        break;
      case 2:
        pendingList(empId);
        break;
      case 3:
        acceptDeny(empId);
        break;
      default:
        empSubMenu(empId, name);
        break;
    }
  }

  //---------------------------------------------------------------------------------------
  private void amenitiesMenu(final int empId, final String empName) {
    System.out.println();
    System.out.println("Welcome to the Amenities Menu");
    System.out.println("-----------------------------");
    System.out.println("1. List My Amenities");
    System.out.println("2. Add a new Amenity");
    System.out.println("3. Update Price for Amenity");
    System.out.print("Please choose: ");
    int ch = scan.nextInt();

    switch (ch) {
      case 1:
        Amenities[] list = AmenitiesFactory.listByEmployee(empId);
        if (list.length > 0) {
          System.out.println("Id  , Description,   Price,   Category");
          for (Amenities a : list) {
            System.out.println(
                a.getAmenityId() + ", " + a.getAmenityName() + ", " + a.getPrice() + ", " + a.getAmenityCategory());
          }
        } else {
          System.out.println("List Not available.");
        }
        break;
      case 2:
        Amenities am = new Amenities();
        System.out.print("Amenity Name: ");
        String name = scan.next();
        System.out.print("Price: ");
        double p = scan.nextDouble();
        String cat = "SPA";
        System.out.println("Choose a Category from the list:");
        System.out.println("  1. SPA ");
        System.out.println("  2. POOL");
        System.out.println("  3. TOUR");
        System.out.println("  4. INDOOR GAMES");
        System.out.println("  5. RESTUARANT");
        System.out.println("  6. ARCHERY");
        System.out.println("  7. PAINTBALL");
        System.out.println("  8. GOLF");
        System.out.println("  9. ROOMS");
        int choice = scan.nextInt();

        switch (choice) {
          case 1:
            cat = "SPA";
            break;
          case 2:
            cat = "POOL";
            break;
          case 3:
            cat = "TOUR";
            break;
          case 4:
            cat = "INDOOR GAMES";
            break;
          case 5:
            cat = "RESTAURANT";
            break;
          case 6:
            cat = "ARCHERY";
            break;
          case 7:
            cat = "PAINTBALL";
            break;
          case 8:
            cat = "GOLF";
            break;
          case 9:
            cat = "ROOMS";
            break;
          default:
            break;
        }
        String msg = am.addNewAmenity(name, p, empId, cat);
        System.out.println(msg);
        break;
      case 3:
        System.out.print("Amenity Id [0 for list of Amenities]: ");
        int amId = scan.nextInt();
        updateAmenityPrice(amId, empId);
        break;
      default:
        empSubMenu(empId, empName);
        break;
    }
  }

  //-----------------------------------------------------------------------------
  private void couponsMenu(final int empId, final String name) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.println();
      System.out.println("Coupons Menu");
      System.out.println("------------");
      System.out.println("1. List All Coupons");
      System.out.println("2. Add a new Coupon");
      System.out.println("3. Update Expiry Date");
      System.out.println("4. Search for a Coupon");
      System.out.println("5. Search By Amenity");
      System.out.print("Your choice: ");
      int ch = scan.nextInt();

      switch (ch) {
        case 1:
          Coupon[] list = CouponFactory.listAll();
          if (list.length > 0) {
            System.out.println("Id   , Expiry   , Discount Amount   , Amenity");
            for (Coupon c: list) {
              Amenities am = AmenitiesFactory.showDetails(c.getAmenityId());
              System.out.println(c.getCouponId() + ", " + c.getExpiryDate()
                  + ", " + c.getDiscAmount() + ", " + am.getAmenityName());
            }
          }
          break;
        case 2:
          System.out.print("Coupon Name: ");
          String cName = scan.next().toUpperCase();
          System.out.print("Expiry Date (yyyy-mm-dd): ");
          String date = scan.next();

          System.out.print("Discount Amount: ");
          double amt = scan.nextDouble();
          System.out.print("Amenity Name: ");
          String amName = br.readLine();

          String msg = new Coupon().addCoupon(cName, date, amt, amName);
          System.out.println(msg);

          break;
        case 3:
          System.out.print("Coupon Name: ");
          String str = scan.next();
          System.out.print("New Expiry Date: ");
          String exp = scan.next();

          String res = new Coupon().updateExpiryDate(str, exp);
          System.out.println(res);
          break;
        case 4:
          System.out.print("Enter Coupon Id [Enter na for list]: ");
          String id = scan.next();
          if (id.equals("na")) {
            Coupon[] clist = CouponFactory.listAll();
            if (clist.length > 0) {
              System.out.println("Id   , Expiry   , Discount Amount   , Amenity");
              for (Coupon c: clist) {
                Amenities am = AmenitiesFactory.showDetails(c.getAmenityId());
                System.out.println(c.getCouponId() + ", " + c.getExpiryDate()
                    + ", " + c.getDiscAmount() + ", " + am.getAmenityName());
              }
              System.out.print("Coupon Id Please: ");
              id = scan.next();
            }
          }
          Coupon coup = CouponFactory.findById(id);
          System.out.println(coup.toString());
          break;
        case 5:
          System.out.print("Amenity Name: ");
          String n = scan.next();
          Amenities am = AmenitiesFactory.listAmenityByName(n);
          if (am != null) {
            int amId = am.getAmenityId();
            Coupon[] cList = CouponFactory.findByAmenity(amId);

            if (cList.length > 0) {
              System.out.println("Coupons for Amenity: " + n);
              System.out.println("Id  , Expiry Date   , Discount Amount ");
              for (Coupon c: cList) {
                System.out.println(c.getCouponId() + ", " + c.getExpiryDate()
                    + ", " + c.getDiscAmount());
              }
            } else {
              System.out.println("No Coupons for this Amenity");
            }
          } else {
            System.out.println("Amenity Name Mismatch. Please try again");
          }
          break;
        default:
          empSubMenu(empId, name);
          break;
      }
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

  //-------------------------------------------------------------------------
  private void updateAmenityPrice(final int id, final int empId) {
    int amId = id;

    if (id == 0) {
      Amenities[] list = AmenitiesFactory.listByEmployee(empId);
      if (list.length > 0) {
        System.out.println("Id  , Description,   Price,   Category");
        for (Amenities a : list) {
          System.out.println(
              a.getAmenityId() + ", " + a.getAmenityName() + ", " + a.getPrice() + ", " + a.getAmenityCategory());
        }
        System.out.print("Amenity Id: ");
        amId = scan.nextInt();
      } else {
        System.out.println("Add a new Amenity to List it");
      }
    }
    System.out.println("New Price please: ");
    double price = scan.nextDouble();
    int msg = AmenitiesFactory.updatePrice(amId, price);
    if (msg > 0) {
      System.out.println("Price Updated on Amenity");
    } else {
      System.out.println("Sorry! Update failed. Please try again");
    }
  }

  //------------------------------------------------------------------------------
  private void acceptDeny(final int empId) {
    Employee emp = new Employee();
    System.out.println("Booking Id [0 for a list of current pending bookings]: ");
    int bId = scan.nextInt();

    Date today = new Date();
    String s = sdf.format(today);
    try {
      today = sdf.parse(s);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    if (bId == 0) {
      Booking[] list = BookingFactory.showAllPendingBookings(empId);

      if (list.length > 0) {
        System.out.println("Id  , Date    , Amount,   Amenity");
        for (Booking b: list) {
          double amt = BookingFactory.retrieveBookingAmount(b.getBookingId());
          Amenities am = AmenitiesFactory.showDetails(b.getAmenityId());
          String n = am.getAmenityName();
          System.out.println(b.getBookingId() + ", " + b.getBookingDate() + ", "
              + amt + ", " + n);
        }
        System.out.print("Please enter Booking Id: ");
        bId = scan.nextInt();
      }
    }
    System.out.println("Please specify status [1 -> Accepted, 2 -> Denied]");
    int stat = scan.nextInt();

    String status = "PENDING";
    switch (stat) {
      case 1:
        status = "ACCEPTED";
        break;
      case 2:
        status = "DENIED";
        break;
      default:
        break;
    }
    double amt = BookingFactory.retrieveBookingAmount(bId);
    String msg = emp.acceptDenyBooking(bId, status, amt);
    System.out.println(msg);
  }

  private void bookingHistory(final int id) {
    Booking[] history = BookingFactory.empBookingHistory(id);
    if (history.length > 0) {
      for (Booking b : history) {
        System.out.println(b.toString());
      }
    } else {
      System.out.print("Looks like you don't have any bookings yet. ");
      System.out.println("Please check after sometime");
    }
  }

  private void myDetails(final int id, final String name) {
    Employee emp = EmployeeFactory.findById(id);

    System.out.println("======================================");
    System.out.println("Welcome " + name);
    System.out.println("1. Show Details");
    System.out.println("2. Update Phone Number");
    System.out.println("3. Update Password");
    System.out.println("Your option?");
    int ch = scan.nextInt();

    switch (ch) {
      case 1:
        System.out.println();
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + emp.getPhone());
        System.out.println("Email: " + emp.getEmail());
        System.out.println("------------------------------------------");
        System.out.println();
        break;
      case 2:
        System.out.println();
        System.out.print("New Phone Number? ");
        String ph = scan.next();
        String res = emp.updatePhone(id, ph);
        System.out.println(res);
        break;
      case 3:
        System.out.print("Enter Current Password: ");
        String curPass = scan.next();
        System.out.print("Enter New Password: ");
        String pass = scan.next();
        System.out.print("Confirm New Password: ");
        String confirm = scan.next();
        if (curPass.equals(emp.getPassKey()) && !curPass.equals(pass) && pass.equals(confirm)) {
          String str = emp.updatePassword(id, pass);
          System.out.println(str);
        } else {
          System.out.println("Please check your password and try again!");
          myDetails(id, name);
        }
        break;
      default:
        empSubMenu(id, name);
        break;
    }
  }

  private void pendingList(final int id) {
    Booking[] pending = BookingFactory.showAllPendingBookings(id);
    if (pending.length > 0) {
      System.out.println("Id  , Date    , Amenity   , Amount    , Member ");
      System.out.println("-------------------------------------------------");
      for (Booking b: pending) {
        double amt = BookingFactory.retrieveBookingAmount(id);
        Amenities am = AmenitiesFactory.showDetails(b.getAmenityId());
        Member m = MemberFactory.getMemberById(b.getMemberId());
        System.out.println(b.getBookingId() + ", " + b.getBookingDate() + ", " + am.getAmenityName()
            + ", " + amt + ", " + m.getMemberName());
      }
    } else {
      System.out.println("No Pending Bookings");
    }
  }
}
