package application;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

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

public class StudentResultPaperController implements Initializable{

	@FXML
	private Button Closebttn4; 
	@FXML
	private Button Minimizebttn4; 
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private Label teacherIDlbl;
	@FXML
	private Label classIDlbl;
	@FXML
	private Label paperIDlbl;
	@FXML
	private Label noQusetionlbl;
	@FXML
	private Label noAnswerlbl;
	@FXML
	private Label resultlbl;
	@FXML
	private JFXDatePicker ReleaseDatedp;
	@FXML
	private JFXDatePicker ReleaseTimedp;
	@FXML
	private JFXDatePicker TerminateDatedp;
	@FXML
	private JFXDatePicker TerminateTimedp;
	@FXML
	private Label UIDlbl;
	@FXML
	private Label systemTimelbl;
	
	public void setUserID (String userID) //set user ID on GUI
	{
		UIDlbl.setText(userID);
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
	
	public void fxmlLoader(String link) throws Exception
	{
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource(link));
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void MovetoStudentHome(MouseEvent event) throws Exception
	{
		String link = "/application/StudentHomeGUI.fxml";
		fxmlLoader(link);
		
	}
	
	public void  paperResults(String techerID, String classID, String paperID, int noQuestions ) throws MalformedURLException, RemoteException, NotBoundException
	{
		  teacherIDlbl.setText(techerID);
		 classIDlbl.setText(classID);
		 paperIDlbl.setText(paperID);
		 noQusetionlbl.setText(String.valueOf(noQuestions));
		String studentID = "STID0123"; 
		
		StudentResultPaperInterface  showResult = (StudentResultPaperInterface) 
				Naming.lookup("rmi://localhost:1099/StudentResultPaper");
		
		 //StudentAnswerPaperFunction sapf = new StudentAnswerPaperFunction();
		resultlbl.setText(String.valueOf(showResult.resultCalculate(paperID, studentID))); //set the the result to label
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
	}
	
}
