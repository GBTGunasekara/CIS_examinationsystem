package tables;

public class ViewClassTable {
	
	String sID, sName, sEmail, sStatus, nPaper;

	public ViewClassTable(String sID, String sName, String sEmail, String sStatus, String nPaper) {
		super();
		this.sID = sID;
		this.sName = sName;
		this.sEmail = sEmail;
		this.sStatus = sStatus;
		this.nPaper = nPaper;
	}

	public String getsID() {
		return sID;
	}

	public void setsID(String sID) {
		this.sID = sID;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}

	public String getnPaper() {
		return nPaper;
	}

	public void setnPaper(String nPaper) {
		this.nPaper = nPaper;
	}
	
	
}
 	