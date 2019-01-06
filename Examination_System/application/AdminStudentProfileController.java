package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	private JFXRadioButton stGenderMale;
	@FXML
	private JFXRadioButton stGenderFemale;
	@FXML
	private JFXTextField stPassword;
	@FXML
	private JFXTextField stRePassword;
	@FXML
	private JFXTextField searchBox;
	@FXML
	private Button searchButton;
	@FXML
	private Button classListButton;

	
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
		stRePassword.setText(password);	
	}

}
