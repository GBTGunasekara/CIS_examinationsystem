package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PasswordEmailCodeVerificationController {
	

	    @FXML
	    private JFXButton Minimizebttn3;

	    @FXML
	    private JFXButton Closebttn3;

	    @FXML
	    private JFXTextField VCodeBox;

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
		
		private void SubmmitVCode() {
			String email = "";//get email passed from previous window
			String VCode = VCodeBox.getText();
			PasswordEmailCodeVerificationFunction pecvf = new PasswordEmailCodeVerificationFunction();
			pecvf.checkCode(email,VCode);
		}

	


}
