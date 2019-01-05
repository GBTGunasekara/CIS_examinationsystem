package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class TeacherViewResultChartFunction {
	public int getRowCount (String paperID) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rawCount = 0;
		String rawCountquerry = "select count(*) from result where paperID = '"+paperID+"'";
		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(rawCountquerry);
        rs = ps.executeQuery();
        while (rs.next())
	    {
        	rawCount = rs.getInt(1);
	    }
		
		return rawCount;
	}
	
	public double[] getMarksList (String paperID) throws SQLException
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rawCount = getRowCount(paperID);
		
		double [] resultsArray = new double[rawCount];
		String showResultsDetails = "select Marks from result where paperID = '"+paperID+"'"; 
		
		
	    ps = (PreparedStatement) DBconnection.Connect().prepareStatement(showResultsDetails);
        rs = ps.executeQuery();
        int i = 0; 
        while (rs.next())
	    {
        	resultsArray[i] = rs.getDouble(1);
        	i=i+1;
	     }
		return resultsArray;
	}
	
	public int getNumQuestions (String paperID) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int numQuestions = 0;
		
		String showResultsDetails = "select numQuestion from paper where paperID = '"+paperID+"'"; 

	    ps = (PreparedStatement) DBconnection.Connect().prepareStatement(showResultsDetails);
        rs = ps.executeQuery();

        while (rs.next())
	    {
        	numQuestions = rs.getInt(1);
	    }
		return numQuestions;
	}
	
	public int[] analyzeMarks (String paperID) throws SQLException
	{
		double marksList[] = getMarksList(paperID);
		int listSize = marksList.length;
		int questions = getNumQuestions(paperID);
		double score = 0;
		int numStudents[] = new int[5];
		
		for(int i=0; i<listSize; i++)
		{
			score = (marksList[i] * 100)/questions;
			
			if  (score < 35)
			{
				numStudents[0] = numStudents[0] +1;	
			}
			else if  ( score >= 35 && score < 55)
			{
				numStudents[1] = numStudents[1] +1;	
			}
			else if  ( score >= 55 && score < 65)
			{
				numStudents[2] = numStudents[2] +1;	
			}
			else if  ( score >= 65 && score < 75)
			{
				numStudents[3] = numStudents[3] +1;	
			}
			else if  ( score >= 75)
			{
				numStudents[4] = numStudents[4] +1;	
			}
		}
		return numStudents;
		
	}
	public int getStudentCount (String paperID) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rawCount = 0;
		String rawCountquerry = "select count(studentID) from result where paperID = '"+paperID+"'";
		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(rawCountquerry);
        rs = ps.executeQuery();
        while (rs.next())
	    {
        	rawCount = rs.getInt(1);
	    }
		
		return rawCount;
	}
	

}
