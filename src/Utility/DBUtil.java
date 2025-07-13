package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import POJO.Login;

public class DBUtil {
	
	public static Connection getConnect(Login L) 
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/ssce";
			String user=L.getUser();
			String pass=L.getPass();
			con=DriverManager.getConnection(url,user,pass);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("....");
		}
		return con;
	}
	
}

//Reeta, Simrankaur
//Staff, ssce1411ssc
//Prog, Prog