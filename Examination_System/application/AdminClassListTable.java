package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminClassListTable {
	
	private StringProperty classID;
	private StringProperty teacherID;
	private StringProperty className;
	private StringProperty subjectName;
	private IntegerProperty grade;
	private StringProperty location;
	
	public AdminClassListTable() {
		
		this.classID = new SimpleStringProperty();
		this.teacherID = new SimpleStringProperty();
		this.className = new SimpleStringProperty();
		this.subjectName = new SimpleStringProperty();
		this.grade = new SimpleIntegerProperty();
		this.location = new SimpleStringProperty();
	}
	//Getter and Setters
	//--ClassID--
	public String getClID() {
		return classID.get();
	}
	
	public void setClID(String string) {
		this.classID.set(string);
	}
	
	public StringProperty getClassID() {
		return classID;
	}
	
	//--TeacherID--
	public String getTeID() {
		return teacherID.get();
	}
	
	public void setTeID(String string) {
		this.teacherID.set(string);
	}
	
	public StringProperty getTeacherID() {
		return teacherID;
	}
	
	//--ClassName--
	public String getClName() {
		return className.get();
	}
	
	public void setClName(String Cname) {
		this.className.set(Cname);
	}
	
	public StringProperty getClassName() {
		return className;
	}
	
	//--SubjectName--
	
	public String getSubName() {
		return subjectName.get();
	}
	
	public void setSubName(String Sname) {
		this.subjectName.set(Sname);
	}
	
	public StringProperty getSubjectName() {
		return subjectName;
	}
	
	//--Grade--
	
	public int getGr() {
		return grade.get();
	}
	
	public void setGr(int gr) {
		this.grade.set(gr);
	}
	
	public IntegerProperty getGrade() {
		return grade;
	}
	
	//--Location--
	
	public String getLoc() {
		return location.get();
	}
	
	public void setLoc(String gr) {
		this.location.set(gr);
	}
	
	public StringProperty getLocation() {
		return location;
	}

}
