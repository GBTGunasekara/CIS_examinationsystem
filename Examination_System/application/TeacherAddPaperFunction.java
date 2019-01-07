package application;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TeacherAddPaperFunction extends UnicastRemoteObject implements TeacherAddPaperInterface {

	protected TeacherAddPaperFunction() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void createPaper (String teID, String clID, String paID, Date createDateTime, int noQuestions, int noAnswers) throws RemoteException
	{
		
		java.sql.Timestamp sqlcurrentDateTime = new java.sql.Timestamp(createDateTime.getTime()); //convert util datetime into sqltime date
		
		PreparedStatement ps;
		String tRegQuery = "INSERT INTO paper(paperID, teacherID, classID, numQuestion, numAnswers, createDateTime) VALUES (?,?,?,?,?,?)";
	try
	{
		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(tRegQuery);
		ps.setString(1, paID);
        ps.setString(2, teID);
        ps.setString(3, clID);
        ps.setInt(4, noQuestions);
        ps.setInt(5, noAnswers);
        ps.setTimestamp(6, sqlcurrentDateTime);
        
        
        if(ps.executeUpdate() > 0)
        {
            JOptionPane.showMessageDialog(null, "New  paper create");
           
        }
	}
	
	

 catch (SQLException ex) 
	{
	 	System.out.println(ex);
    	JOptionPane.showMessageDialog(null, "error");
 	}
	}
	
	public String paperIDgenerate () throws RemoteException
    {
        String paperID = null;
       // Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //PreparedStatement ps;
         try{
            String maxPaperid  = "select paperID from paper order by paperID desc limit 1";
            ps =  (PreparedStatement) DBconnection.Connect().prepareStatement(maxPaperid);
            rs =ps.executeQuery();
            if(rs.next())
            {
                String prePaperID = rs.getString("paperID");
                int paperIDLen = prePaperID.length();
                String lettersSet = prePaperID.substring(0,2);
                String numbersSet = prePaperID.substring(2, paperIDLen);
                int numbersSetInt = Integer.parseInt(numbersSet);
                numbersSetInt = numbersSetInt + 1;
                numbersSet = Integer.toString(numbersSetInt);
                paperID = (lettersSet + numbersSet);     
            }
            else
            {
            	paperID = "PA10000001";
            }    
        }
        catch(SQLException e10){
            JOptionPane.showMessageDialog(null, "paperID not increment");
            System.out.println(e10);
        }
        
        return paperID;
    }
	
}
