package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TeacherPaperListTable {

	
	private StringProperty paperID;
	private StringProperty paperPassword;
	private StringProperty classID;
	private StringProperty noQuestions;
	private StringProperty noAnswers;
	private StringProperty createDate;
	
	public TeacherPaperListTable ()
	{
		
		
		this.paperID = new SimpleStringProperty();
		this.paperPassword = new SimpleStringProperty();
		this.classID = new SimpleStringProperty();
		this.noQuestions = new SimpleStringProperty();
		this.noAnswers = new SimpleStringProperty();
		this.createDate = new SimpleStringProperty();
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
	
	public String getNoAnswers ()
	{
		return noAnswers.get();
	}
	public void setNoAnswers(String noAns)
	{
		this.noAnswers.set(noAns);
	}
	public StringProperty getNoAns()
	{
		return noAnswers;
	}
	
	public String getCreateDate ()
	{
		return createDate.get();
	}
	public void setCreateDate (String CtDate)
	{
		this.createDate.set(CtDate);
	}
	public StringProperty getCtDate()
	{
		return createDate;
	}
}
