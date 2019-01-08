package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class StudentProfileResultChartFunction {
	
	
	
	
	public int getRowCount(String stID, String clID) {
		int rowCount = 0;
		
		String sql = "select COUNT(r.paperID) from result r, studentclass c, paper p where p.paperID = r.paperid and"
				+ " r.studentID = c.studentID and c.classID = p.classID and c.studentID = '"+stID+"' and c.classID = '"+clID+"'";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(sql);
		rs = ps.executeQuery();
		
		if(rs.next()) {
			
			rowCount = rs.getInt(1);
		}
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rowCount;
	}

	public String[][] ViewMarks(String stID, String clID) {  //Get PaperIDs and Marks of the Given Student.
		
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int rows = getRowCount(stID, clID);
		String results[][] = new String[rows][2];
		
		String sql = "select r.paperID , r.Marks from result r, studentclass c, paper p where p.paperID = r.paperid and"
				+ " r.studentID = c.studentID and c.classID = p.classID and"
				+ " c.studentID = '"+stID+"' and c.classID = '"+clID+"'";
		
		
		try {
			
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int i = 0;
				results[i][0] = rs.getString(1);
				results[i][1] = rs.getString(2);
				i++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		
		return results;
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

}
