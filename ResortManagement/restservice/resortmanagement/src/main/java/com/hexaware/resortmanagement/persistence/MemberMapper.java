package com.hexaware.resortmanagement.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hexaware.resortmanagement.model.Member;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * Mapper class for Member.
 */
public class MemberMapper implements ResultSetMapper<Member> {
  /**
   * map method.
   * @param idx for index
   * @param rs for Resultset
   * @param stmt for StatementContext
   * @return Member
   * @throws SQLException for SQLException
   */
  public final Member map(final int idx, final ResultSet rs, final StatementContext stmt) throws SQLException {
    return new Member(rs.getInt("MEMBERID"), rs.getString("MEMBERNAME"), rs.getString("EMAIL"),
        rs.getString("PHONE"), rs.getDouble("WALLETBALANCE"), rs.getString("PASSKEY"), rs.getDate("MEMBERSHIPDATE"));
  }
}
