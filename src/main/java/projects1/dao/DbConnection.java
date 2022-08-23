package projects1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import projects1.exception.DbException;

public class DbConnection {
	private static String SCHEMA = "projects";
	private static String HOST = "localhost";
	private static String PASSWORD = "projects";
	private static int PORT = 3306;
	private static String USER = "projects";
	
	public static Connection getConnection()	{
		String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", HOST, PORT, SCHEMA, USER, PASSWORD);
		
		System.out.println("connecting with url=" + url);
		
		try {
			Connection conn = DriverManager.getConnection(url);
			System.out.println("succesfully obtained connection!" );
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DbException(e);
		}
		
	}
}
