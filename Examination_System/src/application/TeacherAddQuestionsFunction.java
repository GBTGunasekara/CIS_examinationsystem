package application;

import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class TeacherAddQuestionsFunction {
	public void createPaper (String paID, String qeID, String anID , String Question, String AnswerA, String AnswerB, String AnswerC, String AnswerD, int QeNo, String correctAns)
	{
		
		String Answer =null;
		String AnsNo = null;
		String AnsStatus = null;
		PreparedStatement ps;
		String insertQuestionQuery = "INSERT INTO question(questionID, paperID, question, questionNo) VALUES (?,?,?,?)";
	try
	{
		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(insertQuestionQuery);
		ps.setString(1, qeID);
        ps.setString(2, paID);
        ps.setString(3, Question);
        ps.setInt(4, QeNo);

        if(ps.executeUpdate() > 0)
        {
        	PreparedStatement ps2;
        	
        	
        		
        	
        	
        	for (int i=1; i<= 4; i++)
        	{
        		if(i==1)
        			Answer = AnswerA;
        			AnsNo = "A";
        			if (correctAns.equals("A"))
        				AnsStatus = "Correct";
        			else
        				AnsStatus = "Wrong";
        		
        		if(i==3)
        			Answer = AnswerB;
        			AnsNo = "B";
        			if (correctAns.equals("B"))
        				AnsStatus = "Correct";
        			else
        				AnsStatus = "Wrong";
        		
        		if(i==2)
        			Answer = AnswerC;
        			AnsNo = "C";
        			if (correctAns.equals("C"))
        				AnsStatus = "Correct";
        			else
        				AnsStatus = "Wrong";
        		
        		if(i==4)
        			Answer = AnswerD;
        			AnsNo = "D";
        			if (correctAns.equals("D"))
        				AnsStatus = "Correct";
        			else
        				AnsStatus = "Wrong";
        		
        		
        	}
        	
    		String insertAnsQuery = "INSERT INTO answer(questionID, answerID, answerNo, answer,status) VALUES (?,?,?,?,?)";
    		try
    		{
    			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(insertQuestionQuery);
    			ps.setString(1, qeID);
    	        ps.setString(2, Answer);
    	        ps.setString(3, AnsNo);
    	        ps.setString(4, Answer);
    	        ps.setString(4, AnsStatus);
    	        
    		}
    		catch (SQLException ex) 
    		{
    		 	System.out.println(ex);
    	    	JOptionPane.showMessageDialog(null, "Answers not added");
    	 	}
        }
	}
	
	
	
 catch (SQLException ex) 
	{
	 	System.out.println(ex);
    	JOptionPane.showMessageDialog(null, "Question not added");
 	}
	}
}
