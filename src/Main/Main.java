package Main;

import java.util.Scanner;

import IMPL.CoursesDAOImpl;
import IMPL.EMIDAOImpl;
import IMPL.LoginDAOImpl;
import IMPL.SCoursesDAOImpl;
import IMPL.StudentDetailsDAOImpl;
import POJO.Courses;
import POJO.EMI;
import POJO.Login;
import POJO.SCourses;
import POJO.StudentDetails;

public class Main {

	public static void main(String[] args) {
		
		Scanner s=new Scanner(System.in);
		Login L=new Login();
		LoginDAOImpl LDI=new LoginDAOImpl();
		//DBUtilDAOImpl DBDI=new DBUtilDAOImpl();
		boolean Mmenu=true;
		do {

			System.out.println("1. Courses");
			System.out.println("2. Students Details");
			System.out.println("3. Students Courses");
			System.out.println("4. Students EMI");
			System.out.println("5. Exit Program");
			int Mchoice=s.nextInt();s.nextLine();
			System.out.println("------------------------------");
			switch(Mchoice) {
			case 1:{																		//Courses
				Courses C=new Courses();
				CoursesDAOImpl CDI=new CoursesDAOImpl();
				boolean Cmenu=true;
				do{
					System.out.println("1. Add Course");
					System.out.println("2. Update Course");
					System.out.println("3. Delete Course");
					System.out.println("4. Show All Courses");
					System.out.println("5. Exit to Main Menu");
					int Cchoice=s.nextInt();
					s.nextLine();
					System.out.println("------------------------------");
					switch(Cchoice) {
					case 1:{																//Add New Course
						LDI.changeLogin(L, s);
						boolean again=true;
						do {
							CDI.addCourse(L, s, C);
							System.out.println("Add Another Course? Y/N");
							String choice=s.nextLine().toUpperCase().trim();
							System.out.println("------------------------------");
							if(choice.equals("N")||choice.equals("NO")) {
								again=false;
							}
						}while(again==true);
						LDI.defaultLogin(L);
						break;
					}
					case 2:{																//Update Courses
						LDI.changeLogin(L, s);
						boolean again=true;
						do {
							CDI.showAllCourses(L, C);
							System.out.println("Enter Course Id to update");
							C.setCid(s.nextInt());s.nextLine();
							System.out.println("------------------------------");
							CDI.updateCourseById(L, s, C);
							System.out.println("Update Another Course? Y/N");
							String choice=s.nextLine().toUpperCase().trim();
							System.out.println("------------------------------");
							if(choice.equals("N")||choice.equals("NO")) {
								again=false;
							}
						}while(again==true);
						LDI.defaultLogin(L);
						break;
					}
					case 3:{																//Delete Courses
						LDI.changeLogin(L, s);
						boolean again=true;
						do {
							CDI.showAllCourses(L, C);
							System.out.println("Enter Course Id to Delete");
							C.setCid(s.nextInt());s.nextLine();
							System.out.println("------------------------------");
							CDI.deleteCourseById(L, s, C);
							System.out.println("Delete Another Course? Y/N");
							String choice=s.nextLine().toUpperCase().trim();
							System.out.println("------------------------------");
							if(choice.equals("N")||choice.equals("NO")) {
								again=false;
							}
						}while(again==true);
						LDI.defaultLogin(L);
						break;
					}
					case 4:{																//Show All Courses
						CDI.showAllCourses(L, C);
						break;
					}
					case 5:{
						System.out.println("Going Back to Main Menu.");
						System.out.println("------------------------------");
						Cmenu=false;
						break;
					}
					}
				}while(Cmenu==true);
				break;
			}
			case 2:{																		//Students Details
				StudentDetails SD=new StudentDetails();
				StudentDetailsDAOImpl SDI=new StudentDetailsDAOImpl();
				boolean Cmenu=true;
				do{
					System.out.println("1. Add Students Details");
					System.out.println("2. Update Students Details");
					System.out.println("3. Delete Students Details");
					System.out.println("4. Show All Students Details");
					System.out.println("5. Exit to Main Menu");
					int Cchoice=s.nextInt();s.nextLine();
					System.out.println("------------------------------");
					switch(Cchoice) {
					case 1:{																//Add New StudentDetails
						LDI.changeLogin(L, s);
						boolean again=true;
						do {
							SDI.addStudentDetails(L, s, SD);
							System.out.println("Add Another Student? Y/N");
							String choice=s.nextLine().toUpperCase().trim();
							System.out.println("------------------------------");
							if(choice.equals("N")||choice.equals("NO")) {
								again=false;
							}
						}while(again==true);
						LDI.defaultLogin(L);
						break;
					}
					case 2:{																//Update StudentDetails
						LDI.changeLogin(L, s);
						boolean again=true;
						do {
							SDI.showAllStudentDetails(L, SD);
							System.out.println("Enter Student Id to update");
							SD.setSid(s.nextInt());s.nextLine();
							System.out.println("------------------------------");
							SDI.updateStudentDetailsById(L, s, SD);
							System.out.println("Update Another Student? Y/N");
							String choice=s.nextLine().toUpperCase().trim();
							System.out.println("------------------------------");
							if(choice.equals("N")||choice.equals("NO")) {
								again=false;
							}
						}while(again==true);
						LDI.defaultLogin(L);
						break;
					}
					case 3:{																//Delete StudentDetails
						LDI.changeLogin(L, s);
						boolean again=true;
						do {
							SDI.showAllStudentDetails(L, SD);
							System.out.println("Enter Student Id to Delete");
							SD.setSid(s.nextInt());s.nextLine();
							System.out.println("------------------------------");
							SDI.deleteStudentDetailsById(L, s, SD);
							System.out.println("Delete Another Student? Y/N");
							String choice=s.nextLine().toUpperCase().trim();
							System.out.println("------------------------------");
							if(choice.equals("N")||choice.equals("NO")) {
								again=false;
							}
						}while(again==true);
						LDI.defaultLogin(L);
						break;
					}
					case 4:{																//Show All StudentDetails
						SDI.showAllStudentDetails(L, SD);
						break;
					}
					case 5:{
						System.out.println("Going Back to Main Menu.");
						System.out.println("------------------------------");
						Cmenu=false;
						break;
					}
					}
				}while(Cmenu==true);
				break;
			}
			case 3:{																		//Students SCcourse
				SCourses SC=new SCourses();
				SCoursesDAOImpl SCDI=new SCoursesDAOImpl();
				boolean Cmenu=true;
				do{
					System.out.println("1. Add Students Courses");
					System.out.println("2. Update Students Courses");
					System.out.println("3. Delete Students Courses");
					System.out.println("4. Show All Students Courses");
					System.out.println("5. Exit to Main Menu");
					int Cchoice=s.nextInt();s.nextLine();
					System.out.println("------------------------------");
					switch(Cchoice) {
					case 1:{																//Add New Course to Student
						LDI.changeLogin(L, s);
						boolean again=true;
						do {
							SCDI.addStudentCourses(L, s, SC);
							System.out.println("Add Another Student? Y/N");
							String choice=s.nextLine().toUpperCase().trim();
							System.out.println("------------------------------");
							if(choice.equals("N")||choice.equals("NO")) {
								again=false;
							}
						}while(again==true);
						LDI.defaultLogin(L);
						break;
					}
					case 2:{																//Update Course of Student
						LDI.changeLogin(L, s);
						boolean again=true;
						do {
							SCDI.showAllStudentCourses(L, SC);
							System.out.println("Enter Student Course Id(SCid) to update");
							SC.setSCid(s.nextInt());s.nextLine();
							System.out.println("------------------------------");
							SCDI.updateStudentCoursesById(L, s, SC);
							System.out.println("Update Another Student? Y/N");
							String choice=s.nextLine().toUpperCase().trim();
							System.out.println("------------------------------");
							if(choice.equals("N")||choice.equals("NO")) {
								again=false;
							}
						}while(again==true);
						LDI.defaultLogin(L);
						break;
					}
					case 3:{																//Delete Course of StudentDetails
						LDI.changeLogin(L, s);
						boolean again=true;
						do {
							SCDI.showAllStudentCourses(L, SC);
							System.out.println("Enter Student Course Id to Delete");
							SC.setSCid(s.nextInt());s.nextLine();
							System.out.println("------------------------------");
							SCDI.deleteStudentCoursesById(L, s, SC);
							System.out.println("Delete Another Student Course? Y/N");
							String choice=s.nextLine().toUpperCase().trim();
							System.out.println("------------------------------");
							if(choice.equals("N")||choice.equals("NO")) {
								again=false;
							}
						}while(again==true);
						LDI.defaultLogin(L);
						break;
					}
					case 4:{																//Show All StudentDetails
						SCDI.showAllStudentCourses(L, SC);
						break;
					}
					case 5:{
						System.out.println("Going Back to Main Menu.");
						System.out.println("------------------------------");
						Cmenu=false;
						break;
					}
					}
				}while(Cmenu==true);
				break;
			}
			case 4:{
				EMI E=new EMI();
				EMIDAOImpl EDI=new EMIDAOImpl();
				boolean Cmenu=true;
				do{
					System.out.println("1. ShowUnpaidEmi");
					System.out.println("2. Pay Student EMI");
					System.out.println("3. UnPay Students EMI");
					System.out.println("4. Show All Students EMI");
					System.out.println("5. Exit to Main Menu");
					int Cchoice=s.nextInt();s.nextLine();
					System.out.println("------------------------------");
					switch(Cchoice) {
					case 1:{
						LDI.changeLogin(L, s);
						EDI.showUnpaidEMI(L, E);
						LDI.defaultLogin(L);
						break;
					}
					case 2:{
						LDI.changeLogin(L, s);
						boolean again=true;
						do {
							System.out.println("Enter Student Id(Sid) to PayEMI");
							E.setSid(s.nextInt());s.nextLine();
							System.out.println("------------------------------");
							EDI.payEMI(L, s, E);
							System.out.println("Update Another Student? Y/N");
							String choice=s.nextLine().toUpperCase().trim();
							System.out.println("------------------------------");
							if(choice.equals("N")||choice.equals("NO")) {
								again=false;
							}
						}while(again==true);
						LDI.defaultLogin(L);
						break;
					}
					case 3:{
						LDI.changeLogin(L, s);
						boolean again=true;
						do {
							System.out.println("Enter Student Id(Sid) to PayEMI");
							E.setSid(s.nextInt());s.nextLine();
							System.out.println("------------------------------");
							EDI.unPayEMI(L, s, E);
							System.out.println("Update Another Student? Y/N");
							String choice=s.nextLine().toUpperCase().trim();
							System.out.println("------------------------------");
							if(choice.equals("N")||choice.equals("NO")) {
								again=false;
							}
						}while(again==true);
						LDI.defaultLogin(L);
						break;
					}
					case 4:{
						EDI.showAllStudentEMI(L, E);
						break;
					}
					case 5:{
						System.out.println("Going Back to Main Menu.");
						System.out.println("------------------------------");
						Cmenu=false;
						break;
					}
					}
				}while(Cmenu==true);
				break;
				}
			case 5:{
				System.out.println("GoodBye :)");
				Mmenu=false;
				break;
			}
			}
					
		}while(Mmenu==true);
		s.close();
	}

}
