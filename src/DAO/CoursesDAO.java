package DAO;

import java.util.Scanner;

import POJO.Courses;
import POJO.Login;

public interface CoursesDAO {

	public Courses addCourse(Login L, Scanner s, Courses C);
	public Courses updateCourseById(Login L, Scanner s, Courses C);
	public Courses deleteCourseById(Login L, Scanner s, Courses C);
	public Courses showAllCourses(Login L, Courses C);
	public Courses showCoursesByCid(Login L,Courses C);
}
