package application;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class PasswordEmailVerificationFunction {
	
	public static String genPword() {
		String letters = "abcdefghijklmnopqrstuvwxyz";
		char[] AN = (letters + letters.toUpperCase() + "1234567890").toCharArray();
		StringBuilder Npass = new StringBuilder();
		for(int i=0; i<8; i++) {
			Npass.append(AN[new Random().nextInt(AN.length)]);
		}
		return Npass.toString();
	}

	public void sendEmail(String email) {
		Date d = Calendar.getInstance().getTime();
		SimpleDateFormat dateF = new SimpleDateFormat("Y-MM-dd hh:mm:ss");
		String StrDate = dateF.format(d);
			try{
				boolean sessionDebug = false;
				String NewP = genPword();
	            String host ="smtp.gmail.com" ;
	            String user = "mcqappuob@gmail.com";
	            String pass = "mcqappUOB19";
	           // String to = "buddhimith@gmail.com";
	            String from = "mcqappuob@gmail.com";
	            String subject = "New paper is uploaded";
	            String messageText = "Dear Student;\n"
	            		+ "The verification code to reset your password is '"+NewP+"'";
	           
//	            String[] emailset = getEmailList(classID);
//	            int emailsetSize = emailset.length;
	            Properties props = System.getProperties();
	            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	            props.put("mail.smtp.starttls.enable", "true");
	            props.put("mail.smtp.host", host);
	            props.put("mail.smtp.port", "587");
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.starttls.required", "true");
	           
//	            for (int i = 0 ; i < emailsetSize ; i++)
//	            {
	            	String to = email;
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
	           
	           String sql = "Insert into systememail (email, password, addedTime) Values (?,?,?)";
	           PreparedStatement ps = null;
	           ps = (PreparedStatement) DBconnection.Connect().prepareStatement(sql);
	           ps.setString(1, email);
	           ps.setString(2, NewP);
	           ps.setString(3, StrDate);
	           
	           if(ps.executeUpdate() > 0)
	            {
	                JOptionPane.showMessageDialog(null, "Verification Code Sent to '"+email+"'");
	                System.out.println("email and verification code added to database");
	            }
	           
	            //}
	        }catch(Exception ex)
	        {
	            System.out.println(ex);
	        }
		}
}
