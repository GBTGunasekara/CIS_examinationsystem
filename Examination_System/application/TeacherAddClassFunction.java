package application;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class TeacherAddClassFunction extends UnicastRemoteObject implements TeacherAddClassFunctionInterface {
	
	protected TeacherAddClassFunction() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void createClass (int teID, int clID, String subName, int grade, String clName, String location) {
		
		PreparedStatement ps;
		String adClsQuery = "INSERT INTO class (classID, teacherID, className, subjectName, grade, location) VALUES (?, ?, ?, ?, ?, ?)";
		try
		{
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(adClsQuery);
			ps.setInt(1, clID);
	        ps.setInt(2, teID);
	        ps.setString(3, subName);
	        ps.setString(4, clName);
	        ps.setInt(5, grade);
	        ps.setString(6, location);
	        
	        
	        if(ps.executeUpdate() > 0)
	        {
	            JOptionPane.showMessageDialog(null, "New  class created");
	           
	        }
		}
		
		

	 catch (SQLException ex) 
		{
		 	System.out.println(ex);
	    	JOptionPane.showMessageDialog(null, "error\n" + ex);
	 	}
		
	}
	
	public void clear(int teID, int clID, String subName, int grade, String clName, String location){
		
	
	}

}