package application;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

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
			res[2]=rs.getString(2);
		}
		
		if(res[1]==email && res[2]==VCode)
			check = true;
		else
			check = false;
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return check;
	}
	
}
