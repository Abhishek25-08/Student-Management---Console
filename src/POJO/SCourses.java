package POJO;

import java.time.LocalDateTime;

public class SCourses {

	private int SCid;
	private int Sid;
	private String Sname;
	private int Cid;
	private String Cname;
	private int Cduration;
	private int Cfee;
	private int SCnum;
	private int SCdiscount;
	private int SCdiscFee;
	private String SCpayType;
	private String SCpayMode;
	private LocalDateTime SCadmissionDate;
	private String SCeraStatus;
	private String SCcertification;
	private String SCresult;
	
	public SCourses() {
		super();
	}

	public SCourses(int sid, int cid, int sCnum, int sCdiscount, String sCpayType, String sCpayMode,
			LocalDateTime sCadmissionDate) {
		super();
		Sid = sid;
		Cid = cid;
		SCnum = sCnum;
		SCdiscount = sCdiscount;
		SCpayType = sCpayType;
		SCpayMode = sCpayMode;
		SCadmissionDate = sCadmissionDate;
	}	//Input Constructor

	public int getSCid() {
		return SCid;
	}

	public void setSCid(int sCid) {
		SCid = sCid;
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

	public int getCduration() {
		return Cduration;
	}

	public void setCduration(int cduration) {
		Cduration = cduration;
	}

	public int getCfee() {
		return Cfee;
	}

	public void setCfee(int cfee) {
		Cfee = cfee;
	}

	public int getSCnum() {
		return SCnum;
	}

	public void setSCnum(int sCnum) {
		SCnum = sCnum;
	}

	public int getSCdiscount() {
		return SCdiscount;
	}

	public void setSCdiscount(int sCdiscount) {
		SCdiscount = sCdiscount;
	}

	public int getSCdiscFee() {
		return SCdiscFee;
	}

	public void setSCdiscFee(int sCdiscFee) {
		SCdiscFee = sCdiscFee;
	}

	public String getSCpayType() {
		return SCpayType;
	}

	public void setSCpayType(String sCpayType) {
		SCpayType = sCpayType;
	}

	public String getSCpayMode() {
		return SCpayMode;
	}

	public void setSCpayMode(String sCpayMode) {
		SCpayMode = sCpayMode;
	}

	public LocalDateTime getSCadmissionDate() {
		return SCadmissionDate;
	}

	public void setSCadmissionDate(LocalDateTime sCadmissionDate) {
		SCadmissionDate = sCadmissionDate;
	}

	public String getSCeraStatus() {
		return SCeraStatus;
	}

	public void setSCeraStatus(String sCeraStatus) {
		SCeraStatus = sCeraStatus;
	}

	public String getSCcertification() {
		return SCcertification;
	}

	public void setSCcertification(String sCcertification) {
		SCcertification = sCcertification;
	}

	public String getSCresult() {
		return SCresult;
	}

	public void setSCresult(String sCresult) {
		SCresult = sCresult;
	}

	@Override
	public String toString() {
		return "SCourses [SCid=" + SCid + ", Sid=" + Sid + ", Sname=" + Sname + ", Cid=" + Cid + ", Cname=" + Cname
				+ ", Cduration=" + Cduration + ", Cfee=" + Cfee + ", SCnum=" + SCnum + ", SCdiscount=" + SCdiscount
				+ ", SCdiscFee=" + SCdiscFee + ", SCpayType=" + SCpayType + ", SCpayMode=" + SCpayMode
				+ ", SCadmissionDate=" + SCadmissionDate + ", SCeraStatus=" + SCeraStatus + ", SCcertification="
				+ SCcertification + ", SCresult=" + SCresult + "]";
	}
	
}
