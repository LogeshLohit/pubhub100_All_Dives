package com.rev.maven.model;

public class TestUserManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
     UserManager usermanager = new UserManager();
     usermanager.addUser(user);
     try {
		System.out.println(usermanager.login(user));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}