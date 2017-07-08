package com.rev.maven.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager {
	HashMap<String,String> userdetailmap = new HashMap<String,String>();
	ArrayList<String> blacklist = new ArrayList<String>(){{ 
		add("mario");
		add("luigi");
	}};
	
	
	public boolean login(User user) throws Exception {
		boolean flag = false;
		
		if(blacklist.contains(user.getUsername()))
			throw new Exception("User Account is blacklisted, please send a mail to him");
		
		//checks if user name is present and for the given user name the password is same or not
		else if(userdetailmap.containsKey(user.getUsername())&&(userdetailmap.get(user.getUsername().toString()).equals(user.getPassword())))
			flag = true;

		return flag;

	}
	
	
	public void addUser(User user)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("To add a new user please enter your name,phone,email,username,pass");
		String name=scan.next();
		int mobileno=scan.nextInt();
		String emailid=scan.next();
		String username=scan.next();
		String password=scan.next();
		
		
		 user.setName(name);
		 user.setMobileno(mobileno);
		 user.setEmailid(emailid);
		 user.setUsername(username);
		 user.setPassword(password);
		 
		 userdetailmap.put(username,password);
		 
		 System.out.println(user.toString());
	}
}