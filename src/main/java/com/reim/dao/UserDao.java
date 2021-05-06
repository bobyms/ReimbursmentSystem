package com.reim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.reim.model.Reimbursement;
import com.reim.model.User;

public class UserDao implements reimDao<User>{
	
private ReimDBConnection dbCon;
	
	public UserDao() {
		dbCon = new ReimDBConnection();
	}
	
	public final static Logger log = Logger.getLogger(UserDao.class);
	

	@Override
	public ArrayList<User> getAll() {
		ArrayList<User> users = new ArrayList<User>();
		try(Connection con = dbCon.getDbConnection()){
			
			String sql = "select * from Users";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				users.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
			}
		
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			System.out.println("Caught 2nd error");
		}
		return users;
	}

	@Override
	public User getByID(int ID) {
		User u = new User();
		try(Connection con = dbCon.getDbConnection()){
			String sql = "select * from Users where U_Id = "+ ID;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			u = new User(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
			e.printStackTrace();
		}
		return u;
	}

	public void insert(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Reimbursement> getAllReims(User u){
		ArrayList<Reimbursement> reims = new ArrayList<Reimbursement>();
		try(Connection con = dbCon.getDbConnection()){
			
			String sql = "select * from Reimbursement where author ="+u.getU_ID();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				reims.add(new Reimbursement(rs.getInt(1),rs.getDouble(2), rs.getInt(3),rs.getTimestamp(4), rs.getTimestamp(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}

		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
		}
		return reims;
		
	}

}
