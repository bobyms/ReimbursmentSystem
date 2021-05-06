package com.reim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReimDBConnection {
	
	private final String URL = "jdbc:postgresql://rev-00.cv1imm2clwov.us-east-2.rds.amazonaws.com:5432/ersdb";
	private final String username = "ersuser";
	private final String password = "pAss";
	
	public Connection getDbConnection() throws SQLException{
	
		return DriverManager.getConnection(URL, username, password);
	}
}
