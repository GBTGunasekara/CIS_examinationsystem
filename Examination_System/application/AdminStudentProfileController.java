package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
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

public class AdminStudentProfileController implements Initializable{
	
	@FXML
	private Button Closebttn4; 
	@FXML
	private Button Minimizebttn4; 
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private Button ClassListbtn, PaperListbtn;
	@FXML
	private Label UIDlLbl;
	@FXML
	private JFXTextField stID;
	@FXML
	private JFXTextField stName;
	@FXML
	private JFXTextField stEmail;
	@FXML
	private JFXDatePicker stDOB;
	//stGender
	@FXML
	private ToggleGroup stGender;
	@FXML
	private JFXRadioButton stGenderMale;
	@FXML
	private JFXRadioButton stGenderFemale;
	@FXML
	private JFXTextField stPassword;
	@FXML
	private JFXTextField stRePassword;
	@FXML
	private JFXTextField stStatus;
	@FXML
	private JFXTextField searchBox;
	@FXML
	private Button searchButton;
	@FXML
	private Button classListButton;
	@FXML
	private Button UpdateButton;
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
	
	public void searchFunc() {
		String uid = searchBox.getText();
		SetUserDetails(uid);
	}
	
	@FXML
	private void updateStudent() {
		String uid = searchBox.getText();
		String Nuid = stID.getText();
		String uName = stName.getText();
		String uEmail = stEmail.getText();
		LocalDate uDOB= stDOB.getValue();
		
		String uGender = null;
		if(stGenderMale.isSelected())
			uGender = "Male";
		else if(stGenderFemale.isSelected())
			uGender = "Female";
		else if(!stGenderMale.isSelected() && !stGenderFemale.isSelected())
			JOptionPane.showMessageDialog(null, "Select Gender");
		
		String uPword = stPassword.getText();
		String uStatus = stStatus.getText();
		
		AdminStudentProfileFunction aspf = new AdminStudentProfileFunction();
		aspf.updateDetails(uid, Nuid, uName, uEmail, uPword, uGender, uDOB, uStatus);
		
	}
	
public void SetUserDetails(String userID) {
		
		AdminStudentProfileFunction aspf = new AdminStudentProfileFunction();
		String[] UserDetails = new String[6]; 
		UserDetails = aspf.getUsrDetails(userID);
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
		}
		String password = UserDetails[5];
		stPassword.setText(password);
		stStatus.setText(UserDetails[6]);	
	}

	@FXML
	private void deleteStudent() {
		String uid = stID.getText();
	
		AdminStudentProfileFunction aspf = new AdminStudentProfileFunction();
		aspf.deleteStudentFunc(uid);
	
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
}
