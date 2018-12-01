package tables;

public class ViewClassListTable {
	
	String clID, clName, grade, subName, location, noStudents;

	public ViewClassListTable(String clID, String clName, String grade, String subName, String location,
			String noStudents) {
		super();
		this.clID = clID;
		this.clName = clName;
		this.grade = grade;
		this.subName = subName;
		this.location = location;
		this.noStudents = noStudents;
	}

	public String getClID() {
		return clID;
	}

	public void setClID(String clID) {
		this.clID = clID;
	}

	public String getClName() {
		return clName;
	}

	public void setClName(String clName) {
		this.clName = clName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNoStudents() {
		return noStudents;
	}

	public void setNoStudents(String noStudents) {
		this.noStudents = noStudents;
	}
	
	

}
