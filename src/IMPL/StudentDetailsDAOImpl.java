package IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import DAO.StudentDetailsDAO;
import POJO.Login;
import POJO.StudentDetails;
import Utility.DBUtil;

public class StudentDetailsDAOImpl implements StudentDetailsDAO{

	public StudentDetails addStudentDetails(Login L, Scanner s, StudentDetails SD) {
		Connection con=DBUtil.getConnect(L);
		if(con!=null) {
			System.out.println("Enter Student Name:");
			SD.setSname(s.nextLine().toUpperCase());
			while(true) {						//Verify Only 10 integers
			System.out.println("Enter Student Phone Number:");
			String Sphone=s.nextLine().trim();
			if (Sphone.matches("\\d{10}")) {
				SD.setSphone(Sphone);
				break;
			}else {
				System.out.println("Phone Number Must be 10 Digits. Try Again!!!");
			}
			}
			System.out.println("Enter Student Address:");
			SD.setSaddress(s.nextLine().toUpperCase());
			System.out.println("------------------------------");
			String sql="insert into StudentDetails (Sname,Sphone,Saddress) values (?,?,?)";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, SD.getSname());
				ps.setString(2, SD.getSphone());
				ps.setString(3, SD.getSaddress());
				int i=ps.executeUpdate();
				if(i>0) {
					System.out.println("New Student Added Successfully.");
					System.out.println("------------------------------");
				}
			} catch(SQLException e) {
				System.out.println("Only Admin/Staff can Add/Update/Delete StudentDetails");
				System.out.println("------------------------------");
			}
		}else {
			System.out.println("Only Admin/Staff Can Insert New StudentDetails.");
		}
		return SD;
	}

	public StudentDetails updateStudentDetailsById(Login L, Scanner s, StudentDetails SD) {
		Connection con=DBUtil.getConnect(L);
		boolean breakLoop=false;
		do {
			if(con!=null)
			{
				SD=showStudentDetailsBySCid(L, SD);
				System.out.println("Are You Sure? Y/N");
				String confirm=s.nextLine().toUpperCase();
				System.out.println("------------------------------");
				if(confirm.equals("N")||confirm.equals("NO")) {
					breakLoop=true;
					continue;
				}
				System.out.println("Enter Student Name:\t\t\t(was:"+SD.getSname()+")");
				SD.setSname(s.nextLine().toUpperCase());
				while(true) {						//Verify Only 10 integers
				System.out.println("Enter Student Phone Number:\t\t(was:"+SD.getSphone()+")");
				String Sphone=s.nextLine().trim();
				if (Sphone.matches("\\d{10}")) {
					SD.setSphone(Sphone);
					break;
				}else {
					System.out.println("Phone Number Must be 10 Digits. Try Again!!!");
				}
				}
				System.out.println("Enter Student Address:\t\t\t(was:"+SD.getSaddress()+")");
				SD.setSaddress(s.nextLine().toUpperCase());
				System.out.println("------------------------------");
				
				String sql="update StudentDetails set Sname=?,Sphone=?,Saddress=? where Sid=?";
				try {
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setInt(4, SD.getSid());
					ps.setString(1, SD.getSname());
					ps.setString(2, SD.getSphone());
					ps.setString(3, SD.getSaddress());
					int i=ps.executeUpdate();
					if(i>0)
					{
						System.out.println("Cid="+SD.getSid()+" Cname="+SD.getSname()+" Sucessfully Updated");
						System.out.println("------------------------------");
						breakLoop=true;
					}
				} catch (SQLException e) {
					System.out.println("Only Admin/Staff can Add/Update/Delete StudentDetails");
					System.out.println("------------------------------");
				}
			}else {
				System.out.println("Only Admin/Staff Can Update StudentDetails.");
				breakLoop=true;
			}
		}while(breakLoop==false);
		return SD;
	}

	public StudentDetails deleteStudentDetailsById(Login L, Scanner s, StudentDetails SD) {
		Connection con=DBUtil.getConnect(L);
		boolean breakLoop=false;
		do {
			if(con!=null)
			{
				String sql="select * from StudentDetails where Sid=?";
				List <StudentDetails> l=new ArrayList<StudentDetails>();
				try {
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setInt(1, SD.getSid());
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						SD=new StudentDetails();
						SD.setSid(rs.getInt(1));
						SD.setSname(rs.getString(2));
						SD.setSphone(rs.getString(3));
						SD.setSaddress(rs.getString(4));
						SD.setStotalDuration(rs.getString(5));
						SD.setSdurationLeft(rs.getString(6));
						SD.setStotalFee(rs.getString(7));
						SD.setSbalanceFee(rs.getString(8));
						l.add(SD);
					}
					if (l.isEmpty()) {
					    System.out.println("No StudentDetails found with Sid = " + SD.getSid());
						System.out.println("------------------------------");
					    breakLoop=true;
					    break;
					} else {
					    for (StudentDetails sd : l) {
					        System.out.println(sd); // or custom format
							System.out.println("------------------------------");
					    }
					}
				}catch(Exception E) {
					E.printStackTrace();
				}
				System.out.println("Are You Sure? Y/N");
				String confirm=s.nextLine().toUpperCase();
				System.out.println("------------------------------");
				if(confirm.equals("N")||confirm.equals("NO")) {
					breakLoop=true;
					continue;
				}
				sql="delete from StudentDetails where Sid=?";
				try {
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setInt(1, SD.getSid());
					int i=ps.executeUpdate();
					if(i>0)
					{
						System.out.println("Cid="+SD.getSid()+" Cname="+SD.getSname()+" Sucessfully Deleted");
						System.out.println("------------------------------");
						breakLoop=true;
						break;
					}
				}catch(SQLIntegrityConstraintViolationException e) {
					System.out.println(SD.getSname()+" is currently ACTIVE student.....\nContact DBAdmin");
					System.out.println("------------------------------");
					break;
				}catch (SQLException e) {
					System.out.println("Only Admin/Staff can Add/Update/Delete StudentDetails");
					System.out.println("------------------------------");
					break;
				}
			}else {
				System.out.println("Only Admin/Staff Can Update StudentDetails.");
				breakLoop=true;
			}
		}while(breakLoop==false);
		return SD;
	}

	public StudentDetails showAllStudentDetails(Login L, StudentDetails SD) {
		Connection con=DBUtil.getConnect(L);
		if(con!=null) {
			String sql="select * from StudentDetails";
			List <StudentDetails> l=new ArrayList<StudentDetails>();
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					SD=new StudentDetails();
					SD.setSid(rs.getInt(1));
					SD.setSname(rs.getString(2));
					SD.setSphone(rs.getString(3));
					SD.setSaddress(rs.getString(4));
					SD.setStotalDuration(rs.getString(5));
					SD.setSdurationLeft(rs.getString(6));
					SD.setStotalFee(rs.getString(7));
					SD.setSbalanceFee(rs.getString(8));
					l.add(SD);
				}
				if(l!=null) {
					Iterator <StudentDetails> it=l.iterator();
					while(it.hasNext()) {
						System.out.println(it.next());
						System.out.println("........................................................................................................................");
					}
					System.out.println("------------------------------");
				}
			}catch(SQLException E) {
				System.out.println("Couldn't Fetch from StudentDetails.");
			}
		}
		return SD;
	}

	@Override
	public StudentDetails showStudentDetailsBySCid(Login L, StudentDetails SD) {
		Connection con=DBUtil.getConnect(L);
		String sql="select * from StudentDetails where Sid=?";
		List <StudentDetails> l=new ArrayList<StudentDetails>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, SD.getSid());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SD=new StudentDetails();
				SD.setSid(rs.getInt(1));
				SD.setSname(rs.getString(2));
				SD.setSphone(rs.getString(3));
				SD.setSaddress(rs.getString(4));
				SD.setStotalDuration(rs.getString(5));
				SD.setSdurationLeft(rs.getString(6));
				SD.setStotalFee(rs.getString(7));
				SD.setSbalanceFee(rs.getString(8));
				l.add(SD);
			}
			if(l!=null) {
				Iterator <StudentDetails> it=l.iterator();
				while(it.hasNext()) {
					System.out.println(it.next());
					System.out.println("........................................................................................................................");
				}
				System.out.println("------------------------------");
			}
		}catch(Exception E) {
			System.out.println("Couldn't Fetch from Courses.");
		}
		return SD;
	}

}
