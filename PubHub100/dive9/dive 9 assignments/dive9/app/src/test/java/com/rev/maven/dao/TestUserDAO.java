package com.rev.maven.dao;

import com.rev.maven.model.Book;
import com.rev.maven.model.User;
import com.rev.maven.model.UserManager;

public class TestUserDAO {
	public static void main(String[] args) {
		User user = new User();
		UserManager userManager = new UserManager();
		BookDAO bookDAO = new BookDAO();
		UserDAO userDAO = new UserDAO();
		
		user.setName("asd");
		user.setPassword("test");
		user.setMobileno(123);
		user.setEmailid("asd");
		user.setUsername("asd");
		
		Book book = new Book();
		book.setAuthor("author");
		book.setContent("content");
		book.setIsbn13("2234567890123");
		book.setPrice(120);
		book.setPublishDate("2011-10-10 10:10:10");
		book.setStatus("PUBLISHED");
		book.setTitle("title2");
		
        //userManager.addUser(user);
		//userDAO.userRegistration(user);
		//user.setPassword("qwe");
		//userDAO.userResetPassword(user, "test");
		//userDAO.userRating(user, book, 4);

	}

}
