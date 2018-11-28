package application;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class TeacherSubmitPaperFunction {
	
	public void submitPaper (String paID, LocalDate releaseDate, LocalTime releaseTime, LocalDate TerminateDate, LocalTime TerminateTime)
	{
		
		//java.sql.Timestamp sqlcurrentDateTime = new java.sql.Timestamp(createDateTime.getTime()); //convert util datetime into sqltime date
		
		Date rdate = java.sql.Date.valueOf(releaseDate);
		Time  rtime = java.sql.Time.valueOf(releaseTime);
		Date Tdate = java.sql.Date.valueOf(TerminateDate);
		Time  Ttime = java.sql.Time.valueOf(TerminateTime);
		
		PreparedStatement ps;
		
	try
	{
		String submitpaper = "update paper set releaseDate = '"+ rdate +"', releaseTime = '"+ rtime +"', terminateDate = '"+ Tdate +"', terminateTime = '"+ Ttime +"' where paperID = '"+ paID +"'";
		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(submitpaper);
		ps.execute();
        
        
        if(ps.executeUpdate() > 0)
        {
            JOptionPane.showMessageDialog(null, "paper is submited");
           
        }
	}
	
	

 catch (SQLException ex) 
	{
	 	System.out.println(ex);
    	JOptionPane.showMessageDialog(null, "error");
 	}
	}
	
}
