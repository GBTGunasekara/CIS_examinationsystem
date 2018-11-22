package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminTeacherRegistrationController {
	
	@FXML
	private Button Closebttn4; 
	@FXML
	private Button Minimizebttn4; 
	@FXML
	private Button StudentRegbtn, TeacherProfilebtn;
	
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
	private void MovetoAdminStudentReg(MouseEvent event) throws Exception
	{
		String link = "/application/AdminStudentRegistrationGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoTeacherProfile(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherProfileGUI.fxml";
		fxmlLoader(link);
		
	}
}
