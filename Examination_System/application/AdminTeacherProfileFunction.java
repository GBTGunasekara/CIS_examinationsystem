import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class AdminTeacherProfileFunction extends UnicastRemoteObject implements AdminTeacherProfileFunctionInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected AdminTeacherProfileFunction() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	public String[] getUsrDetails(String userID) {
		String [] UserDetails = new String[6];
		try {
			Connection con = DBconnection.Connect();
			ResultSet rs = con.createStatement().executeQuery("SELECT teacherID, teName, teEmail, teDOB, teGender, tePassword FROM teacher where teacherID = '"+userID+"' ");
	
			if(rs.next()) {
				UserDetails[0]=rs.getString(1);
				UserDetails[1]=rs.getString(2);
				UserDetails[2]=rs.getString(3);
				UserDetails[3]=rs.getString(4);
				UserDetails[4]=rs.getString(5);
				UserDetails[5]=rs.getString(6);
			}
	
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error\n" + e);
		}
		
		return UserDetails;
	}
}