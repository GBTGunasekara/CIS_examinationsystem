package application;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class TeacherPapersListFunction {

	
	public int paperCount (String teacherID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int papercount = 0;
		
		try
		{
			String papercountquery = "SELECT count(*)  from paper where teacherID = '"+teacherID+"'";
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(papercountquery );
            rs = ps.executeQuery();
            if(rs.next())
            {
            	papercount = rs.getInt(1);
           	 
            }
		}
		 catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper count.");
	            System.out.println(e);
	        }
		return papercount;
	}
	public String[][] selcetPaperList (String teacherID) throws RemoteException
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		int papercount = paperCount(teacherID); // get paper count according to teacher (selcet count(*)  from paper where teacherID = '"teacherID"')
		String [][] paperlist = new String[papercount][6];
	    
	    
		for(int i = 0; i < papercount; i++) //get answers into an array list
		{
	    try{
	           String showPaperDetails = "select paperID,pePassword,classID,numQuestion,numAnswers,createDateTime from paper where teacherID = '"+teacherID+"'"; //select all paper details relevant to login teacher
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(showPaperDetails);
	             rs = ps.executeQuery();
	             
	             while (rs.next()) { //store these IDs in an array list 
	            	 paperlist[i][0] = rs.getString(1);
	            	 paperlist[i][1] = rs.getString(2);
	            	 paperlist[i][2] = rs.getString(3);
	            	 paperlist[i][3] = rs.getString(4);
	            	 paperlist[i][4] = rs.getString(5);
	            	 paperlist[i][5] = rs.getString(6);
	             } 
	             
	      
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper details.");
	            System.out.println(e);
	        }
		}
	   
	   // Collections.shuffle(questionIDlist); //shuffle the array list
	    return paperlist; 
	}
}
