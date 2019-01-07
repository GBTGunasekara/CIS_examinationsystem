package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentViewClassFunction {
	
public static ObservableList <TeacherViewClassTable> SelectClassList(String teacherID) throws ClassNotFoundException, SQLException{

		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select * from class"; // where teacherID = '"+teacherID+"'"; //Get Class Details for the logged in teacher
        
		ObservableList Classobj = FXCollections.observableArrayList();
        
        ps = (PreparedStatement) DBconnection.Connect().prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next())
        {
        	TeacherViewClassTable tvct = new TeacherViewClassTable(); 
        	tvct.setClID(Integer.parseInt(rs.getString("classID")));
        	tvct.setTeID(Integer.parseInt(rs.getString("teacherID")));
        	tvct.setClName(rs.getString("className"));
        	tvct.setSubName(rs.getString("subjectName"));
        	tvct.setGr(Integer.parseInt(rs.getString("grade")));
        	tvct.setLoc(rs.getString("location"));

        	
        	Classobj.add(tvct);
        }
		return Classobj;
	    
	}

}
