package application;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentViewResultsFunction {


	//Reference (You Tube video)
	// B2 Tech (2017).JavaFx Database Operations - Part 8 - Display the DB values in TableView. [Video] Available at: https://www.youtube.com/watch?v=L8iuBXl-F8U [Accessed Day : 17-12-2018].

	public static ObservableList  selcetStudentResultsList (String studentID) throws RemoteException, SQLException
	{
			
		PreparedStatement ps = null;
		ResultSet rs = null;
		String showResultsDetails = "select p.paperID,p.teacherID,p.classID,p.numQuestion,r.Marks,r.ansDateTime from paper p , result r where p.paperID = r.paperID and r.studentID = '"+studentID+"'"; //select all paper details relevant to login teacher
	        
		ObservableList resultslist = FXCollections.observableArrayList();
		
	    ps = (PreparedStatement) DBconnection.Connect().prepareStatement(showResultsDetails);
        rs = ps.executeQuery();
        while (rs.next())
	    {
        	StudentViewResultsTable stvrt = new StudentViewResultsTable(); 
        	stvrt.setPaperID(rs.getString("paperID"));
        	stvrt.setTeacherID(rs.getString("teacherID"));
        	stvrt.setClassID(rs.getString("classID"));
        	stvrt.setNoQuestions(rs.getString("numQuestion"));
        	stvrt.setMarks(rs.getString("Marks"));
        	stvrt.setAnsweredTime(rs.getString("ansDateTime"));

        	resultslist.add(stvrt);
	        }
			return resultslist;
		} 
	

}
