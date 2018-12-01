package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

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

public class TeacherProfileController implements Initializable{
	
	@FXML
	private Button Closebttn, Minimizebttn, ClassListbtn, PaperListbtn;
	
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private Label userIDlbl;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		
		
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
	
	
	public void SetUserDetails(String userID) {
		
		
		TeacherProfileFunction tpf = new TeacherProfileFunction();
		String[] UserDetails = new String[6]; 
		//String userID2 = "TID123";
		UserDetails = tpf.getUsrDetails(userID);
		
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
}
