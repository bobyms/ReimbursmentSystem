package com.reim;

import java.util.ArrayList;

import com.reim.dao.*;
import com.reim.service.*;
import com.reim.model.*;

public class TestDrive {

	private static UserDao ud = new UserDao();
	private static ReimbursementDao rd = new ReimbursementDao();
	private static ErsService serv = new ErsService(rd,ud);
	
	static User u = serv.getById(1);
	static ArrayList<User> us = serv.getAllUsers();
	
	public static void test() {
		System.out.println(""+us);
	}
	
	public static void main(String[] args) {
		test();
	}
	
}
