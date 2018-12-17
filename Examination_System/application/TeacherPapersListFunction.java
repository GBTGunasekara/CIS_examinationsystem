package application;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeacherPapersListFunction 
{
	
	//Reference (You Tube video)
	// B2 Tech (2017).JavaFx Database Operations - Part 8 - Display the DB values in TableView. [Video] Available at: https://www.youtube.com/watch?v=L8iuBXl-F8U [Accessed Day : 17-12-2018].

	

	
	
	public static ObservableList  selcetPaperList1 (String teacherID) throws RemoteException, SQLException
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String showPaperDetails = "select paperID,pePassword,classID,numQuestion,numAnswers,createDateTime from paper where teacherID = '"+teacherID+"'"; //select all paper details relevant to login teacher
        
		ObservableList paperlist = FXCollections.observableArrayList();
        
        ps = (PreparedStatement) DBconnection.Connect().prepareStatement(showPaperDetails);
        rs = ps.executeQuery();
        while (rs.next())
        {
        	TeacherPaperListTable tplt = new TeacherPaperListTable(); 
        	tplt.setPaperID(rs.getString("paperID"));
        	tplt.setPaperPassword(rs.getString("pePassword"));
        	tplt.setClassID(rs.getString("classID"));
        	tplt.setNoQuestions(rs.getString("numQuestion"));
        	tplt.setNoAnswers(rs.getString("numAnswers"));
        	tplt.setCreateDate(rs.getString("createDateTime"));
        	paperlist.add(tplt);
        }
		return paperlist;
	    
	}
	
}
