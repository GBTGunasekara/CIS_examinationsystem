package application;

import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class TeacherAddQuestionsFunction {
	
	
	String Answer =null;
	String AnsNo = null;
	String AnsStatus = null;
	PreparedStatement ps;
	
	
	
	public void SaveNextQuestion (String paID, String qeID , String Question, String AnswerA, String AnswerB, String AnswerC, String AnswerD, int QeNo, String correctAns)
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
            //JOptionPane.showMessageDialog(null, "New User Add");
        
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
}
