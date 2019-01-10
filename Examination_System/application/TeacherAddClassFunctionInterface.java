package application;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TeacherAddClassFunctionInterface extends Remote {
	
	public void createClass (String teID, String classID, String subName, int grade, String clName, String location) throws RemoteException;
}
