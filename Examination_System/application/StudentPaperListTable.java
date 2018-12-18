package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentPaperListTable {


	private StringProperty paperID;
	private StringProperty paperPassword;
	private StringProperty classID;
	private StringProperty noQuestions;
	private StringProperty marks;
	//private StringProperty noAnswers;
	private StringProperty answeredTime;
	private StringProperty releaseDateTime;
	private StringProperty terminateDateTime;
	
	
	public StudentPaperListTable ()
	{

		this.paperID = new SimpleStringProperty();
		this.paperPassword = new SimpleStringProperty();
		this.classID = new SimpleStringProperty();
		this.noQuestions = new SimpleStringProperty();
		this.marks = new SimpleStringProperty();
		//this.noAnswers = new SimpleStringProperty();
		this.answeredTime = new SimpleStringProperty();
		this.releaseDateTime = new SimpleStringProperty();
		this.terminateDateTime = new SimpleStringProperty();
		
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
	
	public String getPaperPassword ()
	{
		return paperPassword.get() ;
	}
	public void  setPaperPassword (String pPwd)
	{
		this.paperPassword.set(pPwd);
	}
	public StringProperty getPwd()
	{
		return paperPassword;
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
	
	public String getReleaseDateTime ()
	{
		return releaseDateTime.get();
	}
	public void setReleaseDate (String rlDT)
	{
		this.releaseDateTime.set(rlDT);
	}
	public StringProperty getReDateTime()
	{
		return releaseDateTime;
	}
	
	public String getTerminateDateTime ()
	{
		return terminateDateTime.get();
	}
	public void setTerminateDate (String teDT)
	{
		this.terminateDateTime.set(teDT);
	}
	public StringProperty getTeDateTime()
	{
		return terminateDateTime;
	}
	

	
}
