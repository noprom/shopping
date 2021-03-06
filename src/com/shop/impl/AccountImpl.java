package com.shop.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import com.shop.dap.IAccount;
import com.shop.pojo.Account;
import com.shop.util.DBHelper;


/**
 * 用户接口实现类
 * @author noprom
 *
 */
public class AccountImpl implements IAccount {

	/**
	 * 查询用户
	 */
	@Override
	public Account queryAccount(Account account) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Account tmpAccount = new Account();
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from account where username = ? and password = ?;"; // SQL查询
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getUsername());
			stmt.setString(2, account.getPassword());
			rs = stmt.executeQuery();
			if (rs.next()) {
				tmpAccount.setId(rs.getInt("id"));
				tmpAccount.setUsername(rs.getString("username"));
				tmpAccount.setPassword(rs.getString("password"));
				tmpAccount.setEmail(rs.getString("email"));
				tmpAccount.setPhone(rs.getString("phone"));
				tmpAccount.setSex(rs.getShort("sex"));
				return tmpAccount;
			} else {
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			//释放资源
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			//关闭
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
	}

}
