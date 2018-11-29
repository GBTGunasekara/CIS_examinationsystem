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
	
	public ArrayList<String> SelectShuffleQuestionID (String paperID)
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> questionIDlist = new ArrayList<String>();
	    
	    
	    
	    try{
	           String searchPaperDetails = "select questionID from question where paperID = '"+paperID+"'"; //select all questionIDs relevant to given paperID 
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             
	             while (rs.next()) {
	            	 questionIDlist.add(rs.getString(1)); //store these IDs in an array list 
	             } 
	             
	      
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper details.");
	            System.out.println(e);
	        }
	   
	    Collections.shuffle(questionIDlist); //shuffle the array list
	    return questionIDlist; 
	}
	
	public String selectFirstQuestion (String paperID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String  question =  null;
		
		String firstquestionID = SelectShuffleQuestionID(paperID).get(0); //initializing first index of shuffled questionIDlist array list 
		try{
			
	           String searchPaperDetails = "select question from question where questionID = '"+firstquestionID+"'"; //select first question 
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             if(rs.next())
	             {
	            	  question= rs.getString("question");
	             }
	            
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper details.");
	            System.out.println(e);
	        }
		return question; //return 1st question
		
	}
	
	public ArrayList<String> SelectShuffleAnswerID (String paperID)
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> answerIDlist = new ArrayList<String>();
	    
		String firstquestionID = SelectShuffleQuestionID(paperID).get(0); //get the 1st questionID
	    
	    try{
	           String searchPaperDetails = "select questionID from question where paperID = '"+firstquestionID+"'"; //select relevant anserIDs
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             
	             while (rs.next()) {
	            	 answerIDlist.add(rs.getString(1));
	             } 
	             
	      
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper details.");
	            System.out.println(e);
	        }
	   
	    Collections.shuffle(answerIDlist); //shuffle array list
	    return answerIDlist; // return shuffled answerID list
	}
	
	public ArrayList<String> selectFirstAnswerset (String paperID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String>  answerlist =  null;
		
		ArrayList<String> firstqusetionAnswerlist = SelectShuffleAnswerID(paperID); //initializing shuffled answerID array list 
		
		for(int i = 0; i < firstqusetionAnswerlist.size(); i++) //get answers into an array list
		{
		try{
			
	           String searchPaperDetails = "select answer from answer where answerID = '"+firstqusetionAnswerlist.get(i)+"'";
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             if(rs.next())
	             {
	            	 answerlist.add(rs.getString(1));
	             }
	            
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper details.");
	            System.out.println(e);
	        }
		}
		return answerlist; //return 1st question's answer list
		
	}
	
}
