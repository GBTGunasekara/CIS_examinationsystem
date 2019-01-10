package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
    private JFXTextField UIDbox;

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
		
		String Email = EmailBox.getText();
		PasswordEmailVerificationFunction pevf = new PasswordEmailVerificationFunction();
		pevf.sendEmail(Email);
		
		
	}
	
	@FXML
	private void nexbtn(MouseEvent event) throws Exception {
		
		saveUserDetails(EmailBox.getText(), UIDbox.getText());
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
	
	public void saveUserDetails (String email, String uid) throws IOException
	{
		UserDetails uidobj = new UserDetails(); 						//create object of userDetails class
		uidobj.email = email; 										//assign the userIDtxt value to  created obj's userID attribute  
		uidobj.userID = uid; 	
					
		FileOutputStream fos = new  FileOutputStream("userfile.txt");	//create object of given file
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(uidobj); 										//write the user id on the file
		oos.close();													//close ObjectOutputStream
		fos.close(); 													// close FileOutputStream
		
	}

}
