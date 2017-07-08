package com.rev.maven.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rev.maven.model.Book;
import com.rev.maven.model.User;
import com.rev.maven.util.ConnectionUtil;

public class BookDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void createBook(Book book){
		String sql = "call AUTHOR_CREATE_BOOK(?, ?, ? ,? ,? ,? ,?)";
		//parameters for ? => (ISBN13, TITLE ,AUTHOR , PUBLISH_DATE, CONTENT, PRICE, STATUS)
		Object[] params = {book.getIsbn13(), book.getTitle(), book.getAuthor(), book.getPublishDate(), book.getContent(), book.getPrice(), book.getStatus()};
		int rows = jdbcTemplate.update(sql, params);
		
		System.out.println("No of books inserted is:" + rows);
	}
	
	public void uploadContents(Book book, String contents){
		String sql = "call AUTHOR_UPLOAD_CONTENTS(?, ?, ?)";
		//parameters for ? => (AUTHOR ,ISBN13, NEW_CONTENT)
		Object[] params = {book.getAuthor(), book.getIsbn13(), contents};
		int rows = jdbcTemplate.update(sql, params);
		
		if (rows == 1) {
			System.out.println("Content changed");
		}
		else {
			System.out.println("No content is changed");
		}	
	}
	
	public void viewBookAndContent(String author){
		String sql = "call AUTHOR_VIEW_BOOK_CONTENTS(?)";
		//parameters for ? => (AUTHOR)
		Object[] params = {author};
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		
		for (Map row : rows){
			System.out.print("CONTENT: " + row.get("CONTENT"));
			System.out.println(" TITLE: " + row.get("TITLE"));
		}
	}
	
	public void deleteBook(Book book){
		String sql = "call AUTHOR_DELETE_BOOK(?, ?)";
		//parameters for ? => (AUTHOR, ISBN13)
		Object[] params = {book.getAuthor(), book.getIsbn13()};
		int rows = jdbcTemplate.update(sql, params);
		
		System.out.println("No of books deleted: " + rows);
	}
	
	public void viewAllBookOfAuthor(String author){
		String sql = "call AUTHOR_VIEW_BOOKS(?)";
		//parameters for ? => (AUTHOR)
		Object[] params = {author};
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		
		for (Map row : rows) {
			System.out.println("TITLE: " + row.get("TITLE"));
		}
	}
	
	public void viewBooks(){
		String sql = "call USER_VIEW_BOOKS()";
		//no parameters
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		for (Map row : rows){
			System.out.println(row.get("TITLE"));
		}
	}
	
	public boolean placeOrder(User user, Book book, int quantity ){
		String sql = "select USER_PLACE_ORDER(?, ?, ?, ?, ?) as USER_PLACE_ORDER";
		//parameters for ? => (EMAIL_ID, BOOK_ID, QTY, TOTAL_AMOUNT, STATUS)
		Object[] params = {user.getEmailid(), book.getIsbn13(), quantity, (book.getPrice() * quantity), "BOOKED"};
		int rows = jdbcTemplate.queryForObject(sql, params, Integer.class);
		
		if (rows == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void bookQuantity(Book book){
		String sql = "call USER_BOOK_QUANTITY(?)";
		//parameters for ? => (BOOK_ID)
		Object[] params = {book.getIsbn13()};
		int rows = jdbcTemplate.queryForObject(sql, params, Integer.class);
		
		System.out.println("No of books in stock are: " + rows);
	}
	
	public void deleteOrder(User user, Book book){
		String sql = "call USER_DELETE_ORDER(?, ?)";
		//parameters for ? => (EMAIL_ID, BOOK_ID)
		Object[] params = {user.getEmailid(), book.getIsbn13()};
		int rows = jdbcTemplate.update(sql, params);
		
		System.out.println("No of order removed: " + rows);
	}
	
	public void listAllBooks(){
		String sql = "call SEARCH_ALL_BOOK()";
		//no parameters 
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		for (Map row : rows){
			System.out.println(row.get("TITLE"));
		}
	}
	
	public void searchByBookName(String bookName){
		String sql = "call SEARCH_BY_TITLE(?)";
		//parameters for ? => (TITLE)
		Object[] params = {bookName};
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		
		for (Map row : rows){
			System.out.println(row.get("TITLE"));
		}
	}
	
	public void searchByAuthorName(String authorName){
		String sql = "call SEARCH_BY_AUTHOR(?)";
		//parameters for ? => (AUTHOR)
		Object[] params = {authorName};
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		
		for (Map row : rows){
			System.out.println(row.get("TITLE"));
		}
	}
	
	public void searchByPrice(float highPrice, float lowPrice){
		String sql = "call SEARCH_BY_PRICE(?, ?)";
		//parameters for ? => (PRICE, PRICE)
		Object[] params = {highPrice, lowPrice};
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		
		for (Map row : rows){
			System.out.println(row.get("TITLE"));
		}
	}
	
	public void searchByISBN13(String isbn13){
		String sql = "call SEARCH_BY_ISBN(?)";
		//parameters for ? => (AUTHOR)
		Object[] params = {isbn13};
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		
		for (Map row : rows){
			System.out.println(row.get("TITLE"));
		}
	}
	
	public void changeBookQuantity(Book book, int quantity){
		String sql = "call ALTER_BOOK_QUANTITY(?, ?)";
		//parameters for ? => (BOOK_ID, QTY)
		Object[] params = {book.getIsbn13(), quantity};
		int rows = jdbcTemplate.update(sql, params);
		
		System.out.println("Quantity of " + rows + " books modified to " + quantity);
	}
	
	public void booksSold(Book book, String filter){
		String sql = "call AUTHOR_BOOKS_SOLD(?, ?)";
		//parameters for ? => (BOOK_ID, FILTER); FILTER can have 'week', 'month', 'yearly'
		Object[] params = {book.getIsbn13(), filter};
		int rows = jdbcTemplate.queryForObject(sql, params, Integer.class);
		
		System.out.println("No of books in sold are: " + rows);
	}
}
