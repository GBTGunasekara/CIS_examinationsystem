package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class LoginController  {
	
	
	@FXML
	private Button Closebttn, Minimizebttn, TeacherRegbtn, loginbtn;
	
	@FXML
	private TextField email; 
	
	
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

	//this function contains fxml file loader. the sting value pass the  relevent fxml link
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
	private void promptTeacherReg(MouseEvent event) throws Exception
	{
		String linktoTeacherReg = "/application/TeacherRegGUI.fxml";
		fxmlLoader(linktoTeacherReg);
		
	}
	
	@FXML
	private void promptStudentReg(MouseEvent event) throws Exception
	{
		String linktoStudentReg = "/application/StudentRegGUI.fxml";
		fxmlLoader(linktoStudentReg);
		
	}
	
	@FXML
	private void MovetoMenue(MouseEvent event) throws Exception
	{
		String userid = email.getText(); //get the entered value and check the user catagory through it.
		
		if (userid.equals("SID") || userid.equals("sid"))
		{
			String linktoStudent = "/application/StudentHomeGUI.fxml";
			fxmlLoader(linktoStudent);
		}
		else if (userid.equals("TID") || userid.equals("tid"))
		{
			String linktoTeacher = "/application/TeacherHomeGUI.fxml";
			fxmlLoader(linktoTeacher);
		}
		else if (userid.equals("AID") || userid.equals("aid"))
		{
			String linktoAdmin = "/application/AdminHomeGUI.fxml";
			fxmlLoader(linktoAdmin);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid login");
		}
	
		
	}
	
}
