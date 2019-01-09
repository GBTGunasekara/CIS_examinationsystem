package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

public class TeacherProfileController implements Initializable{
	
	@FXML
	private Button Closebttn, Minimizebttn, ClassListbtn, PaperListbtn;
	
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private Label UIDlbl;
	@FXML
	private JFXTextField teID;
	@FXML
	private JFXTextField teName;
	@FXML
	private JFXTextField teEmail;
	@FXML
	private JFXDatePicker teDOB;
	@FXML
	private JFXPasswordField tePasswd;
	@FXML
	private JFXPasswordField teRePasswd;
	@FXML
	private JFXRadioButton teGenderMale;
	@FXML
	private JFXRadioButton teGenderFemale;
	@FXML
	private ToggleGroup teGender;
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
	private void MovetoClassList(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherViewClassGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoPaperList(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherPapersListGUI.fxml";
		fxmlLoader(link);
		
	}

	
	
	@FXML
	private void selectGender(MouseEvent event) {
		if(teGenderMale.isSelected()) {
			teGenderFemale.setSelected(false);
		}
		if(teGenderFemale.isSelected()) {
			teGenderMale.setSelected(false);
		}
	}
	
	
	public void SetUserDetails(String userID) throws RemoteException, MalformedURLException, NotBoundException {
		
		
		TeacherProfileFunctionInterface  loginobj = (TeacherProfileFunctionInterface) 
				Naming.lookup("rmi://localhost:1099/TeacherProfile");
		
		//TeacherProfileFunction tpf = new TeacherProfileFunction();
		String[] UserDetails = new String[6]; 
		//String userID2 = "TID123";
		
		UserDetails = loginobj.getUsrDetails(userID);
		
		LocalDate DOB =  LocalDate.parse(UserDetails[3]);
		
		teID.setText(UserDetails[0]);
		teName.setText(UserDetails[1]);
		teEmail.setText(UserDetails[2]);
		teDOB.setValue(DOB);
		String gender = UserDetails[4];
		if(gender=="Male") {
			teGenderMale.setSelected(true);
			teGenderFemale.setSelected(false);
		}
		else {
			teGenderFemale.setSelected(true);
			teGenderMale.setSelected(false);
		}
		
		String password = UserDetails[5];
		tePasswd.setText(password);
		//tePPic.setText(UserDetails[6]);
			
		teRePasswd.setText(password);	
	}
	
	
	@FXML
	private void updateTeacher() throws RemoteException {
		String uid = teID.getText();
		String uName = teName.getText();
		String uEmail = teEmail.getText();
		LocalDate uDOB= teDOB.getValue();
		
		String uGender = null;
		if(teGenderMale.isSelected())
			uGender = "Male";
		else if(teGenderFemale.isSelected())
			uGender = "Female";
		else if(!teGenderMale.isSelected() || !teGenderFemale.isSelected())
			JOptionPane.showMessageDialog(null, "Select Gender");
		
		String uPword = tePasswd.getText();
		String uRePword = teRePasswd.getText();
		
		if(!uPword.equals(uRePword)) {
			JOptionPane.showMessageDialog(null, "Passwords Don't Match");
			return;
		}
		TeacherProfileFunction tpf = new TeacherProfileFunction();
		tpf.updateDetails(uid, uName, uEmail, uPword, uGender, uDOB);
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
		
		liveDateTime();
		
		try {
			setUserID ();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
}
