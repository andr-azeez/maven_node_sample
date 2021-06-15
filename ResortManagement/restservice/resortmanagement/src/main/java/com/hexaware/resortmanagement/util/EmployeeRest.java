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

import com.hexaware.resortmanagement.factory.EmployeeFactory;
import com.hexaware.resortmanagement.model.Employee;

/**
 * rest class for Employees.
 */
@Path("/employees")
public class EmployeeRest {
  /**
   * to get an employee by Id.
   * @param id Employee Id
   * @return Employee
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public final Employee findById(@PathParam("id") final int id) {
    Employee e = EmployeeFactory.findById(id);

    if (e == null) {
      throw new NotFoundException("Employee with Id: " + id + " Not Found!");
    }

    return e;
  }

  /**
   * to find an employee by email.
   * @param email Employee email
   * @return employee
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/check/{email}")
  public final Employee findByEmail(@PathParam("email") final String email) {
    Employee e = EmployeeFactory.findByEmail(email);

    if (e == null) {
      throw new NotFoundException("Employee with Email: " + email + " Not Found!");
    }

    return e;
  }

  /**
   * to register an employee.
   * @param name Employee name
   * @param ph Employee phone
   * @param em Employee email
   * @param pass Employee Password
   * @return string
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/register/{name}/{ph}/{em}/{pass}")
  public final String registerEmployee(@PathParam("name") final String name, @PathParam("ph") final String ph,
      @PathParam("em") final String em, @PathParam("pass") final String pass) {
    Employee e = new Employee();
    String msg = e.registerEmployee(name, ph, em, pass);
    return msg;
  }

  /**
   * to update the phone number.
   * @param id Employee id
   * @param ph phone
   * @return string
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/phone/{id}/{ph}")
  public final String updatePhone(@PathParam("id") final int id, @PathParam("ph") final String ph) {
    Employee e = new Employee();
    String msg = e.updatePhone(id, ph);
    return msg;
  }

  /**
   * to update Password.
   * @param id Employee id
   * @param pass password
   * @return string
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/password/{id}/{pass}")
  public final String updatePassword(@PathParam("id") final int id, @PathParam("pass") final String pass) {
    Employee e = new Employee();
    String msg = e.updatePassword(id, pass);
    return msg;
  }

  /**
   * to accept or deny booking.
   * @param bId Booking id
   * @param stat Status
   * @param amt Amount
   * @return string
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/acceptdeny/{bId}/{stat}/{amt}")
  public final String acceptDenyBooking(@PathParam("bId") final int bId, @PathParam("stat") final String stat,
      @PathParam("amt") final double amt) {
    Employee e = new Employee();
    String msg = e.acceptDenyBooking(bId, stat, amt);
    return msg;
  }
}
