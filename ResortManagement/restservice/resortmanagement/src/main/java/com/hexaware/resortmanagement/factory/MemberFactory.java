package com.hexaware.resortmanagement.factory;

import java.util.Date;

import com.hexaware.resortmanagement.model.Member;
import com.hexaware.resortmanagement.persistence.DBConnection;
import com.hexaware.resortmanagement.persistence.MembersDAO;

/**
 * factory class for member.
 */
public class MemberFactory {
  /**
   * protected constructor.
   */
  protected MemberFactory() {

  }

  /**
   * dao() method.
   * @return MembersDAO
   */
  private static MembersDAO dao() {
    DBConnection db  = new DBConnection();
    return db.getConnect().onDemand(MembersDAO.class);
  }

  /**
   * to retrieve a member by email.
   * @param argEmail for email
   * @return Member
   */
  public static Member getMemberByEmail(final String argEmail) {
    Member m = dao().getMemberByEmail(argEmail);
    return m;
  }

  /**
   * to retrieve member details by id.
   * @param id for member id
   * @return Member
   */
  public static Member getMemberById(final int id) {
    Member m = dao().findById(id);
    return m;
  }

  /**
   * to get the last row.
   * @return Member
   */
  public static Member findLastRow() {
    Member m = dao().findLastRow();
    return m;
  }

  /**
   * to register a new member.
   * @param argId for id
   * @param argName for name
   * @param argEmail for email
   * @param argPhone for phone
   * @param balance for wallet balance
   * @param pass for password
   * @param joinDate for membership date
   * @return int
   */
  public static int registerMember(final int argId, final String argName, final String argEmail,
      final String argPhone, final double balance, final String pass, final Date joinDate) {
    int val = dao().registerMember(argId, argName, argEmail, argPhone, balance, pass, joinDate);
    return val;
  }

  /**
   * to update phone.
   * @param id for id
   * @param argPhone for new phone number
   * @return int
   */
  public static int updatePhone(final int id, final String argPhone) {
    int val = dao().updatePhone(id, argPhone);
    return val;
  }

  /**
   * to update password.
   * @param id for id
   * @param pass for new password
   * @return int
   */
  public static int updatePassword(final int id, final String pass) {
    int val = dao().updatePassword(id, pass);
    return val;
  }

  /**
   * to decrement the wallet when room is booked.
   * @param id for member id
   * @param curamt for current wallet balance
   * @param amt for booking amount
   * @return int
   */
  public static int decrementWallet(final int id, final double curamt, final double amt) {
    int val = dao().decrementWallet(id, curamt, amt);
    return val;
  }

  /**
   * to increment the wallet when booking is cancelled or denied.
   * @param id for member id
   * @param curamt for current wallet balance
   * @param amt for booking amount
   * @return int
   */
  public static int incrementWallet(final int id, final double curamt, final double amt) {
    int val = dao().incrementWallet(id, curamt, amt);
    return val;
  }

  /**
   * to increase the wallet balance.
   * @param id for member id
   * @param bal for current balance
   * @param amt for amount to be added
   * @return int
   */
  public static int updateWalletAmount(final int id, final double bal, final double amt) {
    int val = dao().updateWalletAmount(id, bal, amt);
    return val;
  }

  /**
   * to update the date of birth.
   * @param id for member id
   * @param dob for date of birth
   * @return int
   */
  public static int updateBirthDate(final int id, final Date dob) {
    int res = dao().updateBirthDate(dob, id);
    return res;
  }

  /**
   * to get the birth date.
   * @param id for member id
   * @return int
   */
  public static int retrieveBirthDate(final int id) {
    int month = dao().retrieveBirthDate(id);
    return month;
  }
}
