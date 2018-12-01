package tables;

public class PaperListTable {
	
	String PaperID, PaperPwd, clID, noQuest, noAns, relDate, terDate, noStd;

	public PaperListTable(String paperID, String paperPwd, String clID, String noQuest, String noAns, String relDate,
			String terDate, String noStd) {
		super();
		PaperID = paperID;
		PaperPwd = paperPwd;
		this.clID = clID;
		this.noQuest = noQuest;
		this.noAns = noAns;
		this.relDate = relDate;
		this.terDate = terDate;
		this.noStd = noStd;
	}

	public String getPaperID() {
		return PaperID;
	}

	public void setPaperID(String paperID) {
		PaperID = paperID;
	}

	public String getPaperPwd() {
		return PaperPwd;
	}

	public void setPaperPwd(String paperPwd) {
		PaperPwd = paperPwd;
	}

	public String getClID() {
		return clID;
	}

	public void setClID(String clID) {
		this.clID = clID;
	}

	public String getNoQuest() {
		return noQuest;
	}

	public void setNoQuest(String noQuest) {
		this.noQuest = noQuest;
	}

	public String getNoAns() {
		return noAns;
	}

	public void setNoAns(String noAns) {
		this.noAns = noAns;
	}

	public String getRelDate() {
		return relDate;
	}

	public void setRelDate(String relDate) {
		this.relDate = relDate;
	}

	public String getTerDate() {
		return terDate;
	}

	public void setTerDate(String terDate) {
		this.terDate = terDate;
	}

	public String getNoStd() {
		return noStd;
	}

	public void setNoStd(String noStd) {
		this.noStd = noStd;
	}
	
	

}
