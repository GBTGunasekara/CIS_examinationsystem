package application;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class DBconnection {
	
	public static Connection Connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dbexamsystem","root","");	
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		
		return con;
		
	}
	
}
