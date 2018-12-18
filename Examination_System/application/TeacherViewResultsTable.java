package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TeacherViewResultsTable {

	private StringProperty studentID;
	private StringProperty studentName;
	private StringProperty marks;
	private StringProperty answeredTime;

	
	public TeacherViewResultsTable ()
	{
		this.studentID = new SimpleStringProperty();
		this.studentName = new SimpleStringProperty();
		this.marks = new SimpleStringProperty();
		this.answeredTime = new SimpleStringProperty();
	
	}
	
	public String getStudentID ()
	{
		return studentID.get();
	}
	public void  setStudentID (String sid)
	{
		this.studentID.set(sid);;
	}
	public StringProperty getSID()
	{
		return studentID;
	}
	
	public String getstudentName ()
	{
		return studentName.get() ;
	}
	public void  setstudentName (String stNm)
	{
		this.studentName.set(stNm);
	}
	public StringProperty getstName()
	{
		return studentName;
	}
	
	public String getMarks ()
	{
		return marks.get();
	}
	public void setMarks(String noAns)
	{
		this.marks.set(noAns);
	}
	public StringProperty getMk()
	{
		return marks;
	}
	
	public String getAnsweredTime ()
	{
		return answeredTime.get();
	}
	public void setAnsweredTime (String CtDate)
	{
		this.answeredTime.set(CtDate);
	}
	public StringProperty getAnsTime()
	{
		return answeredTime;
	}
	
}
