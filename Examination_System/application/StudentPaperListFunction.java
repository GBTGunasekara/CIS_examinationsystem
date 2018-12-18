package application;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentPaperListFunction {

	//Reference (You Tube video)
		// B2 Tech (2017).JavaFx Database Operations - Part 8 - Display the DB values in TableView. [Video] Available at: https://www.youtube.com/watch?v=L8iuBXl-F8U [Accessed Day : 17-12-2018].

		public static ObservableList  selcetAnsPaperList (String studentID) throws RemoteException, SQLException
		{
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			String showAnsPaperDetails = "select p.paperID,p.pePassword,p.classID,p.numQuestion,r.Marks,r.ansDateTime,p.releaseDate,p.releaseTime,p.terminateDate,p.terminateTime from paper p , result r where p.paperID = r.paperID and r.studentID = '"+studentID+"'"; //select all paper details relevant to login teacher
	        
			ObservableList ansPaperlist = FXCollections.observableArrayList();
	        
	        ps = (PreparedStatement) DBconnection.Connect().prepareStatement(showAnsPaperDetails);
	        rs = ps.executeQuery();
	        while (rs.next())
	        {
	        	StudentPaperListTable stplt = new StudentPaperListTable(); 
	        	stplt.setPaperID(rs.getString("paperID"));
	        	stplt.setPaperPassword(rs.getString("pePassword"));
	        	stplt.setClassID(rs.getString("classID"));
	        	stplt.setNoQuestions(rs.getString("numQuestion"));
	        	stplt.setMarks(rs.getString("Marks"));
	        	//tplt.setNoAnswers(rs.getString("numAnswers"));
	        	stplt.setAnsweredTime(rs.getString("ansDateTime"));
	        	
	        	String releaseDateTime = rs.getString("releaseDate") +" "+ rs.getString("releaseTime");
	        	stplt.setReleaseDate(releaseDateTime);
	        	
	        	String terminateDateTime = rs.getString("terminateDate") +" "+ rs.getString("terminateTime");
	        	stplt.setTerminateDate(terminateDateTime);

	        	ansPaperlist.add(stplt);
	        }
			return ansPaperlist;
		} 
	
}
