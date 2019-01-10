package application;

import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class PasswordResetFunction {

	public void resetPwrod(String Pword, String rePword, String uid) {
		
		String pw = Pword;
		String rpw = rePword;
		String userID =  uid;
		try {
			PreparedStatement ps;
			
			if(pw.equals(rpw) && CheckUSercategory(uid).equals("student")) {
				
				String sql = "Update student Set stPassword = '"+Pword+"' where studentID = '"+uid+"'";
				try {
					ps = (PreparedStatement) DBconnection.Connect().prepareStatement(sql);
					if(ps.executeUpdate() > 0)
		            {
		                JOptionPane.showMessageDialog(null, "Password Changed Successfuly");
		            }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				
				
			}
			
			else if(pw.equals(rpw) && CheckUSercategory(uid).equals("teacher")) {
				
				String sql = "Update teacher SET tePassword = '"+Pword+"' where teacherID = '"+uid+"'";
				try {
					ps = (PreparedStatement) DBconnection.Connect().prepareStatement(sql);
					if(ps.executeUpdate() > 0)
		            {
		                JOptionPane.showMessageDialog(null, "Password Changed Successfuly");
		            }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				
				
			}
			
			else if(pw.equals(rpw) && CheckUSercategory(uid).equals("admin")) {
				
				String sql = "Update admin SET adPassword = '"+Pword+"' where adminID = '"+uid+"'";
				try {
					ps = (PreparedStatement) DBconnection.Connect().prepareStatement(sql);
					if(ps.executeUpdate() > 0)
		            {
		                JOptionPane.showMessageDialog(null, "Password Changed Successfuly");
		            }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				
				
			}
			
			else {
				if (!pw.equals(rpw)) {
					JOptionPane.showMessageDialog(null, "Passwords does not match.");
				}
				else if (!CheckUSercategory(uid).equals("student") && !CheckUSercategory(uid).equals("teacher") && !CheckUSercategory(uid).equals("admin")) {
					JOptionPane.showMessageDialog(null, "Invalid UserID");
				
				
			}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String CheckUSercategory (String userID) throws RemoteException
	{
		
        String lettersSet = userID.substring(0,3);
        String UserCat = null;
        
   
        if (lettersSet.equals("SID"))
        {
        	 UserCat = "student";
        }
        else if (lettersSet.equals("TID"))
        {
        	 UserCat ="teacher";
        }
        else if (lettersSet.equals("AID"))
        {
        	 UserCat = "admin";
        }
        else
        {
        	UserCat = "invalid";
        	
        }
        
       
		return UserCat;
    
	}

}
