package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AdminStudentProfileFunction {
	
	public AdminStudentProfileFunction() {
		super();
	}
	
	public String[] getUsrDetails(String userID) {
		String [] UserDetails = new String[7];
		try {
			Connection con = DBconnection.Connect();
			ResultSet rs = con.createStatement().executeQuery("SELECT studentID, stName, stEmail, stDOB, stGender, stPassword, stStatus FROM student where studentID = '"+userID+"' ");
	
			if(rs.next()) {
				UserDetails[0]=rs.getString(1);
				UserDetails[1]=rs.getString(2);
				UserDetails[2]=rs.getString(3);
				UserDetails[3]=rs.getString(4);
				UserDetails[4]=rs.getString(5);
				UserDetails[5]=rs.getString(6);
				UserDetails[6]=rs.getString(7);

			}
	
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error\n" + e);
		}
		
		return UserDetails;
	}
	
	public void updateDetails(String uid, String uName, String uEmail, String uPword, String uGender, LocalDate uDOB, String uStatus) {
		
		if(uid.equals("") || uName.equals("") || uEmail.equals("") || uPword.equals("") || uStatus.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Complete data fields");
			return;
		}
		
		else if  (uDOB == null)
		{
				JOptionPane.showMessageDialog(null, "Insert DOB");
				return;

		}
		
		try {
		String DOBtoString = uDOB.toString();
		PreparedStatement ps;
		
		String updateQuery = "UPDATE student SET studentID='"+uid+"',stName='"+uName+"',stEmail='"+uEmail+"',"
				+ "stPassword='"+uPword+"',stDOB='"+DOBtoString+"',stGender='"+uGender+"',stStatus= '"+uStatus+"'"
						+ " WHERE studentID = '"+uid+"'";
		
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(updateQuery);
			 if(ps.executeUpdate() > 0)
	            {
	                JOptionPane.showMessageDialog(null, "Updated");
	            }
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void deleteStudentFunc(String uid) {
		PreparedStatement ps;
		String DelQuery = "Delete From student Where studentID = '"+uid+"'";
		try {
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(DelQuery);
			if(ps.executeUpdate() > 0) 
			{
	            JOptionPane.showMessageDialog(null, "Deleted");

			}
		} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
	}

}

