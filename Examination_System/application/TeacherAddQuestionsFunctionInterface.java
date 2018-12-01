package application;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TeacherAddQuestionsFunctionInterface extends Remote {
	public void SaveNextQuestion (String paID, String qeID , String Question, String AnswerA, String AnswerB, String AnswerC, String AnswerD, int QeNo, String correctAns) throws RemoteException;
}
