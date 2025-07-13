package POJO;

public class Courses {

	private int Cid;
	private String Cname;
	private String Ccompany;
	private String Ccertification;
	private String Ctype;
	private int Cduration;
	private int Cfee;
	
	public Courses() {
		super();
	}

	public Courses(String cname, String ccompany, String ccertification, String ctype, int cduration, int cfee) {
		super();
		Cname = cname;
		Ccompany = ccompany;
		Ccertification = ccertification;
		Ctype = ctype;
		Cduration = cduration;
		Cfee = cfee;
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

	public String getCcompany() {
		return Ccompany;
	}

	public void setCcompany(String ccompany) {
		Ccompany = ccompany;
	}

	public String getCcertification() {
		return Ccertification;
	}

	public void setCcertification(String ccertification) {
		Ccertification = ccertification;
	}

	public String getCtype() {
		return Ctype;
	}

	public void setCtype(String ctype) {
		Ctype = ctype;
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

	public String toString() {
		return "Cid= " + Cid + "\t| Cname= " + Cname + "\t| Ccompany= " + Ccompany + "\t| Ccertification= "
				+ Ccertification + "\t| Ctype= " + Ctype + "\t| Cduration= " + Cduration + "\t| Cfee= " + Cfee;
	}
	
	
	
}
