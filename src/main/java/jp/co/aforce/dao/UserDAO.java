package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.User;

public class UserDAO extends DAO {

	public List<User> getAllUsers() throws Exception {
	    List<User> users = new ArrayList<>();
	    Connection con = getConnection();

	    String sql = "SELECT * FROM users ORDER BY member_id";
	    PreparedStatement st = con.prepareStatement(sql);
	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {
	        User user = new User();
	        user.setMemberId(rs.getString("member_id"));
	        user.setLastName(rs.getString("last_name"));
	        user.setFirstName(rs.getString("first_name"));
	        user.setAddress(rs.getString("address"));
	        user.setMailAddress(rs.getString("mail_address"));
	        user.setAdmin(rs.getInt("admin"));  // ← これもあるなら追加
	        users.add(user);
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return users;
	}
	
	public User findUserById(String memberId) throws Exception {
	    Connection con = getConnection();
	    String sql = "SELECT * FROM users WHERE member_id=?";
	    PreparedStatement st = con.prepareStatement(sql);
	    st.setString(1, memberId);
	    ResultSet rs = st.executeQuery();

	    User user = null;
	    if (rs.next()) {
	        user = new User();
	        user.setMemberId(rs.getString("member_id"));
	        user.setLastName(rs.getString("last_name"));
	        user.setFirstName(rs.getString("first_name"));
	        user.setAddress(rs.getString("address"));
	        user.setMailAddress(rs.getString("mail_address"));
	        user.setAdmin(rs.getInt("admin"));
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return user;
	}
	
	public void updateUser(User user) throws Exception {
	    Connection con = getConnection();

	    String sql = "UPDATE users SET last_name=?, first_name=?, address=?, mail_address=?, admin=? WHERE member_id=?";
	    PreparedStatement st = con.prepareStatement(sql);
	    st.setString(1, user.getLastName());
	    st.setString(2, user.getFirstName());
	    st.setString(3, user.getAddress());
	    st.setString(4, user.getMailAddress());
	    st.setInt(5, user.getAdmin());
	    st.setString(6, user.getMemberId());

	    st.executeUpdate();

	    st.close();
	    con.close();
	}
	
	public int deleteUser(String memberId) throws Exception {
		Connection con = getConnection();
		String sql = "DELETE FROM users WHERE member_id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberId);
		int result = st.executeUpdate();

		st.close();
		con.close();

		return result;
	}


}
