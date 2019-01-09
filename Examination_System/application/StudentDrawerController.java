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

public class StudentDrawerController {
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
	private void MovetoStudentHome(MouseEvent event) throws Exception
	{
		String link = "/application/StudentHomeGUI.fxml";
		fxmlLoader(link);
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
		
	}
	
	@FXML
	private void MovetoSelectPaper(MouseEvent event) throws Exception
	{
		String link = "/application/StudentSelectPaperGUI.fxml";
		fxmlLoader(link);
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
		
	}
	
	@FXML
	private void MovetoViewPaper(MouseEvent event) throws Exception
	{
		String link = "/application/StudentPaperListGUI.fxml";
		fxmlLoader(link);
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	
	@FXML
	private void MovetoViewResults(MouseEvent event) throws Exception
	{
		String link = "/application/StudentViewResultsGUI.fxml";
		fxmlLoader(link);
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	
	@FXML
	private void MovetoJoinClass(MouseEvent event) throws Exception
	{
		String link = "/application/StudentJoinClassGUI.fxml";
		fxmlLoader(link);
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	
	@FXML
	private void MovetoViewClass(MouseEvent event) throws Exception
	{
		String link = "/application/StudentViewClassGUI.fxml";
		fxmlLoader(link);
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	
	@FXML
	private void MovetoStudentProfile(MouseEvent event) throws Exception
	{
		String link = "/application/StudentProfileGUI.fxml";
		fxmlLoader(link);
		Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow(); //close current window
		stage2.close();
	}
	
	//Reference - https://www.youtube.com/watch?v=tgV8dDP9DtM
	// https://github.com/afsalashyana/JavaFX-Tutorial-Codes/blob/master/JavaFX%20Navigation%20Drawer/src/genuinecoder/main/MainController.java
	public void StudentDrawer(JFXHamburger Hamburger, JFXDrawer Drawer)
	{
		try {
			VBox box =  FXMLLoader.load(getClass().getResource("/application/StudentDrawerGUI.fxml"));
             
            
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
