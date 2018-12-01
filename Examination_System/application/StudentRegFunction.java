package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class StudentRegFunction extends UnicastRemoteObject implements StudentRegFunctionInterface {

	public StudentRegFunction() throws RemoteException{
		
	}
	
	public void createStudentAccount (String sid, String sname, String semail, String spassword, LocalDate sdob, String sgender, String simgPath, String srepassword) throws FileNotFoundException, RemoteException
	{
		
		
		if(sid.equals("") || sname.equals("") || semail.equals("") || spassword.equals("") || srepassword.equals("")) 
		{
			JOptionPane.showMessageDialog(null, "Complete data fields");
		}
		else if (!spassword.equals(srepassword))
		{
			JOptionPane.showMessageDialog(null, "Passwords are not similar");
		}
		else if  (sdob == null)
		{
				JOptionPane.showMessageDialog(null, "Insert DOB");
		}
		else if (simgPath == null)
		{
				JOptionPane.showMessageDialog(null, "Insert image");
		}
		else
		{
			InputStream imagePath = new FileInputStream(new File(simgPath));
			String dob1 = sdob.toString();
			PreparedStatement ps;
			String tRegQuery = "INSERT INTO student(studentID, stName, stEmail, stPassword, stDOB, stGender, stProPic) VALUES (?,?,?,?,?,?,?)";
		try
		{
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(tRegQuery);
			ps.setString(1, sid);
            ps.setString(2, sname);
            ps.setString(3, semail);
            ps.setString(4, spassword);
            ps.setString(5, dob1);
            ps.setString(6, sgender);
            ps.setBinaryStream(7, (InputStream)imagePath);
            
            if(ps.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null, "New User Add");
            }
		}
		
		
		
	 catch (SQLException ex) 
		{
		 	System.out.println(ex);
        	JOptionPane.showMessageDialog(null, "error");
	 	}
		}
	}
	
	
}
