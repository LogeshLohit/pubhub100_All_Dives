package reva2prg;

import java.util.Scanner;

public class UserManager {

	
boolean login(User user)
{
	String temp1=user.getUsername();
	String temp2=user.getPassword();
	
	if(temp1.equals("admin") && (temp2.equals("admin")))
	{
		System.out.println("it's true ..you logged in successfully");
		return true;
	}
	System.out.println("oh ! its false ! try again");
	return false;
}


public void addUser(User user)
{
	Scanner scanner=new Scanner(System.in);
	int a=scanner.nextInt();
	if(a==1)
	{
		System.out.println("Enter name:");
		String n=scanner.next();
		System.out.println("Enter emailid:");
		String mailid=scanner.next();
		System.out.println("Enter username:");
		String name=scanner.next();
		System.out.println("Enter password:");
		String pass=scanner.next();
		System.out.println("Enter mobile number:");
		int no=scanner.nextInt();
		System.out.println("\n");
		System.out.println(
				"Name:\t" + n +"Email id:\t"+ mailid +
				"Mobile no:\t"+ no +"\n you are added as our new user !"
				
				
				);
		
		
	
	}
	
}




}
