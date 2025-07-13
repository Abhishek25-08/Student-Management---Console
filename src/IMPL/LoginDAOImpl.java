package IMPL;

import java.sql.Connection;
import java.util.Scanner;

import DAO.LoginDAO;
import POJO.Login;
import Utility.DBUtil;

public class LoginDAOImpl implements LoginDAO{
	Login L=new Login();

public Connection changeLogin(Login L, Scanner s) {
		
		Connection con=null;
		while(con==null) {
			try {
				System.out.println("Username:");
				L.setUser(s.nextLine());
				System.out.println("Password:");
				L.setPass(s.nextLine());
				System.out.println("------------------------------");
				con=DBUtil.getConnect(L);
				if(con!=null) {
					System.out.println("Logged in successfully.");
					System.out.println("------------------------------");
				}
				else {
					System.out.println("Failed to Login.\nUse correct username/password.");
					System.out.println("------------------------------");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("------------------------------");
			}
		}
		return con;
	}
	
	public Connection defaultLogin(Login L) {
		L.setUser("Prog");
		L.setPass("Prog");
		Connection con=DBUtil.getConnect(L);
		if(con!=null) {
			System.out.println("LoggedOut");
			System.out.println("------------------------------");
		}
		return con;
	}
	
}
