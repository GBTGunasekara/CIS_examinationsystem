package application;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;

public interface TeacherRegFunctionInterface extends Remote {
	
	public void createTeacherAccount(String tid, String tname, String temail, String tpassword, LocalDate tdob, String tgender, String timgPath, String trepassword) throws FileNotFoundException,RemoteException;
}
