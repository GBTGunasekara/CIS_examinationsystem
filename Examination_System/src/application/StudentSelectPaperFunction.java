package application;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class StudentSelectPaperFunction {
	
	
	
	public boolean paperIDcheck (String paperID) throws SQLException
    {
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        String checkpaperID ="select paperID from paper where paperID = ?";

         ps = (PreparedStatement) DBconnection.Connect().prepareStatement(checkpaperID);
         ps.setString(1,paperID);
         rs = ps.executeQuery();
			
        }
        catch (SQLException e)
        {
            System.out.println(e);
            System.out.println("paperID not cheking");
        }
        if (!rs.next()) 
            return false;
        else
            return true;
        
    }
	
	public boolean paperPasswordcheck (String paperPassword, String paperID) throws SQLException
    {
		PreparedStatement ps = null;
		ResultSet rs = null;
        try {
        String checkpaperPassword ="select * from paper where paperID = '"+paperID+"' and pePassword = '"+paperPassword+"'";

         ps = (PreparedStatement) DBconnection.Connect().prepareStatement(checkpaperPassword);
         rs = ps.executeQuery();
			
        }
        catch (SQLException e)
        {
            System.out.println(e);
            System.out.println("paper password not cheking");
        }
        if (!rs.next()) 
            return false;
        else
            return true;
        
    }
	
	
	public String[] enrollPaper (String paperID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		
	    String classID = null;
	    String teacherID= null;
	    int noQuestions= 0;
	    int noAnswers= 0;
	    Date releaseDate =null;
	    Time releaseTime = null;
	    Date TerminateDate =null;
	    Time TerminateTime = null;
	    
	    
	    try{
	           String searchPaperDetails = "select classID, teacherID, numQuestion, numAnswers, releaseDate, releaseTime, terminateDate, terminateTime  from paper where paperID = '"+paperID+"'";
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             if(rs.next())
	             {
	            	 classID= rs.getString("classID");
	            	 teacherID= rs.getString("teacherID");
	            	 noQuestions= rs.getInt("numQuestion");
	            	 noAnswers= rs.getInt("numAnswers");
	            	 releaseDate= rs.getDate("releaseDate");
	            	 releaseTime= rs.getTime("releaseTime");
	            	 TerminateDate= rs.getDate("terminateDate");
	            	 TerminateTime= rs.getTime("terminateTime");
	             }
	             
	      
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper details.");
	            System.out.println(e);
	        }
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    DateFormat tf = new SimpleDateFormat("HH:mm:ss");
	    String releaseDatestr = df.format(releaseDate);
	    String releaseTimestr = tf.format(releaseTime);
	    String TerminateDatestr = df.format(TerminateDate);
	    String TerminateTimestr = tf.format(TerminateTime);
	    
	             String[] paperdetarr = new String[] {classID,teacherID,String.valueOf(noQuestions),String.valueOf(noAnswers),releaseDatestr, releaseTimestr, TerminateDatestr,TerminateTimestr};
	             return paperdetarr;
	}
	
}
