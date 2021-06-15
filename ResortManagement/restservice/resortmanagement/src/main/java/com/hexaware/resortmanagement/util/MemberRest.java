package com.hexaware.resortmanagement.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hexaware.resortmanagement.factory.MemberFactory;
import com.hexaware.resortmanagement.model.Booking;
import com.hexaware.resortmanagement.model.Member;

/**
 * provides the rest services for member.
 */
@Path("/members")
public class MemberRest {
  /**
   * to get a particular member's details.
   * @param id for memberId
   * @return member object
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Member listMemberById(@PathParam("id") final int id) {
    Member m = MemberFactory.getMemberById(id);

    if (m == null) {
      throw new NotFoundException("Member with ID: " + id + " not found!");
    }

    return m;
  }

  /**
   * to get a particular member's details by email.
   * @param em for email
   * @return member object
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/check/{em}")
  public final Member listMemberByEmail(@PathParam("em") final String em) {
    Member m = MemberFactory.getMemberByEmail(em);

    if (m == null) {
      throw new NotFoundException("Member with Email: " + em + " not found!");
    }
    return m;
  }

  /**
   * to add a new member.
   * @param name for name
   * @param phone for phone
   * @param email for email
   * @param pass for password
   * @param dob for dob
   * @return string
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/addnew/{name}/{phone}/{email}/{pass}/{dob}")
  public final String registerNewMember(@PathParam("name") final String name, @PathParam("phone") final String phone,
      @PathParam("email") final String email, @PathParam("pass") final String pass,
      @PathParam("dob") final String dob) {
    Member m = new Member();
    String memName = name.replace("_", " ");
    String res = m.registerMember(memName, phone, email, pass, dob);
    return res;
  }

  /**
   * to update phone number.
   * @param id for member id
   * @param ph for new phone number
   * @return string
   */
  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/phone/{id}/{ph}")
  public final String updatePhone(@PathParam("id") final int id, @PathParam("ph") final String ph) {
    Member m = new Member();
    String res = m.updatePhone(id, ph);
    return res;
  }

  /**
   * to update the password.
   * @param id for member id
   * @param pass for member password
   * @return string
   */
  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/password/{id}/{pass}")
  public final String updatePassword(@PathParam("id") final int id, @PathParam("pass") final String pass) {
    Member m = new Member();
    String res = m.updatePassword(id, pass);
    return res;
  }

  /**
   * to update wallet amount.
   * @param id for member id
   * @param amt for amount
   * @return string
   */
  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/wallet/{id}/{amt}")
  public final String updateWallet(@PathParam("id") final int id, @PathParam("amt") final double amt) {
    Member m = new Member();
    String res = m.updateWallet(id, amt);
    return res;
  }

  /**
   * to book an amenity.
   * @param amId for amenityId
   * @param qty for quantity
   * @param id for member id
   * @return string
   */
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/book/{amId}/{qty}/{id}")
  public final String bookAnAmenity(@PathParam("amId") final int amId, @PathParam("qty") final int qty,
      @PathParam("id") final int id) {
    Member m = new Member();
    String res = m.bookAnAmenity(amId, qty, id);
    return res;
  }

  /**
   * to get the booking history of a member.
   * @param id for member id
   * @return list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/history/{id}")
  public final Booking[] bookingHistory(@PathParam("id") final int id) {
    Member m = new Member();
    Booking[] list = m.bookingHistory(id);
    return list;
  }

  /**
   * to cancel a booking.
   * @param bId for booking id
   * @return string
   */
  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/cancel/{bId}")
  public final String cancelBooking(@PathParam("bId") final int bId) {
    Member m = new Member();
    String res = m.cancelBooking(bId);
    return res;
  }

  /**
   * increment wallet balance.
   * @param id for member id
   * @param bal for current wallet balance
   * @param amt for amount to be credited
   * @return string
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/credit/{id}/{bal}/{amt}")
  public final String incrementBalance(@PathParam("id") final int id,
      @PathParam("bal") final double bal, @PathParam("amt") final double amt) {
    String msg = "Booking Amount Not Credited to Wallet";

    int i = MemberFactory.incrementWallet(id, bal, amt);

    if (i > 0) {
      msg = "Booking Amount Credited to Wallet";
    }

    return msg;
  }

  /**
   * decrement wallet balance.
   * @param id for member id
   * @param bal for current wallet balance
   * @param amt for amount to be debited
   * @return string
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/debit/{id}/{bal}/{amt}")
  public final String decrementBalance(@PathParam("id") final int id,
      @PathParam("bal") final double bal, @PathParam("amt") final double amt) {
    String msg = "Booking Amount Not Debited From Wallet";

    int i = MemberFactory.decrementWallet(id, bal, amt);

    if (i > 0) {
      msg = "Booking Amount Debited From Wallet";
    }

    return msg;
  }

  /**
   * to update the birthdate.
   * @param id for member id
   * @param date for birthdate
   * @return string
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/birthday/{id}/{date}")
  public final String updateBirthDate(@PathParam("id") final int id, @PathParam("date") final String date) {
    String msg = "Birthdate Not Added Successfully";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date dob = new Date();
    try{
      dob = sdf.parse(date);
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
    }
    int i = MemberFactory.updateBirthDate(id, dob);

    if (i > 0) {
      msg = "Birthdate Added Successfully";
    }

    return msg;
  }

  /**
   * to retrieve the birthdate.
   * @param id for member id
   * @return int
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/getmonth/{id}")
  public final int retrieveBirthDate(@PathParam("id") final int id) {
    int res = MemberFactory.retrieveBirthDate(id);
    return res;
  }
}
