package application;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface StudentSelectPaperInterface extends Remote{
	public boolean paperIDcheck (String paperID) throws RemoteException, SQLException;
	public boolean paperPasswordcheck (String paperPassword, String paperID) throws RemoteException, SQLException;
	public String[] enrollPaper (String paperID) throws RemoteException;
}
