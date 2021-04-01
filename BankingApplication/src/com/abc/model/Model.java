package com.abc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Model {
	
	private String name;
	private String custid;
	private int accno;
	private String pwd;
	private int bal;
	private String email;
	private int raccno;
	
    private Connection con;
    private PreparedStatement pstmt;
	private ResultSet res;
	public ArrayList al = new ArrayList();
	public ArrayList sal = new ArrayList();
	public ArrayList ral = new ArrayList();
     
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getBal() {
		return bal;
	}
	public void setBal(int bal) {
		this.bal = bal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRaccno() {
		return raccno;
	}
	public void setRaccno(int raccno) {
		this.raccno = raccno;
	}
	
	public Model() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");//Loading the Driver
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankApplication", "root", "password");
	
	System.out.println("Loading the driver and establishing the connection is completed");
	
		
	}
	public boolean Register() throws SQLException {
		String s = "insert into ABCBank values(?,?,?,?,?,?)";//Incomplete Query
		pstmt  = con.prepareStatement(s);
		pstmt.setString(1, name);
		pstmt.setString(2, custid);
		pstmt.setInt(3, accno);
		pstmt.setString(4, pwd);
		pstmt.setInt(5, bal);
		pstmt.setString(6, email);
		
		int x = pstmt.executeUpdate();
		
		if(x>0) {
			return true;
		}
		
		return false;
	}
	public int login() throws SQLException {
		String s = "select * from ABCBank where custid=? and password=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, custid);
		pstmt.setString(2, pwd);
		
		ResultSet res = pstmt.executeQuery();
		
		while(res.next()==true) {
			int accno = res.getInt("ACCNO");
			return accno;
		}
			
		return 0;
	}
	public boolean checkBalance() throws SQLException {
		String s = "select balance from abcbank where accno=?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, accno);
		ResultSet res = pstmt.executeQuery();
		
		while(res.next()==true) {
			bal = res.getInt("BALANCE");
			return true;
		}
		return false;
	}
	public boolean ChangePwd() throws SQLException {
		String s = "update ABCBank set password=? where accno=?";
		pstmt = con.prepareStatement(s);
		
		pstmt.setString(1, pwd);
		pstmt.setInt(2, accno);
		int x = pstmt.executeUpdate();
		
		if(x>0) {
			return true; 
		}
		
		return false;
	}
	public boolean transfer() throws SQLException {
		String s1 = "select * from ABCBank where accno = ?";
		pstmt = con.prepareStatement(s1);
	    pstmt.setInt(1, raccno);
	    
	    res = pstmt.executeQuery();
//	    if(res.next()==true) {
//	    	System.out.println("no data");
//	    }
//	    else {
//	    	System.out.println("data collected");
//	    }
    	System.out.println("before while");

	    while(res.next()==true) {
	    	System.out.println("entered while");
	    	System.out.println("Inside while - 1");
	    	String s2 = "update ABCBank set balance=balance-? where accno = ?";
	    	pstmt = con.prepareStatement(s2);
	    	pstmt.setInt(1, bal);
	    	pstmt.setInt(2, accno);
	    	pstmt.executeQuery();
	    	int y1 = pstmt.executeUpdate();
	    	while(res.next()==true) {
	    		System.out.println("Inside while - 2");
	    		int x = res.getInt("BALANCE");
	    		if(x>0) {
	    			String s3 = "update ABCBank set balance=balance=+? where accno = ?";
	    			pstmt = con.prepareStatement(s2);
	    			pstmt.setInt(1, bal);
	    			pstmt.setInt(2, raccno);
	    			int y2 = pstmt.executeUpdate();
	    			if(y2>0) {
	    				System.out.println("Inside if - 1");
	    				String s4 = "insert into GetStatement values(?,?,?)";
	    				pstmt = con.prepareStatement(s4);
	    				pstmt.setInt(1, accno);
	    				pstmt.setInt(2, raccno);
	    				pstmt.setInt(3, bal);
	    				int y = pstmt.executeUpdate();
	    				
	    				if(y>0) {
	    					return true;
	    				}
	    				else {
	    					return false;
	    				}
	    				
	    			
	    				}
	    		}
	    		else {
	    			
	    			return false;
	    		}
	    	}
	    	
	    }
	    System.out.println("after while. it did not enter while.");		
		
		return false;
	}
	public ArrayList getStatement() throws SQLException {
		String s = "select 8 from getStatement where accno=?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, accno);
		res = pstmt.executeQuery();
		
		while(res.next()==true) {
			sal.add(res.getInt("ACCNO"));
			ral.add(res.getInt("RACCNO"));
	       al.add(res.getInt("BALANCE"));
	      
		}
		
		return al;
	}
	public boolean ApplyLoan() throws SQLException {
		String s = "select * from ABCBank where accno=?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, accno);
		res = pstmt.executeQuery();
		
		while(res.next()==true) {
		name = res.getString("NAME");
		email = res.getString("EMAIL");
		return true;
		}
		return false;
	}
	
}


 

