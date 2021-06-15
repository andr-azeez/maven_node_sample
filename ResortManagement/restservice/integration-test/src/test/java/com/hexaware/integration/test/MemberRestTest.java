package com.hexaware.integration.test;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.jayway.restassured.http.ContentType;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MemberRestTest {
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  @Test
  public void testListMemberById() throws URISyntaxException, ParseException, AssertionError {
    Member m = given().accept(ContentType.JSON).when()
        .get(CommonUtil.getURI("/api/members/112")).getBody().as(Member.class);
    
    assertEquals(112, m.getMemberId());
    assertEquals("Jean King", m.getMemberName());
    assertEquals("jking@xyz.com", m.getEmail());
    assertEquals("7025551838", m.getPhone());
    assertEquals(71800.0, m.getWalletbalance(), 1);
    assertEquals("king123", m.getPassKey());
    assertNotEquals(sdf.parse("2020-10-02"), m.getMembershipDate());
  }
  
  @Test
  public void testListMemberByEmail() throws URISyntaxException, ParseException, AssertionError {
    Member m = given().accept(ContentType.JSON).when()
        .get(CommonUtil.getURI("/api/members/check/jking@xyz.com")).getBody().as(Member.class);

    assertEquals(112, m.getMemberId());
    assertEquals("Jean King", m.getMemberName());
    assertEquals("jking@xyz.com", m.getEmail());
    assertEquals("7025551838", m.getPhone());
    assertEquals(71800.0, m.getWalletbalance(), 1);
    assertEquals("king123", m.getPassKey());
    assertNotEquals(sdf.parse("2020-10-02"), m.getMembershipDate());
  }

  @Test
  public void testRegisterNewMember() throws URISyntaxException, AssertionError {
    String msg = given().contentType("application/json").when()
        .post(CommonUtil.getURI("/api/members/addnew/Kevin_Costner/93882922/kevin@xyz.com/kevin123/1997-10-12"))
        .getBody().asString();
    assertEquals("Member Registered Successfully! Please login to continue", msg);
  }

  @Test
  public void testUpdateWallet() throws URISyntaxException, AssertionError {
    String msg = given().contentType("application/json").when()
        .put(CommonUtil.getURI("/api/members/wallet/131/3000"))
        .getBody().asString();
    assertEquals("Balance updated", msg);
  }

  @Test
  public void testUpdatePassword() throws URISyntaxException, AssertionError {
    String msg = given().contentType("application/json").when()
        .put(CommonUtil.getURI("/api/members/password/131/test123"))
        .getBody().asString();
    assertEquals("Password updated successfully", msg);
  }

  @Test
  public void testUpdatePhone() throws URISyntaxException, AssertionError {
    String msg = given().contentType("application/json").when()
        .put(CommonUtil.getURI("/api/members/phone/129/837773737"))
        .getBody().asString();
    assertEquals("Phone updated successfully", msg);
  }

  @Test
  public void testBookAnAmenity() throws URISyntaxException, AssertionError {
    String msg = given().contentType("application/json").when()
        .post(CommonUtil.getURI("/api/members/book/4002/2/129"))
        .getBody().asString();
    assertEquals("Amenity Booked Successfully", msg);
  }

  @Test
  public void testMemberNotFound404() throws URISyntaxException, AssertionError {
    given().accept(ContentType.JSON).when()
      .get(CommonUtil.getURI("/api/members/2000")).then().assertThat().statusCode(404);
  }

  @Test
  public void testMemberExists200() throws URISyntaxException, AssertionError {
    given().accept(ContentType.JSON).when()
        .get(CommonUtil.getURI("/api/members/129")).then().assertThat().statusCode(200);
  }

  @Test
  public void testCancelBooking() throws URISyntaxException, AssertionError {
    String msg = given().contentType("application/json").when()
        .put(CommonUtil.getURI("/api/members/cancel/3003"))
        .getBody().asString();
    assertEquals("Booking Cancelled Successfully", msg);
  }

  @Test
  public void testBookingHistory() throws URISyntaxException, AssertionError, ParseException {
    Booking[] list = given().accept(ContentType.JSON).when()
        .get(CommonUtil.getURI("/api/members/history/129")).getBody().as(Booking[].class);
    assertEquals(2, list.length);
    assertEquals(3002, list[0].getBookingId());
    assertNotEquals(sdf.parse("2021-03-30"), list[0].getBookingDate());
    assertEquals(4002, list[0].getAmenityId());
    assertEquals(2, list[0].getQuantity());
    assertEquals(1076, list[0].getEmployeeId());
    assertEquals("PENDING", list[0].getStatus().name());
  }
}
