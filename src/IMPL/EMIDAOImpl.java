package IMPL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import DAO.EMIDAO;
import POJO.EMI;
import POJO.Login;
import Utility.DBUtil;

public class EMIDAOImpl implements EMIDAO {

	LocalDateTime LDT = LocalDateTime.now();
	DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-mm-yyyy");
	String SystemDate = LDT.format(DTF);

	@Override
	public boolean payEMI(Login L, Scanner s, EMI E) {
		Connection con = DBUtil.getConnect(L);
		if (con != null) {
			showEMIById(L, s, E);
			String sql = "update EMI set Epaid=?, EpayDate=?, EpayMode=? where Eid=?";
			try {
				System.out.println("Enter Eid to pay:");
				E.setEid(s.nextInt());s.nextLine();
	            System.out.println("------------------------------");
				System.out.println("Enter EpayMode:\\tCASH/UPI/CHEQUE");
				E.setEpayMode(s.nextLine().toUpperCase());
	            System.out.println("------------------------------");
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setBoolean(1, true);
				ps.setTimestamp(2, Timestamp.valueOf(LDT));
				ps.setString(3, E.getEpayMode());
				ps.setInt(4, E.getEid());
				int i = ps.executeUpdate();
				if (i > 0) {
					System.out.println("EMI Updated Successfully");
					System.out.println("------------------------------");
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Only Admin/Staff Can Add/Update/Delete SCourses");
				System.out.println("------------------------------");
			}
		} else {
			System.out.println("Only Admin/Staff Can Insert New SCourses.");
		}
		return false;
	}

	@Override
	public EMI updateStudentEMIById(Login L, Scanner s, EMI E) {
		// TODO Auto-generated method stub
		return E;
	}

	@Override
	public EMI unPayEMI(Login L, Scanner s, EMI E) {
		Connection con = DBUtil.getConnect(L);
		if (con != null) {
			showEMIById(L, s, E);
			System.out.println("Enter Eid to UnPay:");
			E.setEid(s.nextInt());s.nextLine();
            System.out.println("------------------------------");
			String sql = "update EMI set Epaid=?, EpayDate=?, EpayMode=? where Eid=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setBoolean(1, false);
				ps.setTimestamp(2, null);
				ps.setString(3, null);
				ps.setInt(4, E.getEid());
				int i = ps.executeUpdate();
				if (i > 0) {
					System.out.println("EMI Updated Successfully");
					System.out.println("------------------------------");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Only Admin/Staff Can Add/Update/Delete SCourses");
				System.out.println("------------------------------");
			}
		} else {
			System.out.println("Only Admin/Staff Can Insert New SCourses.");
		}
		return E;
	}

	@Override
	public EMI showEMIById(Login L, Scanner s, EMI E) {
		Connection con = DBUtil.getConnect(L);
		String sql = "select * from EMI where Sid=?";
		List <EMI> l=new ArrayList<EMI>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, E.getSid());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				E=new EMI();
				E.setEid(rs.getInt(1));
				E.setSid(rs.getInt(2));
				E.setSname(rs.getString(3));
				E.setCid(rs.getInt(4));
				E.setCname(rs.getString(5));
				E.setSCnum(rs.getInt(6));
				Date emonthDate = rs.getDate(7); // getDate returns java.sql.Date
				if (emonthDate != null) {
				    E.setEmonth(emonthDate.toLocalDate());
				} else {
				    E.setEmonth(null);
				}
				E.setEinstallment(rs.getInt(8));
				E.setEpaid(rs.getBoolean(9));
				Timestamp ts = rs.getTimestamp(10);
	            if (ts != null) {
	                E.setEpayDate(ts.toLocalDateTime());
	            } else {
	                E.setEpayDate(null); // or default value if needed
	            }
				E.setEpayMode(rs.getString(11));
				l.add(E);
			}
			if(l!=null) {
				Iterator <EMI> it=l.iterator();
				while(it.hasNext()) {
					System.out.println(it.next());
				}
				System.out.println("------------------------------");
			}
		} catch (SQLException e) {
			System.out.println("Only Admin/Staff Can Add/Update/Delete SCourses");
			System.out.println("------------------------------");
		}
		return E;
	}

	@Override
	public EMI showAllStudentEMI(Login L, EMI E) {
		Connection con = DBUtil.getConnect(L);
		String sql = "select * from EMI";
		List <EMI> l=new ArrayList<EMI>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				E=new EMI();
				E.setEid(rs.getInt(1));
				E.setSid(rs.getInt(2));
				E.setSname(rs.getString(3));
				E.setCid(rs.getInt(4));
				E.setCname(rs.getString(5));
				E.setSCnum(rs.getInt(6));
				Date emonthDate = rs.getDate(7);
				if (emonthDate != null) {
				    E.setEmonth(emonthDate.toLocalDate());
				} else {
				    E.setEmonth(null);
				}
				E.setEinstallment(rs.getInt(8));
				E.setEpaid(rs.getBoolean(9));
				Timestamp ts = rs.getTimestamp(10);
	            if (ts != null) {
	                E.setEpayDate(ts.toLocalDateTime());
	            } else {
	                E.setEpayDate(null); // or default value if needed
	            }
				E.setEpayMode(rs.getString(11));
				l.add(E);
			}
			if(l!=null) {
				Iterator <EMI> it=l.iterator();
				while(it.hasNext()) {
					System.out.println(it.next());
				}
				System.out.println("------------------------------");
			}
		} catch (SQLException e) {
			System.out.println("Only Admin/Staff Can Add/Update/Delete SCourses");
			System.out.println("------------------------------");
		}
		return E;
	}

	@Override
	public EMI showUnpaidEMI(Login L, EMI E) {
		Connection con = DBUtil.getConnect(L);
		String sql = "SELECT * FROM EMI e\r\n"
				+ "WHERE Epaid = false\r\n"
				+ "  AND NOT EXISTS (\r\n"
				+ "    SELECT 1 FROM EMI e2\r\n"
				+ "    WHERE e.Sid = e2.Sid AND e.SCnum = e2.SCnum\r\n"
				+ "      AND e2.Epaid = false AND e2.Emonth < e.Emonth\r\n"
				+ "  )\r\n"
				+ "ORDER BY e.Sid, e.SCnum, e.Emonth;";
		List <EMI> l=new ArrayList<EMI>();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				E=new EMI();
				E.setEid(rs.getInt(1));
				E.setSid(rs.getInt(2));
				E.setSname(rs.getString(3));
				E.setCid(rs.getInt(4));
				E.setCname(rs.getString(5));
				E.setSCnum(rs.getInt(6));
				Date emonthDate = rs.getDate(7);
				if (emonthDate != null) {
				    E.setEmonth(emonthDate.toLocalDate());
				} else {
				    E.setEmonth(null);
				}
				E.setEinstallment(rs.getInt(8));
				E.setEpaid(rs.getBoolean(9));
				Timestamp ts = rs.getTimestamp(10);
	            if (ts != null) {
	                E.setEpayDate(ts.toLocalDateTime());
	            } else {
	                E.setEpayDate(null); // or default value if needed
	            }
				E.setEpayMode(rs.getString(11));
				l.add(E);
			}
			if(l!=null) {
				Iterator <EMI> it=l.iterator();
				while(it.hasNext()) {
					System.out.println(it.next());
				}
				System.out.println("------------------------------");
			}
		} catch (SQLException e) {
			System.out.println("Only Admin/Staff Can Add/Update/Delete SCourses");
			System.out.println("------------------------------");
		}
		return E;
	}
	
}
