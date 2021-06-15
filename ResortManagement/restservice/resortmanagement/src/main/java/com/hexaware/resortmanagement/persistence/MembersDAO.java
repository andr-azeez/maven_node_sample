package com.hexaware.resortmanagement.persistence;

import java.util.Date;

import com.hexaware.resortmanagement.model.Member;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * DAO class for Members.
 */
public interface MembersDAO {
  /**
   * get member by email -> for login.
   * @param email for email
   * @return member
   */
  @SqlQuery("SELECT * FROM MEMBERS WHERE EMAIL = :email")
  @Mapper(MemberMapper.class)
  Member getMemberByEmail(@Bind("email") final String email);

  /**
   * register a new member.
   * @param id member id
   * @param name membername
   * @param phone phone
   * @param email email
   * @param pass password
   * @param balance walletbalance
   * @param joinDate date of joining
   * @return int
   */
  // @SqlUpdate("INSERT INTO MEMBERS VALUES (:id, :name, :email, :phone, :balance, :pass, DATE_ADD(:joinDate, INTERVAL 2 MONTH))")
  @SqlUpdate("INSERT INTO MEMBERS (MEMBERID, MEMBERNAME, EMAIL, PHONE, WALLETBALANCE,"
      + "PASSKEY, MEMBERSHIPDATE) VALUES (:id, :name, :email, :phone, :balance, :pass, :joinDate)")
  int registerMember(@Bind("id") final int id, @Bind("name") final String name,
      @Bind("email") final String email, @Bind("phone") final String phone,
      @Bind("balance") final double balance, @Bind("pass") final String pass,
      @Bind("joinDate") final Date joinDate);

  /**
   * find the last row in member table.
   * @return member
   */
  @SqlQuery("SELECT * FROM MEMBERS WHERE MEMBERID = (SELECT MAX(MEMBERID) FROM MEMBERS)")
  @Mapper(MemberMapper.class)
  Member findLastRow();

  /**
   * find member by id.
   * @param id memberId
   * @return member
   */
  @SqlQuery("SELECT * FROM MEMBERS WHERE MEMBERID = :id")
  @Mapper(MemberMapper.class)
  Member findById(@Bind("id") final int id);

  /**
   * update phone.
   * @param id memberid
   * @param phone new phone
   * @return int
   */
  @SqlUpdate("UPDATE MEMBERS SET PHONE = :phone WHERE MEMBERID = :id")
  int updatePhone(@Bind("id") final int id, @Bind("phone") final String phone);

  /**
   * update password.
   * @param id memberid
   * @param pass new password
   * @return int
   */
  @SqlUpdate("UPDATE MEMBERS SET PASSKEY = :pass WHERE MEMBERID = :id")
  int updatePassword(@Bind("id") final int id, @Bind("pass") final String pass);

  /**
   * reduce wallet balance on booking room.
   * @param id member id
   * @param curamt current wallet balance
   * @param amt amount to be deducted
   * @return int
   */
  @SqlUpdate("UPDATE MEMBERS SET WALLETBALANCE = :curamt - :amt WHERE MEMBERID = :id")
  int decrementWallet(@Bind("id") final int id, @Bind("curamt") final double curamt, @Bind("amt") final double amt);

  /**
   * increase wallet balance on booking cancellation.
   * @param id member id
   * @param curamt current wallet balance
   * @param amt amount to be credited.
   * @return int
   */
  @SqlUpdate("UPDATE MEMBERS SET WALLETBALANCE = :curamt + :amt WHERE MEMBERID = :id")
  int incrementWallet(@Bind("id") final int id, @Bind("curamt") final double curamt, @Bind("amt") final double amt);

  /**
   * update wallet balance.
   * @param id member id
   * @param bal current wallet balance
   * @param newAmt amount to be added
   * @return int
   */
  @SqlUpdate("UPDATE MEMBERS SET WALLETBALANCE = :bal + :newAmt WHERE MEMBERID = :id")
  int updateWalletAmount(@Bind("id") final int id, @Bind("bal") final double bal, @Bind("newAmt") final double newAmt);

  /**
   * update date of birth.
   * @param dob for date of birth
   * @param id for member id
   * @return int
   */
  @SqlUpdate("UPDATE MEMBERS SET DOB = :dob WHERE MEMBERID = :id")
  int updateBirthDate(@Bind("dob") final Date dob, @Bind("id") final int id);

  /**
   * to get the date of birth.
   * @param id for member id
   * @return int
   */
  @SqlQuery("SELECT MONTH(DOB) FROM MEMBERS WHERE MEMBERID = : id")
  int retrieveBirthDate(@Bind("id") final int id);
}
