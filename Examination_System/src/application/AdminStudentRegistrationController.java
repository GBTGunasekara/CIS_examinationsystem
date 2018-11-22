package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminStudentRegistrationController {
	@FXML
	private Button Closebttn4; 
	@FXML
	private Button Minimizebttn4; 
	@FXML
	private Button TeacherRegbtn, StudentProfilebtn;
	
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
		
	}
	
	@FXML
	private void MovetoStudentProfile(MouseEvent event) throws Exception
	{
		String link = "/application/StudentProfileGUI.fxml";
		fxmlLoader(link);
		
	}
}
