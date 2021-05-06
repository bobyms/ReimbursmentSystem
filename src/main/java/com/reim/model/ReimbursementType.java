package com.reim.model;

public class ReimbursementType {
	
	private int T_ID;
	private String typ;
	public ReimbursementType(int t_ID, String typ) {
		T_ID = t_ID;
		this.typ = typ;
	}
	public ReimbursementType(String typ) {
		this.typ = typ;
	}
	public int getT_ID() {
		return T_ID;
	}
	public void setT_ID(int t_ID) {
		T_ID = t_ID;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	@Override
	public String toString() {
		return "ReimbursementType [T_ID=" + T_ID + ", typ=" + typ + "]";
	}
	
	

}
