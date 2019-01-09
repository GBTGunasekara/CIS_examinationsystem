package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PasswordResetController {
	
	@FXML
    private JFXButton Minimizebttn3;

    @FXML
    private JFXButton Closebttn3;

    @FXML
    private JFXTextField Pwordbox;
    @FXML
    private JFXTextField RePwordbox;

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
	@FXML
	private void resetPword(MouseEvent event) {
//		String uid = null;//*have to add user ID
//		String newPword = Pwordbox.getText();
//		String newRePword = RePwordbox.getText();
//		
//		PasswordResetFunction prf = new PasswordResetFunction();
//		prf.resetPwrod(newPword, newRePword, uid);
		
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	
}
