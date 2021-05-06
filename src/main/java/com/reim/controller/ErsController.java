package com.reim.controller;

import java.io.IOException;
import java.util.ArrayList;

//import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reim.dao.ReimbursementDao;
import com.reim.dao.UserDao;
import com.reim.model.Reimbursement;
import com.reim.model.User;
import com.reim.service.ErsService;

public class ErsController {
	
	static ErsService reimServ =  new ErsService(new ReimbursementDao(),new UserDao());
	
	public static String login(HttpServletRequest req) {
		System.out.println("in employee controller login");
		if(!req.getMethod().equals("POST")) {
			return "/Ass1/html/index.html";
		}
		User u = reimServ.login(req.getParameter("uname"), req.getParameter("pass"));
		if(u == null) {
			return "wrongcreds.change";
		}else if(u.getRole_id()==2){
			ArrayList<Reimbursement> reims = reimServ.getAllReims();
			req.getSession().setAttribute("currentUser", u);
			req.getSession().setAttribute("reimbursements", reims);
			return "/html/ManLogin.html";
			
		}else {
			//make a getRole function so can return manager or employee page
			ArrayList<Reimbursement> reims = reimServ.getUsersReim(u);
			req.getSession().setAttribute("currentUser", u);
			req.getSession().setAttribute("reimbursements", reims);
			return "/html/EmpLogin.html";
		}
	}
	
	public static String logout(HttpServletRequest req) {
		// invalidate user session, redirect to login page
		req.getSession().invalidate();
		System.out.println("User successfully logged out.");
		return "/html/index.html";
	}
	
	public static void getSessionUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		User u = (User)req.getSession().getAttribute("currentUser");
		res.getWriter().write(new ObjectMapper().writeValueAsString(u));
	}
	
	//Loads current session reims and writes to table
	public static void reimLoad(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		User u = (User)req.getSession().getAttribute("currentUser");
		ArrayList<Reimbursement> reims;
		if (u.getRole_id() == 2)reims = reimServ.getAllReims();
		else reims = reimServ.getUsersReim(u);
		System.out.println("Got reims");
		//for(Reimbursement r : reims) {System.out.println("Stat: "+r.getstatus()); System.out.println("SUBMIT HERE!!!! "+r.getSubmit());}
		res.getWriter().write(new ObjectMapper().writeValueAsString(reims));
	}
	public static String reimLoad(HttpServletRequest req) {
		User u = (User)req.getSession().getAttribute("currentUser");
		ArrayList<Reimbursement> reims;
		if (u.getRole_id() == 2)reims = reimServ.getAllReims();
		else reims = reimServ.getUsersReim(u);
		System.out.println("Got reims");
		//for(Reimbursement r : reims) {System.out.println("Stat: "+r.getstatus()); System.out.println("SUBMIT HERE!!!! "+r.getSubmit());}
		return "/html/ManLogin.html";
	}
	//inserts new reim req
	public static String newReim(HttpServletRequest req){
		int type =Integer.parseInt(req.getParameter("type"));
		double amount = Double.parseDouble(req.getParameter("amount"));
		String des =req.getParameter("desc");
		reimServ.insertReim((User)req.getSession().getAttribute("currentUser"), amount, des, type);
		return "/html/EmpLogin.html";
	}
	public static void getPending(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		ArrayList<Reimbursement> reims=reimServ.getPending();
		System.out.println("Got Pending reims");
		res.getWriter().write(new ObjectMapper().writeValueAsString(reims));
	}

	public static void getByID(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
	}
	//Approve reims
	public static String approveReim(HttpServletRequest req) {
		User u = (User)req.getSession().getAttribute("currentUser");
		int i = Integer.parseInt(req.getParameter("id"));
		Reimbursement r = reimServ.getRById(i);
		if(r!=null) {
			if(r.getResolver()==0)reimServ.approve(r,u);
			else {
				req.setAttribute("error", "Reimbursement decision already decided");
				System.out.println("Reimbursement decision already decided");
			}
		}
		else System.out.println("User does not exist");
		return "/html/ManLogin.html";
	}
	//deny reims
	public static String denyReim(HttpServletRequest req) {
		User u = (User)req.getSession().getAttribute("currentUser");
		int i = Integer.parseInt(req.getParameter("id"));
		Reimbursement r = reimServ.getRById(i);
		if(r!=null) {
			if(r.getResolver()==0)reimServ.deny(r,u);
			else {
				req.setAttribute("error", "Reimbursement decision already decided");
				System.out.println("Reimbursement decision already decided");
			}
		}
		else System.out.println("User does not exist");
		return "/html/ManLogin.html";
	}
	


}
