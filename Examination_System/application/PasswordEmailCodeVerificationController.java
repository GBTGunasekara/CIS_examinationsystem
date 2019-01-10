package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PasswordEmailCodeVerificationController implements Initializable{
	

	    @FXML
	    private JFXButton Minimizebttn3;

	    @FXML
	    private JFXButton Closebttn3;

	    @FXML
	    private JFXTextField VCodeBox;
	    
	    @FXML
	    private JFXTextField Emailbox;

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
		private void SubmmitVCode (MouseEvent event) throws Exception{
			String email = Emailbox.getText();
			String VCode = VCodeBox.getText();
			
			PasswordEmailCodeVerificationFunction pecvf = new PasswordEmailCodeVerificationFunction();
			
			if(pecvf.checkCode(email,VCode)) {
			String linktoStudentReg = "/application/PasswordResetGUI.fxml";
			fxmlLoader(linktoStudentReg);
			Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
			stage2.close();
			pecvf.deleteVcode(email,VCode);
			}
			
			else if(VCodeBox.getText().equals(null)){
				JOptionPane.showMessageDialog(null, "Enter Verification Code");
			}
			
			
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
		
		public void setemail () throws IOException, ClassNotFoundException
		{
			FileInputStream fis = new  FileInputStream("userfile.txt");		//create the object of give file
			ObjectInputStream ois = new ObjectInputStream(fis);
			UserDetails uobj  = (UserDetails) ois.readObject();				//read file object
			Emailbox.setText(uobj.email);									//set the current logged user's id on UIDlbl label   
			ois.close(); 													//close ObjectInputStream
			fis.close();													//close FileInputStream
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			
			try {
				setemail ();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
				
}
