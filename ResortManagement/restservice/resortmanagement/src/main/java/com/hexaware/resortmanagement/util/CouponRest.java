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

import com.hexaware.resortmanagement.factory.CouponFactory;
import com.hexaware.resortmanagement.model.Coupon;

/**
 * rest api for Coupon.
 */
@Path("/coupons")
public class CouponRest {
  /**
   * to list all Coupons.
   * @return list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Coupon[] listAll() {
    Coupon[] list = CouponFactory.listAll();
    return list;
  }

  /**
   * to show a particular coupon detail.
   * @param id Coupon id
   * @return coupon
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public final Coupon showDetails(@PathParam("id") final String id) {
    Coupon c = CouponFactory.findById(id);

    if (c == null) {
      throw new NotFoundException("Coupon not found");
    }
    return c;
  }

  /**
   * to add new coupon.
   * @param cId Coupon id
   * @param exp Expiry date
   * @param amt Discount Amount
   * @param amName Amenity name
   * @return string
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/add/{cId}/{exp}/{amt}/{amName}")
  public final String addNewCoupon(@PathParam("cId") final String cId, @PathParam("exp") final String exp,
      @PathParam("amt") final double amt, @PathParam("amName") final String amName) {
    Coupon c = new Coupon();

    String msg = c.addCoupon(cId, exp, amt, amName);
    return msg;
  }

  /**
   * to update expiry date.
   * @param cName Coupon name
   * @param date New Date
   * @return string
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/expiry/{cName}/{date}")
  public final String updateExpiryDate(@PathParam("cName") final String cName, @PathParam("date") final String date) {
    Coupon c = new Coupon();

    String msg = c.updateExpiryDate(cName, date);
    return msg;
  }

  /**
   * to get coupons by amenity
   * @param id Amenity Id
   * @return list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/amenity/{id}")
  public final Coupon[] findByAmenity(@PathParam("id") final int id) {
    Coupon[] list = CouponFactory.findByAmenity(id);
    return list;
  }

  /**
   * to list availed coupons by member.
   * @param id Member id
   * @return list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/member/{id}")
  public final Coupon[] availedByMember(@PathParam("id") final int id) {
    Coupon[] list = CouponFactory.availedByMember(id);
    return list;
  }

  /**
   * to update availed coupons list.
   * @param id Coupon id
   * @param mem Member id
   * @return string
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/avail/{id}/{mem}")
  public final String availCoupon(@PathParam("id") final String id, @PathParam("mem") final int mem) {
    String msg = "Unable to update Availed Coupons list";

    int i = CouponFactory.availCoupon(id, mem);
    if (i > 0) {
      msg = "Updated Availed Coupons list";
    }

    return msg;
  }

  /**
   * to check for available coupons for a member.
   * @param mem Member id
   * @param amId Amenity Id
   * @param exp Expiry Date
   * @return list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/available/{mem}/{amId}/{exp}")
  public final Coupon[] availableCoupons(@PathParam("mem") final int mem, @PathParam("amId") final int amId,
      @PathParam("exp") final String exp) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date d = new Date();
    
    try {
      d = sdf.parse(exp);
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
    }

    Coupon[] list = CouponFactory.availableCoupons(mem, amId, d);
    return list;
  }
}
