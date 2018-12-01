package application;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StudentResultPaperController implements Initializable{

	@FXML
	private Button Closebttn4; 
	@FXML
	private Button Minimizebttn4; 
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private Label teacherIDlbl;
	@FXML
	private Label classIDlbl;
	@FXML
	private Label paperIDlbl;
	@FXML
	private Label noQusetionlbl;
	@FXML
	private Label noAnswerlbl;
	@FXML
	private Label resultlbl;
	@FXML
	private JFXDatePicker ReleaseDatedp;
	@FXML
	private JFXDatePicker ReleaseTimedp;
	@FXML
	private JFXDatePicker TerminateDatedp;
	@FXML
	private JFXDatePicker TerminateTimedp;
	
	
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
	private void MovetoStudentHome(MouseEvent event) throws Exception
	{
		String link = "/application/StudentHomeGUI.fxml";
		fxmlLoader(link);
		
	}
	
	public void  paperResults(String techerID, String classID, String paperID, int noQuestions ) throws MalformedURLException, RemoteException, NotBoundException
	{
		  teacherIDlbl.setText(techerID);
		 classIDlbl.setText(classID);
		 paperIDlbl.setText(paperID);
		 noQusetionlbl.setText(String.valueOf(noQuestions));
		String studentID = "STID012546"; 
		
		StudentResultPaperInterface  showResult = (StudentResultPaperInterface) 
				Naming.lookup("rmi://localhost:1099/StudentResultPaper");
		
		 //StudentAnswerPaperFunction sapf = new StudentAnswerPaperFunction();
		resultlbl.setText(String.valueOf(showResult.resultCalculate(paperID, studentID))); //set the the rsult to label
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		StudentDrawerController ad1 = new StudentDrawerController();
		ad1.StudentDrawer(Hamburger, Drawer);
	}
	
}
