package application;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class StudentAnswerPaperFunction {
	
	public ArrayList<String> SelectQuestionID (String paperID)
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> questionIDlist = new ArrayList<String>();
	    
	    
	    
	    try{
	           String searchPaperDetails = "select questionID from question where paperID = '"+paperID+"'";
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             
	             while (rs.next()) {
	            	 questionIDlist.add(rs.getString(1));
	             } 
	             
	      
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper details.");
	            System.out.println(e);
	        }
	   
	    Collections.shuffle(questionIDlist); //shuffle arraylist
	    return questionIDlist;
	}
	
}
