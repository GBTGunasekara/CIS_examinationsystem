package application;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentResultPaperInterface extends Remote{
	public int resultCalculate (String paperID, String StudentID ) throws RemoteException;
}
