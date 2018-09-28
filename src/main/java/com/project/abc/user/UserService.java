package com.project.abc.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private User user;

	@Autowired
	private UserDAO userDAO;

	private boolean success;

	public Map<String, Object> getUsers(String searchQuery, Integer pageNumber, String sortBy, String order, Integer limit) {
		Map<String, Object> users = new HashMap<>();
		try {
			users = userDAO.getUsers(searchQuery, pageNumber, sortBy, order, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}

	public User retrieveUserInfo(String userName, String Password) {
		user = null;
		try {
			user = userDAO.retrieveUserInfo(userName, Password);
			if (user != null) {
				user.setSuccessful(isSuccessfull(Password, user.getUserPassword()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public boolean addNewUser(User user) {
		success = false;
		try {
			user.setUserPassword(encryptPassword(user.getUserLastName()));
			success = userDAO.addNewUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	public boolean deleteUser(Integer userId) {
		success = false;
		try {
			success = userDAO.deleteUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	public boolean updateUserInfo(User user) {
		success = false;
		try {
			success = userDAO.updateUserInfo(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	private String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	private boolean isSuccessfull(String inputPassword, String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(inputPassword, password);
	}

	public User getUser(Integer id) {
		User user = new User();

		try {
			user = userDAO.retrieveUserInfo(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
}
