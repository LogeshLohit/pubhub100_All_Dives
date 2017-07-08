package reva2prg;

public class User {
	private String name;
	private String emailid;
	private int mobilno;
	private String username;
	private String password;
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
	public int getMobilno() {
		return mobilno;
	}
	public void setMobilno(int mobilno) {
		this.mobilno = mobilno;
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
	@Override
	public String toString() {
		StringBuffer buffer=new  StringBuffer("welcome !\t");
		buffer.append(name);
		buffer.append("\n");
		buffer.append("(" +emailid+ ")");
		return buffer.toString();
	}

}
