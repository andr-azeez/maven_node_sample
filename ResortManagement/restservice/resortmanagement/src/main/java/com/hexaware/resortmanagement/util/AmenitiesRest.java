package com.hexaware.resortmanagement.util;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hexaware.resortmanagement.factory.AmenitiesFactory;
import com.hexaware.resortmanagement.model.Amenities;

/**
 * rest api for Amenities.
 */
@Path("/amenities")
public class AmenitiesRest {
  /**
   * to find the last row.
   * @return amenity
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/last")
  public final Amenities findLastRow() {
    Amenities a = AmenitiesFactory.findLastRow();
    if (a == null) {
      throw new NotFoundException("No Amenities Available");
    }
    return a;
  }

  /**
   * to list all amenities.
   * @return list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Amenities[] listAllAmenities() {
    Amenities[] list = AmenitiesFactory.listAllAmenities();
    return list;
  }

  /**
   * to list an amenity by name.
   * @param name amenity name
   * @return amenity
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/byname/{name}")
  public final Amenities listAmenityByName(@PathParam("name") final String name) {
    Amenities a = AmenitiesFactory.listAmenityByName(name);

    if (a == null) {
      throw new NotFoundException("No Amenities Available");
    }
    return a;
  }

  /**
   * to add new amenity.
   * @param name Amenity name
   * @param price Amenity price
   * @param id employee id
   * @param cat Amenity category
   * @return string
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/addnew/{name}/{price}/{id}/{cat}")
  public final String addNewAmenity(@PathParam("name") final String name, @PathParam("price") final double price,
      @PathParam("id") final int id, @PathParam("cat") final String cat) {
    Amenities a = new Amenities();
    String amName = name.replace("_", " ");
    String msg = a.addNewAmenity(amName, price, id, cat);
    return msg;
  }

  /**
   * to show details of an amenity.
   * @param id Amenity id
   * @return amenity
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public final Amenities showDetails(@PathParam("id") final int id) {
    Amenities a = AmenitiesFactory.showDetails(id);

    if (a == null) {
      throw new NotFoundException("No Amenities Available");
    }
    return a;
  }

  /**
   * to get list of amenities by category.
   * @param cat Category
   * @return list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/category/{cat}")
  public final Amenities[] listByCategory(@PathParam("cat") final String cat) {
    Amenities[] list = AmenitiesFactory.listByCategory(cat);
    return list;
  }

  /**
   * to list by employee.
   * @param id Employee id
   * @return list
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/employee/{id}")
  public final Amenities[] listByEmployee(@PathParam("id") final int id) {
    Amenities[] list = AmenitiesFactory.listByEmployee(id);
    return list;
  }

  /**
   * to update price.
   * @param id Amenity Id
   * @param price price
   * @return string
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/update/{id}/{price}")
  public final String updatePrice(@PathParam("id") final int id, @PathParam("price") final double price) {
    String msg = "Price Not Updated";

    int i = AmenitiesFactory.updatePrice(id, price);

    if (i > 0) {
      msg = "Price Updated Successfully";
    }

    return msg;
  }
}
