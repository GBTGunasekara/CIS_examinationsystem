package application;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

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

public class StudentHomeController implements Initializable{

	@FXML
	private Button Closebttn3;
	@FXML
	private Button Minimizebttn3, AnswerPaperbtn, ViewPaperbtn, ViewResultsbtn, JoinClassbtn, ViewClassbtn, StudentProbtn;
	
	@FXML
	private Label UIDlbl;
	
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
		Parent root = FXMLLoader.load(getClass().getResource(link));
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void MovetoSelectPaper(MouseEvent event) throws Exception
	{
		String link = "/application/StudentSelectPaperGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoViewPaper(MouseEvent event) throws Exception
	{
		String link = "/application/StudentPaperListGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoViewResults(MouseEvent event) throws Exception
	{
		String link = "/application/StudentViewResultsGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoJoinClass(MouseEvent event) throws Exception
	{
		String link = "/application/StudentJoinClassGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoViewClass(MouseEvent event) throws Exception
	{
		String link = "/application/StudentViewClassGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoStudentProfile(MouseEvent event) throws Exception
	{
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/StudentProfileGUI.fxml"));
	
		Parent root = loader.load();
	
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	

		
		StudentProfileController spc = loader.getController();
		 spc.SetUserDetails(UIDlbl.getText());
		
	}

	
	
	public void SystemTime ()
	{
		Thread dateTimeRunning  = new Thread()
		{
			public void runDateTime() 
			{
				try {
				for(;;)
				{
					Calendar gcalendar = new GregorianCalendar();
					int day = gcalendar.get(Calendar.DAY_OF_MONTH);
					int month = gcalendar.get(Calendar.MONTH);
					int year = gcalendar.get(Calendar.YEAR);
				
					int second = gcalendar.get(Calendar.SECOND);
					int minute = gcalendar.get(Calendar.MINUTE);
					int hour = gcalendar.get(Calendar.HOUR);
					
					systemTimelbl.setText(day + " "+ month + " " + year+ "  "+ hour +":"+ minute + ":" + second);
					
						sleep(1000);
				}
				} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
		};
		dateTimeRunning.start();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		SystemTime();
	}
}
