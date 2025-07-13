package POJO;

public class StudentDetails {
	
	private int Sid;
	private String Sname;
	private String Sphone;
	private String Saddress;
	private String StotalDuration;
	private String SdurationLeft;
	private String StotalFee;
	private String SbalanceFee;
	
	public StudentDetails() {
		super();
	}

	public StudentDetails(String sname, String sphone, String saddress) {
		super();
		Sname = sname;
		Sphone = sphone;
		Saddress = saddress;
	}	//Input Constructor
	
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

	public String getSphone() {
		return Sphone;
	}

	public void setSphone(String sphone) {
		Sphone = sphone;
	}

	public String getSaddress() {
		return Saddress;
	}

	public void setSaddress(String saddress) {
		Saddress = saddress;
	}

	public String getStotalDuration() {
		return StotalDuration;
	}

	public void setStotalDuration(String stotalDuration) {
		StotalDuration = stotalDuration;
	}

	public String getSdurationLeft() {
		return SdurationLeft;
	}

	public void setSdurationLeft(String sdurationLeft) {
		SdurationLeft = sdurationLeft;
	}

	public String getStotalFee() {
		return StotalFee;
	}

	public void setStotalFee(String stotalFee) {
		StotalFee = stotalFee;
	}

	public String getSbalanceFee() {
		return SbalanceFee;
	}

	public void setSbalanceFee(String sbalanceFee) {
		SbalanceFee = sbalanceFee;
	}

	public String toString() {
		return "Sid=" + Sid + "\t\t| Sname=" + Sname + "\t| Sphone=" + Sphone + "\t| Saddress=" + Saddress
				+ "\nStotalDuration=" + StotalDuration + "\t| SdurationLeft=" + SdurationLeft + "\t| StotalFee=" + StotalFee
				+ "\t\t| SbalanceFee=" + SbalanceFee ;
	}
	
}
