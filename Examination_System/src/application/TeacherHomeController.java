package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TeacherHomeController {
	
	@FXML
	private Button Closebttn2; 
	@FXML
	private Button Minimizebttn2; 
	
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

}
