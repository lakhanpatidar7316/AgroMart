package com.agromart.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUtil {
	private final static String URL="jdbc:mysql://localhost:3306/agromart";
	private final static String USERNAME="root";
	private final static  String PASSWORD="root@123";
	private final static String DRIVER="com.mysql.cj.jdbc.Driver";


	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		Connection con =DriverManager.getConnection(URL, USERNAME, PASSWORD);
		System.out.println("//\\Namastey Padharo Saa...");
		return con;
	}
	public void close(Connection con ,PreparedStatement pstmt , ResultSet rs) throws SQLException {
	if(rs!=null)
		rs.close();
	if(pstmt!=null)
		pstmt.close();
	if(con!=null)
		con.close();
	}
	public void close( ResultSet rs) throws SQLException {
	close(null, null, rs);
	}
	public void close(Connection con ,PreparedStatement pstmt) throws SQLException {
	close(con, pstmt, null);
	}
	public void close(PreparedStatement pstmt , ResultSet rs) throws SQLException {
	close(null, pstmt, rs);
	}
	public void close(PreparedStatement pstmt) throws SQLException {
	close(null, pstmt, null);
	}
	public void close(Connection con ) throws SQLException {
	close(con, null, null);
	}
	public void close(Connection con , ResultSet rs) throws SQLException {
	close(con, null, rs);
	}
	public static void main(String[] args) {
		//DBUtil dbutil =new DBUtil();
		try {
			getConnection();
			//dbutil.getConnection();
		}catch (ClassNotFoundException e) {
		e.printStackTrace();
		}catch (SQLException e) {
	          e.printStackTrace();
		}
	}


}
