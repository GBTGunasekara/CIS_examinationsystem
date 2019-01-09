package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StudentViewPaperController implements Initializable {

	@FXML
	private Button Closebttn3, Minimizebttn3, savebtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
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
		Drawer.toBack();
		liveDateTime (); 
	}
	
}
