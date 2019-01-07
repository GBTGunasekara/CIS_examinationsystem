package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminHomeController {
	@FXML
	private Button Closebttn4; 
	@FXML
	private Button Minimizebttn4; 
	@FXML
	private Button TeacherRegbtn, Classregbtn, TecherProfilebtn, StudentProfilebtn, SystemSettingsbtn, AdminProfilebtn; 
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
	private void MovetoAdminTeacherReg(MouseEvent event) throws Exception
	{
		String link = "/application/AdminTeacherRegistrationGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
		
	}
	
	@FXML
	private void MovetoClassReg(MouseEvent event) throws Exception
	{
		String link = "/application/AdminClassListGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	private void MovetoTeacherProfile(MouseEvent event) throws Exception
	{
		String link = "/application/AdminTeacherProfileGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	private void MovetoStudentProfile(MouseEvent event) throws Exception
	{
		String link = "/application/AdminStudentProfileGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	private void MovetoSystemSettings(MouseEvent event) throws Exception
	{
		String link = "/application/AdminSettingsGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoAdminProfile(MouseEvent event) throws Exception
	{
		String link = "/application/AdminProfileGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
}
