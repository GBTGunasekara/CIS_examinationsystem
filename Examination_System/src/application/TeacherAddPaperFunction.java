package application;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
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
	
	public String paperIDgenerate ()
    {
        String paperID = null;
       // Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //PreparedStatement ps;
         try{
            String max_paperid  = "select paperID from paper order by paperID desc limit 1";
            ps =  (PreparedStatement) DBconnection.Connect().prepareStatement(max_paperid);
            rs =ps.executeQuery();
            if(rs.next())
            {
                String papernum = rs.getString("paperID");
                int papernum_len = papernum.length();
                String letters_set = papernum.substring(0,2);
                String numbers_set = papernum.substring(2, papernum_len);
                int numbers_set_int = Integer.parseInt(numbers_set);
                numbers_set_int = numbers_set_int + 1;
                numbers_set = Integer.toString(numbers_set_int);
                paperID = (letters_set + numbers_set);     
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
