package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class TeacherHomeController implements Initializable{
	
	@FXML 
	private Button Closebttn2, Minimizebttn2, AddPaperbtn, ViewPaperbtn, ViewResultsbtn, CreateClassbtn, ViewClassbtn, TeacherProbtn; 
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
	
	
	@FXML
	private void MovetoAddPaper(MouseEvent event) throws Exception
	{
		
		
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/TeacherAddPaperGUI.fxml"));
	
		Parent root = loader.load();
	
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
		
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
	private void MovetoViewPaper(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherPapersListGUI.fxml";
		fxmlLoader(link);
		
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	
	@FXML
	private void MovetoViewResults(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherViewResultsGUI.fxml";
		fxmlLoader(link);
		
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	
	@FXML
	private void MovetoAddClass(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherAddClassGUI.fxml";
		fxmlLoader(link);
		
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	
	@FXML
	private void MovetoViewClass(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherViewClassGUI.fxml";
		fxmlLoader(link);
		
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	
	@FXML
	private void MovetoTeacherProfile(MouseEvent event) throws Exception
	{

		
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/TeacherProfileGUI.fxml"));
	
		Parent root = loader.load();
	
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	
		String userIDlabel =UIDlbl.getText();
		
		TeacherProfileController tpc = loader.getController();
		 tpc.SetUserDetails(userIDlabel);
		 
		 Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		 stage2.close();
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
					        
					        systemTimelbl.setText(hour +":"+ minute + ":" + second+ "    "+ day + "/"+ month + "/" +year);
					    }),
					         new KeyFrame(Duration.seconds(1))
					    );
					    clock.setCycleCount(Animation.INDEFINITE);
					    clock.play();
			}

			@Override
			public void initialize(URL location, ResourceBundle resources) {
				// TODO Auto-generated method stub
				liveDateTime();
			}

}
