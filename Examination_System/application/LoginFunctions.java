package application;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class LoginFunctions extends UnicastRemoteObject implements LoginFunctionsInterface{
	
	protected LoginFunctions() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String CheckUSercategory (String userID) throws RemoteException
	{
		
        String lettersSet = userID.substring(0,3);
        String UserCat = null;
        
   
        if (lettersSet.equals("SID"))
        {
        	 UserCat = "student";
        }
        else if (lettersSet.equals("TID"))
        {
        	 UserCat ="teacher";
        }
        else if (lettersSet.equals("AID"))
        {
        	 UserCat = "admin";
        }
        else
        {
        	UserCat = "invalid";
        	
        }
        
       
		return UserCat;
    
	}

	public boolean userIDcheck (String userID) throws RemoteException, SQLException
    {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if (CheckUSercategory(userID).equals("student")) //checking studentID
		{
			try {
		        String checkstudentID ="select studentID from student where studentID = ?";

		         ps = (PreparedStatement) DBconnection.Connect().prepareStatement(checkstudentID);
		         ps.setString(1,userID);
		         rs = ps.executeQuery();	
		        }
		        catch (SQLException e)
		        {
		            System.out.println(e);
		            System.out.println("studentID not cheking");
		        }
		}
		else if (CheckUSercategory(userID).equals("teacher"))//checking teacehrID
		{
			try {
		        String checkteacherID ="select teacherID from teacher where teacherID = ?";

		         ps = (PreparedStatement) DBconnection.Connect().prepareStatement(checkteacherID );
		         ps.setString(1,userID);
		         rs = ps.executeQuery();	
		        }
		        catch (SQLException e)
		        {
		            System.out.println(e);
		            System.out.println("teacherID not cheking");
		        }
			
		}
		else if (CheckUSercategory(userID).equals("admin")) // checking adminID
		{
			try {
		        String checkadminID ="select adminID from admin where adminID = ?";

		         ps = (PreparedStatement) DBconnection.Connect().prepareStatement(checkadminID );
		         ps.setString(1,userID);
		         rs = ps.executeQuery();	
		        }
		        catch (SQLException e)
		        {
		            System.out.println(e);
		            System.out.println("adminID  not cheking");
		        }
			
		}
		else
		{
			return false;
		}
		
        if (!rs.next()) 
            return false;
        else
            return true;
        
    }
	
	public boolean userPasswordcheck (String userPassword, String userID) throws RemoteException, SQLException
    {
		PreparedStatement ps = null;
		ResultSet rs = null;
        
		if (CheckUSercategory(userID).equals("student")) //checking studentID
		{
			try {
		        String checkpaperPassword ="select * from student where studentID = '"+userID+"' and stPassword = '"+userPassword+"'";

		         ps = (PreparedStatement) DBconnection.Connect().prepareStatement(checkpaperPassword);
		         rs = ps.executeQuery();
					
		        }
		        catch (SQLException e)
		        {
		            System.out.println(e);
		            System.out.println("paper password not cheking");
		        }
		}
		else if (CheckUSercategory(userID).equals("teacher"))//checking teacehrID
		{
			try {
		        String checkpaperPassword ="select * from teacher where teacherID = '"+userID+"' and tePassword = '"+userPassword+"'";

		         ps = (PreparedStatement) DBconnection.Connect().prepareStatement(checkpaperPassword);
		         rs = ps.executeQuery();
					
		        }
		        catch (SQLException e)
		        {
		            System.out.println(e);
		            System.out.println("teacher password not cheking");
		        }
			
		}
		else if (CheckUSercategory(userID).equals("admin")) // checking adminID
		{
			try {
		        String checkpaperPassword ="select * from admin where adminID = '"+userID+"' and adPassword = '"+userPassword+"'";

		         ps = (PreparedStatement) DBconnection.Connect().prepareStatement(checkpaperPassword);
		         rs = ps.executeQuery();
					
		        }
		        catch (SQLException e)
		        {
		            System.out.println(e);
		            System.out.println("admin password not cheking");
		        }
			
		}
		else {
			return false;
		}
		
		
        if (!rs.next()) 
            return false;
        else
            return true;
        
    }
}
