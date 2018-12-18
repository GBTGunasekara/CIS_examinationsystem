package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentViewResultsTable {

	private StringProperty paperID;
	private StringProperty classID;
	private StringProperty teacherID;
	private StringProperty noQuestions;
	private StringProperty marks;
	private StringProperty answeredTime;

	
	public StudentViewResultsTable ()
	{
		this.paperID = new SimpleStringProperty();
		this.classID = new SimpleStringProperty();
		this.teacherID = new SimpleStringProperty();
		this.noQuestions = new SimpleStringProperty();
		this.marks = new SimpleStringProperty();
		this.answeredTime = new SimpleStringProperty();
	
	}
	
	public String getPaperID ()
	{
		return paperID.get();
	}
	public void  setPaperID (String pid)
	{
		this.paperID.set(pid);;
	}
	public StringProperty getPID()
	{
		return paperID;
	}
	
	public String getTeacherID ()
	{
		return teacherID.get() ;
	}
	public void  setTeacherID (String pPwd)
	{
		this.teacherID.set(pPwd);
	}
	public StringProperty getTID()
	{
		return teacherID;
	}
	
	public String getClassID ()
	{
		return classID.get();
	}
	public void setClassID (String CID)
	{
		this.classID.set(CID);
	}
	public StringProperty getCID()
	{
		return classID;
	}
	
	public String getNoQuestions ()
	{
		return noQuestions.get();
	}
	public void setNoQuestions(String noQ)
	{
		this.noQuestions.set(noQ);
	}
	public StringProperty getNoQ()
	{
		return noQuestions;
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
