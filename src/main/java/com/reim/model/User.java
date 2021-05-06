package com.reim.model;

public class User {

	private int U_ID;
	private String username;
	private String pass;
	private String fname;
	private String lname;
	private String email;
	private int role_id;
	

	public User(int U_ID, String username,String pass, String fname, String lname, String email,
			int role_id) {
		super();
		this.U_ID = U_ID;
		this.username = username;
		this.pass = pass;
		this.fname= fname;
		this.lname = lname;
		this.email = email;
		this.role_id = role_id;
	}

	public User() {}

	public int getU_ID() {
		return U_ID;
	}

	public void setUser_id(int user_id) {
		this.U_ID = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getfname() {
		return fname;
	}

	public void setfname(String fname) {
		this.fname = fname;
	}

	public String getPass() {
		return pass;
	}

	public void setpass(String pass) {
		this.pass = pass;
	}
	public String getlname() {
		return lname;
	}

	public void setlname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "User [user_id=" + U_ID + ", username=" + username + ", first_name="
				+ fname + ", last_name=" + lname + ", email=" + email + ", role_id=" + role_id + "]";
	}


}
