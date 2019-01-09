package application;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class StudentSelectPaperController implements Initializable {
	@FXML
	private Button Closebttn3, Minimizebttn3;
	@FXML
	private Button enrollbtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private Label teacherIDlbl;
	@FXML
	private Label classIDlbl;
	@FXML
	private JFXTextField paperIDtxt;
	@FXML
	private JFXTextField paperPasswordtxt;
	@FXML
	private Label noQusetionlbl;
	@FXML
	private Label noAnswerlbl;
	@FXML
	private Label ReleaseDatelbl;
	@FXML
	private Label ReleaseTimelbl;
	@FXML
	private Label TerminateDatelbl;
	@FXML
	private Label TerminateTimelbl;
	@FXML
	private Button searchPaperbtn; 
	@FXML
	private Label UIDlbl;
	@FXML
	private Label systemTimelbl;
	
	//set the logged user id on the GUI
	public void setUserID () throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new  FileInputStream("userfile.txt");		//create the object of give file
		ObjectInputStream ois = new ObjectInputStream(fis);
		UserDetails uobj  = (UserDetails) ois.readObject();				//read file object
		UIDlbl.setText(uobj.userID);									//set the current logged user's id on UIDlbl label   
		ois.close(); 													//close ObjectInputStream
		fis.close();													//close FileInputStream
	}
	
	@FXML
	private void handleClose(MouseEvent event)
	{
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void handleMinimize(MouseEvent event)
	{
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}
	
	@FXML
	private void MovetoAnswerPaper(MouseEvent event) throws Exception
	{
		String studentID = "STID0123";
		 StudentSelectPaperFunction sspf = new StudentSelectPaperFunction();
		if  ( paperIDtxt.getText().isEmpty() || classIDlbl.getText().isEmpty() || teacherIDlbl.getText().isEmpty() || noQusetionlbl.getText().isEmpty() )
		{
				JOptionPane.showMessageDialog(null, "Selcet a paper ");
		}
		 else  
		{
//			 if (sspf.checkStudent(studentID,paperIDtxt.getText()) == false)
//			 {
				 if (dateTimeCompare().equals("now")) 
				 {

				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/StudentAnswerPaperGUI.fxml"));
		
				Parent root = loader.load();
		
				stage.initStyle(StageStyle.UNDECORATED);
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
		
				StudentAnswerPaperController taqc = loader.getController();
				taqc.setStudentPaperDetails(paperIDtxt.getText(), noQusetionlbl.getText(),classIDlbl.getText(), teacherIDlbl.getText()); //pass this values to next GUI
				//taqc.setFirstQuestionAnswer(paperIDtxt.getText());
				 }
				 else if (dateTimeCompare().equals("before")) 
				 {
					 JOptionPane.showMessageDialog(null, "Paper is not released");
				 }
				 else if (dateTimeCompare().equals("after")) 
				 {
					 JOptionPane.showMessageDialog(null, "Paper is terminated");
				 }
				 else if (dateTimeCompare().equals("error")) 
				 {
					 JOptionPane.showMessageDialog(null, "Check your PC time");
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null, "error");
				 }
//			 }
//			 else
//			 {
//				 JOptionPane.showMessageDialog(null, "You have already completed this paper");
//			 }	 
		}
		
		
		
	}
	/*@FXML
	private void MovetoAnswerPaper(MouseEvent event) throws Exception
	{
		String link = "/application/StudentAnswerPaperGUI.fxml";
		fxmlLoader(link);
		
		
	}*/
	
	@FXML
	private void SearchPaper(MouseEvent event) throws HeadlessException, RemoteException, MalformedURLException, NotBoundException 
	{
		String paperID = paperIDtxt.getText();
		String paperPassword = paperPasswordtxt.getText(); 
		
		StudentSelectPaperInterface  SearchPe = (StudentSelectPaperInterface) 
				Naming.lookup("rmi://localhost:1099/StudentSelectPaper");
		
		//StudentSelectPaperFunction sspf = new StudentSelectPaperFunction();
		
		if  (paperID.equals(""))
		{
				JOptionPane.showMessageDialog(null, "Insert paperID ");
		}
		else if  (paperPassword.equals(""))
		{
				JOptionPane.showMessageDialog(null, "Insert Paper Password");
		}
		else
		{
		try {
			if (SearchPe.paperIDcheck(paperID) == true)
			{
				if (SearchPe.paperPasswordcheck(paperPassword,paperID) == true)
				{
					String peDetArr[] = SearchPe.enrollPaper(paperID);
					
					classIDlbl.setText(peDetArr[0]);
					teacherIDlbl.setText(peDetArr[1]);
					noQusetionlbl.setText(peDetArr[2]);
					noAnswerlbl.setText(peDetArr[3]);
					ReleaseDatelbl.setText(peDetArr[4]);
					ReleaseTimelbl.setText(peDetArr[5]);
					TerminateDatelbl.setText(peDetArr[6]);
					TerminateTimelbl.setText(peDetArr[7]);	
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid paper password");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid paperID");
			}
		} catch (SQLException e) 
		{
			System.out.println(e);
		}
		}
	}
	
	public String dateTimeCompare() throws ParseException, RemoteException
	{
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    Date date = new Date();
	    String currentdatestr = formatter.format(date); 
	    Date currentDate = formatter.parse(currentdatestr);
	    Date releaseDate = formatter.parse(ReleaseDatelbl.getText()) ;
	    Date terminateDate = formatter.parse(TerminateDatelbl.getText()) ;
	    
	   
	    SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");  
	    String currenTimestr = timeformatter.format(date); 
	    Date currentTime = timeformatter.parse(currenTimestr);
	    Date releaseTime = timeformatter.parse(ReleaseTimelbl.getText()) ;
	    Date terminateTime = timeformatter.parse(TerminateTimelbl.getText()) ;
	   
	    StudentSelectPaperFunction sspf = new StudentSelectPaperFunction();
	    String status = sspf.timeChecker(currentDate, currentTime, releaseDate, releaseTime, terminateDate, terminateTime);
	    return status;
  
	}
	// show live system time on the window 
	public void liveDateTime () 
	{
		//reference - https://stackoverflow.com/questions/42383857/javafx-live-time-and-date 
				Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {        
			        int second = LocalDateTime.now().getSecond();
			        int minute = LocalDateTime.now().getMinute();
			        int hour = LocalDateTime.now().getHour();
			        int day = LocalDateTime.now().getDayOfMonth();
			        int month = LocalDateTime.now().getMonthValue();
			        int year = LocalDateTime.now().getYear();
			        
			        systemTimelbl.setText(hour +":"+ minute + ":" + second+ "  "+ day + "/"+ month + "/" +year);
			    }),
			         new KeyFrame(Duration.seconds(1))
			    );
			    clock.setCycleCount(Animation.INDEFINITE);
			    clock.play();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		StudentDrawerController ad1 = new StudentDrawerController();
		ad1.StudentDrawer(Hamburger, Drawer);
		liveDateTime (); 
		try {
			setUserID ();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
