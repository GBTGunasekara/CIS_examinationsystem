package application;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

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

public class TeacherAddPaperController implements Initializable{
	
	@FXML 
	private Button Closebttn2, Minimizebttn2;
	
	@FXML
	private Button createbtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private JFXTextField teacherIDtxt;
	@FXML
	private JFXTextField classIDtxt;
	@FXML
	private JFXTextField paperIDtxt;
	@FXML
	private JFXTextField noQusetiontxt;
	@FXML
	private JFXTextField noAnswertxt;
	@FXML
	private JFXTextField subjcetNametxt;
	
	@FXML
	private Label systemTimelbl;
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource(link));
		
		Parent root = loader.load();
		
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
		TeacherAddQuestionController taqc = loader.getController();
		taqc.setTeacherPaperDetails(paperIDtxt.getText(), Integer.parseInt(noQusetiontxt.getText()),classIDtxt.getText(), teacherIDtxt.getText()); //pass this values to next GUI
	}
	
	@FXML
	private void MovetoAddQuestions(MouseEvent event) throws Exception
	{
		CreatePaper();
		
		String link = "/application/TeacherAddQuestionsGUI.fxml";
		fxmlLoader(link);
		
		
		
	}
	
	@FXML
	private void CreatePaper() throws RemoteException, MalformedURLException, NotBoundException 
	{
		
		String teacherID = teacherIDtxt.getText();
		String classID = classIDtxt.getText();
		String paperID = paperIDtxt.getText();
		String subjcetName = subjcetNametxt.getText();
		int noQuestions = Integer.parseInt(noQusetiontxt.getText());
		int noAnswers = Integer.parseInt(noAnswertxt.getText());

	    Date currentdatetime = new Date(); //get current date and time
	    
	    TeacherAddPaperInterface  PaperIDGen = (TeacherAddPaperInterface) 
				Naming.lookup("rmi://localhost:1099/TeacherAddPaper");
		PaperIDGen.createPaper(teacherID,classID,paperID, currentdatetime,noQuestions,noAnswers);
		/*
		String paperPassword = "123";
		//String teacherID = "TID123";
		String ReleaseDate = "rdate";
		String TerminateDate = "tdate";
		TeacherSubmitPaperFunction tsp = new TeacherSubmitPaperFunction();
		tsp.generateEmail(paperID, paperPassword, classID, teacherID, ReleaseDate, TerminateDate);*/
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
		
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		Drawer.toBack();
		
		liveDateTime();
		
		try {
			TeacherAddPaperInterface  PaperIDGen = (TeacherAddPaperInterface) 
					Naming.lookup("rmi://localhost:1099/TeacherAddPaper");
			paperIDtxt.setText(PaperIDGen.paperIDgenerate ());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	}
