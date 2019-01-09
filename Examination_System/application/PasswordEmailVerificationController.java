package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	@FXML
	private void SubmmitEmail(MouseEvent event) throws Exception {
		
//		String Email = EmailBox.getText();
//		PasswordEmailVerificationFunction pevf = new PasswordEmailVerificationFunction();
//		pevf.sendEmail(Email);
		
		String linktoStudentReg = "/application/PasswordEmailCodeVerificationGUI.fxml";
		fxmlLoader(linktoStudentReg);
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	FXMLLoader loader;
	//this function contains fxml file loader. the sting value pass the  relevent fxml link
	public void fxmlLoader(String link) throws Exception
	{
		Stage stage = new Stage();
		 this.loader = new FXMLLoader(getClass().getResource(link));
		Parent root = loader.load();

		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

}
