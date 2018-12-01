package application;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface TeacherAddPaperInterface extends Remote {
	public String paperIDgenerate () throws RemoteException;
	public void createPaper (String teID, String clID, String paID, Date createDateTime, int noQuestions, int noAnswers) throws RemoteException;
}
