package application;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeacherViewResultsFunction {



	//Reference (You Tube video)
	// B2 Tech (2017).JavaFx Database Operations - Part 8 - Display the DB values in TableView. [Video] Available at: https://www.youtube.com/watch?v=L8iuBXl-F8U [Accessed Day : 17-12-2018].

	public static ObservableList  selcetPaperResultsList (String paperID) throws RemoteException, SQLException
	{
			
		PreparedStatement ps = null;
		ResultSet rs = null;
		String showResultsDetails = "select r.studentID,s.stName,r.Marks,r.ansDateTime from student s , result r where s.studentID = r.studentID and r.paperID = '"+paperID+"'"; //select all paper details relevant to login teacher
	        
		ObservableList resultslist = FXCollections.observableArrayList();
		
	    ps = (PreparedStatement) DBconnection.Connect().prepareStatement(showResultsDetails);
        rs = ps.executeQuery();
        while (rs.next())
	    {
        	TeacherViewResultsTable tvrt = new TeacherViewResultsTable(); 
        	tvrt.setStudentID(rs.getString("studentID"));
        	tvrt.setstudentName(rs.getString("stName"));
        	tvrt.setMarks(rs.getString("Marks"));
        	tvrt.setAnsweredTime(rs.getString("ansDateTime"));

        	resultslist.add(tvrt);
	        }
			return resultslist;
		} 
	//Reference (You Tube video) over
	
	
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

	public String[] paperDetails (String paperID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		//String[] paperDetailsArr = new String[6];
		String classID = null;
	    String teacherID= null;
	    int noQuestions= 0;
	    Date releaseDate =null;
	    Time releaseTime = null;
	    Date TerminateDate =null;
	    Time TerminateTime = null;
	    
		
		 try{
	           String searchPaperDetails = "select paperID, teacherID, classID, numQuestion, releaseDate, releaseTime, terminateDate, terminateTime  from paper where paperID = '"+paperID+"'";
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             if(rs.next())
	             {
	            	 
	            	 classID= rs.getString("classID");
	            	 teacherID= rs.getString("teacherID");
	            	 noQuestions= rs.getInt("numQuestion");
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
	    String releaseDateTime = releaseDatestr +" " + releaseTimestr;
	    
	    String TerminateDatestr = df.format(TerminateDate);
	    String TerminateTimestr = tf.format(TerminateTime);
	    String terminateDateTime = TerminateDatestr +" " + TerminateTimestr;
	    
	    String paperDetailsArr[] =  {paperID,classID,teacherID,String.valueOf(noQuestions),releaseDateTime, terminateDateTime};
	    return paperDetailsArr;
	
	}
	
	public int getRowCount (String paperID) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rawCount = 0;
		String rawCountquerry = "select count(*) from student s , result r where s.studentID = r.studentID and r.paperID = '"+paperID+"'";
		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(rawCountquerry);
        rs = ps.executeQuery();
        while (rs.next())
	    {
        	rawCount = rs.getInt(1);
	    }
		
		return rawCount;
	}
	
	public String[][] printResultList (String paperID) throws RemoteException, SQLException
	{
			
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rawCount = getRowCount(paperID);
		
		String [][] resultsArray = new String[rawCount][4];
		String showResultsDetails = "select r.studentID,s.stName,r.Marks,r.ansDateTime from student s , result r where s.studentID = r.studentID and r.paperID = '"+paperID+"'"; 
		
		
	    ps = (PreparedStatement) DBconnection.Connect().prepareStatement(showResultsDetails);
        rs = ps.executeQuery();
        int i =0; 
        while (rs.next())
	    {
        	
        	resultsArray[i][0] = rs.getString(1);
        	resultsArray[i][1] = rs.getString(2);
        	resultsArray[i][2] = rs.getString(3);
        	resultsArray[i][3] = rs.getString(4);
        	
        	i=i+1;

        	
	        }
			return resultsArray;
		} 
}
