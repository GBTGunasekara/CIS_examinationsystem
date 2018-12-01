package application;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface LoginFunctionsInterface extends Remote {
	
	public String CheckUSercategory(String userID) throws RemoteException;
	public boolean userIDcheck(String userID) throws RemoteException, SQLException; 
	public boolean userPasswordcheck (String userPassword, String userID) throws RemoteException, SQLException;
	
}
