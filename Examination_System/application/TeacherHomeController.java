package application;

import javafx.collections.ObservableList;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TeacherHomeController {
	
	@FXML 
	private Button Closebttn2, Minimizebttn2, AddPaperbtn, ViewPaperbtn, ViewResultsbtn, CreateClassbtn, ViewClassbtn, TeacherProbtn; 
	
	
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
	private Label UIDlbl;
	

	
	
	
	@FXML
	private void MovetoAddPaper(MouseEvent event) throws Exception
	{
		
		
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/TeacherAddPaperGUI.fxml"));
	
		Parent root = loader.load();
	
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	

		
	}
	
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
	private void MovetoViewPaper(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherPapersListGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoViewResults(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherViewResultsGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoAddClass(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherAddClassGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoViewClass(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherViewClassGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoTeacherProfile(MouseEvent event) throws Exception
	{
		
		
		
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/TeacherProfileGUI.fxml"));
	
		Parent root = loader.load();
	
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	
		String userIDlabel =UIDlbl.getText();
		
		TeacherProfileController tpc = loader.getController();
		 tpc.SetUserDetails(userIDlabel);
	}

}
