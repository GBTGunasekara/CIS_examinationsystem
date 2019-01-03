package application;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

import com.mysql.jdbc.PreparedStatement;

public class TeacherSubmitPaperFunction extends UnicastRemoteObject implements TeacherSubmitPaperFunctionInterface{
	
	protected TeacherSubmitPaperFunction() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void submitPaper (String paID, String paperPassword, LocalDate releaseDate, LocalTime releaseTime, LocalDate TerminateDate, LocalTime TerminateTime) throws RemoteException 
	{
		
		//java.sql.Timestamp sqlcurrentDateTime = new java.sql.Timestamp(createDateTime.getTime()); //convert util datetime into sqltime date
		
		
	if  (releaseDate == null)
	{
			JOptionPane.showMessageDialog(null, "Insert Release Date");
	}
	else if  (releaseTime == null)
	{
			JOptionPane.showMessageDialog(null, "Insert Release Time");
	}	
	else if  (TerminateDate == null)
	{
			JOptionPane.showMessageDialog(null, "Insert Terminate Date");
	}	
	else if  (TerminateTime == null)
	{
			JOptionPane.showMessageDialog(null, "Insert Terminate Time");
	}	
	else
	{
		try
		{
			Date rdate = java.sql.Date.valueOf(releaseDate);
			Time  rtime = java.sql.Time.valueOf(releaseTime);
			Date Tdate = java.sql.Date.valueOf(TerminateDate);
			Time  Ttime = java.sql.Time.valueOf(TerminateTime);
		
			PreparedStatement ps;
		
			String submitpaper = "update paper set pePassword = '"+ paperPassword +"', releaseDate = '"+ rdate +"', releaseTime = '"+ rtime +"', terminateDate = '"+ Tdate +"', terminateTime = '"+ Ttime +"' where paperID = '"+ paID +"'";
			ps = (PreparedStatement) DBconnection.Connect().prepareStatement(submitpaper);
			ps.execute();
        
        
			if(ps.executeUpdate() > 0)
			{
				JOptionPane.showMessageDialog(null, "paper is submited");   
			}
	}
	
	

	catch (SQLException ex) 
		{
			System.out.println(ex);
    	JOptionPane.showMessageDialog(null, "error");
		}
	}
	}
	// reference - https://youtu.be/PBAX8r9YWW0
	
	public String GeneratePassword () throws RemoteException 
	{
		String password = "";
		
		for(int i = 0; i < 8; i++)
		{
			password = password + randomCharacter("abcdefghijklmnopqrstuwxyz");
			
		}
		String randomDigit = randomCharacter("0123456789");
		password = insertAtRandom(password, randomDigit);
		return password;
	}


	public String randomCharacter(String charachters) {
		int n = charachters.length();
		int r = (int) (n* Math.random());
		return charachters.substring(r, r+1);
	}
	
	public String insertAtRandom (String str, String toInsert)
	{
		int n = str.length();
		int r = (int)((n+1) * Math.random());
		return str.substring(0,r) + toInsert + str.substring(r);
	}
	
	//reference - https://www.youtube.com/watch?v=UMfjndwGwnM 
	public void generateEmail (String paperID, String paperPassword, String classID, String teacherID, String ReleaseDate, String TerminateDate)
	{
		try{
			boolean sessionDebug = false;
            String host ="smtp.gmail.com" ;
            String user = "mcqappuob@gmail.com";
            String pass = "mcqappUOB19";
           // String to = "buddhimith@gmail.com";
            String from = "mcqappuob@gmail.com";
            String subject = "New paper is uploaded";
            String messageText = "Dear Student;\n"
            		+ "New paper is uploaded into MCQ APP software. The paper details are mentioned below. Please enter paper ID and password into the software and do it after rleased. Good Luck.\n"
            		+ "Paper Details \n" 
            		+ "\t Paper ID              -  "  +paperID+ "\n"
            		+ "\t Paper Password        -  "  +paperPassword+ "\n"
            		+ "\t Class ID              -  "  +classID+ "\n"
            		+ "\t Teacher ID            -  "  +teacherID+ "\n"
            		+ "\t Release Date & Time   -  "  +ReleaseDate+ "\n"
            		+ "\t Terminate Date & Time -  "  +TerminateDate+ "\n";
           
            String[] emailset = getEmailList(classID);
            int emailsetSize = emailset.length;
            Properties props = System.getProperties();
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
           
            for (int i = 0 ; i < emailsetSize ; i++)
            {
            	String to = emailset[i];
            //java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
            }
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
	}
	
	public String[] getEmailList (String classID) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int rawCount = getRowCount(classID);
		String [] emailList = new String[rawCount];
		
		String showResultsDetails = "select s.stEmail from student s, studentclass c where s.studentID = c.studentID and c.classID = '"+classID+"'"; 

	    ps = (PreparedStatement) DBconnection.Connect().prepareStatement(showResultsDetails);
        rs = ps.executeQuery();
        int i = 0; 
        while (rs.next())
	    {
        	emailList[i] = rs.getString(1);
        	i=i+1;
	    }
		return emailList;
	
	}
	
	public int getRowCount (String classID) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rawCount = 0;
		String rawCountquerry = "select count(*) from student s , studentclass c where s.studentID = c.studentID and c.classID = '"+classID+"'";
		ps = (PreparedStatement) DBconnection.Connect().prepareStatement(rawCountquerry);
        rs = ps.executeQuery();
        while (rs.next())
	    {
        	rawCount = rs.getInt(1);
	    }
		
		return rawCount;
	}
}
