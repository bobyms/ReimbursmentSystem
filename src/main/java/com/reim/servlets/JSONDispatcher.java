package com.reim.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reim.controller.ErsController;
import com.reim.model.User;

public class JSONDispatcher {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		switch(req.getRequestURI()) {
		
		 case "/Ass1/getsessionuser.json":
			 ErsController.getSessionUser(req, res);
			 break;
		 case "/Ass1/getsessionreims.json":
			 ErsController.reimLoad(req, res);
			 break;
		 case "/Ass1/getPending.json":
			 ErsController.getPending(req, res);
			 break;
		 case "/Ass1/getById.json":
			 ErsController.getByID(req, res);
			 break;
		 /*case "/Ass1/Approve.json":
			 ErsController.approveReim(req, res);
			 break;
		 case "/Ass1/Deny.json":
			 ErsController.denyReim(req, res);
			 break;*/
			 
		default:
			res.getWriter().write(new ObjectMapper().writeValueAsString(new User()));
		
		}
	}


}
