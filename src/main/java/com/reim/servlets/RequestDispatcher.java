package com.reim.servlets;

import javax.servlet.http.HttpServletRequest;

import com.reim.controller.ErsController;

public class RequestDispatcher {
	
public static String process(HttpServletRequest req) {
		
		switch(req.getRequestURI()) {
			case "/Ass1/login.change":
				System.out.println("in Login.change dispatcher");
				return ErsController.login(req);
			case "/Ass1/loginMan.change":
				System.out.println("in LoginMan.change dispatcher");
				return ErsController.login(req);
			case "/Ass1/logout.change":
				System.out.println("in Logout.change dispatcher");
				return ErsController.logout(req);
			case "/Ass1/reimreq.change":
				 return ErsController.newReim(req);
			case "/Ass1/getsessionreims.change":
				 return ErsController.reimLoad(req);
			case "/Ass1/Approve.change":
				 return ErsController.approveReim(req);
			 case "/Ass1/Deny.change":
				 return ErsController.denyReim(req);
			default:
					System.out.println("in default");
					return "html/FailedLogin.html";
		}
		
	}


}
