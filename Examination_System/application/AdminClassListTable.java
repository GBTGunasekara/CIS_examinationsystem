package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminClassListTable {
	
	private IntegerProperty classID;
	private IntegerProperty teacherID;
	private StringProperty className;
	private StringProperty subjectName;
	private IntegerProperty grade;
	private StringProperty location;
	
	public AdminClassListTable() {
		
		this.classID = new SimpleIntegerProperty();
		this.teacherID = new SimpleIntegerProperty();
		this.className = new SimpleStringProperty();
		this.subjectName = new SimpleStringProperty();
		this.grade = new SimpleIntegerProperty();
		this.location = new SimpleStringProperty();
	}
	//Getter and Setters
	//--ClassID--
	public int getClID() {
		return classID.get();
	}
	
	public void setClID(int id) {
		this.classID.set(id);
	}
	
	public IntegerProperty getClassID() {
		return classID;
	}
	
	//--TeacherID--
	public int getTeID() {
		return teacherID.get();
	}
	
	public void setTeID(int id) {
		this.teacherID.set(id);
	}
	
	public IntegerProperty getTeacherID() {
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
