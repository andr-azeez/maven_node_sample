package com.hexaware.integration.test;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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

  public String toString() {
    String response = "";
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    try {
      response = ow.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return response;
  }
}
