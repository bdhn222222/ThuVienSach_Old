package model.bo;

import java.sql.SQLException;

import model.bean.User;
import model.dao.UserDAO;

public class UserBO {
	UserDAO userDAO = new UserDAO();

	public User getAccount(String username, String password) throws ClassNotFoundException, SQLException {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return userDAO.getUser(username, password);
	}
}
