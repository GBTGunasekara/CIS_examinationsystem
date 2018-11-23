package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StudentHomeController {

	@FXML
	private Button Closebttn3;
	@FXML
	private Button Minimizebttn3, AnswerPaperbtn, ViewPaperbtn, ViewResultsbtn, JoinClassbtn, ViewClassbtn, StudentProbtn;
	
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
	private void MovetoSelectPaper(MouseEvent event) throws Exception
	{
		String link = "/application/StudentSelectPaperGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoViewPaper(MouseEvent event) throws Exception
	{
		String link = "/application/StudentPaperListGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoViewResults(MouseEvent event) throws Exception
	{
		String link = "/application/StudentViewResultsGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoJoinClass(MouseEvent event) throws Exception
	{
		String link = "/application/StudentJoinClassGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoViewClass(MouseEvent event) throws Exception
	{
		String link = "/application/StudentViewClassGUI.fxml";
		fxmlLoader(link);
		
	}
	
	@FXML
	private void MovetoStudentProfile(MouseEvent event) throws Exception
	{
		String link = "/application/StudentProfileGUI.fxml";
		fxmlLoader(link);
		
	}

	
}
