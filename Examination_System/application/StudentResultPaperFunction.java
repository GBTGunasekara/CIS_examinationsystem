package application;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class StudentResultPaperFunction extends UnicastRemoteObject implements StudentResultPaperInterface{

	protected StudentResultPaperFunction() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int resultCalculate (String paperID, String StudentID ) throws RemoteException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try{
	 //the below SQL quarry counts number of correct answer by checking student provided answers' status through answerID. if answer status is "Correct" then it will count as one correct answer. 
				String status = "Correct";
	            String searchPaperDetails = "select count(*) from answer a, studentanswer s where a.answerID = s.answerID and s.studentID = '"+StudentID+"' and s.paperID ='"+paperID+"' and a.ansStatus = '"+status+"'";
	             
	             
	             ps = (PreparedStatement) DBconnection.Connect().prepareStatement(searchPaperDetails);
	             rs = ps.executeQuery();
	             if(rs.next())
	             {
	            	 result = rs.getInt(1); 
	             }
	            
	    }
	    catch (SQLException e){
	            JOptionPane.showMessageDialog(null, "unable count correct answers");
	            System.out.println(e);
	        }
		return result; // return the count of correct answers
		
	}
	
}
