package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TeacherProfileController {
	
	@FXML
	private Button Closebttn, Minimizebttn, ClassListbtn, PaperListbtn;
	
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
}