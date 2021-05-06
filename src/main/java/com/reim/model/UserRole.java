package com.reim.model;

public class UserRole {

	private int Role_ID;
	private String roles;
	public UserRole(int role_ID, String roles) {
		Role_ID = role_ID;
		this.roles = roles;
	}
	public int getRole_ID() {
		return Role_ID;
	}
	public void setRole_ID(int role_ID) {
		Role_ID = role_ID;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserRole [Role_ID=" + Role_ID + ", roles=" + roles + "]";
	}
	
	
	
}
