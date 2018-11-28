package application;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class TeacherSubmitPaperFunction {
	
	public void submitPaper (String paID, String paperPassword, LocalDate releaseDate, LocalTime releaseTime, LocalDate TerminateDate, LocalTime TerminateTime)
	{
		
		//java.sql.Timestamp sqlcurrentDateTime = new java.sql.Timestamp(createDateTime.getTime()); //convert util datetime into sqltime date
		
		
	if  (releaseDate == null)
	{
			JOptionPane.showMessageDialog(null, "Insert Release Date");
	}
	else if  (releaseTime == null)
	{
			JOptionPane.showMessageDialog(null, "Insert Release Time");
	}	
	else if  (TerminateDate == null)
	{
			JOptionPane.showMessageDialog(null, "Insert Terminate Date");
	}	
	else if  (TerminateTime == null)
	{
			JOptionPane.showMessageDialog(null, "Insert Terminate Time");
	}	
	else
	{
		try
		{
			Date rdate = java.sql.Date.valueOf(releaseDate);
			Time  rtime = java.sql.Time.valueOf(releaseTime);
			Date Tdate = java.sql.Date.valueOf(TerminateDate);
			Time  Ttime = java.sql.Time.valueOf(TerminateTime);
		
			PreparedStatement ps;
		
			String submitpaper = "update paper set pePassword = '"+ paperPassword +"', releaseDate = '"+ rdate +"', releaseTime = '"+ rtime +"', terminateDate = '"+ Tdate +"', terminateTime = '"+ Ttime +"' where paperID = '"+ paID +"'";
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
	// reference - https://youtu.be/PBAX8r9YWW0
	
	public String GeneratePassword ()
	{
		String password = "";
		
		for(int i = 0; i < 8; i++)
		{
			password = password + randomCharacter("abcdefghijklmnopqrstuwxyz");
			
		}
		String randomDigit = randomCharacter("0123456789");
		password = insertAtRandom(password, randomDigit);
		return password;
	}


	public String randomCharacter(String charachters) {
		int n = charachters.length();
		int r = (int) (n* Math.random());
		return charachters.substring(r, r+1);
	}
	
	public String insertAtRandom (String str, String toInsert)
	{
		int n = str.length();
		int r = (int)((n+1) * Math.random());
		return str.substring(0,r) + toInsert + str.substring(r);
	}
	
}
