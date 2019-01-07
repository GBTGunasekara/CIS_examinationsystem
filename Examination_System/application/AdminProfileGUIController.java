package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminProfileGUIController implements Initializable{
	@FXML
	private Button Closebttn4; 
	@FXML
	private Button Minimizebttn4; 
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private Label UIDlLbl;
	@FXML
	private JFXTextField adID;
	@FXML
	private JFXTextField adName;
	@FXML
	private JFXTextField adEmail;
	@FXML
	private JFXDatePicker adDOB;
	//stGender
	@FXML
	private JFXRadioButton adGenderMale;
	@FXML
	private JFXRadioButton adGenderFemale;
	@FXML
	private JFXTextField adPassword;
	@FXML
	private JFXTextField adRePassword;
	
	
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
	private void getUID() {
		//String UID = LoginController.getUID();
		
	}
	
	public void SetUserDetails(String userID) {
		
		AdminProfileGUIFunction apguif = new AdminProfileGUIFunction();
		String[] UserDetails = new String[6]; 
		UserDetails = apguif.getUsrDetails(userID);
		LocalDate DOB =  LocalDate.parse(UserDetails[3]);
		
		adID.setText(UserDetails[0]);
		adName.setText(UserDetails[1]);
		adEmail.setText(UserDetails[2]);
		adDOB.setValue(DOB);
		String gender = UserDetails[4];
		if(gender=="Male") {
			adGenderMale.setSelected(true);
			adGenderFemale.setSelected(false);
		}
		else {
			adGenderFemale.setSelected(true);
			adGenderMale.setSelected(false);
		}
		String password = UserDetails[5];
		adPassword.setText(password);
		adRePassword.setText(password);	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		AdminDrawerController ad1 = new AdminDrawerController();
		ad1.AdminDrawer(Hamburger, Drawer);
	}
	
	
}
