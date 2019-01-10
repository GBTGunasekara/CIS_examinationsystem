package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminClassListFunction {
	
public static ObservableList <AdminClassListTable> SelectClassList(String teacherID) throws ClassNotFoundException, SQLException{

		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select classID, teacherID, className, subjectName, grade, location from class"; // where teacherID = '"+teacherID+"'"; //Get Class Details for the logged in teacher
        
		ObservableList Classobj = FXCollections.observableArrayList();
        
        ps = (PreparedStatement) DBconnection.Connect().prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next())
        {
        	AdminClassListTable tvct = new AdminClassListTable(); 
        	tvct.setClID(rs.getString("classID"));
        	tvct.setTeID(rs.getString("teacherID"));
        	tvct.setClName(rs.getString("className"));
        	tvct.setSubName(rs.getString("subjectName"));
        	tvct.setGr(Integer.parseInt(rs.getString("grade")));
        	tvct.setLoc(rs.getString("location"));

        	
        	Classobj.add(tvct);
        }
		return Classobj;
	    
	}

		public void DeleteRow(String cid) {
				PreparedStatement ps;
				String DelQuery = "Delete From class Where classID = '"+cid+"'";
				try {
					ps = (PreparedStatement) DBconnection.Connect().prepareStatement(DelQuery);
					if(ps.executeUpdate() > 0) 
					{
			            JOptionPane.showMessageDialog(null, "Class Deleted");

					}
				} catch (SQLException e) {
		            JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				
			}
}


