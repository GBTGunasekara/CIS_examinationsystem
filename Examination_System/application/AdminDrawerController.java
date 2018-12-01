package application;


import java.io.IOException;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminDrawerController {
	
	@FXML
	private Button Home;
	
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
	private void MovetoAdminHome(MouseEvent event) throws Exception
	{
		String link = "/application/AdminHomeGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
		
	}
	
	@FXML
	private void MovetoAdminTeacherReg(MouseEvent event) throws Exception
	{
		String link = "/application/AdminTeacherRegistrationGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
		
	}
	
	@FXML
	private void MovetoClassReg(MouseEvent event) throws Exception
	{
		String link = "/application/AdminClassListGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	private void MovetoTeacherProfile(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherProfileGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	private void MovetoStudentProfile(MouseEvent event) throws Exception
	{
		String link = "/application/StudentProfileGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	@FXML
	private void MovetoSystemSettings(MouseEvent event) throws Exception
	{
		//String link = "/application/AdminSystemSettingsGUI.fxml";
		//fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoAdminProfile(MouseEvent event) throws Exception
	{
		String link = "/application/AdminProfileGUI.fxml";
		fxmlLoader(link);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	//Reference - https://www.youtube.com/watch?v=tgV8dDP9DtM
	// https://github.com/afsalashyana/JavaFX-Tutorial-Codes/blob/master/JavaFX%20Navigation%20Drawer/src/genuinecoder/main/MainController.java
	public void AdminDrawer(JFXHamburger Hamburger, JFXDrawer Drawer)
	{
		try {
			VBox box =  FXMLLoader.load(getClass().getResource("/application/AdminDrawerGUI.fxml"));
             
            
            Drawer.setSidePane(box);
        } catch (IOException ex) {
        	System.out.println(ex);;
        }
		
		HamburgerBackArrowBasicTransition burgertask = new HamburgerBackArrowBasicTransition(Hamburger);
		burgertask.setRate(-1);
		Hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
		burgertask.setRate(burgertask.getRate()*-1);
		burgertask.play();
		if (Drawer.isShown())
		{
			Drawer.close();
			Drawer.toBack();
		}
		else
		{
			Drawer.open();
			Drawer.toFront();
		}
		});
	}
}
