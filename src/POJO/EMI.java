package POJO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EMI {

	private int Eid;
	private int Sid;
	private String Sname;
	private int Cid;
	private String Cname;
	private int SCnum;
	private LocalDate Emonth;
	private int Einstallment;
	private boolean Epaid;
	private LocalDateTime EpayDate;
	private String EpayMode;
	
	public EMI() {
		super();
	}

	public EMI(int sid, int cid, int sCnum, LocalDate emonth, int einstallment, boolean epaid) {
		super();
		Sid = sid;
		Cid = cid;
		SCnum = sCnum;
		Emonth = emonth;
		Einstallment = einstallment;
		Epaid = epaid;
	}	//Input Constructor

	public EMI(LocalDateTime epayDate, String epayMode) {
		super();
		EpayDate = epayDate;
		EpayMode = epayMode;
	}	//Payment Constructor

	public int getEid() {
		return Eid;
	}

	public void setEid(int eid) {
		Eid = eid;
	}

	public int getSid() {
		return Sid;
	}

	public void setSid(int sid) {
		Sid = sid;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public int getCid() {
		return Cid;
	}

	public void setCid(int cid) {
		Cid = cid;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public int getSCnum() {
		return SCnum;
	}

	public void setSCnum(int sCnum) {
		SCnum = sCnum;
	}

	public LocalDate getEmonth() {
		return Emonth;
	}

	public void setEmonth(LocalDate emonth) {
		Emonth = emonth;
	}

	public int getEinstallment() {
		return Einstallment;
	}

	public void setEinstallment(int einstallment) {
		Einstallment = einstallment;
	}

	public boolean isEpaid() {
		return Epaid;
	}

	public void setEpaid(boolean epaid) {
		Epaid = epaid;
	}

	public LocalDateTime getEpayDate() {
		return EpayDate;
	}

	public void setEpayDate(LocalDateTime epayDate) {
		EpayDate = epayDate;
	}

	public String getEpayMode() {
		return EpayMode;
	}

	public void setEpayMode(String epayMode) {
		EpayMode = epayMode;
	}

	public String toString() {
		return "EMI [Eid=" + Eid + ", Sid=" + Sid + ", Sname=" + Sname + ", Cid=" + Cid + ", Cname=" + Cname
				+ ", SCnum=" + SCnum + ", Emonth=" + Emonth + ", Einstallment=" + Einstallment + ", Epaid=" + Epaid
				+ ", EpayDate=" + EpayDate + ", EpayMode=" + EpayMode + "]";
	}
	
}
