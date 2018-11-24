package application;

import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TeacherRegFunction {

	public void createTeacherAccount (String tid, String tname, String temail, String tpassword, String tdob, String tgender, InputStream timage)
	{
		PreparedStatement ps;
		String tRegQuery = "INSERT INTO teacher(teacherID, teName, teEmail, tePassword, teDOB, teGender, teProPic) VALUES (?,?,?,?,?,?,?)";
		try
		{
			
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(tRegQuery);
			ps.setString(1, tid);
            ps.setString(2, tname);
            ps.setString(3, temail);
            ps.setString(4, tpassword);
            ps.setString(5, tdob);
            ps.setString(6, tgender);
            ps.setBinaryStream(7, (InputStream)timage);
            
            if(ps.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null, "New User Add");
            }
		}
		
		
		
	 catch (SQLException ex) {
        System.out.println(ex);
        JOptionPane.showMessageDialog(null, "error");
    }
	}
	
	public void testingdb (String tid)
	{
		DBconnection dbn = new DBconnection();
		Connection c1 = dbn.Connect();
		PreparedStatement ps;
		String tRegQuery = "INSERT INTO testing(teacherID) VALUES (?)";
		try
		{
			
			ps = (PreparedStatement) c1.prepareStatement(tRegQuery);
			ps.setString(1, tid);
            
            
            if(ps.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null, "New User Add");
            }
		}
		
		
		
	 catch (SQLException ex) {
        System.out.println(ex);
        JOptionPane.showMessageDialog(null, "error");
    }
	}
}
