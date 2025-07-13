package DAO;

import java.sql.Connection;
import java.util.Scanner;

import POJO.Login;

public interface LoginDAO {

	public Connection changeLogin(Login L, Scanner s);
	public Connection defaultLogin(Login L);
	
}
