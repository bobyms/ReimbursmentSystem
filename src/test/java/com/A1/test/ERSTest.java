package com.A1.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.reim.model.*;
import com.reim.dao.*;
import com.reim.service.*;

public class ERSTest {
	 @Mock
	 private UserDao fakeUDao;
	 private ReimbursementDao fakeRDao;
	 private ErsService es;
	 private Reimbursement reim;
	 private User u;
	 //private User man;
	 
	 @BeforeEach
	 public void setUp() {
		 MockitoAnnotations.initMocks(this);
		 es = new ErsService(fakeRDao, fakeUDao);
		 reim = new Reimbursement(5,100,5,new Timestamp(System.currentTimeMillis()),null,"hotel",6,1,1);
		 u = new User(5,"user5","pass","Patrick","Star","bikini@bottom.com",1);
		 //man =new User(6,"man5","pass","Squidward","Tentacles","ten@tickles.com",2);
		 when(fakeUDao.getAllReims(u)).thenReturn(new ArrayList<Reimbursement>(Arrays.asList(reim)));
		 //doNothing().when(es).approve(reim,man);
		// doNothing().when(es).approve(reim,man);
	 }
	 @Test 
	 public void getUserByID() {
		 assertThrows(NullPointerException.class,()-> es.getById(90));
	 }
	 
	 @Test 
	 public void getReimByID() {
		 assertThrows(NullPointerException.class,()->es.getRById(50).getR_ID());
	 }
	 @Test 
	 public void getAuthorOfReim() {
		 assertEquals(5, es.getUsersReim(u).get(0).getAuthor());
	 }
	 @Test 
	 public void getAmountFromReim() {
		 assertEquals(100, es.getUsersReim(u).get(0).getAmount());
	 }
	 @Test 
	 public void getUnresolved() {
		 assertEquals(null, es.getUsersReim(u).get(0).getResolved());
	 }
	 
	 

}
