package IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import DAO.CoursesDAO;
import POJO.Courses;
import POJO.Login;
import Utility.DBUtil;

public class CoursesDAOImpl implements CoursesDAO{
	
	Courses C=new Courses();
	DBUtilDAOImpl DBDI=new DBUtilDAOImpl();
	
	public Courses addCourse(Login L, Scanner s, Courses C) {
		Connection con=DBUtil.getConnect(L);
		if(con!=null) {
			System.out.println("Enter Course Name:");
			C.setCname(s.nextLine().replaceAll("\\s+", "").toUpperCase());
			System.out.println("Enter Course Company:\tMKCL/GCC-TBC/SSC");
			C.setCcompany(s.nextLine().toUpperCase());
			System.out.println("Enter Course Certification:\tGov/Priv");
			C.setCcertification(s.nextLine().toUpperCase());
			System.out.println("Enter Course Type:\tComp/Prog/Typ/EngSpk");
			C.setCtype(s.nextLine().toUpperCase());
			while(true) {						//Verify Only Integer Values
			System.out.println("Enter Course Duration:");
			try {
			C.setCduration(s.nextInt());
			s.nextLine();
			break;
			}catch(InputMismatchException e) {
				System.out.println("Enter numeric Value for Duration");
				s.next();
			}
			}
			while(true) {						//Verify Only Integer Values
			System.out.println("Enter Course Fee:");
			try {
			C.setCfee(s.nextInt());
			s.nextLine();
			break;
			}catch(InputMismatchException e) {
				System.out.println("Enter numeric Value for Fee");
				s.next();
			}
			}
			System.out.println("------------------------------");
			String sql="insert into Courses (Cname,Ccompany,Ccertification,Ctype,Cduration,Cfee) values (?,?,?,?,?,?)";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, C.getCname());
				ps.setString(2, C.getCcompany());
				ps.setString(3, C.getCcertification());
				ps.setString(4, C.getCtype());
				ps.setInt(5, C.getCduration());
				ps.setInt(6, C.getCfee());
				int i=ps.executeUpdate();
				if(i>0) {
					System.out.println("New Course Added Successfully.");
					System.out.println("------------------------------");
				}
			} catch(SQLException e) {
				System.out.println("Only Admin/Staff can Add/Update/Delete Courses");
				System.out.println("------------------------------");
			}
		}else {
			System.out.println("Only Admin/Staff Can Insert New Courses.");
		}
		return C;
	}

	public Courses updateCourseById(Login L, Scanner s, Courses C) {
		Connection con=DBUtil.getConnect(L);
		boolean breakLoop=false;
		do {
			if(con!=null)
			{
				C=showCoursesByCid(L, C);
				System.out.println("Are You Sure? Y/N");
				String confirm=s.nextLine().toUpperCase();
				System.out.println("------------------------------");
				if(confirm.equals("N")||confirm.equals("NO")) {
					breakLoop=true;
					continue;
				}
				System.out.println("Enter Course Name:\t\t\t\t(was:"+C.getCname()+")");
				C.setCname(s.nextLine().replaceAll("\\s+", "").toUpperCase());
				System.out.println("Enter Course Company:\tMKCL/GCC-TBC/SSC\t\t(was:"+C.getCcompany()+")");
				C.setCcompany(s.nextLine().toUpperCase());
				System.out.println("Enter Course Certification:\tGov/Priv\t\t(was:"+C.getCcertification()+")");
				C.setCcertification(s.nextLine().toUpperCase());
				System.out.println("Enter Course Type:\tComp/Prog/Typ/EngSpk\t(was:"+C.getCtype()+")");
				C.setCtype(s.nextLine().toUpperCase());
				while(true) {													//Verify Only Integer Values
					System.out.println("Enter Course Duration:\t\t\t\t(was:"+C.getCduration()+")");
					try {
					C.setCduration(s.nextInt());
					s.nextLine();
					break;
					}catch(InputMismatchException e) {
						System.out.println("Enter numeric Value for Duration");
						s.next();
					}
					}
					while(true) {													//Verify Only Integer Values
					System.out.println("Enter Course Fee:\t\t\t\t(was:"+C.getCfee()+")");
					try {
					C.setCfee(s.nextInt());
					s.nextLine();
					break;
					}catch(InputMismatchException e) {
						System.out.println("Enter numeric Value for Fee");
					}
					}
				System.out.println("------------------------------");
				
				String sql="update Courses set Cname=?,Ccompany=?,Ccertification=?,Ctype=?,Cduration=?,Cfee=? where Cid=?";
				try {
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setInt(7, C.getCid());
					ps.setString(1, C.getCname());
					ps.setString(2, C.getCcompany());
					ps.setString(3, C.getCcertification());
					ps.setString(4, C.getCtype());
					ps.setInt(5, C.getCduration());
					ps.setInt(6, C.getCfee());
					int i=ps.executeUpdate();
					if(i>0)
					{
						System.out.println("Cid="+C.getCid()+" Cname="+C.getCname()+" Sucessfully Updated");
						System.out.println("------------------------------");
						breakLoop=true;
					}
				} catch (SQLException e) {
					System.out.println("Only Admin/Staff can Add/Update/Delete Courses");
					System.out.println("------------------------------");
				}
			}else {
				System.out.println("Only Admin/Staff Can Update Courses.");
				breakLoop=true;
			}
		}while(breakLoop==false);
		return C;	
	}

	public Courses deleteCourseById(Login L, Scanner s, Courses C) {
		Connection con=DBUtil.getConnect(L);
		boolean breakLoop=false;
		do {
			if(con!=null)
			{
				String sql="select * from Courses where Cid=?";
				List <Courses> l=new ArrayList<Courses>();
				try {
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setInt(1, C.getCid());
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						C=new Courses();
						C.setCid(rs.getInt(1));
						C.setCname(rs.getString(2));
						C.setCcompany(rs.getString(3));
						C.setCcertification(rs.getString(4));
						C.setCtype(rs.getString(5));
						C.setCduration(rs.getInt(6));
						C.setCfee(rs.getInt(7));
						l.add(C);
					}
					if (l.isEmpty()) {
					    System.out.println("No course found with Cid = " + C.getCid());
						System.out.println("------------------------------");
					    breakLoop=true;
					    break;
					} else {
					    for (Courses c : l) {
					        System.out.println(c); // or custom format
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
				sql="delete from Courses where Cid=?";
				try {
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setInt(1, C.getCid());
					int i=ps.executeUpdate();
					if(i>0)
					{
						System.out.println("Cid="+C.getCid()+" Cname="+C.getCname()+" Sucessfully Deleted");
						System.out.println("------------------------------");
						breakLoop=true;
						break;
					}
				}catch(SQLIntegrityConstraintViolationException e) {
					System.out.println(C.getCname()+" is currently used by Student.....\nContact DBAdmin");
					System.out.println("------------------------------");
					break;
				}catch (SQLException e) {
					System.out.println("Only Admin/Staff can Add/Update/Delete Courses");
					System.out.println("------------------------------");
					break;
				}
			}else {
				System.out.println("Only Admin/Staff Can Update Courses.");
				breakLoop=true;
			}
		}while(breakLoop==false);
		return C;
	}

	public Courses showAllCourses(Login L, Courses C) {
		Connection con=DBUtil.getConnect(L);
		if(con!=null) {
			String sql="select * from Courses";
			List <Courses> l=new ArrayList<Courses>();
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					C=new Courses();
					C.setCid(rs.getInt(1));
					C.setCname(rs.getString(2));
					C.setCcompany(rs.getString(3));
					C.setCcertification(rs.getString(4));
					C.setCtype(rs.getString(5));
					C.setCduration(rs.getInt(6));
					C.setCfee(rs.getInt(7));
					l.add(C);
				}
				if(l!=null) {
					Iterator <Courses> it=l.iterator();
					while(it.hasNext()) {
						System.out.println(it.next());
						System.out.println("........................................................................................................................");
					}
					System.out.println("------------------------------");
				}
			}catch(SQLException E) {
				System.out.println("Couldn't Fetch from Courses.");
			}
		}
		return C;
	}

	
	public Courses showCoursesByCid(Login L,Courses C){
		Connection con=DBUtil.getConnect(L);
		String sql="select * from Courses where Cid=?";
		List <Courses> l=new ArrayList<Courses>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, C.getCid());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				C=new Courses();
				C.setCid(rs.getInt(1));
				C.setCname(rs.getString(2));
				C.setCcompany(rs.getString(3));
				C.setCcertification(rs.getString(4));
				C.setCtype(rs.getString(5));
				C.setCduration(rs.getInt(6));
				C.setCfee(rs.getInt(7));
				l.add(C);
			}
			if(l!=null) {
				Iterator <Courses> it=l.iterator();
				while(it.hasNext()) {
					System.out.println(it.next());
				}
				System.out.println("------------------------------");
			}
		}catch(Exception E) {
			System.out.println("Couldn't Fetch from Courses.");
		}
		return C;
	}
}
