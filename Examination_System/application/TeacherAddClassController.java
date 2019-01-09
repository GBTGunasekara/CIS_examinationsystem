package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TeacherAddClassController implements Initializable{
	
	@FXML
	private Button Closebttn3, Minimizebttn3, savebtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	public JFXTextField teacherIDbox;
	@FXML
	public JFXTextField classIDbox;
	@FXML
	public JFXTextField subNamebox;
	@FXML
	public JFXTextField gradebox;
	@FXML
	public JFXTextField classNamebox;
	@FXML
	public JFXTextField locNamebox;
	@FXML
	public Button clearbtn;
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
	private void CreateClass() throws RemoteException, MalformedURLException, NotBoundException 
	{
		
		int teID = Integer.parseInt(teacherIDbox.getText());
		int classID = Integer.parseInt(classIDbox.getText());
		int grade = Integer.parseInt(gradebox.getText());
		String subName = subNamebox.getText();
		String className = classNamebox.getText();
		String location = locNamebox.getText();

		TeacherAddClassFunctionInterface  tacf = (TeacherAddClassFunctionInterface) 
				Naming.lookup("rmi://localhost:1099/TeacherAddClass");
	    	    
		//TeacherAddClassFunction tac1 = new TeacherAddClassFunction();
		tacf.createClass(teID,classID,subName, grade,className,location);
		
	}
	
	@FXML
	private void clear(MouseEvent event) {
		
		teacherIDbox.setText("");
		classIDbox.setText("");
		gradebox.setText("");
		subNamebox.setText("");
		classNamebox.setText("");
		locNamebox.setText("");
		
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
				TeacherDrawerController ad1 = new TeacherDrawerController();
				ad1.TeacherDrawer(Hamburger, Drawer);
				liveDateTime ();
				try {
					setUserID ();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
}
