package com.rev.maven.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rev.maven.model.Book;
import com.rev.maven.model.User;
import com.rev.maven.util.ConnectionUtil;

public class UserDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	
	public void userRegistration(User user){
		String sql = "call USER_REGISTRATION(?, ?, ?, ?, 'yes', 'USER');";
		//parameters for ? => (NAME, PASSWORD, MOBILE_NO, EMAIL_ID, ACTIVE, ROLE)
		Object[] params = {user.getUsername(), user.getPassword(), user.getMobileno(), user.getEmailid()};
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of user inserted: " + rows);
	}
	
	public boolean userLogin(User user){
		String sql = "call USER_LOGIN(?, ?, ?)";
		//parameters for ? => (NAME, PASSWORD, EMAIL_ID)
		Object[] params = {user.getUsername(), user.getPassword(), user.getEmailid()};
		int rows = jdbcTemplate.queryForObject(sql, params, Integer.class);
		
		if (rows == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void userResetPassword(User user, String newPassword){
		String sql = "call USER_PASSWORD_RESET(?, ?, ?, ?)";
		//parameters for ? => (NAME, EMAIL_ID, PASSWORD, NEW_PASSWORD)
		Object[] params = {user.getUsername(), user.getPassword(), user.getEmailid(), newPassword};
		int rows = jdbcTemplate.update(sql, params);
		
		if (rows == 1) {
			System.out.println("Password changed");
		}
		else {
			System.out.println("No password is changed");
		}
	}
	
	public void userRating(User user, Book book, int rating){
		String sql = "call USER_RATING(?, ?, ?)";
		//parameters for ? => (EAMIL_ID, BOOK_ID, RATING)
		Object[] params = {user.getEmailid(), book.getIsbn13(), rating};
		int rows = jdbcTemplate.update(sql, params);
		
		System.out.println(rows);
	}

}
