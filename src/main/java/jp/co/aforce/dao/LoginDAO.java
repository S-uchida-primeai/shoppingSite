package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Login;

public class LoginDAO extends DAO {
	
	 public int insertUser(Login user) throws Exception {
	        Connection con = getConnection();

	        String sql = "INSERT INTO users (id, password, lastName, firstName, address, email) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement st = con.prepareStatement(sql);
	        st.setString(1, user.getId());
	        st.setString(2, user.getPassword());
	        st.setString(3, user.getLastName());
	        st.setString(4, user.getFirstName());
	        st.setString(5, user.getAddress());
	        st.setString(6, user.getEmail());

	        int result = st.executeUpdate();

	        st.close();
	        con.close();

	        return result;
	    }
	
	public List<Login> getALLlogin() throws Exception {
		List<Login> users = new ArrayList<>();
		Connection con = getConnection();
		String sql = "SELECT * FROM users";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			String id = rs.getString("MEMBER_ID");
			String password = rs.getString("PASSWORD");
			String lastName = rs.getString("LAST_NAME");
			String firstName = rs.getString("FIRST_NAME");
			String address = rs.getString("ADDRESS");
			String email = rs.getString("MAIL_ADDRESS");
			Login customer = new Login(id, password, lastName, firstName, address, email);
			users.add(customer);
		}
		
		st.close();
		con.close();
		return users;
	}
	
	public Login loginCheck (String id, String password) throws Exception {
		Connection con = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    Login user = null;
	    
	    try {
	        con = getConnection();
	        String sql = "SELECT * FROM users WHERE MEMBER_ID = ? AND PASSWORD = ?";
	        st = con.prepareStatement(sql);
	        st.setString(1, id);
	        st.setString(2, password);

	        rs = st.executeQuery();

	        if (rs.next()) {
	        	String memberId = rs.getString("MEMBER_ID"); 
	            String dbPassword = rs.getString("PASSWORD");
	            String lastName = rs.getString("LAST_NAME");
	            String firstName = rs.getString("FIRST_NAME");
	            String address = rs.getString("ADDRESS");
	            String email = rs.getString("MAIL_ADDRESS");

	            user = new Login(memberId, dbPassword, lastName, firstName, address, email);
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (st != null) st.close();
	        if (con != null) con.close();
	    }

	    return user;
	}
	
	
	
	


}
