package reva2prg;

import java.util.Scanner;

public class TestUserManager {

	public static void main(String[] args) {
		
		User user=new User();
		
		user.setMobilno(97656565);
		user.setEmailid("abcdef@gmail.com");
		user.setName("logesh");
		user.setPassword("admin");
		user.setUsername("admin");
		
		UserManager manager=new UserManager();
		
		manager.login(user);
		
		System.out.println("\n");
		System.out.println(user.toString());
		System.out.print("...........");
		System.out.print("want register as new user ? ........");
		System.out.println("( press 1 to register new )");
		
		UserManager manager2=new UserManager();
		
		manager2.addUser(user);
		
		
		
		
	}

}
