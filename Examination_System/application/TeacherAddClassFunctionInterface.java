package application;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TeacherAddClassFunctionInterface extends Remote {
	
	public void createClass (int teID, int clID, String subName, int grade, String clName, String location) throws RemoteException;
}
