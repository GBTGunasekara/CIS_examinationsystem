package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

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

public class AdminHomeController implements Initializable {
	@FXML
	private Button Closebttn4; 
	@FXML
	private Button Minimizebttn4; 
	@FXML
	private Button TeacherRegbtn, Classregbtn, TecherProfilebtn, StudentProfilebtn, SystemSettingsbtn, AdminProfilebtn; 
	@FXML
	private Label systemTimelbl;
	@FXML
	private Label UIDlbl;
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
	private void MovetoAdminTeacherReg(MouseEvent event) throws Exception
	{
		String link = "/application/AdminTeacherRegistrationGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
		
	}
	
	@FXML
	private void MovetoClassReg(MouseEvent event) throws Exception
	{
		String link = "/application/AdminClassListGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	private void MovetoTeacherProfile(MouseEvent event) throws Exception
	{
		String link = "/application/AdminTeacherProfileGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	private void MovetoStudentProfile(MouseEvent event) throws Exception
	{
		String link = "/application/AdminStudentProfileGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	private void MovetoSystemSettings(MouseEvent event) throws Exception
	{
		String link = "/application/AdminSettingsGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoAdminProfile(MouseEvent event) throws Exception
	{
		String link = "/application/AdminProfileGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	public void setUserID (String userID)
	{
		UIDlbl.setText(userID);
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
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			liveDateTime();
		}
		
}
