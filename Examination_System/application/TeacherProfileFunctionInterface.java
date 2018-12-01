package application;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TeacherProfileFunctionInterface extends Remote{

	public String[] getUsrDetails(String userID)throws RemoteException;
}
