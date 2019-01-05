package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.rmi.Naming;
import java.sql.SQLException;

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
	private TextField userIDtxt; 
	@FXML
	private TextField userPasswordtxt; 
	
	
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
	
	public String getUID() {
		
		String UID=userIDtxt.getText();
		return UID;
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
		
		String userID = userIDtxt.getText();
		String userPassword = userPasswordtxt.getText(); 
		
		LoginFunctionsInterface  loginobj = (LoginFunctionsInterface) 
				Naming.lookup("rmi://localhost:1099/Login");
		
		
		//LoginFunctions lf = new LoginFunctions();
		if  (userID.equals(""))
		{
				JOptionPane.showMessageDialog(null, "Insert userID ");
		}
		else if  (userPassword.equals(""))
		{
				JOptionPane.showMessageDialog(null, "Insert user Password");
		}
		else
		{
			try {
				if (loginobj.CheckUSercategory(userID).equals("student")) //load student menu
				{
					if (loginobj.userIDcheck(userID) == true)
						{
							if (loginobj.userPasswordcheck(userPassword,userID) == true)
							{
								String linktoStudent = "/application/StudentHomeGUI.fxml";
								fxmlLoader(linktoStudent);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Invalid user password");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid userID");
						}
				}
				
				else if (loginobj.CheckUSercategory(userID).equals("teacher")) //load student menu
				{
					if (loginobj.userIDcheck(userID) == true)
						{
							if (loginobj.userPasswordcheck(userPassword,userID) == true)
							{
								String linktoStudent = "/application/TeacherHomeGUI.fxml";
								fxmlLoader(linktoStudent);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Invalid user password");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid userID");
						}
				}
				
				else if (loginobj.CheckUSercategory(userID).equals("admin")) //load student menu
				{
					if (loginobj.userIDcheck(userID) == true)
						{
							if (loginobj.userPasswordcheck(userPassword,userID) == true)
							{
								String linktoStudent = "/application/AdminHomeGUI.fxml";
								fxmlLoader(linktoStudent);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Invalid user password");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid userID");
						}
				}
					
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid userID Format");
				}
					
			
			
		} catch (SQLException e) 
		{
			System.out.println(e);
		}
		}
		
	}
	
}
