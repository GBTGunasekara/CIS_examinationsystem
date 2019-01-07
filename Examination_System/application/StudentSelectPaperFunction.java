package application;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class StudentSelectPaperFunction extends UnicastRemoteObject implements StudentSelectPaperInterface{
	
	
	
	protected StudentSelectPaperFunction() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public boolean paperIDcheck (String paperID) throws RemoteException, SQLException
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
	
	public boolean paperPasswordcheck (String paperPassword, String paperID) throws RemoteException, SQLException
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
	
	
	public String[] enrollPaper (String paperID) throws RemoteException
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
	
	public String timeChecker (Date currentDate, Date currentTime,  Date releaseDate, Date releaseTime, Date terminateDate, Date terminateTime)
	{
		String status = null;
	    
	    if (currentDate.compareTo(releaseDate) > 0 && currentDate.compareTo(terminateDate) < 0 )
	    {
	    	status = "now";
	    }

	    else if (currentDate.compareTo(releaseDate) == 0 && currentDate.compareTo(terminateDate) < 0)
	    {
	    	if(currentTime.compareTo(releaseTime) >= 0)
	    	{
	    		status = "now";
	    	}
	    	else
	    	{
	    		status = "before";
	    	}
	    }
	    else if (currentDate.compareTo(releaseDate) > 0 && currentDate.compareTo(terminateDate) == 0)
	    {
	    	if(currentTime.compareTo(terminateTime) <= 0)
	    	{
	    		status = "now";
	    	}
	    	else
	    	{
	    		status = "after";
	    	}
	    }
	    else if (currentDate.compareTo(releaseDate) == 0 && currentDate.compareTo(terminateDate) == 0)
	    {
	    	if(currentTime.compareTo(releaseTime) >= 0 && currentTime.compareTo(terminateTime) <= 0)
	    	{
	    		status = "now";
	    	}
	    	else if (currentTime.compareTo(releaseTime) < 0 && currentTime.compareTo(terminateTime) < 0) 
		    {
		    	status = "before";
	        } 
		    else if (currentTime.compareTo(releaseTime) > 0 && currentTime.compareTo(terminateTime) > 0) 
		    {
		    	status = "after";
	        } 
	    }
	   
	    else if (currentDate.compareTo(releaseDate) < 0 && currentDate.compareTo(terminateDate) < 0) 
	    {
	    	status = "before";
        } 
	    else if (currentDate.compareTo(terminateDate) > 0 && currentDate.compareTo(terminateDate) > 0) 
	    {
	    	status = "after";
        } 
	    else 
	    {
            System.out.println("error");
        }
	    return status;
	}
	public boolean checkStudent (String studentID, String paperID) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String checkstudent ="select studentID from studentanswer where studentID = ? and paperID = '"+paperID+"'";

		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(checkstudent);
		ps.setString(1,studentID);
		rs = ps.executeQuery();	
		if (!rs.next()) 
			return false;
		else
		    return true;     
		
	}
}
