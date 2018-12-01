package application;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentProfileFunctionInterface extends Remote{

	public String[] getUsrDetails(String userID) throws RemoteException;
	
}
