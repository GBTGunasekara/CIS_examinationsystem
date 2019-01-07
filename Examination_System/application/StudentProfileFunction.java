package application;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class StudentProfileFunction extends UnicastRemoteObject implements StudentProfileFunctionInterface{
	
	protected StudentProfileFunction() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String[] getUsrDetails(String userID) {
		String [] UserDetails = new String[6];
		
		try {
			Connection con = DBconnection.Connect();
			ResultSet rs = con.createStatement().executeQuery("SELECT studentID, stName, stEmail, stDOB, stGender, stPassword FROM student where studentID = '"+userID+"' ");
	
			if(rs.next()) {
				UserDetails[0]=rs.getString(1);
				UserDetails[1]=rs.getString(2);
				UserDetails[2]=rs.getString(3);
				UserDetails[3]=rs.getString(4);
				UserDetails[4]=rs.getString(5);
				UserDetails[5]=rs.getString(6);
				//UserDetails[6]=rs.getString(7);
			}
	
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error\n" + e);
		}
		
		return UserDetails;
	}
	
	public void updateDetails(String uid, String uName, String uEmail, String uPword, String uGender, LocalDate uDOB) {
		
		if(uid.equals("") || uName.equals("") || uEmail.equals("") || uPword.equals(""))
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
				+ "stPassword='"+uPword+"',stDOB='"+DOBtoString+"',stGender='"+uGender+"' WHERE studentID = '"+uid+"'";
		
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(updateQuery);
			 if(ps.executeUpdate() > 0)
	            {
	                JOptionPane.showMessageDialog(null, "Updated");
	            }
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
}