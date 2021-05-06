package com.reim.model;

public class ReimbursementStatus {

	private int S_ID;
	private String status;
	public ReimbursementStatus(int s_ID, String status) {
		S_ID = s_ID;
		this.status = status;
	}
	public ReimbursementStatus(String status) {
		this.status = status;
	}
	
	public int getS_ID() {
		return S_ID;
	}
	public void setS_ID(int s_ID) {
		S_ID = s_ID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ReimbursementStatus [S_ID=" + S_ID + ", status=" + status + "]";
	}
	
	
	
}
