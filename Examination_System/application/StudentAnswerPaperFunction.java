package application;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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

public class StudentAnswerPaperFunction extends UnicastRemoteObject implements StudentAnswerPaperFunctionInterface{
	
	protected StudentAnswerPaperFunction() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ArrayList<String> SelectShuffleQuestionID (String paperID) throws RemoteException
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> questionIDlist = new ArrayList<String>();
  
	    try{
	           String searchPaperDetails = "select questionID from question where paperID = '"+paperID+"' order by questionNo asc"; //select all questionIDs relevant to given paperID 
  
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
	   
	   // Collections.shuffle(questionIDlist); //shuffle the array list
	    return questionIDlist; 
	}
	
	//select first question of the question paper from database and return that value
	public String selectFirstQuestion (String paperID) throws RemoteException
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
	
	public ArrayList<String> SelectShuffleAnswerID (String paperID, int Qno) throws RemoteException//first question answer shuffle
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> answerIDlist = new ArrayList<String>();
	    
		String firstquestionID = SelectShuffleQuestionID(paperID).get(Qno); //get the 1st questionID
	    
	    try{
	           String searchPaperDetails = "select answerID from answer where questionID = '"+firstquestionID+"'"; //select relevant anserIDs
	             
	             
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
	   
	    //Collections.shuffle(answerIDlist); //shuffle array list
	    return answerIDlist; // return shuffled answerID list
	}
	
	public String [] selectFirstAnswerset (String paperID) throws RemoteException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String []  answerlist =  new String[4];
		
		ArrayList<String> firstqusetionAnswerlist = SelectShuffleAnswerID(paperID,0); //initializing shuffled answerID array list 
		
		for(int i = 0; i < firstqusetionAnswerlist.size(); i++) //get answers into an array list
		{
		try{
			
	           String searchPaperDetails = "select answer from answer where answerID = '"+firstqusetionAnswerlist.get(i)+"'";
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             if(rs.next())
	             {
	            	 answerlist[i] = rs.getString(1);   //store first question answers' to answerlist[] array
	             }
	            
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper details.");
	            System.out.println(e);
	        }
		}
		return answerlist; //return 1st question's answer list
		
	}
	
	public  String [][] loadQuestionsList (String paperID, int noOfQs) throws RemoteException
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String [][]  questionlist =  new String[noOfQs][noOfQs]; //initializing array for store questionIDs and questions
		
		ArrayList<String> qusetionlist = SelectShuffleQuestionID(paperID); //initializing shuffled questionID array list 
		
		//store all questions and questionID on questionlist[][] 2D array
		for(int i = 0; i < qusetionlist.size(); i++) //get answers into an array list
		{
		try{
	           String searchPaperDetails = "select questionID, question from question where questionID = '"+qusetionlist.get(i)+"'";
  
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             if(rs.next()) 
	             {
	            	 questionlist[i][0] = rs.getString(1);
	            	 questionlist[i][1] = rs.getString(2);
	            	 
	             }
	            
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper details.");
	            System.out.println(e);
	        }
		}
		return questionlist; //return question and its' ID list
		
	}
	
	public String [][] loadAnswerlist (String paperID, int Qno) throws RemoteException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String [][]  answerlist =  new String[4][4];
		
		ArrayList<String> qusetionAnswerlist = SelectShuffleAnswerID(paperID, Qno); //initializing shuffled answerID array list and notified which answer set needs
		
		//get answers into an array list
		for(int i = 0; i < qusetionAnswerlist.size(); i++) 
		{
		try{
	           String searchPaperDetails = "select answerID, answer from answer where answerID = '"+qusetionAnswerlist.get(i)+"'";
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             if(rs.next())
	             {
	            	 answerlist[i][0] = rs.getString(1);
	            	 answerlist[i][1] = rs.getString(2);
	             }
	            
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper details.");
	            System.out.println(e);
	        }
		}
		return answerlist; //return given question's answer list
		
	}
	
	
	public void InsertStudentAnswer (String stID, String paID,String qeID, String anID) throws RemoteException
	{
		PreparedStatement ps;
		String setansQuery = "INSERT INTO studentanswer(studentID, paperID, questionID, answerID) VALUES (?,?,?,?)";
	try
	{
		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(setansQuery);
		ps.setString(1, stID);
        ps.setString(2, paID);
        ps.setString(3, qeID);
        ps.setString(4, anID);
       
        
        
        if(ps.executeUpdate() > 0)
        {
            System.out.println("Answer added to database");
        }
	}

	 catch (SQLException ex) 
		{
		 	System.out.println(ex);
	    	JOptionPane.showMessageDialog(null, "error");
	 	}
	}
	
//	public boolean checkComboAnswer (String studentID, String paperID) throws SQLException
//	{
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		String checkQuestion ="select questionID from question where studentID = '"+studentID+"' and paperID = '"+paperID+"'";
//
//		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(checkQuestion);
//		rs = ps.executeQuery();	
//		if (!rs.next()) 
//			return false;
//		else
//		    return true;     
//		
//	}
	public void  paperCountIncremant(String studentID, String classID) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int paperCount = 0;
		
		String papercountQuerry = "select noPapers from studentclass where studentID = '"+studentID+"' and classId = '"+classID+"'"; //get current paper count
        ps = (PreparedStatement) DBconnection.Connect().prepareStatement(papercountQuerry);
        rs = ps.executeQuery();
        if(rs.next())
        {
        	paperCount= rs.getInt(1);
        }
        paperCount = paperCount +1; //increment the paper count by 1
        String setpapercountQuerry = "Update studentclass set noPapers = '"+paperCount+"' where studentID = '"+studentID+"' and classId = '"+classID+"'"; //update incremented paper count by 1
        ps = (PreparedStatement) DBconnection.Connect().prepareStatement(setpapercountQuerry);
        ps.execute();
        
	}

	

	
}
