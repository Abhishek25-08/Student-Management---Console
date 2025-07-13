package DAO;

import java.util.Scanner;

import POJO.Login;
import POJO.SCourses;

public interface SCoursesDAO {
	
	public SCourses addStudentCourses(Login L, Scanner s, SCourses SC);
	public SCourses updateStudentCoursesById(Login L, Scanner s, SCourses SC);
	public SCourses deleteStudentCoursesById(Login L, Scanner s, SCourses SC);
	public SCourses showAllStudentCourses(Login L, SCourses SC);
	public SCourses showSCoursesBySCid(Login L,SCourses SC);
	
}
