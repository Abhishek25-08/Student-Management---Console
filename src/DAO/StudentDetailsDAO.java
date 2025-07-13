package DAO;

import java.util.Scanner;

import POJO.Login;
import POJO.StudentDetails;

public interface StudentDetailsDAO {

	public StudentDetails addStudentDetails(Login L, Scanner s, StudentDetails SD);
	public StudentDetails updateStudentDetailsById(Login L, Scanner s, StudentDetails SD);
	public StudentDetails deleteStudentDetailsById(Login L, Scanner s, StudentDetails SD);
	public StudentDetails showAllStudentDetails(Login L, StudentDetails SD);
	public StudentDetails showStudentDetailsBySCid(Login L,StudentDetails SD);
}
