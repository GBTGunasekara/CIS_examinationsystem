package application;

import java.awt.HeadlessException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class TeacherAddQuestionsFunction extends UnicastRemoteObject implements TeacherAddQuestionsFunctionInterface{
	
	
	protected TeacherAddQuestionsFunction() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Answer =null;
	String AnsNo = null;
	String AnsStatus = null;
	PreparedStatement ps;
	
	//save and move onto question
	//update question
	public void SaveNextQuestion (String paID, String qeID , String Question, String AnswerA, String AnswerB, String AnswerC, String AnswerD, int QeNo, String correctAns) throws RemoteException, HeadlessException, SQLException
	{
		if(Question.equals("")) 
		{
			JOptionPane.showMessageDialog(null, "Question is not entered");
		}
		else if(AnswerA.equals("")) 
		{
			JOptionPane.showMessageDialog(null, "Answer A is not completed");
		}
		else if(AnswerB.equals("")) 
		{
			JOptionPane.showMessageDialog(null, "Answer B is not completed");
		}
		else if(AnswerC.equals("")) 
		{
			JOptionPane.showMessageDialog(null, "Answer C is not completed");
		}
		else if(AnswerD.equals("")) 
		{
			JOptionPane.showMessageDialog(null, "Answer D is not completed");
		}
		
		else 
		{
			if (checkComboQuestion(QeNo,paID) == false)
			{
				String insertQuestionQuery = "INSERT INTO question(questionID, paperID, question, questionNo) VALUES (?,?,?,?)";
		
			//UPDATE `question` SET `question`= "new question" WHERE questionID= "PA100000031" // update question quarry
	try
	{
		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(insertQuestionQuery);
		ps.setString(1, qeID);
        ps.setString(2, paID);
        ps.setString(3, Question);
        ps.setInt(4, QeNo);

        
        if(ps.executeUpdate() > 0)
        {
        
        	for (int i= 1; i<= 4; i++)
        	{
        		if(i==1)
        		{
        			Answer = AnswerA;
        			AnsNo = "A";
        			if (correctAns.equals("A"))
        				AnsStatus = "Correct";
        			else
        				AnsStatus = "Wrong";
        		}
        		
        		else if(i==2)
        		{
        			Answer = AnswerB;
        			AnsNo = "B";
        			if (correctAns.equals("B"))
        				AnsStatus = "Correct";
        			else 
        				AnsStatus = "Wrong";
        		}
        		
        		else if(i==3)
        		{
        			Answer = AnswerC;
        			AnsNo = "C";
        			if (correctAns.equals("C"))
        				AnsStatus = "Correct";
        			else
        				AnsStatus = "Wrong";
        		}
        		else if(i==4)
        		{
        			Answer = AnswerD;
        			AnsNo = "D";
        			if (correctAns.equals("D"))
        				AnsStatus = "Correct";  
        			else
        				AnsStatus = "Wrong";
        		}
        		
        		String answerID =  qeID +  String.valueOf(i);
        		String insertAnsQuery = "INSERT INTO answer( answerID, questionID , answerNo, answer, ansStatus) VALUES (?,?,?,?,?)";

        		try
        		{
        			PreparedStatement ps2 =null;
        			ps2 = (PreparedStatement) DBconnection.Connect().prepareStatement(insertAnsQuery);
        			ps2.setString(1, answerID );
        			ps2.setString(2, qeID);
        			ps2.setString(3, AnsNo);
        			ps2.setString(4, Answer);
        			ps2.setString(5, AnsStatus);
        	        
        			if(ps2.executeUpdate() > 0)
                    {
        				System.out.println("succesfully answers added");
                    }
        		}
        		catch (SQLException ex) 
        		{
        		 	System.out.println(ex);
        	    	JOptionPane.showMessageDialog(null, "Answers not added");
        	 	}
        		
        	} //insert answers for loop over
        	 
        	}
  
	}
	catch (SQLException ex) 
	{
	 	System.out.println(ex);
    	JOptionPane.showMessageDialog(null, "Question not added");
 	}
	}// insert questions and answers process over
			
			//update questions and answers
			else 
			{
				String updateQuestionQuery = "UPDATE question SET question = '"+Question+"' WHERE questionID= '"+qeID+"'";

		try
		{
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(updateQuestionQuery);
			ps.execute(); 
			/*ps.setString(1, qeID);
	        ps.setString(2, paID);
	        ps.setString(3, Question);
	        ps.setInt(4, QeNo);*/
			
			//update answers
	        if(ps.executeUpdate() > 0)
	        {
	        
	        	for (int i= 1; i<= 4; i++)
	        	{
	        		if(i==1)
	        		{
	        			Answer = AnswerA;
	        			AnsNo = "A";
	        			if (correctAns.equals("A"))
	        				AnsStatus = "Correct";
	        			else
	        				AnsStatus = "Wrong";
	        		}
	        		
	        		else if(i==2)
	        		{
	        			Answer = AnswerB;
	        			AnsNo = "B";
	        			if (correctAns.equals("B"))
	        				AnsStatus = "Correct";
	        			else 
	        				AnsStatus = "Wrong";
	        		}
	        		
	        		else if(i==3)
	        		{
	        			Answer = AnswerC;
	        			AnsNo = "C";
	        			if (correctAns.equals("C"))
	        				AnsStatus = "Correct";
	        			else
	        				AnsStatus = "Wrong";
	        		}
	        		else if(i==4)
	        		{
	        			Answer = AnswerD;
	        			AnsNo = "D";
	        			if (correctAns.equals("D"))
	        				AnsStatus = "Correct";  
	        			else
	        				AnsStatus = "Wrong";
	        		}
	        		
	        		String answerID =  qeID +  String.valueOf(i);
	 
	        		String updateAnsQuery = "UPDATE answer SET answer= '"+Answer+"' ,ansStatus= '"+AnsStatus+"' WHERE answerNo = '"+AnsNo+"' and questionID = '"+qeID+"'";
	        		
	        		try
	        		{
	        			PreparedStatement ps2 =null;
	        			ps2 = (PreparedStatement) DBconnection.Connect().prepareStatement(updateAnsQuery);
	        			ps2.execute(); 
	        			/*ps2.setString(1, answerID );
	        			ps2.setString(2, qeID);
	        			ps2.setString(3, AnsNo);
	        			ps2.setString(4, Answer);
	        			ps2.setString(5, AnsStatus);*/
	        	        
	        			if(ps2.executeUpdate() > 0)
	                    {
	        				System.out.println("succesfully answers updated");
	                    }
	        		}
	        		catch (SQLException ex) 
	        		{
	        		 	System.out.println(ex);
	        	    	JOptionPane.showMessageDialog(null, "Answers not added");
	        	 	}
	        		
	        	}// update answer for loop over
	        	 
	        	}
	  
		}
		catch (SQLException ex) 
		{
		 	System.out.println(ex);
	    	JOptionPane.showMessageDialog(null, "Question not added");
	 	}
			}// update question for loop over
	}
	
		
	
	}
	
	//this method check combo box selected question (number) is already in the database
	public boolean checkComboQuestion (int questionNo, String paperID) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String checkQuestion ="select questionID from question where paperID = '"+paperID+"' and questionNo = ?";

		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(checkQuestion);
		ps.setInt(1,questionNo); //
		rs = ps.executeQuery();	
		if (!rs.next()) 
			return false;
		else
		    return true;     
		
	}
	
	//select combo box selected question number's question
	public String[] selectComboQuestion (String questionNo, String paperID) throws RemoteException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[]  question =  new String[2] ;

		try{		
	           String searchPaperDetails = "select questionID, question from question where paperID = '"+paperID+"' and questionNo = '"+questionNo+"'"; //select question 
  
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             if(rs.next())
	             {
	            	  question[0]= rs.getString("questionID");
	            	  question[1]= rs.getString("question");
	             }
	            
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select combo box selected question.");
	            System.out.println(e);
	        }
		return question; //return 1st question
		
	}
	
	//select combo box selected question's answer set
	public String [] selectFirstAnswerset (String questionID) throws RemoteException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String []  answerlist =  new String[4];
		
		for(int i = 0; i < 4; i++) //get answers into an array list
		{
		try{
			
	           String searchPaperDetails = "select answer from answer where questionID = '"+questionID+"'";
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             if(rs.next())
	             {
	            	 answerlist[i] = rs.getString(1);
	             }
	            
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable to select paper details.");
	            System.out.println(e);
	        }
		}
		return answerlist; //return selected question's answer list
		
	}
	
	
}
