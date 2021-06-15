package com.hexaware.resortmanagement.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.ArrayList;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.Date;
import java.util.Scanner;

import com.hexaware.resortmanagement.factory.AmenitiesFactory;
import com.hexaware.resortmanagement.factory.BookingFactory;
import com.hexaware.resortmanagement.factory.MemberFactory;
import com.hexaware.resortmanagement.model.Amenities;
import com.hexaware.resortmanagement.model.Booking;
import com.hexaware.resortmanagement.model.Member;

/**
 * Utility class for Members.
 */
public class MemberUtil {
  private Scanner opt = new Scanner(System.in, "UTF-8");

  /**
   * main menu for members.
   */
  public final void memberMenu() {
    System.out.println("Welcome to Black Stone Resorts");
    System.out.println("1. Login");
    System.out.println("2. Register");
    System.out.println("3. Exit");
    System.out.print("Your Choice: ");
    int ch = opt.nextInt();
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
        System.out.println("Registration is working as of now");
        break;
    }
  }

  private void signin() {
    // Member m = new Member();
    // Member[] list = m.getMemList();
    System.out.println("Enter your Email: ");
    String em = opt.next();
    System.out.println("Enter your Password: ");
    String pass = opt.next();

    Member m = MemberFactory.getMemberByEmail(em);

    if (m != null && pass.equals(m.getPassKey())) {
      memberSubMenu(m.getMemberId(), m.getMemberName());
    } else {
      System.out.println("Wrong Username and Password! Please try again!");
      signin();
    }
  }

  private void register() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Member m = new Member();
    System.out.println("Enter your details please");
    // System.out.print("ID: ");
    // int id = opt.nextInt();
    try {
      System.out.print("Full Name: ");
      String fullName = br.readLine();
      System.out.print("Phone No: ");
      String ph = opt.next();
      System.out.print("Date of Birth (yyyy-mm-dd): ");
      String dob = opt.next();
      System.out.print("Email : ");
      String em = opt.next();
      System.out.print("Your Password: ");
      String pass = opt.next();
      System.out.print("Re-enter Password: ");
      String rePass = opt.next();

      if (pass.equals(rePass)) {
        String res = m.registerMember(fullName, ph, em, pass, dob);
        System.out.println(res);
        memberMenu();
      } else {
        System.out.println("Password Mismatch! Please re - register");
        register();
      }
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

  private void memberSubMenu(final int id, final String name) {
    System.out.println("===========================================");
    System.out.println("Welcome " + name);
    System.out.println("1. Amenities");
    System.out.println("2. Bookings");
    System.out.println("3. View Personal Details");
    System.out.println("4. Check Wallet");
    System.out.println("5. Sign Out");
    System.out.print("Your choice: ");
    int ch = opt.nextInt();
    System.out.println();
    switch (ch) {
      case 1:
        amenitiesMenu(id, name);
        break;
      case 2:
        bookingsMenu(id);
        break;
      case 3:
        myDetails(id, name);
        break;
      case 4:
        checkWallet(id);
        break;
      case 5:
        Runtime.getRuntime().exit(0);
      default:
        System.out.println("Please choose again!");
        break;
    }
    memberSubMenu(id, name);
  }

  private void amenitiesMenu(final int id, final String name) {
    System.out.println();
    System.out.println("1. List All Amenities");
    System.out.println("2. Find an Amenity");
    System.out.println("3. List By Category");
    System.out.print("Your Selection? ");
    int ch = opt.nextInt();

    switch (ch) {
      case 1:
        Amenities[] amList = AmenitiesFactory.listAllAmenities();
        if (amList.length > 0) {
          System.out.println("ID      , Description         , Price");
          for (Amenities a : amList) {
            System.out.println(a.getAmenityId() + ", " + a.getAmenityName() + ", " + a.getPrice());
          }
        } else {
          System.out.println("Sorry! Unable to get the Amenities List");
        }
        break;
      case 2:
        System.out.print("Amenity Id Please [Enter 0 for list of Amenities]: ");
        int x = opt.nextInt();
        if (x == 0) {
          Amenities[] aList = AmenitiesFactory.listAllAmenities();
          if (aList.length > 0) {
            System.out.println("ID      , Description");
            for (Amenities a : aList) {
              System.out.println(a.getAmenityId() + ", " + a.getAmenityName());
            }
            System.out.println("------------------------------------");
            System.out.println();
          }
        }
        System.out.print("Id please: ");
        x = opt.nextInt();
        Amenities am = AmenitiesFactory.showDetails(x);
        if (am != null) {
          System.out.println(am.toString());
        } else {
          System.out.println("Amenity Details Unavailable. Please check the Id.");
        }
        break;
      case 3:
        System.out.println("Amenity Categories: ");
        System.out.println("1. SPA (Charged Per Person)");
        System.out.println("2. POOL (Charged Per Person/hr)");
        System.out.println("3. TOUR (Charged based on Package");
        System.out.println("4. INDOOR GAMES (Charged Per Person/hr)");
        System.out.println("5. RESTUARANT (Charged based on number of Persons");
        System.out.println("6. ARCHERY (Charged Per Person/hr)");
        System.out.println("7. PAINTBALL (Charged Per Person/hr)");
        System.out.println("8. GOLF (Charged Per Person)");
        System.out.println("9. ROOMS (Charged based on number and type of Room");
        System.out.println("Please choose your category: ");
        int cat = opt.nextInt();

        String category = "";
        switch (cat) {
          case 1:
            category = "SPA";
            break;
          case 2:
            category = "POOL";
            break;
          case 3:
            category = "TOUR";
            break;
          case 4:
            category = "INDOOR GAMES";
            break;
          case 5:
            category = "RESTUARANT";
            break;
          case 6:
            category = "ARCHERY";
            break;
          case 7:
            category = "PAINTBALL";
            break;
          case 8:
            category = "GOLF";
            break;
          case 9:
            category = "ROOMS";
            break;
          default:
            category = "SPA";
            System.out.println("Not ready");
            break;
        }
        Amenities[] list = AmenitiesFactory.listByCategory(category);
        if (list.length > 0) {
          System.out.println("ID      , Description,    Price");
          for (Amenities a : list) {
            System.out.println(a.getAmenityId() + ", " + a.getAmenityName() + ", " + a.getPrice());
          }
          System.out.println("------------------------------------");
          System.out.println();
        }
        break;
      default:
        memberSubMenu(id, name);
        break;
    }
  }

  private void bookingsMenu(final int id) {
    Member m = new Member();

    // ArrayList<Booking> bookings = new ArrayList<Booking>();

    System.out.println();
    System.out.println("Welcome to Bookings Menu");
    System.out.println("========================");
    System.out.println("1. View My Previous Bookings");
    System.out.println("2. Book an Amenity");
    System.out.println("3. Cancel a Booking");
    System.out.print("What is your option? ");
    int ch = opt.nextInt();

    switch (ch) {
      case 1:
        Booking[] history = m.bookingHistory(id);
        if (history.length > 0) {
          for (Booking b : history) {
            System.out.println(b.toString());
          }
        } else {
          System.out.print("Looks like you are new here. ");
          System.out.println("Please book an Amenity to view your booking history");
        }
        break;
      case 2:
        System.out.println("Listing the Amenities: ");
        System.out.println("------------------------");
        Amenities[] list = AmenitiesFactory.listAllAmenities();

        if (list.length > 0) {
          System.out.println("ID    , Description     , Price");
          for (Amenities am : list) {
            System.out.println(am.getAmenityId() + ", " + am.getAmenityName() + ", " + am.getPrice());
          }
        } else {
          System.out.println("Sorry! unable to retrieve list");
        }

        System.out.print("Would you like to book one Amenity or more than one? 1-> Single booking, 2-> Multiple");
        int choice = opt.nextInt();

        String msg = "";
        //char c = 'n';

        switch (choice) {
          case 1:
            System.out.print("Id of the Amenity: ");
            int amId = opt.nextInt();
            System.out.print("How Many? ");
            int qty = opt.nextInt();
            msg = m.bookAnAmenity(amId, qty, id);
            System.out.println(msg);
            break;
          case 2:
            //int counter = 0;
            // do {
            //   System.out.print("Id of the Amenity: ");
            //   int amId1 = opt.nextInt();
            //   System.out.print("How Many? ");
            //   int qty1 = opt.nextInt();

            //   System.out.println("Another booking? ");
            //   counter++;

            //   if (counter > 3) {
            //     System.out.println("You can place a max of 3 bookings");
            //     break;
            //   }

            //   //msg = m.bookMultiple(amId1, qty1, id);
            System.out.println("Not ready yet");

           // } while (c == 'Y' || c == 'y');
            break;
          default:
            break;
        }

        // char c = opt.next().charAt(0);
        // char op = 'n';

        // if (c == 'y' || c == 'Y') {
        //   int counter = 0;
        //   do{
        //     Amenities[] list = AmenitiesFactory.listAllAmenities();
        //     if (list.length > 0) {
        //       System.out.println("ID    , Description     , Price");
        //       for (Amenities am : list) {
        //         System.out.println(am.getAmenityId() + ", " + am.getAmenityName() + ", " + am.getPrice());
        //       }
        //     } else {
        //       System.out.println("Sorry! unable to retrieve list");
        //     }

        //     System.out.print("Id of the Amenity: ");
        //     int amId = opt.nextInt();
        //     System.out.print("How Many? ");
        //     int qty = opt.nextInt();


        //     System.out.print("Another Booking? ");
        //     op = opt.next().charAt(0);

        //     if (op == 'Y' || op == 'y') {
        //       counter++;
        //     }
        //     if (counter > 0 && counter <= 3) {
        //       String msg = m.bookAnAmenity(amId, qty, id, counter);
        //       System.out.println(msg);
        //     } else {
        //       System.out.println("You can Book a max of 3 amenities and a min on 1");
        //     }

        //   } while (op == 'Y' || op == 'y');
        // }
        break;
      case 3:
        System.out.println("Booking Id [0 to view the list of current bookings]: ");
        int bId = opt.nextInt();
        String res = cancelBooking(id, bId);
        System.out.println(res);
        break;
      default:
        System.out.println("Wrong option");
        bookingsMenu(id);
        break;
    }
  }

  private void myDetails(final int id, final String name) {
    Member m = MemberFactory.getMemberById(id);
    System.out.println("======================================");
    System.out.println("Welcome " + name);
    System.out.println("1. Show Details");
    System.out.println("2. Update Phone Number");
    System.out.println("3. Update Password");
    System.out.println("Your option?");
    int ch = opt.nextInt();

    switch (ch) {
      case 1:
        System.out.println();
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + m.getPhone());
        System.out.println("Email: " + m.getEmail());
        System.out.println("Current Balance: " + m.getWalletbalance());
        System.out.println("Membership Date: " + m.getMembershipDate());
        System.out.println("------------------------------------------");
        System.out.println();
        break;
      case 2:
        System.out.println();
        System.out.print("New Phone Number? ");
        String ph = opt.next();
        String res = m.updatePhone(id, ph);
        System.out.println(res);
        break;
      case 3:
        System.out.print("Enter Current Password: ");
        String curPass = opt.next();
        System.out.print("Enter New Password: ");
        String pass = opt.next();
        System.out.print("Confirm New Password: ");
        String confirm = opt.next();
        if (curPass.equals(m.getPassKey()) && !curPass.equals(pass) && pass.equals(confirm)) {
          String str = m.updatePassword(id, pass);
          System.out.println(str);
        } else {
          System.out.println("Please check your password and try again!");
          myDetails(id, name);
        }
        break;
      default:
        System.out.println("Returning to Member Menu");
        memberSubMenu(id, name);
        break;
    }
  }

  private void checkWallet(final int id) {
    Member m = MemberFactory.getMemberById(id);
    System.out.println("Hi " + m.getMemberName());
    System.out.println("Your Wallet Balance At Present: " + m.getWalletbalance());
    System.out.print("Would you like to add to your wallet (y or n)? ");
    char yes = opt.next().charAt(0);
    if (yes == 'y' || yes == 'Y') {
      System.out.print("Enter the Amount to be Credited: ");
      double amt = opt.nextDouble();
      while (amt < 1000) {
        System.out.println("Amount should be Rs. 1000 minimum!");
        System.out.print("Enter the Amount to be Credited: ");
        amt = opt.nextDouble();
      }
      String res = m.updateWallet(id, amt);
      System.out.println(res);

      m = MemberFactory.getMemberById(id);
      System.out.println("Your Wallet Balance Now: " + m.getWalletbalance());
    }
  }

  private String cancelBooking(final int memId, final int bId) {
    Member m = new Member();

    String msg = "Cancellation was unsuccessful";
    int id = bId;

    if (bId == 0) {
      Booking[] bList = BookingFactory.listCurrentBookings(memId);
      if (bList.length > 0) {
        System.out.println("Today's Bookings for You");
        System.out.println("------------------------");
        System.out.println("Id  , Amount   , Amenity   ");
        for (Booking b: bList) {
          double amt = BookingFactory.retrieveBookingAmount(b.getBookingId());
          Amenities am = AmenitiesFactory.showDetails(b.getAmenityId());
          String n = am.getAmenityName();
          System.out.println(b.getBookingId() + ", " + amt + ", " + n);
        }
        System.out.print("Please enter Booking Id: ");
        id = opt.nextInt();
        msg = m.cancelBooking(id);
      } else {
        System.out.println("Please Book an amenity. No Bookings available for today");
      }
    } else {
      msg = m.cancelBooking(id);
    }
    return msg;
  }
}
