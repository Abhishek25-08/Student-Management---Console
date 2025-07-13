package IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import DAO.SCoursesDAO;
import POJO.Login;
import POJO.SCourses;
import Utility.DBUtil;

public class SCoursesDAOImpl implements SCoursesDAO{

	LocalDateTime LDT=LocalDateTime.now();
	DateTimeFormatter DTF=DateTimeFormatter.ofPattern("dd-mm-yyyy");
	String SystemDate=LDT.format(DTF);
	
	public SCourses addStudentCourses(Login L, Scanner s, SCourses SC) {
		Connection con=DBUtil.getConnect(L);
		if(con!=null) {			
			System.out.println("Enter Student Id:");
			SC.setSid(s.nextInt());s.nextLine();							
			System.out.println("Enter Course Id:");
			SC.setCid(s.nextInt());s.nextLine();
			System.out.println("Enter Student Course Number:");
			SC.setSCnum(s.nextInt());s.nextLine();
			System.out.println("Enter Student Course Discount:\t in RS");
			SC.setSCdiscount(s.nextInt());s.nextLine();
			System.out.println("Enter Student Course PayType:\tFULL/EMI");
			SC.setSCpayType(s.nextLine().toUpperCase());
			String payType=SC.getSCpayType();
			if(payType.equals("FULL")) {
				System.out.println("Enter Student Course PayMode:\tCASH/UPI/CHEQUE");
				SC.setSCpayMode(s.nextLine().toUpperCase());
			}else {
				SC.setSCpayMode("EMI");
			}
			System.out.println("------------------------------");
			String sql="insert into SCourses (Sid,Cid,SCnum,SCdiscount,SCpayType,SCpayMode,SCadmissionDate) values (?,?,?,?,?,?,?)";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setInt(1, SC.getSid());
				ps.setInt(2, SC.getCid());
				ps.setInt(3, SC.getSCnum());
				ps.setInt(4, SC.getSCdiscount());
				ps.setString(5, SC.getSCpayType());
				if(SC.getSCpayType()=="EMI") {
					ps.setString(6, "EMI");
				}else {
					ps.setString(6, SC.getSCpayMode());
				}
				ps.setTimestamp(7, Timestamp.valueOf(LDT));
				int i=ps.executeUpdate();
				if(i>0) {
					System.out.println("Student Added Successfully To New SCourses");
					System.out.println("------------------------------");
				}
			} catch(SQLException e) {
				System.out.println("Only Admin/Staff Can Add/Update/Delete SCourses");
				System.out.println("------------------------------");
			}
		}else {
			System.out.println("Only Admin/Staff Can Insert New SCourses.");
		}
		return SC;
	}

	public SCourses updateStudentCoursesById(Login L, Scanner s, SCourses SC) {
		Connection con = DBUtil.getConnect(L);
	    boolean breakLoop = false;
	    do {
	        if (con != null) {
	        	SC=showSCoursesBySCid(L, SC);
	            System.out.println("Are You Sure? Y/N");
	            String confirm = s.nextLine().toUpperCase();
	            if (confirm.equals("N") || confirm.equals("NO")) {
	                breakLoop = true;
	                continue;
	            }
	            System.out.println("------------------------------");
	            System.out.println("Update AdmissionDate?\tYES/NO\t(was:" + SC.getSCadmissionDate() + ")");
	            String AdmissionDate = s.nextLine().toUpperCase();
	            if (AdmissionDate == "YES" || AdmissionDate == "Y") {
	                SC.setSCadmissionDate(LocalDateTime.now());
	            }
	            System.out.println("------------------------------");
	            System.out.println("Enter CourseID:\t\t(was:" + SC.getCid() + ")");
	            SC.setCid(s.nextInt());
	            s.nextLine(); // fix: consume leftover newline
	            System.out.println("Enter SCnum:\t\t\t(was:" + SC.getSCnum() + ")");
	            SC.setSCnum(s.nextInt());
	            s.nextLine(); // fix: consume leftover newline
	            System.out.println("Enter SCdiscount:\t\t(was:" + SC.getSCdiscount() + ")");
	            SC.setSCdiscount(s.nextInt());
	            s.nextLine(); // fix: consume leftover newline
	            System.out.println("Enter SCpayType:\tFULL/EMI\t(was:" + SC.getSCpayType() + ")");
	            SC.setSCpayType(s.nextLine().toUpperCase());
	            if (SC.getSCpayType() == "FULL") {
	                System.out.println("Enter SCpayMode:\tCASH/UPI/CHEQUE\t(was:" + SC.getSCpayMode() + ")");
	                SC.setSCpayMode(s.nextLine().toUpperCase());
	            } else {
	                System.out.println("Enter SCpayMode:\tPAID/UNPAID\t(was:" + SC.getSCpayMode() + ")");
	                SC.setSCpayMode(s.nextLine().toUpperCase());
	            }
	            String sql = "update SCourses set Cid=?, SCnum=?, SCdiscount=?, SCpayType=?, SCpayMode=?, SCadmissionDate=? where SCid=?";
	            try {
	                PreparedStatement ps = con.prepareStatement(sql);
	                ps.setInt(1, SC.getCid());
	                ps.setInt(2, SC.getSCnum());
	                ps.setInt(3, SC.getSCdiscount());
	                ps.setString(4, SC.getSCpayType());
	                ps.setString(5, SC.getSCpayMode());
	                ps.setTimestamp(6, Timestamp.valueOf(SC.getSCadmissionDate()));
	                ps.setInt(7, SC.getSCid());
	                int i = ps.executeUpdate();
	                if (i > 0) {
	                    System.out.println("SCid=" + SC.getSCid() + " Sucessfully Updated");
	                    System.out.println("------------------------------");
	                    breakLoop = true;
	                }
	            } catch (SQLException e) {
	                System.out.println("Only Admin/Staff can Add/Update/Delete SCourses");
	                System.out.println("------------------------------");
	            }
	        } else {
	            System.out.println("Only Admin/Staff Can Update SCourses.");
	            breakLoop = true;
	        }
	    } while (breakLoop == false);
	    return SC;
	}

	@Override
	public SCourses deleteStudentCoursesById(Login L, Scanner s, SCourses SC) {
		Connection con=DBUtil.getConnect(L);
		boolean breakLoop=false;
		do {
			if(con!=null)
			{
				SC=showSCoursesBySCid(L, SC);
				System.out.println("Are You Sure? Y/N");
				String confirm=s.nextLine().toUpperCase();
				System.out.println("------------------------------");
				if(confirm.equals("N")||confirm.equals("NO")) {
					breakLoop=true;
					continue;
				}
				String sql="delete from SCourses where SCid=?";
				try {
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setInt(1, SC.getSid());
					int i=ps.executeUpdate();
					if(i>0)
					{
						System.out.println("Sid="+SC.getSid()+" Sucessfully Deleted");
						System.out.println("------------------------------");
						breakLoop=true;
						break;
					}
				}catch(SQLIntegrityConstraintViolationException e) {
					System.out.println("Sid="+SC.getSid()+" is currently used by DATABASE.....\nContact DBAdmin");
					System.out.println("------------------------------");
					break;
				}catch (SQLException e) {
					System.out.println("Only Admin/Staff can Add/Update/Delete SCourses");
					System.out.println("------------------------------");
					break;
				}
			}else {
				System.out.println("Only Admin/Staff Can Update SCourses.");
				breakLoop=true;
			}
		}while(breakLoop==false);
		return SC;
	}

	@Override
	public SCourses showAllStudentCourses(Login L, SCourses SC) {
		Connection con=DBUtil.getConnect(L);
		if(con!=null) {
			String sql="select * from SCourses";
			List <SCourses> l=new ArrayList<SCourses>();
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					SC=new SCourses();
					SC.setSCid(rs.getInt(1));
					SC.setSid(rs.getInt(2));
					SC.setSname(rs.getString(3));
					SC.setCid(rs.getInt(4));
					SC.setCname(rs.getString(5));
					SC.setCduration(rs.getInt(6));
					SC.setCfee(rs.getInt(7));
					SC.setSCnum(rs.getInt(8));
					SC.setSCdiscount(rs.getInt(9));
					SC.setSCdiscFee(rs.getInt(10));
					SC.setSCpayType(rs.getString(11));
					SC.setSCpayMode(rs.getString(12));
					SC.setSCadmissionDate(rs.getTimestamp(13).toLocalDateTime());
					SC.setSCeraStatus(rs.getString(14));
					SC.setSCcertification(rs.getString(15));
					SC.setSCresult(rs.getString(16));
					l.add(SC);
				}
				if(l!=null) {
					Iterator <SCourses> it=l.iterator();
					while(it.hasNext()) {
						System.out.println(it.next());
						System.out.println("........................................................................................................................");
					}
					System.out.println("------------------------------");
				}
			}catch(SQLException E) {
				E.printStackTrace();
				System.out.println("Couldn't Fetch from SCourses.");
			}
		}
		return SC;
	}

	public SCourses showSCoursesBySCid(Login L,SCourses SC){
		Connection con=DBUtil.getConnect(L);
		String sql = "select * from SCourses where SCid=?";
        List<SCourses> l = new ArrayList<SCourses>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, SC.getSCid());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SC = new SCourses();
                SC.setSCid(rs.getInt(1));
                SC.setSid(rs.getInt(2));
                SC.setSname(rs.getString(3));
                SC.setCid(rs.getInt(4));
                SC.setCname(rs.getString(5));
                SC.setCduration(rs.getInt(6));
                SC.setCfee(rs.getInt(7));
                SC.setSCnum(rs.getInt(8));
                SC.setSCdiscount(rs.getInt(9));
                SC.setSCdiscFee(rs.getInt(10));
                SC.setSCpayType(rs.getString(11));
                SC.setSCpayMode(rs.getString(12));
                SC.setSCadmissionDate(rs.getTimestamp(13).toLocalDateTime());
                SC.setSCeraStatus(rs.getString(14));
                SC.setSCcertification(rs.getString(15));
                SC.setSCresult(rs.getString(16));
                l.add(SC);
            }
            if (l != null) {
                Iterator<SCourses> it = l.iterator();
                while (it.hasNext()) {
                    System.out.println(it.next());
                }
                System.out.println("------------------------------");
            }
        } catch (Exception E) {
            System.out.println("Couldn't Fetch from Student Courses.");
        }
		
		return SC;
	}
	
}
