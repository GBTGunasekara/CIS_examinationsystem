package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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
    
    private String uid;

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
	
	public void setDetails () throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new  FileInputStream("userfile.txt");		//create the object of give file
		ObjectInputStream ois = new ObjectInputStream(fis);
		UserDetails uobj  = (UserDetails) ois.readObject();				//read file object
		uid=(uobj.userID);									//set the current logged user's id on UIDlbl label   
		ois.close(); 													//close ObjectInputStream
		fis.close();													//close FileInputStream
	}
	
	@FXML
	private void resetPword(MouseEvent event) {
		
		String newPword = Pwordbox.getText();
		String newRePword = RePwordbox.getText();
		try {
			setDetails();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PasswordResetFunction prf = new PasswordResetFunction();
		prf.resetPwrod(newPword, newRePword, uid);
		
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	
}
