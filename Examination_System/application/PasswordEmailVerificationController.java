package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PasswordEmailVerificationController {
	
	@FXML
    private JFXButton Minimizebttn3;

    @FXML
    private JFXButton Closebttn3;

    @FXML
    private JFXTextField EmailBox;

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
	
	private void SubmmitEmail() {
		String Email = EmailBox.getText();
		PasswordEmailVerificationFunction pevf = new PasswordEmailVerificationFunction();
		pevf.sendEmail(Email);
	}


}
