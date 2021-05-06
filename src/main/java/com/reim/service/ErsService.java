package com.reim.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.reim.dao.ReimbursementDao;
import com.reim.dao.UserDao;
import com.reim.model.Reimbursement;
import com.reim.model.User;

public class ErsService {
	
	private ReimbursementDao RDao;
	private UserDao UDao;
	
	public ErsService(ReimbursementDao r, UserDao u) {
		RDao = r;
		UDao = u;
	}
	
	public User login(String uname, String pass) {
		ArrayList<User> users = new ArrayList<User>();
		users = UDao.getAll();
		for(User u: users) {
			if(u.getUsername().equals(uname) && u.getPass().equals(pass)) {
				return u;
			}
		}
		return null;
	}
	
	public ArrayList<Reimbursement> getUsersReim(User user){
		ArrayList<Reimbursement> reims = new ArrayList<Reimbursement>();
		reims = UDao.getAllReims(user);
		return reims;
	}
	
	public ArrayList<Reimbursement> getAllReims(){
		ArrayList<Reimbursement> reims = new ArrayList<Reimbursement>();
		reims = RDao.getAll();
		return reims;
	}
	
	public User getById(int id) {
		User u = UDao.getByID(id);
		if(u.getfname()==null) throw new NullPointerException();
		return u;
	}
	public Reimbursement getRById(int id) {
		Reimbursement r = RDao.getByID(id);
		if(r.getDescription()==null) throw new NullPointerException();
		return r;
	}
	public ArrayList<Reimbursement> getPending() {
		ArrayList<Reimbursement> r = RDao.getPending();
		return r;
	}
	
	public ArrayList<User> getAllUsers() {
		ArrayList<User> u = UDao.getAll();
		return u;
	}
	public void insertReim(User u, double amount, String desc, int type) {
		System.out.println("Type: "+type);
		RDao.insert(new Reimbursement(amount, u.getU_ID(), new Timestamp(System.currentTimeMillis()),null,desc,0,type,1));
	}
	public String approve(Reimbursement r, User u) {
		RDao.approveStatus(r, u);
		System.out.println("APPROVED!");
		return"approved";
	}
	public String deny(Reimbursement r, User u) {
		RDao.denyStatus(r, u);
		System.out.println("DENIED!");
		return"denied";
	}
}
