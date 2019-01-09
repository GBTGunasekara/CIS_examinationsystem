package application;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class StudentProfileController implements Initializable {
	
	@FXML
	private Button Closebttn4; 
	@FXML
	private Button Minimizebttn4; 
	@FXML
	private Button ClassListbtn, PaperListbtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private Label userIDlbl;
	@FXML
	private JFXTextField stID;
	@FXML
	private JFXTextField stName;
	@FXML
	private JFXTextField stEmail;
	@FXML
	private JFXDatePicker stDOB;
	@FXML
	private JFXPasswordField stPasswd;
	@FXML
	private JFXPasswordField stRePasswd;
	//@FXML
	//private JFXButton tePPic;
	@FXML
	private JFXRadioButton stGenderMale;
	@FXML
	private JFXRadioButton stGenderFemale;
	@FXML
	private ToggleGroup stGender;
	@FXML
	private Label UIDlbl;
	@FXML
	private Label systemTimelbl;
	
	public void setUserID (String userID) //set user ID on GUI
	{
		UIDlbl.setText(userID);
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
	private void MovetoStudentClassList(MouseEvent event) throws Exception
	{
		String link = "/application/StudentViewClassGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoStudentPaperList(MouseEvent event) throws Exception
	{
		String link = "/application/StudentPaperListGUI.fxml";
		fxmlLoader(link);
	}
	
	@FXML
	private void selectGender(MouseEvent event) {
		if(stGenderMale.isSelected()) {
			stGenderFemale.setSelected(false);
		}
		if(stGenderFemale.isSelected()) {
			stGenderMale.setSelected(false);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		StudentDrawerController ad1 = new StudentDrawerController();
		ad1.StudentDrawer(Hamburger, Drawer);
		liveDateTime (); 
	}
	
	public void SetUserDetails(String userID) throws MalformedURLException, RemoteException, NotBoundException {
		
		StudentProfileFunctionInterface  loginobj = (StudentProfileFunctionInterface) 
				Naming.lookup("rmi://localhost:1099/StudentProfile");
		
		//StudentProfileFunction spf = new StudentProfileFunction();
		String[] UserDetails = new String[6]; 
		//String userID2 = "TID123";
		UserDetails = loginobj.getUsrDetails(userID);
		
		LocalDate DOB =  LocalDate.parse(UserDetails[3]);
		
		stID.setText(UserDetails[0]);
		stName.setText(UserDetails[1]);
		stEmail.setText(UserDetails[2]);
		stDOB.setValue(DOB);
		String gender = UserDetails[4];
		if(gender=="Male") {
			stGenderMale.setSelected(true);
			stGenderFemale.setSelected(false);
		}
		else {
			stGenderFemale.setSelected(true);
			stGenderMale.setSelected(false);
		};
		
		String password = UserDetails[5];
		stPasswd.setText(password);
		//tePPic.setText(UserDetails[6]);
			
		stRePasswd.setText(password);
	}
	
	@FXML
	private void updateStudent() throws RemoteException {
		String uid = stID.getText();
		String uName = stName.getText();
		String uEmail = stEmail.getText();
		LocalDate uDOB= stDOB.getValue();
		
		String uGender = null;
		if(stGenderMale.isSelected())
			uGender = "Male";
		else if(stGenderFemale.isSelected())
			uGender = "Female";
		else if(!stGenderMale.isSelected() || !stGenderFemale.isSelected())
			JOptionPane.showMessageDialog(null, "Select Gender");
		
		String uPword = stPasswd.getText();
		String uRePword = stRePasswd.getText();
		
		if(!uPword.equals(uRePword)) {
			JOptionPane.showMessageDialog(null, "Passwords Don't Match");
			return;
		}
		StudentProfileFunction spf = new StudentProfileFunction();
		spf.updateDetails(uid, uName, uEmail, uPword, uGender, uDOB);
		
		
		
	}
	@FXML
	private void viewResultChart (MouseEvent event) throws Exception
	{
	
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/StudentProfileResultChartGUI.fxml"));
		
		Parent root = loader.load();
		//Stage stage = new Stage();
		//Parent root = FXMLLoader.load(getClass().getResource("/application/TeacherViewResultChartGUI.fxml"));
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
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
	
}
	

