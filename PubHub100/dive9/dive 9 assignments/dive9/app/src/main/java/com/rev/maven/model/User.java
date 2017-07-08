package com.rev.maven.model;

public class User {
	private String name;
	private String emailid;
	private int mobileno;
	private String username;
	private String password;

	@Override
	public String toString() {
		StringBuilder buffer=new StringBuilder();
		buffer.append(name);
		buffer.append(emailid);
		buffer.append(mobileno);
		buffer.append(username);
		return buffer.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public int getMobileno() {
		return mobileno;
	}

	public void setMobileno(int mobileno) {
		this.mobileno = mobileno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
