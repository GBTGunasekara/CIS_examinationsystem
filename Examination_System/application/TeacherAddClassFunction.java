package application;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
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

	public void createClass (String teID, String clID, String subName, int grade, String clName, String location) {
		
		PreparedStatement ps;
		String adClsQuery = "INSERT INTO class (classID, teacherID, className, subjectName, grade, location) VALUES (?, ?, ?, ?, ?, ?)";
		try
		{
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(adClsQuery);
			ps.setString(1, clID);
	        ps.setString(2, teID);
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
	
	public String classIDgenerate () throws RemoteException
    {
        String classID = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
         try{
            String maxClassid  = "select classID from class order by classID desc limit 1";
            ps =  (PreparedStatement) DBconnection.Connect().prepareStatement(maxClassid);
            rs =ps.executeQuery();
            if(rs.next())
            {
                String preClassID = rs.getString("classID");
                int classIDLen = preClassID.length();
                String lettersSet = preClassID.substring(0,2);
                String numbersSet = preClassID.substring(2, classIDLen);
                int numbersSetInt = Integer.parseInt(numbersSet);
                numbersSetInt = numbersSetInt + 1;
                numbersSet = Integer.toString(numbersSetInt);
                classID = (lettersSet + numbersSet);     
            }
            else
            {
            	classID = "CL001";
            }    
        }
        catch(SQLException e10){
            JOptionPane.showMessageDialog(null, "classID not increment");
            System.out.println(e10);
        }
        
        return classID;
    }
	
	public void clear(int teID, int clID, String subName, int grade, String clName, String location){
		
	
	}

}