package application;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentAnswerPaperFunctionInterface extends Remote{
	public String selectFirstQuestion (String paperID) throws RemoteException;
	public String [] selectFirstAnswerset (String paperID) throws RemoteException;
	public  String [][] loadQuestionsList (String paperID, int noOfQs) throws RemoteException;
	public String [][] loadAnswerlist (String paperID, int Qno) throws RemoteException;
	public void InsertStudentAnswer (String stID, String paID,String qeID, String anID) throws RemoteException;
}
