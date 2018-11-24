package application;

import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class TeacherAddPaperFunction {

	public void createPaper (String teID, String clID, String paID, Date createDateTime, int noQuestions, int noAnswers)
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
}
