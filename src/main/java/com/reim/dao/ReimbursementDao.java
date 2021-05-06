package com.reim.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.reim.model.Reimbursement;
import com.reim.model.User;

public class ReimbursementDao implements reimDao<Reimbursement> {

	private ReimDBConnection dbCon;
	public UserDao ud = new UserDao();
	public ReimbursementDao() {
		dbCon = new ReimDBConnection();
	}

	public final static Logger log = Logger.getLogger(ReimbursementDao.class);

	public ArrayList<Reimbursement> getAll() {
		ArrayList<Reimbursement> reims = new ArrayList<Reimbursement>();
		try (Connection con = dbCon.getDbConnection()) {

			String sql = "select * from Reimbursement";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				reims.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getTimestamp(4),
						rs.getTimestamp(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}

		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
		}
		return reims;
	}

	public Reimbursement getByID(int ID) {

		Reimbursement r = new Reimbursement();
		try (Connection con = dbCon.getDbConnection()) {
			String sql = "select * from Reimbursement where R_Id = " + ID;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			r = (new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5),
					rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
			e.printStackTrace();
		}
		return r;
	}

	public ArrayList<Reimbursement> getPending() {

		ArrayList<Reimbursement> r = new ArrayList<Reimbursement>();
		try (Connection con = dbCon.getDbConnection()) {
			String sql = "select * from Reimbursement where rstat_id = 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				r.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getTimestamp(4),
						rs.getTimestamp(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
			e.printStackTrace();
		}
		return r;
	}
	
	public void insert(Reimbursement entity) {
		try (Connection con = dbCon.getDbConnection()) {
			CallableStatement cs3 = con.prepareCall("{? = call insert_reim(?,?,?,?,?,?,?)}");
			
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			LocalDateTime localDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
			
			cs3.registerOutParameter(1, Types.VARCHAR);

			cs3.setDouble(2, entity.getAmount());
			cs3.setInt(3, entity.getAuthor());
			cs3.setTimestamp(4, new Timestamp(localDt.toInstant(ZoneOffset.UTC).toEpochMilli()));
			cs3.setString(5, entity.getDescription());
			cs3.setNull(6,Types.NULL);
			cs3.setInt(7, entity.getstatus());
			cs3.setInt(8, entity.getType());

			cs3.execute();

			System.out.println(cs3.getString(1));
			log.info("Rows inserted into Account table");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
			e.printStackTrace();
		}
	}

	public void delete(Reimbursement entity) {
		// TODO Auto-generated method stub

	}
	public void approveStatus(Reimbursement entity, User u) {
		try (Connection con = dbCon.getDbConnection()) {
			String sql = "update Reimbursement set rstat_id = " + 2 + " where R_ID= " + entity.getR_ID();
			String sql2 = "update Reimbursement set resolver = " + u.getU_ID()+ " where R_ID= " + entity.getR_ID();
			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps2 = con.prepareStatement(sql2);
			PreparedStatement ps3 = con.prepareStatement("update Reimbursement set resolved = ? where R_ID= ?");
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			LocalDateTime localDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
			ps3.setTimestamp(1, new Timestamp(localDt.toInstant(ZoneOffset.UTC).toEpochMilli()));
			ps3.setInt(2, entity.getR_ID());
			ps.execute();
			ps2.execute();
			ps3.execute();
			entity.setName(ud.getByID(u.getU_ID()).getfname());
			log.info("Reimbursement Status updated");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
			e.printStackTrace();
		}
		
	}
	public void denyStatus(Reimbursement entity, User u) {
		try (Connection con = dbCon.getDbConnection()) {
			String sql = "update Reimbursement set rstat_id = " + 3 + " where R_ID= " + entity.getR_ID();
			String sql2 = "update Reimbursement set resolver = " + u.getU_ID()+ " where R_ID= " + entity.getR_ID();
			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps2 = con.prepareStatement(sql2);
			PreparedStatement ps3 = con.prepareStatement("update Reimbursement set resolved = ? where R_ID= ?");
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			LocalDateTime localDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
			ps3.setTimestamp(1, new Timestamp(localDt.toInstant(ZoneOffset.UTC).toEpochMilli()));
			ps3.setInt(2, entity.getR_ID());
			ps.execute();
			ps2.execute();
			ps3.execute();
			log.info("Reimbursement Status updated");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
			e.printStackTrace();
		}
		
	}

}
