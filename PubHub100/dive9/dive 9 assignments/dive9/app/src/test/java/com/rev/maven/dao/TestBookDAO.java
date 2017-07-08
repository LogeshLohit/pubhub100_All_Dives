package com.rev.maven.dao;

import com.rev.maven.model.Book;
import com.rev.maven.model.User;
import com.rev.maven.model.UserManager;

public class TestBookDAO {
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
		//bookDAO.createBook(book);
		//bookDAO.uploadContents(book, "new cont");
		//bookDAO.viewBookAndContent("author");
		//bookDAO.deleteBook(book);
		//bookDAO.viewAllBookOfAuthor("author");
		//bookDAO.viewBooks();
		//System.out.println(bookDAO.placeOrder(user, book, 1));
		//bookDAO.bookQuantity(book);
		//bookDAO.deleteOrder(user, book);
		//bookDAO.listAllBooks();
		//bookDAO.searchByBookName("titl");
		//bookDAO.searchByAuthorName("auth");
		//bookDAO.searchByPrice(130, 10);
		//bookDAO.searchByISBN13("2234567890123");
		//bookDAO.changeBookQuantity(book, 40);
		//bookDAO.booksSold(book, "week");
	}

}
