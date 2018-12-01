package application;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;

public interface TeacherSubmitPaperFunctionInterface extends Remote{
	public void submitPaper (String paID, String paperPassword, LocalDate releaseDate, LocalTime releaseTime, LocalDate TerminateDate, LocalTime TerminateTime) throws RemoteException;
	public String GeneratePassword () throws RemoteException;
}
