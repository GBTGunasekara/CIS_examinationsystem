package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

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

public class AdminSettingsController implements Initializable{

	@FXML
	private Button Closebttn4; 
	@FXML
	private Button Minimizebttn4; 
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		AdminDrawerController ad1 = new AdminDrawerController();
		ad1.AdminDrawer(Hamburger, Drawer);
		liveDateTime (); 
		try {
			setUserID ();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
