package com.reim.model;

import java.sql.Timestamp;
//import java.time.LocalDate;

public class Reimbursement {

	private int R_ID;
	private double amount;
	private int author;
	private Timestamp submit;
	private Timestamp resolved;
	private String description;
	private int resolver;
	private int type;
	private int status;
	private String stat;
	private String typ;
	private int num;
	//private LocalDate date;
	private String name;
	

	public Reimbursement() {
		
	}
	
	public Reimbursement(int r_ID, double amount,int author, Timestamp submit, Timestamp resolved, String description, int resolver, int status,int type) {
		this.R_ID = r_ID;
		this.author = author;
		this.amount = amount;
		this.submit = submit;
		this.resolved = resolved;
		this.description = description;
		this.resolver = resolver;
		this.type = type;
		this.status = status;
		this.setTyp(type);
		this.setStat(status);
		num = r_ID;
	}
	public Reimbursement(double amount,int author, Timestamp submit, Timestamp resolved, String description, int resolver,int type, int status) {
		this.author = author;
		this.amount = amount;
		this.submit = submit;
		this.resolved = resolved;
		this.description = description;
		this.resolver = resolver;
		this.type = type;
		this.status = status;
		this.setTyp(type);
		this.setStat(status);
	}
	
	public Reimbursement(int r_ID, double amount,int author, Timestamp submit, Timestamp resolved, String description) {
		this.R_ID = r_ID;
		this.author = author;
		this.amount = amount;
		this.submit = submit;
		this.resolved = resolved;
		this.description = description;
		num = r_ID;
	}
	
	public Reimbursement(int amount,int author, String description) {
		this.author = author;
		this.amount = amount;
		this.submit = new Timestamp(System.currentTimeMillis());
		resolved = null;
		this.description = description;
	}
	
	public String getStat() {
		return stat;
	}

	public void setStat(int status) {
		if(status == 1) stat="Pending";
		else if (status == 2)stat="Approved";
		else if (status == 3) stat="Denied";
	}

	public void setName(String name) {
		this.name=name;
	}
	public String getTyp() {
		return typ;
	}

	public void setTyp(int type) {
		if(type ==1) typ = "Lodging";
		else if (type ==2) typ = "Travel";
		else if (type ==3) typ = "Food";
		else typ ="Other";
	}
	
	public int getResolver() {
		return resolver;
	}
	public int getNum() {
		return num;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	public int getR_ID() {
		return R_ID;
	}
	public void setR_ID(int r_ID) {
		R_ID = r_ID;
	}
	public int getType() {
		return type;
	}
	public void settype(int type) {
		this.type = type;
	}
	public int getstatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getSubmit() {
		return submit;
	}
	public void setSubmit(Timestamp submit) {
		this.submit = submit;
	}
	public Timestamp getResolved() {
		return resolved;
	}
	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Reimbursement [R_ID=" + R_ID + ", amount=" + amount + ", author=" + author + ", submit=" + submit
				+ ", resolved=" + resolved + ", description=" + description + "]";
	}
	

	
}
