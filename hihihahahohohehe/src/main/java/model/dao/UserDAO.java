package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.bean.User;

public class UserDAO {
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;

	public User getUser(String username, String password) throws ClassNotFoundException, SQLException {
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Select * from User where username=? and password=?";

		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Integer idUser = rs.getInt("idUser");
			String username1= rs.getString("username");
			String password1 = rs.getString("password");
			User user = new User();
			user.setIdUser(idUser);
			user.setUsername(username1);
			user.setPassword(password1);
			return user;
		}
		return null;
	}
}
