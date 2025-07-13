package IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DAO.DBUtilDAO;
import POJO.Login;
import Utility.DBUtil;

public class DBUtilDAOImpl implements DBUtilDAO{
	Login L=new Login();

public Login showCurrentUser(Login L) {
		
		try {
		Connection con=DBUtil.getConnect(L);
		if(con!=null) {
		String sql="select current_user()";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		if (rs.next()) {
		    String USER = rs.getString(1);
		    System.out.println("Current User: " + USER);
			System.out.println("------------------------------");
		}
		}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("------------------------------");
		}
		return null;
	}

}
