package DAO;

import java.util.Scanner;

import POJO.EMI;
import POJO.Login;

public interface EMIDAO {
	public boolean payEMI(Login L, Scanner s, EMI E);
	public EMI updateStudentEMIById(Login L, Scanner s, EMI E);
	public EMI unPayEMI(Login L, Scanner s, EMI E);
	public EMI showEMIById(Login L, Scanner s, EMI E);
	public EMI showAllStudentEMI(Login L, EMI E);
	public EMI showUnpaidEMI(Login L, EMI E);
}
