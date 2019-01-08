package application;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class TeacherViewQuestionsFunction {
	
	public ArrayList<String> SelectQuestionIDList (String paperID) throws RemoteException
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
	            JOptionPane.showMessageDialog(null, "unable to select QuestionID List.");
	            System.out.println(e);
	        }

	    return questionIDlist; 
	}
	
	public ArrayList<String> SelectAnswerIDList (String paperID, int Qno) throws RemoteException//first question answer shuffle
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> answerIDlist = new ArrayList<String>();
	    
		String firstquestionID = SelectQuestionIDList(paperID).get(Qno); //get the 1st questionID
	    
	    try{
	           String searchPaperDetails = "select answerID from answer where questionID = '"+firstquestionID+"'"; //select relevant anserIDs
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             
	             while (rs.next()) {
	            	 answerIDlist.add(rs.getString(1));
	             } 
	             
	      
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select AnswerID List.");
	            System.out.println(e);
	        }

	    return answerIDlist; // return answerID list
	}
	
	public  String [][] loadQuestionsList (String paperID, int noOfQs) throws RemoteException
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String [][]  questionlist =  new String[noOfQs][noOfQs]; //initializing array for store questionIDs and questions
		
		ArrayList<String> qusetionlist = SelectQuestionIDList(paperID); //initializing shuffled questionID array list 
		
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
	            JOptionPane.showMessageDialog(null, "unable to load Questions List.");
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
		
		ArrayList<String> qusetionAnswerlist = SelectAnswerIDList(paperID, Qno); //initializing shuffled answerID array list and notified which answer set needs
		
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
	            JOptionPane.showMessageDialog(null, "unable to load answer list.");
	            System.out.println(e);
	        }
		}
		return answerlist; //return given question's answer list
		
	}
}
