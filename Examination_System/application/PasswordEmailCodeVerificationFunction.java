package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class PasswordEmailCodeVerificationFunction {

	public boolean checkCode(String email, String VCode) {
		boolean check = false;
		String sql = "Select email, password from systememail where email = '"+email+"'";
		ResultSet rs = null;
		String[] res = new String[2];
		try {
		Connection con = DBconnection.Connect();
		rs = con.createStatement().executeQuery(sql);
		
		if(rs.next()) {
			res[0]=rs.getString(1);
			res[1]=rs.getString(2);
		}
		
		if(res[0].equals(email) && res[1].equals(VCode))
			check = true;
		else
			check = false;
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return check;
	}

	public void deleteVcode(String email, String vCode) {
		PreparedStatement ps;
		String DelQuery = "Delete From systememail Where email = '"+email+"' and password = '"+vCode+"'";
		try {
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(DelQuery);
			if(ps.executeUpdate() > 0) 
			{
	            System.out.println("verification code deleted.");

			}
		} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
	}
	
}
