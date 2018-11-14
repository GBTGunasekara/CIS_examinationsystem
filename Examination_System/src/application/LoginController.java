package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class LoginController  {
	
	@FXML
	private Button Closebttn; 
	@FXML
	private Button Minimizebttn; 
	@FXML
	private Button TeacherRegbtn, loginbtn; 
	
	
	@FXML
	private void handleClose(MouseEvent event)
	{
		System.exit(0);
	}
	
	@FXML
	private void handleMinimize(MouseEvent event)
	{
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}

	
	
	@FXML
	private void promptTeacherReg(MouseEvent event) throws Exception
	{
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/TeacherRegGUI.fxml"));
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void promptStudentReg(MouseEvent event) throws Exception
	{
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/StudentRegGUI.fxml"));
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void MovetoMenue(MouseEvent event) throws Exception
	{
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/TeacherHomeGUI.fxml"));
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
