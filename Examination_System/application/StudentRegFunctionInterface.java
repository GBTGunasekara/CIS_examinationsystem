package application;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;

public interface StudentRegFunctionInterface extends Remote {

	public void createStudentAccount (String sid, String sname, String semail, String spassword, LocalDate sdob, String sgender, String simgPath, String srepassword) throws FileNotFoundException,RemoteException;
}
